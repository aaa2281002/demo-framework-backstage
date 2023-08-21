package com.framework.web.controller.other.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.controller.other.error
 * @description 错误页面请求控制类
 * @datetime 2019/12/23 11:46
 */
@Controller
@RequestMapping("/error")
public class ErrorPageController {
    private String path = "/error/";

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @titel 跳转无权限页面
     * @description 跳转无权限页面
     * @author 邋遢龘鵺
     * @datetime 2019/12/23 14:01
     */
    @RequestMapping("/401")
    public ModelAndView error401() {
        ModelAndView mv = new ModelAndView(path + "401");
        return mv;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @titel 跳转禁止访问页面
     * @description 跳转禁止访问页面
     * @author 邋遢龘鵺
     * @datetime 2019/12/23 14:01
     */
    @RequestMapping("/403")
    public ModelAndView error403() {
        ModelAndView mv = new ModelAndView(path + "403");
        return mv;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @titel 跳转资源找不到页面
     * @description 跳转资源找不到页面
     * @author 邋遢龘鵺
     * @datetime 2019/12/23 14:01
     */
    @RequestMapping("/404")
    public ModelAndView error404() {
        ModelAndView mv = new ModelAndView(path + "404");
        return mv;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @titel 跳转系统内部错误页面
     * @description 跳转系统内部错误页面
     * @author 邋遢龘鵺
     * @datetime 2019/12/23 14:01
     */
    @RequestMapping("/500")
    public ModelAndView error500() {
        ModelAndView mv = new ModelAndView(path + "500");
        return mv;
    }

}
