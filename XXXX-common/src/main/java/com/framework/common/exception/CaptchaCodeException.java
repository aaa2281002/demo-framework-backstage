package com.framework.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.common.exception
 * @Description 验证码异常自定义信息类
 * @DateTime 2019/12/21 13:46
 * @Version 1.0
 */
public class CaptchaCodeException extends AuthenticationException {
    private static final long serialVersionUID = -5183910951107271002L;

    public CaptchaCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public CaptchaCodeException(String msg) {
        super(msg);
    }
}
