<!DOCTYPE html>
<html lang="zh-CN" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">
<head
        th:replace="~{segmentation/_right_bottom_header::_header_common(~{::title},~{::link},~{::style})}">
    <meta charset="utf-8">
    <title>${fullName}列表</title>
    <link>
    <style type="text/css"></style>
</head>
<body>
<div class="layuimini-container" id="${className}List" value="0">
    <div class="layui-row">
        <div class="layuimini-main">
            <fieldset class="layui-elem-field layuimini-search">
                <legend>搜索信息</legend>
                <div style="margin: 10px 10px 10px 10px">
                    <form class="layui-form" id="${className}-search-form" action="">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">开始时间</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="startTime" name="startTime" autocomplete="off"
                                           placeholder="请选择开始时间"
                                           class="layui-input startTime" readonly>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">结束时间</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="endTime" name="endTime" autocomplete="off"
                                           placeholder="请选择结束时间"
                                           class="layui-input endTime" readonly>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"></label>
                                <div class="layui-input-inline">
                                    <input type="text" name="" id="" autocomplete="off"
                                           placeholder="请输入"
                                           class="layui-input">
                                </div>
                            </div>

                            <div class="layui-inline"
                                 sec:authorize="hasPermission(#authorization.authentication,'menus','${tableCode}_LIST_MANAGEMENT')">
                                <a class="layui-btn" lay-submit="" lay-filter="${className}-search" title="搜搜">
                                    <i class="fa fa-search" aria-hidden="true"></i>
                                </a>
                            </div>
                        </div>
                    </form>
                </div>
            </fieldset>

            <div class="layui-btn-group" style="min-width: 100%;">
                <a sec:authorize="hasPermission(#authorization.authentication,'button','${tableCode}_LIST_MANAGEMENT:add')"
                   class="layui-btn ${className}-add" title="新增">
                    新增
                </a>
                <a sec:authorize="hasPermission(#authorization.authentication,'button','${tableCode}_LIST_MANAGEMENT:batchDel')"
                   class="layui-btn layui-btn-danger ${className}-batch-del"
                   style="margin-left: 10px!important;" title="批量删除">
                    批量删除
                </a>
            </div>
            <table id="${className}TableId" lay-filter="${className}TableFilter"></table>
            <script type="text/html" id="${className}CurrentTableBar">
                <a sec:authorize="hasPermission(#authorization.authentication,'button','${tableCode}_LIST_MANAGEMENT:view')"
                   class="layui-btn layui-btn-xs ${className}-view" value="{{d.id}}" lay-event="view" title="查看">
                    查看
                </a>
                <a sec:authorize="hasPermission(#authorization.authentication,'button','${tableCode}_LIST_MANAGEMENT:edit')"
                   class="layui-btn layui-btn-xs ${className}-edit" value="{{d.id}}" lay-event="edit" title="编辑">
                    编辑
                </a>
                <a sec:authorize="hasPermission(#authorization.authentication,'button','${tableCode}_LIST_MANAGEMENT:del')"
                   class="layui-btn layui-btn-xs layui-btn-danger ${className}-delete" value="{{d.id}}"
                   lay-event="delete" title="删除">
                    删除
                </a>
            </script>

            <script type="text/html" id="isEnableCurrentTableBar">
                {{#  if(d.isEnable == 1){ }}
                <span style="color: limegreen;">是</span>
                {{#  } else { }}
                <span style="color: red;">否</span>
                {{#  } }}
            </script>
            <script type="text/html" id="operaterStatusCurrentTableBar">
                {{#  if(d.operaterStatus == 1){ }}
                新增
                {{#  } else { }}
                修改
                {{#  } }}
            </script>
        </div>
    </div>
</div>
<div th:replace="~{segmentation/_tail::_tail_common}"></div>
<script th:inline="javascript">

    /*<![CDATA[*/
    [# sec:authorize="hasPermission(#authorization.authentication,'menus','${tableCode}_LIST_MANAGEMENT')"]
    layui.use(['form', 'table', 'common', 'laydate'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table,
            laydate = layui.laydate,
            common = layui.common,
            startTime = {}, endTime = {},
            numView = 0, numEdit = 0, numDel = 0,
            tableId = "${className}TableId";

        [# sec:authorize="hasPermission(#authorization.authentication,'button','${tableCode}_LIST_MANAGEMENT:add')"]
        // 新增操作
        $(document).off("click", ".${className}-add");
        $(document).on("click", ".${className}-add", function () {
            var title = $(this).attr("title");
            var url = "${url}/get/add";
            var formId = "${className}FormAddId";
            common.layer_show(title, url, null, null, formId, null, null, null, function () {
                if ($("#${className}List").attr('value') == 1) {
                    $("#${className}List").attr('value', 0);
                    var queryData = common.getFormData($("#${className}-search-form"));
                    common.tableRefresh(tableId, queryData);
                }
            });
        });
        [/]

        [# sec:authorize="hasPermission(#authorization.authentication,'button','${tableCode}_LIST_MANAGEMENT:view')"]
        numView = 1;
        function initView() {
            // 查看信息
            $(".layui-table").off("click", ".${className}-view");
            $(".layui-table").on("click", ".${className}-view", function () {
                var title = $(this).attr("title");
                var url = "${url}/get/view?id=" + $(this).attr("value");
                var formId = "${className}FormViewId";
                common.layer_show(title, url, null, null, formId, null, null, null, function () {
                    // var queryData = common.getFormData($("#${className}-search-form"));
                    // common.tableRefresh(tableId, queryData);
                });
            });
        }
        [/]

        [# sec:authorize="hasPermission(#authorization.authentication,'button','${tableCode}_LIST_MANAGEMENT:edit')"]
        numEdit = 1;
        function initEdit() {
            // 编辑信息
            $(".layui-table").off("click", ".${className}-edit");
            $(".layui-table").on("click", ".${className}-edit", function () {
                var title = $(this).attr("title");
                var url = "${url}/get/edit?id=" + $(this).attr("value");
                var formId = "${className}FormEditId";
                common.layer_show(title, url, null, null, formId, null, null, null, function () {
                    if ($("#${className}List").attr('value') == 1) {
                        $("#${className}List").attr('value', 0);
                        var queryData = common.getFormData($("#${className}-search-form"));
                        common.tableRefresh(tableId, queryData);
                    }
                });
            });
        }
        [/]

        [# sec:authorize="hasPermission(#authorization.authentication,'button','${tableCode}_LIST_MANAGEMENT:del')"]
        numDel = 1;
        function initDel() {
            // 删除信息
            $(".layui-table").off("click", ".${className}-delete");
            $(".layui-table").on("click", ".${className}-delete", function () {
                // var ids = common.getTableRowId('${className}TableId', "id")
                var id = $(this).attr("value");
                if (id.length == 0) {
                    layer.msg('请选中删除信息');
                    return;
                }
                layer.confirm('确认删除吗？', {
                    icon: 3,
                    title: '提示',
                    shadeClose: true
                }, function (index) {
                    layer.close(index);
                    $.ajax({
                        type: 'DELETE',
                        url: '${url}/del',
                        data: {
                            id: id
                        },
                        dataType: 'json',
                        success: function (data) {
                            if (data.code == Result.SUCCESS) {
                                layer.msg('成功!', {
                                    icon: 1,
                                    shade: [0.3, '#f5f5f5'],
                                    time: 1500
                                }, function () {
                                    var queryData = common.getFormData($("#${className}-search-form"));
                                    common.tableRefresh(tableId, queryData);
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
                });
                return;
            });
        }
        [/]

        [# sec:authorize="hasPermission(#authorization.authentication,'button','${tableCode}_LIST_MANAGEMENT:batchDel')"]
        // 批量删除操作
        $(document).off("click", ".${className}-batch-del");
        $(document).on("click", ".${className}-batch-del", function () {
            var ids = common.getTableRowId(tableId, "id");
            if (ids.length == 0) {
                layer.msg('请选中删除信息');
                return;
            }
            layer.confirm('确认删除吗？', {
                icon: 3,
                title: '提示',
                shadeClose: true
            }, function (index) {
                layer.close(index);
                $.ajax({
                    type: 'POST',
                    url: '${url}/batch/del',
                    data: {
                        idList: ids
                    },
                    dataType: 'json',
                    success: function (data) {
                        if (data.code == Result.SUCCESS) {
                            layer.msg('成功!', {
                                icon: 1,
                                shade: [0.3, '#f5f5f5'],
                                time: 1500
                            }, function () {
                                var queryData = common.getFormData($("#${className}-search-form"));
                                common.tableRefresh(tableId, queryData);
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
            });
            return;
        });
        [/]

        //表格数据完成后初始化
        function tableInit(res, curr, count) {
            if (numView == 1) {
                initView();
            }
            if (numEdit == 1) {
                initEdit();
            }
            if (numDel == 1) {
                initDel();
            }
            common.initCheckbokOrRadio(tableId, 1);
        }

        var ${className}TableId = table.render({
            elem: '#' + tableId,
            id: tableId,
            url: '${url}/find/page/list',
            height: 'full-175',
            // contentType: 'application/json',
            // method: 'post',
            // even: true,
            done: tableInit,
            cols: [
                [
                    {type: "checkbox", width: 50, field: 'id', fixed: "left"},
                    {type: "numbers", width: 50, fixed: "left", title: '序号'},
                    <#list listList as field>
                    <#if field.code != 'isEnable'>
                    {field: '${field.code}', width: 200, title: '${field.name}', sort: true},
                    <#elseif field.code == 'isEnable'>
                    {field: 'isEnable', width: 200, title: '是否启用', sort: true, templet: '#isEnableCurrentTableBar'},
                    </#if>
                    </#list>
                    {field: 'operaterStatus', width: 100, title: '状态', sort: true, templet: '#operaterStatusCurrentTableBar'},
                    {field: 'operaterTime', width: 160, title: '操作时间', sort: true},
                    {field: 'operaterUserName', width: 120, title: '操作人', sort: true},
                    {title: '操作', minWidth: 250, templet: '#${className}CurrentTableBar', fixed: "right", align: "center"}
                ]
            ],
            // initSort: {
            //      sort: "createTime", //排序字段   在接口作为参数字段  field order
            //      order: "desc"
            // },
            sort: true,
            limits: [10, 25, 50, 100],
            limit: 10,
            page: true
        });

        table.on('sort(${className}TableFilter)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            //尽管我们的 table 自带排序功能，但并没有请求服务端。
            //有些时候，你可能需要根据当前排序的字段，重新向服务端发送请求，从而实现服务端排序，如：
            var queryData = common.getFormData($("#${className}-search-form"));
            queryData.sort = obj.field;//排序字段   在接口作为参数字段  field order
            queryData.order = obj.type;//排序方式   在接口作为参数字段  field order
            //请求参数（注意：这里面的参数可任意定义，并非下面固定的格式）
            common.tableRefresh(tableId, queryData);
        });

        // 监听搜索操作
        form.on('submit(${className}-search)', function (data) {
            //执行搜索重载
            common.tableRefresh(tableId, data.field);
            return false;//返回false防止重复提交
        });

        startTime = laydate.render({
            elem: '#startTime',
            type: 'datetime',
            // min: -7, //7天前
            max: 'nowTime',//7天后
            btns: ['clear', 'confirm'],
            theme: 'grid',
            done: function (value, date, endDate) {
                common.setUpEndTime(endTime, value, date, 6);
            }
        });

        endTime = laydate.render({
            elem: '#endTime',
            type: 'datetime',
            max: 'nowTime',//7天后
            btns: ['clear', 'confirm'],
            theme: 'grid',
            ready: function (data) {
                var now = new Date();
                this.dateTime.hours = now.getHours();
                this.dateTime.minutes = now.getMinutes();
                this.dateTime.seconds = now.getSeconds();
            },
            done: function (value, date, endDate) {
                common.setUpStartTime(startTime, value, date, 6);
            }
        });
    });
    [/]
    /*]]>*/
</script>
</body>
</html>