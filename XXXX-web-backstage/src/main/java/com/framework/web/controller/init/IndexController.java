package com.framework.web.controller.init;

import com.framework.common.response.ResponseResult;
import com.framework.service.service.init.IndexService;
import com.framework.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.controller.init
 * @Description 登录后初始化页面请求类
 * @DateTime 2019/12/2 16:01
 * @Version 1.0
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {
    @Autowired
    private IndexService indexServiceImpl;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Titel 跳转到登录首页
     * @Description 跳转到登录首页
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/28 18:40
     */
    @RequestMapping(value = "/index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Titel 跳转我的主页
     * @Description 跳转我的主页
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/28 18:40
     */
    @RequestMapping(value = "/home")
    public ModelAndView home() {
        return new ModelAndView("/home/home");
    }

    /**
     * @return com.framework.common.response.ResponseResult
     * @Titel 获取当前用户权限菜单
     * @Description 获取当前用户权限菜单
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/28 18:39
     */
    @RequestMapping(value = "/initMenu", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult initMenu() {
        try {
            return indexServiceImpl.findByMenuList();
        } catch (Exception e) {
            e.printStackTrace();
            return getError();
        }
    }
}
