package com.framework.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.enums
 * @description 文件信息类型枚举类
 * @datetime 2019/10/11
 */
public enum FileTypeEnum {
    TXT_CODE(1, "TXT"),
    DOC_CODE(2, "DOC"),
    ;


    private int code;
    private String name;

    FileTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static FileTypeEnum getFileTypeEnum(Integer code) {
        if (code == null) {
            return null;
        }
        FileTypeEnum[] values = FileTypeEnum.values();
        for (FileTypeEnum e : values) {
            if (e.getCode() == code.intValue()) {
                return e;
            }
        }
        return null;
    }

    public static Integer getFileTypeEnum(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        FileTypeEnum[] values = FileTypeEnum.values();
        for (FileTypeEnum e : values) {
            if (e.getName().equalsIgnoreCase(name)) {
                return e.getCode();
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
