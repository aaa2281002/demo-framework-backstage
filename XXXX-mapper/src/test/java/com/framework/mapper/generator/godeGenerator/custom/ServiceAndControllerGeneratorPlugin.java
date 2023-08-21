package com.framework.mapper.generator.godeGenerator.custom;

import com.framework.common.util.hump.HumpOrLineUtil;
import com.framework.common.util.other.NumeralUtil;
import com.framework.mapper.generator.godeGenerator.html.HtmlGeneratorConfig;
import com.framework.mapper.generator.godeGenerator.html.HtmlGeneratorUtil;
import com.framework.mapper.generator.godeGenerator.html.HtmlModelUtil;
import com.framework.mapper.generator.godeGenerator.html.TemplateUtils;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.mapper.generator.godeGenerator.custom
 * @description 自定义controller和service生成类
 * @date 2023/3/6 19:42
 */
public class ServiceAndControllerGeneratorPlugin extends PluginAdapter {
    //https://blog.csdn.net/qq_28898309/article/details/106812466
    // service项目目录，一般为 src/main/java
    private String serviceTargetProject;

    // service包名，如：com.thinkj2ee.cms.service.service
    private String servicePackage;

    // service实现类包名，如：com.thinkj2ee.cms.service.service.impl
    private String serviceImplPackage;

    // controller项目目录，一般为 src/main/java
    private String controllerTargetProject;
    // controller类包名，如：com.thinkj2ee.cms.service.controller
    private String controllerPackage;
    //html目录
    private String htmlPackage;

    // service接口名前缀
    private String servicePreffix;

    // service接口名后缀
    private String serviceSuffix;

    // service接口的父接口
    private String superServiceInterface;

    // service实现类的父类
    private String superServiceImpl;
    // controller类的父类
    private String superController;

    // dao接口基类
    private String superDaoInterface;

    // Example类的包名
    private String examplePacket;
    //实体类路径
    private String recordType;
    //实体类类名 如：SystemButton
    private String modelName;
    //实体类类名 如：systemButton
    private String lowercaseInitialModelName;
    //实体类路径
    private FullyQualifiedJavaType model;

    private String serviceName;//业务接口类路径
    private String serviceImplName;//业务实现类路径
    private String controllerName;//请求控制类路径
    private String nowTime;//时间
    private String author;//开发人
    private String version;//版本号
    private String tableName;//表名注视 如：按钮表
    private String className;//表名注视 如：按钮
    private String returnName = "com.framework.common.response.ResponseResult";//默认全局返回工具类
    private String tableCode;//表名全大写
    private String pathPrefix;//html页面路径
    private String controllerPath;//请求控制类路径
    private boolean generateHtml;//是否生成HTML  true=生成  false=不生成
    private boolean swagger;//是否生成swagger  true=生成  false=不生成

    @Override
    public boolean validate(List<String> warnings) {
        boolean valid = true;

       /* if (!stringHasValue(properties
                .getProperty("targetProject"))) { //$NON-NLS-1$
            warnings.add(getString("ValidationError.18", //$NON-NLS-1$
                    "MapperConfigPlugin", //$NON-NLS-1$
                    "targetProject")); //$NON-NLS-1$
            valid = false;
        }
        if (!stringHasValue(properties.getProperty("servicePackage"))) { //$NON-NLS-1$
            warnings.add(getString("ValidationError.18", //$NON-NLS-1$
                    "MapperConfigPlugin", //$NON-NLS-1$
                    "servicePackage")); //$NON-NLS-1$
            valid = false;
        }
        if (!stringHasValue(properties.getProperty("serviceImplPackage"))) { //$NON-NLS-1$
            warnings.add(getString("ValidationError.18", //$NON-NLS-1$
                    "MapperConfigPlugin", //$NON-NLS-1$
                    "serviceImplPackage")); //$NON-NLS-1$
            valid = false;
        }
*/
        serviceTargetProject = properties.getProperty("serviceTargetProject");
        controllerTargetProject = properties.getProperty("controllerTargetProject");
        servicePackage = properties.getProperty("servicePackage");
        serviceImplPackage = properties.getProperty("serviceImplPackage");
        servicePreffix = properties.getProperty("servicePreffix");
        servicePreffix = stringHasValue(servicePreffix) ? servicePreffix : "";
        serviceSuffix = properties.getProperty("serviceSuffix");
        serviceSuffix = stringHasValue(serviceSuffix) ? serviceSuffix : "";
        superServiceInterface = properties.getProperty("superServiceInterface");
        superServiceImpl = properties.getProperty("superServiceImpl");
        superDaoInterface = properties.getProperty("superDaoInterface");
        controllerPackage = properties.getProperty("controllerPackage");
        htmlPackage = properties.getProperty("htmlPackage");
        generateHtml = Boolean.valueOf(properties.getProperty("generateHtml"));
        swagger = Boolean.valueOf(properties.getProperty("swagger"));
        superController = properties.getProperty("superController");
        nowTime = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
        author = properties.getProperty("author");
        version = properties.getProperty("version");
        pathPrefix = htmlPackage.substring(htmlPackage.lastIndexOf("lowerright"));
        controllerPath = htmlPackage.substring(htmlPackage.lastIndexOf("lowerright") + 10);
        return valid;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        recordType = introspectedTable.getBaseRecordType();
        modelName = recordType.substring(recordType.lastIndexOf(".") + 1);
        model = new FullyQualifiedJavaType(recordType);
        serviceName = servicePackage + "." + servicePreffix + modelName + serviceSuffix;
        serviceImplName = serviceImplPackage + "." + modelName + serviceSuffix + "Impl";
        examplePacket = recordType.substring(0, recordType.lastIndexOf("."));
        controllerName = controllerPackage.concat(".").concat(modelName).concat("Controller");
        tableName = introspectedTable.getRemarks().lastIndexOf("表") > -1 ? introspectedTable.getRemarks().substring(0,
                introspectedTable.getRemarks().lastIndexOf("表")) + "类" : introspectedTable.getRemarks() + "类";
        className = introspectedTable.getRemarks().lastIndexOf("表") > -1 ? introspectedTable.getRemarks().substring(0,
                introspectedTable.getRemarks().lastIndexOf("表")) : introspectedTable.getRemarks();
        tableCode = HumpOrLineUtil.humpToLine(modelName).toUpperCase().substring(1);
        lowercaseInitialModelName = StringUtils.uncapitalize(modelName);
        List<GeneratedJavaFile> answer = new ArrayList<>();
        GeneratedJavaFile gjf = generateServiceInterface(introspectedTable);
        GeneratedJavaFile gjf2 = generateServiceImpl(introspectedTable);
        GeneratedJavaFile gjf3 = generateController(introspectedTable);
        answer.add(gjf);
        answer.add(gjf2);
        answer.add(gjf3);

        if (generateHtml) {
            //生成HTML
            HtmlGeneratorConfig htmlGeneratorConfig = new HtmlGeneratorConfig();
            Map<String, String> map = htmlGeneratorConfig.getGeneratorConfig();
            Map<String, Object> model = HtmlModelUtil.getModel(introspectedTable);
            model.put("className", lowercaseInitialModelName);
            model.put("fullName", className);
            model.put("tableCode", tableCode);
            String url = HumpOrLineUtil.humpToSlash(modelName).toLowerCase();
            model.put("url", controllerPath + url.substring(url.indexOf("/") + 1));
            String addContent = TemplateUtils.getContent(map.get("add"), model, "add");
            String editContent = TemplateUtils.getContent(map.get("edit"), model, "edit");
            String viewContent = TemplateUtils.getContent(map.get("view"), model, "view");
            String listContent = TemplateUtils.getContent(map.get("list"), model, "list");
            HtmlGeneratorUtil.htmlGenerator(addContent, htmlPackage + lowercaseInitialModelName, lowercaseInitialModelName + "Add.html");
            HtmlGeneratorUtil.htmlGenerator(editContent, htmlPackage + lowercaseInitialModelName, lowercaseInitialModelName + "Edit.html");
            HtmlGeneratorUtil.htmlGenerator(viewContent, htmlPackage + lowercaseInitialModelName, lowercaseInitialModelName + "View.html");
            HtmlGeneratorUtil.htmlGenerator(listContent, htmlPackage + lowercaseInitialModelName, lowercaseInitialModelName + "List.html");
        }
        return answer;
    }

