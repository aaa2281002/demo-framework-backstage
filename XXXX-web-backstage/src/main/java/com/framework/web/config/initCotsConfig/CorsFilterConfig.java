package com.framework.web.config.initCotsConfig;

import com.framework.common.util.other.CorsUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.lottery.controller.config.initCotsConfig
 * @Description 跨域初始化配置类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Configuration
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilterConfig {//implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        List<String> allowedHeaders = Arrays.toString("x-auth-token", "content-type", "X-Requested-With", "XMLHttpRequest");
//        List<String> exposedHeaders = Arrays.asList("x-auth-token", "content-type", "X-Requested-With", "XMLHttpRequest");
//        List<String> allowedMethods = Arrays.asList("POST", "GET", "DELETE", "PUT", "OPTIONS");
//        List<String> allowedOrigins = Arrays.asList("*");
//        registry.addMapping(CorsUtil.CORS_SLASH_ALL)
//                .allowCredentials(true)
//                .allowedHeaders(allowedHeaders.)
//                .allowedOrigins(CorsUtil.CORS_ALL)
//                .allowedMethods(CorsUtil.CORS_ALL)
//                .exposedHeaders(CorsUtil.CORS_ALL);
//    }

    /**
     * @Titel 跨域参数配置过滤方法
     * @Description 跨域参数配置过滤方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/27 17:34
     * @return org.springframework.web.cors.CorsConfiguration
     */
    private CorsConfiguration corsConfiguration() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedHeader(CorsUtil.CORS_ALL);
//        corsConfiguration.addAllowedOrigin(CorsUtil.CORS_ALL);
//        corsConfiguration.addAllowedMethod(CorsUtil.CORS_ALL);
//        corsConfiguration.addExposedHeader(CorsUtil.CORS_ALL);
//        corsConfiguration.setAllowCredentials(true);
        List<String> allowedHeaders = Arrays.asList("x-auth-token", "content-type", "X-Requested-With", "XMLHttpRequest");
        List<String> exposedHeaders = Arrays.asList("x-auth-token", "content-type", "X-Requested-With", "XMLHttpRequest");
        List<String> allowedMethods = Arrays.asList("POST", "GET", "DELETE", "PUT", "OPTIONS");
        List<String> allowedOrigins = Arrays.asList(CorsUtil.CORS_ALL);
        corsConfiguration.setAllowedHeaders(allowedHeaders);
        corsConfiguration.setAllowedMethods(allowedMethods);
        corsConfiguration.setAllowedOrigins(allowedOrigins);
        corsConfiguration.setExposedHeaders(exposedHeaders);
        corsConfiguration.setMaxAge(CorsUtil.maxAge);
        corsConfiguration.setAllowCredentials(Boolean.TRUE);
        return corsConfiguration;
    }

    /**
     * @Titel 跨域配置过滤方法
     * @Description 跨域配置过滤方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/27 17:34
     * @return org.springframework.web.filter.CorsFilter
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(CorsUtil.CORS_SLASH_ALL, corsConfiguration());
        return new CorsFilter(source);
    }
}
