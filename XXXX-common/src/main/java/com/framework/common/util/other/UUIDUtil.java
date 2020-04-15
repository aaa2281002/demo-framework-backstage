package com.framework.common.util.other;

import java.util.UUID;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.common.util.other
 * @Description UUID工具类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public class UUIDUtil {
    /**
     * @return java.lang.String
     * @Titel 生成32位字符串
     * @Description 生成32位字符串
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll(SymbolUtil.NO_INPUT_METHOD_MINUS_SIGN, "");
    }
}
