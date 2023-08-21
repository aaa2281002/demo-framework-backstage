package com.framework.common.util;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.other.CodingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/**
 * @Author 龘鵺
 * @ClassName com.framework.common.util
 * @Description 返回公用类
 * @Date 2022/3/2 16:11
 * @Version 1.0
 */

public class ResponseUtil {
    private static Logger log = LoggerFactory.getLogger(ResponseUtil.class);

    public static void out(HttpServletResponse response, ResponseResult responseResult) {
        try {
            response.setCharacterEncoding(CodingUtil.CODING_UTF8);
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getOutputStream().write(responseResult.toJsonString().getBytes(StandardCharsets.UTF_8));
//            response.getOutputStream().flush();
//            response.getOutputStream().close();
//            response.getWriter().flush();
//            response.getWriter().close();
        } catch (Exception e) {
            log.error(e + "，输出JSON出错");
        }
    }
}
