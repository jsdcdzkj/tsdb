<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>登录页面</title>
    <link rel="icon" href="favicon.ico" type="image/ico">
    <meta name="keywords" content="后台管理系统">
    <meta name="description" content="登录页面">
    <link href="/libs/css/bootstrap.min.css" rel="stylesheet">
    <link href="/libs/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="/libs/css/style.min.css" rel="stylesheet">
    <!-- layui Css-->
    <link rel="stylesheet" href="/libs/js/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/libs/js/layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" href="/libs/js/layuiadmin/style/css.css" media="all">
    <style>
        body {
            background-color: #fff;
        }

        .lyear-login-box {
            position: relative;
            overflow-x: hidden;
            width: 100%;
            height: 100%;
            -webkit-transition: 0.5s;
            -o-transition: 0.5s;
            transition: 0.5s;
        }

        .lyear-login-left {
            width: 100%;
            top: 0;
            left: 0;
            bottom: 0;
            position: fixed;
            height: 100%;
            z-index: 555;
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center center;
        }

        .lyear-overlay {
            position: absolute;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            z-index: 10;
            background: rgba(0, 0, 0, 0);
        }

        .lyear-logo {
            margin-bottom: 50px;
        }

        .lyear-featured {
            z-index: 12;
            position: absolute;
            bottom: 0;
            padding: 30px;
            width: 100%;
        }

        .lyear-featured h4 {
            color: #fff;
            line-height: 32px;
        }

        .lyear-featured h4 small {
            color: #fff;
            display: block;
            text-align: right;
            margin-top: 15px;
        }

        .lyear-login-right {
            margin-left: 50%;
            position: relative;
            z-index: 999;
            padding: 0px;
            background-color: #fff;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100%;
        }

        .lyear-login-right .formbox {
            width: 40%;
            margin: auto;

        }

        @media screen and (max-width: 1024px) {
            .lyear-login-right {
                padding: 50px;
            }
        }

        @media screen and (max-width: 820px) {
            .lyear-login-left {
                width: 100%;
                position: relative;
                z-index: 999;
                height: 300px;
            }

            .lyear-login-right {
                margin-left: 0;
            }
        }

        @media screen and (max-width: 480px) {
            .lyear-login-right {
                padding: 50px;
            }
        }

        @media screen and (max-width: 320px) {
            .lyear-login-right {
                padding: 30px;
            }
        }
    </style>
</head>

<body>
<div class="lyear-login-box">
    <div class="lyear-login-left" style="background-image: url(/libs/images/login-bg-5.jpg)">
        <div class="lyear-overlay"></div>

    </div>
    <div class="lyear-login-right" id="loginForm">
        <div class="formbox">
            <div class="lyear-logo text-center">
                <a href="#!"><img src="/libs/images/logo-sidebar.png" alt="logo"/></a>
            </div>
            <form action="#!" method="post">
                <div class="form-group">
                    <label for="username">用户名</label>
                    <input type="text" class="form-control" id="username" v-model="userName" placeholder="请输入您的用户名">
                </div>

                <div class="form-group">
                    <label for="password">密码</label>
                    <input type="password" class="form-control" id="password" v-model="passWord" placeholder="请输入您的密码" @keyup.enter="loginIn">
                </div>

                <!--        <div class="form-group">-->
                <!--          <label for="captcha">验证码</label>-->
                <!--          <div class="row">-->
                <!--            <div class="col-xs-7">-->
                <!--              <input type="text" name="captcha" id="captcha" class="form-control" placeholder="验证码">-->
                <!--            </div>-->
                <!--            <div class="col-xs-5">-->
                <!--              <img src="/libs/images/captcha.png" class="pull-right" id="captcha" style="cursor: pointer;" onclick="this.src=this.src+'?d='+Math.random();" title="点击刷新" alt="captcha">-->
                <!--            </div>-->
                <!--          </div>-->
                <!--        </div>-->
                <!--        -->
                <!--        <div class="form-group">-->
                <!--          <label class="lyear-checkbox checkbox-primary m-t-10">-->
                <!--            <input type="checkbox" checked=""><span>5天内自动登录</span>-->
                <!--          </label>-->
                <!--        </div>-->

                <div class="form-group">
                    <button class="btn btn-block btn-primary" type="button" @click="loginIn">立即登录
                    </button>
                </div>

            </form>
        </div>


    </div>
</div>
<script type="text/javascript" src="/libs/js/jquery.min.js"></script>
<script type="text/javascript" src="/libs/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/libs/js/vue.js"></script>
<script src="/libs/js/layuiadmin/layui/layui.js"></script>
<script src="/libs/js/layuiadmin/common.js"></script>
<script type="text/javascript">
    layui.config({
        base: '/libs/js/layuiadmin/' //静态资源所在路径
    }).use(['index', 'layer'], function () {
        var admin = layui.admin
            ,layer= layui.layer;
    });
    var app = new Vue({
        el: '#loginForm',
        data: {
            "userName": "",
            "passWord": "",
            "user": {}
        },
        mounted: function () {
            this.$nextTick(function () {
            })
        },
        methods: {
            loginIn: function () {//中心药品信息
                var that = this;
                if (this.userName.length < 1) {
                    layer.msg("请输入用户名");
                    return;
                } else if (this.passWord.length < 6) {
                    layer.msg("请输入至少6位密码");
                    return;
                }
                //规格参数含%  需要处理
                // var specifications = this.centerDrug.specifications.replace("%","%25") ;
                $.ajax({
                    type: "post",
                    url: "login.do",
                    data: {
                        "consumer": this.userName,
                        "cypher": this.passWord
                    },
                    dataType: "json",
                    success: function (data) {
                        that.user = data.user;
                        if (data.success) {
                            window.location.href = "main.do"
                        } else {
                            layer.msg(data.msg);
                        }
                    }
                });
            },


        }
    })
</script>
</body>
</html>