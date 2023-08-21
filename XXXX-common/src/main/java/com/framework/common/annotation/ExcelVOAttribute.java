package com.framework.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.annotation
 * @description excel注解类
 * @date 2020/1/8 10:44
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Inherited
public @interface ExcelVOAttribute {
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

    /**
     * 导出到Excel中的名字.
     */
    public abstract String name();

    /**
     * 配置列的名称,对应A,B,C,D....
     */
    public abstract String column();

    /**
     * 配置列的宽度.
     */
    public abstract int width() default 8000;

    /**
     * 提示信息
     */
    public abstract String prompt() default "";

    /**
     * 设置只能选择不能输入的列内容.
     */
    public abstract String[] combo() default {};

    /**
     * 是否导出数据,应对需求:有时我们需要导出一份模板,这是标题需要但内容需要用户手工填写.
     */
    public abstract boolean isExport() default true;

    /**
     * 日期格式，用于时间字段类型转换格式
     */
    public abstract String pattern() default "";

    /**
     * 设置字体名称
     */
    public abstract String fontName() default "宋体";
}
