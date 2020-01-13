package com.framework.common.util.system;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.common.util.system
 * @Description 验证码生成图片工具类
 * @DateTime 2019/12/21 11:14
 * @Version 1.0
 */
public class CaptchaUtil {
    public static final Integer CAPTCHA_EXPIRED_TIME = 60;//验证码超时时间
    public static final String CAPTCHA_SESSION_KEY = "CAPTCHA_SESSION_KEY";//验证码在session中的key
    public static final String CAPTCHA_CODE = "captchaCode";//验证码在session中的key
}
