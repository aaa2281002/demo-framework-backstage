package com.framework.common.util.encrypt;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.util.encrypt
 * @description MD5加密工具类
 * @datetime 2019/10/11
 */
public class MD5Util {
    public final static String KEY_NAME = "key";

    /**
     * @param value 1 排序值
     * @return java.lang.String
     * @titel 字符串按ASCII升序排序
     * @description 字符串按ASCII升序排序
     * @author 邋遢龘鵺
     * @datetime 2020/4/7 11:47
     */
    public static String ASCIISort(String value) {
        char[] privateKey = new char[value.length()];
        StringBuilder sb = new StringBuilder();
        String a = value;//直接读取这行当中的字符串。
        for (int i = 0; i < value.length(); i++) {
            privateKey[i] = a.charAt(i);//字符串处理每次读取一位。
        }
        Arrays.sort(privateKey);
        for (int i = 0; i < privateKey.length; i++) {
            sb.append(privateKey[i]);
        }
        String trim = sb.toString();
        return trim;
    }

    /**
     * @param treeMap 1 树形集合
     * @param keyName 2 秘钥变量
     * @param code    3 秘钥
     * @return java.lang.String
     * @titel 树形键值集合对象转get提交字符串
     * @description 树形键值集合对象转get提交字符串
     * @author 邋遢龘鵺
     * @datetime 2019/7/3 18:38
     */
    public static String getTreeMapToString(Map<String, Object> treeMap, String keyName, String code) {
        StringBuilder sb = new StringBuilder();
        treeMap.forEach((k, v) -> {
            if (StringUtils.isNotEmpty(String.valueOf(v)) && !"null".equals(String.valueOf(v))) {
                sb.append(k).append("=").append(v).append("&");
            }
        });
        sb.append(keyName).append("=").append(code);
        return sb.toString();
    }

    /**
     * @param jsonObject 1 jsonObject
     * @param keyName    2 秘钥变量
     * @param code       3 秘钥
     * @return java.lang.String
     * @titel 树形键值集合对象转get提交字符串
     * @description 树形键值集合对象转get提交字符串
     * @author 邋遢龘鵺
     * @datetime 2019/7/3 18:38
     */
    public static String getJsonObjectNotNullToString(JSONObject jsonObject, String keyName, String code) {
        StringBuilder sb = new StringBuilder();
        jsonObject.forEach((k, v) -> {
            if (StringUtils.isNotEmpty(String.valueOf(v)) && !"null".equals(String.valueOf(v))) {
                sb.append(k).append("=").append(v).append("&");
            }
        });
        sb.append(keyName).append("=").append(code);
        return sb.toString();
    }

    /**
     * @param jsonObject 1 json键值对象
     * @return java.lang.String
     * @titel jsonObject转换字符串方法，包含所有键值组装
     * @description jsonObject转换字符串方法，包含所有键值组装
     * @author 邋遢龘鵺
     * @datetime 2020/4/7 14:29
     */
    public static String getJsonObjectToString(JSONObject jsonObject) {
        StringBuilder sb = new StringBuilder();
        jsonObject.forEach((k, v) -> {
            sb.append(k).append("=").append(v).append("&");
        });
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * @param jsonObject 1 json键值对象
     * @return java.lang.String
     * @titel jsonObject转换字符串方法，key小写,包含所有键值组装
     * @description jsonObject转换字符串方法，key小写,包含所有键值组装
     * @author 邋遢龘鵺
     * @datetime 2020/4/7 14:29
     */
    public static String getJsonObjectToLowerCaseToString(JSONObject jsonObject) {
        StringBuilder sb = new StringBuilder();
        jsonObject.forEach((k, v) -> {
            sb.append(k.toLowerCase()).append("=").append(v).append("&");
        });
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * @param jsonObject 1 json键值对象
     * @return java.lang.String
     * @titel jsonObject转换字符串方法, 只组装键值不为null和""的
     * @description jsonObject转换字符串方法, 只组装键值不为null和""的
     * @author 邋遢龘鵺
     * @datetime 2020/4/7 14:29
     */
    public static String getJsonObjectNotNullToString(JSONObject jsonObject) {
        StringBuilder sb = new StringBuilder();
        jsonObject.forEach((k, v) -> {
            if (StringUtils.isNotEmpty(String.valueOf(v)) && !"null".equals(String.valueOf(v))) {
                sb.append(k).append("=").append(v).append("&");
            }
        });
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * @param str 1 加密字符串
     * @return java.lang.String
     * @titel MD5加密
     * @description MD5加密
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public static String MD5Encode(String str) {
        return DigestUtils.md5Hex(str);
    }

    /**
     * @param password 1 账户密码
     * @return java.lang.String
     * @titel MD5密码加盐加密
     * @description MD5密码加盐加密
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public static String MD5EncodeToPassword(String password) {
        String str = password + PasswordUtil.ADD_SALT;
        return DigestUtils.md5Hex(str);
    }
}
