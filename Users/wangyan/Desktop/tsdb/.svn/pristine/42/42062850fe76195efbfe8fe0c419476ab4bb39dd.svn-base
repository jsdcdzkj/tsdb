<!doctype html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>登陆页面</title>
    <script type="text/javascript" src="../../libs/jquery-3.3.1.min.js" ></script>
    <script type="text/javascript" src="../../libs/base64.min.js" ></script>
    <link rel="stylesheet" href="../../libs/layui/css/layui.css">
</head>

<body>

<div class="layui-container">
    <div class="layui-progress" style="margin: 15px 0 30px;">
        <div class="layui-progress-bar" lay-percent="100%"></div>
    </div>


    <div class="layui-btn-container">
        <button class="layui-btn" test-active="test-form" id="yl">预览</button>
        <button type="button" class="layui-btn" id="test1">
            <i class="layui-icon">&#xe67c;</i>上传文件
        </button>
        <button class="layui-btn" onclick="hehe()" id="xz">下载</button>

    </div>

    <blockquote class="layui-elem-quote" style="margin-top: 30px;">
        <div class="layui-text">
            <ul>
                <li>你当前预览的是：<span>layui-v<span id="version"></span></span></li>
                <li>layui 是一套开源的 Web UI（界面）组件库。这是一个极其简洁的演示页面</li>
            </ul>
        </div>
    </blockquote>
</div>
<script src="../../libs/layui/layui.js"></script>
<script>
    var filename = ''
    function hehe() {
        let link =
            'http://192.168.0.96/%E3%80%90%E5%86%99%E4%BD%9C%E3%80%91%E7%AE%A1%E7%90%86%E7%B1%BB%E8%81%94%E8%80%83%3C%E9%A2%98%E6%B5%B7%E8%AF%BE1%3E.mp4'
        let fileName = '可怕.mp4'
        let x = new XMLHttpRequest()
        x.open('GET', link, true)
        x.responseType = 'blob'
        x.onload = (e) => {
            let url = window.URL.createObjectURL(x.response)
            let a = document.createElement('a')
            a.href = url
            a.download = fileName
            a.click()
        }
        x.send()
    }
    layui.use(function(){
        var layer = layui.layer
            ,form = layui.form
            ,laypage = layui.laypage
            ,element = layui.element
            ,laydate = layui.laydate
            ,upload = layui.upload
            ,util = layui.util;

        upload.render({
            elem: '#test1' //绑定元素
            ,url: '/upLoadFile.json' //上传接口
            ,exts:'doc|docx'
            , multiple: true
            ,done: function(res){
                //上传完毕回调
                filename = res.msg
            }
            ,error: function(){
                //请求异常回调
                console.log(1)
            }
        });

        //日期
        laydate.render({
            elem: '#test2'
            ,value: new Date()
            ,isInitValue: true
        });

        //触发事件
        util.on('test-active', {
            'test-form': function(){
                layer.open({
                    type: 2,
                    area: ['90%','90%'],
                    // content: 'http://192.168.0.93:8888/onlinePreview?url=' + ss
                    content: 'http://192.168.0.100:8080/' + filename
                });
            }
        });
    });
</script>
</body>
</html>
