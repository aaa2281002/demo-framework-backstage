package com.framework.mapper.generator.godeGenerator.custom;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.mapper.generator.godeGenerator.custom
 * @description 自定义mapper配置类
 * @date 2023/3/4 14:06
 */
public class CustomJavaMapperMethodGenerator extends AbstractJavaMapperMethodGenerator {
    @Override
    public void addInterfaceElements(Interface interfaze) {
        //InsertMethodGenerator

        //非空新增
        insertSelective(interfaze);
        //批量新增
        insertList(interfaze);
        //非空修改
        updateByPrimaryKeySelective(interfaze);
        //批量非空修改
        updateList(interfaze);
        // 增加 delete
        delete(interfaze);
        // 增加 selectByParam
        selectByParam(interfaze);
        // 增加 findPageList
        findPageList(interfaze);
        // 增加 findPageListCount
        findPageListCount(interfaze);
        // 生成 findByList
        findByList(interfaze);
    }

    private void insertSelective(Interface interfaze) {
        // 先创建import对象
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        // 添加Lsit的包
        importedTypes.add(FullyQualifiedJavaType.getNewListInstance());
        // 创建方法对象
        Method method = new Method("");
        // 设置该方法为public
        method.setVisibility(JavaVisibility.PUBLIC);
        // 设置返回类型
        FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getIntInstance();
        // 方法对象设置返回类型对象
        method.setReturnType(returnType);
        // 设置为接口方法
        method.setAbstract(true);
        // 设置方法名称为我们在IntrospectedTable类中初始化的 “selectByObject”
        method.setName("insertSelective");

        // 设置参数类型是对象
        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        // import参数类型对象
        importedTypes.add(parameterType);
        // 为方法添加参数，变量名称row
        method.addParameter(new Parameter(parameterType, "row"));
        //
        addMapperAnnotations(interfaze, method);
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        if (context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

    private void insertList(Interface interfaze) {
        // 先创建import对象
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        // 添加Lsit的包
        importedTypes.add(FullyQualifiedJavaType.getNewListInstance());
        // 创建方法对象
        Method method = new Method("");
        // 设置该方法为public
        method.setVisibility(JavaVisibility.PUBLIC);
        // 设置返回类型
        FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getIntInstance();
        // 方法对象设置返回类型对象
        method.setReturnType(returnType);
        // 设置为接口方法
        method.setAbstract(true);
        // 设置方法名称为我们在IntrospectedTable类中初始化的 “selectByObject”
        // 设置方法名称为我们在IntrospectedTable类中初始化的 “selectByObject”
        method.setName("insertList");


        // 设置参数类型是对象
        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType("java.util.List");
        parameterType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        // import参数类型对象
        importedTypes.add(parameterType);
        // 为方法添加参数，变量名称 list
        method.addParameter(new Parameter(parameterType, "list"));
        //
        addMapperAnnotations(interfaze, method);
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        if (context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

    private void updateByPrimaryKeySelective(Interface interfaze) {
        // 先创建import对象
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        // 添加Lsit的包
        importedTypes.add(FullyQualifiedJavaType.getNewListInstance());
        // 创建方法对象
        Method method = new Method("");
        // 设置该方法为public
        method.setVisibility(JavaVisibility.PUBLIC);
        // 设置返回类型
        FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getIntInstance();
        // 方法对象设置返回类型对象
        method.setReturnType(returnType);
        // 设置为接口方法
        method.setAbstract(true);
        // 设置方法名称为我们在IntrospectedTable类中初始化的 “selectByObject”
        method.setName("updateByPrimaryKeySelective");

        // 设置参数类型是对象
        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        // import参数类型对象
        importedTypes.add(parameterType);
        // 为方法添加参数，变量名称row
        method.addParameter(new Parameter(parameterType, "row"));
        //
        addMapperAnnotations(interfaze, method);
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        if (context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

    private void updateList(Interface interfaze) {
        // 先创建import对象
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        // 添加Lsit的包
        importedTypes.add(FullyQualifiedJavaType.getNewListInstance());
        // 创建方法对象
        Method method = new Method("");
        // 设置该方法为public
        method.setVisibility(JavaVisibility.PUBLIC);
        // 设置返回类型
        FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getIntInstance();
        // 方法对象设置返回类型对象
        method.setReturnType(returnType);
        // 设置为接口方法
        method.setAbstract(true);
        // 设置方法名称为我们在IntrospectedTable类中初始化的 “selectByObject”
        // 设置方法名称为我们在IntrospectedTable类中初始化的 “selectByObject”
        method.setName("updateList");


        // 设置参数类型是对象
        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType("java.util.List");
        parameterType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        // import参数类型对象
        importedTypes.add(parameterType);
        // 为方法添加参数，变量名称 list
        method.addParameter(new Parameter(parameterType, "list"));
        //
        addMapperAnnotations(interfaze, method);
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        if (context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

    private void delete(Interface interfaze) {
        // 先创建import对象
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        // 添加Lsit的包
        importedTypes.add(FullyQualifiedJavaType.getNewListInstance());
        // 创建方法对象
        Method method = new Method("");
        // 设置该方法为public
        method.setVisibility(JavaVisibility.PUBLIC);
        // 设置返回类型
        FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getIntInstance();
        // 方法对象设置返回类型对象
        method.setReturnType(returnType);
        // 设置为接口方法
        method.setAbstract(true);
        // 设置方法名称为我们在IntrospectedTable类中初始化的 “selectByObject”
        method.setName("delete");

        // 设置参数类型是对象
        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        // import参数类型对象
        importedTypes.add(parameterType);
        // 为方法添加参数，变量名称row
        method.addParameter(new Parameter(parameterType, "row"));
        //
        addMapperAnnotations(interfaze, method);
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        if (context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

    private void selectByParam(Interface interfaze) {
        // 先创建import对象
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        // 添加Lsit的包
        importedTypes.add(FullyQualifiedJavaType.getNewListInstance());
        // 创建方法对象
        Method method = new Method("");
        // 设置该方法为public
        method.setVisibility(JavaVisibility.PUBLIC);
        // 设置返回类型是List
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        // 方法对象设置返回类型对象
        method.setReturnType(returnType);
        // 设置为接口方法
        method.setAbstract(true);
        // 设置方法名称为我们在IntrospectedTable类中初始化的 “selectByObject”
        method.setName("selectByParam");

        // 设置参数类型是对象
        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        // import参数类型对象
        importedTypes.add(parameterType);
        // 为方法添加参数，变量名称row
        method.addParameter(new Parameter(parameterType, "row"));
        //
        addMapperAnnotations(interfaze, method);
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        if (context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

    private void findPageList(Interface interfaze) {
        // 先创建import对象
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        // 添加Lsit的包
        importedTypes.add(FullyQualifiedJavaType.getNewListInstance());
        // 创建方法对象
        Method method = new Method("");
        // 设置该方法为public
        method.setVisibility(JavaVisibility.PUBLIC);
        // 设置返回类型是List
        FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance();
        // 设置List的类型是实体类的对象
        FullyQualifiedJavaType listType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        importedTypes.add(listType);
        // 返回类型对象设置为List
        returnType.addTypeArgument(listType);
        // 方法对象设置返回类型对象
        method.setReturnType(returnType);
        // 设置为接口方法
        method.setAbstract(true);
        // 设置方法名称为我们在IntrospectedTable类中初始化的 “selectByObject”
        method.setName("findPageList");

        // 设置参数类型是对象
        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        // import参数类型对象
        importedTypes.add(parameterType);
        // 为方法添加参数，变量名称row
        method.addParameter(new Parameter(parameterType, "row"));
        //
        addMapperAnnotations(interfaze, method);
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        if (context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

    private void findPageListCount(Interface interfaze) {
        // 先创建import对象
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        // 添加Lsit的包
        importedTypes.add(FullyQualifiedJavaType.getNewListInstance());
        // 创建方法对象
        Method method = new Method("");
        // 设置该方法为public
        method.setVisibility(JavaVisibility.PUBLIC);
        // 设置返回类型
        FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getIntInstance();
        // 方法对象设置返回类型对象
        method.setReturnType(returnType);
        // 设置为接口方法
        method.setAbstract(true);
        // 设置方法名称为我们在IntrospectedTable类中初始化的 “selectByObject”
        method.setName("findPageListCount");

        // 设置参数类型是对象
        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        // import参数类型对象
        importedTypes.add(parameterType);
        // 为方法添加参数，变量名称row
        method.addParameter(new Parameter(parameterType, "row"));
        //
        addMapperAnnotations(interfaze, method);
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        if (context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

    private void findByList(Interface interfaze) {
        // 先创建import对象
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        // 添加Lsit的包
        importedTypes.add(FullyQualifiedJavaType.getNewListInstance());
        // 创建方法对象
        Method method = new Method("");
        // 设置该方法为public
        method.setVisibility(JavaVisibility.PUBLIC);
        // 设置返回类型是List
        FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance();
        // 设置List的类型是实体类的对象
        FullyQualifiedJavaType listType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        importedTypes.add(listType);
        // 返回类型对象设置为List
        returnType.addTypeArgument(listType);
        // 方法对象设置返回类型对象
        method.setReturnType(returnType);
        // 设置为接口方法
        method.setAbstract(true);
        // 设置方法名称为我们在IntrospectedTable类中初始化的 “selectByObject”
        method.setName("findByList");

        // 设置参数类型是对象
        FullyQualifiedJavaType parameterType;
        parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        // import参数类型对象
        importedTypes.add(parameterType);
        // 为方法添加参数，变量名称row
        method.addParameter(new Parameter(parameterType, "row"));
        //
        addMapperAnnotations(interfaze, method);
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        if (context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

    public void addMapperAnnotations(Interface interfaze, Method method) {

    }
}
