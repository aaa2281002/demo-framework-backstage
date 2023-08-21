package com.framework.common.util.match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.util.match
 * @description 匹配工具类
 * @date 2020/3/10 18:41
 */
public class MatchUtil {

    /**
     * @param regex 1 正则表达式
     * @param str   2 匹配值
     * @return boolean, 成功true，false失败
     * @titel 根据正则表达式和值匹配是否正确
     * @description 根据正则表达式和值匹配是否正确
     * @author 邋遢龘鵺
     * @datetime 2019/9/11 9:48
     */
    public static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

}
