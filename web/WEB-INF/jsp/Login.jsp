<%--
  Created by IntelliJ IDEA.
  User: 28401
  Date: 2019/12/4
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学校管理登陆页面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <style>
        body{
            background-image: url("/image/sii.jpg");
            background-size: 93%;
            background-position:bottom;
            background-repeat: no-repeat;
            background-color: #0C0C0C;
        }

        #fdiv{
            background-color: rgba(42,113,125,0.67);
        }

        #fdiv:hover{
            background-color:  rgba(42,113,125,0.88);
        }
    </style>
</head>
<body>
    <h2 id="pageTitle" align="center" style="margin-bottom: 220px;color: #b13bff;margin-top: 50px"> 员工登录办公后台系统</h2>


    <div id="fdiv" align="center" style="width: 310px;height: 144px;margin: 0 auto;border-radius: 6px;padding: 5px;
            transition:background-color 0.2s linear;">
        <form id="ff" class="layui-form" method="post" action="${pageContext.request.contextPath}/Controller/checkUser/" style="width:310px;margin: 0 auto">
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 60px">名字：</label>
                <div class="layui-input-inline">
                    <input id="nameInput" type="text" name="empName" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item" >
                <label class="layui-form-label" style="width: 60px">密码：</label>
                <div class="layui-input-inline">
                    <input id="passwordInput" type="password" name="password" lay-verify="required" lay-reqtext="密码不能为空" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block" style="margin-left: 70px">
                    <button class="layui-btn" lay-submit lay-filter="demoBtn">登录</button>
                    <a class="layui-btn layui-btn-danger " id="loginTypeA" onclick="changeStudentLogin()" style="color: #ff0;">学生登录</a>
                </div>
            </div>
        </form>
    </div>
</body>
<script>
    var array = ${array};

    console.log(array);

    if(array == 0){
        layui.use('form', function() {
            layer.msg("账户名或密码不正确！");
        });
        setTimeout(function () {
            $.ajax({
                url:"${pageContext.request.contextPath}/Controller/changeArrayOnSession"
                ,type: 'post'
                ,success : function () {

                }
            });
        },500)
    }

    loginType = 'emp';

    layui.use('form', function(){
        var form = layui.form;
        //监听提交
        form.on('submit(demoBtn)', function(data){
            $.ajax({
                url:"${pageContext.request.contextPath}/Controller/checkUser/" + loginType
                ,type:'POST'
                ,data:$('#ff').serialize()
                ,async : false
                ,success: function (result) {
                    if ("\"1\"" == result){
                        return true;
                    }else {

                    }
                },
                error : function() {
                    alert("异常！");
                }
            });
        });
    });

    $("#ff").attr("action","${pageContext.request.contextPath}/Controller/login");

    function changeStudentLogin() {
        $("#ff").attr("action","${pageContext.request.contextPath}/Controller/studentLogin");
        loginType = 'stu';
        $("#pageTitle").html("学生登录OA系统");
        $("#nameInput").attr("name","stuname");
        $("#loginTypeA").html("员工登录");
        $("#loginTypeA").attr("onclick","changeEmpLogin()");
    }

    function changeEmpLogin() {
        $("#ff").attr("action","${pageContext.request.contextPath}/Controller/login");
        loginType = 'emp';
        $("#pageTitle").html("员工登录办公后台系统");
        $("#nameInput").attr("name","empName");
        $("#loginTypeA").html("学生登录");
        $("#loginTypeA").attr("onclick","changeStudentLogin()");
    }
</script>
</html>
