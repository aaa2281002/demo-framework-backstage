package com.framework.common.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.common.start
 * @Description 工具模块启动类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@SpringBootApplication
public class CommonParentApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonParentApplication.class, args);
    }
}
