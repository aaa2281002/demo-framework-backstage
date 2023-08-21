package com.framework.web.controller.personalCenter;

import com.framework.common.model.validation.ValidationGroup;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.other.NumeralUtil;
import com.framework.model.system.SystemUser;
import com.framework.service.personalCenter.PersonalCenterService;
import com.framework.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.controller.personalCenter
 * @description 用户登录个人中心请求控制类
 * @date 2020/1/2 14:14
 */
@Api(tags = "个人中心", description = "个人中心")
@Validated
@Controller
@RequestMapping(value = "/personal/center")
public class PersonalCenterController extends BaseController {
    @Autowired
    private PersonalCenterService personalCenterServiceImpl;

    /**
     * @return com.framework.common.response.ResponseResult
     * @Title 查询当前登录账户登录信息
     * @Description 查询当前登录账户登录信息
     * @Author 龘鵺
     * @DateTime 2023/5/15 10:25
     */
    @GetMapping(value = "/get/login/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "查询当前登录账户登录信息", httpMethod = "GET", produces = "application/json", consumes = "text/html", response = ResponseResult.class)
    public ResponseResult getLoginUser() {
        return personalCenterServiceImpl.getLoginUser();
    }

    /**
     * @param oldPassword     1 旧密码
     * @param password        2 新密码
     * @param confirmPassword 3 确认密码
     * @return com.framework.common.response.ResponseResult
     * @titel 用户个人密码修改
     * @description 用户个人密码修改
     * @author 邋遢龘鵺
     * @datetime 2020/1/2 18:39
     */
    @PostMapping(value = "/edit/password", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "用户个人密码修改", httpMethod = "POST", produces = "application/json", consumes = "application/json", response = ResponseResult.class)
    public ResponseResult editPassword(@NotBlank(message = "请输入旧密码")
                                               String oldPassword,
                                       @NotBlank(message = "请输入新密码")
                                               String password,
                                       @NotBlank(message = "请输入确认新密码")
                                               String confirmPassword) {
        return personalCenterServiceImpl.edit(oldPassword, password, confirmPassword);
    }

    /**
     * @param p 1 用户信息实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 用户个人信息修改
     * @description 用户个人信息修改
     * @author 邋遢龘鵺
     * @datetime 2020/1/2 18:41
     */
    @PostMapping(value = "/edit/info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "用户个人信息修改", httpMethod = "POST", produces = "application/json", consumes = "application/json", response = ResponseResult.class)
    public ResponseResult editInfo(@Validated(value = {ValidationGroup.formOther.class}) SystemUser p) {
        return personalCenterServiceImpl.edit(p);
    }

}
