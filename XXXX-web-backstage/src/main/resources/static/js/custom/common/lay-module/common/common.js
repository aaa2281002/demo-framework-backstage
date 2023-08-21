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

    this.common = new function () {

        /**
         * 删除当前打开tabs
         * @param value 对应lay-id
         */
        this.closeThisTabs = function (value) {
            $(parent.top.document).find(".layui-tab[lay-filter=layuiminiTab]")
                .children(".layui-tab-title").find(">li[lay-id='" + value + "']")
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
                // w = ($(window).width() - 50);
                w = "100%";
            }
            if (h == null || h == '') {
                // h = ($(window).height() - 10);
                h = "100%";
            }
            layer.open({
                id: formId,
                type: 2,
                area: [w, h],
                fixed: false, //不固定
                maxmin: true,
                shade: 0.4,
                title: title,
                content: url,
                success: successFun,//层弹出后的成功回调方法：layero前层DOM，index当前层索引
                yes: yesFun,//第一个按钮事件，也可以叫btn1
                cancel: cancelFun, //右上角关闭按钮触发的回调：默认会自动触发关闭。如果不想关闭，return false即可
                // full:full,//还原后触发的回调：携带一个参数，即当前层DOM
                // min:min,//还原后触发的回调：携带一个参数，即当前层DOM
                // restore:restore,//还原后触发的回调：携带一个参数，即当前层DOM
                end: endFun //层销毁后触发的回调：无论是确认还是取消，只要层被销毁了，end都会执行，不携带任何参数。
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

        //验证是否小数或整数
        var regular = /^(0|(0.[1-9])|(0.[0-9]{1,2}[^0])|([1-9][0-9]*)+(.[0-9]{1,2})|([1-9][0-9]*))?$/;
        /**
         * 验证是否小数或整数
         * @param value 参数值
         * @returns {boolean} false成功， ture失败
         */
        this.isNumber = function (value) {
            if (regular.test(value)) {
                //为正确整数和小数返回 false
                return false;
            }
            //错误返回true
            return true;
        }

        /**
         * 根据form表单Id获取表单数据组装data返回
         * @param formId 表单编号
         * @returns {key:value,key:value} 返回map键值对格式值
         */
        this.serializeData = function (formId) {
            var formArray = $("#" + formId).serializeArray();
            var data = {};
            $.each(formArray, function (i, field) {
                data[field.name] = field.value;
            });
            return data;
        };

        /**
         * 根据form表单Id获取表单数据组装data返回
         * @param url 上传地址
         * @param fieldName 后台上传变量名
         * @param allowedFileTypes:['image/*'] 文件限制
         * @returns uploadImage 返回map键值对格式值
         */
        this.uploadImage = function (url, fieldName, allowedFileTypes) {
            var wangEditorIndexClose = null;
            fieldName = !fieldName ? "file" : fieldName;
            url = !url ? '/other/annex/wangeditor/image/upload' : url;
            allowedFileTypes = !allowedFileTypes ? ['image/*'] : allowedFileTypes;
            var uploadImage = {
                //上传地址
                server: url,
                //设置对应服务端接受参数变量名
                fieldName: fieldName,
                // 单个文件的最大体积限制，默认为 2M
                maxFileSize: 1 * 1024 * 1024, // 1M
                // 最多可上传几个文件，默认为 100
                maxNumberOfFiles: 1,
                // 选择文件时的类型限制，默认为 ['image/*'] 。如不想限制，则设置为 []
                allowedFileTypes: allowedFileTypes,
                // 自定义上传参数，例如传递验证的 token 等。参数会被添加到 formData 中，一起上传到服务端。
                meta: {
                    // token: 'xxx',
                    // otherKey: 'yyy'
                },
                // 将 meta 拼接到 url 参数中，默认 false
                metaWithUrl: false,
                // // 自定义增加 http  header
                // headers: {
                //     Accept: 'text/x-json',
                //     otherKey: 'xxx'
                // },
                // // 跨域是否传递 cookie ，默认为 false
                // withCredentials: true,
                // // 超时时间，默认为 10 秒
                // timeout: 5 * 1000, // 5 秒

                // 上传之前触发
                onBeforeUpload: function () {
                    wangEditorIndexClose = layer.load(5);
                },
                // 上传进度的回调函数
                onProgress: function (progress) {
                    // onProgress(progress) {       // JS 语法
                    // progress 是 0-100 的数字
                    console.log('progress', progress)
                },
                // 单个文件上传成功之后
                onSuccess: function (file, res) {
                    console.log(res);
                    layer.close(wangEditorIndexClose);
                    // onSuccess(file, res) {          // JS 语法
                    console.log(`上传成功`, res)
                },
                // 单个文件上传失败
                onFailed: function (file, res) {
                    console.log(res);
                    layer.close(wangEditorIndexClose);
                    layer.msg("系统繁忙，请稍后尝试!", {
                        icon: 2,
                        time: 3000
                    });
                    // onFailed(file, res) {           // JS 语法
                    console.log(`上传失败`, res)
                },
                // 上传错误，或者触发 timeout 超时
                onError: function (file, err, res) {  // TS 语法
                    console.log(res);
                    layer.close(wangEditorIndexClose);
                    layer.msg("系统内部错误，请联系管理员!", {
                        icon: 2,
                        time: 3000
                    });
                    // onError(file, err, res) {               // JS 语法
                    console.log(`上传出错`, err, res)
                },

                // 自定义插入图片
                customInsert: function (res, insertFn) {
                    layer.close(wangEditorIndexClose);
                    if(JSON.stringify(res) == '{}'){
                        return;
                    }
                    if (res.data == null || res.code != 0 || res.code != '0') {
                        layer.msg(res.msg, {
                            icon: 2,
                            time: 3000
                        });
                        return;
                    }
                    console.log(res);
                    // customInsert(res, insertFn) {                  // JS 语法
                    // res 即服务端的返回结果
                    // 从 res 中找到 url alt href ，然后插入图片
                    // 参数1：图片 src ，必须
                    // 参数2：图片描述文字，非必须
                    // 参数3：图片的链接，非必须
                    insertFn(res.data.url, '', '');
                },
            };
            return uploadImage;
        };
    }

    exports("common", common);
});
