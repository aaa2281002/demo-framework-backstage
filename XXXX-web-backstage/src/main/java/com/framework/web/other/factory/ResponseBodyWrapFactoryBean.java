//package com.framework.web.other.factory;
//
//import com.framework.common.util.RedisUtil;
//import com.framework.web.config.initResponseBodyConfig.ResponseBodyWrapHandler;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
//import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @Author 邋遢龘鵺
// * @ClassName com.framework.web.other.factory
// * @Description 响应体包装工厂bean类
// * @DateTime 2019/10/11
// * @Version 1.0
// */
//@Configuration
//public class ResponseBodyWrapFactoryBean implements InitializingBean {
//    @Autowired
//    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;
//    @Autowired
//    private HttpServletRequest request;
//    @Autowired
//    private RedisUtil redisUtil;
//
//    //设置属性设置后
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        List<HandlerMethodReturnValueHandler> returnValueHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();
//        List<HandlerMethodReturnValueHandler> handlers = new ArrayList(returnValueHandlers);
//        decorateHandlers(handlers);
//        requestMappingHandlerAdapter.setReturnValueHandlers(handlers);
//    }
//
//    //重新注入ResponseBody注解返回操作类。
//    private void decorateHandlers(List<HandlerMethodReturnValueHandler> handlers) {
//        for (HandlerMethodReturnValueHandler handler : handlers) {
//            if (handler instanceof RequestResponseBodyMethodProcessor) {
//                ResponseBodyWrapHandler decorator = new ResponseBodyWrapHandler(handler, request, redisUtil);
//                int index = handlers.indexOf(handler);
//                handlers.set(index, decorator);
//                break;
//            }
//        }
//    }
//}
