package com.framework.model.system.vo;

import com.framework.model.system.SystemType;

import java.io.Serializable;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.model.system.vo
 * @description 系统类型视图类
 * @datetime 2023/3/30 17:45
 */
public class SystemTypeVo extends SystemType implements Serializable {
    private static final long serialVersionUID = -1L;
    //上级类型代码
    private String parentTypeCode;

    public String getParentTypeCode() {
        return parentTypeCode;
    }

    public void setParentTypeCode(String parentTypeCode) {
        this.parentTypeCode = parentTypeCode == null ? null : parentTypeCode.trim();
    }
}
