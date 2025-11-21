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
                        <h4>案件基本信息</h4>
                        <ul class="card-actions1">
                            <li><a class="btn btn-info btn-xs" href="javascript:;" onclick="history.back();"><i
                                        class="mdi mdi-refresh"></i> 返回</a></li>


                        </ul>
                    </div>
                    <div class="card-body">
                        <div class="row" style="line-height:25px;">
                            <div class="col-md-3 col-xs-3">
                                庭审名称：（2022）苏00民初21号
                            </div>
                            <div class="col-md-3 col-xs-3">
                                庭审时间：2022-08-11
                            </div>
                            <div class="col-md-3 col-xs-3">
                                承办部门：民事第二审判庭
                            </div>
                            <div class="col-md-3 col-xs-3">
                                主审法官：李五
                            </div>
                            <div class="col-md-3 col-xs-3">
                                案件编号：（2022）苏00民初21号
                            </div>
                            <div class="col-md-3 col-xs-3">
                                案件名称：民间借贷纠纷
                            </div>
                            <div class="col-md-3 col-xs-3">
                                案件类型：执行案件
                            </div>
                            <div class="col-md-3 col-xs-3">
                                立案时间：2022-02-06
                            </div>
                            <div class="col-md-3 col-xs-3">
                                当事人：原告：张三 ; 被告：王五
                            </div>
                            <div class="col-md-12 col-xs-12">
                                案件描述：民间金融借贷纠纷，执行分配方案异议之诉
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-header">
                        <h4>庭审视频点播信息</h4>
                    </div>
                    <div class="card-body">

                        <ul id="myTabs" class="nav nav-tabs" role="tablist">
                            <li class="active"><a href="#home" id="home-tab" role="tab" data-toggle="tab">庭审视频</a></li>
                            <li><a href="#profile" role="tab" id="profile-tab" data-toggle="tab">笔录信息</a></li>
                            <li><a href="#other" role="tab" id="other-tab" data-toggle="tab">其他</a></li>

                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <div class="tab-pane fade active in" id="home">
                                <div class="box_video">
                                    <div class="wrap_video">
                                        <div class="main"><video src="/libs/images/sp.mp4" controls autoplay></video>
                                        </div>
                                        <div class="list">
                                            <div class="title">
                                                <h4>视频列表</h4>
                                                <div class="down" onclick="down()"><i
                                                        class="mdi mdi-cloud-download m-r-5"></i>视频下载</div>
                                            </div>
                                            <div class="content">
                                                <div class="treebox">
                                                    <div id="test7" class="demo-tree"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="profile">
                                <div class="box_video">
                                    <div class="wrap_video">
                                        <div class="main">
                                            <iframe src="/pdfjs/pdf.html?file=build/1.pdf" class="pdfbox" style="border:0"></iframe>
                                        </div>
                                        <div class="list">
                                            <div class="title">
                                                <h4>笔录列表</h4>
                                                <div class="down" onclick="down()"><i
                                                        class="mdi mdi-cloud-download m-r-5"></i>笔录下载</div>
                                            </div>
                                            <div class="content">
                                                <div class="treebox">
                                                    <div id="test8" class="demo-tree"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="other">
                                <p>原名熊耀华，籍贯江西，汉族。1938年6月7日生于香港。著名武侠小说家，新派武侠小说泰斗，与金庸、梁羽生并称为中国武侠小说三大宗师。代表作有《多情剑客无情剑》、《绝代双骄》、《英雄无泪》等。古龙把武侠小说引入了经典文学的殿堂，将戏剧、推理、诗歌等元素带入传统武侠，又将自己独特的人生哲学融入其中，使中外经典镕铸一炉，开创了近代武侠小说新纪元，将武侠文学推上了一个新的高峰。
                                </p>
                            </div>
                        </div>

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
        }).use(['index', 'table', 'laydate', 'tree'], function () {
            var admin = layui.admin
                , tree = layui.tree
                , laydate = layui.laydate
                , table = layui.table
                , data2 = [{
                    title: '早餐'
                    , id: 1
                    , spread: true
                    , children: [{
                        title: '油条'
                        , id: 5
                    }, {
                        title: '包子'
                        , id: 6
                    }, {
                        title: '豆浆'
                        , id: 7
                    }]
                }, {
                    title: '午餐'
                    , id: 2
                    , spread: true
                    , checked: true
                    , children: [{
                        title: '藜蒿炒腊肉'
                        , id: 8
                    }, {
                        title: '西湖醋鱼'
                        , id: 9
                    }, {
                        title: '小白菜'
                        , id: 10
                    }, {
                        title: '海带排骨汤'
                        , id: 11
                    }]
                }, {
                    title: '晚餐'
                    , id: 3
                    , spread: true
                    , children: [{
                        title: '红烧肉'
                        , id: 12
                        , fixed: true
                    }, {
                        title: '番茄炒蛋'
                        , id: 13
                    }]
                }, {
                    title: '夜宵'
                    , id: 4
                    , spread: true
                    , children: [{
                        title: '小龙虾'
                        , id: 14
                        , checked: true
                    }, {
                        title: '香辣蟹'
                        , id: 15
                        , disabled: true
                    }, {
                        title: '烤鱿鱼'
                        , id: 16
                    }]
                }]
                , data3 = [{
                    title: '早餐3'
                    , id: 1
                    , spread: true
                    , children: [{
                        title: '油条3'
                        , id: 5
                    }, {
                        title: '包子3'
                        , id: 6
                    }, {
                        title: '豆浆3'
                        , id: 7
                    }]
                }, {
                    title: '午餐'
                    , id: 2
                    , spread: true
                    , checked: true
                    , children: [{
                        title: '藜蒿炒腊肉'
                        , id: 8
                    }, {
                        title: '西湖醋鱼'
                        , id: 9
                    }, {
                        title: '小白菜'
                        , id: 10
                    }, {
                        title: '海带排骨汤'
                        , id: 11
                    }]
                }, {
                    title: '晚餐'
                    , id: 3
                    , spread: true
                    , children: [{
                        title: '红烧肉'
                        , id: 12
                        , fixed: true
                    }, {
                        title: '番茄炒蛋'
                        , id: 13
                    }]
                }, {
                    title: '夜宵'
                    , id: 4
                    , spread: true
                    , children: [{
                        title: '小龙虾'
                        , id: 14
                        , checked: true
                    }, {
                        title: '香辣蟹'
                        , id: 15
                        , disabled: true
                    }, {
                        title: '烤鱿鱼'
                        , id: 16
                    }]
                }];


            //开启复选框
            tree.render({
                elem: '#test7'
                , data: data2
                , showCheckbox: true
            });
            tree.render({
                elem: '#test8'
                , data: data3
                , showCheckbox: true
            });



        });
        function down() {
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
    </script>
</body>

</html>