package com.framework.web.controller.system;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.system.SystemUtil;
import com.framework.model.entity.system.SystemRole;
import com.framework.model.entity.system.SystemUser;
import com.framework.model.entity.system.SystemUserRole;
import com.framework.service.service.system.SystemRoleService;
import com.framework.service.service.system.SystemUserRoleService;
import com.framework.service.service.system.SystemUserService;
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
 * @Description 用户角色关联请求控制类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "/system/user/role")
public class SystemUserRoleController extends BaseController {
    private String path = "lowerright/system/userRole/";
    @Autowired
    private SystemUserRoleService systemUserRoleServiceImpl;
    @Autowired
    private SystemRoleService systemRoleServiceImpl;
    @Autowired
    private SystemUserService systemUserServiceImpl;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Titel 分页页面跳转
     * @Description 分页页面跳转
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/14 18:20
     */
    @RequestMapping("/page/list")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_USER_ROLE_MANAGEMENT')")
    public ModelAndView pageList() {
        return new ModelAndView(path + "userRoleList");
    }

    /**
     * @param param 1 用户实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 用户分页查询
     * @Description 用户分页查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 17:58
     */
    // method = RequestMethod.POST,
    @RequestMapping(value = "/findUserRoleUserPageList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_USER_ROLE_MANAGEMENT')")
    public ResponseResult findUserRoleUserPageList(SystemUser param) {
        try {
            return systemUserServiceImpl.findParamPageList(param);
        } catch (Exception e) {
            e.printStackTrace();
            return getError();
        }
    }

    /**
     * @param param 1 用户实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 角色分页查询
     * @Description 角色分页查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 17:58
     */
    // method = RequestMethod.POST,
    @RequestMapping(value = "/findUserRoleRolePageList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_USER_ROLE_MANAGEMENT')")
    public ResponseResult findUserRoleRolePageList(SystemRole param) {
        try {
            return systemRoleServiceImpl.findParamPageList(param);
        } catch (Exception e) {
            e.printStackTrace();
            return getError();
        }
    }

    /**
     * @param param 1 用户角色关联实体类
     * @return com.framework.common.response.ResponseResult
     * @Titel 根据条件查询集合关联选中方法
     * @Description 根据条件查询集合关联选中方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 18:21
     */
    @RequestMapping(value = "/findByParamList")
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_USER_ROLE_MANAGEMENT')")
    public ResponseResult findByParamList(SystemUserRole param) {
        return systemUserRoleServiceImpl.findByParamList(param);
    }

    /**
     * @param userId     1 用户编号
     * @param roleIdList 2 角色编号集合
     * @return com.framework.common.response.ResponseResult
     * @Titel 保存用户关联角色方法
     * @Description 保存用户关联角色方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 17:58
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_USER_ROLE_MANAGEMENT:save')")
    public ResponseResult save(Long userId, @RequestParam(value = "roleIdList[]", required = false) List<Long> roleIdList) {
        return systemUserRoleServiceImpl.save(userId, roleIdList);
    }

}
