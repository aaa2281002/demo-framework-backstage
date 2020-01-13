package com.framework.web.controller.other.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.controller.other.error
 * @Description 错误页面请求控制类
 * @DateTime 2019/12/23 11:46
 * @Version 1.0
 */
@Controller
@RequestMapping("/error")
public class ErrorPageController {
    private String path = "/error/";

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Titel 跳转无权限页面
     * @Description 跳转无权限页面
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/23 14:01
     */
    @RequestMapping("/401")
    public ModelAndView error401() {
        ModelAndView mv = new ModelAndView(path + "401");
        return mv;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Titel 跳转禁止访问页面
     * @Description 跳转禁止访问页面
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/23 14:01
     */
    @RequestMapping("/403")
    public ModelAndView error403() {
        ModelAndView mv = new ModelAndView(path + "403");
        return mv;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Titel 跳转资源找不到页面
     * @Description 跳转资源找不到页面
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/23 14:01
     */
    @RequestMapping("/404")
    public ModelAndView error404() {
        ModelAndView mv = new ModelAndView(path + "404");
        return mv;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Titel 跳转系统内部错误页面
     * @Description 跳转系统内部错误页面
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/23 14:01
     */
    @RequestMapping("/500")
    public ModelAndView error500() {
        ModelAndView mv = new ModelAndView(path + "500");
        return mv;
    }

}
