package com.framework.common.util.filter;

import com.framework.common.util.other.NumeralUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.util.filter
 * @description 参数过滤工具类
 * @date 2021/12/13 12:56
 */
public class XssUtil {

    public final static String DESCRIPTION = "description";
    public final static List<String> list = new ArrayList<>(NumeralUtil.POSITIVE_TWO);

    static {
        list.add("sort");
        list.add("order");
    }

}
