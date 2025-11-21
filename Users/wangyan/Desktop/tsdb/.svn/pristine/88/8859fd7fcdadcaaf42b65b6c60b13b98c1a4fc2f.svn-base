<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>列表页</title>
    <link rel="icon" href="favicon.ico" type="image/ico">
    <meta name="keywords" content="列表页">
    <meta name="description" content="列表页">
    <link href="/libs/css/bootstrap.min.css" rel="stylesheet">
    <link href="/libs/css/materialdesignicons.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/libs/js/jconfirm/jquery-confirm.min.css">
    <link href="/libs/css/style.min.css" rel="stylesheet">

    <!-- layui Css-->
    <link rel="stylesheet" href="/libs/js/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/libs/js/layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" href="/libs/js/layuiadmin/style/css.css" media="all">
</head>

<body>
<div class="container-fluid p-t-15">

    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header">
                    <h4>筛选条件</h4>
                    <!-- <ul class="card-actions1">
                        <li><a class="btn btn-info m-r-5 btn-xs" href="javascript:;"><i
                                    class="mdi mdi-account-search"></i> 查询</a></li>
                        <li><a class="btn btn-default m-r-5 btn-xs" href="javascript:;"><i
                                    class="mdi mdi-refresh"></i> 重置</a></li>

                    </ul> -->
                </div>
                <div class="card-body">
                    <form class="form-inline" action="index.html" method="post" onsubmit="return false;">

                        <div class="form-group m-r-5">
                            <label for="example-if-email">庭审编号：</label>
                            <input class="form-control input-sm" type="text" id="case_code" placeholder="请输入庭审编号..">
                        </div>
                        <div class="form-group m-r-5">
                            <label for="example-if-password">案件名称：</label>
                            <input class="form-control input-sm" type="text" id="case_name" placeholder="请输入案件名称..">
                        </div>
                        <div class="form-group m-r-5">
                            <label for="example-if-password">案件类型：</label>
                            <select class="form-control input-sm" id="case_type" name="example-select"
                                    size="1">
                                <option value="">请选择案件类型</option>
                                <option value="1">刑事案件</option>
                                <option value="2">民事案件</option>
                                <option value="3">执行案件</option>
                            </select>
                        </div>
<#--                        <div class="form-group m-r-5">-->
<#--                            <label for="example-if-password">承办部门：</label>-->
<#--                            <select class="form-control input-sm" id="undertake_dept" name="example-select"-->
<#--                                    size="1">-->
<#--                                <option value="">请选择承办部门</option>-->
<#--                                <#list sysDepts as dept>-->
<#--                                    <option value="${dept.dept_name}">${dept.dept_name}</option>-->
<#--                                </#list>-->
<#--                            </select>-->
<#--                        </div>-->
                        <div class="form-group m-r-5">
                            <label for="example-if-password">庭审时间：</label>
                            <input class="form-control input-sm" type="text" id="time1">
                        </div>
                        <div class="form-group m-r-5">
                            <label for="example-if-password">立案时间：</label>
                            <input class="form-control input-sm" type="text" id="time2">
                        </div>
                        <div class="form-group">
                            <button class="btn btn-info btn-sm" onclick="queryData()" type="submit"><i
                                        class="mdi mdi-account-search"></i>查询
                            </button>
                            <button class="btn btn-default btn-sm" type="reset"><i
                                        class="mdi mdi-refresh"></i>重置
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header">
                    <h4>庭审视频点播列表</h4>
                    <!-- <ul class="card-actions1">
                        <li><a class="btn btn-primary m-r-5 btn-xs" href="javascript:;" onclick="plbf()"><i
                                    class="mdi mdi-content-paste"></i> 批量备份</a></li>
                        <li><a class="btn btn-success m-r-5 btn-xs" href="javascript:;" onclick="add()"><i
                                    class="mdi mdi-history"></i> 定时自动备份</a></li>

                    </ul> -->
                </div>
                <div class="card-body">

                    <table class="layui-hide" id="test-table-height" lay-filter="table-toolbar"></table>
                    <script type="text/html" id="table-toolbar">
                        <div>
                            <a class="btn btn-info btn-xs m-r-5" lay-event="edit">点播</a>
                            <a class="btn btn-info btn-xs" lay-event="views">下载</a>
                        </div>
                    </script>

                </div>
            </div>
        </div>

    </div>

</div>

<script type="text/javascript" src="/libs/js/jquery.min.js"></script>
<script type="text/javascript" src="/libs/js/bootstrap.min.js"></script>
<!--对话框-->
<script src="/libs/js/jconfirm/jquery-confirm.min.js"></script>
<!--消息提示-->
<script src="/libs/js/bootstrap-notify.min.js"></script>
<script type="text/javascript" src="/libs/js/lightyear.js"></script>
<script type="text/javascript" src="/libs/js/main.min.js"></script>

