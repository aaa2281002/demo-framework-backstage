package com.framework.mapper.generator.godeGenerator.custom;

import com.framework.common.model.validation.ValidationGroup;
import com.framework.common.util.other.NumeralUtil;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.PropertyRegistry;

import javax.validation.constraints.Digits;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.mapper.generator.godeGenerator
 * @description 自定义注解生成类
 * @date 2023/3/3 11:55
 */
public class MybatisCommentGenerator implements CommentGenerator {

    private Properties properties;
    //    private Properties systemPro;
    private boolean suppressDate;
    private boolean suppressAllComments;
    private String nowTime;
    private String author;
    private Map<String, String> map = new HashMap<String, String>();
    private Map<String, String> descMap = new HashMap<String, String>();
    private String tableName; // 实体名称 （不带类字）
    private String className; // 实体类名称 （带类字）
    private String version; // 版本号
    private boolean swagger; // 是否生成注解 true=是， false=否
    private int position = 100;// 实体类 swagger 显示排序自增字段 始终从100开始。 多个实体类不会重置


    public MybatisCommentGenerator() {
        super();
        properties = new Properties();
//        systemPro = System.getProperties();
        suppressDate = false;
        suppressAllComments = false;
        nowTime = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
        descMap.put("id", "编号");
        descMap.put("row", "类对象");
        descMap.put("list", "集合对象");
        map.put("deleteByPrimaryKey", "公共根据编号，物理删除数据");
        map.put("insert", "");
        map.put("insertSelective", "公共非空字段验证添加");
        map.put("insertList", "批量添加");
        map.put("selectByPrimaryKey", "公共根据编号查询数据");
        map.put("updateByPrimaryKeySelective", "公共根据编号非空字段验证修改");
        map.put("updateList", "公共根据编号非空字段验证批量修改");
        map.put("selectByParam", "公共根据条件查询数据");
        map.put("findPageList", "公共根据条件分页查询");
        map.put("findPageListCount", "公共根据条件分页查询总数");
        map.put("findByList", "公共根据条件查询集合");
        map.put("delete", "公共根据条件逻辑删除数据");
    }

    //从注释生成器配置中配置的任何属性添加此实例的属性。此方法将在任何其他方法之前调用。参数：属性 – 配置中的所有属性
    @Override
    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);
        suppressDate = Boolean.valueOf(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));
        suppressAllComments = Boolean.valueOf(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
        author = String.valueOf(properties.getProperty("author"));
        version = String.valueOf(properties.getProperty("version"));
        swagger = Boolean.valueOf(properties.getProperty("swagger"));
    }

    /**
     * 类的注释
     *
     * @param innerClass
     * @param introspectedTable
     * @param markAsDoNotDelete
     */
    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        if (suppressAllComments) {
            return;
        }
