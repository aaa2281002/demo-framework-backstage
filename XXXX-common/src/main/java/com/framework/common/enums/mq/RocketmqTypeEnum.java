package com.framework.common.enums.mq;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.common.enums.mq
 * @description rocketmq发送状态枚举类
 * @datetime 2023/4/23 11:26
 */
public enum RocketmqTypeEnum {
    code0("ONEWAY", "单向发送"),
    code1("ASYNC", "异步发送"),
    code2("SYNC", "同步发送"),
    ;

    private String code;
    private String name;

    RocketmqTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
