package com.framework.mapper.generator.godeGenerator.html;

import com.framework.mapper.generator.godeGenerator.MybatisGeneratorMain;
import org.springframework.util.StreamUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 龘鵺
 * @ClassName com.framework.mapper.generator.godeGenerator.html
 * @description html生成器配置
 * @date 2023/3/10 10:31
 * @Version 1.0
 */
public class HtmlGeneratorConfig {
    public String getPath() {
        return this.getClass().getResource("/").getPath();
    }

    public Map<String, String> getGeneratorConfig() {
        String path = new MybatisGeneratorMain().getPath();
        path = path.substring(1);
        path = path.substring(0, path.lastIndexOf("target"));
        String add = path + "src/test/resources/generator/Add.html.ftl";
        String edit = path + "src/test/resources/generator/Edit.html.ftl";
        String view = path + "src/test/resources/generator/View.html.ftl";
        String list = path + "src/test/resources/generator/List.html.ftl";
        Map<String, String> map = new HashMap<String, String>();
        try {
            // 读取模板配置文件
            // 模板文件
            InputStream viewTemplate = new FileInputStream(view);
            // 读取模板内容
            String viewTemplateContent = StreamUtils.copyToString(viewTemplate, StandardCharsets.UTF_8);
            map.put("view", viewTemplateContent);
            // 模板文件
            InputStream editTemplate = new FileInputStream(edit);
            // 读取模板内容
            String editTemplateContent = StreamUtils.copyToString(editTemplate, StandardCharsets.UTF_8);
            map.put("edit", editTemplateContent);
            // 模板文件
            InputStream addTemplate = new FileInputStream(add);
            // 读取模板内容
            String addTemplateContent = StreamUtils.copyToString(addTemplate, StandardCharsets.UTF_8);
            map.put("add", addTemplateContent);
            // 模板文件
            InputStream listTemplate = new FileInputStream(list);
            // 读取模板内容
            String listTemplateContent = StreamUtils.copyToString(listTemplate, StandardCharsets.UTF_8);
            map.put("list", listTemplateContent);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
