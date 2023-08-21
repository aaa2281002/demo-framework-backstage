package com.framework.model.system;

import com.framework.common.annotation.QueryParam;
import com.framework.model.base.BaseModel;

import java.io.Serializable;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.model.system
 * @description 系统日志实体类
 * @datetime 2019/10/11
 */
public class SystemLog extends BaseModel implements Serializable {
    private static final long serialVersionUID = -1L;
    //操作账户名
    @QueryParam(code = "p.LOGIN_NAME")
    private String loginName;
    //操作IP地址
    @QueryParam(code = "p.IP")
    private String ip;
    //操作标题
    @QueryParam(code = "p.OPERATER_TITLE")
    private String operaterTitle;
    //操作内容
    @QueryParam(code = "p.OPERATER_CONTENT")
    private String operaterContent;
    //公司代码
    @QueryParam(code = "p.COMPANY_CODE")
    private String companyCode;
    //公司编号
    @QueryParam(code = "p.COMPANY_ID")
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