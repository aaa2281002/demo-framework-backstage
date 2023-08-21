package com.framework.common.enums.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.common.enums.menu
 * @description 系统菜单打开方式枚举类
 * @date 2022/5/12 16:37
 */
public enum MenuTargetEnum {
    //-1=删除， 1=新增, 2=修改
    CODE0("_self", "当前页打开新页面", false, false),
    CODE1("_blank", "浏览器新窗口页面", false, false),
    CODE2("_item", "子页面", true, false),
    CODE3("_event", "提交事件", true, true),
    ;

    private String code;
    private String name;
    private Boolean isView;
    private Boolean isCache;

    MenuTargetEnum(String code, String name, boolean isView, boolean isCache) {
        this.code = code;
        this.name = name;
        this.isView = isView;
        this.isCache = isCache;
    }

    public static boolean getCodeIsView(String code) {
        MenuTargetEnum[] values = MenuTargetEnum.values();
        for (MenuTargetEnum item : values) {
            if (item.getCode().equals(code)) {
                return item.getView();
            }
        }
        return false;
    }

    public static boolean getCodeIsCache(String code) {
        MenuTargetEnum[] values = MenuTargetEnum.values();
        for (MenuTargetEnum item : values) {
            if (item.getCode().equals(code)) {
                return item.getCache();
            }
        }
        return false;
    }

    public static List<Map<String, Object>> getList() {
        MenuTargetEnum[] values = MenuTargetEnum.values();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(values.length);
        for (MenuTargetEnum item : values) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", item.getCode());
            map.put("name", item.getName());
            list.add(map);
        }
        return list;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Boolean getView() {
        return isView;
    }

    public Boolean getCache() {
        return isCache;
    }
}
