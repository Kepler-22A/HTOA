<%--
  Created by IntelliJ IDEA.
  User: 28401
  Date: 2019/12/9
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>答辩成绩页面</title>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
</head>
<body>
<table class="layui-hide" id="test"></table>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test'
            ,url:'${pageContext.request.contextPath}/student/Ling_Score'
            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {field:'scoreId', title: 'ID', sort: true}
                ,{field:'stuname', title: '学生姓名'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
                ,{field:'score', title: '分数', sort: true}
                ,{field:'Rescore', title: '补考分数'}
                ,{field:'courseName', title: '课程名称'}
                ,{field:'courseTypeName', title: '考试类别', align: 'center'} //单元格内容水平居中
                ,{field:'termName', title: '在读学期',  align: 'right'} //单元格内容水平居中
                ,{field:'scoreTime', title: '考试时间', align: 'right'}
                ,{field:'empName', title: '录入人员', sort: true, align: 'right'}
            ]]
        });
    });
</script>

</body>
</html>
