package com.framework.common.response;

import com.alibaba.fastjson.JSONObject;
import com.framework.common.enums.result.ResultEnum;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.response
 * @description 返回参数对应类
 * @datetime 2019/10/11
 */
public class ResponseResult implements Serializable {
    private static final long serialVersionUID = -1L;
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
    //处理时间戳
    private Long timeStamp;

    public ResponseResult() {
        this.timeStamp = System.currentTimeMillis();
    }

    public ResponseResult(String code) {
        this.code = code;
        this.timeStamp = System.currentTimeMillis();
    }


    public ResponseResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.timeStamp = System.currentTimeMillis();
//        this.data = data;
    }

    public ResponseResult(String code, String msg, String token) {
        this.code = code;
        this.msg = msg;
        this.timeStamp = System.currentTimeMillis();
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

    public Long gettimeStamp() {
        return timeStamp;
    }

    public ResponseResult settimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    /**
     * @return com.framework.common.response.ResponseResult
     * @Titel 成功返回
     * @Description 成功返回
     * @Author 邋遢龘鵺
     * @DateTime 2020/4/2 18:43
     */
    public ResponseResult success() {
        this.code = ResultEnum.CODE0.getCode();
        this.msg = ResultEnum.CODE0.getMsg();
        this.isStatus = Boolean.TRUE;
        return this;
    }

    /**
     * @param resultEnum 1 参数枚举
     * @return com.framework.common.response.ResponseResult
     * @Title 成功返回
     * @Description 成功返回
     * @Author 龘鵺
     * @DateTime 2023/5/23 14:02
     */
    public ResponseResult success(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
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
    public ResponseResult fail() {
        this.code = ResultEnum.CODE_1.getCode();
        this.msg = ResultEnum.CODE_1.getMsg();
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
    public ResponseResult fail(String code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    /**
     * @param resultEnum 1 参数枚举
     * @return com.framework.common.response.ResponseResult
     * @Title 操作失败返回
     * @Description 操作失败返回
     * @Author 龘鵺
     * @DateTime 2023/5/23 14:02
     */
    public ResponseResult fail(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
        return this;
    }

    /**
     * @return com.framework.common.response.ResponseResult
     * @Titel 重复添加操作失败返回
     * @Description 重复添加操作失败返回
     * @Author 邋遢龘鵺
     * @DateTime 2020/4/2 18:43
     */
    public ResponseResult failRepeat() {
        this.code = ResultEnum.CODE3000.getCode();
        this.msg = ResultEnum.CODE3000.getMsg();
        return this;
    }

    /**
     * @return com.framework.common.response.ResponseResult
     * @Titel 异常返回
     * @Description 异常返回
     * @Author 邋遢龘鵺
     * @DateTime 2020/4/2 18:43
     */
    public ResponseResult error() {
        this.code = ResultEnum.CODE_2.getCode();
        this.msg = ResultEnum.CODE_2.getMsg();
        return this;
    }

    /**
     * @return com.framework.common.response.ResponseResult
     * @Titel 没登录返回
     * @Description 没登录返回
     * @Author 邋遢龘鵺
     * @DateTime 2020/4/2 18:43
     */
    public ResponseResult noLogin() {
        this.code = ResultEnum.CODE_3.getCode();
        this.msg = ResultEnum.CODE_3.getMsg();
        return this;
    }

    /**
     * @return com.framework.common.response.ResponseResult
     * @Titel 登录成功返回
     * @Description 登录成功返回
     * @Author 邋遢龘鵺
     * @DateTime 2020/4/2 18:43
     */
    public ResponseResult login() {
        this.code = ResultEnum.CODE0.getCode();
        this.msg = ResultEnum.CODE0.getMsg();
        this.isStatus = Boolean.TRUE;
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
        map.put("timeStamp", timeStamp);
        return JSONObject.toJSONString(map);
    }

}
