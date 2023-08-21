package com.framework.service.init;

import com.framework.common.response.ResponseResult;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.service.init
 * @description 登录后初始化页面业务接口类
 * @datetime 2019/12/25 11:07
 */
public interface InitService {

    /**
     * @return com.framework.common.response.ResponseResult
     * @titel 登录后初始化菜单
     * @description 登录后初始化菜单
     * @author 邋遢龘鵺
     * @datetime 2019/12/25 11:13
     */
    ResponseResult findByMenuList();
}
