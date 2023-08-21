<!DOCTYPE html>
<html lang="zh-CN" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">
<head
        th:replace="~{segmentation/_right_bottom_header::_header_common(~{::title},~{::link},~{::style})}">
    <#if isText>
        <link rel="stylesheet" th:href="@{/js/custom/common/lay-module/wangEditor/wangEditor.css?v=1.0.0}" media="all">
    </#if>
    <title>${fullName}查看</title>
    <link>
    <style type="text/css"></style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>${fullName}查看</legend>
        </fieldset>
        <form class="layui-form" id="${className}FormView" action="">
            <#list editList as field>
            <div class="layui-form-item">
                <label class="layui-form-label">${field.name}
                </label>
                <div class="layui-input-block">
                    <#if field.code == 'operaterStatus'>
                    <span class="view-span" th:each="item : ${currencySymbol}{T(com.framework.common.enums.OperaterStatusEnum).values()}"
                          th:text="${currencySymbol}{item.name}"
                          th:if="${currencySymbol}{p.operaterStatus == item.state}"></span>
                    <#elseif field.code == 'isEnable'>
                    <span class="view-span" th:each="item : ${currencySymbol}{@systemTypeServiceImpl.findByCodeList('isEnable')}"
                          th:text="${currencySymbol}{item.typeName}"
                          th:if="${currencySymbol}{p.isEnable == item.typeValue}"
                          th:style="${currencySymbol}{item.typeValue == 1 ? 'color: limegreen;' : 'color: red;' }"></span>
                    <#else>
                        <#if field.type == 'Date'>
                    <span class="view-span" th:text="${currencySymbol}{#dates.format(p.${field.code}, 'yyyy-MM-dd HH:mm:ss')}"></span>
                        <#elseif field.type == 'LONGVARCHAR'>
                    <div id="${field.code}Editor" style="height: 500px;"></div>
                    <textarea style="display: none;" name="${field.code}" id="${field.code}"
                              th:text="${currencySymbol}{p.${field.code}}"></textarea>
                        <#else>
                    <span class="view-span" th:text="${currencySymbol}{p.${field.code}}"></span>
                        </#if>
                    </#if>
                </div>
            </div>
            </#list>
        </form>
    </div>
</div>
<#if isText>
<div th:replace="~{segmentation/_tail::_tail_common}"></div>
<script th:inline="javascript">
    /*<![CDATA[*/
    [# sec:authorize="hasPermission(#authorization.authentication,'button','${tableCode}_LIST_MANAGEMENT:view')"]
    layui.use(['wangEditor'], function () {
        var
            <#list textList as field>
            ${field.code}Html = $("#${field.code}").val(),
            </#list>
            wangEditor = layui.wangEditor;



        <#list textList as field>
        // 创建编辑器
        var ${field.code}Editor = wangEditor.createEditor({
            html: ${field.code}Html,
            selector: '#${field.code}Editor',
            mode: 'default' // 或 'simple' 参考下文
        });
        ${field.code}Editor.disable();
        </#list>

    });
    [/]
    /*]]>*/
</script>
</#if>
</body>
</html>