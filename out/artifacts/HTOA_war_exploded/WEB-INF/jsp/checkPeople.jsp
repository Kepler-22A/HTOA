<%--
  Created by IntelliJ IDEA.
  User: 半夏VEZ
  Date: 2019/12/25
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>考评人员</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title">
    <legend>待考评人员</legend>
</fieldset>
<button class="layui-btn" onclick="rederr()">刷新</button>
<table class="layui-hide" id="test" lay-filter="testTable"></table>
<form  class="layui-form" id="projectFrom" style="display:none" method="post" >

</form>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" onclick="addTab('{{d.templateId}}','{{d.empName}}')" >打分</a>
</script>
<script>
    rederr();
    function rederr() {
        layui.use('table', function () {
            var table = layui.table;
            table.render(
                {
                    elem: '#test'
                    , url: '/Controller/table5'
                    , cols: [[
                        , {field: 'templateId', width: 150, title: '模板编号'}
                        , {field: 'templateName', width: 200, title: '考核名称'}
                        , {field: 'empName', width: 200, title: '考核人'}
                        , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 200}
                    ]]
                    , page: true
                }
            );
        });
    }
    //新增tab页
    function addTab(templateId,empName) {
        var url = encodeURI("/Controller/leadCheckMark/"+templateId+"/"+empName);
        parent.active.tabAdd(url, 48, "打分");
        parent.active.tabChange(48)
    }
</script>
</body>
</html>
