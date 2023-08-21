package com.framework.common.exception.captcha;

import org.springframework.security.core.AuthenticationException;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.exception.captcha
 * @description 验证码异常自定义信息类
 * @datetime 2019/12/21 13:46
 */
public class CaptchaCodeException extends AuthenticationException {
    private static final long serialVersionUID = -1L;

    public CaptchaCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public CaptchaCodeException(String msg) {
        super(msg);
    }
}