    // 生成service接口
    private GeneratedJavaFile generateServiceInterface(IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType service = new FullyQualifiedJavaType(serviceName);
        Interface serviceInterface = new Interface(service);
        serviceInterface.setVisibility(JavaVisibility.PUBLIC);
        // 添加父接口
        if (stringHasValue(superServiceInterface)) {
            String superServiceInterfaceName = superServiceInterface.substring(superServiceInterface.lastIndexOf(".") + 1);
            serviceInterface.addImportedType(new FullyQualifiedJavaType(superServiceInterface));
            serviceInterface.addImportedType(model);
            serviceInterface.addSuperInterface(new FullyQualifiedJavaType(superServiceInterfaceName + "<" + modelName + ">"));
        }
        //引用对象
        serviceInterface.addImportedType(new FullyQualifiedJavaType("com.framework.common.response.ResponseResult"));
        serviceInterface.addImportedType(model);
        serviceInterface.addImportedType(new FullyQualifiedJavaType("java.util.List"));
        //类注释
        serviceInterface.addJavaDocLine("/**");
        serviceInterface.addJavaDocLine(" * @author " + author);
        serviceInterface.addJavaDocLine(" * @version " + version);
        serviceInterface.addJavaDocLine(" * @className " + serviceImplName);
        serviceInterface.addJavaDocLine(" * @description " + className + "接口实现类");
        serviceInterface.addJavaDocLine(" * @datetime " + nowTime);
        serviceInterface.addJavaDocLine(" */");

        //描述 方法名
        Method getByIdParam = new Method("getByIdParam");
        annotate(getByIdParam, returnName, "根据编号查询" + className, "根据编号查询" + className, ("id 1 编号"));
        //方法注解
        //返回值
        FullyQualifiedJavaType methodReturnType = new FullyQualifiedJavaType(modelName);
        getByIdParam.setReturnType(methodReturnType);
        //设置参数列表
        Parameter parameter = new Parameter(new FullyQualifiedJavaType("Long"), "id");
        getByIdParam.addParameter(parameter);
        // 设置为接口方法
        getByIdParam.setAbstract(true);
        serviceInterface.addMethod(getByIdParam);

        if (!generateHtml) {
            //描述 方法名
            Method getByIdInfo = new Method("getByIdInfo");
            annotate(getByIdInfo, returnName, "根据编号查询" + className, "根据编号查询" + className, ("id 1 编号"));
            //方法注解
            //返回值
            methodReturnType = new FullyQualifiedJavaType("ResponseResult");
            getByIdInfo.setReturnType(methodReturnType);
            //设置参数列表
            parameter = new Parameter(new FullyQualifiedJavaType("Long"), "id");
            getByIdInfo.addParameter(parameter);
            // 设置为接口方法
            getByIdInfo.setAbstract(true);
            serviceInterface.addMethod(getByIdInfo);
        }

        //描述 方法名
        Method save = new Method("save");
        annotate(save, returnName, "新增" + className, "新增" + className, ("row 1 " + tableName));
        //方法注解

        //返回值
        methodReturnType = new FullyQualifiedJavaType("ResponseResult");
        save.setReturnType(methodReturnType);
        //设置参数列表
        parameter = new Parameter(new FullyQualifiedJavaType(modelName), "row");
        save.addParameter(parameter);
        // 设置为接口方法
        save.setAbstract(true);
        serviceInterface.addMethod(save);

        //描述 方法名
        Method edit = new Method("edit");
        annotate(edit, returnName, "编辑" + className, "编辑" + className, ("row 1 " + tableName));
        //方法注解

        //返回值
        methodReturnType = new FullyQualifiedJavaType("ResponseResult");
        edit.setReturnType(methodReturnType);
        //设置参数列表
        parameter = new Parameter(new FullyQualifiedJavaType(modelName), "row");
        edit.addParameter(parameter);
        // 设置为接口方法
        edit.setAbstract(true);
        serviceInterface.addMethod(edit);


        //描述 方法名
        Method delete = new Method("delete");
        annotate(delete, returnName, "删除" + className, "删除" + className, ("id 1 编号"));
        //方法注解

        //返回值
        methodReturnType = new FullyQualifiedJavaType("ResponseResult");
        delete.setReturnType(methodReturnType);
        //设置参数列表
        parameter = new Parameter(new FullyQualifiedJavaType("Long"), "id");
        delete.addParameter(parameter);
        // 设置为接口方法
        delete.setAbstract(true);
        serviceInterface.addMethod(delete);

        //描述 方法名
        Method batchDeleteList = new Method("batchDeleteList");
        annotate(batchDeleteList, returnName, "批量删除" + className, "批量删除" + className, ("idList 1 编号集合"));
        //方法注解

        //返回值
        methodReturnType = new FullyQualifiedJavaType("ResponseResult");
        batchDeleteList.setReturnType(methodReturnType);
        //设置参数列表
        parameter = new Parameter(new FullyQualifiedJavaType("List<Long>"), "idList");
        batchDeleteList.addParameter(parameter);
        // 设置为接口方法
        batchDeleteList.setAbstract(true);
        serviceInterface.addMethod(batchDeleteList);

        //    public List<SystemButton> findByList(SystemButton record) {
        //        return systemButtonMapper.findByList(record);
        //    }

        //描述 方法名
        Method findByList = new Method("findByList");
        annotate(findByList, "java.util.List<" + model.getFullyQualifiedName() + ">", "查询" + className + "集合", "查询" + className + "集合", ("row 1 " + tableName));
        //方法注解

        //返回值
        methodReturnType = new FullyQualifiedJavaType("List<" + modelName + ">");
        findByList.setReturnType(methodReturnType);
        //设置参数列表
        parameter = new Parameter(new FullyQualifiedJavaType(modelName), "row");
        findByList.addParameter(parameter);
        // 设置为接口方法
        findByList.setAbstract(true);
        serviceInterface.addMethod(findByList);


        //描述 方法名
        Method findParamPageList = new Method("findParamPageList");
        annotate(findParamPageList, returnName, "分页查询" + className + "列表", "分页查询" + className + "列表", ("row 1 " + tableName));
        //方法注解

        //返回值
        methodReturnType = new FullyQualifiedJavaType("ResponseResult");
        findParamPageList.setReturnType(methodReturnType);
        //设置参数列表
        parameter = new Parameter(new FullyQualifiedJavaType(modelName), "row");
        findParamPageList.addParameter(parameter);
        // 设置为接口方法
        findParamPageList.setAbstract(true);
        serviceInterface.addMethod(findParamPageList);

        GeneratedJavaFile gjf = new GeneratedJavaFile(serviceInterface, serviceTargetProject, context.getJavaFormatter());
        return gjf;
    }

