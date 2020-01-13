package com.framework.web.controller.system;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.system.SystemUtil;
import com.framework.model.entity.system.SystemRole;
import com.framework.model.entity.system.SystemRoleMenu;
import com.framework.service.service.system.SystemRoleMenuService;
import com.framework.service.service.system.SystemRoleService;
import com.framework.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.controller.system
 * @Description 角色菜单请求控制类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
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
     * @Titel 分页页面跳转
     * @Description 分页页面跳转
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/14 18:20
     */
    @RequestMapping("/page/list")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_ROLE_MENU_MANAGEMENT')")
    public ModelAndView pageList() {
        return new ModelAndView(path + "roleMenuList");
    }

    /**
     * @param param 1 角色实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 角色分页查询
     * @Description 角色分页查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 17:58
     */
    @RequestMapping(value = "/findRoleParamList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_ROLE_MENU_MANAGEMENT')")
    public ResponseResult findRoleParamList(SystemRole param) {
        return systemRoleServiceImpl.findParamPageList(param);
    }

    /**
     * @param id      1 编号
     * @param pId     2 上级编号
     * @param keyword 3 模糊搜索
     * @return com.framework.common.response.ResponseResult
     * @Titel 菜单树形查询
     * @Description 菜单树形查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 18:22
     */
    @RequestMapping(value = "/findMenuTreeList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_ROLE_MENU_MANAGEMENT')")
    public ResponseResult findMenuTreeList(Long roleId, Long id, Long pId, String keyword) {
        try {
            return systemRoleMenuServiceImpl.findMenuTreeList(roleId, id, pId, keyword);
        } catch (Exception e) {
            e.printStackTrace();
            return getError();
        }
    }

    /**
     * @param param 1 角色菜单关联实体类
     * @return com.framework.common.response.ResponseResult
     * @Titel 根据条件查询集合关联选中方法
     * @Description 根据条件查询集合关联选中方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 18:21
     */
    @RequestMapping(value = "/findByParamList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_ROLE_MENU_MANAGEMENT')")
    public ResponseResult findByParamList(SystemRoleMenu param) {
        return systemRoleMenuServiceImpl.findByParamList(param);
    }

    /**
     * @param roleId     1 角色编号
     * @param menuIdList 2 菜单编号集合
     * @return com.framework.common.response.ResponseResult
     * @Titel 保存角色关联菜单方法
     * @Description 保存角色关联菜单方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 17:58
     */
    @RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_ROLE_MENU_MANAGEMENT:save')")
    public ResponseResult save(Long roleId, @RequestParam(value = "menuIdList[]", required = false) List<Long> menuIdList) {
        return systemRoleMenuServiceImpl.save(roleId, menuIdList);
    }

}
