package com.framework.common.response;

import com.alibaba.fastjson.JSONObject;
import com.framework.common.util.ResponseResultStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.common.response
 * @Description 返回参数对应实体类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public class ResponseResult implements Serializable {
    private static final long serialVersionUID = -6995375575100199614L;
    //是否成功， 成功为true,失败为false
    private boolean isStatus;
    //对应状态码
    private String code;
    //返回描述
    private String msg;
    //返回数据
    private Object data;
    //查询总数
    private Integer count;

    public ResponseResult() {

    }

    public ResponseResult(String code) {
        this.code = code;
    }


    public ResponseResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
//        this.data = data;
    }

    public ResponseResult(String code, String msg, String token) {
        this.code = code;
        this.msg = msg;
//        this.data = data;
    }

    public boolean getIsStatus() {
        return isStatus;
    }

    public ResponseResult setIsStatus(boolean isStatus) {
        this.isStatus = isStatus;
        return this;
    }

    public ResponseResult setCode(String code) {
        this.code = code;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ResponseResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public ResponseResult setData(Object data) {
        this.data = data;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public ResponseResult setCount(Integer count) {
        this.count = count;
        return this;
    }

    /**
     * @return com.framework.common.response.ResponseResult
     * @Titel 成功返回
     * @Description 成功返回
     * @Author 邋遢龘鵺
     * @DateTime 2020/4/2 18:43
     */
    public ResponseResult ResponseResultSuccess() {
        this.code = ResponseResultStatus.SUCCESS;
        this.msg = ResponseResultStatus.SUCCESS_MESSAGE;
        this.isStatus = Boolean.TRUE;
        return this;
    }

    /**
     * @return com.framework.common.response.ResponseResult
     * @Titel 操作失败返回
     * @Description 操作失败返回
     * @Author 邋遢龘鵺
     * @DateTime 2020/4/2 18:43
     */
    public ResponseResult ResponseResultFail() {
        this.code = ResponseResultStatus.FAIL;
        this.msg = ResponseResultStatus.FAIL_MESSAGE;
        return this;
    }

    /**
     * @param code 1 错误代码
     * @param msg  2  返回描述
     * @return com.framework.common.response.ResponseResult
     * @Titel 操作失败返回
     * @Description 操作失败返回
     * @Author 邋遢龘鵺
     * @DateTime 2020/4/2 18:43
     */
    public ResponseResult ResponseResultFail(String code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    /**
     * @return com.framework.common.response.ResponseResult
     * @Titel 添加操作失败返回
     * @Description 添加操作失败返回
     * @Author 邋遢龘鵺
     * @DateTime 2020/4/2 18:43
     */
    public ResponseResult ResponseResultAddFail() {
        this.code = ResponseResultStatus.FAIL;
        this.msg = ResponseResultStatus.ADD_FAIL_MESSAGE;
        return this;
    }

    /**
     * @return com.framework.common.response.ResponseResult
     * @Titel 更新=修改失败返回
     * @Description 更新=修改失败返回
     * @Author 邋遢龘鵺
     * @DateTime 2020/4/2 18:43
     */
    public ResponseResult ResponseResultUpdateFail() {
        this.code = ResponseResultStatus.FAIL;
        this.msg = ResponseResultStatus.UPDATE_FAIL_MESSAGE;
        return this;
    }

    /**
     * @return com.framework.common.response.ResponseResult
     * @Titel 删除失败返回
     * @Description 删除失败返回
     * @Author 邋遢龘鵺
     * @DateTime 2020/4/2 18:43
     */
    public ResponseResult ResponseResultDeleteFail() {
        this.code = ResponseResultStatus.FAIL;
        this.msg = ResponseResultStatus.DELETE_FAIL_MESSAGE;
        return this;
    }

    /**
     * @return com.framework.common.response.ResponseResult
     * @Titel 重复添加操作失败返回
     * @Description 重复添加操作失败返回
     * @Author 邋遢龘鵺
     * @DateTime 2020/4/2 18:43
     */
    public ResponseResult ResponseResultFailRepeat() {
        this.code = ResponseResultStatus.FAIL;
        this.msg = ResponseResultStatus.FAIL_REPEAT_MESSAGE;
        return this;
    }

    /**
     * @return com.framework.common.response.ResponseResult
     * @Titel 异常返回
     * @Description 异常返回
     * @Author 邋遢龘鵺
     * @DateTime 2020/4/2 18:43
     */
    public ResponseResult ResponseResultError() {
        this.code = ResponseResultStatus.ERROR;
        this.msg = ResponseResultStatus.ERROR_MESSAGE;
        return this;
    }

    /**
     * @return com.framework.common.response.ResponseResult
     * @Titel 没登录返回
     * @Description 没登录返回
     * @Author 邋遢龘鵺
     * @DateTime 2020/4/2 18:43
     */
    public ResponseResult ResponseResultNoLogin() {
        this.code = ResponseResultStatus.NO_LOGIN;
        this.msg = ResponseResultStatus.NO_LOGIN_MESSAGE;
        return this;
    }

    /**
     * @return com.framework.common.response.ResponseResult
     * @Titel 登录成功返回
     * @Description 登录成功返回
     * @Author 邋遢龘鵺
     * @DateTime 2020/4/2 18:43
     */
    public ResponseResult ResponseResultLogin() {
        this.code = ResponseResultStatus.SUCCESS;
        this.msg = ResponseResultStatus.LOGIN_MESSAGE;
        this.isStatus = Boolean.TRUE;
        return this;
    }

    /**
     * @return com.framework.common.response.ResponseResult
     * @Titel 无权限操作返回
     * @Description 无权限操作返回
     * @Author 邋遢龘鵺
     * @DateTime 2020/4/2 18:43
     */
    public ResponseResult ResponseResultNoPermission() {
        this.code = ResponseResultStatus.NO_PERMISSION;
        this.msg = ResponseResultStatus.LOGIN_NO_PERMISSION;
        return this;
    }

    /**
     * @return java.lang.String
     * @Titel 转成JSON字符串
     * @Description 转成JSON字符串
     * @Author 邋遢龘鵺
     * @DateTime 2020/4/2 18:43
     */
    public String toJsonString() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isStatus", isStatus);
        map.put("code", code);
        map.put("msg", msg);
        map.put("data", data);
        map.put("count", count);
        return JSONObject.toJSONString(map);
    }

}
