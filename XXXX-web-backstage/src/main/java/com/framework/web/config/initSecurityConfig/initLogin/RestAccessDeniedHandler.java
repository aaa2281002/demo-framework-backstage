package com.framework.web.config.initSecurityConfig.initLogin;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 龘鵺
 * @ClassName com.framework.api.config.initLogin
 * @Description 拒绝访问处理
 * @Date 2022/3/2 23:23
 * @Version 1.0
 */
//@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    private Logger log = LoggerFactory.getLogger(RestAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("RestAccessDeniedHandler.handle:403:抱歉，您没有访问权限, error：{}", accessDeniedException.getMessage());
        ResponseUtil.out(response, new ResponseResult().fail().setCode("403").setMsg("抱歉，您没有访问权限"));
    }
}
