package com.framework.web.controller.system;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.system.SystemUtil;
import com.framework.model.entity.system.SystemButton;
import com.framework.model.entity.system.SystemRole;
import com.framework.model.entity.system.SystemRoleMenuButton;
import com.framework.service.service.system.SystemButtonService;
import com.framework.service.service.system.SystemMenuService;
import com.framework.service.service.system.SystemRoleMenuButtonService;
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
 * @Description 菜单按钮关联请求控制类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "/system/role/menu/button")
public class SystemRoleMenuButtonController extends BaseController {
    private String path = "lowerright/system/roleMenuButton/";
    @Autowired
    private SystemRoleMenuButtonService systemRoleMenuButtonServiceImpl;
    @Autowired
    private SystemMenuService systemMenuServiceImpl;
    @Autowired
    private SystemButtonService systemButtonServiceImpl;
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
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_ROLE_MENU_BUTTON_MANAGEMENT')")
    public ModelAndView pageList() {
        return new ModelAndView(path + "roleMenuButtonList");
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
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_ROLE_MENU_BUTTON_MANAGEMENT')")
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
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_ROLE_MENU_BUTTON_MANAGEMENT')")
    public ResponseResult findMenuTreeList(Long id, Long pId, String keyword) {
        try {
            return systemMenuServiceImpl.findMenuTreeList(id, pId, keyword);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getError();
        }
    }

    /**
     * @param p 1 按钮实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 查询按钮
     * @Description 查询按钮
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 18:21
     */
    @RequestMapping(value = "/findButtonParamList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_ROLE_MENU_BUTTON_MANAGEMENT')")
    public ResponseResult findButtonParamList(SystemButton p) {
        try {
            return systemButtonServiceImpl.findParamList(p);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getError();
        }
    }

    /**
     * @param param 1 菜单按钮关联实体类
     * @return com.framework.common.response.ResponseResult
     * @Titel 根据条件查询集合关联选中方法
     * @Description 根据条件查询集合关联选中方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 18:21
     */
    @RequestMapping(value = "/findByParamList")
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_ROLE_MENU_BUTTON_MANAGEMENT')")
    public ResponseResult findByParamList(SystemRoleMenuButton param) {
        try {
            return systemRoleMenuButtonServiceImpl.findByParamList(param);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getError();
        }
    }

    /**
     * @param menuId       1 菜单编号
     * @param buttonIdList 2 按钮编号集合
     * @return com.framework.common.response.ResponseResult
     * @Titel 保存菜单关联按钮方法
     * @Description 保存菜单关联按钮方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 17:58
     */
    @RequestMapping(value = "/save")
    @ResponseBody//
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_ROLE_MENU_BUTTON_MANAGEMENT:save')")
    public ResponseResult save(Long roleId, Long menuId, @RequestParam(value = "buttonIdList[]", required = false) List<Long> buttonIdList) {
        try {
            return systemRoleMenuButtonServiceImpl.save(roleId, menuId, buttonIdList);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getError();
        }
    }
}
