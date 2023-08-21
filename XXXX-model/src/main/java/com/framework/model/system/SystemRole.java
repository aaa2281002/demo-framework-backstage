package com.framework.model.system;

import com.framework.common.annotation.QueryParam;
import com.framework.common.model.validation.ValidationGroup;
import com.framework.common.util.other.NumeralUtil;
import com.framework.model.base.BaseModel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.model.system
 * @description 角色实体类
 * @datetime 2019/10/11
 */
public class SystemRole extends BaseModel implements Serializable {
    private static final long serialVersionUID = -1L;
    //是否启用=1启用,2禁用
    @NotNull(message = "请选择是否启用", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @Min(value = NumeralUtil.POSITIVE_ONE, message = "错误选择", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @Max(value = NumeralUtil.POSITIVE_TWO, message = "错误选择", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(code = "p.IS_ENABLE")
    private Integer isEnable;
    //角色名称
    @NotBlank(message = "请输入角色名称", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @Size(message = "角色名称最多64个字符", max = NumeralUtil.POSITIVE_SIXTY_FOUR, groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(code = "p.ROLE_NAME")
    private String roleName;
    //角色代码
    @NotBlank(message = "请输入角色代码", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @Size(message = "角色代码最多64个字符", max = NumeralUtil.POSITIVE_SIXTY_FOUR, groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(code = "p.ROLE_CODE")
    private String roleCode;
    //角色级别, 用于用户个角色1对多关联后，  多个角色显示数值最小为称号
    @NotNull(message = "请输入角色级别", groups = {ValidationGroup.formEdit.class})
    @Min(value = NumeralUtil.POSITIVE_ZERO, message = "错误角色级别", groups = {ValidationGroup.formEdit.class})
    @Max(value = NumeralUtil.POSITIVE_TEN_MILLION, message = "错误角色级别", groups = {ValidationGroup.formEdit.class})
    @QueryParam(code = "p.ROLE_LEVEL")
    private Integer roleLevel;
    //角色描述
    @Size(message = "描述最多1000个字符", max = NumeralUtil.POSITIVE_ONE_THOUSAND, groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(code = "p.DESCRIPTION")
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