package com.framework.mapper.generator.godeGenerator.custom;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

import java.util.List;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.mapper.generator.godeGenerator.custom
 * @description 自定义插件
 * @date 2023/3/4 14:03
 */
public class CustomPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    //https://www.cnblogs.com/se7end/p/9293755.html
    //

    /**
     * @param document          1 文件对象
     * @param introspectedTable 2 表对象
     * @return boolean
     * @titel 生成 xml 文件
     * @description 生成 xml 文件
     * @author 龘鵺
     * @datetime 2023/3/4 19:56
     */
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        AbstractXmlElementGenerator elementGenerator = new CustomAbstractXmlElementGenerator();
        elementGenerator.setContext(context);
        elementGenerator.setIntrospectedTable(introspectedTable);
        elementGenerator.addElements(document.getRootElement());
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    /**
     * @param interfaze         1 文件对象
     * @param introspectedTable 2 表对象
     * @return boolean
     * @titel 生成 mapper 类
     * @description 生成 mapper 类
     * @author 龘鵺
     * @datetime 2023/3/4 19:55
     */
    @Override
    public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {
        AbstractJavaMapperMethodGenerator methodGenerator = new CustomJavaMapperMethodGenerator();
        methodGenerator.setContext(context);
        methodGenerator.setIntrospectedTable(introspectedTable);
        methodGenerator.addInterfaceElements(interfaze);
        return super.clientGenerated(interfaze, introspectedTable);
    }
}
