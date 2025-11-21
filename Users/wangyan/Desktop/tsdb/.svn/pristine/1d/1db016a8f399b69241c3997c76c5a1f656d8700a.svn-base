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
                    <h4>案件基本信息</h4>
                    <ul class="card-actions1">
                        <li><a class="btn btn-info btn-xs" href="javascript:;" onclick="history.back();"><i
                                        class="mdi mdi-refresh"></i> 返回</a></li>


                    </ul>
                </div>
                <div class="card-body">
                    <div class="row" style="line-height:25px;">
                        <div class="col-md-3 col-xs-3">
                            庭审名称：<span id="trial_name">（2022）苏00民初21号</span>
                        </div>
                        <div class="col-md-3 col-xs-3">
                            庭审时间：<span id="trial_time">2022-08-11</span>
                        </div>
                        <div class="col-md-3 col-xs-3">
                            承办部门：<span id="undertake_dept">民事第二审判庭</span>
                        </div>
                        <div class="col-md-3 col-xs-3">
                            主审法官：<span id="presiding_judges">李五</span>
                        </div>
                        <div class="col-md-3 col-xs-3">
                            案件编号：<span id="case_code">（2022）苏00民初21号</span>
                        </div>
                        <div class="col-md-3 col-xs-3">
                            案件名称：<span id="case_name">民间借贷纠纷</span>
                        </div>
                        <div class="col-md-3 col-xs-3">
                            案件类型：<span id="case_type">执行案件</span>
                        </div>
                        <div class="col-md-3 col-xs-3">
                            立案时间：<span id="case_time">2022-02-06</span>
                        </div>
                        <div class="col-md-3 col-xs-3">
                            当事人：原告：<span id="plaintiff">张三</span> ; 被告：<span id="defendant">王五</span>
                        </div>
                        <div class="col-md-12 col-xs-12">
                            案件描述：<span id="case_memo">民间金融借贷纠纷，执行分配方案异议之诉</span>
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
<#--                        <li><a href="#other" role="tab" id="other-tab" data-toggle="tab">其他</a></li>-->

                    </ul>
                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade active in" id="home">
                            <div class="box_video">
                                <div class="wrap_video">
                                    <div class="main">
                                        <#--                                            <video src="/libs/images/sp.mp4" controls autoplay></video>-->
                                        <video id="video" src="" controls autoplay></video>
                                    </div>
                                    <div class="list">
                                        <div class="title">
                                            <h4>视频列表</h4>
                                            <div class="down" onclick="down()"><i
                                                        class="mdi mdi-cloud-download m-r-5"></i>打包下载
                                            </div>
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
                                        <#--                                            <iframe src="http://localhost:8888/AJ00012_2022-10-08/笔录/笔录001.pdf" class="pdfbox" style="border:0"></iframe>-->
                                        <iframe id="showPdf" src="" class="pdfbox"
                                                style="border:0"></iframe>
                                    </div>
                                    <div class="list">
                                        <div class="title">
                                            <h4>笔录列表</h4>
                                            <div class="down" onclick="downCourt()"><i
                                                        class="mdi mdi-cloud-download m-r-5"></i>笔录下载
                                            </div>
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
                            <p>
                                原名熊耀华，籍贯江西，汉族。1938年6月7日生于香港。著名武侠小说家，新派武侠小说泰斗，与金庸、梁羽生并称为中国武侠小说三大宗师。代表作有《多情剑客无情剑》、《绝代双骄》、《英雄无泪》等。古龙把武侠小说引入了经典文学的殿堂，将戏剧、推理、诗歌等元素带入传统武侠，又将自己独特的人生哲学融入其中，使中外经典镕铸一炉，开创了近代武侠小说新纪元，将武侠文学推上了一个新的高峰。
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
    var admin, tree, laydate, table, data2, data3;

    layui.config({
        base: '/libs/js/layuiadmin/' //静态资源所在路径
    }).use(['index', 'table', 'laydate', 'tree'], function () {
        admin = layui.admin
            , tree = layui.tree
            , laydate = layui.laydate
            , table = layui.table


        //开启复选框





    });
    $(function () {
        var id = '${ id }';
        setTimeout(function () {
            getDetail(id);
        }, 300)
    });

    //获取案件详情
    function getDetail(id) {
        var that = this;
        $.ajax({
            type: "post",
            url: "/bizCaseInfo/getCaseDetail.do",
            data: {
                "id": id
            },
            dataType: "json",
            success: function (res) {
                if (res.code == 0) {
                    //庭审数据
                    var bizCaseInfo = res.data.bizCaseInfo;
                    $("#trial_name").html(bizCaseInfo.trial_name);
                    $("#trial_time").html(bizCaseInfo.trial_time);
                    $("#undertake_dept").html(bizCaseInfo.undertake_dept);
                    $("#presiding_judges").html(bizCaseInfo.presiding_judges);
                    $("#case_code").html(bizCaseInfo.case_code);
                    $("#case_name").html(bizCaseInfo.case_name);
                    $("#case_type").html(bizCaseInfo.case_type);
                    $("#case_time").html(bizCaseInfo.case_time);
                    $("#plaintiff").html(bizCaseInfo.plaintiff);
                    $("#defendant").html(bizCaseInfo.defendant);
                    $("#case_memo").html(bizCaseInfo.case_memo);
                    //视频数据
                    var bizCaseVideos = res.data.bizCaseVideos;
                    var video_hc = [];
                    for (const i in bizCaseVideos) {
                        var v = {};
                        v.title = bizCaseVideos[i].video_name;
                        v.id = bizCaseVideos[i].id;
                        v.url = bizCaseVideos[i].video_url;
                        v.checked = false;
                        video_hc.push(v);
                    }
                    //视频树
                    var data2 = [{
                        title: '合成视频'
                        , id: 'c1'
                        , spread: true
                        , checked: false
                        , children: video_hc
                    }, {
                        title: '高清视频'
                        , id: 'c2'
                        , spread: true
                        , checked: false
                        , children: []
                    }]
                    var upload_path = res.data.upload_path;
                    tree.render({
                        elem: '#test7'
                        , data: data2
                        , showCheckbox: true
                        ,id:'demoId'
                        , click: function (obj) {
                            $.ajax({
                                type: "post",
                                url: "/bizCaseInfo/genUserDbRecord.do",
                                data: {
                                    "case_code": bizCaseInfo.case_code
                                },
                                dataType: "json",
                                success: function (res) {
                                    if(res.code == 0){
                                        $("#video").attr('src', upload_path+obj.data.url);
                                    }else{

                                    }
                                }
                            });

                        }
                    });
                    //笔录数据
                    var bizCourtRecords = res.data.bizCourtRecords;
                    var court_bl = [];
                    for (const i in bizCourtRecords) {
                        var v = {};
                        v.title = bizCourtRecords[i].record_name;
                        v.id = bizCourtRecords[i].id;
                        v.url = bizCourtRecords[i].record_url;
                        v.checked = false;
                        court_bl.push(v);
                    }
                    //笔录树
                    data3 = [{
                        title: '笔录'
                        , id: 'c1'
                        , spread: true
                        , children: court_bl
                    }];
                    tree.render({
                        elem: '#test8'
                        , data: data3
                        , showCheckbox: true
                        ,id:'courtId'
                        , click: function (obj) {
                            layer.load();
                            setTimeout(function(){
                                layer.closeAll('loading');
                            }, 3000);
                            $("#showPdf").attr("src","/bizCaseInfo/showPdf?recordUrl="+obj.data.url+"&fileName="+obj.data.title);
                        }
                    });
                }
            }
        });
    }

    function down() {
        var arr = [];
        var list = new Array();
        var checkData = tree.getChecked('demoId');

        list = getChecked_list(checkData);
        arr= list.split(',');
        if(list.length == 0){
            lightyear.notify("未选中数据，禁止下载", 'warning', 5000);
        }else {
            $.confirm({
                title: '提醒',
                content: '确认下载吗？',
                type: 'green',
                buttons: {
                    omg: {
                        text: '确认',
                        btnClass: 'btn-green',
                        action: function () {
                            lightyear.loading('show');
                            $.ajax({
                                type: "post",
                                url: '/bizCaseInfo/dowVideo.do?strings='+arr,
                                success: function(res){
                                    lightyear.loading('hide');
                                    if(res.code == 0){
                                        window.location.href = "/common/temdownload?fileName=" + encodeURI(res.data) + "&delete=" + true + "&name=" + "视频.zip";                                }
                                }
                            });
                        }
                    },
                    close: {
                        text: '取消',
                    }
                }
            });
        }

    }

    function downCourt() {
        var arr = [];
        var list = new Array();
        var checkData = tree.getChecked('courtId');
        list = getChecked_list(checkData);
        arr= list.split(',');
        if(list.length == 0){
            lightyear.notify("未选中数据，禁止下载", 'warning', 5000);
        }else {
            $.confirm({
                title: '提醒',
                content: '确认下载吗？',
                type: 'green',
                buttons: {
                    omg: {
                        text: '确认',
                        btnClass: 'btn-green',
                        action: function () {
                            lightyear.loading('show');


                            $.ajax({
                                type: "post",
                                url: '/bizCaseInfo/dowCourt.do?strings='+arr,
                                success: function(res){
                                    lightyear.loading('hide');
                                    if(res.code == 0){
                                        window.location.href = "/common/temdownload?fileName=" + encodeURI(res.data) + "&delete=" + true + "&name=" + "笔录.zip";                                }
                                }
                            });
                        }
                    },
                    close: {
                        text: '取消',
                    }
                }
            });
        }

    }

    // 获取选中节点的id
    function getChecked_list(data) {

        var id = "";
        if(data != undefined){
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
        }

        return id;
    }
</script>
</body>

</html>