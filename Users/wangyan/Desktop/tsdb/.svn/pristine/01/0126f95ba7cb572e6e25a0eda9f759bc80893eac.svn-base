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
<style>
    #detailBox {
        position: relative;
        display: none;
        padding: 20px 20px 100px;
    }

    .detail-table {
        font-size: 12px;
    }

    .td-label {
        text-align: right;
        width: 120px;
        color: #4d5259;
        font-weight: bold;
        background-color: #fafafa;
    }

    .fixedfoot {
        position: absolute;
    }

</style>
<body>
<div class="container-fluid p-t-15">

    <div class="row">
        <div class="col-lg-4">
            <div class="card">
                <div class="card-body" style="padding-top: 16px;">
                    <form class="form-inline" action="" method="" onsubmit="return false;">

                        <div class="form-group m-r-5">
                            <label for="example-if-password">时间范围：</label>
                            <input class="form-control input-sm" style="width: 170px" type="text" name="trial_time">

                        </div>
                        <div class="form-group">
                            <button class="btn btn-info btn-sm" onclick="queryTrialTime()" type="submit"><i
                                        class="mdi mdi-account-search"></i>查询
                            </button>
                        </div>
                    </form>
                    <div class="row panel-data-row">
                        <div class="col-lg-6 m-b-15">
                            <div class="panel-box">
                                <div class="panel-icon">
                                    <span class="icon-1"></span>
                                </div>
                                <div class="panel-nums">
                                    <b id="total"></b>
                                    <p>总点播数</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 m-b-15">
                            <div class="panel-box">
                                <div class="panel-icon">
                                    <span class="icon-2"></span>
                                </div>
                                <div class="panel-nums">
                                    <b id="case_num"></b>
                                    <p>庭审数</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 m-b-15">
                            <div class="panel-box">
                                <div class="panel-icon">
                                    <span class="icon-3"></span>
                                </div>
                                <div class="panel-nums">
                                    <b id="video_num"></b>
                                    <p>录像数</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 m-b-15">
                            <div class="panel-box">
                                <div class="panel-icon">
                                    <span class="icon-4"></span>
                                </div>
                                <div class="panel-nums">
                                    <b id="record_num"></b>
                                    <p>笔录数</p>
                                </div>
                            </div>
                        </div>
