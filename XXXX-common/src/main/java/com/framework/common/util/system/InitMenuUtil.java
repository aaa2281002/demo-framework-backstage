package com.framework.common.util.system;

import com.framework.common.util.filter.FilterStringUtil;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.util.system
 * @description 初始化菜单工具类
 * @date 2020/1/10 17:24
 */
public class InitMenuUtil {
    //首页名称
    public final static String VALUE_MENU_HOME_NAME = "";
    //首页图标
    public final static String VALUE_MENU_HOME_ICON = "fa fa-home";
    //首页请求URL
    public final static String VALUE_MENU_HOME_URL_PATH = "/init/home";

    //Logo系统名称
    public final static String VALUE_MENU_LOGO_NAME = "xxx管理系统";
    //Logo图片路径
    public final static String VALUE_MENU_LOGO_IMG_PATH = "/img/plugins/logo.png";
    //Logo请求URL
    public final static String VALUE_MENU_LOGO_URL_PATH = FilterStringUtil.FILTER_STRING_INDEX;

    //清除请求URL
    public final static String VALUE_MENU_CLEAR_URL = "";

    //map模块键值
    public final static String KEY_CLEAR_INFO = "clearInfo";//清除模块键
    public final static String KEY_HOME_INFO = "homeInfo";//首页模块键
    public final static String KEY_LOGO_INFO = "logoInfo";//图标模块键
    public final static String KEY_MENU_INFO = "menuInfo";//菜单模块键

    //map子项键
    public final static String KEY_MENU_NAME = "name";//菜单键
    public final static String KEY_MENU_ICON = "icon";//图标键
    public final static String KEY_MENU_URL = "url";//请求路径键
    public final static String KEY_MENU_IMAGE = "image";//图片键
    public final static String KEY_MENU_CLEAR_URL = "clear/url";//清除键

}
