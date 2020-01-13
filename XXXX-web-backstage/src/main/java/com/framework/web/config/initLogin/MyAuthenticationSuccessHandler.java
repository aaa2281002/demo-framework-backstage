package com.framework.web.config.initLogin;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.system.IPUtil;
import com.framework.common.util.system.LogUtil;
import com.framework.model.entity.login.UserPrincipal;
import com.framework.service.service.system.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.config.initLogin
 * @Description 自定义登录成功处理类
 * @DateTime 2019/12/18 15:52
 * @Version 1.0
 */
@Service("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private SystemLogService systemLogServiceImpl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("自定义登录成功处理类");
        ResponseResult rr = new ResponseResult();
        response.setContentType("application/json;charset=utf-8");
//        String userName = ServletRequestUtils.getStringParameter(request, Constant.USER_NAME);
        UserPrincipal up = (UserPrincipal) authentication.getPrincipal();
        systemLogServiceImpl.loginInfo(up.getSystemUser(), IPUtil.getIpAddr(request), LogUtil.LOG_USER_LOGIN, LogUtil.LOG_USER_LOGIN);
        response.getWriter().write(rr.ResponseResultLogin().setData(authentication.getDetails()).toJsonString());
        response.getWriter().flush();
        response.getWriter().close();
    }
}
