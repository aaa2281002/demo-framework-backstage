package com.framework.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.common.enums
 * @description 操作状态枚举类
 * @date 2022/4/26 11:18
 */
public enum OperaterStatusEnum {
    //-1=删除， 1=新增, 2=修改
    CODE0(-1, "删除"),
    CODE1(1, "新增"),
    CODE2(2, "修改"),

    ;
    private int state;
    private String name;

    OperaterStatusEnum(int state, String name) {
        this.state = state;
        this.name = name;
    }

    public static List<Map<String, Object>> getList() {
        OperaterStatusEnum[] operaterStatusEnums = OperaterStatusEnum.values();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(operaterStatusEnums.length);
        for (OperaterStatusEnum item : operaterStatusEnums) {
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
}
