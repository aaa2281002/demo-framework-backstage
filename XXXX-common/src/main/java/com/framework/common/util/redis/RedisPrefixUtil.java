package com.framework.common.util.redis;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.common.util.redis
 * @Description 权限工具类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public class RedisPrefixUtil {
    public static final String REDIS_KEY_SPRING_NAME = "spring"; //spring组装前缀
    public static final String REDIS_KEY_SECURITY_NAME = "security";//安全模块组装前缀
    public static final String REDIS_KEY_REMEMBER_ME_NAME = "RememberMe";//记住我组装前缀
    public static final String REDIS_KEY_TOKEN_NAME = "token";//token存放组装前缀
    public static final String REDIS_KEY_CROSS_KEY_NAME = "crossKey";//交叉键组装前缀

    public static final String REDIS_KEY_SYSTEM_NAME = "system"; //系统模块组装前缀

    public static final String PREFIX_STR_PERMISSION_ROLE = "PERMISSION_ROLE";//redis权限角色代码缓存前缀
    public static final String PREFIX_STR_PERMISSION_MENU = "PERMISSION_MENU";//redis权限菜单代码缓存前缀
    public static final String PREFIX_STR_PERMISSION_BUTTON = "PERMISSION_BUTTON";//redis权限按钮代码缓存前缀

    public static final String PREFIX_STR_LOGIN_SYSTEM_USER = "LOGIN_SYSTEM_USER";//redis用户登陆成功缓存前缀
    public static final String PREFIX_STR_LOGIN_SYSTEM_USER_TOKEN = "LOGIN_SYSTEM_USER_TOKEN";//redis用户登陆Token成功缓存前缀
    public static final String PREFIX_STR_LOGIN_SYSTEM_USER_WHITE_LIST_IP = "SYSTEM_USER_WHITE_LIST_IP";//redis系统后台操作IP白名单缓存前缀
    public static final String PREFIX_STR_LOGIN_SYSTEM_USER_BLACK_LIST_IP = "SYSTEM_USER_BLACK_LIST_IP";//redis系统后台操作IP黑名单缓存前缀

    public static final String PREFIX_STR_DICT_KEY = "DICT_KEY";//redis字典代码缓存前缀
}