    // 生成serviceImpl实现类
    private GeneratedJavaFile generateServiceImpl(IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType service = new FullyQualifiedJavaType(serviceName);
        FullyQualifiedJavaType serviceImpl = new FullyQualifiedJavaType(serviceImplName);
        TopLevelClass clazz = new TopLevelClass(serviceImpl);
        //描述类的作用域修饰符
        clazz.setVisibility(JavaVisibility.PUBLIC);
        //描述类 引入的类
        clazz.addImportedType(service);
        //描述类 的实现接口类
        clazz.addSuperInterface(service);
        if (stringHasValue(superServiceImpl)) {
            String superServiceImplName = superServiceImpl.substring(superServiceImpl.lastIndexOf(".") + 1);
            clazz.addImportedType(superServiceImpl);
            clazz.addImportedType(model);
            //带泛型
//            clazz.setSuperClass(superServiceImplName + "<" + modelName + ">");
            //不带泛型
            clazz.setSuperClass(superServiceImplName);
        }
        String serviceImplFieldName = serviceImplName.substring(serviceImplName.lastIndexOf(".") + 1);
        String smallServiceImplFieldName = StringUtils.uncapitalize(serviceImplFieldName);
        //引用对象
        clazz.addImportedType(new FullyQualifiedJavaType("com.alibaba.fastjson.JSONObject"));
        clazz.addImportedType(new FullyQualifiedJavaType("com.framework.common.response.ResponseResult"));
        clazz.addImportedType(new FullyQualifiedJavaType("com.framework.common.util.other.NumeralUtil"));
        clazz.addImportedType(new FullyQualifiedJavaType("com.framework.model.system.SystemUser"));
        clazz.addImportedType(new FullyQualifiedJavaType("org.slf4j.Logger"));
        clazz.addImportedType(new FullyQualifiedJavaType("org.slf4j.LoggerFactory"));
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Service"));
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.transaction.annotation.Transactional"));
        clazz.addImportedType(new FullyQualifiedJavaType("java.util.Date"));
        clazz.addImportedType(new FullyQualifiedJavaType("java.util.List"));
        clazz.addAnnotation("@Service(\"" + smallServiceImplFieldName + "\")");

        String daoFieldType = introspectedTable.getMyBatis3JavaMapperType();
        String daoFieldName = StringUtils.uncapitalize(daoFieldType.substring(daoFieldType.lastIndexOf(".") + 1));
        //类注释
        clazz.addJavaDocLine("/**");
        clazz.addJavaDocLine(" * @author " + author);
        clazz.addJavaDocLine(" * @version " + version);
        clazz.addJavaDocLine(" * @className " + serviceImplName);
        clazz.addJavaDocLine(" * @description " + className + "业务接口实现类");
        clazz.addJavaDocLine(" * @datetime " + nowTime);
        clazz.addJavaDocLine(" */");
        //描述类的成员属性
        Field daoField = new Field(daoFieldName, new FullyQualifiedJavaType(daoFieldType));
        //加入日志代码
        daoField.addJavaDocLine("private Logger log = LoggerFactory.getLogger(" + serviceImplFieldName + ".class);");
        //创建mapper申明
        clazz.addImportedType(new FullyQualifiedJavaType(daoFieldType));
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
        //描述成员属性 的注解
        daoField.addAnnotation("@Autowired");
        //描述成员属性修饰符
        daoField.setVisibility(JavaVisibility.PRIVATE);
        clazz.addField(daoField);

        //描述 方法名
        Method getByIdParam = new Method("getByIdParam");
        annotate(getByIdParam, returnName, "根据编号查询" + className, "根据编号查询" + className, ("id 1 编号"));
        //方法注解
        getByIdParam.addAnnotation("@Override");
        //返回值
        FullyQualifiedJavaType methodReturnType = new FullyQualifiedJavaType(modelName);
        getByIdParam.setReturnType(methodReturnType);
        //设置参数列表
        Parameter parameter = new Parameter(new FullyQualifiedJavaType("Long"), "id");
        getByIdParam.addParameter(parameter);
        //方法体，逻辑代码
        getByIdParam.addBodyLine(modelName + " row = new " + modelName + "();");
        getByIdParam.addBodyLine("row.setId(id);");
        getByIdParam.addBodyLine("row.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ONE);");
        getByIdParam.addBodyLine(modelName + " p = " + daoFieldName + ".selectByParam(row);");
        getByIdParam.addBodyLine("return p;");
        //修饰符
        getByIdParam.setVisibility(JavaVisibility.PUBLIC);
        clazz.addMethod(getByIdParam);

        if (!generateHtml) {
            //描述 方法名
            Method getByIdInfo = new Method("getByIdInfo");
            annotate(getByIdInfo, returnName, "根据编号查询" + className, "根据编号查询" + className, ("id 1 编号"));
            //方法注解
            getByIdInfo.addAnnotation("@Override");
            //返回值
            methodReturnType = new FullyQualifiedJavaType("ResponseResult");
            getByIdInfo.setReturnType(methodReturnType);
            //设置参数列表
            parameter = new Parameter(new FullyQualifiedJavaType("Long"), "id");
            getByIdInfo.addParameter(parameter);
            //方法体，逻辑代码
            getByIdInfo.addBodyLine(modelName + " row = new " + modelName + "();");
            getByIdInfo.addBodyLine("row.setId(id);");
            getByIdInfo.addBodyLine("row.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ONE);");
            getByIdInfo.addBodyLine("row = " + daoFieldName + ".selectByParam(row);");
            getByIdInfo.addBodyLine(" return super.getResponseResult().success().setData(row);");
            //修饰符
            getByIdInfo.setVisibility(JavaVisibility.PUBLIC);
            clazz.addMethod(getByIdInfo);
        }
        //描述 方法名
        Method save = new Method("save");
        annotate(save, returnName, "新增" + className, "新增" + className, ("row 1 " + tableName));
        //方法注解
        save.addAnnotation("@Override");
        save.addAnnotation("@Transactional");
        //返回值
        methodReturnType = new FullyQualifiedJavaType("ResponseResult");
        save.setReturnType(methodReturnType);
        //设置参数列表
        parameter = new Parameter(new FullyQualifiedJavaType(modelName), "row");
        save.addParameter(parameter);
        //方法体，逻辑代码
        save.addBodyLine("SystemUser systemUser = getUser();");
        save.addBodyLine("Long userId = systemUser.getId();");
        save.addBodyLine("Date date = new Date();");
        save.addBodyLine("row.setOperaterTime(date);");
        save.addBodyLine("row.setCreateId(userId);");
        save.addBodyLine("row.setOperaterId(userId);");
        save.addBodyLine("row.setCreateTime(date);");
        save.addBodyLine("row.setOperaterStatus(NumeralUtil.POSITIVE_ONE);");
        save.addBodyLine(daoFieldName + ".insertSelective(row);");
        save.addBodyLine("return super.getResponseResult().success();");
        //修饰符
        save.setVisibility(JavaVisibility.PUBLIC);
        clazz.addMethod(save);


        //描述 方法名
        Method edit = new Method("edit");
        annotate(edit, returnName, "编辑" + className, "编辑" + className, ("row 1 " + tableName));
        //方法注解
        edit.addAnnotation("@Override");
        edit.addAnnotation("@Transactional");
        //返回值
        methodReturnType = new FullyQualifiedJavaType("ResponseResult");
        edit.setReturnType(methodReturnType);
        //设置参数列表
        parameter = new Parameter(new FullyQualifiedJavaType(modelName), "row");
        edit.addParameter(parameter);
        //方法体，逻辑代码
        edit.addBodyLine(modelName + " queryParam = " + daoFieldName + ".selectByPrimaryKey(row.getId());");
        edit.addBodyLine("if (queryParam == null) {");
        edit.addBodyLine("return super.getResponseResult().fail().setMsg(\"编辑信息不存在!\");");
        edit.addBodyLine("}");
        edit.addBodyLine("SystemUser systemUser = getUser();");
        edit.addBodyLine("Long userId = systemUser.getId();");
        edit.addBodyLine("Date date = new Date();");
        edit.addBodyLine("row.setOperaterTime(date);");
        edit.addBodyLine("row.setOperaterId(userId);");
        edit.addBodyLine("row.setOperaterStatus(NumeralUtil.POSITIVE_TWO);");
        edit.addBodyLine(daoFieldName + ".updateByPrimaryKeySelective(row);");
        edit.addBodyLine("return super.getResponseResult().success();");
        //修饰符
        edit.setVisibility(JavaVisibility.PUBLIC);
        clazz.addMethod(edit);


        //描述 方法名
        Method delete = new Method("delete");
        annotate(delete, returnName, "删除" + className, "删除" + className, ("id 1 编号"));
        //方法注解
        delete.addAnnotation("@Override");
        delete.addAnnotation("@Transactional");
        //返回值
        methodReturnType = new FullyQualifiedJavaType("ResponseResult");
        delete.setReturnType(methodReturnType);
        //设置参数列表
        parameter = new Parameter(new FullyQualifiedJavaType("Long"), "id");
        delete.addParameter(parameter);
        //方法体，逻辑代码
        delete.addBodyLine(modelName + " row = new " + modelName + "();");
        delete.addBodyLine("SystemUser systemUser = getUser();");
        delete.addBodyLine("Long userId = systemUser.getId();");
        delete.addBodyLine("Date date = new Date();");
        delete.addBodyLine("row.setId(id);");
        delete.addBodyLine("row.setOperaterTime(date);");
        delete.addBodyLine("row.setOperaterId(userId);");
        delete.addBodyLine("row.setOperaterStatus(NumeralUtil.NEGATIVE_ONE);");
        delete.addBodyLine(daoFieldName + ".delete(row);");
        delete.addBodyLine("return super.getResponseResult().success();");
        //修饰符
        delete.setVisibility(JavaVisibility.PUBLIC);
        clazz.addMethod(delete);

        //描述 方法名
        Method batchDeleteList = new Method("batchDeleteList");
        annotate(batchDeleteList, returnName, "批量删除" + className, "批量删除" + className, ("idList 1 编号集合"));
        //方法注解
        batchDeleteList.addAnnotation("@Override");
        batchDeleteList.addAnnotation("@Transactional");
        //返回值
        methodReturnType = new FullyQualifiedJavaType("ResponseResult");
        batchDeleteList.setReturnType(methodReturnType);
        //设置参数列表
        parameter = new Parameter(new FullyQualifiedJavaType("List<Long>"), "idList");
        batchDeleteList.addParameter(parameter);
        //方法体，逻辑代码
        batchDeleteList.addBodyLine(modelName + " row = new " + modelName + "();");
        batchDeleteList.addBodyLine("SystemUser systemUser = getUser();");
        batchDeleteList.addBodyLine("Long userId = systemUser.getId();");
        batchDeleteList.addBodyLine("Date date = new Date();");
        batchDeleteList.addBodyLine("row.setIdList(idList);");
        batchDeleteList.addBodyLine("row.setOperaterTime(date);");
        batchDeleteList.addBodyLine("row.setOperaterId(userId);");
        batchDeleteList.addBodyLine("row.setOperaterStatus(NumeralUtil.NEGATIVE_ONE);");
        batchDeleteList.addBodyLine("//" + daoFieldName + ".deleteList(row);");
        batchDeleteList.addBodyLine("return super.getResponseResult().success();");
        //修饰符
        batchDeleteList.setVisibility(JavaVisibility.PUBLIC);
        clazz.addMethod(batchDeleteList);

        //    public List<SystemButton> findByList(SystemButton record) {
        //        return systemButtonMapper.findByList(record);
        //    }

        //描述 方法名
        Method findByList = new Method("findByList");
        annotate(findByList, "java.util.List<" + model.getFullyQualifiedName() + ">", "查询" + className + "集合", "查询" + className + "集合", ("row 1 " + tableName));
        //方法注解
        findByList.addAnnotation("@Override");
        //返回值
        methodReturnType = new FullyQualifiedJavaType("List<" + modelName + ">");
        findByList.setReturnType(methodReturnType);
        //设置参数列表
        parameter = new Parameter(new FullyQualifiedJavaType(modelName), "row");
        findByList.addParameter(parameter);
        //方法体，逻辑代码
        findByList.addBodyLine("row.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ONE);");
        findByList.addBodyLine("List<" + modelName + "> list = " + daoFieldName + ".findByList(row);");
        findByList.addBodyLine("return list;");
        //修饰符
        findByList.setVisibility(JavaVisibility.PUBLIC);
        clazz.addMethod(findByList);


        //描述 方法名
        Method findParamPageList = new Method("findParamPageList");
        annotate(findParamPageList, returnName, "分页查询" + className + "列表", "分页查询" + className + "列表", ("row 1 " + tableName));
        //方法注解
        findParamPageList.addAnnotation("@Override");
        //返回值
        methodReturnType = new FullyQualifiedJavaType("ResponseResult");
        findParamPageList.setReturnType(methodReturnType);
        //设置参数列表
        parameter = new Parameter(new FullyQualifiedJavaType(modelName), "row");
        findParamPageList.addParameter(parameter);
        //方法体，逻辑代码
        findParamPageList.addBodyLine("row.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ONE);");
        findParamPageList.addBodyLine("int count = " + daoFieldName + ".findPageListCount(row);");
        findParamPageList.addBodyLine("List<" + modelName + "> list = " + daoFieldName + ".findPageList(row);");
        findParamPageList.addBodyLine("return super.getResponseResult().success().setData(list).setCount(count);");
        //修饰符
        findParamPageList.setVisibility(JavaVisibility.PUBLIC);
        clazz.addMethod(findParamPageList);

        GeneratedJavaFile gjf2 = new GeneratedJavaFile(clazz, serviceTargetProject, context.getJavaFormatter());
        return gjf2;
    }

