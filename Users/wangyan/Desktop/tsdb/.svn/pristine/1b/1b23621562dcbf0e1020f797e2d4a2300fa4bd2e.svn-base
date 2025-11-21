<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
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
                                <label for="example-if-email">点播人：</label>
                                <input class="form-control input-sm" type="text" placeholder="请输入点播人.." id="onDemandOf">
                            </div>
                            <div class="form-group m-r-5">
                                <label for="example-if-password">庭审名称：</label>
                                <input class="form-control input-sm" type="text" placeholder="请输入庭审名称.." id="trial_name">
                            </div>
                            <div class="form-group m-r-5">
                                <label for="example-if-password">案件编号：</label>
                                <input class="form-control input-sm" type="text" placeholder="请输入案件编号.." id="case_code">
                            </div>
                            <div class="form-group">
                                <button class="btn btn-info btn-sm" type="submit" onclick="searchQuery()"><i
                                        class="mdi mdi-account-search"></i>查询</button>
                                <button class="btn btn-default btn-sm" type="button" onclick="reseat()"><i
                                        class="mdi mdi-refresh"></i>重置</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-header">
                        <h4>视频点播日志</h4>
                        <#--<ul class="card-actions1">-->
                            <#--<li><a class="btn btn-primary m-r-5 btn-xs" href="javascript:;" onclick="daochu()"><i-->
                                        <#--class="mdi mdi-file-export"></i> 导出</a></li>                            -->

                        <#--</ul>-->
                    </div>
                    <div class="card-body">

                        <table class="layui-hide" id="test-table-height" lay-filter="table-toolbar"></table>
                        <#--<script type="text/html" id="table-toolbar">-->
                           <#--<a class="btn btn-info btn-xs" lay-event="edit">点播</a> -->
                           <#--<a class="btn btn-info btn-xs" lay-event="views">下载</a>                                      -->
                        <#--</script>-->

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
        layui.config({
            base: '/libs/js/layuiadmin/' //静态资源所在路径
        }).use(['index', 'table', 'laydate'], function () {
            var admin = layui.admin
                , laydate = layui.laydate
                , table = layui.table;

            table.render({
                elem: '#test-table-height'
                , url: '/log/getList'
                ,method: 'post'
                , height: 'full-350'
                , cellMinWidth: 80
                , limit: 10
                , cols: [[
                    { type: "numbers", title: '序号', width: 80, fixed: 'left', unresize: true, sort: true,formatter: function (value, row, index) {
                            return index+1;
                        } },
                    { field: 'onDemandOf', title: '点播人', align: 'left' , width: 180, align: 'center'}

                    , { field: 'trial_name', title: '庭审名称', sort: true, align: 'center' }
                    , { field: 'case_code', title: '案件编号', width: 250 }
                    , {field: 'onDemandOfTime', title: '点播时间', width: 200, align: 'center'}
                    , {field: 'trial_time', title: '庭审时间', width: 200, align: 'center'}
                    , {field: 'case_type', title: '案件类型', width: 200, align: 'center'}
                    , {field: 'undertake_dept', title: '承办部门', width: 200, align: 'center'}
                ]]
                , page: true
                , parseData: function (item) { //将原始数据解析成 table 组件所规定的数据
                    return {
                        "code": 0,   //解析接口状态
                        "message": "成功",  //解析提示文本
                        "count": item.data.total,       //解析数据长度
                        "data": item.data.list //解析数据列表
                    };
                },
                id: 'testReload'
            });

            //日期时间选择器
            laydate.render({
                elem: '#time1'
                , type: 'datetime'
            });
            laydate.render({
                elem: '#time2'
                , type: 'datetime'
            });

            //监听行工具事件
            table.on('tool(table-toolbar)', function (obj) {
                var data = obj.data;
                if (obj.event === 'edit') {
                    // var iframeObj = $(window.frameElement).attr('name');
                    // page("新增", "add.html", iframeObj, "520", "500");
                    location.href = 'detail.html'

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
                                    // $.alert('你点击了确认!');
                                    // var loading = layer.load('备份中...', {
                                    //     icon: 16, shade: [0.8, '#000'] //0.1透明度的白色背景
                                    // });
                                    // setTimeout(function (data) {
                                    //     //最后数据加载完 让 loading层消失
                                    //     layer.close(loading);
                                    // }, 5000);
                                    lightyear.loading('show');
                                    // 假设ajax提交操作
                                    setTimeout(function () {
                                        lightyear.loading('hide');
                                        lightyear.notify('下载成功，页面即将自动跳转~', 'success', 5000);
                                    }, 1e3)
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
        function daochu() {
            $.confirm({
                        title: '提醒',
                        content: '确认导出吗？',
                        type: 'green',
                        buttons: {
                            omg: {
                                text: '确认',
                                btnClass: 'btn-green',
                                action: function () {
                                    // $.alert('你点击了确认!');
                                    // var loading = layer.load('备份中...', {
                                    //     icon: 16, shade: [0.8, '#000'] //0.1透明度的白色背景
                                    // });
                                    // setTimeout(function (data) {
                                    //     //最后数据加载完 让 loading层消失
                                    //     layer.close(loading);
                                    // }, 5000);
                                    lightyear.loading('show');
                                    // 假设ajax提交操作
                                    setTimeout(function () {
                                        lightyear.loading('hide');
                                        lightyear.notify('导出成功，页面即将自动跳转~', 'success', 5000);
                                    }, 1e3)
                                }
                            },
                            close: {
                                text: '取消',
                            }
                        }
                    });
        }

        function searchQuery(){
            var $onDemandOf = $("#onDemandOf").val() ;
            var $trial_name = $("#trial_name").val() ;
            var $case_code = $("#case_code").val() ;
            layui.use('table', function() {
                var table = layui.table;
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        onDemandOf: $onDemandOf,
                        trial_name: $trial_name,
                        case_code: $case_code
                    }
                });
            });
        }

        function reseat() {
            $("#onDemandOf").val("") ;
            $("#trial_name").val("") ;
           $("#case_code").val("") ;
            searchQuery() ;
        }

        function enter(evt) {
            var evt=evt?evt:(window.event?window.event:null);//兼容IE和FF
            if (evt.keyCode==13){
                searchQuery() ;
            }

        }
    </script>
</body>

</html>