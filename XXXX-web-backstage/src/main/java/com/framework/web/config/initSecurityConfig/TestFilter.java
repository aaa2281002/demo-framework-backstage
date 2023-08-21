//package com.framework.web.config.initSecurityConfig;
//
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
///**
// * @author 邋遢龘鵺
// * @className com.framework.web.config.initSecurityConfig
// * @description
// * @datetime 2019/12/21 16:02
// * @version 1.0
// */
//@Configuration
//public class TestFilter extends OncePerRequestFilter implements InitializingBean {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        System.out.println(request.getRequestURL());
////        HttpSession session = request.getSession();
////        String requestUri = request.getRequestURI();
////        System.out.println((session.getAttribute("SPRING_SECURITY_CONTEXT") == null));
////        System.out.println(!"/login".equals(requestUri));
////        System.out.println(!"/defaultCaptcha".equals(requestUri));
////        System.out.println(!requestUri.contains("css"));
////        System.out.println(!requestUri.contains("img"));
////        System.out.println(!requestUri.contains("js"));
////        System.out.println(!"/".equals(requestUri));
////        boolean is = (session.getAttribute("SPRING_SECURITY_CONTEXT") == null && !"/login".equals(requestUri)
////                && !"/defaultCaptcha".equals(requestUri) && !requestUri.contains("css")
////                && !requestUri.contains("img") && !requestUri.contains("js")) && !"/".equals(requestUri);
////        if (is) {
////            response.setContentType("text/html;charset=utf-8");
////            response.getWriter().print("<script type=\"text/javascript\">");
////            response.getWriter().print("window.top.location=\"/\"");
////            response.getWriter().print("</script>");
////        } else {
////            filterChain.doFilter(request, response);
////        }
//            filterChain.doFilter(request, response);
//    }
//}
