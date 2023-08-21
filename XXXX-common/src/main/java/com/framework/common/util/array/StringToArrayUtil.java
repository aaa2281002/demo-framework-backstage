package com.framework.common.util.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.util.array
 * @description 字符串转数组
 * @date 2020/3/28 14:25
 */
public class StringToArrayUtil {
    /**
     * @param value 1 字符串数组
     * @return java.util.List<java.lang.Long>
     * @titel 字符串数组转Long类集合
     * @description 字符串数组转Long类集合
     * @author 邋遢龘鵺
     * @datetime 2020/3/28 14:30
     */
    public static List<Long> stringToLongList(String[] value) {
        List<Long> idList = new ArrayList<Long>();
        for (String str : value) {
            idList.add(Long.parseLong(str));
        }
        return idList;
    }

    /**
     * @param value 1 字符串数组
     * @return java.util.List<java.lang.Integer>
     * @titel 字符串数组转Integer类集合
     * @description 字符串数组转Integer类集合
     * @author 邋遢龘鵺
     * @datetime 2020/3/28 14:30
     */
    public static List<Integer> stringToIntegerList(String[] value) {
        List<Integer> idList = new ArrayList<Integer>();
        for (String str : value) {
            idList.add(Integer.parseInt(str));
        }
        return idList;
    }


}
