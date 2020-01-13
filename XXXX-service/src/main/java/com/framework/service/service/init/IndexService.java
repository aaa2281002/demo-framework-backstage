package com.framework.service.service.init;

import com.framework.common.response.ResponseResult;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.service.init
 * @Description 登录后初始化页面业务接口类
 * @DateTime 2019/12/25 11:07
 * @Version 1.0
 */
public interface IndexService {

    /**
     * @return com.framework.common.response.ResponseResult
     * @Titel 登录后初始化菜单
     * @Description 登录后初始化菜单
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/25 11:13
     */
    public ResponseResult findByMenuList();
}