    //公用注释
    private void annotate(Method method, String returnStr, String title, String description, String... params) {
        method.addAnnotation("/**");
        if (params != null && params.length > NumeralUtil.POSITIVE_ZERO) {
            for (String item : params) {
                method.addAnnotation(" * @param " + item);
            }
        }
        method.addAnnotation(" * @return " + returnStr);
        method.addAnnotation(" * @title " + title);
        method.addAnnotation(" * @description " + description);
        method.addAnnotation(" * @author " + author);
        method.addAnnotation(" * @datetime " + nowTime);
        method.addAnnotation(" * @version " + version);
        method.addAnnotation(" */");
    }

    // 生成controller类
    private GeneratedJavaFile generateController(IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType controller = new FullyQualifiedJavaType(controllerName);
        TopLevelClass clazz = new TopLevelClass(controller);
        //描述类的作用域修饰符
        clazz.setVisibility(JavaVisibility.PUBLIC);

        clazz.addImportedType(new FullyQualifiedJavaType("com.framework.common.annotation.QueryTarget"));
        clazz.addImportedType(new FullyQualifiedJavaType("com.framework.common.model.validation.ValidationGroup"));
        clazz.addImportedType(new FullyQualifiedJavaType("com.framework.common.response.ResponseResult"));
        clazz.addImportedType(new FullyQualifiedJavaType("com.framework.common.util.other.NumeralUtil"));
        clazz.addImportedType(new FullyQualifiedJavaType("com.framework.common.util.system.SystemUtil"));
        clazz.addImportedType(new FullyQualifiedJavaType("com.framework.common.util.other.NumeralUtil"));
        clazz.addImportedType(new FullyQualifiedJavaType("com.framework.web.base.BaseController"));
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.http.MediaType"));
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.security.access.prepost.PreAuthorize"));
        //添加@Controller注解，并引入相应的类
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Controller"));
        //添加@RequestMapping注解，并引入相应的类
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestMapping"));
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.GetMapping"));
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.PostMapping"));
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.DeleteMapping"));
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.validation.annotation.Validated"));
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestParam;"));
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestParam"));
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.ResponseBody"));
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.web.servlet.ModelAndView"));

        clazz.addImportedType(new FullyQualifiedJavaType("javax.validation.constraints.Min"));
        clazz.addImportedType(new FullyQualifiedJavaType("javax.validation.constraints.NotEmpty"));
        clazz.addImportedType(new FullyQualifiedJavaType("javax.validation.constraints.NotNull"));
        clazz.addImportedType(new FullyQualifiedJavaType("javax.validation.constraints.Size"));
        clazz.addImportedType(new FullyQualifiedJavaType("java.util.Date"));
        clazz.addImportedType(new FullyQualifiedJavaType("java.util.List"));

        //swagger
        clazz.addImportedType(new FullyQualifiedJavaType("io.swagger.annotations.Api"));
        clazz.addImportedType(new FullyQualifiedJavaType("io.swagger.annotations.ApiImplicitParam"));
        clazz.addImportedType(new FullyQualifiedJavaType("io.swagger.annotations.ApiImplicitParams"));
        clazz.addImportedType(new FullyQualifiedJavaType("io.swagger.annotations.ApiOperation"));

        //类注释
        clazz.addJavaDocLine("/**");
        clazz.addJavaDocLine(" * @author " + author);
        clazz.addJavaDocLine(" * @version " + version);
        clazz.addJavaDocLine(" * @className " + controllerName);
        clazz.addJavaDocLine(" * @description " + className + "请求控制类");
        clazz.addJavaDocLine(" * @datetime " + nowTime);
        clazz.addJavaDocLine(" */");

        if (swagger) {
            clazz.addAnnotation("@Api(tags = \"" + className + "\", description = \"" + className + "\")");
        }
//        clazz.addAnnotation("@QueryTarget");
        clazz.addAnnotation("@Validated");
        clazz.addAnnotation("@Controller");
        clazz.addAnnotation("@RequestMapping(\"" + controllerPath + HumpOrLineUtil.humpToSlash(lowercaseInitialModelName) + "\")");
        //添加@Api注解，并引入相应的类
//        String controllerSimpleName = controllerName.substring(controllerName.lastIndexOf(".") + 1);

        //引入controller的父类和model，并添加泛型
        if (stringHasValue(superController)) {
            clazz.addImportedType(superController);
            clazz.addImportedType(model);
            FullyQualifiedJavaType superInterfac = new FullyQualifiedJavaType(superController);
            clazz.setSuperClass(superInterfac);
        }

        //引入Service
        FullyQualifiedJavaType service = new FullyQualifiedJavaType(serviceName);
        clazz.addImportedType(service);
        //添加Service成员变量
//        String pathName = StringUtils.uncapitalize(modelName);
        String serviceImplFieldName = StringUtils.uncapitalize(serviceImplName.substring(serviceImplName.lastIndexOf(".") + 1));
        Field daoField = new Field(serviceImplFieldName, new FullyQualifiedJavaType(serviceName));
        daoField.addJavaDocLine("private String path = \"" + pathPrefix + lowercaseInitialModelName + "/\";");
        clazz.addImportedType(new FullyQualifiedJavaType(serviceName));

        //描述成员属性 的注解
        daoField.addAnnotation("@Autowired");
        //描述成员属性修饰符
        daoField.setVisibility(JavaVisibility.PRIVATE);
        clazz.addField(daoField);

//    /**
//     * @return org.springframework.web.servlet.ModelAndView
//     * @titel 分页页面跳转
//     * @description 分页页面跳转
//     * @author 邋遢龘鵺
//     * @datetime 2019/12/14 18:20
//     */
//    @RequestMapping("/page/list")
//    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_BACKSTAGE_BLACK_LIST_IP_MANAGEMENT')")
//    public ModelAndView pageList() {
//        return new ModelAndView(path + "backstageBlackListIpList");
//    }
        //返回类型
        FullyQualifiedJavaType methodReturnType = null;
        Parameter parameter = null;
        if (generateHtml) {
            String modelAndView = "ModelAndView";
            String modelAndViewPath = "org.springframework.web.servlet.ModelAndView";
            //描述 方法名
            Method pageList = new Method("pageList");
            annotate(pageList, modelAndViewPath, "分页页面跳转", "分页页面跳转");
            //方法注解
            pageList.addAnnotation("@GetMapping(\"/page/list\")");
            pageList.addAnnotation("@PreAuthorize(\"hasPermission('\" + SystemUtil.SYSTEM_MENU_NAME + \"','" + tableCode +
                    "_LIST_MANAGEMENT" +
                    "')\")");
            //返回类型
            methodReturnType = new FullyQualifiedJavaType(modelAndView);
            pageList.setReturnType(methodReturnType);
            //方法体，逻辑代码
            pageList.addBodyLine(modelAndView + " mv = new " + modelAndView + "(path + \"" + lowercaseInitialModelName + "List\");");
            pageList.addBodyLine("return mv;");
            //修饰符
            pageList.setVisibility(JavaVisibility.PUBLIC);
            clazz.addMethod(pageList);


            //描述 方法名
            Method getAdd = new Method("getAdd");
            annotate(getAdd, modelAndViewPath, "新增页面跳转", "新增页面跳转");

            //方法注解
            getAdd.addAnnotation("@GetMapping(\"/get/add\")");
            getAdd.addAnnotation("@PreAuthorize(\"hasPermission('\" + SystemUtil.SYSTEM_BUTTON_NAME + \"','" + tableCode +
                    "_LIST_MANAGEMENT:add" +
                    "')\")");
            //返回类型
            methodReturnType = new FullyQualifiedJavaType(modelAndView);
            getAdd.setReturnType(methodReturnType);
            //方法体，逻辑代码
            getAdd.addBodyLine(modelAndView + " mv = new " + modelAndView + "(path + \"" + lowercaseInitialModelName + "Add\");");
            getAdd.addBodyLine("return mv;");
            //修饰符
            getAdd.setVisibility(JavaVisibility.PUBLIC);
            clazz.addMethod(getAdd);


            //描述 方法名
            Method getEdit = new Method("getEdit");
            annotate(getEdit, modelAndViewPath, "编辑页面跳转", "编辑页面跳转", "id 1 编号");
            //设置参数列表
            parameter = new Parameter(new FullyQualifiedJavaType("@NotNull(message = \"请选择" + className + "\") @Min(value = NumeralUtil.POSITIVE_ONE, " +
                    "message = \"" + className + "不存在\") Long"), "id");
            getEdit.addParameter(parameter);
            //方法注解
            getEdit.addAnnotation("@GetMapping(\"/get/edit\")");
            getEdit.addAnnotation("@PreAuthorize(\"hasPermission('\" + SystemUtil.SYSTEM_BUTTON_NAME + \"','" + tableCode +
                    "_LIST_MANAGEMENT:edit" +
                    "')\")");
            //返回类型
            methodReturnType = new FullyQualifiedJavaType(modelAndView);
            getEdit.setReturnType(methodReturnType);
            //方法体，逻辑代码
            getEdit.addBodyLine(modelAndView + " mv = new " + modelAndView + "(path + \"" + lowercaseInitialModelName + "Edit\");");
            getEdit.addBodyLine("mv.addObject(\"p\", " + serviceImplFieldName + ".getByIdParam(id));");
            getEdit.addBodyLine("return mv;");
            //修饰符
            getEdit.setVisibility(JavaVisibility.PUBLIC);
            clazz.addMethod(getEdit);


            //描述 方法名
            Method getView = new Method("getView");
            annotate(getView, modelAndViewPath, "查看页面跳转", "查看页面跳转", "id 1 编号");
            //设置参数列表
            getView.addParameter(parameter);
            //方法注解
            getView.addAnnotation("@GetMapping(\"/get/view\")");
            getView.addAnnotation("@PreAuthorize(\"hasPermission('\" + SystemUtil.SYSTEM_BUTTON_NAME + \"','" + tableCode +
                    "_LIST_MANAGEMENT:view" +
                    "')\")");
            //返回类型
            methodReturnType = new FullyQualifiedJavaType(modelAndView);
            getView.setReturnType(methodReturnType);
            //方法体，逻辑代码
            getView.addBodyLine(modelAndView + " mv = new " + modelAndView + "(path + \"" + lowercaseInitialModelName + "View\");");
            getView.addBodyLine("mv.addObject(\"p\", " + serviceImplFieldName + ".getByIdParam(id));");
            getView.addBodyLine("return mv;");
            //修饰符
            getView.setVisibility(JavaVisibility.PUBLIC);
            clazz.addMethod(getView);
        }

        //描述 方法名
        Method findPageList = new Method("findPageList");
        annotate(findPageList, returnName, "分页查询", "分页查询", "row 1 " + tableName);
        //方法注解
        findPageList.addAnnotation("//@Validated(value = {ValidationGroup.formQuery.class})");
        findPageList.addAnnotation("@ResponseBody");
        findPageList.addAnnotation("@QueryTarget");
        findPageList.addAnnotation("@GetMapping(\"/find/page/list\")");
        if (generateHtml) {
            findPageList.addAnnotation("@PreAuthorize(\"hasPermission('\" + SystemUtil.SYSTEM_MENU_NAME + \"','" + tableCode +
                    "_LIST_MANAGEMENT:find" +
                    "')\")");
        }
        if (swagger) {
            findPageList.addAnnotation("@ApiImplicitParams({\n" +
                    "            @ApiImplicitParam(name = \"page\", value = \"页码\", required = true, paramType = \"query\", example = \"1\"),\n" +
                    "            @ApiImplicitParam(name = \"limit\", value = \"条数\", required = true, paramType = \"query\", example = \"10\"),\n" +
                    "            @ApiImplicitParam(name = \"startTime\", value = \"开始时间\", required = false, paramType = \"query\", example = \"2023-01-01 00:00:00\"),\n" +
                    "            @ApiImplicitParam(name = \"endTime\", value = \"结束时间\", required = false, paramType = \"query\", example = \"2023-01-30 23:59:59\")\n" +
                    "    })");
            findPageList.addAnnotation("@ApiOperation(value = \"分页查询\", httpMethod = \"GET\", produces = \"application/json\", consumes = \"text/html\", response = ResponseResult.class)");
        }
        //返回值
        methodReturnType = new FullyQualifiedJavaType("ResponseResult");
        findPageList.setReturnType(methodReturnType);
        //设置参数列表
        parameter = new Parameter(new FullyQualifiedJavaType("@Validated(value = {ValidationGroup.formPageQuery.class}) " + modelName), "row");
        findPageList.addParameter(parameter);
        //方法体，逻辑代码
        findPageList.addBodyLine("return " + serviceImplFieldName + ".findParamPageList(row);");
        //修饰符
        findPageList.setVisibility(JavaVisibility.PUBLIC);
        clazz.addMethod(findPageList);


        if (!generateHtml) {
            //描述 方法名
            Method getByIdInfo = new Method("getByIdInfo");
            annotate(getByIdInfo, returnName, "根据编号查询信息", "根据编号查询信息", "id 1 编号");
            //设置参数列表
            //方法注解
            getByIdInfo.addAnnotation("@ResponseBody");
            getByIdInfo.addAnnotation("@GetMapping(\"/get/by/id/info\")");
            if (generateHtml) {
                getByIdInfo.addAnnotation("@PreAuthorize(\"hasPermission('\" + SystemUtil.SYSTEM_BUTTON_NAME + \"','" + tableCode +
                        "_LIST_MANAGEMENT:get" +
                        "')\")");
            }
            if (swagger) {
                getByIdInfo.addAnnotation("@ApiOperation(value = \"根据编号查询信息\", httpMethod = \"GET\", produces = \"application/json\", consumes = \"text/html\", response = ResponseResult.class)");
                getByIdInfo.addAnnotation("@ApiImplicitParams({\n" +
                        "            @ApiImplicitParam(name = \"id\", value = \"编号\", required = true, paramType = \"query\", example = \"1-\" + Long.MAX_VALUE),\n" +
                        "    })");
            }
            //返回值
            methodReturnType = new FullyQualifiedJavaType("ResponseResult");
            getByIdInfo.setReturnType(methodReturnType);
            //设置参数列表
            parameter = new Parameter(new FullyQualifiedJavaType("@NotNull(message = \"请选择" + className + "\") @Min(value = NumeralUtil.POSITIVE_ONE, " +
                    "message = \"" + className + "不存在\") Long"), "id");
            getByIdInfo.addParameter(parameter);
            //方法体，逻辑代码
            getByIdInfo.addBodyLine("return " + serviceImplFieldName + ".getByIdInfo(id);");
            //修饰符
            getByIdInfo.setVisibility(JavaVisibility.PUBLIC);
            clazz.addMethod(getByIdInfo);
        }


        //描述 方法名
        Method save = new Method("save");
        annotate(save, returnName, "新增", "新增", "row 1 " + tableName);
        //方法注解
        save.addAnnotation("@ResponseBody");
        save.addAnnotation("@PostMapping(\"/save\")");
        if (generateHtml) {
            save.addAnnotation("@PreAuthorize(\"hasPermission('\" + SystemUtil.SYSTEM_BUTTON_NAME + \"','" + tableCode +
                    "_LIST_MANAGEMENT:add" +
                    "')\")");
        }
        if (swagger) {
            save.addAnnotation("@ApiOperation(value = \"新增\", httpMethod = \"POST\", produces = \"application/json\", consumes = \"application/json\", response = ResponseResult.class)");
        }
        //返回值
        methodReturnType = new FullyQualifiedJavaType("ResponseResult");
        save.setReturnType(methodReturnType);
        //设置参数列表
        parameter = new Parameter(new FullyQualifiedJavaType("@Validated(value = {ValidationGroup.formAdd.class}) " + modelName), "row");
        save.addParameter(parameter);
        //方法体，逻辑代码
        save.addBodyLine("return " + serviceImplFieldName + ".save(row);");
        //修饰符
        save.setVisibility(JavaVisibility.PUBLIC);
        clazz.addMethod(save);


        //描述 方法名
        Method edit = new Method("edit");
        annotate(edit, returnName, "编辑", "编辑", "row 1 " + tableName);
        //方法注解
        edit.addAnnotation("@ResponseBody");
        edit.addAnnotation("@PostMapping(\"/edit\")");
        if (generateHtml) {
            edit.addAnnotation("@PreAuthorize(\"hasPermission('\" + SystemUtil.SYSTEM_BUTTON_NAME + \"','" + tableCode +
                    "_LIST_MANAGEMENT:edit" +
                    "')\")");
        }
        if (swagger) {
            edit.addAnnotation("@ApiOperation(value = \"修改\", httpMethod = \"POST\", produces = \"application/json\", consumes = \"application/json\", response = ResponseResult.class)");
        }
        //返回值
        methodReturnType = new FullyQualifiedJavaType("ResponseResult");
        edit.setReturnType(methodReturnType);
        //设置参数列表
        parameter = new Parameter(new FullyQualifiedJavaType("@Validated(value = {ValidationGroup.formEdit.class}) " + modelName), "row");
        edit.addParameter(parameter);
        //方法体，逻辑代码
        edit.addBodyLine("return " + serviceImplFieldName + ".edit(row);");
        //修饰符
        edit.setVisibility(JavaVisibility.PUBLIC);
        clazz.addMethod(edit);


        //描述 方法名
        Method batchDel = new Method("batchDel");
        annotate(batchDel, returnName, "批量删除", "批量删除", "idList 1 编号集合");
        //方法注解
        batchDel.addAnnotation("@ResponseBody");
        batchDel.addAnnotation("@PostMapping(\"/batch/del\")");
        if (generateHtml) {
            batchDel.addAnnotation("@PreAuthorize(\"hasPermission('\" + SystemUtil.SYSTEM_BUTTON_NAME + \"','" + tableCode +
                    "_LIST_MANAGEMENT:batchDel" +
                    "')\")");
        }
        if (swagger) {
            batchDel.addAnnotation("@ApiImplicitParams({\n" +
                    "            @ApiImplicitParam(name = \"idList[]\", value = \"编号数组\", required = true, paramType = \"query\", example = \"[1,2,3]\"),\n" +
                    "    })");
            batchDel.addAnnotation("@ApiOperation(value = \"批量删除\", httpMethod = \"DELETE\", produces = \"application/json\", consumes = \"text/html\", response = ResponseResult.class)");
        }
        //返回值
        methodReturnType = new FullyQualifiedJavaType("ResponseResult");
        batchDel.setReturnType(methodReturnType);
        //设置参数列表
        parameter = new Parameter(new FullyQualifiedJavaType("@NotEmpty(message = \"请选择" + className + "\")" +
                " @Size(min = NumeralUtil.POSITIVE_ONE, message = \"" + className + "不存在\")" +
                " @RequestParam(value = \"idList[]\") List<Long>"), "idList");
        batchDel.addParameter(parameter);
        //方法体，逻辑代码
        batchDel.addBodyLine("return " + serviceImplFieldName + ".batchDeleteList(idList);");
        //修饰符
        batchDel.setVisibility(JavaVisibility.PUBLIC);
        clazz.addMethod(batchDel);


        //描述 方法名
        Method del = new Method("del");
        annotate(del, returnName, "删除", "删除", "id 1 编号");
        //方法注解
        del.addAnnotation("@ResponseBody");
        del.addAnnotation("@DeleteMapping(\"/del\")");
        if (generateHtml) {
            del.addAnnotation("@PreAuthorize(\"hasPermission('\" + SystemUtil.SYSTEM_BUTTON_NAME + \"','" + tableCode +
                    "_LIST_MANAGEMENT:del" +
                    "')\")");
        }
        if (swagger) {
            del.addAnnotation("@ApiImplicitParams({\n" +
                    "            @ApiImplicitParam(name = \"id\", value = \"编号\", required = true, paramType = \"query\", example = \"1-\" + Long.MAX_VALUE),\n" +
                    "    })");
            del.addAnnotation("@ApiOperation(value = \"删除\", httpMethod = \"DELETE\", produces = \"application/json\", consumes = \"text/html\", response = ResponseResult.class)");
        }
        //返回值
        methodReturnType = new FullyQualifiedJavaType("ResponseResult");
        del.setReturnType(methodReturnType);
        //设置参数列表
        parameter = new Parameter(new FullyQualifiedJavaType("@NotNull(message = \"请选择" + className + "\") " +
                "@Min(value = NumeralUtil.POSITIVE_ONE, message = \"" + className + "不存在\") " +
                "@RequestParam(value = \"id\") Long"), "id");
        del.addParameter(parameter);
        //方法体，逻辑代码
        del.addBodyLine("return " + serviceImplFieldName + ".delete(id);");
        //修饰符
        del.setVisibility(JavaVisibility.PUBLIC);
        clazz.addMethod(del);


        GeneratedJavaFile gjf2 = new GeneratedJavaFile(clazz, controllerTargetProject, context.getJavaFormatter());
        return gjf2;
    }


//    private String firstCharToLowCase(String str) {
//        char[] chars = new char[1];
//        //String str="ABCDE1234";
//        chars[0] = str.charAt(0);
//        String temp = new String(chars);
//        if (chars[0] >= 'A' && chars[0] <= 'Z') {
//            return str.replaceFirst(temp, temp.toLowerCase());
//        }
//        return str;
//    }

}
