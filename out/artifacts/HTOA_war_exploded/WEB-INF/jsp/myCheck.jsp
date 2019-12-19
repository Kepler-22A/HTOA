<%--
  Created by IntelliJ IDEA.
  User: 半夏VEZ
  Date: 2019/12/10
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的考评</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
</head>
<body>
    <table class="layui-hide" id="test" lay-filter="testTable"></table>
    <script>
    layui.use('table', function() {
        var table = layui.table;
        table.render({
            elem: '#test'
            , url: '/Controller/table'
            , cols: [[
                {field: 'auditModelID', width: 100, title: '编号'}
                , {field: 'auditName', width: 350, title: '考评内容'}
                , {field: 'scores', width: 100, title: '考核人', sort: true}
                , {field: 'auditTypeName', width: 150, title: '考评时间'}
                , {field: 'remark', title: '得分', minWidth: 100}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 200}
            ]]
            , page: true
        });
    });
    </script>
</body>
</html>
