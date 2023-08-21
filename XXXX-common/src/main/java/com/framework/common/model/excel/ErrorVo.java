package com.framework.common.model.excel;

import java.io.Serializable;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.model.excel
 * @description excel设置的错误消息
 * @date 2020/1/8 10:46
 */
public class ErrorVo implements Serializable {
    private static final long serialVersionUID = -1L;
    /**
     * 行号
     */
    private int row;

    /**
     * 列号
     */
    private int cell;

    /**
     * 批准内容
     */
    private String text;

    public ErrorVo() {
    }

    public ErrorVo(int row, int cell, String text) {
        this.row = row;
        this.cell = cell;
        this.text = text;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCell() {
        return cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorVo)) return false;

        ErrorVo errorVo = (ErrorVo) o;

        if (row != errorVo.row) return false;
        if (cell != errorVo.cell) return false;
        return text != null ? text.equals(errorVo.text) : errorVo.text == null;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + cell;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
