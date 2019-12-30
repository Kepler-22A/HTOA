<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/30
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>验证旧密码</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
</head>
<body>
    <form id="oldPwdForm" class="layui-form" action="${pageContext.request.contextPath}/emp/checkOldPwd">
        <input id="userId" type="hidden" name="userId" value="${empId}">
        <label class="layui-form-label">旧密码</label>
        <div class="layui-input-inline">
            <input id="oldPasswordInput" type="password" name="password" lay-verify="oldPasswordInput" autocomplete="off" placeholder="请输入您的旧密码" class="layui-input">
        </div>
        <div class="layui-form-item" style="margin: 0 auto">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a class="layui-btn" lay-submit lay-filter="oldPwdFormDemo">验证密码</a>
        </div>
    </form>
    <form id="changePwdForm" class="layui-form" action="${pageContext.request.contextPath}/emp/changePwd" style="display: none">
        <input id="userIdC" type="hidden" name="userId" value="${empId}">
        <div class="layui-form-item">
            <label class="layui-form-label">请输入密码</label>
            <div class="layui-input-inline">
                <input id="pwdC" type="text" name="password" lay-verify="pwdC" autocomplete="off" placeholder="请输入您的新密码" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" style="margin: 0 auto">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a class="layui-btn" lay-submit lay-filter="changePwdFormDemo">提交</a>
        </div>
    </form>
</body>
<script>
    if (${empId == null}){
        $("#userId").val('${studentId}');
    }

    layui.use(['layer', 'jquery', 'form'], function() {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form;

        form.on('submit(oldPwdFormDemo)', function () {
            form.verify({
                oldPasswordInput: function (value) {
                    if (value.length < 1) {
                        return '请输入旧密码';
                    }
                }
            });

            $.ajax({url:'${pageContext.request.contextPath}/emp/checkOldPwd',dataType:'json',data:$("#oldPwdForm").serialize(),success:function (data) {
                if (data.count == 1){
                    $("#oldPwdForm").css("display",'none');
                    $("#changePwdForm").css("display",'block');
                    layer.msg("验证通过！");
                }else {
                    layer.msg("密码错误！");
                }
                },error:function (data) {
                    layer.msg("验证出错！");
                }
            });

        });

        form.on('submit(changePwdFormDemo)', function () {

            $.ajax({url:'${pageContext.request.contextPath}/emp/changePwd',dataType:'json',data:$("#changePwdForm").serialize(),success:function (data) {
                    if (data.count == 1){
                        layer.msg("修改成功！");
                        top.location.href = "${pageContext.request.contextPath}/Controller/loginOut";
                    }else {
                        layer.msg("修改失败！");
                    }
                },error:function (data) {
                    layer.msg("出错未知错误！");
                }
            });
        });
    });
</script>
</html>
