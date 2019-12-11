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
            ,url:'${pageContext.request.contextPath}/student/Ling_ReplyScore'
            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {field:'replyId', title: 'ID', sort: true}
                ,{field:'stuname', title: '学生姓名'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
                ,{field:'className', title: '班级', sort: true}
                ,{field:'projectId', title: '项目名称'}
                ,{field:'score1', title: '功能完善50', align: 'center'} //单元格内容水平居中
                ,{field:'score2', title: '技术难度10',  align: 'right'} //单元格内容水平居中
                ,{field:'score3', title: '界面完美10', align: 'right'}
                ,{field:'score4', title: '回答方式10', sort: true, align: 'right'}
                ,{field:'score5', title: '演示方式10', sort: true, align: 'right'}
                ,{field:'score6', title: '语言表达10', sort: true, align: 'right'}
                ,{field:'score7', title: '总分100',sort: true, align: 'right'}
                ,{field:'Remark', title: '备注'}
            ]]
        });
    });
</script>

</body>
</html>