//        System.out.println("addClassComment1");
        StringBuilder sb = new StringBuilder();
        innerClass.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
        sb.setLength(0);
        sb.append(" * @author ");
        sb.append(author);
        sb.append(" ");
        sb.append(nowTime);
        innerClass.addJavaDocLine(" */");
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
//        System.out.println("addClassComment2");
        StringBuilder sb = new StringBuilder();
        innerClass.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append(" ");
        sb.append(getDateString());
        innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
        innerClass.addJavaDocLine(" */");
    }

    /**
     * 设置字段注释
     * 此方法应将 Javadoc 注释添加到指定的字段。
     * 该字段与指定的表相关，用于保存指定列的值。
     * 重要提示：此方法应在注释中添加非标准的 JavaDoc 标记“@mbg.generated ”。
     * 如果没有此标记，基于 Eclipse 的 Java 合并功能将失败。参数：字段 – 字段 内省表 – 内省表 内省列 – 内省列
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (suppressAllComments
//                || "id".equalsIgnoreCase(field.getName())
//                || "createId".equalsIgnoreCase(field.getName())
//                || "createTime".equalsIgnoreCase(field.getName())
//                || "operaterId".equalsIgnoreCase(field.getName())
//                || "operaterTime".equalsIgnoreCase(field.getName())
//                || "operaterStatus".equalsIgnoreCase(field.getName())
        ) {
            return;
        }
        StringBuilder example = new StringBuilder();
        String dataType = "";
        String allowableValues = String.valueOf(introspectedColumn.getLength());
        if ("INTEGER".equalsIgnoreCase(introspectedColumn.getJdbcTypeName())
                || "BIGINT".equalsIgnoreCase(introspectedColumn.getJdbcTypeName())) {
            //notBlank
//            @NotNull(message = "请选择是否启用", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
            StringBuilder notBlank = new StringBuilder("@NotNull(");
            notBlank.append("message = \"请输入");
            notBlank.append(introspectedColumn.getRemarks());
            notBlank.append("\", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class, ValidationGroup.formQuery.class}");
            notBlank.append(")");
            field.addAnnotation(notBlank.toString());
            //size
//             @Max(value = NumeralUtil.POSITIVE_TWO, message = "错误选择", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
            StringBuilder size = new StringBuilder("@Max(");
            size.append("message = \"");
            size.append(introspectedColumn.getRemarks());
            size.append("最多");
            size.append(introspectedColumn.getLength());
            size.append("位数字");
            example.append("1-");
            if ("BIGINT".equalsIgnoreCase(introspectedColumn.getJdbcTypeName())) {
                size.append("\", value = Long.MAX_VALUE");
                example.append(Long.MAX_VALUE);
                dataType = "Long";
            } else {
                size.append("\", value = Integer.MAX_VALUE");
                example.append(Integer.MAX_VALUE);
                dataType = "Integer";
            }
//            size.append(introspectedColumn.getLength());
            size.append(", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class, ValidationGroup.formQuery.class}");
            size.append(")");
            field.addAnnotation(size.toString());
        } else if ("varchar".equalsIgnoreCase(introspectedColumn.getJdbcTypeName()) || "LONGVARCHAR".equalsIgnoreCase(introspectedColumn.getJdbcTypeName())) {
            example.append("xxx");
            //notBlank
            StringBuilder notBlank = new StringBuilder("@NotBlank(");
            notBlank.append("message = \"请输入");
            notBlank.append(introspectedColumn.getRemarks());
            notBlank.append("\", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class, ValidationGroup.formQuery.class}");
            notBlank.append(")");
            field.addAnnotation(notBlank.toString());
            //size
            StringBuilder size = new StringBuilder("@Size(");
            size.append("message = \"");
            size.append(introspectedColumn.getRemarks());
            size.append("最多");
            size.append(introspectedColumn.getLength());
            size.append("字符");
            size.append("\", max = ");
            size.append(introspectedColumn.getLength());
            size.append(", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class, ValidationGroup.formQuery.class}");
            size.append(")");
            field.addAnnotation(size.toString());
            dataType = "String";
//            @Size(message = "按钮代码最多64个字符", max = NumeralUtil.POSITIVE_SIXTY_FOUR, groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
        } else if ("DECIMAL".equalsIgnoreCase(introspectedColumn.getJdbcTypeName())) {
            StringBuilder notBlank = new StringBuilder("@NotNull(");
            notBlank.append("message = \"请输入");
            notBlank.append(introspectedColumn.getRemarks());
            notBlank.append("\", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class, ValidationGroup.formQuery.class}");
            notBlank.append(")");
            field.addAnnotation(notBlank.toString());
            //size
//             @Max(value = NumeralUtil.POSITIVE_TWO, message = "错误选择", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})
            StringBuilder size = new StringBuilder("@Digits(");
            size.append("message = \"");
            size.append(introspectedColumn.getRemarks());
            size.append("最多");
            size.append(introspectedColumn.getLength());
            size.append("位数字或带有");
            size.append(introspectedColumn.getLength());
            size.append("位数字带");
            size.append(introspectedColumn.getScale());
            size.append("位小数\"");
            size.append(", ");
            size.append("integer = ");
            size.append(introspectedColumn.getLength());
            size.append(", ");
            size.append("fraction = ");
            size.append(introspectedColumn.getScale());
            size.append(",");
            size.append(" groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class, ValidationGroup.formQuery.class}");
            size.append(")");
            example.append("1.0-");
            example.append(Double.MAX_VALUE);
            field.addAnnotation(size.toString());
            dataType = "Double";
            allowableValues = introspectedColumn.getLength() + "-" + introspectedColumn.getLength() + "." + introspectedColumn.getScale();
        }
        //   @NotBlank(message = "请输入菜单名称", groups = {ValidationGroup.formEdit.class, ValidationGroup.formAdd.class})

        field.addAnnotation("@QueryParam(name = \"" + introspectedColumn.getJavaProperty() + "\", code = \"p." + introspectedColumn.getActualColumnName() + "\")");
        if (swagger) {
            field.addAnnotation("@ApiModelProperty(value = \"" + introspectedColumn.getRemarks() + "\", name = \"" + introspectedColumn.getJavaProperty() + "\"," +
                    " dataType = \"" + dataType + "\", required = true, allowableValues = \"" + allowableValues + "\", hidden = true, " +
                    "example = \"" + example + "\", position = " + position + ")");
            position++;
        }

//        System.out.println("addFieldComment1");
        StringBuilder sb = new StringBuilder();
        field.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(introspectedColumn.getRemarks() + " " + introspectedColumn.getActualColumnName());
        field.addJavaDocLine(sb.toString().replace("\n", " "));
        field.addJavaDocLine(" */");
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
//        System.out.println("addFieldComment2");
        StringBuilder sb = new StringBuilder();
        field.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        field.addJavaDocLine(sb.toString().replace("\n", " "));
        field.addJavaDocLine(" */");
    }

    //

    /**
     * @param topLevelClass     1 写入对象
     * @param introspectedTable 2 数据表对象
     * @titel 重写给实体类加的注释
     * @description 重写给实体类加的注释
     * @author 龘鵺
     * @datetime 2023/3/3 15:47
     */
    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
