package com.framework.common.util.match;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.util.match
 * @description 匹配变量工具类
 * @date 2020/3/10 18:42
 */
public class MatchConstantUtil {
    //银行-定时任务规则
    public static final String REGEX_TIMETASK = "^((((([1-9]{1}\\d*)|([0]{1}))(\\.(\\d{1,2})))|(\\d*))((,(((([1-9]{1}\\d*)|([0]{1}))(\\.(\\d{1,2})))|(\\d*)))*))$";
    //商户-费率进行校验
    public static final String REGEX_FEERATE = "^([0-1])(\\.(\\d){0,5})?$";
    //商户-费率进行校验
    public static final String REGEX_WHITELIST_IP = "^((25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)((,(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d))*))$";
    //手机号码效验
    public static final String REGEX_PHONE = "^((\\+86|0086)?\\s*)((134[0-8]\\d{7})|(((13([0-3]|[5-9]))|(14[5-9])|15([0-3]|[5-9])|(16(2|[5-7]))|17([0-3]|[5-8])|18[0-9]|19(1|[8-9]))\\d{8})|(14(0|1|4)0\\d{7})|(1740([0-5]|[6-9]|[10-12])\\d{7}))$";
    //    public static final String REGEX_PHONE = "^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$";
    //IP验证
    public static final String REGEX_IP = "^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$";
    //邮箱效验
    public static final String REGEX_EMAIL = "^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$";
    //年月效验
//    public static final String REGEX_YYYY_MM = "^([1-9]\\d{3}-)(([0]{0,1}[1-9])|([1][0-2]))*$";
    public static final String REGEX_YYYY_MM = "^([1-9]\\d{3}-)(([0]{0,1}[1-9])|([1][0-2]))$";
    //验证字母数字
    public static final String REGEX_EN = "^[a-zA-Z0-9]+$";
    //验证字母
    public static final String REGEX_ENGLISH = "^[a-zA-Z]+$";
    //验证数字
    public static final String REGEX_NUMBER = "^[0-9]+$";
    //判断是否为纯中文，不是返回false
    public static final String REGEX_REGE = "^[\\u4e00-\\u9fa5]+$";
    //判断是否为url
    public static final String REGEX_URL = "^(http(s)?:\\/\\/){1}(www\\.)?([0-9a-z-]{1,}.)?[0-9a-z-]{2,}.([0-9a-z-]{2,}.)?[a-z]{2,}(\\/)?$";
    //判断是否为域名后缀URL
    public static final String REGEX_URL_SUFFIX = "^(\\/{1}[a-z]+)+$";


    //替换手机号码中间4位正则
    public static final String HIDE_PHONE_KEY = "(\\d{3})\\d{4}(\\d{4})";
    //替换手机号码中间4位值
    public static final String HIDE_PHONE_VALUE = "$1****$2";
}
