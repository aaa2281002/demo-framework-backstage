package com.framework.web.start;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.jms.annotation.EnableJms;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.start
 * @Description 后台管理启动类
 * @DateTime 2019/10/11
 * @Version 1.0 , exclude = {DataSourceAutoConfiguration.class}
 */
//@EnableJms
@SpringBootApplication(scanBasePackages = "com.framework.**")//
//@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
//@EnableDiscoveryClient//其他注册中心的注解，也可以用于Eureka注册中心。
//@EnableEurekaClient//专门对于Eureka注册中心使用的专一注解
@EnableCaching
public class WebBParentApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebBParentApplication.class, args);
    }
}
