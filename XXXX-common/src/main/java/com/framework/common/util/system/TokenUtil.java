package com.framework.common.util.system;

import com.framework.common.util.date.DateStyleUtil;
import com.framework.common.util.other.UUIDUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import java.util.Date;
import java.util.Random;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.common.util.system
 * @Description token生成工具类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public class TokenUtil {

    /**
     * @Titel 生成token字符串
     * @Description 生成token字符串
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     * @return java.lang.String token字符串
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
