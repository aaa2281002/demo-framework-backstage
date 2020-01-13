package com.framework.common.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.common.annotation
 * @Description excel注解类
 * @Date 2020/1/8 10:44
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD})
@Inherited
public @interface ExcelVOAttribute {
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
}
