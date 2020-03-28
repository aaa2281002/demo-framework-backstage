package com.framework.web.base;

import com.framework.common.response.ResponseResult;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.base
 * @Description 公共请求控制类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public class BaseController {

    /**
     * @return com.framework.common.response.ResponseResult
     * @Titel 操作异常对象返回
     * @Description 操作异常对象返回
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/27 15:03
     */
    protected ResponseResult getError() {
        return new ResponseResult().ResponseResultError();
    }

}
