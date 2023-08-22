package com.framework.mapper.generator.godeGenerator.custom;

import com.framework.common.util.other.NumeralUtil;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.ListUtilities;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.mapper.generator.godeGenerator.custom
 * @description 自定义xml配置文件类
 * @date 2023/3/4 14:05
 */
public class CustomAbstractXmlElementGenerator extends AbstractXmlElementGenerator {
    private Map<String, Integer> jdbcTypeMap = new HashMap<String, Integer>(NumeralUtil.POSITIVE_TWO);
    private Map<String, Integer> columnMap = new HashMap<String, Integer>(NumeralUtil.POSITIVE_EIGHT);
    private Map<String, Integer> insertFilterMap = new HashMap<String, Integer>(NumeralUtil.POSITIVE_EIGHT);
    private Map<String, Integer> updateFilterMap = new HashMap<String, Integer>(NumeralUtil.POSITIVE_EIGHT);
    private Map<String, Integer> filterMap = new HashMap<String, Integer>(NumeralUtil.POSITIVE_EIGHT);
    private Map<String, Integer> deleteMap = new HashMap<String, Integer>(NumeralUtil.POSITIVE_EIGHT);

    public CustomAbstractXmlElementGenerator() {
        jdbcTypeMap.put("VARCHAR", NumeralUtil.POSITIVE_ONE);
        jdbcTypeMap.put("LONGVARCHAR", NumeralUtil.POSITIVE_ONE);
        columnMap.put("id", NumeralUtil.POSITIVE_ZERO);
        insertFilterMap.put("id", NumeralUtil.POSITIVE_ZERO);
        updateFilterMap.put("id", NumeralUtil.POSITIVE_ZERO);
        updateFilterMap.put("createId", NumeralUtil.POSITIVE_ZERO);
        updateFilterMap.put("createTime", NumeralUtil.POSITIVE_ZERO);
        filterMap.put("createId", NumeralUtil.POSITIVE_ZERO);
        filterMap.put("createTime", NumeralUtil.POSITIVE_ZERO);
        filterMap.put("operaterId", NumeralUtil.POSITIVE_ZERO);
        filterMap.put("operaterTime", NumeralUtil.POSITIVE_ZERO);
        filterMap.put("operaterStatus", NumeralUtil.POSITIVE_ZERO);
//        filterMap.put("LONGVARCHAR", NumeralUtil.POSITIVE_ZERO);
        deleteMap.put("operaterId", NumeralUtil.POSITIVE_ZERO);
        deleteMap.put("operaterTime", NumeralUtil.POSITIVE_ZERO);
        deleteMap.put("operaterStatus", NumeralUtil.POSITIVE_ZERO);
    }

