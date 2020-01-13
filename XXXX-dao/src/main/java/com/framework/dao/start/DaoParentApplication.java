package com.framework.dao.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.dao.start
 * @Description 数据库连接模块启动类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@SpringBootApplication
public class DaoParentApplication {
    public static void main(String[] args) {
        SpringApplication.run(DaoParentApplication.class, args);
    }
}
