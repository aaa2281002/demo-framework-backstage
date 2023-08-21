package com.framework.common.util.other;

import java.util.UUID;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.util.other
 * @description UUID工具类
 * @datetime 2019/10/11
 */
public class UUIDUtil {
    /**
     * @return java.lang.String
     * @titel 生成32位字符串
     * @description 生成32位字符串
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll(SymbolUtil.NO_INPUT_METHOD_MINUS_SIGN, "");
    }
}
