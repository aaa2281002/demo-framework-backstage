package com.framework.common.enums.result;

/**
 * @Author 龘鵺
 * @ClassName com.framework.common.enums.result
 * @Description 异常信息返回类
 * @Date 2022/12/8 10:58
 * @Version 1.0
 */
public enum ResultEnum {
    CODE0("0", "成功"),
    CODE_1("-1", "失败"),
    CODE_2("-2", "异常"),
    CODE_3("-3", "登录失效"),
    CODE_4("-4", "无权限"),
    CODE1000("1000", "旧密码错误"),
    CODE1001("1001", "两次密码不相同"),
    CODE1002("1002", "新旧密码相同"),
    CODE2000("2000", "上级信息不存在"),
    CODE2001("2001", "层级已上限"),
    CODE3000("3000", "已经存在相同，不能重复添加！"),

    CODE10000("10000", "操作频繁，请稍后尝试!"),
    CODE30000("30000", "无效申请订单!"),
    CODE30001("30001", "渠道未认证!"),

    CODE49999("49999", "三方接口错误!"),
    CODE50000("50000", "用户未认证电子合同!"),
    CODE50001("50001", "机构未认证电子合同!"),
    CODE50002("50002", "渠道未认证电子合同!"),
    CODE50003("50003", "渠道账户未认证电子合同!"),
    CODE50004("50004", "生成签署文件错误!"),
    CODE50005("50004", "发起签署错误!"),
    CODE50006("50005", "获取签署地址错误!"),

    CODE100000("100000", "已认证，无需重复认证!"),
    CODE100001("100001", "无渠道，无需发起签章合同！"),
    CODE100002("100002", "无渠道，无需发起签章合同！！"),
    CODE100003("100003", "已完成签署"),


    CODE500000("500000", "RSA解密错误"),


    CODE1000000("1000000", "未关注，请关注公众号!"),
    CODE1000001("1000001", "微信未授权!"),
    CODE9999998("9999998", "登录失败:"),
    CODE9999999("9999999", "邀请码失效"),
    ;
    //对应状态码
    private String code;
    //返回描述
    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
