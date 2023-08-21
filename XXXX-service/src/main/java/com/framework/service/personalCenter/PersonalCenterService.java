package com.framework.service.personalCenter;

import com.framework.common.response.ResponseResult;
import com.framework.model.system.SystemUser;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.service.personalCenter
 * @description 用户登录个人中心业务接口类
 * @date 2020/1/2 17:14
 */
public interface PersonalCenterService {
    /**
     * @return com.framework.model.system.SystemUser
     * @title 获取当前登录用户信息
     * @description 获取当前登录用户信息
     * @author 邋遢龘鵺
     * @datetime 2020/1/2 18:40
     */
    SystemUser getLoginNameUser();

    /**
     * @param oldPassword     1 旧密码
     * @param password        2 新密码
     * @param confirmPassword 3 确认密码
     * @return com.framework.common.response.ResponseResult
     * @titel 用户个人密码修改
     * @description 用户个人密码修改
     * @author 邋遢龘鵺
     * @datetime 2020/1/2 18:39
     */
    ResponseResult edit(String oldPassword, String password, String confirmPassword);

    /**
     * @param p 1 用户信息实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 用户个人信息修改
     * @description 用户个人信息修改
     * @author 邋遢龘鵺
     * @datetime 2020/1/2 18:41
     */
    ResponseResult edit(SystemUser p);
}
