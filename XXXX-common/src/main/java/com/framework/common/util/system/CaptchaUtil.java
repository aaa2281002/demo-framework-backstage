package com.framework.common.util.system;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.util.system
 * @description 验证码生成图片工具类
 * @datetime 2019/12/21 11:14
 */
public class CaptchaUtil {
    public static final Integer CAPTCHA_EXPIRED_TIME = 60;//验证码超时时间
    public static final String CAPTCHA_SESSION_KEY = "CAPTCHA_SESSION_KEY";//验证码在session中的key
    public static final String CAPTCHA_CODE = "captchaCode";//验证码在session中的key
}
