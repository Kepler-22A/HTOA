<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/12/4
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script>
    </script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>员工资料</legend>
</fieldset>

<div class="layui-form">
    <table class="layui-table" align="center">
        <colgroup>
            <col width="150">
            <col width="150">
            <col width="200">
            <col>
        </colgroup>
        <thead>
        <tr>

            <th>员工编号</th>
            <th>员工姓名</th>
            <th>部门</th>
            <th>职务</th>
            <th>性别</th>
            <th>手机号码</th>
            <th>家庭住址</th>
            <th>状态</th>
            <th>设置状态</th>
            <th>初始密码</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr align="center">
            <td>1</td>
            <td>某某</td>
            <td>开发</td>
            <td>学生</td>
            <td>男</td>
            <td>123456768</td>
            <td>赣州</td>
            <td>启用</td>
            <td><a href="#">禁用</a></td>
            <td><a href="#">重置密码</a></td>
            <td> <button type="button" class="layui-btn layui-btn-sm layui-btn-normal"><i class="layui-icon"></i> 删除</button>
                &nbsp;&nbsp;<button type="button" class="layui-btn layui-btn-sm layui-btn-normal"><i class="layui-icon"></i>编辑</button></td>

        </tr>
        </tbody>
    </table>
</div>

</body>
</html>
