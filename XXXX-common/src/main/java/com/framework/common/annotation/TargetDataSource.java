package com.framework.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.common.annotation
 * @Description 数据源注解类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface TargetDataSource {
    //默认连接为只读
    String dataSource() default "";
}
