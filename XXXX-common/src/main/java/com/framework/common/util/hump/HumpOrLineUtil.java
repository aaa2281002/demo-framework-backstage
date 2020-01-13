package com.framework.common.util.hump;

import com.framework.common.util.other.SymbolUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.common.util.hump
 * @Description 驼峰和下划线互转工具类
 * @DateTime 2019/12/10 17:37
 * @Version 1.0
 */
public class HumpOrLineUtil {
    private static Pattern humpPattern = Pattern.compile("[A-Z]");
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * @param str 1 字符串
     * @return java.lang.String
     * @Titel 驼峰转下划线
     * @Description 驼峰转下划线
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/10 17:39
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, SymbolUtil.NO_INPUT_METHOD_UNDERLINE + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * @param str 1 字符串
     * @return java.lang.String
     * @Titel 下划线转驼峰
     * @Description 下划线转驼峰
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/10 17:39
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
