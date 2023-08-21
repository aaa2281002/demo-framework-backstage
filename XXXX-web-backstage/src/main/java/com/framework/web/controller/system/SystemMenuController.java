package com.framework.web.controller.system;

import com.framework.common.annotation.QueryTarget;
import com.framework.common.model.validation.ValidationGroup;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.system.SystemUtil;
import com.framework.model.system.SystemMenu;
import com.framework.service.system.SystemMenuService;
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
import java.util.Arrays;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.controller.system
 * @description 菜单请求控制类
 * @datetime 2019/10/11
 */
@Validated
@Controller
@RequestMapping(value = "/system/menu")
public class SystemMenuController extends BaseController {
    private String path = "lowerright/system/menu/";
    @Autowired
    private SystemMenuService systemMenuServiceImpl;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @titel 分页页面跳转
     * @description 分页页面跳转
     * @author 邋遢龘鵺
     * @datetime 2019/12/14 18:20
     */
    @RequestMapping("/page/list")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_MENU_LIST_MANAGEMENT')")
    public ModelAndView pageList() {
        return new ModelAndView(path + "menuList");
    }

    /**
     * @param id 1 编号
     * @return org.springframework.web.servlet.ModelAndView
     * @titel 新增页面跳转
     * @description 新增页面跳转
     * @author 邋遢龘鵺
     * @datetime 2019/12/14 18:21
     */
    @RequestMapping("/get/add")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_MENU_LIST_MANAGEMENT:add')")
    public ModelAndView getAdd(@NotNull(message = "请选择上级菜单") @Min(value = NumeralUtil.POSITIVE_ZERO, message = "上级菜单不存在") Long id) {
        ModelAndView mv = new ModelAndView(path + "menuAdd");
        mv.addObject("p", systemMenuServiceImpl.selectByPrimaryKey(id));
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
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_MENU_LIST_MANAGEMENT:edit')")
    public ModelAndView getEdit(@NotNull(message = "请选择菜单") @Min(value = NumeralUtil.POSITIVE_ONE, message = "菜单不存在") Long id) {
        ModelAndView mv = new ModelAndView(path + "menuEdit");
        mv.addObject("p", systemMenuServiceImpl.getByIdParam(id));
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
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_MENU_LIST_MANAGEMENT:view')")
    public ModelAndView getView(@NotNull(message = "请选择菜单") @Min(value = NumeralUtil.POSITIVE_ONE, message = "菜单不存在") Long id) {
        ModelAndView mv = new ModelAndView(path + "menuView");
        mv.addObject("p", systemMenuServiceImpl.getByIdParam(id));
        return mv;
    }

    /**
     * @param param 1 菜单实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 菜单分页查询
     * @description 菜单分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:58
     */
    // method = RequestMethod.POST,
    @RequestMapping(value = "/find/page/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_MENU_LIST_MANAGEMENT')")
    @QueryTarget
    public ResponseResult findPageList(@Validated(value = {ValidationGroup.formPageQuery.class}) SystemMenu param) {
        return systemMenuServiceImpl.findParamPageList(param);
    }

    /**
     * @param id      1 编号
     * @param pId     2 上级编号
     * @param keyword 3 模糊搜索
     * @return com.framework.common.response.ResponseResult
     * @titel 菜单树形查询
     * @description 菜单树形查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 19:27
     */
    @RequestMapping(value = "/find/menu/tree/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_MENU_LIST_MANAGEMENT')")
    public ResponseResult findMenuTreeList(Long id, Long pId, String keyword) {
        return systemMenuServiceImpl.findMenuTreeList(id, pId, keyword);
    }

    /**
     * @param param 1 菜单实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 新增
     * @description 新增
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:58
     */
    @RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_MENU_LIST_MANAGEMENT:add')")
    public ResponseResult save(@Validated(value = {ValidationGroup.formAdd.class}) SystemMenu param) {
        return systemMenuServiceImpl.save(param);
    }

    /**
     * @param param 1 菜单实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 修改
     * @description 修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:59
     */
    @RequestMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_MENU_LIST_MANAGEMENT:edit')")
    public ResponseResult edit(@Validated(value = {ValidationGroup.formEdit.class}) SystemMenu param) {
        return systemMenuServiceImpl.edit(param);
    }

    /**
     * @param idList 1 菜单编号集合
     * @return com.framework.common.response.ResponseResult
     * @titel 批量删除
     * @description 批量删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 18:00
     */
    @RequestMapping(value = "/batch/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_MENU_LIST_MANAGEMENT:batchDel')")
    public ResponseResult batchDel(@NotEmpty(message = "请选择菜单") @Size(min = NumeralUtil.POSITIVE_ONE,
            message = "菜单不存在") @RequestParam(value = "idList[]") List<Long> idList) {
        return systemMenuServiceImpl.batchDeleteList(idList);
    }

    /**
     * @param id 1 菜单编号
     * @return com.framework.common.response.ResponseResult
     * @titel 删除
     * @description 删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 18:00
     */
    @RequestMapping(value = "/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_MENU_LIST_MANAGEMENT:del')")
    public ResponseResult del(@NotNull(message = "请选择菜单") @Min(value = NumeralUtil.POSITIVE_ONE, message = "菜单不存在") Long id) {
        return systemMenuServiceImpl.delId(id);
    }

//    /**
//     * @param id       1 菜单编号
//     * @param menuCode 2 菜单代码
//     * @return com.framework.common.response.ResponseResult
//     * @titel 验证是否重复菜单code
//     * @description 验证是否重复菜单code
//     * @author 邋遢龘鵺
//     * @datetime 2019/12/22 18:00
//     */
//    @RequestMapping("/isExist")
//    @ResponseBody
//    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_MENU_LIST_MANAGEMENT:isExist')")
//    public ResponseResult isExist(Long id, String menuCode) {
//        return systemMenuServiceImpl.isExist(id, menuCode);
//    }


}
