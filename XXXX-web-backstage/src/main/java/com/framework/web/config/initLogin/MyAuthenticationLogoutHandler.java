package com.framework.web.config.initLogin;

import com.framework.common.util.system.IPUtil;
import com.framework.common.util.system.LogUtil;
import com.framework.model.entity.login.UserPrincipal;
import com.framework.service.service.system.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.config.initLogin
 * @Description 自定义退出处理
 * @DateTime 2019/12/18 16:03
 * @Version 1.0
 */
@Service("myAuthenticationLogoutHandler")
public class MyAuthenticationLogoutHandler implements LogoutSuccessHandler {
    @Autowired
    private SystemLogService systemLogServiceImpl;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("自定义退出处理");
        UserPrincipal up = (UserPrincipal) authentication.getPrincipal();
        systemLogServiceImpl.loginInfo(up.getSystemUser(), IPUtil.getIpAddr(request), LogUtil.LOG_USER_LOGOUT, LogUtil.LOG_USER_LOGOUT);
        response.sendRedirect("/");
    }
}
