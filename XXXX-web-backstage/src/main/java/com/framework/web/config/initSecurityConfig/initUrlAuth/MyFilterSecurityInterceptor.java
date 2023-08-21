package com.framework.web.config.initSecurityConfig.initUrlAuth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author 龘鵺
 * @ClassName com.framework.api.config.initSecurityConfig.initUrlAuth
 * @Description 权限管理过滤器，监控用户行为
 * @Date 2022/3/2 15:03
 * @Version 1.0
 */
//@Component("myFilterSecurityInterceptor")
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
    private static Logger log = LoggerFactory.getLogger(MyFilterSecurityInterceptor.class);
    private static final String FILTER_APPLIED = "__spring_security_filterSecurityInterceptor_filterApplied";
    @Autowired
    private MySecurityMetadataSource mySecurityMetadataSource;

    @Autowired
    public void setMyAccessDecisionManager(MyAccessDecisionManager myAccessDecisionManager) {
        super.setAccessDecisionManager(myAccessDecisionManager);
        log.info("MyFilterSecurityInterceptor.setMyAccessDecisionManager=1");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("MyFilterSecurityInterceptor.init=4");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("MyFilterSecurityInterceptor.doFilter=5");
        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request != null && request.getMethod().equals(HttpMethod.OPTIONS.toString())) {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
            return;
        }
        if (request != null
                && request.getAttribute(FILTER_APPLIED) != null) {
            request.removeAttribute(FILTER_APPLIED);
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
            return;
        }
        request.setAttribute(FILTER_APPLIED, true);
        invoke(fi);
    }


    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        //调用 MySecurityMetadataSource 的 getAttributes(Object object) 这个方法获取fi对应的所有权限
        //再 MyAccessDecisionManager 的 decide 方法来校验用户的权限是否足够
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.finallyInvocation(token);
            super.afterInvocation(token, (Object) null);
        }

    }

    @Override
    public void destroy() {
        log.info("MyFilterSecurityInterceptor.destroy");
    }

    @Override
    public Class<?> getSecureObjectClass() {
//        log.info("MyFilterSecurityInterceptor.getSecureObjectClass=2");
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
//        log.info("MyFilterSecurityInterceptor.obtainSecurityMetadataSource=3");
        return this.mySecurityMetadataSource;
//        return null;
    }
}
