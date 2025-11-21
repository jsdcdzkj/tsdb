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
                    <form class="form-inline" id="form" action="/sysUser/pageList.do" method="post"
                          onsubmit="return false;">

                        <div class="form-group m-r-5">
                            <label for="example-if-email">输入查询：</label>
                            <input id="query-input" class="form-control input-sm" type="text" placeholder="姓名/账号/手机">
                        </div>
                        <div class="form-group m-r-5">
                            <label for="example-if-password">所属部门：</label>
                            <select class="form-control input-sm" id="dept-select" name="example-select"
                                    size="1">
                                <option value="">请选择承办部门</option>
                                <#list deptList as dept>
                                    <option value="${dept.id}">${dept.dept_name}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="form-group m-r-5">
                            <label for="example-if-password">所属角色：</label>
                            <select class="form-control input-sm" id="role-select" name="example-select"
                                    size="1">
                                <option value="">请选择角色</option>
                                <#list roleList as role>
                                    <option value="${role.id}">${role.role_name}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="form-group">
                            <button onclick="queryDept()" class="btn btn-info btn-sm" type="submit"><i
                                        class="mdi mdi-account-search"></i>查询
                            </button>
                            <button onclick="resetDept()" class="btn btn-default btn-sm" type="button"><i
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
                    <h4>人员管理列表</h4>
                    <ul class="card-actions1">
                        <li><a class="btn btn-success m-r-5 btn-xs" href="javascript:;" onclick="add()"><i
                                        class="mdi mdi-plus"></i> 添加</a></li>

                    </ul>
                </div>
                <div class="card-body">

                    <table class="layui-hide" id="test-table-height" lay-filter="table-toolbar"></table>
                    <script type="text/html" id="table-toolbar">
                        <a class="btn btn-info btn-xs" lay-event="edit"><i class="mdi mdi-pencil"></i>编辑</a>
                        <a class="btn btn-warning btn-xs" lay-event="resetpass"><i
                                    class="mdi mdi-lock-reset"></i>重置密码</a>
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
            , url: '/sysUser/pageList.do'
            , height: 'full-350'
            , cellMinWidth: 80
            , page: true
            , limit: 10
            , cols: [[
                {field: 'name', title: '员工姓名', align: 'center'}
                , {
                    field: 'sex', title: '性别', width: 150, align: 'center', templet: function (res) {
                        if (res.sex == 0) {
                            return '<span>男</span>'
                        } else {
                            return '<span>女</span>'
                        }
                    }
                }
                , {field: 'dept_name', title: '所属部门', width: 180, align: 'center'}
                , {field: 'role_name', title: '所属角色', width: 140, align: 'center'}
                , {field: 'login_name', title: '登陆账号', width: 120, align: 'center'}
                , {field: 'phone', title: '手机', width: 120, align: 'center'}
                , {
                    field: 'is_enable', title: '启用', width: 120, align: 'center', templet: function (res) {
                        if (res.is_enable == 1) {
                            return '<input id="' + res.id + '" login_name="' + res.login_name + '" type="checkbox" checked="" lay-skin="switch" lay-filter="is_enable" lay-text="开|关">'
                        } else {
                            return '<input id="' + res.id + '" login_name="' + res.login_name + '"  type="checkbox" lay-skin="switch" lay-filter="is_enable" lay-text="开|关">'
                        }
                    }
                }
                , {fixed: 'right', title: '操作', toolbar: '#table-toolbar', width: 240, align: 'center'}
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

        form.on('switch(is_enable)', function (data) {
            lightyear.loading('show');
            $.ajax({
                type: "post",
                dataType: 'json',
                contentType: 'application/json;charset=UTF-8',
                url: '/sysUser/edit.json',
                data: JSON.stringify({
                    id: data.elem.attributes.id.value
                    , login_name: data.elem.attributes.login_name.value
                    , is_enable: data.elem.checked == true ? 1 : 0
                }),
            });
            setTimeout(function () {
                lightyear.loading('hide');
                lightyear.notify(data.elem.checked == true ? '启动成功' : '关闭成功', 'success', 5000);
            }, 1e3)
        });
        //监听行工具事件
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
                                    url: '/sysUser/edit.json',
                                    data: JSON.stringify({
                                        id: data.id
                                        , login_name: data.login_name
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
                    content: "/sysUser/edit.do",
                    success: function () {
                        var body = layer.getChildFrame('body');
                        body.find('form').find('input[name="id"]').val(data.id);
                        body.find('form').find('input[name="login_name"]').val(data.login_name);
                        body.find('form').find('input[name="login_name"]').attr('disabled', 'disabled');
                        body.find('form').find('input[name="pass"]').val(data.pass);
                        body.find('form').find('input[name="pass"]').attr('disabled', 'disabled');
                        body.find('form').find('input[name="name"]').val(data.name);
                        body.find('form').find('input[name="sex"][value="' + data.sex + '"]').attr('checked', true);
                        body.find('form').find('input[name="phone"]').val(data.phone);
                        if(data.dept_id != null){
                            body.find('form').find('select[name="dept_id"]').prepend('<option value="'+data.dept_id+'">'+data.dept_name+'</option>');
                            body.find('form').find('select[name="dept_id"]').val(data.dept_id);
                        }
                        if(data.role_id != null){
                            body.find('form').find('select[name="role_id"]').prepend('<option value="'+data.role_id+'">'+data.role_name+'</option>');
                            body.find('form').find('select[name="role_id"]').val(data.role_id);
                        }
                    }
                });

            } else if (obj.event === 'resetpass') {
                $.confirm({
                    title: '提醒',
                    content: '确认要重置密码吗？',
                    type: 'orange',
                    buttons: {
                        omg: {
                            text: '确认',
                            btnClass: 'btn-orange',
                            action: function () {
                                lightyear.loading('show');
                                $.ajax({
                                    type: "post",
                                    dataType: 'json',
                                    contentType: 'application/json;charset=UTF-8',
                                    url: '/sysUser/edit.json',
                                    data: JSON.stringify({
                                        id: data.id
                                        , login_name: data.login_name
                                        , pass: '123456'
                                        , tempPass: data.pass
                                    }),
                                });
                                setTimeout(function () {
                                    lightyear.loading('hide');
                                    lightyear.notify('重置密码成功', 'success', 5000);
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

    function queryDept() {
        table.reload('test-table-height', {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            , where: {
                query_where: $('#query-input').val()
                , dept_id: $('#dept-select').val()
                , role_id: $('#role-select').val()
            }
        });
    }

    function resetDept() {
        $('#query-input').val('')
        $('#dept-select').val('')
        $('#role-select').val('')
        queryDept()
    }

    function add() {
        var iframeObj = $(window.frameElement).attr('name');
        page("新增", "/sysUser/edit.do", iframeObj, "520", "500");
    }
</script>
</body>

</html>