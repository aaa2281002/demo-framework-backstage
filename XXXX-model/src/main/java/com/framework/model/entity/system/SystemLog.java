package com.framework.model.entity.system;

import com.framework.model.entity.base.BaseModel;

import java.io.Serializable;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.model.entity.system
 * @Description 系统日志实体类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public class SystemLog extends BaseModel implements Serializable {
    private static final long serialVersionUID = 4661347866595629919L;
    //操作账户名
    private String loginName;
    //操作IP地址
    private String ip;
    //操作标题
    private String operaterTitle;
    //操作内容
    private String operaterContent;
    //公司代码
    private String companyCode;
    //公司编号
    private Long companyId;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getOperaterTitle() {
        return operaterTitle;
    }

    public void setOperaterTitle(String operaterTitle) {
        this.operaterTitle = operaterTitle == null ? null : operaterTitle.trim();
    }

    public String getOperaterContent() {
        return operaterContent;
    }

    public void setOperaterContent(String operaterContent) {
        this.operaterContent = operaterContent == null ? null : operaterContent.trim();
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
}