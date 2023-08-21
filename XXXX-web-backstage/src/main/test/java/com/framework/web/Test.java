package com.framework.web;

import com.framework.common.util.encrypt.MD5Util;
import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web
 * @description 测试工具类
 * @datetime 2019/10/11
 */
public class Test {
    public static void main(String[] args) {
//        StringBuilder token = new StringBuilder();
//        //加token:
//        token.append("token:");
//        //加加密的用户名
//        token.append(DigestUtils.md5Hex("1") + "-");
//        //加时间
//        token.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "-");
//        //加六位随机数111111-999999
//        token.append(new Random().nextInt((999999 - 111111 + 1)) + 111111);
//        System.out.println("token=>" + token.toString());
//        BigDecimal bd = new BigDecimal("1");
//        BigDecimal bd2 = new BigDecimal("1");
//        System.out.println(bd.equals(bd2));
//
//        System.out.println(bd.compareTo(bd2));
//        String str = "/findaaaaa";
//        System.out.println(str.lastIndexOf("find"));
//        System.out.println(Integer.MAX_VALUE);
//        List<String> list = new ArrayList<String>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        list.add("4");
//        boolean is = list.remove("2");
//        boolean is2 = list.remove("5");
//        System.out.println(is);
//        System.out.println(is2);
//        list.add("22");
//        System.out.println(list.toString());

//        System.out.println(MD5Util.MD5EncodeToPassword("123456"));

        String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|%|chr|mid|master|truncate|" +
                "char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like|and|exec|execute|insert|create|drop|" +
                "table|from|grant|use|group_concat|column_name|" +
                "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|" +
                "chr|mid|master|truncate|char|declare|or|;|-|--|,|like|//|/|%|#";
        String[] badStrs = badStr.split("\\|");
        HashSet<String> set = new HashSet<String>(Arrays.asList(badStrs));
        StringBuilder sb = new StringBuilder();
        for (String str : set) {
            sb.append(str).append("|");
        }
        String a = "drop|select|declare|information_schema.columns|use|insert|update|mid|delete|truncate|and|by|sitename|create|from|where|xp_cmdshell|table|order|--|//|or|#|%|like|'|count|column_name|+|union|chr|net user|,|execute|-|master|/|group_concat|char|table_schema|;|grant|exec|";
        System.out.println(badStrs.length);
        System.out.println(set.size());
        System.out.println(a.split("\\|").length);
        System.out.println(sb);

    }
}
