package com.framework.web.config.initSecurityConfig.initLogin;

import com.framework.common.util.system.IPUtil;
import com.framework.common.util.system.LogUtil;
import com.framework.model.login.UserPrincipal;
import com.framework.service.system.SystemLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.config.initLogin
 * @description 自定义退出处理
 * @datetime 2019/12/18 16:03
 */
@Service("myAuthenticationLogoutHandler")
public class MyAuthenticationLogoutHandler implements LogoutSuccessHandler {
    private Logger log = LoggerFactory.getLogger(MyAuthenticationFailureHandler.class);
    @Autowired
    private SystemLogService systemLogServiceImpl;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("onLogoutSuccess:自定义退出处理");
        UserPrincipal up = (UserPrincipal) authentication.getPrincipal();
        systemLogServiceImpl.loginInfo(up.getSystemUser(), IPUtil.getIpAddr(request), LogUtil.LOG_USER_LOGOUT, LogUtil.LOG_USER_LOGOUT);
        response.sendRedirect("/login");
    }
}
