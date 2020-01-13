package com.framework.model.entity.system;

import com.framework.model.entity.base.BaseModel;

import java.io.Serializable;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.model.entity.system
 * @Description 角色菜单关联实体类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public class SystemRoleMenu extends BaseModel implements Serializable {
    private static final long serialVersionUID = 3596497578198519819L;
    //角色编号
    private Long roleId;
    //菜单编号
    private Long menuId;

    /**
     * 以下是临时字段，用于业务逻辑处理
     */
    //角色名称
    private String roleName;
    //菜单名称
    private String menuName;
    //角色代码
    private String roleCode;
    //菜单代码
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