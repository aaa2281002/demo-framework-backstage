/**
 * date:2019/12/11
 * author: 邋遢龘鵺
 * description:common 公共引用
 */

layui.define(["layer", "table"], function (exports) {
    var table = layui.table;

    //获取属性节点ID
    function getTreeId(jsonObj) {
        var ids = new Array();
        $.each(jsonObj, function (index, item) {
            ids.push(item.id);
            if (item.children.length > 0) {
                var arr = getTreeId(item.children);
                if (arr.length > 0) {
                    ids = ids.concat(arr);
                }
            }
        });
        return ids;
    }

    common = new function () {

        /**
         * 删除当前打开tabs
         * @param value 对应lay-id
         */
        this.closeThisTabs = function(value){
            $(parent.top.document).find(".layui-tab[lay-filter=layuiminiTab]")
                .children(".layui-tab-title").find(">li[lay-id='"+value+"']")
                .find("i.layui-tab-close").click();
        }

        /**
         * 获取树形选中节点ID数组
         * @param data 参数集合
         * @returns {any[]} 返回数字编号
         */
        this.getTreeSelectId = function (data) {
            return getTreeId(data);
        }

        /**
         * 公共表格刷新表格方法,禁用分页
         * @param tableId
         * @param data
         */
        this.tablePageRefresh = function (tableId, data) {
            table.reload(tableId, {
                page: {
                    page: 1
                },
                page: false,
                where: data
            });
        }

        /**
         * 公共表格刷新表格方法
         * @param tableId
         * @param data
         */
        this.tableRefresh = function (tableId, data) {
            table.reload(tableId, {
                page: {
                    page: 1
                },
                where: data
            });
        }

        /**
         * 初始化表格单选框和复选框行选中事件
         * @param tableId   当前table表单ID
         * @param type      类型，1=checkbok多选， 2=radio多选
         */
        this.initCheckbokOrRadio = function (tableId, type) {
            $(document).off("click", "[lay-id='" + tableId + "'] .layui-table-body table.layui-table tbody tr")
            $(document).on("click", "[lay-id='" + tableId + "'] .layui-table-body table.layui-table tbody tr", function (e) {
                // if (e.target.tagName == "I") {
                //     var hasClss = $(e.target).parent().hasClass("layui-form-checked");
                //     // var selectCheckboxOrRadio = $(this).parents("[lay-id='" + tableId + "']").find("tr[data-index=" + index + "]");
                //     // var selectCheckboxOrRadio = $(this).parents("[lay-id='" + tableId + "']").find("tr[data-index=" + index + "]");
                //     // if (hasClss) {
                //     //     selectCheckboxOrRadio.removeClass("select-checkbox-radio");
                //     // } else {
                //     //     selectCheckboxOrRadio.addClass("select-checkbox-radio");
                //     // }
                //     var index = $(this).attr('data-index');
                //     var selectCheckboxOrRadio = $(this).parents("[lay-id='" + tableId + "']").find("tr[data-index=" + index + "]");
                //     var isCheckboxOrRadio = selectCheckboxOrRadio.hasClass("select-checkbox-radio");
                //     if (hasClss && !isCheckboxOrRadio) {
                //         selectCheckboxOrRadio.addClass("select-checkbox-radio");
                //     } else if (!hasClss && isCheckboxOrRadio) {
                //         selectCheckboxOrRadio.removeClass("select-checkbox-radio");
                //     }
                //     return;
                // }
                if (e.target.tagName != "DIV") {
                    return;
                }
                var index = $(this).attr('data-index');
                var tableBox = $(this).closest('.layui-table-box');
                var tableFixed = tableBox.find(".layui-table-fixed.layui-table-fixed-l");
                var tableBody = tableBox.find(".layui-table-body.layui-table-main");
                var tableDiv = tableFixed.length > 0 ? tableFixed : tableBody;
                if (type == 1) {//处理多选框
                    var checkCell = tableDiv.find("tr[data-index=" + index + "]").find("td div.laytable-cell-checkbox div.layui-form-checkbox");
                    if (checkCell.length) {
                        checkCell.click();
                        // var is = checkCell.hasClass("layui-form-checked");
                        // var selectCheckboxOrRadio = $(this).parents("[lay-id='" + tableId + "']").find("tr[data-index=" + index + "]");
                        // //!is &&
                        // if (!selectCheckboxOrRadio.hasClass("select-checkbox-radio") && is) {
                        //     selectCheckboxOrRadio.addClass("select-checkbox-radio");
                        // } else if (selectCheckboxOrRadio.hasClass("select-checkbox-radio") && !is) {
                        //     selectCheckboxOrRadio.removeClass("select-checkbox-radio");
                        // }
                    }
                } else if (type == 2) {//处理单选框
                    var radioCell = tableDiv.find("tr[data-index=" + index + "]").find("td div.laytable-cell-radio div.layui-form-radio i");
                    if (radioCell.length) {
                        radioCell.click();
                        // var $this_table = $(this).parents("[lay-id='" + tableId + "']");
                        // $this_table.find("tr").removeClass("select-checkbox-radio");
                        // $this_table.find("tr[data-index=" + index + "]").addClass("select-checkbox-radio");
                    }
                }
                // common.checkbokOrRadio(this, tableId, type);
            });
            if (type == 1) {
                //TD多选禁止重复
                $(document).off("click", "[lay-id='" + tableId + "'] td div.laytable-cell-checkbox div.layui-form-checkbox")
                $(document).on("click", "[lay-id='" + tableId + "'] td div.laytable-cell-checkbox div.layui-form-checkbox", function (e) {
                    // $(document).off("click", "div[class*='laytable-cell-checkbox'] .layui-form-checkbox")
                    // $(document).on("click", "div[class*='laytable-cell-checkbox'] .layui-form-checkbox", function (e) {
                    // if (e.originalEvent) {
                    //     if (e.target.tagName == "I") {
                    //         var hasClss = $(e.target).parent().hasClass("layui-form-checked");
                    //         var $this_table = $(this).parents("[lay-id='" + tableId + "']");
                    //         var index = $this_table.find("tr[class*='layui-table-hover']").attr('data-index');
                    //         var selectCheckboxOrRadio = $this_table.find("tr[data-index=" + index + "]");
                    //         var isCheckboxOrRadio = selectCheckboxOrRadio.hasClass("select-checkbox-radio");
                    //         if (hasClss && !isCheckboxOrRadio) {
                    //             selectCheckboxOrRadio.addClass("select-checkbox-radio");
                    //         } else if (!hasClss && isCheckboxOrRadio) {
                    //             selectCheckboxOrRadio.removeClass("select-checkbox-radio");
                    //         }
                    //     }
                    // }
                    e.stopPropagation();
                });
                // //TH多选禁止重复
                // $(document).off("click", "[lay-id='" + tableId + "'] th div.laytable-cell-checkbox div.layui-form-checkbox")
                // $(document).on("click", "[lay-id='" + tableId + "'] th div.laytable-cell-checkbox div.layui-form-checkbox", function (e) {
                // // $("[lay-id='" + tableId + "'] [class*='layui-table-fixed'] th div.laytable-cell-checkbox div.layui-form-checkbox").on("click", function (e) {
                //     if (e.originalEvent) {
                //         e.stopPropagation();
                //         if (e.target.tagName == "I") {
                //             var tableBox = $(this).closest('.layui-table-box');
                //             tableBox.find("tr[data-index]").addClass("select-checkbox-radio")
                //             // return;
                //         }
                //     }
                // });
            } else if (type == 2) {
                //单选禁止重复
                $(document).off("click", "[lay-id='" + tableId + "'] td div.laytable-cell-radio div.layui-form-radio")
                $(document).on("click", "[lay-id='" + tableId + "'] td div.laytable-cell-radio div.layui-form-radio", function (e) {
                    // if (e.originalEvent) {
                    //     if (e.target.tagName == "I") {
                    //         var $this_table = $(this).parents("[lay-id='" + tableId + "']");
                    //         var index = $this_table.find("tr[class*='layui-table-hover']").attr('data-index');
                    //         $this_table.find("tr").removeClass("select-checkbox-radio");
                    //         $this_table.find("tr[data-index=" + index + "]").addClass("select-checkbox-radio");
                    //     }
                    // }
                    e.stopPropagation();
                });
            }
        };

        //获取表格选中行ID
        this.getTableRowId = function (tableId, value) {
            var checkStatus = table.checkStatus(tableId)
                , data = checkStatus.data,
                arr = new Array();
            if (!data) {
                return arr;
            }
            for (var i = 0; i < data.length; i++) {
                arr.push(data[i][value]);
            }
            return arr;
        };

        //弹出层，可以缩小放大
        this.layer_show = function (title, url, w, h, formId, successFun, yesFun, cancelFun, endFun) {
            if (title == null || title == '') {
                title = false;
            }
            if (url == null || url == '') {
                url = "404.html";
            }
            if (w == null || w == '') {
                w = ($(window).width() - 50);
            }
            if (h == null || h == '') {
                h = ($(window).height() - 10);
            }
            layer.open({
                id: formId,
                type: 2,
                area: [w + 'px', h + 'px'],
                fixed: false, //不固定
                maxmin: true,
                shade: 0.4,
                title: title,
                content: url,
                success: successFun,
                yes: yesFun,
                cancel: cancelFun,
                end: endFun
            });
        }

        /**
         * 获取form表单对象
         * @param $this
         * @returns {xxx:xxx,xxx:xxx}
         */
        this.getFormData = function ($this) {
            var arr = $this.serializeArray();
            if (arr.length > 0) {
                var data = {};
                for (var i = 0; i < arr.length; i++) {
                    data[arr[i].name] = arr[i].value;
                }
                return data;
            }
            return {};
        }

        //计算两个日期相差几天, 通过结束时间减去开始时间
        this.getDaysBetween = function (startTime, endTime) {
            var startDate = Date.parse(startTime);
            var endDate = Date.parse(endTime);
            var days = (endDate - startDate) / (1 * 24 * 60 * 60 * 1000);
            // alert(days);
            return days;
        }

        /**
         * 处理开始日期限制范围
         * @param startTime laydate.render开始日期对象
         * @param selectedValue value值
         * @param selectedDate date值
         * @param minusDay 正数，减少天数数量
         */
        this.setUpStartTime = function (startTime, selectedValue, selectedDate, minusDay) {
            var newDate = new Date();
            var num = this.getDaysBetween(selectedValue, newDate);
            startTime.config.min = {
                year: selectedDate.year,
                month: selectedDate.month - 1,//关键
                date: selectedDate.date - minusDay,
                hours: 0,
                minutes: 0,
                seconds: 0
            };
            if (num >= 1) {
                startTime.config.max = {
                    year: selectedDate.year,
                    month: selectedDate.month - 1,//关键
                    date: selectedDate.date,
                    hours: 0,
                    minutes: 0,
                    seconds: 0
                };
            } else if (1 > num) {
                startTime.config.max = {
                    year: newDate.getFullYear(),
                    month: newDate.getMonth(),//关键
                    date: newDate.getDate(),
                    hours: 0,
                    minutes: 0,
                    seconds: 0
                };
            }
        }

        /**
         * 处理结束日期限制范围
         * @param endTime laydate.render结束日期对象
         * @param selectedValue value值
         * @param selectedDate date值
         * @param minusDay 正数，加上天数数量
         */
        this.setUpEndTime = function (endTime, selectedValue, selectedDate, minusDay) {
            var newDate = new Date();
            var num = common.getDaysBetween(selectedValue, newDate);
            endTime.config.min = {
                year: selectedDate.year,
                month: selectedDate.month - 1,//关键
                date: selectedDate.date,
                hours: selectedDate.hours,
                minutes: selectedDate.minutes,
                seconds: selectedDate.seconds
            };
            if (num >= 1) {
                if (minusDay > num) {
                    minusDay = parseInt(num);
                }
                endTime.config.max = {
                    year: selectedDate.year,
                    month: selectedDate.month - 1,//关键
                    date: selectedDate.date + minusDay,
                    hours: newDate.getHours(),
                    minutes: newDate.getMinutes(),
                    seconds: newDate.getSeconds()
                };
            } else if (1 > num) {
                endTime.config.max = {
                    year: newDate.getFullYear(),
                    month: newDate.getMonth(),//关键
                    date: newDate.getDate(),
                    hours: newDate.getHours(),
                    minutes: newDate.getMinutes(),
                    seconds: newDate.getSeconds()
                };
            }
        }

    }

    exports("common", common);
});
