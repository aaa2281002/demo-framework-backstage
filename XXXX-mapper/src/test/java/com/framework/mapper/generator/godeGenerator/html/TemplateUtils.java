package com.framework.mapper.generator.godeGenerator.html;

import freemarker.template.Template;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

/**
 * @author 龘鵺
 * @ClassName com.framework.mapper.generator.godeGenerator.html
 * @description 渲染模版
 * @date 2023/3/10 11:06
 * @Version 1.0
 */
public class TemplateUtils {
    /**
     * 获取模板渲染后的内容
     *
     * @param content   模板内容
     * @param dataModel 数据模型
     */
    public static String getContent(String content, Map<String, Object> dataModel, String templateName) {
        if (dataModel.isEmpty()) {
            return content;
        }

        StringReader reader = new StringReader(content);
        StringWriter sw = new StringWriter();
        try {
            // 渲染模板
            Template template = new Template(templateName, reader, null, "utf-8");
            template.process(dataModel, sw);
            sw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                sw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        content = sw.toString();

        return content;
    }
}
