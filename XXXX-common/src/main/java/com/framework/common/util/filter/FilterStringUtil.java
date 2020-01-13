package com.framework.common.util.filter;

import com.framework.common.util.other.SymbolUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.common.util.filter
 * @Description 拦截器过滤字符串和URL工具类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public class FilterStringUtil {
    public final static String FILTER_STRING_LOGIN = "/login";//登陆
    public final static String FILTER_STRING_LOGIN_PAGE = "/loginPage";//过期跳转
    public final static String FILTER_STRING_LOGIN_PAGE_INVALID = "/loginPage?invalid";//session失效跳转
    public final static String FILTER_STRING_INDEX = "/index";//首页
    public final static String FILTER_STRING_DEFAULT_CAPTCHA = "/defaultCaptcha";//验证代码


    public final static String FILTER_STRING_RESOURCES = "/resources/**";//资源目录
    public final static String FILTER_STRING_CSS = "/css/**";//样式目录
    public final static String FILTER_STRING_IMG = "/img/**";//图片目录
    public final static String FILTER_STRING_JS = "/js/**";//js代码目录
    public final static String FILTER_STRING_FAVICON_ICO = "/favicon.ico";//图标目录
    public final static String FILTER_STRING_ALL_FAVICON_ICO = "/**/favicon.ico";//图标目录
    public final static String FILTER_STRING_GOOGLEV = "/googlev/**";//谷歌请求

    //逗号分隔,请求参数值过滤处理，以下URL中带有不不过滤
    public final static String FILTER_NO_BLOCKING_CODE = "/login,/loginPage,/defaultKaptcha,/,/favicon.ico";
    //请求参数值过滤处理,匹配集合
    public final static List<String> FILTER_NO_BLOCKING_CODE_LIST = new ArrayList<String>(Arrays.asList(FILTER_NO_BLOCKING_CODE.split(SymbolUtil.NO_INPUT_METHOD_COMMA)));
}
