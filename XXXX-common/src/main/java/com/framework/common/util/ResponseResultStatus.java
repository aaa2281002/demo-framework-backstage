package com.framework.common.util;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.common.util
 * @Description 响应结果状态工具类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public class ResponseResultStatus {
    public static final String FAIL = "-1";//响应失败
    public static final String ERROR = "-2";//响应异常
    public static final String NO_LOGIN = "-3";//没登录异常
    public static final String NO_PERMISSION = "-4";//权限不足
    public static final String ERROR_ILLEGAL_FOUR_HUNDRED_AND_ONE = "401";//非法请求
    public static final String SUCCESS = "0";//成功请求

    public static final String SUCCESS_MESSAGE = "操作成功";
    public static final String FAIL_MESSAGE = "操作失败";
    public static final String UPDATE_FAIL_MESSAGE = "更新失败";
    public static final String DELETE_FAIL_MESSAGE = "删除失败";
    public static final String ADD_FAIL_MESSAGE = "添加失败";
    public static final String ERROR_MESSAGE = "操作异常";
    public static final String FAIL_REPEAT_MESSAGE = "已经存在，不能重复添加！";
    public static final String NO_LOGIN_MESSAGE = "登录失效!";
    public static final String LOGIN_MESSAGE = "登录成功!";
    public static final String LOGIN_NO_PERMISSION = "无权限!";

}
