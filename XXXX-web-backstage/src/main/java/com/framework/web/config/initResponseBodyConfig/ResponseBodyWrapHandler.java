//package com.framework.web.config.initResponseBodyConfig;
//
//import com.framework.common.response.ResponseResult;
//import com.framework.common.util.ConstantUtil;
//import com.framework.common.util.RedisUtil;
//import com.framework.common.util.TokenUtil;
//import com.framework.model.entity.system.SystemUser;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.core.MethodParameter;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
//import org.springframework.web.method.support.ModelAndViewContainer;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @Author 邋遢龘鵺
// * @ClassName com.framework.web.config.initResponseBodyConfig
// * @Description 响应正文包装处理类WebMvcConfigurationSupport
// * @DateTime 2019/10/11
// * @Version 1.0
// */
//
//public class ResponseBodyWrapHandler implements HandlerMethodReturnValueHandler {
//    private final HandlerMethodReturnValueHandler delegate;
//    private final HttpServletRequest request;
//    private final RedisUtil redisUtil;
//
//    public ResponseBodyWrapHandler(HandlerMethodReturnValueHandler delegate, HttpServletRequest request, RedisUtil redisUtil) {
//        this.delegate = delegate;
//        this.request = request;
//        this.redisUtil = redisUtil;
//    }
//
//    @Override
//    public boolean supportsReturnType(MethodParameter returnType) {
////        System.out.println("111111111111");
//        return delegate.supportsReturnType(returnType);
//    }
//
//    @Override
//    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
//        String url = request.getRequestURI();
//        if ("/login".equals(url)) {
//            delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
//            return;
//        }
//        ResponseResult r = new ResponseResult();
//        String token = request.getParameter(ConstantUtil.CONSTANT_TOKEN);
//        if (StringUtils.isEmpty(token)) {
//            delegate.handleReturnValue(r.ResponseResultNoLogin(), returnType, mavContainer, webRequest);
//            return;
//        }
//        Object loginName = redisUtil.getLoginTokenString(token);
//        if (loginName == null || "".equals(loginName)) {
//            delegate.handleReturnValue(r.ResponseResultNoLogin(), returnType, mavContainer, webRequest);
//            return;
//        }
//        r = (ResponseResult) returnValue;
//        String returnToken = null;
//        while (Boolean.TRUE) {
//            returnToken = TokenUtil.generateToken();
//            boolean is = redisUtil.updLoginTokenKeyIf(token, returnToken);
//            if (is) {
//                break;
//            }
//        }
//        SystemUser systemUsers = (SystemUser) redisUtil.getLoginSystemUserString(loginName.toString());
//        systemUsers.setToken(returnToken);
////        r.setToken(returnToken);
//        redisUtil.setLoginSystemUserString(systemUsers.getLoginName(), systemUsers);
//        delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
//    }
//}
