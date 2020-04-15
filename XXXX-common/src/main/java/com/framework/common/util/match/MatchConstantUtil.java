package com.framework.common.util.match;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.common.util.match
 * @Description 匹配变量工具类
 * @Date 2020/3/10 18:42
 * @Version 1.0
 */
public class MatchConstantUtil {
    //银行-定时任务规则
    public static final String REGEX_TIMETASK = "^((((([1-9]{1}\\d*)|([0]{1}))(\\.(\\d{1,2})))|(\\d*))((,(((([1-9]{1}\\d*)|([0]{1}))(\\.(\\d{1,2})))|(\\d*)))*))$";
    //商户-费率进行校验
    public static final String REGEX_FEERATE = "^([0-1])(\\.(\\d){0,5})?$";
    //商户-费率进行校验
    public static final String REGEX_WHITELIST_IP = "^((25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)((,(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d))*))$";

}
