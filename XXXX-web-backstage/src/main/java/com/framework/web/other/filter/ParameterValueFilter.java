package com.framework.web.other.filter;

import com.framework.common.util.filter.FilterStringUtil;
import com.framework.web.config.initInjectSQLFilter.XssHttpServletRequestWrapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.other.filter
 * @Description 请求参数值过滤处理
 * @Date 2020/1/9 17:08
 * @Version 1.0
 */
@Configuration
public class ParameterValueFilter extends OncePerRequestFilter implements InitializingBean {
    /**
     * @param request     1 请求对象
     * @param response    2 相应对象
     * @param filterChain 3 过滤链接视图对象
     * @Titel 请求参数值过滤处理
     * @Description 请求参数值过滤处理
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/10 10:58
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        System.out.println("进入过滤器");
        String requestUri = request.getRequestURI();
        HttpSession session = request.getSession();
        boolean is = (
                session.getAttribute("SPRING_SECURITY_CONTEXT") != null
                        && !requestUri.contains("/css/")
                        && !requestUri.contains("/img/")
                        && !requestUri.contains("/js/")
                        && !FilterStringUtil.FILTER_NO_BLOCKING_CODE_LIST.contains(requestUri)
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
            System.out.println(requestUri);
            filterChain.doFilter(new XssHttpServletRequestWrapper(request), response);
        } else {
            //正常跳转，不过滤
            filterChain.doFilter(request, response);
        }
    }

}
