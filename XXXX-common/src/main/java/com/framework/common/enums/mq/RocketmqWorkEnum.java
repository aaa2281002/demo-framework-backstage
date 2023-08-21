package com.framework.common.enums.mq;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.common.enums.mq
 * @description rocket队列业务枚举类
 * @datetime 2023/4/9 16:22
 */
public enum RocketmqWorkEnum {
    code1(1, "sms"),
    code2(2, ""),
    code3(3, ""),
    ;


    private int index;
    private String code;

    RocketmqWorkEnum(int index, String code) {
        this.index = index;
        this.code = code;
    }

    public int getIndex() {
        return index;
    }

    public String getCode() {
        return code;
    }
}
