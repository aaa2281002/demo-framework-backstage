package com.framework.common.util.date;

import com.framework.common.util.other.NumeralUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.util.date
 * @description 日期时间工具类
 * @date 2020/1/8
 */
public class DateUtil {

    /**
     * @return java.lang.String
     * @titel 生成唯一序列代码所属的键参数字符串
     * @description 生成唯一序列代码所属的键参数字符串
     * @author 邋遢龘鵺
     * @datetime 2020/3/10 11:45
     */
    public static String getDateSeqString() {
        SimpleDateFormat fm = new SimpleDateFormat(DateStyleUtil.FORMAT_YYYYMMDDHHMMSSSSS); // "yyyyMMdd G
        return fm.format(new Date());
    }

    /**
     * @param date 1 日期字符串
     * @return java.util.Date
     * @titel 字符串转日期时间
     * @description 字符串转日期时间
     * @author 邋遢龘鵺
     * @datetime 2020/1/8
     */
    public static Date getStringToDate(String date) {
        return getStringToDate(date, DateStyleUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * @param date   1 日期字符串
     * @param format 2 转换日期格式
     * @return java.util.Date
     * @titel 字符串转日期时间
     * @description 字符串转日期时间
     * @author 邋遢龘鵺
     * @datetime 2020/1/8
     */
    public static Date getStringToDate(String date, String format) {
        if ((format == null) || format.equals("")) {
            format = DateStyleUtil.FORMAT_YYYY_MM_DD_HH_MM_SS;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null) {
            try {
                return sdf.parse(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @param date 1 日期字符串
     * @return java.util.Date
     * @titel 导出专用字符串转日期时间
     * @description 导出专用字符串转日期时间
     * @author 邋遢龘鵺
     * @datetime 2020/1/8
     */
    public static Date getExcelStringToDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DateStyleUtil.STRING_EEE_MMM_DD_HH_MM_SS_ZZZ_YYYY, Locale.US);
        if (date != null) {
            try {
                return sdf.parse(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

//    public static void main(String[] args) {
//        Date date = getExcelStringToDate("Thu Feb 20 15:31:07 CST 2020");
//        Date date2 = new Date("Thu Feb 20 15:31:07 CST 2020");
//        System.out.println(getDateToString(date, DateStyleUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
//        System.out.println(getDateToString(date2, DateStyleUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
//    }

    /**
     * @param date   1 日期
     * @param format 2 转换字符串
     * @return java.lang.String
     * @titel 日期转换成字符串
     * @description 日期转换成字符串
     * @author 邋遢龘鵺
     * @datetime 2020/1/8
     */
    public static String getDateToString(Date date, String format) {
        if ((format == null) || format.equals("")) {
            format = DateStyleUtil.FORMAT_YYYY_MM_DD_HH_MM_SS;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null) {
            return sdf.format(date);
        }
        return "";
    }


    /**
     * @param date   1 日期
     * @param minute 2 减少分钟数值
     * @return java.util.Date
     * @titel 把给定的时间减掉给定的分钟数
     * @description 把给定的时间减掉给定的分钟数
     * @author 邋遢龘鵺
     * @datetime 2020/1/8
     */
    public static Date minusDateByMinute(Date date, int minute) {
        Date newDate = new Date(date.getTime() - (minute * NumeralUtil.POSITIVE_SIXTY * NumeralUtil.POSITIVE_ONE_THOUSAND));
        return newDate;
    }

    /**
     * @param startDate 1 开始日期时间
     * @param endDate   2 结束日期时间
     * @return int
     * @titel 计算2个日期时间相差几天
     * @description 计算2个日期时间相差几天
     * @author 邋遢龘鵺
     * @datetime 2020/1/8
     */
    public static int getDays(Date startDate, Date endDate) {
        int num = getHour(startDate, endDate);
        return num / NumeralUtil.POSITIVE_TWENTY_FOUR;
    }

    /**
     * @param startDate 1 开始日期时间
     * @param endDate   2 结束日期时间
     * @return int
     * @titel 计算2个日期时间相差几小时
     * @description 计算2个日期时间相差几小时
     * @author 邋遢龘鵺
     * @datetime 2020/1/8
     */
    public static int getHour(Date startDate, Date endDate) {
        int num = getMinute(startDate, endDate);
        return num / NumeralUtil.POSITIVE_SIXTY;
    }

    /**
     * @param date 1 日期
     * @param num  2 数字
     * @return java.util.Date
     * @titel 根据当前日期操作天数，正数为加，负数为减
     * @description 根据当前日期操作天数，正数为加，负数为减
     * @author 邋遢龘鵺
     * @datetime 2020/1/8
     */
    public static Date getDays(Date date, int num) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.DAY_OF_MONTH, num);
        return rightNow.getTime();
    }

    /**
     * @param date 1 日期
     * @param num  2 数字
     * @return java.util.Date
     * @titel 根据当前日期操作月份，正数为加，负数为减
     * @description 根据当前日期操作月份，正数为加，负数为减
     * @author 邋遢龘鵺
     * @datetime 2020/1/8
     */
    public static Date getMonth(Date date, int num) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.MONTH, num);
        return rightNow.getTime();
    }

    /**
     * @param startDate 1 开始日期时间
     * @param endDate   2 结束日期时间
     * @return int
     * @titel 计算2个日期时间相差几分钟
     * @description 计算2个日期时间相差几分钟
     * @author 邋遢龘鵺
     * @datetime 2020/1/8 11:21
     */
    public static int getMinute(Date startDate, Date endDate) {
        Long between = (endDate.getTime() - startDate.getTime()) / NumeralUtil.POSITIVE_SIXTY_THOUSAND;
        return between != null ? between.intValue() : NumeralUtil.POSITIVE_ZERO;
    }

    /**
     * @param startDate 1 开始日期时间
     * @param endDate   2 结束日期时间
     * @return int
     * @titel 计算2个日期时间相差几秒
     * @description
     * @author 邋遢龘鵺
     * @datetime 2020/1/8 11:22
     */
    public static int getSecond(Date startDate, Date endDate) {
        Long between = (endDate.getTime() - startDate.getTime()) / NumeralUtil.POSITIVE_ONE_THOUSAND;
        return between != null ? between.intValue() : NumeralUtil.POSITIVE_ZERO;
    }
}
