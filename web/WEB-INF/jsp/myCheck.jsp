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
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title">
    <legend>已结束的考评</legend>
</fieldset>
<button class="layui-btn" onclick="rederr()">刷新</button>
<table class="layui-hide" id="test" lay-filter="testTable"></table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" onclick="addTab('{{d.templateId}}')" >查看</a>
</script>
<script>
    rederr();
    function rederr() {
        layui.use('table', function () {
            var table = layui.table;
            table.render(
                {
                    elem: '#test'
                    , url: '/Controller/selectMyCheck'
                    , cols: [[
                        , {field: 'templateId', width: 150, title: '考核编号'}
                        , {field: 'templateName', width: 150, title: '考核名称'}
                        , {field: 'templateType', width: 150, title: '考核类型'}
                        , {field: 'templateTime', width: 200, title: '创建日期', templet: '<span>{{layui.util.toDateString(d.templateTime,"yyyy-MM-dd HH:mm:ss")}}</span>'}
                        , {field: 'remark', title: '备注', Width: 350}
                        , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 200}
                    ]]
                    , page: true
                }
            );
        });
    }
    //新增tab页
    function addTab(templateId) {
        parent.active.tabAdd("/Controller/toCheckScore/"+templateId, 49, "查看考评");
        parent.active.tabChange(49)
    }
    // function
</script>
</body>
</html>
