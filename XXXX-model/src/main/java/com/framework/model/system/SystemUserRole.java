package com.framework.model.system;

import com.framework.common.annotation.QueryParam;
import com.framework.common.model.validation.ValidationGroup;
import com.framework.common.util.other.NumeralUtil;
import com.framework.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.model.system
 * @description 用户角色关联实体类
 * @datetime 2019/10/11
 */
@ApiModel(value = "用户角色关联", description = "用户角色关联", parent = BaseModel.class)
public class SystemUserRole extends BaseModel implements Serializable {
    private static final long serialVersionUID = -1L;
    //用户编号
    @ApiModelProperty(value = "用户编号", name = "userId", dataType = "Long", required = true, allowableValues = "20", hidden = false)
    @NotNull(message = "请选择用户", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @Min(value = NumeralUtil.POSITIVE_ONE, message = "错误用户", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    private Long userId;
    //角色编号
    @ApiModelProperty(value = "角色编号", name = "roleId", dataType = "Long", required = false, allowableValues = "20", hidden = false)
    private Long roleId;

    //以下是临时字段
    //登录名
    @ApiModelProperty(value = "登录名", name = "loginName", dataType = "String", required = false, allowableValues = "6-25", hidden = true)
    @QueryParam(code = "su.LOGIN_NAME")
    private String loginName;
    //角色名
    @ApiModelProperty(value = "角色名", name = "roleName", dataType = "String", required = false, allowableValues = "64", hidden = true)
    @QueryParam(code = "sr.ROLE_NAME")
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