package com.framework.common.util.encrypt;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.common.util.encrypt
 * @Description MD5加密工具类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public class MD5Util {

    /**
     * @Titel MD5加密
     * @Description MD5加密
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     * @param str 1 加密字符串
     * @return java.lang.String
     */
    public static String MD5Encode(String str) {
        return DigestUtils.md5Hex(str);
    }

    /**
     * @Titel MD5密码加盐加密
     * @Description MD5密码加盐加密
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     * @param password 1 账户密码
     * @return java.lang.String
     */
    public static String MD5EncodeToPassword(String password){
        String str = password + PasswordUtil.ADD_SALT;
        return DigestUtils.md5Hex(str);
    }
}
