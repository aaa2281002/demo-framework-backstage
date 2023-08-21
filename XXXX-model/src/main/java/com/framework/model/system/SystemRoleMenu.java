package com.framework.model.system;

import com.framework.common.annotation.QueryParam;
import com.framework.common.model.validation.ValidationGroup;
import com.framework.common.util.other.NumeralUtil;
import com.framework.model.base.BaseModel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.model.system
 * @description 角色菜单关联实体类
 * @datetime 2019/10/11
 */
public class SystemRoleMenu extends BaseModel implements Serializable {
    private static final long serialVersionUID = -1L;
    //角色编号
    @NotNull(message = "请选择角色", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @Min(value = NumeralUtil.POSITIVE_ONE, message = "错误角色", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    private Long roleId;
    //菜单编号
    @Min(value = NumeralUtil.POSITIVE_ONE, message = "错误菜单", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    private Long menuId;

    /**
     * 以下是临时字段，用于业务逻辑处理
     */
    //角色名称
    @QueryParam(code = "sr.ROLE_NAME")
    private String roleName;
    //菜单名称
//    @QueryParam(code = "sm.MENU_NAME")
    private String menuName;
    //角色代码
    @QueryParam(code = "sr.ROLE_CODE")
    private String roleCode;
    //菜单代码
    @QueryParam(code = "sm.MENU_CODE")
    private String menuCode;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }
}