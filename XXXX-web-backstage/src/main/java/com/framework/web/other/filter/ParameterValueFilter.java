package com.framework.web.other.filter;

import com.framework.common.model.properties.IgnoredUrlsProperties;
//import com.framework.common.util.filter.FilterStringUtil;
import com.framework.web.config.initInjectSQLFilter.XssHttpServletRequestWrapper;
import com.framework.web.config.initSecurityConfig.initLogin.MyAuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.other.filter
 * @description 请求参数值过滤处理
 * @date 2020/1/9 17:08
 */
@Configuration
public class ParameterValueFilter extends OncePerRequestFilter implements InitializingBean {
    private Logger log = LoggerFactory.getLogger(MyAuthenticationProvider.class);
    @Autowired
    private IgnoredUrlsProperties ignoredUrlsProperties;

    /**
     * @param request     1 请求对象
     * @param response    2 相应对象
     * @param filterChain 3 过滤链接视图对象
     * @titel 请求参数值过滤处理
     * @description 请求参数值过滤处理
     * @author 邋遢龘鵺
     * @datetime 2020/1/10 10:58
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        System.out.println("进入过滤器");
        String requestUri = request.getRequestURI();
        HttpSession session = request.getSession();
//        Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
//        if (FilterStringUtil.FILTER_STRING_SLASH.equals(requestUri) && obj == null) {
//            log.error("doFilterInternal:SPRING_SECURITY_CONTEXT:/：null:请登录");
//            response.sendRedirect("/login");
//            return;
//        }
        boolean is = (
                session.getAttribute("SPRING_SECURITY_CONTEXT") != null
                        && !requestUri.contains("/css/")
                        && !requestUri.contains("/img/")
                        && !requestUri.contains("/js/")
                        && !ignoredUrlsProperties.getInitIgnoreUrl().contains(requestUri)
        );
//        Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
//        System.out.println("attribute=" + (obj != null));
//        System.out.println("login=" + !"/login".equals(requestUri));
//        System.out.println("loginPage=" + !"/loginPage".equals(requestUri));
//        System.out.println("defaultKaptcha=" + !"/defaultKaptcha".equals(requestUri));
//        System.out.println("css=" + !requestUri.contains("/css/"));
//        System.out.println("img=" + !requestUri.contains("/img/"));
//        System.out.println("js=" + !requestUri.contains("/js/"));
//        System.out.println("/=" + !"/".equals(requestUri));
//        System.out.println(is);
        if (is) {
            //登录后请求过滤参数值,预防注入
            log.info("doFilterInternal:url:{}", requestUri);
            filterChain.doFilter(new XssHttpServletRequestWrapper(request), response);
        } else {
            //正常跳转，不过滤
            filterChain.doFilter(request, response);
        }
    }

}
