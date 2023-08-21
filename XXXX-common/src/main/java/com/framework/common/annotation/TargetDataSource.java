package com.framework.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.annotation
 * @description 数据源注解类
 * @datetime 2019/10/11
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface TargetDataSource {
    //默认连接为只读
    String dataSource() default "";
}
