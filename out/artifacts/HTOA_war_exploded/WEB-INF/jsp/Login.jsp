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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
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
    <h2 id="pageTitle" align="center" style="margin-bottom: 220px;color: #52bfff;margin-top: 50px"> 员工登录办公后台系统</h2>


    <div id="fdiv" align="center" style="width: 310px;height: 144px;margin: 0 auto;border-radius: 6px;padding: 5px;
            transition:background-color 0.2s linear;">
        <form id="ff" class="layui-form" method="post" action="${pageContext.request.contextPath}/Controller/login" style="width:310px;margin: 0 auto">
            <div class="layui-form-item">
                <label class="layui-form-label">名字：</label>
                <div class="layui-input-inline">
                    <input id="nameInput" type="text" name="empName" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item" >
                <label class="layui-form-label">密码：</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" lay-verify="required" lay-reqtext="密码不能为空" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" type="submit">登录</button>
                    <button class="layui-btn" ><a id="loginTypeA" href="javascript:changeStudentLogin()">学生登录</a></button>
                </div>
            </div>
        </form>
    </div>
</body>
<script>
    layui.use('form', function(){
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function(data){
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });

    function changeStudentLogin() {
        $("#ff").attr("action","${pageContext.request.contextPath}/Controller/studentLogin");
        $("#pageTitle").html("学生登录OA系统");
        $("#nameInput").attr("name","stuname");
        $("#loginTypeA").html("员工登录");
        $("#loginTypeA").attr("href","javascript:changeEmpLogin()");
    }

    function changeEmpLogin() {
        $("#ff").attr("action","${pageContext.request.contextPath}/Controller/login");
        $("#pageTitle").html("员工登录办公后台系统");
        $("#nameInput").attr("name","empName");
        $("#loginTypeA").html("学生登录");
        $("#loginTypeA").attr("href","javascript:changeStudentLogin()");
    }
</script>
</html>
