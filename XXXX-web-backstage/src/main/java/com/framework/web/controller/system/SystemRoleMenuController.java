package com.framework.web.controller.system;

import com.framework.common.annotation.QueryTarget;
import com.framework.common.model.validation.ValidationGroup;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.system.SystemUtil;
import com.framework.model.system.SystemRole;
import com.framework.model.system.SystemRoleMenu;
import com.framework.service.system.SystemRoleMenuService;
import com.framework.service.system.SystemRoleService;
import com.framework.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.controller.system
 * @description 角色菜单请求控制类
 * @datetime 2019/10/11
 */
@Validated
@Controller
@RequestMapping(value = "/system/role/menu")
public class SystemRoleMenuController extends BaseController {
    private String path = "lowerright/system/roleMenu/";
    @Autowired
    private SystemRoleMenuService systemRoleMenuServiceImpl;
    @Autowired
    private SystemRoleService systemRoleServiceImpl;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @titel 分页页面跳转
     * @description 分页页面跳转
     * @author 邋遢龘鵺
     * @datetime 2019/12/14 18:20
     */
    @RequestMapping("/page/list")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_ROLE_MENU_LIST_MANAGEMENT')")
    public ModelAndView pageList(@NotNull(message = "请选择角色") @Min(value = NumeralUtil.POSITIVE_ONE, message = "角色不存在") Long roleId) {
        ModelAndView mv = new ModelAndView(path + "roleMenuList");
        mv.addObject("roleId", roleId);
        return mv;
    }

//    /**
//     * @param param 1 角色实体类对象
//     * @return com.framework.common.response.ResponseResult
//     * @titel 角色分页查询
//     * @description 角色分页查询
//     * @author 邋遢龘鵺
//     * @datetime 2019/12/22 17:58
//     */
//    @RequestMapping(value = "/find/role/param/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ResponseBody
//    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_ROLE_MENU_LIST_MANAGEMENT')")
//    @QueryTarget
//    public ResponseResult findRoleParamList(@Validated(value = {ValidationGroup.formPageQuery.class}) SystemRole param) {
//        return systemRoleServiceImpl.findParamPageList(param);
//    }

    /**
     * @param id      1 编号
     * @param pId     2 上级编号
     * @param keyword 3 模糊搜索
     * @return com.framework.common.response.ResponseResult
     * @titel 菜单树形查询
     * @description 菜单树形查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 18:22
     */
    @RequestMapping(value = "/find/menu/tree/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_ROLE_MENU_LIST_MANAGEMENT')")
    public ResponseResult findMenuTreeList(Long roleId, Long id, Long pId, String keyword) {
        return systemRoleMenuServiceImpl.findMenuTreeList(roleId, id, pId, keyword);
    }

//    /**
//     * @param param 1 角色菜单关联实体类
//     * @return com.framework.common.response.ResponseResult
//     * @titel 根据条件查询集合关联选中方法
//     * @description 根据条件查询集合关联选中方法
//     * @author 邋遢龘鵺
//     * @datetime 2019/12/22 18:21
//     */
//    @RequestMapping(value = "/find/by/param/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ResponseBody
//    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_ROLE_MENU_LIST_MANAGEMENT')")
//    public ResponseResult findByParamList(@Validated(value = {ValidationGroup.formPageQuery.class}) SystemRoleMenu param) {
//        return systemRoleMenuServiceImpl.findByParamList(param);
//    }

    /**
     * @param roleId     1 角色编号
     * @param menuIdList 2 菜单编号集合
     * @return com.framework.common.response.ResponseResult
     * @titel 保存角色关联菜单方法
     * @description 保存角色关联菜单方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:58
     */
    @RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_ROLE_MENU_LIST_MANAGEMENT:save')")
    public ResponseResult save(@NotNull(message = "请选择角色") @Min(value = NumeralUtil.POSITIVE_ONE, message = "角色不存在") Long roleId,
                               @RequestParam(value = "menuIdList[]", required = false) List<Long> menuIdList) {
        return systemRoleMenuServiceImpl.save(roleId, menuIdList);
    }

}
