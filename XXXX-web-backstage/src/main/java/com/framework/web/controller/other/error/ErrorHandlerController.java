package com.framework.web.controller.other.error;

import com.framework.web.base.BaseController;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.controller.other.error
 * @Description 异常页面统一处理请求类
 * @DateTime 2019/12/22
 * @Version 1.0
 */
@ControllerAdvice
public class ErrorHandlerController extends BaseController implements ErrorController {
    private String path = "/error/";

    @Override
    public String getErrorPath() {
        return "/error";
    }

//    @RequestMapping("/error")
//    public String handleError(HttpServletRequest request) {
//        //获取statusCode:401,404,500
//        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        System.out.println(statusCode);
//        if (statusCode == 401) {
//            return path + "401";
//        } else if (statusCode == 404) {
//            return path + "404";
//        } else if (statusCode == 403) {
//            return path + "403";
//        }
//        return path + "500";
//    }

    /**
     * @param request  1 请求对象
     * @param response 2 响应对象
     * @param e        3 异常对象
     * @Titel 全局异常拦截处理方法
     * @Description 全局异常拦截处理方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/28 18:42
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public void errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        System.out.println(e.getMessage());
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        System.out.println(statusCode);
        response.setContentType("text/html;charset=utf-8");
        StringBuilder sb = new StringBuilder("<script type=\"text/javascript\">");
        if (e instanceof AccessDeniedException) {
            sb.append("window.location='" + path + "401 '");
        } else {
            sb.append("window.location='" + path + "500 '");
        }
        sb.append("</script>");
        try {
            response.getWriter().write(sb.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            response.getWriter().flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            response.getWriter().close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


//        return path + "500";
    }
//    @ExceptionHandler(RuntimeException.class) //拦截所有运行时的全局异常
//    public void ErrorTest(RuntimeException e) {
//        System.err.println(e.getMessage());
//    }

}
