package com.framework.common.model.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.common.model.auth
 * @description 忽略数据权限角色
 * @datetime 2023/4/19 14:45
 */
@Configuration
@ConfigurationProperties(prefix = "custom.ignored-data-role-auth")
public class IgnoredDataRoleAuth {
    //忽略数据角色权限
    private List<String> authList = new ArrayList<>();

    public List<String> getAuthList() {
        return authList;
    }

    public void setAuthList(List<String> authList) {
        this.authList = authList;
    }
}
