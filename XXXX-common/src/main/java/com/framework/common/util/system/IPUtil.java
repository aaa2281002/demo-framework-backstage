package com.framework.common.util.system;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.common.util.system
 * @Description IP获取工具类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public class IPUtil {
    /**
     * @param request 1 javax.servlet.http.HttpServletRequest
     * @return java.lang.String 不存在返回空值或null
     * @Titel 获取IP地址
     * @Description 获取IP地址
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    public static String getIpAddr(HttpServletRequest request) {

        if (request == null) return "";
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1") || ipAddress.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ipAddress = "";
        }
        if ("".equals(ipAddress.toString())) ipAddress = request.getRemoteAddr();

        return ipAddress;
    }
}
