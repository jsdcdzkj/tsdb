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
  <style>
    .layui-laydate-content>.layui-laydate-list {
      padding-bottom: 0px;
      overflow: hidden;
    }
    .layui-laydate-content>.layui-laydate-list>li{
      width:50%
    }
  </style>
</head>

<body class="bdwhite">
  <!-- ////////////////////////////////////////////////////////////////////////////-->


  <div class="content-wrapper openbox">   
      
      <div class="card-body">
        
        <form class="form-horizontal" action="lyear_forms_elements.html" method="post" onsubmit="return false;">
          <div class="form-group">
            <label class="col-md-3 col-xs-3 control-label" for="example-hf-email">是否开启定时：</label>
            <div class="col-md-7 col-xs-7">
              
              <div class="example-box">
                <label class="lyear-radio radio-inline radio-primary">
                  <input type="radio" name="e" value="1" checked   <#if  cron.is_use ==1>  checked </#if>><span>是</span>
                </label>
                <label class="lyear-radio radio-inline radio-primary">
                  <input type="radio" name="e" value="0" <#if  cron.is_use ==0>  checked </#if>><span>否</span>
                </label>
                
              </div>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 col-xs-3 control-label" for="example-hf-password">备份频率：</label>
            <div class="col-md-7 col-xs-7">             
              <select class="form-control input-sm" id="example-select" name="example-select" size="1">
                <option value="">请选择</option>
                <option value="0" <#if  cron.the_backup_frequency ==0>  selected </#if>>每天</option>
                <option value="1" <#if  cron.the_backup_frequency ==1>  selected </#if>>周一</option>
                <option value="2" <#if  cron.the_backup_frequency ==2>  selected </#if>>周二</option>
                <option value="3" <#if  cron.the_backup_frequency ==3>  selected </#if>>周三</option>
                <option value="4" <#if  cron.the_backup_frequency ==4>  selected </#if>>周四</option>
                <option value="5" <#if  cron.the_backup_frequency ==5>  selected </#if>>周五</option>
                <option value="6" <#if  cron.the_backup_frequency ==6>  selected </#if>>周六</option>
                <option value="7" <#if  cron.the_backup_frequency ==7>  selected </#if>>周日</option>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 col-xs-3 control-label" for="example-hf-password">备份时间：</label>
            <div class="col-md-7 col-xs-7">
              <input class="form-control input-sm" type="text" readonly id="time1" value="${cron.the_backup_time}">
            </div>
          </div>
          
        </form>
        
      </div>


    <div class="fixedfoot">

      <button id="form-submit" type="button" class="btn btn-info btn-sm btn-w-sm m-r-5" onclick="save()">
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
  <script src="/libs/js/layuiadmin/layui/layui.js"></script>
  <script src="/libs/js/layuiadmin/common.js"></script>
  <!-- END CONVEX JS-->
  <script>
    layui.config({
      base: '/libs/app-assets/layuiadmin/' //静态资源所在路径
    }).use(['form', 'laydate'], function () {
      var $ = layui.$
        , form = layui.form
        , laydate = layui.laydate;

      laydate.render({
        elem: '#time1'
        ,format:'HH:mm',//显示格式：小时：分钟
        type:'time' ,
      });
      laydate.render({
        elem: '#time2'
        , type: 'datetime'
      });

    })
    function closewin() {
      //刷新父页面
      window.parent.location.reload();
      //关闭当前弹窗
      var index = parent.layer.getFrameIndex(window.name);
      parent.layer.close(index);
    }
    
    function save() {
      var $is_use = $("input[name='e']:checked").val();
      var $the_backup_frequency = $("#example-select").val();
      var $the_backup_time = $("#time1").val();
      var id = '${cron.id}' ;
      var job_id = '${cron.job_id}' ;
      $.ajax({
        type: "post",
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8',
        url: '/cron/save',
        data: JSON.stringify({"is_use":$is_use,"the_backup_frequency":$the_backup_frequency,"the_backup_time":$the_backup_time,"id":id,"job_id":job_id}),
        success: function(res){
          if(res.code == 0){
            closewin() ;
          }
        }
      });
    }
  </script>
</body>

</html>