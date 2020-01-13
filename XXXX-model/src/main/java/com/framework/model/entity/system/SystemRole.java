package com.framework.model.entity.system;

import com.framework.model.entity.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.model.entity.system
 * @Description 角色实体类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public class SystemRole extends BaseModel implements Serializable {
    private static final long serialVersionUID = 3573587158296362210L;
    //是否启用=1启用,2禁用
    private Integer isEnable;
    //角色名称
    private String roleName;
    //角色代码
    private String roleCode;
    //角色级别, 用于用户个角色1对多关联后，  多个角色显示数值最小为称号
    private Integer roleLevel;
    //角色描述
    private String description;

    //以下是临时字段
    private List<String> menuCodeList;

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public List<String> getMenuCodeList() {
        return menuCodeList;
    }

    public void setMenuCodeList(List<String> menuCodeList) {
        this.menuCodeList = menuCodeList;
    }
}