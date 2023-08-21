package com.framework.web.controller.system;

import com.framework.common.annotation.QueryTarget;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.system.SystemUtil;
import com.framework.model.system.SystemRole;
import com.framework.service.system.SystemRoleService;
import com.framework.service.system.SystemUserRoleService;
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
 * @description 用户角色关联请求控制类
 * @datetime 2019/10/11
 */
@Validated
@Controller
@RequestMapping(value = "/system/user/role")
public class SystemUserRoleController extends BaseController {
    private String path = "lowerright/system/userRole/";
    @Autowired
    private SystemUserRoleService systemUserRoleServiceImpl;
    @Autowired
    private SystemRoleService systemRoleServiceImpl;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @title 分页页面跳转
     * @description 分页页面跳转
     * @author 邋遢龘鵺
     * @datetime 2019/12/14 18:20
     */
    @RequestMapping("/page/list")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_USER_ROLE_LIST_MANAGEMENT')")
    public ModelAndView pageList(@NotNull(message = "请选择用户") @Min(value = NumeralUtil.POSITIVE_ONE, message = "用户不存在") Long userId) {
        ModelAndView mv = new ModelAndView(path + "userRoleList");
        mv.addObject("userId", userId);
        mv.addObject("userRoleData", systemUserRoleServiceImpl.findByParamList(userId));
        return mv;
    }

    /**
     * @param param 1 用户实体类对象
     * @return com.framework.common.response.ResponseResult
     * @title 角色分页查询
     * @description 角色分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:58
     */
    // method = RequestMethod.POST,
    @RequestMapping(value = "/find/user/role/role/page/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @QueryTarget
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_USER_ROLE_LIST_MANAGEMENT')")
    public ResponseResult findUserRoleRolePageList(SystemRole param) {
        return systemRoleServiceImpl.findUserByRolePageList(param);
    }

    /**
     * @param userId     1 用户编号
     * @param roleIdList 2 角色编号集合
     * @return com.framework.common.response.ResponseResult
     * @title 保存用户关联角色方法
     * @description 保存用户关联角色方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:58
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_USER_ROLE_LIST_MANAGEMENT:save')")
    public ResponseResult save(@NotNull(message = "请选择用户") @Min(value = NumeralUtil.POSITIVE_ONE, message = "用户不存在") Long userId,
                               @RequestParam(value = "roleIdList[]", required = false) List<Long> roleIdList) {
        return systemUserRoleServiceImpl.save(userId, roleIdList);
    }

}
