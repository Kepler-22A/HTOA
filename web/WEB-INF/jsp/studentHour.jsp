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
    <title>宿舍管理</title>
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
    <legend>宿舍管理</legend>
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

            <th>编号</th>
            <th>排编序号</th>
            <th>宿舍房号</th>
            <th>宿舍楼栋</th>
            <th>床位数</th>
            <th>宿舍地址</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr align="center">
            <td>1</td>
            <td>0</td>
            <td>406</td>
            <td>5</td>
            <td>8</td>
            <td>5栋</td>
            <td> <button type="button" class="layui-btn layui-btn-sm layui-btn-normal"><i class="layui-icon"></i> 删除</button>
                &nbsp;&nbsp;<button type="button" class="layui-btn layui-btn-sm layui-btn-normal"><i class="layui-icon"></i>编辑</button>

            </td>

        </tr>
        </tbody>
    </table>
</div>

</body>
</html>
