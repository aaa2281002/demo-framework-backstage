package com.framework.common.model.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 龘鵺
 * @ClassName com.framework.common.model.properties
 * @Description 配置文件忽略URL地址属性类
 * @Date 2022/4/25 15:34
 * @Version 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "custom.ignored")
public class IgnoredUrlsProperties {
    //初始化忽略URL地址
    private List<String> initIgnoreUrl = new ArrayList<>();
    //忽略视图url
    private List<String> initViewIgnoreUrl = new ArrayList<>();
    //忽略URL地址
    private List<String> ignoreUrl = new ArrayList<>();
    //登录后忽略URL地址
    private List<String> logonIgnoreUrl = new ArrayList<>();
    //请求参数忽略地址
    private List<String> parameterNotFilterUrl = new ArrayList<>();

    public List<String> getInitIgnoreUrl() {
        return initIgnoreUrl;
    }

    public void setInitIgnoreUrl(List<String> initIgnoreUrl) {
        this.initIgnoreUrl = initIgnoreUrl;
    }

    public List<String> getInitViewIgnoreUrl() {
        return initViewIgnoreUrl;
    }

    public void setInitViewIgnoreUrl(List<String> initViewIgnoreUrl) {
        this.initViewIgnoreUrl = initViewIgnoreUrl;
    }

    public List<String> getIgnoreUrl() {
        return ignoreUrl;
    }

    public void setIgnoreUrl(List<String> ignoreUrl) {
        this.ignoreUrl = ignoreUrl;
    }

    public List<String> getLogonIgnoreUrl() {
        return logonIgnoreUrl;
    }

    public void setLogonIgnoreUrl(List<String> logonIgnoreUrl) {
        this.logonIgnoreUrl = logonIgnoreUrl;
    }

    public List<String> getParameterNotFilterUrl() {
        return parameterNotFilterUrl;
    }

    public void setParameterNotFilterUrl(List<String> parameterNotFilterUrl) {
        this.parameterNotFilterUrl = parameterNotFilterUrl;
    }
}
