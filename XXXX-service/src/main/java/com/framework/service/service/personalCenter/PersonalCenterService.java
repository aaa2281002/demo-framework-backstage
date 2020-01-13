package com.framework.service.service.personalCenter;

import com.framework.common.response.ResponseResult;
import com.framework.model.entity.system.SystemUser;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.service.personalCenter
 * @Description 用户登录个人中心业务接口类
 * @Date 2020/1/2 17:14
 * @Version 1.0
 */
public interface PersonalCenterService {
    /**
     * @return com.framework.model.entity.system.SystemUser
     * @Titel 获取当前登录用户信息
     * @Description 获取当前登录用户信息
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/2 18:40
     */
    SystemUser getLoginNameUser();

    /**
     * @param oldPassword     1 旧密码
     * @param password        2 新密码
     * @param confirmPassword 3 确认密码
     * @return com.framework.common.response.ResponseResult
     * @Titel 用户个人密码修改
     * @Description 用户个人密码修改
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/2 18:39
     */
    ResponseResult edit(String oldPassword, String password, String confirmPassword);

    /**
     * @param p 1 用户信息实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 用户个人信息修改
     * @Description 用户个人信息修改
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/2 18:41
     */
    ResponseResult edit(SystemUser p);
}
