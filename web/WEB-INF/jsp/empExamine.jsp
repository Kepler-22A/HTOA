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
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
</head>
<body>

<table class="layui-hide" id="test"></table>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" onclick="del('{{ d.auditLogID}}')">删除</a>
</script>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    rederr()
    function rederr() {
        layui.use('table', function () {
            var table = layui.table;

            table.render({
                elem: '#test'
                , url: '/Controller/table2'
                , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
                , defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                    title: '提示'
                    , layEvent: 'LAYTABLE_TIPS'
                    , icon: 'layui-icon-tips'
                }]
                , title: '用户数据表'
                , cols: [[
                    {field: 'auditLogID', width: 100, title: '编号'}
                    , {field: 'auditName', width: 350, title: '考核内容'}
                    , {field: 'empName', width: 100, title: '员工'}
                    , {field: 'scores', width: 100, title: '考核分数', sort: true}
                    , {field: 'auditDate', width: 150, title: '考核时间',templet : '<span>{{layui.util.toDateString(d.templateTime,"yyyy-MM-dd HH:mm:ss")}}</span>'}
                    , {field: 'auditPerson', width: 100, title: '录入人员'}
                    , {field: 'remark', title: '说明', minWidth: 300}
                    , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 200}
                ]]
                , page: true
            });
        });
    }
    function del(auditLogID) {
        console.log(auditLogID);
        layer.confirm('是否要删除？', {
            icon:3,
            btn: ['确认','取消'] //按钮
        }, function(){
            $.post('<%=request.getContextPath()%>/Controller/delete2/'+auditLogID,{},
                function (data) {
                    rederr();
                });
            layer.msg('已删除', {
                icon: 1,
                time:2000
            });
        }, function(){
            layer.msg('已取消', {
                icon:0,
                time: 2000 //20s后自动关闭
            });
        });
    }
</script>
</body>
</html>
