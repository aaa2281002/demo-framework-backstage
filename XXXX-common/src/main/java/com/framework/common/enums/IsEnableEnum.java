package com.framework.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.common.enums
 * @description 是否启用枚举类
 * @date 2022/4/26 11:25
 */
public enum IsEnableEnum {
    CODE1(1, "是", "color: limegreen;", "isEnable1", true),
    CODE2(2, "否", "color: red;", "isEnable2", false),
    ;

    private int state;
    private String name;
    private String css;
    private String idName;
    private boolean checked;

    IsEnableEnum(int state, String name, String css, String idName, boolean checked) {
        this.state = state;
        this.name = name;
        this.css = css;
        this.idName = idName;
        this.checked = checked;
    }

    public static List<Map<String, Object>> getList() {
        IsEnableEnum[] oisEnableEnums = IsEnableEnum.values();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(oisEnableEnums.length);
        for (IsEnableEnum item : oisEnableEnums) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("state", item.getState());
            map.put("name", item.getName());
            list.add(map);
        }
        return list;
    }

    public int getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public String getCss() {
        return css;
    }

    public String getIdName() {
        return idName;
    }

    public boolean getChecked() {
        return checked;
    }
}
