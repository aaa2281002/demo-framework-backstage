package com.framework.common.util.filter;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.util.filter
 * @description 拦截器过滤字符串和URL工具类
 * @datetime 2019/10/11
 */
public class FilterStringUtil {
    //后台管理-开始
    public final static String FILTER_STRING_SLASH = "/";//登陆
    public final static String FILTER_STRING_LOGIN = "/login";//登陆
    public final static String FILTER_STRING_LOGIN_PAGE = "/login/page";//过期跳转
    public final static String FILTER_STRING_LOGIN_PAGE_INVALID = "/login/page?invalid";//session失效跳转
    public final static String FILTER_STRING_INDEX = "/init/index";//首页
    public final static String FILTER_STRING_DEFAULT_CAPTCHA = "/default/captcha";//验证代码


    public final static String FILTER_STRING_RESOURCES = "/resources/**";//资源目录
    public final static String FILTER_STRING_CSS = "/css/**";//样式目录
    public final static String FILTER_STRING_IMG = "/img/**";//图片目录
    public final static String FILTER_STRING_JS = "/js/**";//js代码目录
    public final static String FILTER_STRING_FAVICON_ICO = "/favicon.ico";//图标目录
    public final static String FILTER_STRING_ALL_FAVICON_ICO = "/**/favicon.ico";//图标目录
    public final static String FILTER_STRING_GOOGLEV = "/googlev/**";//谷歌请求

}
