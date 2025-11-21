<!DOCTYPE html>
<html lang="en" class="loading">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
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

      <form id="testform" class="form-horizontal" action="lyear_forms_elements.html" method="post" onsubmit="return false;">
        <div class="form-group">
          <label class="col-md-3 col-xs-3 control-label" for="example-hf-password"><span style="color: red">*</span>角色名称：</label>
          <div class="col-md-7 col-xs-7">
            <input class="form-control input-sm" type="text" placeholder="请输入角色名称.." id="roleName" name="roleName">
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 col-xs-3 control-label" for="example-hf-password">权限设置：</label>
          <div class="col-md-7 col-xs-7">
            <div class="treeboxs">
              <div id="test7" class="demo-tree"></div>
            </div>
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 col-xs-3 control-label" for="example-hf-password">角色描述：</label>
          <div class="col-md-7 col-xs-7">
            <textarea class="form-control" rows="5" placeholder="描述.." id="remarke"></textarea>
          </div>
        </div>

        <div class="fixedfoot">

          <button id="form-submit" type="button" class="btn btn-info btn-sm btn-w-sm m-r-5" lay-filter="login_button" lay-submit="">
            保存
          </button>
          <button type="button" class="btn btn-default btn-sm btn-w-sm m-r-5" onclick="closewin()">
            取消
          </button>

        </div>
      </form>

    </div>




  </div>


  <script type="text/javascript" src="/libs/js/jquery.min.js"></script>
  <script type="text/javascript" src="/libs/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="/libs/js/main.min.js"></script>
  <script src="/libs/js/layuiadmin/layui/layui.js"></script>
  <script src="/libs/js/layuiadmin/common.js"></script>
  <script src="/libs/jquery.validate.min.js"></script>
  <!-- END CONVEX JS-->
  <script>
    layui.config({
      base: '/libs/app-assets/layuiadmin/' //静态资源所在路径
    }).use(['form', 'laydate', 'tree'], function () {
      var $ = layui.$
        , form = layui.form
        , tree = layui.tree
        , laydate = layui.laydate
        , data2 =getData();

        tree.render({
    elem: '#test7'
    ,data: data2
    ,showCheckbox: true
          ,id:'demoId'

  });

      form.on('submit(login_button)', function (data) {
        var flag = $("#testform").valid();
        if(!flag){
          //没有通过验证
          return;
        }else {
          var checkData = tree.getChecked('demoId');
          console.log(checkData);
          var list = new Array();
          list = getChecked_list(checkData);
          var arr = [];
          arr  = list.split(',')
          var role_name = $("#roleName").val() ;
          var memo = $("#remarke").val() ;
          $.ajax({
            type: "post",
            dataType: 'json',
            contentType: 'application/json;charset=UTF-8',
            url: '/sysRole/saveRole',
            data: JSON.stringify({"role_name":role_name,"memo":memo,"menuIds":arr}),
            success: function(res){
              if(res.code == 0){
                closewin() ;
              }
            }
          });
        }

        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
      });

    });

    function closewin() {
      //刷新父页面
      window.parent.location.reload();
      //关闭当前弹窗
      var index = parent.layer.getFrameIndex(window.name);
      parent.layer.close(index);
    }



    function getData(){
      var data2 = [];
      $.ajax({
        url: "/sysRole/tree",    //后台数据请求地址
        type: "post",
        async:false,
        success: function(resut){
          data2 = resut.data;
        }
      });
      return data2;
    }


    // 获取选中节点的id
    function getChecked_list(data) {
      var id = "";
      $.each(data, function (index, item) {
        if (id != "") {
          id = id + "," + item.id;
        }
        else {
          id = item.id;
        }
        var i = getChecked_list(item.children);
        if (i != "") {
          id = id + "," + i;
        }
      });
      return id;
    }



    $(function() {
      //表单验证
      $("#testform").validate({
        //失去焦点校验
        onfocusout: function(element) { $(element).valid(); },
        rules: {
          roleName: {
            required: true,
          }
        },
        messages: {
          roleName: {
            required: "必填项",
          }
        },
        focusCleanup: true  //表示按键松开时候监听验证
      });
    } );

  </script>
</body>
<style type="text/css">
  input.error { border: 1px solid red; }
  label.error {
    font-weight: bold;
    color: red;
  }
</style>
</html>