package com.framework.web.other.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.redis.RedisUtil;
import com.framework.model.system.SystemMenu;
import com.framework.model.system.SystemRole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.other.interceptor
 * @description 请求拦截器
 * @datetime 2019/10/11
 */
//@Configuration
public class InterceptorConfig implements HandlerInterceptor {
    @Autowired
    private RedisUtil redisUtil;
    /*
    preHandle：调用Controller某个方法之前
　　postHandle：Controller之后调用，视图渲染之前，如果控制器Controller出现了异常，则不会执行此方法
　　afterCompletion：不管有没有异常，这个afterCompletion都会被调用，用于资源清理
     */

    /**
     * @param request  1 请求对象
     * @param response 2 响应对象
     * @param handler  3 参数对象
     * @return boolean
     * @titel 进入controller层之前拦截请求
     * @description 进入controller层之前拦截请求
     * @author 邋遢龘鵺
     * @datetime 2020/3/28 13:08
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
//        if (!(handler instanceof HandlerMethod)) {
        return true;
//        }
//        System.out.println("token:" + request.getParameter(ConstantUtil.CONSTANT_TOKEN));//获取token
//        HandlerMethod hm = (HandlerMethod) handler;//获取对象
//        //验证IP白名单代码，暂时没实现
//
//        //验证权限代码
//        ResponseResult r = this.authPermission(request);
//        if (r == null) {
//            return true;
//        }
//
//        //重置response
////        response.reset();
//        //设置编码格式
//        response.setCharacterEncoding(CodingUtil.CODING_UTF8);
//        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
//        PrintWriter pw = response.getWriter();
//        pw.write(r.toJsonString());
//        pw.flush();
//        pw.close();
//        return false;

//        return true;
//        HttpSession session=request.getSession();
//        if(session.getAttribute("User")!=null){
//            return true;
//        }
//        String url = "/login.html";
//        request.getRequestDispatcher(url).forward(request,response);
//        response.setContentType("application/json; charset=utf-8");
//        response.getWriter().write("失败!");
//        return false;
    }

    //验证角色，菜单，按钮等私有方法
    private ResponseResult authPermission(HttpServletRequest request) {
//        ResponseResult r = new ResponseResult();
////        //验证参数列表不是null
////        if (pMap == null) {
////            return r.ResponseResultNoLogin();//登陆失效
////        }
//        //验证token是否存在
//        String token = request.getParameter(ConstantUtil.CONSTANT_TOKEN);
//        if (StringUtils.isEmpty(token) || FilterStringUtil.FILTER_STRING_UNDEFINED.equals(token)) {
//            return r.ResponseResultNoLogin();//登陆失效
//        }
//        //验证token对应账户是否存在=========================================
//        Object loginName = redisUtil.getLoginTokenString(token);
//        if (loginName == null) {
//            return r.ResponseResultNoPermission();
//        }
//        //验证用户===============================================================
//        String userStr = redisUtil.getLoginSystemUserString(loginName.toString());
//        if (StringUtils.isEmpty(userStr)) {
//            return r.ResponseResultNoPermission();
//        }
//        SystemUser user = JSONObject.parseObject(userStr,SystemUser.class);
//        if (user == null || StringUtils.isEmpty(user.getRoleCode())) {
//            return r.ResponseResultNoPermission();
//        }
//        String url = request.getRequestURI();
//        //验证是否查询菜单URL是的话。直接放过，不用验证后面步骤
//        if (FilterStringUtil.FILTER_STRING_SYSTEM_MENU_FINDBYMENULIST.equals(url)) {
//            return null;
//        }
//        //验证菜单代码
////        r.setToken(token);
//        String menuPrefix = url.substring(NumeralUtil.POSITIVE_ONE, url.lastIndexOf(SymbolUtil.NO_INPUT_METHOD_FORWARD_SLASH)).replaceAll(SymbolUtil.NO_INPUT_METHOD_FORWARD_SLASH, SymbolUtil.NO_INPUT_METHOD_UNDERLINE);
//        if (StringUtils.isEmpty(menuPrefix)) {
//            return r.ResponseResultNoPermission();
//        }
//        String opMenu = menuPrefix.toUpperCase() + ConstantUtil.CONSTANT_OP_MANAGEMENT;
//        //验证角色信息是否存在===================================================
//        Object roleStr = redisUtil.getAuthRoleString(user.getRoleCode());
//        if (roleStr == null) {
//            return r.ResponseResultNoPermission();
//        }
//        SystemRole systemRole = JSONObject.parseObject(roleStr, SystemRole.class);
//        if (systemRole == null || systemRole.getIsEnable() > NumeralUtil.POSITIVE_ONE) {
//            return r.ResponseResultNoPermission();
//        }
//        //验证菜单权限======================================================
//        if (systemRole.getMenuCodeList() == null || !systemRole.getMenuCodeList().contains(opMenu)) {
//            return r.ResponseResultNoPermission();
//        }
//        String menuStr = redisUtil.getAuthMenuString(opMenu);
//        if (StringUtils.isEmpty(menuStr)) {
//            return r.ResponseResultNoPermission();
//        }
//        //判断菜单是否空和禁用
//        SystemMenu systemMenu = JSONObject.parseObject(menuStr, SystemMenu.class);
//        if (systemMenu == null || systemMenu.getIsEnable() > NumeralUtil.POSITIVE_ONE) {
//            return r.ResponseResultNoPermission();
//        }
//        /**
//         *重新写业务。根据URL后缀来判断是否有权限
//         */
//        //验证按钮对应的操作权限，如增·删·改·查，需要前端传递按钮代码，用于判断========================================
//        List<String> buttonCodeList = systemMenu.getButtonCodeList();
//        if (buttonCodeList == null || buttonCodeList.size() == 0) {
//            return r.ResponseResultNoPermission();
//        }
//        //获取URL最后一个名称
//        String lastUrl = url.substring(url.lastIndexOf(SymbolUtil.NO_INPUT_METHOD_FORWARD_SLASH), url.length());
//        String opButton = "";
//        for (String buutonCode : buttonCodeList) {
//            if (lastUrl.lastIndexOf(buutonCode) == 1) {
//                opButton = buutonCode;
//                break;
//            }
//        }
//        if (StringUtils.isEmpty(opButton)) {
//            return r.ResponseResultNoPermission();
//        }
//        String buttonStr = redisUtil.getAuthButtonString(opButton);
//        if (StringUtils.isEmpty(buttonStr)) {
//            return r.ResponseResultNoPermission();
//        }
//        SystemButton systemButton = JSONObject.parseObject(buttonStr, SystemButton.class);
//        //判断按钮是否空和禁用
//        if (systemButton == null || systemButton.getIsEnable() > NumeralUtil.POSITIVE_ONE) {
//            return r.ResponseResultNoPermission();
//        }
        return null;
    }

    //处理请求完成后视图渲染之前的处理操作
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");

    }

    //视图渲染之后的操作
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
//        PrintWriter out = response.getWriter();
//        out.write("1111111111111");
//        out.flush();
//        out.close();
        System.out.println(request.getParameterMap().toString());
    }
}
