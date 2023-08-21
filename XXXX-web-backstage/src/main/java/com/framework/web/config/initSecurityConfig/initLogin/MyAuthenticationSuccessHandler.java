package com.framework.web.config.initSecurityConfig.initLogin;

import com.alibaba.fastjson.JSON;
import com.framework.common.model.properties.InitTokenProperties;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.ResponseUtil;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.other.UUIDUtil;
import com.framework.common.util.redis.RedisUtil;
import com.framework.common.model.security.JwtConstant;
import com.framework.common.util.system.IPUtil;
import com.framework.common.util.system.LogUtil;
import com.framework.model.login.UserPrincipal;
import com.framework.model.system.SystemUser;
import com.framework.service.init.JwtService;
import com.framework.service.system.SystemLogService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Titel 登录成功处理类
 * @Description 登录成功处理类
 * @Author 龘鵺
 * @DateTime 2022/3/3 8:51
 * @Version 1.0
 */
//@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private Logger log = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);
    private InitTokenProperties initTokenProperties;
    private SystemLogService systemLogServiceImpl;
    private JwtConstant jwtConstant;
    private JwtService jwtServiceImpl;
    private RedisUtil redisUtil;


    public MyAuthenticationSuccessHandler(InitTokenProperties initTokenProperties, SystemLogService systemLogServiceImpl, JwtConstant jwtConstant, JwtService jwtServiceImpl, RedisUtil redisUtil) {
        this.initTokenProperties = initTokenProperties;
        this.systemLogServiceImpl = systemLogServiceImpl;
        this.jwtConstant = jwtConstant;
        this.jwtServiceImpl = jwtServiceImpl;
        this.redisUtil = redisUtil;
    }

    @Override
//    @SystemLog(description = "登录系统", type = LogType.LOGIN)
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("AuthenticationSuccessHandler.onAuthenticationSuccess");
        // 用户选择保存登录状态几天（记住我）
        String saveLogin = request.getParameter(jwtConstant.getSaveLogin());
        Boolean saved = false;
        if (StringUtils.isNotBlank(saveLogin) && Boolean.valueOf(saveLogin)) {
            saved = true;
            if (!initTokenProperties.getRedis()) {
                initTokenProperties.setTokenExpireTime(initTokenProperties.getSaveLoginTime() * NumeralUtil.POSITIVE_SIXTY * NumeralUtil.POSITIVE_TWENTY_FOUR);
            }
        }
        SystemUser systemUser = ((UserPrincipal) authentication.getPrincipal()).getSystemUser();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) ((UserDetails) authentication.getPrincipal()).getAuthorities();
        List<String> permissions = new ArrayList<>();
        if (authorities != null) {
            for (GrantedAuthority g : authorities) {
                permissions.add(g.getAuthority());
            }
        }
//        ipInfoUtil.getInfo(request, "");
        // 登陆成功生成token
        String token;
        if (initTokenProperties.getRedis()) {
            // redis
            token = UUIDUtil.getUUID();
            // 不缓存权限
//            if (!initTokenProperties.getStorePerms()) {
////                tokenUser.setPermissions(null);
//            }
            // 单设备登录 之前的token失效
            if (initTokenProperties.getSdl()) {
                String oldToken = redisUtil.getLoginUserString(systemUser.getLoginName());
                if (StringUtils.isNotBlank(oldToken)) {
                    redisUtil.delTokenKey(oldToken);
                }
//                systemUser.setToken(token);
            }
            long time = initTokenProperties.getTokenExpireTime() * NumeralUtil.POSITIVE_SIXTY;
            if (saved) {
                //保存7天
                redisUtil.setLoginUserString(systemUser.getLoginName(), token, time);
                redisUtil.setLoginTokenStringIfLogin(token, JSON.toJSONString(systemUser), time);
            } else {
                //保存临时时间-默认30分钟
                redisUtil.setLoginUserString(systemUser.getLoginName(), token, time);
                redisUtil.setLoginTokenStringIfLogin(token, JSON.toJSONString(systemUser), time);
            }
        } else {
            // JWT
            token = jwtServiceImpl.createToken(systemUser.getLoginName());
            long time = jwtConstant.getExpire() * NumeralUtil.POSITIVE_SIXTY;
            if (initTokenProperties.getSdl()) {
                systemUser.setToken(token.replace(jwtConstant.getTokenSplit(), ""));
            }
            redisUtil.setLoginUserString(systemUser.getLoginName(), JSON.toJSONString(systemUser), time);
        }
        ResponseResult rr = new ResponseResult().login().setData(token);
        systemLogServiceImpl.loginInfo(systemUser, IPUtil.getIpAddr(request), LogUtil.LOG_USER_LOGIN, LogUtil.LOG_USER_LOGIN);
        ResponseUtil.out(response, rr);
    }
}
