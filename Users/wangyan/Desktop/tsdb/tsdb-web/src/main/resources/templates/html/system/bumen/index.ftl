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
                </div>
                <div class="card-body">
                    <form class="form-inline" action="index.html" method="post" onsubmit="return false;">

                        <div class="form-group m-r-5">
                            <label for="example-if-email">部门名称：</label>
                            <input id="query-input" class="form-control input-sm" type="text" placeholder="部门名称">
                        </div>

                        <div class="form-group">
                            <button onclick="queryUser()" class="btn btn-info btn-sm" type="submit"><i
                                        class="mdi mdi-account-search"></i>查询
                            </button>
                            <button onclick="resetUser()" class="btn btn-default btn-sm" type="button"><i
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
                    <h4>部门管理列表</h4>
                    <ul class="card-actions1">
                        <li><a class="btn btn-success m-r-5 btn-xs" href="javascript:;" onclick="add()"><i
                                        class="mdi mdi-plus"></i> 添加</a></li>

                    </ul>
                </div>
                <div class="card-body">

                    <table class="layui-hide" id="test-table-height" lay-filter="table-toolbar"></table>
                    <script type="text/html" id="table-toolbar">
                        <a class="btn btn-info btn-xs" lay-event="edit"><i class="mdi mdi-pencil"></i>编辑</a>
                        <a class="btn btn-danger btn-xs" lay-event="del"><i class="mdi mdi-delete"></i>删除</a>
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
<script type="text/html" id="formatTime">
    {{# if(d.updateTime!=null){}}
    <div>{{layui.util.toDateString(d.updateTime, 'yyyy-MM-dd HH:mm:ss')}}</div>
    {{# } }}
</script>
<script type="text/javascript">
    let table;
    let form;
    layui.config({
        base: '/libs/js/layuiadmin/' //静态资源所在路径
    }).use(['index', 'table'], function () {
        table = layui.table;
        form = layui.form;

        table.render({
            elem: '#test-table-height'
            , method: 'post'
            , dataType: 'json'
            , url: '/sysDept/pageList.do'
            , height: 'full-350'
            , cellMinWidth: 80
            , limit: 10
            , cols: [[
                {field: 'dept_name', title: '部门名称', align: 'left', width: 140}
                , {field: 'sysUserCount', title: '部门成员', width: 150, align: 'center'}
                , {field: 'updateTime', title: '更新时间', width: 200, sort: true, align: 'center',templet :"#formatTime"}
                , {field: 'describes', title: '部门描述', align: 'left'}
                , {
                    field: 'is_enable', title: '启用', width: 120, align: 'center', templet: function (res) {
                        if (res.is_enable == 1) {
                            return '<input id="' + res.id + '" dept_name="' + res.dept_name + '" type="checkbox" checked="" lay-skin="switch" lay-filter="is_enable" lay-text="开|关">'
                        } else {
                            return '<input id="' + res.id + '" dept_name="' + res.dept_name + '" type="checkbox" lay-skin="switch" lay-filter="is_enable" lay-text="开|关">'
                        }
                    }
                }
                , {fixed: 'right', title: '操作', toolbar: '#table-toolbar', width: 160, align: 'center'}
            ]]
            , page: true
            , parseData: function (item) { //将原始数据解析成 table 组件所规定的数据
                return {
                    "code": 0,   //解析接口状态
                    "message": "成功",  //解析提示文本
                    "count": item.data.total,       //解析数据长度
                    "data": item.data.list //解析数据列表
                };
            }
        });

        //监听行工具事件
        form.on('switch(is_enable)', function (data) {
            lightyear.loading('show');
            $.ajax({
                type: "post",
                dataType: 'json',
                contentType: 'application/json;charset=UTF-8',
                url: '/sysDept/edit.json',
                data: JSON.stringify({
                    id: data.elem.attributes.id.value
                    , dept_name: data.elem.attributes.dept_name.value
                    , is_enable: data.elem.checked == true ? 1 : 0
                }),
            });
            setTimeout(function () {
                lightyear.loading('hide');
                lightyear.notify(data.elem.checked == true ? '启动成功' : '关闭成功', 'success', 5000);
            }, 1e3)
        });
        table.on('tool(table-toolbar)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                $.confirm({
                    title: '提醒',
                    content: '确认删除吗？',
                    type: 'red',
                    buttons: {
                        omg: {
                            text: '确认',
                            btnClass: 'btn-red',
                            action: function () {
                                lightyear.loading('show');
                                $.ajax({
                                    type: "post",
                                    dataType: 'json',
                                    contentType: 'application/json;charset=UTF-8',
                                    url: '/sysDept/edit.json',
                                    data: JSON.stringify({
                                        id: data.id
                                        , dept_name: data.dept_name
                                        , is_delete: '1'
                                    }),
                                });
                                setTimeout(function () {
                                    table.reload('test-table-height');
                                    lightyear.loading('hide');
                                    lightyear.notify('删除成功', 'success', 5000);
                                }, 1e3)
                            }
                        },
                        close: {
                            text: '取消',
                        }
                    }
                });
            } else if (obj.event === 'edit') {
                layer.open({
                    type: 2,
                    title: "编辑",
                    area: [520 + 'px', 500 + 'px'],
                    fixed: false, //不固定
                    content: "/sysDept/edit.do",
                    success: function () {
                        var body = layer.getChildFrame('body');
                        body.find('form').find('input[name="id"]').val(data.id);
                        body.find('form').find('input[name="dept_name"]').val(data.dept_name);
                        body.find('form').find('textarea[name="describes"]').val(data.describes);
                    }
                });

            }
        });

    });

    function queryUser() {
        table.reload('test-table-height', {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            , where: {
                dept_name: $('#query-input').val()
            }
        });
    }

    function resetUser() {
        $('#query-input').val('')
        queryUser()
    }

    function add() {
        var iframeObj = $(window.frameElement).attr('name');
        page("新增", "/sysDept/edit.do", iframeObj, "520", "500");
    }
</script>
</body>

</html>