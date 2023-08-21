package com.framework.web.config.initSecurityConfig.initLogin;

import com.framework.common.exception.login.LoginFailLimitException;
import com.framework.common.model.properties.InitTokenProperties;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.ResponseUtil;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.redis.RedisUtil;
import com.framework.common.util.system.IPUtil;
import com.framework.common.util.system.LogUtil;
import com.framework.model.system.SystemUser;
import com.framework.service.system.SystemLogService;
import com.framework.service.system.SystemUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Titel 身份验证失败处理程序
 * @Description 身份验证失败处理程序
 * @Author 龘鵺
 * @DateTime 2022/3/3 8:51
 * @Version 1.0
 */
//@Component
public class MyAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {
    private Logger log = LoggerFactory.getLogger(MyAuthenticationFailHandler.class);
    private InitTokenProperties initTokenProperties;
    private SystemLogService systemLogServiceImpl;
    private SystemUserService systemUserServiceImpl;
    private RedisUtil redisUtil;

    public MyAuthenticationFailHandler(InitTokenProperties initTokenProperties, SystemLogService systemLogServiceImpl, SystemUserService systemUserServiceImpl, RedisUtil redisUtil) {
        this.initTokenProperties = initTokenProperties;
        this.systemLogServiceImpl = systemLogServiceImpl;
        this.systemUserServiceImpl = systemUserServiceImpl;
        this.redisUtil = redisUtil;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.info("onAuthenticationFailure:{}", e.getMessage());
        ResponseResult rr = new ResponseResult();
//        log.info("onAuthenticationFailure:{}", rr.toJsonString());
        String username = request.getParameter("username");
        recordLoginTime(username);
        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            String key = "loginTimeLimit:" + username;
            String value = redisUtil.getString(key);
            if (StringUtils.isBlank(value)) {
                value = "0";
            }
            //获取已登录错误次数
            int loginFailTime = Integer.parseInt(value);
            int restLoginTime = initTokenProperties.getLoginTimeLimit() - loginFailTime;
            log.info("onAuthenticationFailure:用户" + username + "登录失败，还有" + restLoginTime + "次机会");
            if (restLoginTime <= NumeralUtil.POSITIVE_THREE && restLoginTime > NumeralUtil.POSITIVE_ZERO) {
                rr.noLogin().fail().setMsg("用户名或密码错误，还有" + restLoginTime + "次尝试机会").setCode("500");
//                ResponseUtil.out(response, rr.noLogin().fail().setMsg("用户名或密码错误，还有" + restLoginTime + "次尝试机会").setCode("500"));
            } else if (restLoginTime <= NumeralUtil.POSITIVE_ZERO) {
                rr.noLogin().fail().setMsg("登录错误次数超过限制，请" + initTokenProperties.getLoginAfterTime() + "分钟后再试").setCode(
                        "500");
//                ResponseUtil.out(response, rr.noLogin().fail().setMsg("登录错误次数超过限制，请" + initTokenProperties.getLoginAfterTime() + "分钟后再试").setCode("500"));
            } else {
                rr.noLogin().fail().setMsg("用户名或密码错误").setCode("500");
//                ResponseUtil.out(response, rr.noLogin().fail().setMsg("用户名或密码错误").setCode("500"));
            }
        } else if (e instanceof DisabledException) {
            rr.noLogin().fail().setMsg("账户被禁用，请联系管理员").setCode("530");
//            ResponseUtil.out(response, rr.noLogin().fail().setMsg("账户被禁用，请联系管理员").setCode("500"));
        } else if (e instanceof LoginFailLimitException) {
            rr.noLogin().fail().setMsg(e.getMessage()).setCode("530");
        } else {
            rr.noLogin().fail().setMsg("登录失败，其他内部错误").setCode("530");
//            ResponseUtil.out(response, rr.noLogin().fail().setMsg("登录失败，其他内部错误").setCode("500"));
        }
        log.info("自定义登录失败处理:username:{}", username);
        if (StringUtils.isNotEmpty(username)) {
            SystemUser su = systemUserServiceImpl.queryForLoginName(username);
            systemLogServiceImpl.loginInfo(su, IPUtil.getIpAddr(request), LogUtil.LOG_USER_FAIL, e.getMessage());
        }

        ResponseUtil.out(response, rr);
    }

    /**
     * 判断用户登录错误次数
     */
    private boolean recordLoginTime(String username) {
        String key = "loginTimeLimit:" + username;
        String flagKey = "loginFailFlag:" + username;
        String value = redisUtil.getString(key);
        if (StringUtils.isBlank(value)) {
            value = "0";
        }
        // 获取已登录错误次数
        Integer loginFailTime = Integer.parseInt(value) + 1;
        redisUtil.setString(key, loginFailTime.toString(), initTokenProperties.getLoginAfterTime(), TimeUnit.MINUTES);
        if (loginFailTime >= initTokenProperties.getLoginTimeLimit()) {
            redisUtil.setString(flagKey, "fail", initTokenProperties.getLoginAfterTime(), TimeUnit.MINUTES);
            return false;
        }
        return true;
    }
}
