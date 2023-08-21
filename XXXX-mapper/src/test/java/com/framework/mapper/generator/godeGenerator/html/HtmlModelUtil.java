package com.framework.mapper.generator.godeGenerator.html;

import com.framework.common.util.other.NumeralUtil;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 龘鵺
 * @ClassName com.framework.mapper.generator.godeGenerator.html
 * @description 参数工具类
 * @date 2023/3/10 11:13
 * @Version 1.0
 */
public class HtmlModelUtil {
    private static Map<String, Integer> addFilterMap = new HashMap<String, Integer>(NumeralUtil.POSITIVE_EIGHT);
    private static Map<String, Integer> editFilterMap = new HashMap<String, Integer>(NumeralUtil.POSITIVE_EIGHT);

    static {
        editFilterMap.put("id", NumeralUtil.POSITIVE_ZERO);
        addFilterMap.put("createId", NumeralUtil.POSITIVE_ZERO);
        addFilterMap.put("createTime", NumeralUtil.POSITIVE_ZERO);
        addFilterMap.put("operaterId", NumeralUtil.POSITIVE_ZERO);
        addFilterMap.put("operaterTime", NumeralUtil.POSITIVE_ZERO);
        addFilterMap.put("operaterStatus", NumeralUtil.POSITIVE_ZERO);
    }

    public static Map<String, Object> getModel(IntrospectedTable introspectedTable) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("currencySymbol", "$");//ftl页面符号冲突。所以这里定义字符串变量传递此符号
        List<Map<String, Object>> editList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> addList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> viewList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> textList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> listList = new ArrayList<Map<String, Object>>();
        boolean isBigDecimal = false;
        boolean isText = false;
        for (IntrospectedColumn item : introspectedTable.getBaseColumns()) {
            Map<String, Object> modelMap = new HashMap<>();
            modelMap.put("name", item.getRemarks());
            modelMap.put("code", item.getJavaProperty());
            modelMap.put("length", item.getLength());
            modelMap.put("type", item.getFullyQualifiedJavaType().getShortName());
            if (item.getFullyQualifiedJavaType().getShortName().equals("BigDecimal")) {
                isBigDecimal = true;
            }
            if (addFilterMap.containsKey(item.getJavaProperty())) {
                viewList.add(modelMap);
                continue;
            }
            if (editFilterMap.containsKey(item.getJavaProperty())) {
                editList.add(modelMap);
                continue;
            }
            addList.add(modelMap);
            editList.add(modelMap);
            viewList.add(modelMap);
            listList.add(modelMap);
        }
        for (IntrospectedColumn item : introspectedTable.getBLOBColumns()) {
            isText = true;
            Map<String, Object> modelMap = new HashMap<>();
            modelMap.put("name", item.getRemarks());
            modelMap.put("code", item.getJavaProperty());
            modelMap.put("length", item.getLength());
            modelMap.put("type", item.getJdbcTypeName());
            if (addFilterMap.containsKey(item.getJavaProperty())) {
                viewList.add(modelMap);
                continue;
            }
            if (editFilterMap.containsKey(item.getJavaProperty())) {
                editList.add(modelMap);
                continue;
            }
            addList.add(modelMap);
            editList.add(modelMap);
            viewList.add(modelMap);
            textList.add(modelMap);
        }
        map.put("addList", addList);
        map.put("editList", editList);
        map.put("viewList", viewList);
        map.put("textList", textList);
        map.put("listList", listList);
        map.put("isBigDecimal", isBigDecimal);
        map.put("isText", isText);
        return map;
    }
}
