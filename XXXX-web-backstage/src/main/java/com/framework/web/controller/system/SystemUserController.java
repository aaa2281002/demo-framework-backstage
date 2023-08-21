package com.framework.web.controller.system;

import com.framework.common.annotation.QueryTarget;
import com.framework.common.model.validation.ValidationGroup;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.system.SystemUtil;
import com.framework.model.system.SystemUser;
import com.framework.service.system.SystemUserService;
import com.framework.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.controller.system
 * @description 用户请求控制类
 * @datetime 2019/10/11
 */
@Validated
@Controller
@RequestMapping(value = "/system/user")
public class SystemUserController extends BaseController {
    private String path = "lowerright/system/user/";
    @Autowired
    private SystemUserService systemUserServiceImpl;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @titel 分页页面跳转
     * @description 分页页面跳转
     * @author 邋遢龘鵺
     * @datetime 2019/12/14 18:20
     */
    @RequestMapping("/page/list")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_USER_LIST_MANAGEMENT')")
    public ModelAndView pageList() {
        return new ModelAndView(path + "userList");
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @titel 新增页面跳转
     * @description 新增页面跳转
     * @author 邋遢龘鵺
     * @datetime 2019/12/14 18:21
     */
    @RequestMapping("/get/add")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_USER_LIST_MANAGEMENT:add')")
    public ModelAndView getAdd() {
        ModelAndView mv = new ModelAndView(path + "userAdd");
//        mv.addObject("p", systemUserServiceImpl.selectByPrimaryKey(id));
        return mv;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @titel 上传页面跳转
     * @description 上传页面跳转
     * @author 邋遢龘鵺
     * @datetime 2019/12/14 18:21
     */
    @RequestMapping("/get/upload")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_USER_LIST_MANAGEMENT:upload')")
    public ModelAndView getUpload() {
        ModelAndView mv = new ModelAndView(path + "userUpload");
        return mv;
    }

    /**
     * @param id 1 编号
     * @return org.springframework.web.servlet.ModelAndView
     * @titel 编辑页面跳转
     * @description 编辑页面跳转
     * @author 邋遢龘鵺
     * @datetime 2019/12/14 18:21
     */
    @RequestMapping("/get/edit")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_USER_LIST_MANAGEMENT:edit')")
    public ModelAndView getEdit(@NotNull(message = "请选择用户") @Min(value = NumeralUtil.POSITIVE_ONE, message = "用户不存在") Long id) {
        ModelAndView mv = new ModelAndView(path + "userEdit");
        mv.addObject("p", systemUserServiceImpl.getByIdParam(id));
        return mv;
    }

    /**
     * @param id 1 编号
     * @return org.springframework.web.servlet.ModelAndView
     * @titel 编辑页面跳转
     * @description 编辑页面跳转
     * @author 邋遢龘鵺
     * @datetime 2019/12/14 18:21
     */
    @RequestMapping("/get/password")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_USER_LIST_MANAGEMENT:password')")
    public ModelAndView getPassword(@NotNull(message = "请选择用户") @Min(value = NumeralUtil.POSITIVE_ONE, message = "用户不存在") Long id) {
        ModelAndView mv = new ModelAndView(path + "userEditPassword");
        mv.addObject("p", systemUserServiceImpl.getByIdParam(id));
        return mv;
    }

    /**
     * @param id 1 编号
     * @return org.springframework.web.servlet.ModelAndView
     * @titel 查看页面跳转
     * @description 查看页面跳转
     * @author 邋遢龘鵺
     * @datetime 2019/12/14 18:21
     */
    @RequestMapping("/get/view")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_USER_LIST_MANAGEMENT:view')")
    public ModelAndView getView(@NotNull(message = "请选择用户") @Min(value = NumeralUtil.POSITIVE_ONE, message = "用户不存在") Long id) {
        ModelAndView mv = new ModelAndView(path + "userView");
        mv.addObject("p", systemUserServiceImpl.getByIdParam(id));
        return mv;
    }

    /**
     * @param param 1 用户实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 用户分页查询
     * @description 用户分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:58
     */
    // method = RequestMethod.POST,
    @RequestMapping(value = "/find/page/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_USER_LIST_MANAGEMENT')")
    @QueryTarget
    public ResponseResult findPageList(@Validated(value = {ValidationGroup.formPageQuery.class}) SystemUser param) {
        return systemUserServiceImpl.findParamPageList(param);
    }

    /**
     * @param param 1 用户实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 新增
     * @description 新增
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:58
     */
    @RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_USER_LIST_MANAGEMENT:add')")
    public ResponseResult save(@Validated(value = {ValidationGroup.formAdd.class}) SystemUser param) {
        return systemUserServiceImpl.save(param);
    }

    /**
     * @param param 1 用户实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 修改
     * @description 修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:59
     */
    @RequestMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_USER_LIST_MANAGEMENT:edit')")
    public ResponseResult edit(@Validated(value = {ValidationGroup.formEdit.class}) SystemUser param) {
        return systemUserServiceImpl.edit(param);
    }

    /**
     * @param param 1 用户实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 修改
     * @description 修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:59
     */
    @RequestMapping(value = "/password", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_USER_LIST_MANAGEMENT:password')")
    public ResponseResult password(@Validated(value = {ValidationGroup.formPassword.class}) SystemUser param) {
        return systemUserServiceImpl.password(param);
    }

    /**
     * @param idList 1 用户编号集合
     * @return com.framework.common.response.ResponseResult
     * @titel 批量删除
     * @description 批量删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 18:00
     */
    @RequestMapping(value = "/batch/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_USER_LIST_MANAGEMENT:batchDel') ")
    public ResponseResult batchDel(@NotEmpty(message = "请选择用户") @Size(min = NumeralUtil.POSITIVE_ONE,
            message = "用户不存在") @RequestParam(value = "idList[]") List<Long> idList) {
        return systemUserServiceImpl.batchDeleteList(idList);
    }

    /**
     * @param id 1 用户编号
     * @return com.framework.common.response.ResponseResult
     * @titel 删除
     * @description 删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 18:00
     */
    @RequestMapping(value = "/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_USER_LIST_MANAGEMENT:del')")
    public ResponseResult del(@NotNull(message = "请选择用户") @Min(value = NumeralUtil.POSITIVE_ONE, message = "用户不存在") Long id) {
        return systemUserServiceImpl.delId(id);
    }

//    /**
//     * @param id        1 用户编号
//     * @param loginName 2 账户号
//     * @return com.framework.common.response.ResponseResult
//     * @titel 验证是否重复账户号
//     * @description 验证是否重复账户号
//     * @author 邋遢龘鵺
//     * @datetime 2019/12/22 18:00
//     */
//    @RequestMapping("/isExist")
//    @ResponseBody
//    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_USER_LIST_MANAGEMENT:isExist')")
//    public ResponseResult isExist(Long id, String loginName) {
//        return systemUserServiceImpl.isExist(id, loginName);
//    }
}
