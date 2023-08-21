package com.framework.model.system;

import com.framework.common.annotation.QueryParam;
import com.framework.common.model.validation.ValidationGroup;
import com.framework.common.util.match.MatchConstantUtil;
import com.framework.common.util.other.NumeralUtil;
import com.framework.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.model.system
 * @description 用户管理类
 * @datetime 2019/10/11
 */
@ApiModel(value = "用户", description = "用户", parent = BaseModel.class)
public class SystemUser extends BaseModel implements Serializable {
    private static final long serialVersionUID = -1L;
    //是否启用=1启用,2禁用
    @ApiModelProperty(value = "否启用=1启用,2禁用", name = "isEnable", dataType = "Integer", required = true, allowableValues = "1", hidden = false)
    @NotNull(message = "请选择是否启用", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @Min(value = NumeralUtil.POSITIVE_ONE, message = "错误选择", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @Max(value = NumeralUtil.POSITIVE_TWO, message = "错误选择", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(name = "isEnable", code = "p.IS_ENABLE")
    private Integer isEnable;
    //是否锁住 1=否, 2=是
    @ApiModelProperty(value = "是否锁住 1=否, 2=是", name = "accountLocked", dataType = "Integer", required = false, allowableValues = "1", hidden = true)
    @Min(value = NumeralUtil.POSITIVE_ONE, message = "错误选择", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @Max(value = NumeralUtil.POSITIVE_TWO, message = "错误选择", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(code = "p.ACCOUNT_LOCKED")
    private Integer accountLocked;
    //帐号是否过期 1=否, 2=是
    @ApiModelProperty(value = "帐号是否过期 1=否, 2=是", name = "accountExpired", dataType = "Integer", required = false, allowableValues = "1", hidden = true)
    @Min(value = NumeralUtil.POSITIVE_ONE, message = "错误选择", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @Max(value = NumeralUtil.POSITIVE_TWO, message = "错误选择", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(code = "p.ACCOUNT_EXPIRED")
    private Integer accountExpired;
    //凭证是否过期 1=否, 2=是
    @ApiModelProperty(value = "凭证是否过期 1=否, 2=是", name = "credentialsExpired", dataType = "Integer", required = false, allowableValues = "1", hidden = true)
    @Min(value = NumeralUtil.POSITIVE_ONE, message = "错误选择", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @Max(value = NumeralUtil.POSITIVE_TWO, message = "错误选择", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(code = "p.CREDENTIALS_EXPIRED")
    private Integer credentialsExpired;
    //账户名
    @ApiModelProperty(value = "账户名", name = "loginName", dataType = "String", required = true, allowableValues = "6-25", hidden = false)
    @NotBlank(message = "请输入账户名", groups = {ValidationGroup.formAdd.class})
    @Size(message = "账户名最少6至最多25个字符", min = NumeralUtil.POSITIVE_SIX, max = NumeralUtil.POSITIVE_TWENTY_FIVE, groups =
            {ValidationGroup.formAdd.class})
    @QueryParam(code = "p.LOGIN_NAME")
    private String loginName;
    //密码
    @ApiModelProperty(value = "密码", name = "password", dataType = "String", required = true, hidden = false)
    @NotBlank(message = "请输入密码", groups = {ValidationGroup.formAdd.class, ValidationGroup.formPassword.class})
//    @Size(message = "密码最少6至最多25个字符", min = NumeralUtil.POSITIVE_SIX, max = NumeralUtil.POSITIVE_TWENTY_FIVE, groups =
//            {ValidationGroup.formAdd.class, ValidationGroup.formPassword.class})
    @QueryParam(code = "p.PASSWORD")
    private String password;
    //姓名
    @ApiModelProperty(value = "姓名", name = "name", dataType = "String", required = true, allowableValues = "1-25", hidden = false)
    @NotBlank(message = "请输入姓名", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class, ValidationGroup.formOther.class})
    @Size(message = "姓名最少1至最多20个字符", min = NumeralUtil.POSITIVE_ONE, max = NumeralUtil.POSITIVE_TWENTY, groups =
            {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class, ValidationGroup.formOther.class})
    @QueryParam(code = "p.NAME")
    private String name;
    //公司唯一代码,UUID32位
//    @NotBlank(message = "请输入公司代码", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
//    @Size(message = "公司代码最少1至最多20个字符", min = NumeralUtil.POSITIVE_ONE, max = NumeralUtil.POSITIVE_TWENTY, groups =
//            {ValidationGroup.formEdit.class,
//                    ValidationGroup.formAdd.class})
    @ApiModelProperty(value = "公司唯一代码", name = "companyCode", dataType = "String", required = true, allowableValues = "32", hidden = true)
    @QueryParam(code = "p.COMPANY_CODE")
    private String companyCode;
    //公司编号
//    @NotNull(message = "请选择公司", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
//    @Min(value = NumeralUtil.POSITIVE_ONE, message = "错误公司", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @ApiModelProperty(value = "公司编号", name = "companyId", dataType = "Long", required = true, allowableValues = "20", hidden = true)
    @QueryParam(code = "p.COMPANY_ID")
    private Long companyId;
    //电话
    @ApiModelProperty(value = "电话", name = "telphone", dataType = "String", required = true, hidden = false)
    @NotBlank(message = "请输入电话号码", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class, ValidationGroup.formOther.class})
    @Pattern(regexp = MatchConstantUtil.REGEX_PHONE, message = "请输入正确电话号码!", groups =
            {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class, ValidationGroup.formOther.class})
    @QueryParam(code = "p.TELPHONE")
    private String telphone;
    //邮箱
    @ApiModelProperty(value = "邮箱", name = "email", dataType = "String", required = true, allowableValues = "1-32", hidden = false)
    @Email(message = "请输入正确邮箱", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class, ValidationGroup.formOther.class})
    @NotBlank(message = "请输入邮箱", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class, ValidationGroup.formOther.class})
    @Size(message = "邮箱最少1至最多32个字符", min = NumeralUtil.POSITIVE_ONE, max = NumeralUtil.POSITIVE_THIRTY_TWO, groups =
            {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class, ValidationGroup.formOther.class})
    @QueryParam(code = "p.EMAIL")
    private String email;
    //用户层级, 顶级为0.根据当前账户的等级+1进行级别控制
    @ApiModelProperty(value = "用户层级", name = "userLevel", dataType = "Integer", required = true, allowableValues = "11", hidden = true)
    @NotNull(message = "请输入账户级别", groups = {ValidationGroup.formEdit.class})
    @Min(value = NumeralUtil.NEGATIVE_ONE, message = "错误账户级别", groups = {ValidationGroup.formEdit.class})
    @QueryParam(code = "p.USER_LEVEL")
    private Integer userLevel;
    //系统管理员(代理商)描述
    @ApiModelProperty(value = "描述", name = "description", dataType = "String", required = false, allowableValues = "1000", hidden = false)
    @Size(message = "描述最多1000个字符", max = NumeralUtil.POSITIVE_ONE_THOUSAND, groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(code = "p.DESCRIPTION")
    private String description;

    /**
     * 以下为临时字段，用于业务逻辑处理
     */
    //确认密码
    @ApiModelProperty(value = "确认密码", name = "confirmPassword", dataType = "String", required = true, hidden = false)
    @NotBlank(message = "请输入确认密码", groups = {ValidationGroup.formAdd.class, ValidationGroup.formPassword.class})
//    @Size(message = "确认密码最少6至最多25个字符", min = NumeralUtil.POSITIVE_SIX, max = NumeralUtil.POSITIVE_TWENTY_FIVE, groups =
//            {ValidationGroup.formAdd.class, ValidationGroup.formPassword.class})
    private String confirmPassword;
    //角色集合
    @ApiModelProperty(value = "角色集合", name = "roleCodeList", dataType = "List<String>", required = true, hidden = true)
    private List<String> roleCodeList;
    //角色代码
    @ApiModelProperty(value = "角色代码", name = "roleCode", dataType = "String", required = true, hidden = true)
    @QueryParam(code = "sr.ROLE_CODE")
    private String roleCode;
    //jwt代码
    @ApiModelProperty(value = "jwt代码", name = "token", dataType = "String", required = true, hidden = true)
    private String token;

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(Integer accountLocked) {
        this.accountLocked = accountLocked;
    }

    public Integer getAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(Integer accountExpired) {
        this.accountExpired = accountExpired;
    }

    public Integer getCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(Integer credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword == null ? null : confirmPassword.trim();
    }

    public List<String> getRoleCodeList() {
        return roleCodeList;
    }

    public void setRoleCodeList(List<String> roleCodeList) {
        this.roleCodeList = roleCodeList;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemUser that = (SystemUser) o;
        return Objects.equals(isEnable, that.isEnable) && Objects.equals(accountLocked, that.accountLocked) && Objects.equals(accountExpired, that.accountExpired) && Objects.equals(credentialsExpired, that.credentialsExpired) && Objects.equals(loginName, that.loginName) && Objects.equals(password, that.password) && Objects.equals(name, that.name) && Objects.equals(companyCode, that.companyCode) && Objects.equals(companyId, that.companyId) && Objects.equals(telphone, that.telphone) && Objects.equals(email, that.email) && Objects.equals(userLevel, that.userLevel) && Objects.equals(description, that.description) && Objects.equals(confirmPassword, that.confirmPassword) && Objects.equals(roleCodeList, that.roleCodeList) && Objects.equals(roleCode, that.roleCode) && Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isEnable, accountLocked, accountExpired, credentialsExpired, loginName, password, name, companyCode, companyId, telphone, email, userLevel, description, confirmPassword, roleCodeList, roleCode, token);
    }
}