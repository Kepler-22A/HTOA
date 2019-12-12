<%--
  Created by IntelliJ IDEA.
  User: 半夏VEZ
  Date: 2019/12/5
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>考核指标页</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>

<table class="layui-hide" id="test"></table>


<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        table.render({
            elem: '#test'
            ,url:'/Controller/table3'
            ,cols: [[
                {field:'auditModelID', width:100, title: '编号'}
                ,{field:'degree', width:250, title: '员工类型'}
                ,{field:'empName', width:250, title: '任课老师/班主任', sort: true}
                ,{field:'className', width:250, title: '任课班级'}
                ,{field:'totalScores', title: '总评分', minWidth: 100}
            ]]
            ,page: true
        });
        var table = layui.table;
    });
</script>
</body>
</html>
