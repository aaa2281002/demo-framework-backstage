package com.framework.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.common.annotation
 * @description 查询参数注解类
 * @date 2022/4/20 10:07
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface QueryParam {
    /**
     * ElementType.TYPE：能修饰类、接口或枚举类型
     * ElementType.FIELD：能修饰成员变量
     * ElementType.METHOD：能修饰方法
     * ElementType.PARAMETER：能修饰参数
     * ElementType.CONSTRUCTOR：能修饰构造器
     * ElementType.LOCAL_VARIABLE：能修饰局部变量
     * ElementType.ANNOTATION_TYPE：能修饰注解
     * ElementType.PACKAGE：能修饰包
     * ElementType.TYPE_PARAMETER：标明注解能够用于类型参数声明
     * ElementType.TYPE_USE：类型使用声明
     */
    //实体类字段名
    public String name() default "";

    //数据库字段名
    public String code();

    //是否为默认，排序。默认是createTime字段。其他字段全部不为默认。不建议其他字段为默认。
    public boolean isDefault() default false;

}