    @Override
    public void addElements(XmlElement parentElement) {
        StringBuilder sb = new StringBuilder();

        // 公用 select
        sb.append("    select ");
        sb.append("\n        <include refid=\"Base_Column_List\" /> ");
        sb.append("\n        , ");
        sb.append("\n        <include refid=\"BaseMapper.publicField\"/> ");
        sb.append("\n        , cuser.LOGIN_NAME createUserName, ouser.LOGIN_NAME operaterUserName");
        sb.append("\n        from ");
        sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        sb.append(" p ");
        sb.append("\n        LEFT JOIN `system_user` cuser on p.CREATE_ID = cuser.ID and cuser.OPERATER_STATUS > -1");
        sb.append("\n        LEFT JOIN `system_user` ouser on p.OPERATER_ID = ouser.ID and ouser.OPERATER_STATUS > -1");
        TextElement selectText = new TextElement(sb.toString());

        XmlElement columnSql = new XmlElement("sql");
        parentElement.addElement(columnSql);

        // 公用include
        XmlElement where = new XmlElement("where");
        XmlElement include = new XmlElement("include");
        include.addAttribute(new Attribute("refid", "conditionSql"));
        where.addElement(include);

        // 生成新增
        insertSelective(parentElement);

        // 生成批量新增
        insertList(parentElement);

        // 生成修改
        updateByPrimaryKeySelective(parentElement);

        // 生成批量修改
        updateList(parentElement);

        // 生成 delete
        delete(parentElement);

        // 增加 selectByParam
        selectByParam(parentElement, selectText, where);
        selectByPrimaryKey(parentElement, selectText);

        // 增加 findPageList
        findPageList(parentElement, selectText, where, sb);

        // 增加 findPageListCount
        findPageListCount(parentElement, where);

        // 生成 findByList
        findByList(parentElement, selectText, where);

        // 增加 fuzzyConditionSql
        sb.setLength(0);
        XmlElement fuzzyConditionSql = new XmlElement("sql");
        fuzzyConditionSql.addAttribute(new Attribute("id", "fuzzyConditionSql"));
////        //在这里添加where条件
//        XmlElement selectTrimElement = new XmlElement("trim"); //设置trim标签
////        selectTrimElement.addAttribute(new Attribute("prefix", "WHERE"));
//        selectTrimElement.addAttribute(new Attribute("prefixOverrides", "AND | OR")); //添加where和and


        XmlElement searchConditionSql = new XmlElement("sql");
        searchConditionSql.addAttribute(new Attribute("id", "searchConditionSql"));
//        XmlElement searchSelectTrimElement = new XmlElement("trim"); //设置trim标签
////        selectTrimElement.addAttribute(new Attribute("prefix", "WHERE"));
//        searchSelectTrimElement.addAttribute(new Attribute("prefixOverrides", "AND | OR")); //添加where和and
//        //在这里添加if条件
        XmlElement searchSelectIfElement = new XmlElement("if"); //设置trim标签
//        selectTrimElement.addAttribute(new Attribute("prefix", "WHERE"));
        searchSelectIfElement.addAttribute(new Attribute("test", "search != null and search != ''")); //添加where和and
        searchSelectIfElement.addElement(new TextElement("AND ("));
        for (IntrospectedColumn introspectedColumn : introspectedTable.getAllColumns()) {
            if (filterMap.containsKey(introspectedColumn.getJavaProperty())
                    || columnMap.containsKey(introspectedColumn.getJavaProperty())) {
                continue;
            }
            XmlElement selectNotNullElement = new XmlElement("if");
            sb.setLength(0);
            sb.append("fuzzyType == '");
            sb.append(introspectedColumn.getJavaProperty());
            sb.append("'");
            selectNotNullElement.addAttribute(new Attribute("test", sb.toString()));
            sb.setLength(0);
            // 添加and
            sb.append(" and ");
            // 添加别名t
            sb.append("p.");
            sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
            // 添加等号
            sb.append(" LIKE CONCAT('%', #{fuzzyValue}, '%')");
            selectNotNullElement.addElement(new TextElement(sb.toString()));

            sb.setLength(0);
            if (searchSelectIfElement.getElements().size() > 1) {
                // 添加or
                sb.append(" or ");
            } else {
                sb.append(" ");
            }
            // 添加别名t
            sb.append("p.");
            sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
            // 添加等号
            sb.append(" LIKE CONCAT('%', #{search}, '%')");
//            selectTrimElement.addElement(selectNotNullElement);
            fuzzyConditionSql.addElement(selectNotNullElement);
            searchSelectIfElement.addElement(new TextElement(sb.toString()));
        }
//        fuzzyConditionSql.addElement(selectTrimElement);
        searchSelectIfElement.addElement(new TextElement(")"));
//        searchSelectTrimElement.addElement(searchSelectIfElement);
//        searchConditionSql.addElement(searchSelectTrimElement);
        searchConditionSql.addElement(searchSelectIfElement);
        parentElement.addElement(fuzzyConditionSql);
        parentElement.addElement(searchConditionSql);

        // 增加 conditionSql
        sb.setLength(0);
        XmlElement conditionSql = new XmlElement("sql");
        conditionSql.addAttribute(new Attribute("id", "conditionSql"));
//        //在这里添加where条件
//        XmlElement selectTrimElement = new XmlElement("trim"); //设置trim标签
////        selectTrimElement.addAttribute(new Attribute("prefix", "WHERE"));
//        selectTrimElement.addAttribute(new Attribute("prefixOverrides", "AND | OR")); //添加where和and

        //关键字模糊搜索
        XmlElement vagueConditionSql = new XmlElement("sql");
        vagueConditionSql.addAttribute(new Attribute("id", "vagueConditionSql"));
        //在这里添加where条件
//        XmlElement vagueSelectTrimElement = new XmlElement("trim"); //设置trim标签
////        selectTrimElement.addAttribute(new Attribute("prefix", "WHERE"));
//        vagueSelectTrimElement.addAttribute(new Attribute("prefixOverrides", "AND | OR")); //添加where和and

//        XmlElement includeBaseMapperPublicWhere = new XmlElement("include");
//        includeBaseMapperPublicWhere.addAttribute(new Attribute("refid", "BaseMapper.publicWhere"));
//        XmlElement includeBaseMapperPublicWhereCreateTime = new XmlElement("include");
//        includeBaseMapperPublicWhereCreateTime.addAttribute(new Attribute("refid", "BaseMapper.publicWhereCreateTime"));


//        selectTrimElement.addElement(includeBaseMapperPublicWhere);
//        selectTrimElement.addElement(includeBaseMapperPublicWhereCreateTime);

//        conditionSql.addElement(includeBaseMapperPublicWhere);
//        conditionSql.addElement(includeBaseMapperPublicWhereCreateTime);

        XmlElement publicWhereId = new XmlElement("include");
        publicWhereId.addAttribute(new Attribute("refid", "BaseMapper.publicWhereId"));
        XmlElement publicWhereStatus = new XmlElement("include");
        publicWhereStatus.addAttribute(new Attribute("refid", "BaseMapper.publicWhereStatus"));
        XmlElement publicWhereUserId = new XmlElement("include");
        publicWhereUserId.addAttribute(new Attribute("refid", "BaseMapper.publicWhereUserId"));
        XmlElement publicWhereCreateTime = new XmlElement("include");
        publicWhereCreateTime.addAttribute(new Attribute("refid", "BaseMapper.publicWhereCreateTime"));

        if (introspectedTable.getPrimaryKeyColumns() != null && introspectedTable.getPrimaryKeyColumns().size() > NumeralUtil.POSITIVE_ZERO) {
            conditionSql.addElement(publicWhereId);
            vagueConditionSql.addElement(publicWhereId);
        }

        boolean isStatus = false;
        for (IntrospectedColumn introspectedColumn : introspectedTable.getAllColumns()) {
            if (columnMap.containsKey(introspectedColumn.getJavaProperty())) {
                continue;
            }
            if (filterMap.containsKey(introspectedColumn.getJavaProperty())) {
                isStatus = true;
                continue;
            }
            XmlElement selectNotNullElement = new XmlElement("if"); //
            XmlElement vagueNotNullElement = new XmlElement("if"); //
            sb.setLength(0);
            sb.append(introspectedColumn.getJavaProperty());
            sb.append(" != null");
            if (jdbcTypeMap.containsKey(introspectedColumn.getJdbcTypeName())) {
                sb.append(" and ");
                sb.append(introspectedColumn.getJavaProperty());
                sb.append(" != ''");
            }
            selectNotNullElement.addAttribute(new Attribute("test", sb.toString()));
            vagueNotNullElement.addAttribute(new Attribute("test", sb.toString()));
            sb.setLength(0);
            // 添加and
            sb.append(" and ");
            // 添加别名t
            sb.append("p.");
            sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
            // 添加等号
            sb.append(" = ");
            sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
            selectNotNullElement.addElement(new TextElement(sb.toString()));

            sb.setLength(0);
            // 添加and
            sb.append(" and ");
            // 添加别名t
            sb.append("p.");
            sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
            // 添加等号
            sb.append(" LIKE CONCAT('%', ");
            sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
            sb.append(", '%') ");
            vagueNotNullElement.addElement(new TextElement(sb.toString()));

//            selectTrimElement.addElement(selectNotNullElement);
//            vagueSelectTrimElement.addElement(vagueNotNullElement);
            conditionSql.addElement(selectNotNullElement);
            vagueConditionSql.addElement(vagueNotNullElement);
        }
        if (isStatus) {
            conditionSql.addElement(publicWhereStatus);
            conditionSql.addElement(publicWhereUserId);
            conditionSql.addElement(publicWhereCreateTime);
            vagueConditionSql.addElement(publicWhereStatus);
            vagueConditionSql.addElement(publicWhereUserId);
            vagueConditionSql.addElement(publicWhereCreateTime);
        }
//        conditionSql.addElement(selectTrimElement);
//        vagueConditionSql.addElement(vagueSelectTrimElement);
        parentElement.addElement(conditionSql);
        parentElement.addElement(vagueConditionSql);

        StringBuilder sqlColumn = new StringBuilder();
        for (IntrospectedColumn introspectedColumn : introspectedTable.getAllColumns()) {
            if (columnMap.containsKey(introspectedColumn.getJavaProperty())) {
                sqlColumn.append("p.").append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
            }
            if (filterMap.containsKey(introspectedColumn.getJavaProperty())
                    || filterMap.containsKey(introspectedColumn.getJdbcTypeName())
                    || columnMap.containsKey(introspectedColumn.getJavaProperty())
                    || columnMap.containsKey(introspectedColumn.getJdbcTypeName())) {
                continue;
            }
            if (sqlColumn.length() > NumeralUtil.POSITIVE_ZERO) {
                sqlColumn.append(", p.").append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn)).append("");
            } else {
                sqlColumn.append("p.").append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn)).append("");
            }
        }

        columnSql.addAttribute(new Attribute("id", "columnSql"));
        columnSql.addElement(new TextElement(sqlColumn.toString()));


    }

    //生成 insertSelective
    private void insertSelective(XmlElement parentElement) {
        // 增加 insertSelective
        XmlElement insertSelective = new XmlElement("insert");
        insertSelective.addAttribute(new Attribute(
                "id", "insertSelective"));
        String parameterType;

        if (introspectedTable.getRules().generateRecordWithBLOBsClass()) {
            parameterType = introspectedTable.getRecordWithBLOBsType();
        } else {
            parameterType = introspectedTable.getBaseRecordType();
        }

        insertSelective.addAttribute(new Attribute("parameterType", parameterType));
        insertSelective.addAttribute(new Attribute("useGeneratedKeys", "true"));
        for (IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns()) {
            insertSelective.addAttribute(new Attribute("keyProperty", introspectedColumn.getJavaProperty()));
        }

        StringBuilder sb = new StringBuilder();

        sb.append("insert into ");
        sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        insertSelective.addElement(new TextElement(sb.toString()));

        XmlElement fieldElement = new XmlElement("trim"); //设置trim标签
        fieldElement.addAttribute(new Attribute("prefix", "(")); //添加where和and
        fieldElement.addAttribute(new Attribute("suffix", ") ")); //添加where和and
        fieldElement.addAttribute(new Attribute("suffixOverrides", ",")); //添加where和and

        XmlElement valueElement = new XmlElement("trim"); //设置trim标签
        valueElement.addAttribute(new Attribute("prefix", "values (")); //添加where和and
        valueElement.addAttribute(new Attribute("suffix", ")")); //添加where和and
        valueElement.addAttribute(new Attribute("suffixOverrides", ",")); //添加where和and

        StringBuilder field = new StringBuilder();
        StringBuilder value = new StringBuilder();


        for (IntrospectedColumn introspectedColumn : introspectedTable.getAllColumns()) {
//            if (filterMap.containsKey(introspectedColumn.getJavaProperty())) {
//                continue;
//            }
            XmlElement fieldNotNullElement = new XmlElement("if"); //
            XmlElement valueNotNullElement = new XmlElement("if"); //
            field.setLength(0);
            field.append(introspectedColumn.getJavaProperty());
            field.append(" != null");
            value.setLength(0);
            value.append(introspectedColumn.getJavaProperty());
            value.append(" != null");
            if (jdbcTypeMap.containsKey(introspectedColumn.getJdbcTypeName())) {
                field.append(" and ");
                field.append(introspectedColumn.getJavaProperty());
                field.append(" != ''");
                value.append(" and ");
                value.append(introspectedColumn.getJavaProperty());
                value.append(" != ''");
            }
            fieldNotNullElement.addAttribute(new Attribute("test", field.toString()));
            valueNotNullElement.addAttribute(new Attribute("test", field.toString()));
            // 添加字段名
            field.setLength(0);
            field.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
            field.append(",");
            // 添加实体类变量
            value.setLength(0);
            value.append(FormattingUtilities.getParameterClause(introspectedColumn));
            value.append(",");

            fieldNotNullElement.addElement(new TextElement(field.toString()));
            valueNotNullElement.addElement(new TextElement(value.toString()));
            fieldElement.addElement(fieldNotNullElement);
            valueElement.addElement(valueNotNullElement);
        }
        insertSelective.addElement(fieldElement);
        insertSelective.addElement(valueElement);
        parentElement.addElement(insertSelective);
    }

    //生成 insertList
    private void insertList(XmlElement parentElement) {
        // 增加 insertList
        XmlElement insertList = new XmlElement("insert");
        insertList.addAttribute(new Attribute(
                "id", "insertList"));

        insertList.addAttribute(new Attribute("parameterType", "java.util.List"));
        insertList.addAttribute(new Attribute("useGeneratedKeys", "true"));
        for (IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns()) {
            insertList.addAttribute(new Attribute("keyProperty", introspectedColumn.getJavaProperty()));
        }

        StringBuilder sb = new StringBuilder();

        sb.append("insert into ");
        sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        insertList.addElement(new TextElement(sb.toString()));


        XmlElement valueElement = new XmlElement("foreach"); //设置 foreach 标签
        valueElement.addAttribute(new Attribute("collection", "list")); //添加 collection
        valueElement.addAttribute(new Attribute("item", "item")); //添加 item
        valueElement.addAttribute(new Attribute("index", "index")); //添加 index
        valueElement.addAttribute(new Attribute("separator", "union all")); //添加 separator

        StringBuilder field = new StringBuilder();
        StringBuilder value = new StringBuilder();

        field.append("(");
        valueElement.addElement(new TextElement("SELECT"));
        for (IntrospectedColumn introspectedColumn : introspectedTable.getAllColumns()) {
            if (insertFilterMap.containsKey(introspectedColumn.getJavaProperty())) {
                continue;
            }
            field.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
            field.append(", ");
            // 添加实体类变量
            value.append(FormattingUtilities.getParameterClause(introspectedColumn, "item."));
            value.append(", ");
        }
        value.deleteCharAt(value.length() - 1);
        value.deleteCharAt(value.length() - 1);
        field.deleteCharAt(field.length() - 1);
        field.deleteCharAt(field.length() - 1);
        field.append(")");
        valueElement.addElement(new TextElement(value.toString()));
        valueElement.addElement(new TextElement("FROM dual"));
        insertList.addElement(new TextElement(field.toString()));
        insertList.addElement(valueElement);
        parentElement.addElement(insertList);
    }

    //生成 updateByPrimaryKeySelective
    private void updateByPrimaryKeySelective(XmlElement parentElement) {
        // 增加 updateByPrimaryKeySelective
        XmlElement updateByPrimaryKeySelective = new XmlElement("update");
        updateByPrimaryKeySelective.addAttribute(new Attribute(
                "id", "updateByPrimaryKeySelective"));
        String parameterType;

        if (introspectedTable.getRules().generateRecordWithBLOBsClass()) {
            parameterType = introspectedTable.getRecordWithBLOBsType();
        } else {
            parameterType = introspectedTable.getBaseRecordType();
        }

        updateByPrimaryKeySelective.addAttribute(new Attribute("parameterType", parameterType));

        context.getCommentGenerator().addComment(updateByPrimaryKeySelective);
        XmlElement fieldElement = new XmlElement("set"); //设置trim标签

        StringBuilder sb = new StringBuilder();
        sb.append("update ");
        sb.append(this.introspectedTable.getFullyQualifiedTableNameAtRuntime());
        updateByPrimaryKeySelective.addElement(new TextElement(sb.toString()));

        for (IntrospectedColumn introspectedColumn : introspectedTable.getAllColumns()) {
            if (updateFilterMap.containsKey(introspectedColumn.getJavaProperty())) {
                continue;
            }
            XmlElement selectNotNullElement = new XmlElement("if"); //
            sb.setLength(0);
            sb.append(introspectedColumn.getJavaProperty());
            sb.append(" != null");
            if (jdbcTypeMap.containsKey(introspectedColumn.getJdbcTypeName())) {
                sb.append(" and ");
                sb.append(introspectedColumn.getJavaProperty());
                sb.append(" != ''");
            }
            selectNotNullElement.addAttribute(new Attribute("test", sb.toString()));
            // 添加字段名
            sb.setLength(0);
            sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
            // 添加等号
            sb.append(" = ");
            sb.append(FormattingUtilities.getParameterClause(introspectedColumn));
            sb.append(",");
            selectNotNullElement.addElement(new TextElement(sb.toString()));
            fieldElement.addElement(selectNotNullElement);
        }
        updateByPrimaryKeySelective.addElement(fieldElement);
        parentElement.addElement(updateByPrimaryKeySelective);
        buildPrimaryKeyWhereClause().forEach(updateByPrimaryKeySelective::addElement);
    }

    //生成 updateList
    private void updateList(XmlElement parentElement) {
        // 增加 updateList
        XmlElement updateList = new XmlElement("update");
        updateList.addAttribute(new Attribute(
                "id", "updateList"));
        updateList.addAttribute(new Attribute("parameterType", "java.util.List"));
        context.getCommentGenerator().addComment(updateList);

        XmlElement foreachElement = new XmlElement("foreach"); //设置 foreach 标签
        foreachElement.addAttribute(new Attribute("collection", "list")); //添加 collection
        foreachElement.addAttribute(new Attribute("item", "item")); //添加 item
        foreachElement.addAttribute(new Attribute("index", "index")); //添加 index
        foreachElement.addAttribute(new Attribute("open", "")); //添加 open
        foreachElement.addAttribute(new Attribute("close", "")); //添加 close
        foreachElement.addAttribute(new Attribute("separator", ";")); //添加 close

        XmlElement fieldElement = new XmlElement("set"); //设置set标签

        StringBuilder sb = new StringBuilder();
        sb.append("update ");
        sb.append(this.introspectedTable.getFullyQualifiedTableNameAtRuntime());
        foreachElement.addElement(new TextElement(sb.toString()));

        for (IntrospectedColumn introspectedColumn : introspectedTable.getAllColumns()) {
            if (updateFilterMap.containsKey(introspectedColumn.getJavaProperty())) {
                continue;
            }
            XmlElement selectNotNullElement = new XmlElement("if"); //
            sb.setLength(0);
            sb.append(introspectedColumn.getJavaProperty());
            sb.append(" != null");
            if (jdbcTypeMap.containsKey(introspectedColumn.getJdbcTypeName())) {
                sb.append(" and ");
                sb.append(introspectedColumn.getJavaProperty());
                sb.append(" != ''");
            }
            selectNotNullElement.addAttribute(new Attribute("test", sb.toString()));
            // 添加字段名
            sb.setLength(0);
            sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
            // 添加等号
            sb.append(" = ");
            sb.append(FormattingUtilities.getParameterClause(introspectedColumn, "item."));
            sb.append(",");
            selectNotNullElement.addElement(new TextElement(sb.toString()));
            fieldElement.addElement(selectNotNullElement);
        }
        foreachElement.addElement(fieldElement);
        foreachElement.addElement(new TextElement("where "));
        for (IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns()) {
            // 添加字段名
            sb.setLength(0);
            sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
            // 添加等号
            sb.append(" = ");
            sb.append(FormattingUtilities.getParameterClause(introspectedColumn, "item."));
            foreachElement.addElement(new TextElement(sb.toString()));
        }
        updateList.addElement(foreachElement);
        parentElement.addElement(updateList);
//        buildPrimaryKeyWhereClause().forEach(updateList::addElement);
    }

    //生成 delete
    private void delete(XmlElement parentElement) {
        // 增加 delete
        XmlElement answer = new XmlElement("update");

        answer.addAttribute(new Attribute(
                "id", "delete"));

        String parameterType;

        if (introspectedTable.getRules().generateRecordWithBLOBsClass()) {
            parameterType = introspectedTable.getRecordWithBLOBsType();
        } else {
            parameterType = introspectedTable.getBaseRecordType();
        }

        answer.addAttribute(new Attribute("parameterType", parameterType));

        context.getCommentGenerator().addComment(answer);

        StringBuilder sb = new StringBuilder();

        sb.append("update ");
        sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));

        answer.addElement(new TextElement("set"));

        for (IntrospectedColumn introspectedColumn :
                ListUtilities.removeGeneratedAlwaysColumns(introspectedTable.getNonPrimaryKeyColumns())) {
            if (deleteMap.size() == NumeralUtil.POSITIVE_ZERO) {
                break;
            }
            if (!deleteMap.containsKey(introspectedColumn.getJavaProperty())) {
                continue;
            }
            sb.setLength(0);
            sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
            sb.append(" = ");
            sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
            if (deleteMap.size() != NumeralUtil.POSITIVE_ONE) {
                sb.append(',');
            }
            answer.addElement(new TextElement(sb.toString()));
            deleteMap.remove(introspectedColumn.getJavaProperty());
        }

        buildPrimaryKeyWhereClause().forEach(answer::addElement);

        if (context.getPlugins().sqlMapUpdateByPrimaryKeySelectiveElementGenerated(answer, introspectedTable)) {
            parentElement.addElement(answer);
        }

    }

    //生成 selectByParam
    private void selectByParam(XmlElement parentElement, TextElement selectText, XmlElement include) {
        // 增加 selectByParam
        XmlElement selectByParam = new XmlElement("select");
        selectByParam.addAttribute(new Attribute("id", "selectByParam"));
        selectByParam.addAttribute(new Attribute("resultMap", "BaseResultMap"));
        selectByParam.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));
        selectByParam.addElement(selectText);
        selectByParam.addElement(include);
        parentElement.addElement(selectByParam);
    }

    //生成 selectByPrimaryKey
    private void selectByPrimaryKey(XmlElement parentElement, TextElement selectText) {
        // 增加 selectByPrimaryKey
        XmlElement selectByPrimaryKey = new XmlElement("select");
        selectByPrimaryKey.addAttribute(new Attribute("id", "selectByPrimaryKey"));
        selectByPrimaryKey.addAttribute(new Attribute("resultMap", "BaseResultMap"));
        selectByPrimaryKey.addAttribute(new Attribute("parameterType", "java.lang.Long"));
        selectByPrimaryKey.addElement(selectText);
        selectByPrimaryKey.addElement(new TextElement("where "));
        StringBuilder sb = new StringBuilder();
        for (IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns()) {
            // 添加字段名
            sb.setLength(0);
            sb.append("p.");
            sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
            // 添加等号
            sb.append(" = ");
            sb.append(FormattingUtilities.getParameterClause(introspectedColumn));
            selectByPrimaryKey.addElement(new TextElement(sb.toString()));
        }
        selectByPrimaryKey.addElement(new TextElement(" and p.OPERATER_STATUS > 0"));
        parentElement.addElement(selectByPrimaryKey);
    }

    //生成 findPageList
    private void findPageList(XmlElement parentElement, TextElement selectText, XmlElement include, StringBuilder sb) {
        // 增加 findPageList
        XmlElement findPageList = new XmlElement("select");
        findPageList.addAttribute(new Attribute("id", "findPageList"));
        findPageList.addAttribute(new Attribute("resultMap", "BaseResultMap"));
        findPageList.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));
        findPageList.addElement(selectText);
        findPageList.addElement(include);
        //sortElement
        XmlElement sortElement = new XmlElement("if");
        sb.setLength(0);
