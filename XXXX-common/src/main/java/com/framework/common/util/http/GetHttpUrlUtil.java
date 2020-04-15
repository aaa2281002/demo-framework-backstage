package com.framework.common.util.http;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.common.util.http
 * @Description 获取请求url工具类
 * @Date 2020/4/2 17:16
 * @Version 1.0
 */
public class GetHttpUrlUtil {

    /**
     * @param request 1  请求对象
     * @return java.lang.String
     * @Titel 根据HttpServletRequest获取请求URL
     * @Description 根据HttpServletRequest获取请求URL
     * @Author 邋遢龘鵺
     * @DateTime 2020/4/2 17:17
     */
    public static String getHttpUrl(HttpServletRequest request) {

        if (request == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(request.getScheme());
        sb.append("://");
        sb.append(request.getServerName());
        sb.append(":");
        sb.append(request.getServerPort());
//        sb.append(request.getContextPath());
        sb.append("/");

//        String domain = request.getScheme()
//                + "://" + request.getServerName()
//                + ":" + request.getServerPort()
//                + request.getContextPath()
//                + "/";
        return sb.toString();
    }
}
