package com.framework.common.util.upload;

import com.framework.common.util.other.NumeralUtil;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.common.util.upload
 * @description 图片工具类
 * @date 2022/9/22 10:11
 */
public class ImageUtil {
    //所有字母必须小鞋。 不然会出问题。
    private static final String[] imageSuffixs = new String[]{"png", "jpg", "jpeg", "gif", "swf"};
    private static final String[] imageSuffixArrys = new String[]{".png", ".jpg", ".jpeg", ".gif", ".swf"};
    public static final String imageSuffix = "png, jpg, jpeg, gif, swf";

    /**
     * @param suffix 1 上传格式字符串，必须传递小写字母 toLowerCase
     * @return boolean
     * @titel 判断图片格式
     * @description 判断图片格式
     * @author 龘鵺
     * @datetime 2022/9/22 10:16
     */
    public static boolean matchImage(String suffix) {
        for (String str : imageSuffixs) {
            if (str.equals(suffix)) {
                return true;
            }
        }
        return true;
    }

    /**
     * @param fileName 1 长传文件名，必须传递小写字母 toLowerCase
     * @return boolean
     * @titel 判断图片格式
     * @description 判断图片格式
     * @author 龘鵺
     * @datetime 2022/9/22 10:16
     */
    public static boolean matchImageFileName(String fileName) {
        for (String str : imageSuffixArrys) {
            if (fileName.lastIndexOf(str) > NumeralUtil.NEGATIVE_ONE) {
                return true;
            }
        }
        return true;
    }


}
