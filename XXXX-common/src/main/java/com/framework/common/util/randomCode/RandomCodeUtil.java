package com.framework.common.util.randomCode;

import com.framework.common.util.other.NumeralUtil;

import java.util.Random;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.common.util.randomCode
 * @description 随机码生成工具类
 * @date 2022/3/8 10:30
 */
public class RandomCodeUtil {

    /**
     * @param length 1 长度值,最低为1
     * @return java.lang.String
     * @titel 根据传递的值生成对应的数字字符串码
     * @description 根据传递的值生成对应的数字字符串码
     * @author 龘鵺
     * @datetime 2022/3/8 10:34
     */
    public static String getRandomCodeToValue(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = NumeralUtil.POSITIVE_ZERO; i < length; i++) {
            sb.append(random.nextInt(NumeralUtil.POSITIVE_TEN));
        }
        return sb.toString();
    }

    /**
     * @param length 1 长度值,最低为1
     * @return java.lang.String
     * @titel 根据长度随机生成数字+字母的长度字符串
     * @description 根据长度随机生成数字+字母的长度字符串
     * @author 龘鵺
     * @datetime 2022/3/8 13:41
     */
    public static String getStringRandomCodeToValue(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        //参数length，表示生成几位随机数
        for (int i = NumeralUtil.POSITIVE_ZERO; i < length; i++) {
            String charOrNum = random.nextInt(NumeralUtil.POSITIVE_TWO) % NumeralUtil.POSITIVE_TWO == NumeralUtil.POSITIVE_ZERO ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(NumeralUtil.POSITIVE_TWO) % NumeralUtil.POSITIVE_TWO == NumeralUtil.POSITIVE_ZERO ? NumeralUtil.POSITIVE_SIXTY_FIVE : NumeralUtil.POSITIVE_NINETY_SEVEN;
                sb.append((char) (random.nextInt(NumeralUtil.POSITIVE_TWENTY_SIX) + temp));
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                sb.append(random.nextInt(NumeralUtil.POSITIVE_TEN));
            }
        }
        return sb.toString();
    }
}
