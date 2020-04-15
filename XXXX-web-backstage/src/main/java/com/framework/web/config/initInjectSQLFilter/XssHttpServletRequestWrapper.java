package com.framework.web.config.initInjectSQLFilter;

import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.sql.InjectSQLFilterUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.config.initInjectSQLFilter
 * @Description 请求参数过滤类，预防sql注入
 * @Date 2020/1/9 15:44
 * @Version 1.0
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private Logger log = LoggerFactory.getLogger(XssHttpServletRequestWrapper.class);
    //当前请求网址
    private String currentUrl;

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.currentUrl = request.getRequestURI();
    }

    /**
     * @return java.util.Map
     * @Titel 整个请求参数值过滤
     * @Description 整个请求参数值过滤
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/9 17:13
     */
    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> values = super.getParameterMap();
        if (values == null) {
            return values;
        }
        Map<String, String[]> result = new HashMap<>();
        for (String key : values.keySet()) {
            String encodedKey = cleanXSS(key);
            int count = values.get(key).length;
            String[] encodedValues = new String[count];
            for (int i = NumeralUtil.POSITIVE_ZERO; i < count; i++) {
                encodedValues[i] = cleanXSS(values.get(key)[i]);
            }
            result.put(encodedKey, encodedValues);
        }
        return result;
    }

    /**
     * @param name 1 键
     * @return java.lang.String[]
     * @Titel 批量参数值获取过滤
     * @Description 批量参数值获取过滤
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/9 17:12
     */
    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = NumeralUtil.POSITIVE_ZERO; i < count; i++) {
            encodedValues[i] = cleanXSS(values[i]);
        }
        return encodedValues;
    }

    /**
     * @param name 1 键
     * @return java.lang.String
     * @Titel 单个参数获取过滤
     * @Description 单个参数获取过滤
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/9 17:11
     */
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        if (StringUtils.isEmpty(value)) {
            return value;
        }
        return cleanXSS(value);
    }

    /**
     * @param name 1 键
     * @return java.lang.String
     * @Titel 单个头部信息获取过滤
     * @Description 单个头部信息获取过滤
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/9 17:12
     */
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (StringUtils.isEmpty(value)) {
            return value;
        }
        return cleanXSS(value);
    }

    //替换XSS关键符号
    private String cleanXSS(String value) {
        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        value = value.replaceAll("'", "& #39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        value = value.replaceAll("[*]", "[" + "*]");
        value = value.replaceAll("[+]", "[" + "+]");
        value = value.replaceAll("[?]", "[" + "?]");
        return cleanSqlCodeWords(value);
    }

    //替换sql代码关键字母
    private String cleanSqlCodeWords(String value) {
        String paramValue = value;
        for (String keyword : InjectSQLFilterUtil.set) {
            if (paramValue.length() > keyword.length() + NumeralUtil.POSITIVE_FOUR
                    && (paramValue.contains(" " + keyword)
                    || paramValue.contains(keyword + " ")
                    || paramValue.contains(" " + keyword + " "))) {
                paramValue = StringUtils.replace(paramValue, keyword, InjectSQLFilterUtil.REPLACE_SQL_CODE);
                StringBuilder sb = new StringBuilder(this.currentUrl);
                sb.append("已被过滤，因为参数中包含不允许sql的关键词(").append(keyword).append(");过滤前的参数：")
                        .append(value).append(";过滤后的参数：").append(paramValue);
                log.info(sb.toString());
            }
        }
        return paramValue;
    }
}
