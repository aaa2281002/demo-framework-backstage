package com.framework.web.config.initSecurityConfig;

import com.framework.web.config.initAuthorityVerify.MyPermissionEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.config.initSecurityConfig
 * @Description 自定义全局方法安全配置
 * @DateTime 2019/12/22 15:10
 * @Version 1.0
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MyGlobalMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {
    @Autowired
    private MyPermissionEvaluator myPermissionEvaluator;

    /**
     * @return org.springframework.security.access.expression.method.MethodSecurityExpressionHandler
     * @Titel 配置权限验证实现类
     * @Description 配置权限验证实现类
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 15:22
     */
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler = new DefaultMethodSecurityExpressionHandler();
        defaultMethodSecurityExpressionHandler.setPermissionEvaluator(myPermissionEvaluator);
        return defaultMethodSecurityExpressionHandler;
    }
}
