package com.framework.common.util.sql;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.util.sql
 * @description sql注入过滤工具类
 * @date 2020/1/9 15:59
 */
public class InjectSQLFilterUtil {
    //过滤值
    private final static String sqlKey = "drop|select|declare|information_schema.columns|use|insert|update|mid|delete|truncate|and|by|sitename|create|from|where|xp_cmdshell|table|order|--|//|or|#|%|like|'|count|column_name|+|union|chr|net user|,|execute|-|master|/|group_concat|char|table_schema|;|grant|exec|";
    //过滤HashSet集合
    public final static HashSet<String> set = new HashSet<String>(Arrays.asList(sqlKey.split("\\|")));
    //替换字符串
    public final static String REPLACE_SQL_CODE = "invalid";

}
