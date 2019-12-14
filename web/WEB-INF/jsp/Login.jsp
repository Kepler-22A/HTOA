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
</head>
<body>
    <h2 align="center" style="margin-bottom: 250px"> 登录办公后台系统</h2>


    <div align="center">
        <form class="layui-form" method="post" action="${pageContext.request.contextPath}/Controller/login" style="width:310px;margin: 0 auto">
            <div class="layui-form-item">
                <label class="layui-form-label">账号：</label>
                <div class="layui-input-inline">
                    <input type="text" name="empName" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
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
                    <button class="layui-btn" ><a href="../../manageLogo.jsp">注册</a></button>
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
</script>
</html>
