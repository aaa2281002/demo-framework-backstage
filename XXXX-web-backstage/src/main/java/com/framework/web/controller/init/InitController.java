package com.framework.web.controller.init;

import com.framework.common.response.ResponseResult;
import com.framework.service.init.InitService;
import com.framework.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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
@Controller
@RequestMapping("/init")
public class InitController extends BaseController {
    @Autowired
    private InitService initServiceImpl;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @titel 跳转到登录首页
     * @description 跳转到登录首页
     * @author 邋遢龘鵺
     * @datetime 2019/12/28 18:40
     */
    @RequestMapping(value = "/index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @titel 跳转我的主页
     * @description 跳转我的主页
     * @author 邋遢龘鵺
     * @datetime 2019/12/28 18:40
     */
    @RequestMapping(value = "/home")
    public ModelAndView home() {
        return new ModelAndView("home/home");
    }

    /**
     * @return com.framework.common.response.ResponseResult
     * @titel 获取当前用户权限菜单
     * @description 获取当前用户权限菜单
     * @author 邋遢龘鵺
     * @datetime 2019/12/28 18:39
     */
    @RequestMapping(value = "/menu", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult initMenu() {
        return initServiceImpl.findByMenuList();
    }
}
