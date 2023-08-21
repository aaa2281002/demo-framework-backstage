package com.framework.common.util.http;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.util.http
 * @description 获取请求url工具类
 * @date 2020/4/2 17:16
 */
public class GetHttpUrlUtil {

    /**
     * @param request 1  请求对象
     * @return java.lang.String
     * @titel 根据HttpServletRequest获取请求URL
     * @description 根据HttpServletRequest获取请求URL
     * @author 邋遢龘鵺
     * @datetime 2020/4/2 17:17
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
