package com.framework.common.util.match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.common.util.match
 * @Description 匹配工具类
 * @Date 2020/3/10 18:41
 * @Version 1.0
 */
public class MatchUtil {

    /**
     * @param regex 1 正则表达式
     * @param str   2 匹配值
     * @return boolean, 成功true，false失败
     * @Titel 根据正则表达式和值匹配是否正确
     * @Description 根据正则表达式和值匹配是否正确
     * @Author 邋遢龘鵺
     * @DateTime 2019/9/11 9:48
     */
    public static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

}