<script src="/libs/js/layuiadmin/layui/layui.js"></script>
<script src="/libs/js/layuiadmin/common.js"></script>
<script type="text/javascript">
    var admin, laydate, table;
    layui.config({
        base: '/libs/js/layuiadmin/' //静态资源所在路径
    }).use(['index', 'table', 'laydate'], function () {
        admin = layui.admin
            , laydate = layui.laydate
            , table = layui.table;

        table.render({
            elem: '#test-table-height'
            , url: "/bizCaseInfo/getCaseInfosDb.do"
            , height: 'full-350'
            , cellMinWidth: 80
            , method: 'post'
            , contentType: 'application/json'
            , dataType: 'json'
            , page: true
            , limit: 10
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'trial_name', title: '庭审名称', align: 'center'}
                , {field: 'trial_time', title: '庭审时间', width: 200, align: 'center'}
                , {field: 'case_code', title: '案件编码', sort: true, align: 'center'}
                , {field: 'case_name', title: '案件名称', sort: true, align: 'center'}
                , {field: 'case_time', title: '立案时间', width: 200, align: 'center'}
                , {field: 'case_type', title: '案件类型', width: 120, align: 'center'}
                , {field: 'undertake_dept', title: '承办部门', width: 120,align: 'center'}
                , {
                    field: 'trial_time', title: '当事人信息', align: 'center', templet: function (res) {
                        return '<span>' + '原告:' + res.plaintiff + '被告:' + res.defendant + '</span>'
                    }
                }
                , {field: 'case_memo', title: '案由描述', align: 'center'}
                , {field: 'num', title: '点播次数',width: 100, align: 'center'}
                , {fixed: 'right', title: '操作', toolbar: '#table-toolbar', width: 120, align: 'center'}
            ]]
            , where: {
                'case_code': $("#case_code").val(),
                'case_name': $("#case_name").val(),
                'case_type': $("#case_type").val(),
                // 'undertake_dept': $("#undertake_dept").val(),
                'trial_time': $("#time1").val(),
                'case_time': $("#time2").val()
            }
            , parseData: function (item) { //将原始数据解析成 table 组件所规定的数据
                return {
                    "code": 0,   //解析接口状态
                    "message": "成功",  //解析提示文本
                    "count": item.data.total,       //解析数据长度
                    "data": item.data.list //解析数据列表
                };
            }
        });

        //日期时间选择器
        laydate.render({
            elem: '#time1'
            , type: 'date'
        });
        laydate.render({
            elem: '#time2'
            , type: 'date'
        });

        //监听行工具事件
        table.on('tool(table-toolbar)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                // var iframeObj = $(window.frameElement).attr('name');
                // page("新增", "add.html", iframeObj, "520", "500");
                location.href = '/bizCaseInfo/toCaseDbDetail.do?id=' + obj.data.id

            } else if (obj.event == 'views') {
                $.confirm({
                    title: '提醒',
                    content: '确认下载吗？',
                    type: 'green',
                    buttons: {
                        omg: {
                            text: '确认',
                            btnClass: 'btn-green',
                            action: function () {
                                lightyear.loading('show','正在打包下载中，文件较大请耐心等待！');
                                $.ajax({
                                    type: "post",
                                    url: '/bizCaseInfo/dowVideoAndCount.do?caseCode='+data.case_code,
                                    success: function(res){
                                        lightyear.loading('hide');
                                        if(res.code == 0){
                                            window.location.href = "/common/temdownload?fileName=" + encodeURI(res.data) + "&delete=" + true + "&name=" + "视频和笔录.zip";
                                        }else {
                                            lightyear.notify(res.msg, 'warning', 5000);
                                        }
                                    }
                                });
                            }
                        },
                        close: {
                            text: '取消',
                        }
                    }
                });
            }
        });

    });

    function add() {
        var iframeObj = $(window.frameElement).attr('name');
        page("定时自动备份", "add.html", iframeObj, "520", "500");
    }

    function queryData() {
        table.reload('test-table-height', {<!-- -->
            where: {
                'case_code': $("#case_code").val(),
                'case_name': $("#case_name").val(),
                'case_type': $("#case_type").val(),
                // 'undertake_dept': $("#undertake_dept").val(),
                'trial_time': $("#time1").val(),
                'case_time': $("#time2").val()
            },//表格重载时设置新的搜索参数
        });
    }
</script>
</body>

</html>