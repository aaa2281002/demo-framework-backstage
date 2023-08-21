package com.framework.web.controller.init;

import com.framework.common.response.ResponseResult;
import com.framework.service.init.InitService;
import com.framework.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.controller.init
 * @description 登录后初始化页面请求类
 * @datetime 2019/12/2 16:01
 */
@Api(tags = "登录后初始化", description = "登录后初始化")
@Controller
@RequestMapping("/init")
public class InitController extends BaseController {
    @Autowired
    private InitService initServiceImpl;

    /**
     * @return com.framework.common.response.ResponseResult
     * @titel 获取当前用户权限菜单
     * @description 获取当前用户权限菜单
     * @author 邋遢龘鵺
     * @datetime 2019/12/28 18:39
     */
    @GetMapping(value = "/menu", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "获取当前用户权限菜单", httpMethod = "GET", produces = "application/json", consumes = "application/json", response = ResponseResult.class)
    public ResponseResult initMenu() {
        return initServiceImpl.findByMenuList();
    }
}
