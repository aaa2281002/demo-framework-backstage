package com.framework.model.entity.system;

import com.framework.model.entity.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.model.entity.system
 * @Description 用户管理类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public class SystemUser extends BaseModel implements Serializable {
    private static final long serialVersionUID = -2210464400247414504L;
    //是否启用=1启用,2禁用
    private Integer isEnable;
    //是否锁住 1=否, 2=是
    private Integer accountLocked;
    //帐号是否过期 1=否, 2=是
    private Integer accountExpired;
    //凭证是否过期 1=否, 2=是
    private Integer credentialsExpired;
    //账户名
    private String loginName;
    //密码
    private String password;
    //姓名
    private String name;
    //公司唯一代码,UUID32位
    private String companyCode;
    //公司编号
    private Integer companyId;
    //电话
    private String telphone;
    //邮箱
    private String email;
    //顶级为0.根据当前账户的等级+1进行级别控制
    private Integer userLevel;
    //系统管理员(代理商)描述
    private String description;

    /**
     * 以下为临时字段，用于业务逻辑处理
     */
    //确认密码
    private String confirmPassword;
    //角色集合
    private List<SystemRole> roleList;
    //角色实体类
    private SystemRole systemRole;
    //角色代码
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