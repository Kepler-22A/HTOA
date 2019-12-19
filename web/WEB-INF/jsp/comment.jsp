<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>查看批注</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
</head>
<body>
<a href="javascript:history.back()">返回</a>
<hr color="red"/>
<table width="90%" cellspacing="1" bgcolor="#6495ED" class="layui-table">
    <tr bgcolor="#2F4F4F">
        <th colspan="2"><font color="white">审批信息列表</font></th>
    </tr>
    <tr bgcolor="#2F4F4F">
        <th><font color="white">ID</font></th>
        <th><font color="white">审批时间</font></th>
        <th><font color="white">审批人</font></th>
        <th><font color="white">批注内容</font></th>
    </tr>
    <c:forEach items="${commentList}" var="t">
        <tr bgcolor="white">
            <td>${t.id }</td>
            <td>${t.time}</td>
            <td>${t.userId }</td>
            <td>${t.fullMessage }</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
