package com.framework.common.util.io;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.common.util.io
 * @Description 读取文件流，字符串和文件流互转
 * @Date 2020/1/8 10:35
 * @Version 1.0
 */
public class ReadIOUtil {

    /**
     * @param is 1 输入流
     * @return java.lang.String
     * @Titel 文件流转换成字符串
     * @Description 文件流转换成字符串
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/8
     */
    public static String readIoToString(InputStream is) {
        String result = null;
        byte[] data = null;
        try {
            data = new byte[is.available()];
            is.read(data);
            BASE64Encoder encoder = new BASE64Encoder();
            result = encoder.encode(data);
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
     * @Titel IO流字符串转OU流输出
     * @Description IO流字符串转OU流输出
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/8
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
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes = decoder.decodeBuffer(ioString);
            fos = response.getOutputStream();
            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));// 设定输出文件头
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.addHeader("Pargam", "no-cache");
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
