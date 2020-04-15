package com.framework.common.util.other;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.lybank.common.util
 * @Description 生成金额尾数工具类
 * @Date 2019/8/31 10:48
 * @Version 1.0
 */
public class GenerateMantissaUtil {
    /**
     * @return java.lang.String
     * @Titel 生成随机小数点，保留1-2位
     * @Description 生成随机小数点，保留1-2位
     * @Author 邋遢龘鵺
     * @DateTime 2019/8/31 10:50
     */
    public static String GenerateOneMantissaToString() {
        DecimalFormat dcmFmt = new DecimalFormat("0.00");
        Random rand = new Random();
        float f = rand.nextFloat() * NumeralUtil.POSITIVE_ONE;
        if (f >= NumeralUtil.POSITIVE_ONE) {
            f -= 0.9;
        }
        return dcmFmt.format(f);
    }

    /**
     * @return java.lang.String
     * @Titel 生成随机数，保留7位
     * @Description 生成随机数，保留7位
     * @Author 邋遢龘鵺
     * @DateTime 2019/8/31 10:50
     */
    public static String GenerateFiveMantissaToString() {
        int five = (int) ((Math.random() * NumeralUtil.POSITIVE_NINE + NumeralUtil.POSITIVE_ONE) * NumeralUtil.POSITIVE_ONE_MILLION);
//        System.out.println(five);
        return String.valueOf(five);
    }

    /**
     * @return java.lang.Double
     * @Titel 生成随机小数点，保留1-2位
     * @Description 生成随机小数点，保留1-2位
     * @Author 邋遢龘鵺
     * @DateTime 2019/8/31 10:50
     */
    public static double GenerateOneMantissaToDouble() {
        return Double.parseDouble(GenerateOneMantissaToString());
    }

    /**
     * ROUND_HALF_UP表示四舍五入
     */
    public static BigDecimal generateTwoMantissaDouble() {
        double d = Double.parseDouble(GenerateOneMantissaToString());
        BigDecimal b = new BigDecimal(d);
        return b.setScale(NumeralUtil.POSITIVE_TWO, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * double 进行加减乘除会有精度丢失，金钱一般使用BigDecimal
     * 加法 add()函数
     * 减法subtract()函数
     * 乘法multipy()函数
     * 除法divide()函数
     * 绝对值abs()函数
     * 使用BigDecimal(double val)构造函数时仍会存在精度丢失问题，建议使用BigDecimal(String val)。
     * 这就需要先把double转换为字符串然后在作为BigDecimal(String val)构造函数的参数
     *
     * @param args
     */
    public static void main(String[] args) {
        BigDecimal one = new BigDecimal("1");
        for (int i = 0; i < 1000; i++) {
            BigDecimal v = generateTwoMantissaDouble();
            //double v = GenerateOneMantissaToDouble();
            //System.out.println(v);
            System.out.println(one.subtract(v));
        }
    }
}
