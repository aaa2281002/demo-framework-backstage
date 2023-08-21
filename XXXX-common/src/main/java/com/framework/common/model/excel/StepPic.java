package com.framework.common.model.excel;

import java.io.Serializable;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.model.excel
 * @description 设置图片类
 * @date 2020/1/8 10:46
 */
public class StepPic implements Serializable {
    private static final long serialVersionUID = -1L;
    private String suffix;

    private byte[] data;

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix == null ? null : suffix.trim();
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
