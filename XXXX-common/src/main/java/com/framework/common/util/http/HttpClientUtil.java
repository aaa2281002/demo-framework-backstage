package com.framework.common.util.http;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.util.http
 * @description 请求工具类
 * @date 2020/3/29 15:18
 */
public class HttpClientUtil {
    private static SSLContextBuilder builder = null;
    private static final String HTTP = "http";
    private static final String HTTPS = "https";
    private static SSLConnectionSocketFactory sslsf = null;
    private static PoolingHttpClientConnectionManager cm = null;
    private static RequestConfig requestConfig = null;

    static {
        try {
            builder = new SSLContextBuilder();
            // 全部信任 不做身份鉴定
            builder.loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            });
            sslsf = new SSLConnectionSocketFactory(builder.build(), new String[]{"SSLv2Hello", "SSLv3", "TLSv1"}, null, NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create().register(HTTP, new PlainConnectionSocketFactory()).register(HTTPS, sslsf).build();
            cm = new PoolingHttpClientConnectionManager(registry);
            cm.setMaxTotal(2000);// max connection
            if (requestConfig == null) {
                // 用于内部环境使用到代理。不然无法发送和接受返回请求
                // HttpHost proxy = new HttpHost("127.0.0.1", 8888);
                // requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(3000).setSocketTimeout(5000).setProxy(proxy).setRedirectsEnabled(false).build();
                //毫秒单位1000毫秒=1秒
                //connectionRequestTimeout 是获取连接池连接的超时时间
                //connectTimeout 是建立连接的超时时间，
                //socketTimeout 是等待服务器响应的超时时间
                requestConfig = RequestConfig.custom().setConnectTimeout(60000).setConnectionRequestTimeout(50000).setSocketTimeout(60000).setRedirectsEnabled(false).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ;

    /**
     * @param url 1 请求地址
     * @return java.lang.String
     * @titel String格式字符串get请求
     * @description String格式字符串get请求
     * @author 邋遢龘鵺
     * @datetime 2020/3/29 15:36
     */
    public static String getString(String url) throws Exception {
        return getString(url, null, null);
    }

    /**
     * @param url    1 请求地址
     * @param header 2 头部信息Map集合
     * @return java.lang.String
     * @titel String格式字符串get请求
     * @description String格式字符串get请求
     * @author 邋遢龘鵺
     * @datetime 2020/3/29 15:36
     */
    public static String getString(String url, Map<String, Object> header) throws Exception {
        return getString(url, header, null);
    }

    /**
     * @param parameter 1 参数map集合
     * @param url       2 请求地址
     * @return java.lang.String
     * @titel String格式字符串get请求
     * @description String格式字符串get请求
     * @author 邋遢龘鵺
     * @datetime 2020/3/29 15:37
     */
    public static String getString(Map<String, Object> parameter, String url) throws Exception {
        return getString(url, null, parameter);
    }

    /**
     * @param url       1 请求地址
     * @param header    2 头部信息
     * @param parameter 3 地址栏后跟随参数
     * @return java.lang.String
     * @titel String格式字符串get请求
     * @description String格式字符串get请求
     * @author 邋遢龘鵺
     * @datetime 2020/3/29 15:37
     */
    public static String getString(String url, Map<String, Object> header, Map<String, Object> parameter) throws Exception {
        // System.out.println(logPrefix + " post=url:" + url);
        HttpGet httpGet = new HttpGet();
        httpGet.setConfig(requestConfig);
        // 判断请求得抬头集合不为空，循环加入抬头
        httpGet.setHeader("Content-Type", "application/json");
        httpGet.setHeader("Content-Encoding", "utf-8");
        httpGet.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        if (header != null) {
            for (Map.Entry<String, Object> entry : header.entrySet()) {
                httpGet.setHeader(entry.getKey(), entry.getValue() + "");
            }
        }
        if (parameter != null) {
            StringBuilder sb = new StringBuilder("?");
            for (Map.Entry<String, Object> entry : parameter.entrySet()) {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
                sb.append("&");
            }
            url += sb.substring(0, sb.lastIndexOf("&")).toString();
        }

        String str = null;
        try {
            httpGet.setURI(new URI(url));
            str = sendEntity(httpGet);
        } catch (Exception e) {
            throw e;
        }
        return str;
    }

    /**
     * @param jsonObjectStr 1 json字符串
     * @param url           2 请求地址
     * @return java.lang.String
     * @titel String格式JSON字符串post请求
     * @description String格式JSON字符串post请求
     * @author 邋遢龘鵺
     * @datetime 2020/3/29 15:38
     */
    public static String postString(String jsonObjectStr, String url) throws Exception {
        return postString(jsonObjectStr, url, null, null);
    }

    /**
     * @param header        1 头部信息map
     * @param jsonObjectStr 2 json字符串
     * @param url           3 请求地址
     * @return java.lang.String
     * @titel String格式JSON字符串post请求
     * @description String格式JSON字符串post请求
     * @author 邋遢龘鵺
     * @datetime 2020/3/29 15:38
     */
    public static String postString(Map<String, Object> header, String jsonObjectStr, String url) throws Exception {
        return postString(jsonObjectStr, url, header, null);
    }

    /**
     * @param url          1 请求地址
     * @param parameterMap 2 请求参数map
     * @return java.lang.String
     * @titel parameterMap格式post请求
     * @description
     * @author 邋遢龘鵺
     * @datetime 2020/3/29 15:41
     */
    public static String postString(String url, Map<String, Object> parameterMap) throws Exception {
        return postString(null, url, null, parameterMap);
    }

    /**
     * @param url          1 请求地址
     * @param header       2 header信息map
     * @param parameterMap 3 请求参数map
     * @return java.lang.String
     * @titel parameterMap格式post请求
     * @description parameterMap格式post请求
     * @author 邋遢龘鵺
     * @datetime 2020/3/29 15:43
     */
    public static String postString(String url, Map<String, Object> header, Map<String, Object> parameterMap) throws Exception {
        return postString(null, url, header, parameterMap);
    }


    /**
     * @param jsonObjectStr 1 json字符串
     * @param url           2 请求地址
     * @param header        3 头部参数map集合
     * @param parameterMap  4 请求参数map
     * @return java.lang.String
     * @titel String格式JSON字符串post请求
     * @description String格式JSON字符串post请求
     * @author 邋遢龘鵺
     * @datetime 2020/3/29 15:44
     */
    public static String postString(String jsonObjectStr, String url, Map<String, Object> header, Map<String, Object> parameterMap) throws Exception {
        HttpPost httpPost = new HttpPost();
        httpPost.setConfig(requestConfig);
        // 判断JSON字符串不为空
        if (StringUtils.isNotEmpty(jsonObjectStr)) {
            try {
                StringEntity se = new StringEntity(jsonObjectStr);
                // System.out.println(jsonObjectStr);
//				 se.setContentType("application/json");
                se.setContentType("text/xml");
                httpPost.setEntity(se);
            } catch (Exception e) {
                throw e;
            }
        }
        // 装填参数
        if (parameterMap != null) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue() + ""));
            }
            // 设置参数到请求对象中
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            } catch (Exception e) {
                throw e;
            }
        }

        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Content-Encoding", "utf-8");
        httpPost.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        // 判断请求得抬头集合不为空，循环加入抬头
        if (header != null) {
            for (Map.Entry<String, Object> entry : header.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue() + "");
            }
        }
        String str = null;
        try {
            httpPost.setURI(new URI(url));
            str = sendEntity(httpPost);
        } catch (Exception e) {
            throw e;
        }
        return str;
    }

    /**
     * @param httpRequestBase 1 请求封装对象
     * @return java.lang.String
     * @throws Exception
     * @titel http请求方法
     * @description http请求方法
     * @author 邋遢龘鵺
     * @datetime 2020/3/29 15:46
     */
    private static String sendEntity(HttpRequestBase httpRequestBase) throws Exception {
        CloseableHttpClient client = getHttpClient();
        CloseableHttpResponse httpResponse = null;
        String resp = null;
        try {
            httpResponse = client.execute(httpRequestBase);
            resp = EntityUtils.toString(httpResponse.getEntity(), Charset.forName("UTF-8"));
        } catch (Exception e) {
            throw e;
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (Exception e) {
                    throw e;
                }
            }
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (Exception e) {
                    throw e;
                }
            }

        }
        return resp;
    }

    /**
     * @return org.apache.http.impl.client.CloseableHttpClient
     * @throws Exception
     * @titel 创建请求参数对象
     * @description 创建请求参数对象
     * @author 邋遢龘鵺
     * @datetime 2020/3/29 15:48
     */
    public static CloseableHttpClient getHttpClient() throws Exception {
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManager(cm).setConnectionManagerShared(true).build();
        return httpClient;
    }


    /**
     * @param cookie 1  密匙
     * @return org.apache.http.impl.client.CloseableHttpClient
     * @titel 创建请求参数对象，给请求设置cookie参数
     * @description 创建请求参数对象，给请求设置cookie参数
     * @author 邋遢龘鵺
     * @datetime 2020/3/29 15:49
     */
    public static CloseableHttpClient getHttpClient(Cookie cookie) throws Exception {
        CookieStore cookieStore = new BasicCookieStore();
        if (cookie != null) {
            cookieStore.addCookie(cookie);
        }
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManager(cm).setConnectionManagerShared(true).setDefaultCookieStore(cookieStore).build();
        return httpClient;
    }
}