<#--                        <div class="col-lg-6">-->
<#--                            <div class="panel-box">-->
<#--                                <div class="panel-icon">-->
<#--                                    <span class="icon-5"></span>-->
<#--                                </div>-->
<#--                                <div class="panel-nums">-->
<#--                                    <b></b>-->
<#--                                    <p>录像时长(h)</p>-->
<#--                                </div>-->
<#--                            </div>-->
<#--                        </div>-->
                        <div class="col-lg-6">
                            <div class="panel-box">
                                <div class="panel-icon">
                                    <span class="icon-6"></span>
                                </div>
                                <div class="panel-nums">
                                    <b id="user_num"></b>
                                    <p>用户数</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-4">
            <div class="card">
                <div class="card-header">
                    <h4>案件类型占比</h4>
                </div>
                <div class="card-body">
                    <div id="mainType" style="width: 100%;height:300px;"></div>
                </div>
            </div>
        </div>
        <div class="col-lg-4">
            <div class="card">
                <div class="card-header">
                    <h4>承办部门占比</h4>
                </div>
                <div class="card-body">
                    <div id="mainClass" style="width: 100%;height:300px;"></div>
                </div>
            </div>
        </div>
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header">
                    <h4>法官工作量统计列表</h4>
                    <ul class="card-actions1">
                    </ul>
                </div>
                <div class="card-body" style="padding-top: 4px;padding-bottom: 10px;">
                    <form class="form-inline" action="index.html" method="post" onsubmit="return false;">
                        <div class="form-group m-r-5">
                            <label for="example-if-password">法官姓名：</label>
                            <input class="form-control input-sm" style="width: 170px" type="text" id="user_name">
                        </div>
                        <div class="form-group m-r-5">
                            <label for="example-if-password">时间范围：</label>
                            <input class="form-control input-sm" style="width: 170px" type="text" id="trial_time">
                        </div>
                        <div class="form-group">
                            <button class="btn btn-info btn-sm" onclick="queryData()" type="submit"><i
                                        class="mdi mdi-account-search"></i>查询
                            </button>
                        </div>
                    </form>
                    <table class="layui-hide" id="test-table-height" lay-filter="table-toolbar"></table>
                    <script type="text/html" id="table-toolbar">
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
<script src="/libs/js/echarts.min.js"></script>
<script type="text/javascript">
    var admin, form, laydate, table;
    layui.config({
        base: '/libs/js/layuiadmin/' //静态资源所在路径
    }).use(['form', 'index', 'table', 'laydate'], function () {
        admin = layui.admin
            , laydate = layui.laydate
            , table = layui.table
            , form = layui.form;

        table.render({
            elem: '#test-table-height'
            , url: "/bizCaseInfo/getWorkload.do"
            , height: 'full-630'
            , cellMinWidth: 80
            , method: 'post'
            , contentType: 'application/json'
            , dataType: 'json'
            , page: true
            , limit: 10
            , cols: [[
                {field: 'user_name', title: '法官姓名', align: 'center'}
                , {field: 'dept_name', title: '所属部门', align: 'center'}
                , {field: 'role_name', title: '所属角色', align: 'center'}
                , {field: 'case_num', title: '点播案件数', align: 'center'}
                , {field: 'total', title: '总点播次数', align: 'center'}
            ]]
            , where: {
                'case_code': $("#case_code").val(),
                'case_name': $("#case_name").val(),
                'case_type': $("#case_type").val(),
                'undertake_dept': $("#undertake_dept").val(),
                'trial_time': $("#time1").val(),
                'case_time': $("#time2").val()
            }
            , parseData: function (item) { //将原始数据解析成 table 组件所规定的数据
                return {
                    "code": 0,   //解析接口状态
                    "message": "成功",  //解析提示文本
                    "count": item.data.total,       //解析数据长度
                    "data": item.data.list //解析数据列表
                };
            }
        });

        //日期时间选择器
        laydate.render({
            elem: 'input[name="trial_time"]'
            , type: 'date'
            , range: true
        });
        laydate.render({
            elem: '#trial_time'
            , type: 'date'
            , range: true
        });

    });

    function queryData() {
        table.reload('test-table-height', {<!-- -->
            where: {
                'user_name': $("#user_name").val(),
                'date': $("#trial_time").val(),
            },//表格重载时设置新的搜索参数
        });
    }

    queryTrialTime()

    function queryTrialTime() {
        getTotal()
        getTypeRatio()
        getDeptRatio()
    }


    function getTotal() {
        $.ajax({
            type: "post",
            dataType: 'json',
            contentType: 'application/json;charset=UTF-8',
            url: '/bizCaseInfo/getTotal.do',
            data: JSON.stringify({
                date: $('input[name="trial_time"]').val()
            }),
            success: function (res) {
                console.log($('input[name="trial_time"]').val())
                $('#user_num').text(res.data.user_num)
                $('#video_num').text(res.data.video_num)
                $('#record_num').text(res.data.record_num)
                $('#case_num').text(res.data.case_num)
                $('#total').text(res.data.total)
            }
        });
    }

    // 案件类型占比
    function getTypeRatio() {
        $.ajax({
            type: "post",
            dataType: 'json',
            contentType: 'application/json;charset=UTF-8',
            url: '/bizCaseInfo/getTypeRatio.do',
            data: JSON.stringify({
                date: $('input[name="trial_time"]').val()
            }),
            success: function (res) {
                const myChart = echarts.init(document.getElementById('mainType'));
                const option = {
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)",
                    },
                    legend: {
                        top: '5%',
                        orient: 'vertical',
                        left: 'left'
                    },
                    series: [
                        {
                            name: '案件类型占比',
                            type: 'pie',
                            radius: ['30%', '60%'],
                            avoidLabelOverlap: false,
                            itemStyle: {
                                borderRadius: 4,
                                borderColor: '#fff',
                                borderWidth: 1
                            },
                            label: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                label: {
                                    show: true,
                                    fontSize: '20',
                                    fontWeight: 'bold'
                                }
                            },
                            labelLine: {
                                show: false
                            },
                            data: res.data
                            // data: [
                            //     { value: 108, name: '刑事案件' },
                            //     { value: 1735, name: '民事案件' },
                            //     { value: 580, name: '行政案件' },
                            //     { value: 484, name: '经济案件' },
                            // ]
                        }
                    ]
                };
                myChart.setOption(option);
            }
        });
    }

    // 部门占比
    function getDeptRatio() {
        $.ajax({
            type: "post",
            dataType: 'json',
            contentType: 'application/json;charset=UTF-8',
            url: '/bizCaseInfo/getDeptRatio.do',
            data: JSON.stringify({
                date: $('input[name="trial_time"]').val()
            }),
            success: function (res) {
                const myChartClass = echarts.init(document.getElementById('mainClass'));
                const option = {
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)",
                    },
                    legend: {
                        orient: "vertical",
                        type: 'scroll',
                        x: "left",
                        // y: "-25",
                        // bottom: 20,
                        itemGap: 2,
                    },
                    series: [
                        {
                            name: '承办部门占比',
                            type: 'pie',
                            radius: ['30%', '60%'],
                            center: ["65%", "50%"],
                            avoidLabelOverlap: false,
                            itemStyle: {
                                borderRadius: 4,
                                borderColor: '#fff',
                                borderWidth: 1
                            },
                            label: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                label: {
                                    show: true,
                                    fontSize: '20',
                                    fontWeight: 'bold'
                                }
                            },
                            labelLine: {
                                show: false
                            },
                            data: res.data
                            // data: [
                            //     { value: 108, name: '部门1' },
                            //     { value: 1735, name: '部门2' },
                            //     { value: 580, name: '部门3' },
                            //     { value: 484, name: '部门4' },
                            //     { value: 484, name: '部门5' },
                            //     { value: 484, name: '部门6' },
                            //     { value: 484, name: '部门7' },
                            //     { value: 484, name: '部门8' },
                            //     { value: 484, name: '部门9' },
                            //     { value: 484, name: '部门10' },
                            //     { value: 484, name: '部门11' },
                            //     { value: 484, name: '部门12' },
                            //     { value: 484, name: '部门13' },
                            //     { value: 484, name: '部门14' },
                            //     { value: 484, name: '部门15' },
                            //     { value: 484, name: '部门16' },
                            //     { value: 484, name: '部门17' },
                            //     { value: 484, name: '部门18' },
                            //     { value: 484, name: '部门19' },
                            //     { value: 484, name: '部门20' },
                            //     { value: 484, name: '部门21' },
                            //     { value: 484, name: '部门22' },
                            //     { value: 484, name: '部门23' },
                            //     { value: 484, name: '部门24' },
                            // ]
                        }
                    ]
                };
                myChartClass.setOption(option);
            }
        });
    }
</script>
</body>

</html>