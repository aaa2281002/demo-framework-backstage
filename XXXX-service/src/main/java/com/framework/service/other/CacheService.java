package com.framework.service.other;

import com.framework.common.response.ResponseResult;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.service.other
 * @description 缓存业务接口类
 * @date 2022/5/13 9:20
 */
public interface CacheService {

    /**
     * @return com.framework.common.response.ResponseResult
     * @titel 刷新权限缓存
     * @description 刷新权限缓存
     * @author 龘鵺
     * @datetime 2022/5/13 9:54
     */
    ResponseResult refreshCache();

    /**
     * @titel 初始化权限缓存
     * @description 初始化权限缓存
     * @author 龘鵺
     * @datetime 2022/5/13 9:53
     */
    void initCache();
}
