package com.framework.common.util.system;

import com.framework.common.util.date.DateStyleUtil;
import com.framework.common.util.other.UUIDUtil;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.Random;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.util.system
 * @description token生成工具类
 * @datetime 2019/10/11
 */
public class TokenUtil {

    /**
     * @return java.lang.String token字符串
     * @titel 生成token字符串
     * @description 生成token字符串
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public static String generateToken() {
        StringBuilder token = new StringBuilder();
        //加加密的用户名
        token.append(UUIDUtil.getUUID());
        //加时间
        token.append(DateFormatUtils.format(new Date(), DateStyleUtil.FORMAT_YYYYMMDDHHMMSSSSS));
        //加六位随机数111111-999999
        int num = new Random().nextInt((999999 - 111111 + 1)) + 111111;
        token.append(num);
        return token.toString();
    }
}
