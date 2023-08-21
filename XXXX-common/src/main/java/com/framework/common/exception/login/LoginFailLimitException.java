package com.framework.common.exception.login;

import org.springframework.security.authentication.InternalAuthenticationServiceException;

/**
 * @Author 龘鵺
 * @ClassName com.framework.common.exception.user
 * @Description 登录失败异常类
 * @Date 2022/3/2 16:57
 * @Version 1.0
 */
public class LoginFailLimitException extends InternalAuthenticationServiceException {
    private String msg;

    public LoginFailLimitException(String message, Throwable cause) {
        super(message, cause);
        msg = message;
    }

    public LoginFailLimitException(String message) {
        super(message);
        msg = message;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }
}