//        sb.append("sort != null and sort != ''");
//        sortElement.addAttribute(new Attribute("test", sb.toString()));
//        sb.setLength(0);
//        sb.append("order by ${sort}");
//        sortElement.addElement(new TextElement(sb.toString()));
//        //orderElement
//        XmlElement orderElement = new XmlElement("if");
//        sb.setLength(0);
//        sb.append("order != null and order != ''");
//        orderElement.addAttribute(new Attribute("test", sb.toString()));
//        sb.setLength(0);
//        sb.append("${order}");
//        orderElement.addElement(new TextElement(sb.toString()));
//        sortElement.addElement(orderElement);
//        findPageList.addElement(sortElement);
        sb.append("<include refid=\"BaseMapper.publicSortAndOrder\"/>");
        findPageList.addElement(new TextElement(sb.toString()));

        //limit
        sb.setLength(0);
//        sb.append("limit #{offset}, #{limit}");
//        findPageList.addElement(new TextElement(sb.toString()));
//        parentElement.addElement(findPageList);
        sb.append("<include refid=\"BaseMapper.publicOffsetAndLimit\"/>");
        findPageList.addElement(new TextElement(sb.toString()));
        parentElement.addElement(findPageList);

    }

    //生成 findPageListCount
    private void findPageListCount(XmlElement parentElement, XmlElement include) {
        // 增加 findPageListCount
        StringBuilder findPageListCountSql = new StringBuilder();
        findPageListCountSql.append("    select ");
        findPageListCountSql.append("\n        COUNT(p.ID) ");
        findPageListCountSql.append("\n        from ");
        findPageListCountSql.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        findPageListCountSql.append(" p ");
        TextElement findPageListCountSqlText = new TextElement(findPageListCountSql.toString());
        XmlElement findPageListCount = new XmlElement("select");
        findPageListCount.addAttribute(new Attribute("id", "findPageListCount"));
        findPageListCount.addAttribute(new Attribute("resultType", "INTEGER"));
        findPageListCount.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));
        findPageListCount.addElement(findPageListCountSqlText);
        findPageListCount.addElement(include);
        parentElement.addElement(findPageListCount);
    }

    //生成 findByList
    private void findByList(XmlElement parentElement, TextElement selectText, XmlElement include) {
        // 增加 findByList
        XmlElement findByList = new XmlElement("select");
        findByList.addAttribute(new Attribute("id", "findByList"));
        findByList.addAttribute(new Attribute("resultMap", "BaseResultMap"));
        findByList.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));
        findByList.addElement(selectText);
        findByList.addElement(include);
        parentElement.addElement(findByList);
    }
}
