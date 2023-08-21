package com.framework.web.controller.other.cache;

import com.framework.common.response.ResponseResult;
import com.framework.service.other.CacheService;
import com.framework.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.web.controller.other.cache
 * @description 缓存请求控制类
 * @date 2022/5/13 9:18
 */
@Controller
@RequestMapping(value = "/cache")
public class CacheController extends BaseController {
    @Autowired
    private CacheService cacheServiceImpl;

    /**
     * @return com.framework.common.response.ResponseResult
     * @titel 获取当前用户权限菜单
     * @description 获取当前用户权限菜单
     * @author 邋遢龘鵺
     * @datetime 2019/12/28 18:39
     */
    @RequestMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult refreshCache() {
        return cacheServiceImpl.refreshCache();
    }
}
