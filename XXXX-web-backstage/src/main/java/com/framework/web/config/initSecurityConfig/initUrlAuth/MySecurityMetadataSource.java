package com.framework.web.config.initSecurityConfig.initUrlAuth;

import com.framework.common.model.auth.IgnoredDataRoleAuth;
import com.framework.common.model.auth.IgnoredRoleAuth;
import com.framework.common.model.properties.IgnoredUrlsProperties;
import com.framework.common.util.other.NumeralUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.PathMatcher;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Author 龘鵺
 * @ClassName com.framework.api.config.initSecurityConfig.initUrlAuth
 * @Description 权限资源管理器, 为权限决断器提供支持，监控用户行为
 * @Date 2022/3/2 15:03
 * @Version 1.0
 */
//@Component("mySecurityMetadataSource")
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private static Logger log = LoggerFactory.getLogger(MySecurityMetadataSource.class);
    //    @Autowired
//    private PermissionService permissionService;

    @Autowired
    private PathMatcher pathMatcher;
    @Autowired
    private IgnoredUrlsProperties ignoredUrlsProperties;
    @Autowired
    private IgnoredRoleAuth ignoredRoleAuth;

    /**
     * @param o 1 对象
     * @return java.util.Collection<org.springframework.security.access.ConfigAttribute>
     * @throws IllegalArgumentException
     * @title 验证权限
     * @description 判定用户请求的url是否在权限表中, 如果在权限表中，则返回给decide方法，用来判定用户是否有此权限, 如果不在权限表中则放行
     * @author 龘鵺
     * @dateTime 2023/5/25 15:34
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {

//        log.info("MySecurityMetadataSource.getAttributes");
//        String path = ((FilterInvocation) o).getRequestUrl();
//        String url = null;
//        if (path.indexOf("?") > NumeralUtil.NEGATIVE_ONE) {
//            url = path.substring(0, path.indexOf("?"));
//        } else {
//            url = path;
//        }
        return null;

//        String url = ((FilterInvocation) o).getHttpRequest().getRequestURI();
//        for (String item : ignoredUrlsProperties.getLogonIgnoreUrl()) {
//            boolean isSms = pathMatcher.match(item, url);
//            if (isSms) {
//                return null;
//            }
//        }
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//            Object principal = authentication.getPrincipal();
//            if (principal instanceof CustomUser) {
//                CustomUser customUser = ((CustomUser) authentication.getPrincipal());
//                if (ignoredRoleAuth.getAuthList().contains(customUser.getSystemUser().getRoleCode())) {
//                    return null;
//                }
//                if (StringUtils.isNotEmpty(url)) {
//                    url = url.substring(NumeralUtil.POSITIVE_ONE);
//                }
//                Collection<GrantedAuthority> authorities = customUser.getAuthorities();
//                for (GrantedAuthority item : authorities) {
//                    if (item.getAuthority().equals(url)) {
//                        return null;
//                    }
//                }
//                Collection<ConfigAttribute> configAttributes = new ArrayList<>();
//                ConfigAttribute cfg = new SecurityConfig("YES");
//                configAttributes.add(cfg);
//                return configAttributes;
//            }
//        }
//        Collection<ConfigAttribute> configAttributes = new ArrayList<>();
//        ConfigAttribute cfg = new SecurityConfig("YES");
//        configAttributes.add(cfg);
//        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        log.info("---------------------------------------------MySecurityMetadataSource.getAllConfigAttributes=2---------------------------------------------");
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        log.info("---------------------------------------------MySecurityMetadataSource.supports=1---------------------------------------------");
        return true;
    }
}
