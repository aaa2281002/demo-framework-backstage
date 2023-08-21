package com.framework.web.config.initSecurityConfig.initLogin;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.system.IPUtil;
import com.framework.common.util.system.LogUtil;
import com.framework.model.login.UserPrincipal;
import com.framework.service.system.SystemLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.config.initLogin
 * @description 自定义登录成功处理类
 * @datetime 2019/12/18 15:52
 */
@Service("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private Logger log = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);
    @Autowired
    private SystemLogService systemLogServiceImpl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("onAuthenticationSuccess:自定义登录成功处理类");
        ResponseResult rr = new ResponseResult();
        response.setContentType("application/json;charset=utf-8");
//        String userName = ServletRequestUtils.getStringParameter(request, Constant.USER_NAME);
        UserPrincipal up = (UserPrincipal) authentication.getPrincipal();
        systemLogServiceImpl.loginInfo(up.getSystemUser(), IPUtil.getIpAddr(request), LogUtil.LOG_USER_LOGIN, LogUtil.LOG_USER_LOGIN);
        response.getWriter().write(rr.login().setData(authentication.getDetails()).toJsonString());
        response.getWriter().flush();
        response.getWriter().close();
    }
}
