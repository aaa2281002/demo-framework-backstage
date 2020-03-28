package com.framework.common.util.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.common.util.array
 * @Description 字符串转数组
 * @Date 2020/3/28 14:25
 * @Version 1.0
 */
public class StringToArrayUtil {
    /**
     * @param value 1 字符串数组
     * @return java.util.List<java.lang.Long>
     * @Titel 字符串数组转Long类集合
     * @Description 字符串数组转Long类集合
     * @Author 邋遢龘鵺
     * @DateTime 2020/3/28 14:30
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
     * @Titel 字符串数组转Integer类集合
     * @Description 字符串数组转Integer类集合
     * @Author 邋遢龘鵺
     * @DateTime 2020/3/28 14:30
     */
    public static List<Integer> stringToIntegerList(String[] value) {
        List<Integer> idList = new ArrayList<Integer>();
        for (String str : value) {
            idList.add(Integer.parseInt(str));
        }
        return idList;
    }


}
