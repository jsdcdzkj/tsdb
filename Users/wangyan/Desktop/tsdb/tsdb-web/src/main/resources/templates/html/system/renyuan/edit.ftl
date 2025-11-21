<!DOCTYPE html>
<html lang="en" class="loading">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="open">
    <meta name="keywords" content="open">
    <title>open</title>
    <link href="/libs/css/bootstrap.min.css" rel="stylesheet">
    <link href="/libs/css/materialdesignicons.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/libs/js/jconfirm/jquery-confirm.min.css">
    <link href="/libs/css/style.min.css" rel="stylesheet">

    <!-- layui Css-->
    <link rel="stylesheet" href="/libs/js/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/libs/js/layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" href="/libs/js/layuiadmin/style/css.css" media="all">
</head>

<body class="bdwhite">
<!-- ////////////////////////////////////////////////////////////////////////////-->


<div class="content-wrapper openbox">

    <div class="card-body">

        <form class="form-horizontal" id="form-horizontal" action="lyear_forms_elements.html" method="post"
              onsubmit="return false;">
            <input name="id" type="text" hidden>
            <div class="form-group">
                <label class="col-md-3 col-xs-3 control-label" for="example-hf-password">登录账号：</label>
                <div class="col-md-7 col-xs-7">
                    <input name="login_name" class="form-control input-sm" type="text" placeholder="请输入登录账号..">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 col-xs-3 control-label" for="example-hf-password">登录密码：</label>
                <div class="col-md-7 col-xs-7">
                    <input name="pass" class="form-control input-sm" type="password" placeholder="请输入登录密码..">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 col-xs-3 control-label" for="example-hf-password">人员姓名：</label>
                <div class="col-md-7 col-xs-7">
                    <input name="name" class="form-control input-sm" type="text" placeholder="请输入人员姓名..">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 col-xs-3 control-label" for="example-hf-email">性别：</label>
                <div class="col-md-7 col-xs-7">
                    <div class="example-box">
                        <label class="lyear-radio radio-inline radio-primary">
                            <input type="radio" name="sex" value="0" checked=""><span>男</span>
                        </label>
                        <label class="lyear-radio radio-inline radio-primary">
                            <input type="radio" name="sex" value="1"><span>女</span>
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 col-xs-3 control-label" for="example-hf-password">手机号码：</label>
                <div class="col-md-7 col-xs-7">
                    <input name="phone" class="form-control input-sm"
                           type="text" placeholder="请输入手机号码..">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 col-xs-3 control-label" for="example-hf-password">所属部门：</label>
                <div class="col-md-7 col-xs-7">
                    <select name="dept_id" class="form-control input-sm" size="1">
                        <option value="">请选择所属部门</option>
                        <#list deptList as dept>
                            <option value="${dept.id}">${dept.dept_name}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 col-xs-3 control-label" for="example-hf-password">所属角色：</label>
                <div class="col-md-7 col-xs-7">
                    <select name="role_id" class="form-control input-sm" size="1">
                        <option value="">请选择角色</option>
                        <#list roleList as role>
                            <option value="${role.id}">${role.role_name}</option>
                        </#list>
                    </select>
                </div>
            </div>
        </form>
    </div>

    <div class="fixedfoot">

        <button id="form-submit" type="button" class="btn btn-info btn-sm btn-w-sm m-r-5" lay-filter="submit_button"
                lay-submit="">
            保存
        </button>
        <button type="button" class="btn btn-default btn-sm btn-w-sm m-r-5" onclick="closewin()">
            取消
        </button>

    </div>

</div>

<script type="text/javascript" src="/libs/js/jquery.min.js"></script>
<script type="text/javascript" src="/libs/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/libs/js/main.min.js"></script>
<!--对话框-->
<script src="/libs/js/jconfirm/jquery-confirm.min.js"></script>
<!--消息提示-->
<script src="/libs/js/bootstrap-notify.min.js"></script>
<script type="text/javascript" src="/libs/js/lightyear.js"></script>
<script type="text/javascript" src="/libs/js/main.min.js"></script>

<script src="/libs/js/layuiadmin/layui/layui.js"></script>
<script src="/libs/js/layuiadmin/common.js"></script>
<script src="/libs/jquery.validate.min.js"></script>
<script>
    layui.config({
        base: '/libs/js/layuiadmin/' //静态资源所在路径
    }).use(['form', 'laydate'], function () {
        const form = layui.form;
        form.on('submit(submit_button)', function () {
            if (!$("#form-horizontal").valid()) {
                //没有通过验证
                return;
            } else {
                $.ajax({
                    type: "post",
                    dataType: 'json',
                    contentType: 'application/json;charset=UTF-8',
                    url: '/sysUser/edit.json',
                    data: JSON.stringify({
                        id: $('input[name="id"]').val()
                        , login_name: $('input[name="login_name"]').val()
                        , pass: $('input[name="pass"]').val()
                        , name: $('input[name="name"]').val()
                        , phone: $('input[name="phone"]').val()
                        , sex: $('input[name="sex"][checked]').val()
                        , dept_id: $('select[name="dept_id"]').val()
                        , dept_name: $('select[name="dept_id"] option:selected').text()
                        , role_id: $('select[name="role_id"]').val()
                        , role_name: $('select[name="role_id"] option:selected').text()
                    }),
                    success: function (res) {
                        if (res.code == 0) {
                            closewin();
                        } else {
                            lightyear.loading('show');
                            // 假设ajax提交操作
                            setTimeout(function () {
                                lightyear.loading('hide');
                                lightyear.notify(res.msg, 'warning', 5000);
                            }, 1e3)
                        }
                    }
                });
            }
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });

    })

    function closewin() {
        //刷新父页面
        window.parent.location.reload();
        //关闭当前弹窗
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }

    $().ready(function () {
        jQuery.validator.addMethod("isPhone", function (value, element) {
            var length = value.length;
            var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "请填写正确的手机号码");//可以自定义默认提示信息

        // 在键盘按下并释放及提交后验证提交表单
        $("#form-horizontal").validate({
            rules: {
                login_name: {
                    required: true,
                    minlength: 2
                },
                pass: {
                    required: true,
                    minlength: 6
                },
                name: {
                    required: true,
                    minlength: 2
                },
                phone: {
                    isPhone: true,
                },
            },
            messages: {
                login_name: {
                    required: "请输入登录账号",
                    minlength: "登录账号必需由两个字符组成"
                },
                pass: {
                    required: "请输入密码",
                    minlength: "密码长度不能小于 6 个字符"
                },
                name: {
                    required: "请输入人员姓名",
                    minlength: "人员姓名必需由两个字符组成"
                },
            }
        })
    });
</script>
</body>

</html>