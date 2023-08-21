package com.framework.common.util.system;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.util.system
 * @description Application上下文对象工具类
 * @datetime 2019/10/11
 */
@Configuration
public class ApplicationContextUtil implements ApplicationContextAware {
    /**
     * 上下文对象实例
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    /**
     * @return org.springframework.context.ApplicationContext
     * @title 获取applicationContext
     * @description 获取applicationContext
     * @author 龘鵺
     * @datetime 2023/3/25 19:09
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * @param name 1 Bean名称
     * @return java.lang.Object
     * @title 通过name获取 Bean.
     * @description 通过name获取 Bean.
     * @author 龘鵺
     * @datetime 2023/3/25 19:09
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * @param clazz 1 类
     * @return T 类型
     * @title 通过class获取Bean
     * @description 通过class获取Bean
     * @author 龘鵺
     * @datetime 2023/3/25 19:10
     */
    public static <T> T getBean(Class<T> clazz) {

        return getApplicationContext().getBean(clazz);
    }

    /**
     * @param name  1 Bean名称
     * @param clazz 2 类
     * @return T 类型
     * @title 通过name, 以及Clazz返回指定的Bean
     * @description 通过name, 以及Clazz返回指定的Bean
     * @author 龘鵺
     * @datetime 2023/3/25 19:11
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}
