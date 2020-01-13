package com.framework.model.entity.system;

import com.framework.model.entity.base.BaseModel;

import java.io.Serializable;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.model.entity.system
 * @Description 用户角色关联实体类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public class SystemUserRole extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1800943073792082053L;
    //用户编号
    private Long userId;
    //角色编号
    private Long roleId;

    //以下是临时字段
    //登录名
    private String loginName;
    //角色名
    private String roleName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }
}