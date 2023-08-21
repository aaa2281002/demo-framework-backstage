package com.framework.common.util.redis;

import com.framework.common.util.other.SymbolUtil;
import com.framework.common.util.system.CaptchaUtil;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.util.redis
 * @description redis键组装工具类
 * @datetime 2019/10/11
 */
public class RedisKeyUtil {

    /**
     * @param prefixKey1 1 第一层级key
     * @param prefixKey2 2 组装key
     * @param key        3 值key
     * @return java.lang.String
     * @Titel 公用redis键组装
     * @Description 公用redis键组装
     * @Author 龘鵺
     * @DateTime 2022/11/25 18:28
     */
    public static String getObjKey(String prefixKey1, String prefixKey2, String key) {
        return getObjKey(RedisPrefixUtil.REDIS_KEY_SYSTEM_NAME, prefixKey1, prefixKey2, key);
    }

    /**
     * @param prefixKey0 1 顶层key
     * @param prefixKey1 2 第一层级key
     * @param prefixKey2 3 组装key
     * @param key        4 值key
     * @return java.lang.String
     * @Titel 公用redis键组装
     * @Description 公用redis键组装
     * @Author 龘鵺
     * @DateTime 2022/11/25 18:28
     */
    public static String getObjKey(String prefixKey0, String prefixKey1, String prefixKey2, String key) {
        StringBuffer sb = new StringBuffer(prefixKey0);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(prefixKey1);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(prefixKey2);
        sb.append(SymbolUtil.NO_INPUT_METHOD_UNDERLINE);
        sb.append(key);
        return sb.toString();
    }

    /**
     * @param key 1 键值
     * @return java.lang.String key
     * @titel 组装登陆后存储的key
     * @description 组装登陆后存储的key
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public static String getLoginSystemUserKey(String key) {
        return getObjKey(RedisPrefixUtil.PREFIX_STR_LOGIN_SYSTEM_USER, RedisPrefixUtil.PREFIX_STR_LOGIN_SYSTEM_USER, key);
    }

    /**
     * @param key 1 键值
     * @return java.lang.String key
     * @titel 组装登陆后存储token的key
     * @description 组装登陆后存储token的key
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public static String getLoginTokenKey(String key) {
        return getObjKey(RedisPrefixUtil.PREFIX_STR_LOGIN_SYSTEM_USER_TOKEN, RedisPrefixUtil.PREFIX_STR_LOGIN_SYSTEM_USER_TOKEN, key);
    }

    /**
     * @param key 1 键值
     * @return java.lang.String key
     * @titel 组装登陆后角色权限存储的key
     * @description 组装登陆后权限存储的key
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public static String getPermissionRoleKey(String key) {
        return getObjKey(RedisPrefixUtil.PREFIX_STR_PERMISSION_ROLE, RedisPrefixUtil.PREFIX_STR_PERMISSION_ROLE, key);
    }

    /**
     * @param key 1 键值
     * @return java.lang.String key
     * @titel 组装登陆后菜单权限存储的key
     * @description 组装登陆后权限存储的key
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public static String getPermissionMenuKey(String key) {
        return getObjKey(RedisPrefixUtil.PREFIX_STR_PERMISSION_MENU, RedisPrefixUtil.PREFIX_STR_PERMISSION_ROLE, key);
    }

    /**
     * @param key 1 键值
     * @return java.lang.String key
     * @titel 组装登陆后按钮权限存储的key
     * @description 组装登陆后权限存储的key
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public static String getPermissionButtonKey(String key) {
        return getObjKey(RedisPrefixUtil.PREFIX_STR_PERMISSION_BUTTON, RedisPrefixUtil.PREFIX_STR_PERMISSION_BUTTON, key);
    }

    /**
     * @param key 1 键值
     * @return java.lang.String key
     * @titel 组装登陆后按钮权限存储的key
     * @description 组装登陆后权限存储的key
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public static String getPermissionDictKey(String key) {
        return getObjKey(RedisPrefixUtil.PREFIX_STR_DICT_KEY, RedisPrefixUtil.PREFIX_STR_DICT_KEY, key);
    }

    /**
     * @param series 1 浏览器系列代码
     * @return java.lang.String
     * @titel 根据浏览器系列代码生成redis存储键
     * @description 根据浏览器系列代码生成redis存储键
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 13:54
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
     * @titel 根据账户名或浏览器系列代码生成redis存储键
     * @description 根据账户名或浏览器系列代码生成redis存储键
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 13:53
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

    /**
     * @param key 1 键值
     * @return java.lang.String key
     * @Titel 组装登陆后存储token的key
     * @Description 组装登陆后存储token的key
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    public static String getCaptchaKey(String key) {
        StringBuffer sb = new StringBuffer(RedisPrefixUtil.REDIS_KEY_SYSTEM_NAME);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(CaptchaUtil.CAPTCHA_SESSION_KEY);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        sb.append(key);
        return sb.toString();
    }

}
