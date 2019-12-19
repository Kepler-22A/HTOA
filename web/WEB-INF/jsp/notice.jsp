<%--
  Created by IntelliJ IDEA.
  User: 28401
  Date: 2019/12/17
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>公告发布</title>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
</head>
<body>
    <table class="layui-hide" id="test"></table>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a href="${pageContext.request.contextPath}/message/addNotice"><button class="layui-btn layui-btn-sm">发布公告</button></a>
            <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reload()">刷新公告</button>
        </div>
    </script>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs">查看</a>
        <a class="layui-btn layui-btn-warm layui-btn-xs">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs">删除</a>
    </script>
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test'
            ,url:'${pageContext.request.contextPath}/message/selectNotice'
            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            ,cols: [[
                {field:'noticeId', title: 'ID', sort: true}
                ,{field:'title', title: '标题'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
                ,{field:'noticeType', title: '类型'}
                ,{field:'content', title: '通知内容', sort: true}
                ,{field:'empName', title: '发布人', align: 'center'} //单元格内容水平居中
                ,{field:'noticeTime', title: '发布时间',  align: 'right'} //单元格内容水平居中
                ,{field:'aaa', title: '已读人数',width:100}
                ,{field:'ccc', title: '未读人数',width:100}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
            ]]
        });
    });
</script>
</body>
</html>
