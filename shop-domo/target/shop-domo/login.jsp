<%--
  Created by IntelliJ IDEA.
  User: 张鹿阁
  Date: 2018/8/23
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>员工管理登录页面</title>

    <link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/js/bootstrap/validator/dist/css/bootstrapValidator.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/js/bootstrap/css/bootstrap-theme.min.css">

    <style>
        #from
        {
            background-color: #96b97d;
        }
        .mycenter
        {
            margin-top: 100px;
            margin-left: auto;
            margin-right: auto;
            height: 350px;
            width: 500px;
            padding: 5%;
            padding-left: 5%;
            padding-right: 5%;
        }
        .mycenter mysign
        {
            width: 440px;
        }
        .mycenter input, checkbox, button
        {
            margin-top: 2%;
            margin-left: 10%;
            margin-right: 10%;
        }
        .mycheckbox
        {
            margin-top: 10px;
            margin-left: 40px;
            margin-bottom: 10px;
            height: 10px;
        }
    </style>
</head>
<body>

<form id="submitValid">
    <div class="mycenter">
        <div class="mysign">
            <div class="col-lg-11 text-center text-info">
                <h2>
                    请登录</h2>
            </div>
            <div class="col-lg-10">
                <input type="text" class="form-control" id="username" name="username" placeholder="请输入账户名" required
                />
            </div>
            <div class="col-lg-10">
            </div>
            <div class="col-lg-10">
                <input type="password" class="form-control" id="userpass" name="userpass" placeholder="请输入密码" required
                />
            </div>
            <div class="col-lg-10">
            </div>

            <div class="col-lg-10">
                <input type="text" class="form-control" id="verifyCode" name="verifyCode" placeholder="请输入验证码" required />
            </div>
            <div class="col-lg-10">
            </div>
            <div class="col-lg-10">
                <img src="<%=request.getContextPath()%>/verifyCode" id="img" style="margin-left: 26px;"> <a href="javascript:;" onclick="flushCode()">刷新</a>
            </div>
            <div class="col-lg-10">
            </div>
            <div class="col-lg-10 mycheckbox checkbox">
                <input type="checkbox" class="col-lg-1">记住密码</input>
            </div>
            <div class="col-lg-10">
            </div>
            <div class="col-lg-10">
                <button type="button" onclick="logins()" id="btn" class="btn btn-success col-lg-12">
                    登录</button>
            </div>
        </div>
    </div>
</form>

<script type="text/javascript"  src="<%=request.getContextPath()%>/js/Jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/bootbox/bootbox.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datatable/DataTables-1.10.18/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datatable/DataTables-1.10.18/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/validator/dist/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/md5/md5.js"></script>
<script>


    $(function () {
        initVerify();
    });


    function flushCode() {
        var ranTime = new Date().getTime();

        //刷新验证码图片路径
        $("#img").attr("src","<%=request.getContextPath()%>/verifyCode?"+ranTime);



    }

    function initVerify() {

        $('form').bootstrapValidator({
            message: '验证未通过！',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                username: {
                    message: '用户名验证失败',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        },
                        stringLength: {
                            min: 3,
                            max: 10,
                            message: '用户名长度必须在3到10位之间'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_]+$/,
                            message: '用户名只能包含大写、小写、数字和下划线'
                        }
                    }
                },
                userpass: {
                    message: '密码验证失败！',
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        stringLength: {
                            min: 3,
                            max: 10,
                            message: '密码长度必须在3到10位之间'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_]+$/,
                            message: '用户名只能包含大写、小写、数字和下划线'
                        }
                    }
                },
                verifyCode: {
                    message: '密码验证失败！',
                    validators: {
                        notEmpty: {
                            message: '验证码不能为空'
                        },
                        stringLength: {
                            min: 4,
                            max: 4,
                            message: '验证码长度必须为四位'
                        }
                        /*regexp: {
                            regexp: /^[a-zA-Z0-9_]+$/,
                            message: '用户名只能包含大写、小写、数字和下划线'
                        }*/
                    }
                },

            }
        });


    }
    function logins() {

        //提交表单时进行验证
        $("form").bootstrapValidator('validate');
        //判断所有输入验证是否通过
        if ($("#submitValid").data('bootstrapValidator').isValid()) {

            var v_username = $("#username").val();
            var v_userpass = $("#userpass").val();
            var v_verifyCode = $("#verifyCode").val();
            var param = {};
            param.username = v_username;
            //MD5加密
            console.log(hex_md5(v_userpass));
            param.userpass = hex_md5(v_userpass);
            param.verifyCode = v_verifyCode;

            $.ajax({
                type: "post",
                url: "<%=request.getContextPath()%>/login",
                data: param,
                success: function (result) {

                    if (result.code == 200) {


                        window.location.href = "http://localhost:8080/index";
                    } else {
                        bootbox.alert({
                            message: "<b style='color:red;'>" +
                            "<span class='glyphicon glyphicon-exclamation-sign'></span>" +
                            "</b><span style='color: #df8505;margin-left:5px;'>" + result.massage + "</span>",
                            size: 'small',
                            title: "提示信息"
                        });
                    }

                }


            })

        }
    }


</script>
</body>
</html>
