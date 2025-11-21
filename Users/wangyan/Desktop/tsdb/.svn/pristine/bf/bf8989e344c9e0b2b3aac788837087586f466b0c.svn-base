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

<body>
<div class="container-fluid p-t-15">

    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-body">

                    <form method="post" id="form-horizontal" action="" method="post" onsubmit="return false;">
                        <div class="form-group">
                            <label for="old-password">旧密码</label>
                            <input type="password" class="form-control" name="pass" id="old-password"
                                   placeholder="输入账号的原登录密码">
                        </div>
                        <div class="form-group">
                            <label for="new-password">新密码</label>
                            <input type="password" class="form-control" id="newPassWord" name="newPassWord" id="new-password"
                                   placeholder="输入新的密码">
                        </div>
                        <div class="form-group">
                            <label for="confirm-password">确认新密码</label>
                            <input type="password" class="form-control" name="confirmPass" id="confirm-password"
                                   placeholder="请输入正确的密码">
                        </div>
                        <button class="btn btn-primary" lay-filter="submit_button" lay-submit="">修改密码</button>
                    </form>

                </div>
            </div>
        </div>

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
    }).use(['form'], function () {
        const form = layui.form;

        form.on('submit(submit_button)', function () {
            if (!$("#form-horizontal").valid()) {
                //没有通过验证
                return;
            } else {
                $.ajax({
                    type: "post",
                    dataType: 'json',
                    url: '/sysUser/changePassword.json',
                    data: {
                        pass: $('input[name="pass"]').val()
                        , newPassWord: $('input[name="newPassWord"]').val()
                    },
                    success: function (res) {
                        if (res.code == 0) {
                            window.location.replace('/logout.do')
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
    });

    // 在键盘按下并释放及提交后验证提交表单
    $("#form-horizontal").validate({
        rules: {
            pass: {
                required: true,
                minlength: 6
            },
            newPassWord: {
                required: true,
                minlength: 6
            },
            confirmPass: {
                required: true,
                minlength: 6,
                equalTo: "#newPassWord",
            },
        },
        messages: {
            pass: {
                required: "请输入密码",
                minlength: "密码长度不能小于 6 个字符",
            },
            newPassWord: {
                required: "请输入密码",
                minlength: "密码长度不能小于 6 个字符"
            },
            confirmPass: {
                required: "请输入密码",
                minlength: "密码长度不能小于 6 个字符",
                equalTo: "两次密码输入不一致"
            },
        }
    })
</script>
</body>
</html>