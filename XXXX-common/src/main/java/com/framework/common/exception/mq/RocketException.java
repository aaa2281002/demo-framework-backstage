package com.framework.common.exception.mq;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.common.exception.mq
 * @description rocket消息队列异常类
 * @datetime 2023/4/23 11:35
 */
public class RocketException extends RuntimeException {

    public RocketException(String msg, Throwable t) {
        super(msg, t);
    }

    public RocketException(String msg) {
        super(msg);
    }
}