//        System.out.println("addModelClassComment2");
        topLevelClass.addImportedType(new FullyQualifiedJavaType("com.framework.common.annotation.QueryParam"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("com.framework.common.model.validation.ValidationGroup"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("io.swagger.annotations.ApiModel"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("io.swagger.annotations.ApiModelProperty"));
//        topLevelClass.addImportedType(new FullyQualifiedJavaType("com.framework.common.util.other.NumeralUtil"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("javax.validation.constraints.Max"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("javax.validation.constraints.Min"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("javax.validation.constraints.NotBlank"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("javax.validation.constraints.NotNull"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("javax.validation.constraints.Digits"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("javax.validation.constraints.Size"));
        tableName = introspectedTable.getRemarks().lastIndexOf("表") > -1 ? introspectedTable.getRemarks().substring(0,
                introspectedTable.getRemarks().lastIndexOf("表")) : introspectedTable.getRemarks();
        className = tableName + "类";
//        System.out.println(topLevelClass.getType());
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * @author " + author);
        topLevelClass.addJavaDocLine(" * @version " + version);
        topLevelClass.addJavaDocLine(" * @className " + topLevelClass.getType());
        topLevelClass.addJavaDocLine(" * @titel " + className);
        topLevelClass.addJavaDocLine(" * @datetime " + nowTime);
        topLevelClass.addJavaDocLine(" */");
        topLevelClass.addJavaDocLine("@ApiModel(value = \"" + tableName + "\", description = \"" + tableName + "\", parent = BaseModel.class)");
    }

    /**
     * 设置setter方法注释
     */
    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }
//        method.addJavaDocLine("/**");
//        StringBuilder sb = new StringBuilder();
//        sb.append(" * ");
//        sb.append(introspectedColumn.getRemarks());
//        method.addJavaDocLine(sb.toString().replace("\n", " "));
//        sb.setLength(0);
//        sb.append(" * @author ");
//        sb.append(systemPro.getProperty("user.name"));
//        method.addJavaDocLine(sb.toString().replace("\n", " "));
//        sb.setLength(0);
//        if (suppressDate) {
//            sb.append(" * @date " + nowTime);
//            method.addJavaDocLine(sb.toString().replace("\n", " "));
//            sb.setLength(0);
//        }
//        Parameter parm = method.getParameters().get(0);
//        sb.append(" * @param ");
//        sb.append(parm.getName());
//        sb.append(" ");
//        sb.append(introspectedColumn.getRemarks());
//        method.addJavaDocLine(sb.toString().replace("\n", " "));
//        method.addJavaDocLine(" */");
    }

    /**
     * 设置getter方法注释
     */
    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }
//        method.addJavaDocLine("/**");
//        StringBuilder sb = new StringBuilder();
//        sb.append(" * ");
//        sb.append(introspectedColumn.getRemarks());
//        method.addJavaDocLine(sb.toString().replace("\n", " "));
//        sb.setLength(0);
//        sb.append(" * @author ");
//        sb.append(systemPro.getProperty("user.name"));
//        method.addJavaDocLine(sb.toString().replace("\n", " "));
//        sb.setLength(0);
//        if (suppressDate) {
//            sb.append(" * @date " + nowTime);
//            method.addJavaDocLine(sb.toString().replace("\n", " "));
//            sb.setLength(0);
//        }
//        sb.append(" * @return ");
//        sb.append(introspectedColumn.getActualColumnName());
//        sb.append(" ");
//        sb.append(introspectedColumn.getRemarks());
//        method.addJavaDocLine(sb.toString().replace("\n", " "));
//        method.addJavaDocLine(" */");
    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        if (suppressAllComments) {
            return;
        }
        //生成的是 JavaModel 和 JavaModelExample 文件
        if (compilationUnit instanceof TopLevelClass) {
//            System.out.println();
            //这里可以修改  JavaModel 和 JavaModelExample 文件
            /*TopLevelClass topLevelClass = (TopLevelClass)compilationUnit;
            String shortName = compilationUnit.getType().getShortName();
            topLevelClass.addAnnotation("@Resource");
            topLevelClass.addImportedType("javax.annotation.Resource");*/
        }
        if (compilationUnit instanceof Interface) {
            Interface anInterface = (Interface) compilationUnit;
            //下面的可以给JavaFile 添加注释
            ///**
            // * @author 邋遢龘鵺
            // * @className com.framework.mapper.system
            // * @description 系统后台操作白名单IPmapper接口类
            // * @datetime 2019/10/11
            // * @version 1.0
            // */
            FullyQualifiedJavaType fullyQualifiedJavaType = anInterface.getType();
            //首字母转小写
            String mapperName = StringUtils.uncapitalize(fullyQualifiedJavaType.getShortName());
            anInterface.addJavaDocLine("/**");
            anInterface.addJavaDocLine(" * @author " + author);
            anInterface.addJavaDocLine(" * @version " + version);
            anInterface.addJavaDocLine(" * @className " + fullyQualifiedJavaType.getPackageName());
            anInterface.addJavaDocLine(" * @description " + tableName + "mapper接口类");
            anInterface.addJavaDocLine(" * @datetime " + nowTime);
            anInterface.addJavaDocLine(" */");
            String shortName = compilationUnit.getType().getShortName();
            if (StringUtils.isEmpty(shortName) || !shortName.endsWith("Mapper")) {
                return;
            }
            //只给JavaModel添加注解就行了，Example不需要
            anInterface.addAnnotation("@Repository(\"" + mapperName + "\")");
            anInterface.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Repository"));
        }
