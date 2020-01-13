package com.framework.common.util.redis;

import com.framework.common.util.other.SymbolUtil;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.common.util.redis
 * @Description redis键组装工具类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public class RedisKeyUtil {

    /**
     * @param key 1 键值
     * @return java.lang.String key
     * @Titel 组装登陆后存储的key
     * @Description 组装登陆后存储的key
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    public static String getLoginSystemUserKey(String key) {
        StringBuffer sb = new StringBuffer(RedisPrefixUtil.REDIS_KEY_SYSTEM_NAME);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(RedisPrefixUtil.PREFIX_STR_LOGIN_SYSTEM_USER);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(RedisPrefixUtil.PREFIX_STR_LOGIN_SYSTEM_USER);
        sb.append(SymbolUtil.NO_INPUT_METHOD_UNDERLINE);
        sb.append(key);
        return sb.toString();
    }

    /**
     * @param key 1 键值
     * @return java.lang.String key
     * @Titel 组装登陆后存储token的key
     * @Description 组装登陆后存储token的key
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    public static String getLoginTokenKey(String key) {
        StringBuffer sb = new StringBuffer(RedisPrefixUtil.REDIS_KEY_SYSTEM_NAME);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(RedisPrefixUtil.PREFIX_STR_LOGIN_SYSTEM_USER_TOKEN);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(RedisPrefixUtil.PREFIX_STR_LOGIN_SYSTEM_USER_TOKEN);
        sb.append(SymbolUtil.NO_INPUT_METHOD_UNDERLINE);
        sb.append(key);
        return sb.toString();
    }

    /**
     * @param key 1 键值
     * @return java.lang.String key
     * @Titel 组装登陆后角色权限存储的key
     * @Description 组装登陆后权限存储的key
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    public static String getPermissionRoleKey(String key) {
        StringBuffer sb = new StringBuffer(RedisPrefixUtil.REDIS_KEY_SYSTEM_NAME);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(RedisPrefixUtil.PREFIX_STR_PERMISSION_ROLE);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(RedisPrefixUtil.PREFIX_STR_PERMISSION_ROLE);
        sb.append(SymbolUtil.NO_INPUT_METHOD_UNDERLINE);
        sb.append(key);
        return sb.toString();
    }

    /**
     * @param key 1 键值
     * @return java.lang.String key
     * @Titel 组装登陆后菜单权限存储的key
     * @Description 组装登陆后权限存储的key
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    public static String getPermissionMenuKey(String key) {
        StringBuffer sb = new StringBuffer(RedisPrefixUtil.REDIS_KEY_SYSTEM_NAME);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(RedisPrefixUtil.PREFIX_STR_PERMISSION_MENU);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(RedisPrefixUtil.PREFIX_STR_PERMISSION_ROLE);
        sb.append(SymbolUtil.NO_INPUT_METHOD_UNDERLINE);
        sb.append(key);
        return sb.toString();
    }

    /**
     * @param key 1 键值
     * @return java.lang.String key
     * @Titel 组装登陆后按钮权限存储的key
     * @Description 组装登陆后权限存储的key
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    public static String getPermissionButtonKey(String key) {
        StringBuffer sb = new StringBuffer(RedisPrefixUtil.REDIS_KEY_SYSTEM_NAME);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(RedisPrefixUtil.PREFIX_STR_PERMISSION_BUTTON);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(RedisPrefixUtil.PREFIX_STR_PERMISSION_BUTTON);
        sb.append(SymbolUtil.NO_INPUT_METHOD_UNDERLINE);
        sb.append(key);
        return sb.toString();
    }

    /**
     * @param key 1 键值
     * @return java.lang.String key
     * @Titel 组装登陆后按钮权限存储的key
     * @Description 组装登陆后权限存储的key
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    public static String getPermissionDictKey(String key) {
        StringBuffer sb = new StringBuffer(RedisPrefixUtil.REDIS_KEY_SYSTEM_NAME);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(RedisPrefixUtil.PREFIX_STR_DICT_KEY);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(RedisPrefixUtil.PREFIX_STR_DICT_KEY);
        sb.append(SymbolUtil.NO_INPUT_METHOD_UNDERLINE);
        sb.append(key);
        return sb.toString();
    }

    /**
     * @param key 1 键值
     * @return java.lang.String key
     * @Titel 系统操作后台白名单存储的key
     * @Description 系统操作后台白名单存储的key
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    public static String getSystemUserWhiteListIpKey(String key) {
        StringBuffer sb = new StringBuffer(RedisPrefixUtil.REDIS_KEY_SYSTEM_NAME);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(RedisPrefixUtil.PREFIX_STR_LOGIN_SYSTEM_USER_WHITE_LIST_IP);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(RedisPrefixUtil.PREFIX_STR_LOGIN_SYSTEM_USER_WHITE_LIST_IP);
        sb.append(SymbolUtil.NO_INPUT_METHOD_UNDERLINE);
        sb.append(key);
        return sb.toString();
    }


    /**
     * @param key 1 键值
     * @return java.lang.String key
     * @Titel 系统操作前端白名单存储的key
     * @Description 系统操作前端白名单存储的key
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    public static String getSystemUserBlackListIpKey(String key) {
        StringBuffer sb = new StringBuffer(RedisPrefixUtil.REDIS_KEY_SYSTEM_NAME);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(RedisPrefixUtil.PREFIX_STR_LOGIN_SYSTEM_USER_BLACK_LIST_IP);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(RedisPrefixUtil.PREFIX_STR_LOGIN_SYSTEM_USER_BLACK_LIST_IP);
        sb.append(SymbolUtil.NO_INPUT_METHOD_UNDERLINE);
        sb.append(key);
        return sb.toString();
    }


    /**
     * @param series 1 浏览器系列代码
     * @return java.lang.String
     * @Titel 根据浏览器系列代码生成redis存储键
     * @Description 根据浏览器系列代码生成redis存储键
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 13:54
     */
    public static String generateTokenKey(String series) {
//        return "spring:security:rememberMe:token:" + series;
        StringBuilder sb = new StringBuilder(RedisPrefixUtil.REDIS_KEY_SPRING_NAME);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(RedisPrefixUtil.REDIS_KEY_SECURITY_NAME);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(RedisPrefixUtil.REDIS_KEY_REMEMBER_ME_NAME);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(RedisPrefixUtil.REDIS_KEY_TOKEN_NAME);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(series);
        return sb.toString();
    }


    /**
     * @param usernameOrSeries 1 账户名或浏览器系列代码
     * @return java.lang.String
     * @Titel 根据账户名或浏览器系列代码生成redis存储键
     * @Description 根据账户名或浏览器系列代码生成redis存储键
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 13:53
     */
    public static String generateUsernameAndSeriesKey(String usernameOrSeries) {
//        return "spring:security:rememberMe:" + usernameOrSeries;
        StringBuilder sb = new StringBuilder(RedisPrefixUtil.REDIS_KEY_SPRING_NAME);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(RedisPrefixUtil.REDIS_KEY_SECURITY_NAME);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(RedisPrefixUtil.REDIS_KEY_REMEMBER_ME_NAME);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(RedisPrefixUtil.REDIS_KEY_CROSS_KEY_NAME);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(usernameOrSeries);
        return sb.toString();
    }
}
