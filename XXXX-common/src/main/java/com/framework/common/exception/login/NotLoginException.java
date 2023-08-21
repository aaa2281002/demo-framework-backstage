package com.framework.common.exception.login;

/**
 * @Author 龘鵺
 * @ClassName com.framework.common.exception.login
 * @Description 返回没登录提示
 * @Date 2022/3/11 12:04
 * @Version 1.0
 */
public class NotLoginException extends RuntimeException {

    public NotLoginException(String msg) {
        super(msg);
    }
}
