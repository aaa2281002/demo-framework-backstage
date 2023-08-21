package com.framework.common.util.io;

import org.apache.commons.lang3.StringUtils;
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Base64;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.util.io
 * @description 读取文件流，字符串和文件流互转
 * @date 2020/1/8 10:35
 */
public class ReadIOUtil {

    /**
     * @param is 1 输入流
     * @return java.lang.String
     * @titel 文件流转换成字符串
     * @description 文件流转换成字符串
     * @author 邋遢龘鵺
     * @datetime 2020/1/8
     */
    public static String readIoToString(InputStream is) {
        String result = null;
        byte[] data = null;
        try {
            data = new byte[is.available()];
            is.read(data);

            Base64.Encoder encoder = Base64.getEncoder();
//            BASE64Encoder encoder = new BASE64Encoder();
            result = encoder.encodeToString(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (data != null) {
                data = null;
            }
        }
        return result;
    }

    /**
     * @param response 1 请求响应对象
     * @param ioString 2 流字符串
     * @param filePath 3 存储路径，可以为空。
     * @param fileName 4 文件名，包含了文件后缀类型
     * @titel IO流字符串转OU流输出
     * @description IO流字符串转OU流输出
     * @author 邋遢龘鵺
     * @datetime 2020/1/8
     */
    public static void readIoStringToFile(HttpServletResponse response, String ioString, String filePath, String fileName) {
        OutputStream fos = null;
        if (StringUtils.isNotEmpty(filePath)) {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
        }
        try {
            Base64.Decoder decoder = Base64.getDecoder();
//            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes = decoder.decode(ioString);
            fos = response.getOutputStream();
            response.reset();// 清空输出流
//        //在线打开方式 文件名应该编码成UTF-8
//        response.setHeader("Content-Disposition", "inline; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            //纯下载方式 文件名应该编码成UTF-8
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));// 设定输出文件头
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.addHeader("pragma", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            fos.write(bytes);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