//        System.out.println("type:" + compilationUnit.getType());
//        System.out.println("addJavaFileComment1");
        return;
    }

    @Override
    public void addComment(XmlElement xmlElement) {
//        System.out.println("addComment");
        return;
    }

    @Override
    public void addRootComment(XmlElement rootElement) {
//        System.out.println("addRootComment");
        return;
    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {
//        System.out.println("addGeneralMethodAnnotation");
    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> set) {
//        System.out.println("addGeneralMethodAnnotation");
    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {
//        System.out.println("addFieldAnnotation");
    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> set) {
//        System.out.println("addFieldAnnotation");
    }

    @Override
    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {
//        System.out.println("addClassAnnotation");
//        String remarks = introspectedTable.getRemarks();
//        innerClass.addJavaDocLine("/**");
//        innerClass.addJavaDocLine(" * " + remarks);
//        innerClass.addJavaDocLine(" * @author " + author);
//        innerClass.addJavaDocLine(" * @date   " + nowTime);
//        innerClass.addJavaDocLine(" */");
    }

    protected void addJavadocTag(Method method, boolean markAsDoNotDelete, String value) {
        position = 100;
//        System.out.println("==========================================================================");
//        System.out.println(method.getReturnType());
//        System.out.println(method.getName());
//        System.out.println(JSONObject.toJSONString(method.getParameters()));
//        System.out.println(JSONObject.toJSONString(method.getBodyLines()));
//        System.out.println(JSONObject.toJSONString(method.getExceptions()));
//        System.out.println(JSONObject.toJSONString(method.getTypeParameters()));
//        System.out.println(JSONObject.toJSONString(method.getAnnotations()));
//        System.out.println(JSONObject.toJSONString(method.getJavaDocLines()));
//        System.out.println(JSONObject.toJSONString(method.getVisibility()));
//        System.out.println("==========================================================================");
        List<Parameter> parameterList = method.getParameters();
        if (parameterList.size() == NumeralUtil.POSITIVE_ONE) {
            Parameter parameter = parameterList.get(NumeralUtil.POSITIVE_ZERO);
            method.addJavaDocLine(" * @param " + parameter.getName() + " " + descMap.get(parameter.getName()));
        }
        Optional<FullyQualifiedJavaType> optional = method.getReturnType();
        if (optional != null) {
            FullyQualifiedJavaType fullyQualifiedJavaType = optional.get();
            if (fullyQualifiedJavaType != null) {
                method.addJavaDocLine(" * @return " + fullyQualifiedJavaType.getFullyQualifiedName());
            }
        }
        method.addJavaDocLine(" * @author " + author);
        method.addJavaDocLine(" * @titel " + value);
        method.addJavaDocLine(" * @description " + value);
        if (suppressDate) {
            method.addJavaDocLine(" * @datetime " + nowTime);
        }
//        if (markAsDoNotDelete) {
//            method.addJavaDocLine(" do_not_delete_during_merge");
//        }
    }

    protected String getDateString() {
        if (suppressDate) {
            return nowTime;
        }
        return "";
    }

    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        innerEnum.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        innerEnum.addJavaDocLine(sb.toString().replace("\n", " "));
        innerEnum.addJavaDocLine(" */");
    }

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        descMap.put("row", className);
        String value = map.get(method.getName());
        if (suppressAllComments || StringUtils.isEmpty(value)) {
            return;
        }
        method.addJavaDocLine("/**");
        addJavadocTag(method, false, value);
        method.addJavaDocLine(" */");
    }
}
