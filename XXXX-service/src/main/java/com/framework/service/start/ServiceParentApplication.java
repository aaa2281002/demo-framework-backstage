package com.framework.service.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.start
 * @Description 业务处理启动类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = "com.framework.**")
public class ServiceParentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceParentApplication.class, args);
    }
}
