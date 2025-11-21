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
                <label class="col-md-3 col-xs-3 control-label" for="example-hf-password">部门名称：</label>
                <div class="col-md-7 col-xs-7">
                    <input name="dept_name" class="form-control input-sm" type="text" placeholder="请输入部门名称..">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 col-xs-3 control-label" for="example-hf-password">部门描述：</label>
                <div class="col-md-7 col-xs-7">
                    <textarea name="describes" class="form-control" rows="6" placeholder="描述.."></textarea>
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
<!-- END CONVEX JS-->
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
                    url: '/sysDept/edit.json',
                    data: JSON.stringify({
                        id: $('input[name="id"]').val()
                        , dept_name: $('input[name="dept_name"]').val()
                        , describes: $('textarea[name="describes"]').val()
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
        // 在键盘按下并释放及提交后验证提交表单
        $("#form-horizontal").validate({
            rules: {
                dept_name: {
                    required: true,
                    minlength: 2
                },
            },
            messages: {
                dept_name: {
                    required: "请输入部门名称",
                    minlength: "部门名称必需由两个字符组成"
                },
            }
        })
    });
</script>
</body>

</html>