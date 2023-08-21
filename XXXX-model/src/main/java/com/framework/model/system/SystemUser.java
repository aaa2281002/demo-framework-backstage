package com.framework.model.system;

import com.framework.common.annotation.QueryParam;
import com.framework.common.model.validation.ValidationGroup;
import com.framework.common.util.match.MatchConstantUtil;
import com.framework.common.util.other.NumeralUtil;
import com.framework.model.base.BaseModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.model.system
 * @description 用户管理类
 * @datetime 2019/10/11
 */
public class SystemUser extends BaseModel implements Serializable {
    private static final long serialVersionUID = -1L;
    //是否启用=1启用,2禁用
    @NotNull(message = "请选择是否启用", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @Min(value = NumeralUtil.POSITIVE_ONE, message = "错误选择", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @Max(value = NumeralUtil.POSITIVE_TWO, message = "错误选择", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(name = "isEnable", code = "p.IS_ENABLE")
    private Integer isEnable;
    //是否锁住 1=否, 2=是
    @Min(value = NumeralUtil.POSITIVE_ONE, message = "错误选择", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @Max(value = NumeralUtil.POSITIVE_TWO, message = "错误选择", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(code = "p.ACCOUNT_LOCKED")
    private Integer accountLocked;
    //帐号是否过期 1=否, 2=是
    @Min(value = NumeralUtil.POSITIVE_ONE, message = "错误选择", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @Max(value = NumeralUtil.POSITIVE_TWO, message = "错误选择", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(code = "p.ACCOUNT_EXPIRED")
    private Integer accountExpired;
    //凭证是否过期 1=否, 2=是
    @Min(value = NumeralUtil.POSITIVE_ONE, message = "错误选择", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @Max(value = NumeralUtil.POSITIVE_TWO, message = "错误选择", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(code = "p.CREDENTIALS_EXPIRED")
    private Integer credentialsExpired;
    //账户名
    @NotBlank(message = "请输入账户名", groups = {ValidationGroup.formAdd.class})
    @Size(message = "账户名最少6至最多25个字符", min = NumeralUtil.POSITIVE_SIX, max = NumeralUtil.POSITIVE_TWENTY_FIVE, groups =
            {ValidationGroup.formAdd.class})
    @QueryParam(code = "p.LOGIN_NAME")
    private String loginName;
    //密码
    @NotBlank(message = "请输入密码", groups = {ValidationGroup.formAdd.class, ValidationGroup.formPassword.class})
//    @Size(message = "密码最少6至最多25个字符", min = NumeralUtil.POSITIVE_SIX, max = NumeralUtil.POSITIVE_TWENTY_FIVE, groups =
//            {ValidationGroup.formAdd.class, ValidationGroup.formPassword.class})
    @QueryParam(code = "p.PASSWORD")
    private String password;
    //姓名
    @NotBlank(message = "请输入姓名", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class, ValidationGroup.formOther.class})
    @Size(message = "姓名最少1至最多20个字符", min = NumeralUtil.POSITIVE_ONE, max = NumeralUtil.POSITIVE_TWENTY, groups =
            {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(code = "p.NAME")
    private String name;
    //公司唯一代码,UUID32位
//    @NotBlank(message = "请输入公司代码", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
//    @Size(message = "公司代码最少1至最多20个字符", min = NumeralUtil.POSITIVE_ONE, max = NumeralUtil.POSITIVE_TWENTY, groups =
//            {ValidationGroup.formEdit.class,
//                    ValidationGroup.formAdd.class})
    @QueryParam(code = "p.COMPANY_CODE")
    private String companyCode;
    //公司编号
//    @NotNull(message = "请选择公司", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
//    @Min(value = NumeralUtil.POSITIVE_ONE, message = "错误公司", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(code = "p.COMPANY_ID")
    private Integer companyId;
    //电话
    @NotBlank(message = "请输入电话号码", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class, ValidationGroup.formOther.class})
    @Pattern(regexp = MatchConstantUtil.REGEX_PHONE, message = "请输入正确电话号码!", groups =
            {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(code = "p.TELPHONE")
    private String telphone;
    //邮箱
    @Email(message = "请输入正确邮箱", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class, ValidationGroup.formOther.class})
    @NotBlank(message = "请输入邮箱", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class, ValidationGroup.formOther.class})
    @Size(message = "邮箱最少1至最多20个字符", min = NumeralUtil.POSITIVE_ONE, max = NumeralUtil.POSITIVE_THIRTY_TWO, groups =
            {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class, ValidationGroup.formOther.class})
    @QueryParam(code = "p.EMAIL")
    private String email;
    //顶级为0.根据当前账户的等级+1进行级别控制
    @NotNull(message = "请输入账户级别", groups = {ValidationGroup.formEdit.class})
    @Min(value = NumeralUtil.NEGATIVE_ONE, message = "错误账户级别", groups = {ValidationGroup.formEdit.class})
    @QueryParam(code = "p.USER_LEVEL")
    private Integer userLevel;
    //系统管理员(代理商)描述
    @Size(message = "描述最多1000个字符", max = NumeralUtil.POSITIVE_ONE_THOUSAND, groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
    @QueryParam(code = "p.DESCRIPTION")
    private String description;

    /**
     * 以下为临时字段，用于业务逻辑处理
     */
    //确认密码
    @NotBlank(message = "请输入确认密码", groups = {ValidationGroup.formAdd.class, ValidationGroup.formPassword.class})
//    @Size(message = "确认密码最少6至最多25个字符", min = NumeralUtil.POSITIVE_SIX, max = NumeralUtil.POSITIVE_TWENTY_FIVE, groups =
//            {ValidationGroup.formAdd.class, ValidationGroup.formPassword.class})
    private String confirmPassword;
    //角色集合
    private List<SystemRole> roleList;
    //角色实体类
    private SystemRole systemRole;
    //角色代码
    @QueryParam(code = "sr.ROLE_CODE")
    private String roleCode;

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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
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

    public List<SystemRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SystemRole> roleList) {
        this.roleList = roleList;
    }

    public SystemRole getSystemRole() {
        return systemRole;
    }

    public void setSystemRole(SystemRole systemRole) {
        this.systemRole = systemRole;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SystemUser)) return false;

        SystemUser that = (SystemUser) o;

        if (isEnable != null ? !isEnable.equals(that.isEnable) : that.isEnable != null) return false;
        if (accountLocked != null ? !accountLocked.equals(that.accountLocked) : that.accountLocked != null)
            return false;
        if (accountExpired != null ? !accountExpired.equals(that.accountExpired) : that.accountExpired != null)
            return false;
        if (credentialsExpired != null ? !credentialsExpired.equals(that.credentialsExpired) : that.credentialsExpired != null)
            return false;
        if (loginName != null ? !loginName.equals(that.loginName) : that.loginName != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (companyCode != null ? !companyCode.equals(that.companyCode) : that.companyCode != null) return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (telphone != null ? !telphone.equals(that.telphone) : that.telphone != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (userLevel != null ? !userLevel.equals(that.userLevel) : that.userLevel != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (confirmPassword != null ? !confirmPassword.equals(that.confirmPassword) : that.confirmPassword != null)
            return false;
        if (roleList != null ? !roleList.equals(that.roleList) : that.roleList != null) return false;
        if (systemRole != null ? !systemRole.equals(that.systemRole) : that.systemRole != null) return false;
        return roleCode != null ? roleCode.equals(that.roleCode) : that.roleCode == null;
    }

    @Override
    public int hashCode() {
        int result = isEnable != null ? isEnable.hashCode() : 0;
        result = 31 * result + (accountLocked != null ? accountLocked.hashCode() : 0);
        result = 31 * result + (accountExpired != null ? accountExpired.hashCode() : 0);
        result = 31 * result + (credentialsExpired != null ? credentialsExpired.hashCode() : 0);
        result = 31 * result + (loginName != null ? loginName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (telphone != null ? telphone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (userLevel != null ? userLevel.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (confirmPassword != null ? confirmPassword.hashCode() : 0);
        result = 31 * result + (roleList != null ? roleList.hashCode() : 0);
        result = 31 * result + (systemRole != null ? systemRole.hashCode() : 0);
        result = 31 * result + (roleCode != null ? roleCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SystemUser{" +
                "isEnable=" + isEnable +
                ", accountLocked=" + accountLocked +
                ", accountExpired=" + accountExpired +
                ", credentialsExpired=" + credentialsExpired +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", companyId=" + companyId +
                ", telphone='" + telphone + '\'' +
                ", email='" + email + '\'' +
                ", userLevel=" + userLevel +
                ", description='" + description + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", roleList=" + roleList +
                ", systemRole=" + systemRole +
                ", roleCode='" + roleCode + '\'' +
                '}';
    }
}