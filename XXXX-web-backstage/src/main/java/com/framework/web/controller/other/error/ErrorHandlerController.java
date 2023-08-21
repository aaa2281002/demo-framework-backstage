package com.framework.web.controller.other.error;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.other.NumeralUtil;
import com.framework.web.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.controller.other.error
 * @description 异常页面统一处理请求类
 * @datetime 2019/12/22
 */
@ControllerAdvice
public class ErrorHandlerController extends BaseController implements ErrorController {
    private Logger log = LoggerFactory.getLogger(ErrorHandlerController.class);
    private String path = "/error/";

//    @Override
//    public String getErrorPath() {
//        return "/error";
//    }

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
    //拒绝访问处理
//    @ExceptionHandler(AccessDeniedException.class)
//    @ResponseStatus(value = HttpStatus.OK)
//    public ResponseResult handleAccessDeniedException(AccessDeniedException e) {
//        log.error("ErrorHandlerController.handleAccessDeniedException.error:{}", e.getMessage());
//        String errorMsg = "AccessDeniedException exception";
//        if (e != null) {
//            errorMsg = e.getMessage();
//            log.warn(e.getMessage(), e);
//        }
//        return new ResponseResult().fail("500", errorMsg);
//    }


    /**
     * @param request  1 请求对象
     * @param response 2 响应对象
     * @param e        3 异常对象
     * @titel 全局异常拦截处理方法
     * @description 全局异常拦截处理方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/28 18:42
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public void errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        log.error("ErrorHandlerController.errorHandler:statusCode:{}, error:{}", statusCode, e.getMessage());
        response.setContentType("text/html;charset=utf-8");
        StringBuilder sb = new StringBuilder("<script type=\"text/javascript\">");

//        if (e instanceof ClassCastException) {
//            String message = e.getMessage();
//            if (message.contains("com.framework.model.login.UserPrincipal")) {
//                try {
//                    response.sendRedirect("/login");
//                } catch (Exception exception) {
//                    exception.printStackTrace();
//                    throw new BadCredentialsException("请登录!");
//                }
//            }
////            System.out.println(e.getLocalizedMessage());
////            System.out.println(e.getClass().getName());
////            System.out.println(e.fillInStackTrace());
////            e.printStackTrace();
//        }
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

    //处理 form data方式调用接口校验失败抛出的异常
    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseResult handleBindException(BindException e) {
//        log.error("ErrorHandlerController.handleBindException.class:{}", e.getParameter().getParameterType().getName());
        log.error("ErrorHandlerController.handleBindException.error:{}", e.getMessage());
        StringBuilder sb = new StringBuilder();
//        e.getBindingResult().getFieldErrors()

        e.getBindingResult().getFieldErrors().forEach(error -> {

            String fieldName = error.getField();
            String message = error.getDefaultMessage();
            sb.append(fieldName + "-" + message + "；");
        });
        String result = sb.toString();
        if (result.length() > NumeralUtil.POSITIVE_ZERO) {
            result = result.substring(NumeralUtil.POSITIVE_ZERO, result.length() - NumeralUtil.POSITIVE_ONE);
        }
        return new ResponseResult().fail(String.valueOf(HttpStatus.BAD_REQUEST.value()), result);
    }


    //方法参数无效异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("ErrorHandlerController.handleMethodArgumentNotValidException.class:{}", e.getParameter().getParameterType().getName());
        log.error("ErrorHandlerController.handleMethodArgumentNotValidException.error:{}", e.getMessage());
        StringBuilder sb = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String message = error.getDefaultMessage();
//            sb.append(fieldName + "-" + message + "；");
            sb.append(message + "；");
        });
        String result = sb.toString();
        if (result.length() > NumeralUtil.POSITIVE_ZERO) {
            result = result.substring(NumeralUtil.POSITIVE_ZERO, result.length() - NumeralUtil.POSITIVE_ONE);
        }
        return new ResponseResult().fail(String.valueOf(HttpStatus.BAD_REQUEST.value()), result);
    }

    // <2> 处理 json 请求体调用接口校验失败抛出的异常
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(value = HttpStatus.OK)
//    public ResponseResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
//        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
//        List collect = fieldErrors.stream().map(o -> o.getDefaultMessage()).collect(Collectors.toList());
//        return new ResponseResult().fail().setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
//    }

    // <3> 处理单个参数校验失败抛出的异常
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseResult constraintViolationExceptionHandler(ConstraintViolationException e) {
        log.error("ErrorHandlerController.constraintViolationExceptionHandler.error:{}", e.getMessage());
//        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
//        List collect = constraintViolations.stream().map(o -> o.getMessage()).collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        StringBuilder classSb = new StringBuilder();
        e.getConstraintViolations().forEach(item -> {
            classSb.append(item.getRootBeanClass()).append("=").append(item.getPropertyPath()).append("；");
            sb.append(item.getMessage()).append("；");
        });
        log.error("ErrorHandlerController.constraintViolationExceptionHandler.class:{}", classSb);
        String result = sb.toString();
        if (result.length() > NumeralUtil.POSITIVE_ZERO) {
            result = result.substring(NumeralUtil.POSITIVE_ZERO, result.length() - NumeralUtil.POSITIVE_ONE);
        }
        return new ResponseResult().fail(String.valueOf(HttpStatus.BAD_REQUEST.value()), result);
    }

}
