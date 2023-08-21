<!DOCTYPE html>
<html lang="zh-CN" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">
<head
        th:replace="~{segmentation/_right_bottom_header::_header_common(~{::title},~{::link},~{::style})}">
    <#if isText>
        <link rel="stylesheet" th:href="@{/js/custom/common/lay-module/wangEditor/wangEditor.css?v=1.0.0}" media="all">
    </#if>
    <title>${fullName}编辑</title>
    <link>
    <style type="text/css"></style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>${fullName}编辑</legend>
        </fieldset>
        <form class="layui-form" id="${className}FormEdit" action="">
            <input type="hidden" id="id" name="id" th:value="${currencySymbol}{p.id}"/>
            <#list editList as field>
            <div class="layui-form-item">
                <label class="layui-form-label"><span class="required-red">*</span>${field.name}
                </label>
                <div class="layui-input-block" <#if isText>style="border: 1px solid #ccc;"</#if>>
                    <#if field.code == 'isEnable'>
                    <input th:each="item : ${currencySymbol}{@systemTypeServiceImpl.findByCodeList('isEnable')}"
                           th:title="${currencySymbol}{item.typeName}"
                           th:value="${currencySymbol}{item.typeValue}"
                           th:checked="${currencySymbol}{p.isEnable == item.typeValue}"
                           type="radio" name="isEnable" lay-verify="required|isEnable"
                           lay-verType="tips">
                    <#else>
                        <#if field.type == 'Date'>
                    <input type="text" name="${field.code}" id="${field.code}" lay-verify="required|${field.code}"
                           lay-verType="tips"
                           th:value="${currencySymbol}{#dates.format(p.${field.code}, 'yyyy-MM-dd HH:mm:ss')}"
                           placeholder="请选择${field.name}" readonly autocomplete="off" class="layui-input">
                        <#elseif field.type == 'LONGVARCHAR'>
                    <div id="${field.code}Toolbar" style="border-bottom: 1px solid #ccc;"></div>
                    <div id="${field.code}Editor" style="height: 500px;"></div>
                    <textarea style="display: none;" name="${field.code}" id="${field.code}"
                              th:text="${currencySymbol}{p.${field.code}}"></textarea>
                        <#else>
                    <input type="text" name="${field.code}" id="${field.code}" lay-verify="required<#if field.type == 'Long'
                    || field.type == 'Integer' || field.type == 'BigDecimal'>|number</#if>|${field.code}"
                           lay-verType="tips"
                           th:value="${currencySymbol}{p.${field.code}}"
                           placeholder="请输入${field.name}" autocomplete="off" class="layui-input">
                        </#if>
                    </#if>
                </div>
            </div>
            </#list>
            <div class="layui-form-item"
                 sec:authorize="hasPermission(#authorization.authentication,'button','${tableCode}_LIST_MANAGEMENT:edit')">
                <div class="layui-input-block">
                    <a class="layui-btn" lay-submit="" lay-filter="${className}FormEditSubmit">保存</a>
                </div>
            </div>
        </form>
    </div>
</div>

<div th:replace="~{segmentation/_tail::_tail_common}"></div>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script th:inline="javascript">
    /*<![CDATA[*/
    [# sec:authorize="hasPermission(#authorization.authentication,'button','${tableCode}_LIST_MANAGEMENT:edit')"]
    layui.use(['form'<#if isBigDecimal || isText>, 'common'</#if><#if isText>, 'wangEditor'</#if>], function () {
        var form = layui.form,
            <#if isBigDecimal || isText>
            common = layui.common,
            </#if>
            <#if isText>
            wangEditor = layui.wangEditor,
            wangEditorIndexClose = null,
            </#if>
            <#list textList as field>
            ${field.code}Html = $("#${field.code}").val(),
            </#list>
            layer = layui.layer;

        <#if isText>
        var uploadImage = common.uploadImage("", "");
        </#if>

        <#list textList as field>
        var ${field.code}EditorConfig = {
            MENU_CONF: {}
        };
        var ${field.code}ToolbarConfig = {};
        ${field.code}EditorConfig.withCredentials = true;//带凭证
        ${field.code}EditorConfig.placeholder = "请输入内容"
        ${field.code}EditorConfig.MENU_CONF.uploadImage = uploadImage;
        ${field.code}EditorConfig.onChange = function (editor, DomEditor) {
            $("#${field.code}").val(editor.getHtml());
        }
        //['headerSelect', 'blockquote', '|', 'bold', 'underline', 'italic', {…}, 'color', 'bgColor', '|', 'fontSize',
        // 'fontFamily', 'lineHeight', '|', 'bulletedList', 'numberedList', 'todo', {…}, {…}, '|', 'emotion',
        //  'insertLink', {…}, {…}, 'insertTable', 'codeBlock', 'divider', '|', 'undo', 'redo', '|', 'fullScreen']
        // toolbarConfig.Partial.IToolbarConfig.excludeKeys = {excludeKeys =['emotion']};
        ${field.code}ToolbarConfig.excludeKeys = ['emotion'];
        // 创建编辑器
        var ${field.code}Editor = wangEditor.createEditor({
            html: ${field.code}Html,
            selector: '#${field.code}Editor',
            config: ${field.code}EditorConfig,
            mode: 'default' // 或 'simple' 参考下文
        });

        var ${field.code}Toolbar = wangEditor.createToolbar({
            editor: ${field.code}Editor,
            config: ${field.code}ToolbarConfig,
            selector: '#${field.code}Toolbar',
            mode: 'default' // 或 'simple' 参考下文
        });
        </#list>


        //自定义验证规则
        form.verify({
            <#list editList as field>
            <#if field.type == 'String'>
            ${field.code}: function (value) {
                if (value.length > ${field.length}) {
                    return '${field.name}最多${field.length}个字符';
                }
            },
            </#if>
            <#if field.type == 'Long' || field.type == 'Integer'>
            ${field.code}: function (value) {
                if (value.length > ${field.length}) {
                    return '${field.name}最多${field.length}个字符';
                }
            },
            </#if>
            <#if field.type == 'BigDecimal'>
            ${field.code}: function (value) {
                if (common.isNumber(value)) {
                    return "请输入正确小数";
                }
            },
            </#if>
            <#if field.type == 'Date'>
            ${field.code}: function (value) {
            },
            </#if>
            </#list>
            <#list textList as field>
            ${field.code}: function (value) {
                if (value.length > ${field.length}) {
                    return '${field.name}最多${field.length}个字符';
                }
            },
            </#list>
        });

        //监听提交
        form.on('submit(${className}FormEditSubmit)', function (data) {
            $.ajax({
                type: 'POST',
                url: '${url}/edit',
                data: data.field,
                dataType: 'json',
                success: function (data) {
                    if (data.code == Result.SUCCESS) {
                        layer.msg('成功!', {
                            icon: 1,
                            shade: [0.3, '#f5f5f5'],
                            time: 1500
                        }, function () {
                            $("#${className}List", window.parent.document).attr('value', 1);
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    } else {
                        layer.msg(data.msg, {
                            icon: 2,
                            time: 3000
                        });
                    }
                }, error:function(data) {
                    console.log(data);
                },
            });
            return false;//返回false防止重复提交
        });
    });
    [/]
    /*]]>*/
</script>

</body>
</html>