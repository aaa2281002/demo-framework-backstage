package com.framework.web.config.initSecurityConfig.initLogin;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.system.IPUtil;
import com.framework.common.util.system.LogUtil;
import com.framework.model.system.SystemUser;
import com.framework.service.system.SystemLogService;
import com.framework.service.system.SystemUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.config.initLogin
 * @description 自定义登录失败处理
 * @datetime 2019/12/18 16:03
 */
@Service("myAuthenticationFailureHandler")
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private Logger log = LoggerFactory.getLogger(MyAuthenticationFailureHandler.class);
    @Autowired
    private SystemLogService systemLogServiceImpl;
    @Autowired
    private SystemUserService systemUserServiceImpl;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("onAuthenticationFailure:自定义登录失败处理");
        ResponseResult rr = new ResponseResult();
        String username = request.getParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY);
        if (StringUtils.isNotEmpty(username)) {
            SystemUser su = systemUserServiceImpl.queryForLoginName(username);
            systemLogServiceImpl.loginInfo(su, IPUtil.getIpAddr(request), LogUtil.LOG_USER_FAIL, exception.getMessage());
        }
        rr.error().setMsg(exception.getMessage());
        response.getWriter().write(rr.toJsonString());
        response.getWriter().flush();
        response.getWriter().close();
    }
}
