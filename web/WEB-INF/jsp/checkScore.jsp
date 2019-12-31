<%--
  Created by IntelliJ IDEA.
  User: 半夏VEZ
  Date: 2019/12/26
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看考评分数</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<hr>
<table class="layui-hide" id="test" lay-filter="testTable"></table>
<script>
    layui.use('table', function () {
        var table = layui.table;
        table.render(
            {
                elem: '#test'
                , url: '/Controller/checkScore'
                ,type:"post"
                , cols: [[
                    , {field: 'templateName', width: 200, title: '考评名称'}
                    , {field: 'empName', width: 150, title: '考评人'}
                    , {field: 'studentComment', width: 150, title: '学生评分'}
                    , {field: 'leadComment', width: 150, title: '领导评分'}
                    , {field: 'total', width: 150, title: '总得分'}
                    , {field: 'grade', width: 150, title: '等级'}
                    , {field: 'remark', width: 150, title: '评语'}
                ]]
                , page: true
            }
        );
    });
</script>
</body>
</html>
