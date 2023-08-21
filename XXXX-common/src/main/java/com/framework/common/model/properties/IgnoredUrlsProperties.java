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
    //忽略URL地址
    private List<String> ignoreUrl = new ArrayList<>();

    public List<String> getInitIgnoreUrl() {
        return initIgnoreUrl;
    }

    public void setInitIgnoreUrl(List<String> initIgnoreUrl) {
        this.initIgnoreUrl = initIgnoreUrl;
    }

    public List<String> getIgnoreUrl() {
        return ignoreUrl;
    }

    public void setIgnoreUrl(List<String> ignoreUrl) {
        this.ignoreUrl = ignoreUrl;
    }
}
