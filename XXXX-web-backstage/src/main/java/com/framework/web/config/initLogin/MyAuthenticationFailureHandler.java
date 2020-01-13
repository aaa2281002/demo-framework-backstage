package com.framework.web.config.initLogin;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.system.IPUtil;
import com.framework.common.util.system.LogUtil;
import com.framework.model.entity.login.UserPrincipal;
import com.framework.model.entity.system.SystemUser;
import com.framework.service.service.system.SystemLogService;
import com.framework.service.service.system.SystemUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.config.initLogin
 * @Description 自定义登录失败处理
 * @DateTime 2019/12/18 16:03
 * @Version 1.0
 */
@Service("myAuthenticationFailureHandler")
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    private SystemLogService systemLogServiceImpl;
    @Autowired
    private SystemUserService systemUserServiceImpl;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("自定义登录失败处理");
        ResponseResult rr = new ResponseResult();
        String username = request.getParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY);
        if (StringUtils.isNotEmpty(username)) {
            SystemUser su = systemUserServiceImpl.queryForLoginName(username);
            systemLogServiceImpl.loginInfo(su, IPUtil.getIpAddr(request), LogUtil.LOG_USER_FAIL, exception.getMessage());
        }
        rr.ResponseResultError().setMsg(exception.getMessage());
        response.getWriter().write(rr.toJsonString());
        response.getWriter().flush();
        response.getWriter().close();
    }
}
