package com.framework.model.system.vo;

import com.framework.common.model.validation.ValidationGroup;
import com.framework.model.system.SystemType;
import com.framework.model.system.SystemUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.model.system.vo
 * @description 系统用户视图类
 * @datetime 2023/5/23 15:00
 */
@ApiModel(value = "系统用户视图", description = "系统用户视图", parent = SystemUser.class)
public class SystemUserVo extends SystemUser implements Serializable {
    @ApiModelProperty(value = "确认密码", name = "oldPassword", dataType = "String", required = false, hidden = false)
    @NotBlank(message = "请输入确认密码", groups = {ValidationGroup.formAdd.class, ValidationGroup.formPassword.class})
    private String oldPassword;
    //角色编号
    @ApiModelProperty(value = "角色编号", name = "roleId", dataType = "String", required = false, allowableValues = "20", hidden = true)
    private String roleId;
    //角色名称
    @ApiModelProperty(value = "角色名称", name = "roleName", dataType = "String", required = false, allowableValues = "64", hidden = true)
    private String roleName;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword == null ? null : oldPassword.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
