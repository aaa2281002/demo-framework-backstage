package com.framework.web.controller.personalCenter;

import com.framework.common.response.ResponseResult;
import com.framework.model.entity.system.SystemUser;
import com.framework.service.service.personalCenter.PersonalCenterService;
import com.framework.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.controller.personalCenter
 * @Description 用户登录个人中心请求控制类
 * @Date 2020/1/2 14:14
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "/personal/center")
public class PersonalCenterController extends BaseController {
    private String path = "lowerright/personalCenter/";
    @Autowired
    private PersonalCenterService personalCenterServiceImpl;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Titel 个人中心修改密码页面跳转
     * @Description 个人中心修改密码页面跳转
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/14 18:20
     */
    @RequestMapping("/reset/password")
    public ModelAndView resetPassword() {
        return new ModelAndView(path + "userPassword");
    }


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Titel 个人中心修改信息页面跳转
     * @Description 个人中心修改信息页面跳转
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/14 18:20
     */
    @RequestMapping("/reset/info")
    public ModelAndView resetInfo() {
        ModelAndView mv = new ModelAndView(path + "userInfo");
        mv.addObject("p", personalCenterServiceImpl.getLoginNameUser());
        return mv;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Titel 个人中心查看信息页面跳转
     * @Description 个人中心查看信息页面跳转
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/14 18:20
     */
    @RequestMapping("/user/show")
    public ModelAndView userShow() {
        ModelAndView mv = new ModelAndView(path + "userShow");
        mv.addObject("p", personalCenterServiceImpl.getLoginNameUser());
        return mv;
    }

    /**
     * @param oldPassword     1 旧密码
     * @param password        2 新密码
     * @param confirmPassword 3 确认密码
     * @return com.framework.common.response.ResponseResult
     * @Titel 用户个人密码修改
     * @Description 用户个人密码修改
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/2 18:39
     */
    @PostMapping(value = "/edit/password", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult editPassword(String oldPassword, String password, String confirmPassword) {
        return personalCenterServiceImpl.edit(oldPassword, password, confirmPassword);
    }

    /**
     * @param p 1 用户信息实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 用户个人信息修改
     * @Description 用户个人信息修改
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/2 18:41
     */
    @PostMapping(value = "/edit/info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult editInfo(SystemUser p) {
        return personalCenterServiceImpl.edit(p);
    }

}
