<%--
  Created by IntelliJ IDEA.
  User: 半夏VEZ
  Date: 2019/12/23
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>考评打分</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<form id="markFrom" method="post" action="/Controller/addMark"  >
    <table class="layui-table">
        <thead>
        <tr>
            <th>考评编号</th>
            <th>考评人</th>
            <th>考评项目</th>
            <th>考评细则</th>
            <th>满分</th>
            <th>打分</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${sessionScope.project}" var="p">
        <tr>
            <td><input type="hidden"  name="projectId" value="${p.projectId}">${p.projectId}</td>
            <td>${sessionScope.teacher}</td>
            <td>${p.projectName}</td>
            <td>${p.judgment}</td>
            <td>${p.maxScoer}</td>
            <td><input class="layui-input" type="text" name="checkScore"></td>
        </tr>
        </c:forEach>
    </table>
    <button type="submit" class="layui-btn layui-btn-normal" >提交</button>
    <button type="button" class="layui-btn layui-btn-normal" >返回</button>
</form>
<script>
    function addMark() {
        $.ajax({
            url:'/Controller/addMark'
            ,dataType:'json'
            ,type:'POST'
             ,data:$('#markFrom').serialize(),
            success: function (result) {
                if (result.success) {
                    layer.msg("成功添加");
                };
            },
            error : function() {
                alert("异常！");
            }
        });
    }
</script>
</body>
</html>
