package com.framework.web.base;

import com.framework.common.response.ResponseResult;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.service.base
 * @description 公共请求控制类
 * @datetime 2019/10/11
 */
public class BaseController {

    /**
     * @return com.framework.common.response.ResponseResult
     * @titel 操作异常对象返回
     * @description 操作异常对象返回
     * @author 邋遢龘鵺
     * @datetime 2019/12/27 15:03
     */
    protected ResponseResult getError() {
        return new ResponseResult().error();
    }

}
