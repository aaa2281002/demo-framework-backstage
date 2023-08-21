package com.framework.mapper.generator.godeGenerator.custom;

import org.mybatis.generator.api.IntrospectedColumn;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * @author 龘鵺
 * @classname com.framework.mapper.generator.godeGenerator.custom
 * @description 参数组装工具类
 * @date 2023/3/26 10:57
 * @version 1.0
 */
public class FormattingUtilities {

    /**
     * @param introspectedColumn 1 参数对象
     * @return java.lang.String
     * @title 组装xml生成变量字符串
     * @description #{xxx,jdbcType=xxx}
     * @author 龘鵺
     * @datetime 2023/3/26 14:12
     */
    public static String getParameterClause(IntrospectedColumn introspectedColumn) {
        return getParameterClause(introspectedColumn, null);
    }

    /**
     * @param introspectedColumn 1 参数对象
     * @param prefix             2 前缀
     * @return java.lang.String
     * @title 组装xml生成变量字符串
     * @description #{prefix + xxx,jdbcType=xxx}
     * @author 龘鵺
     * @datetime 2023/3/26 14:12
     */
    public static String getParameterClause(IntrospectedColumn introspectedColumn, String prefix) {
        StringBuilder sb = new StringBuilder();

        sb.append("#{"); //$NON-NLS-1$
        sb.append(introspectedColumn.getJavaProperty(prefix));

        if (!"TIMESTAMP".equalsIgnoreCase(introspectedColumn.getJdbcTypeName())) {
            sb.append(",jdbcType="); //$NON-NLS-1$
            sb.append(introspectedColumn.getJdbcTypeName());
        }

        if (stringHasValue(introspectedColumn.getTypeHandler())) {
            sb.append(",typeHandler="); //$NON-NLS-1$
            sb.append(introspectedColumn.getTypeHandler());
        }

        sb.append('}');

        return sb.toString();
    }

}
