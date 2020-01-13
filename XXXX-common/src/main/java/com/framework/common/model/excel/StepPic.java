package com.framework.common.model.excel;

import java.io.Serializable;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.common.model.excel
 * @Description 设置图片类
 * @Date 2020/1/8 10:46
 * @Version 1.0
 */
public class StepPic implements Serializable {
    private static final long serialVersionUID = -1266063002222808498L;
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
