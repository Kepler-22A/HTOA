<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/18
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的任务</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
</head>
<body>
    <div id="myTaskTable"></div>
    <%--    员工表表头工具    --%>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-sm" href="${pageContext.request.contextPath}/leave/toEmpApplyPage">返回</a>
            <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reload()">刷新表格</button>
        </div>
    </script>

    <%--    每行员工信息的操作按钮    --%>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" href="${pageContext.request.contextPath}/leave/taskDetail/{{d.id}}/{{d.processInstanceId}}" lay-event="edit">查看详情</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" href="${pageContext.request.contextPath }/leave/lookTaskImg/{{ d.processInstanceId }}/processInstanceId">办理进度</a>
    </script>

    <script>
        function reload(){
            layui.use('table', function(){
                var table = layui.table;

                //第一个实例
                table.render({
                    elem: '#myTaskTable'
                    ,height: 523
                    ,url: '/leave/selMyTask' //数据接口
                    ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
                    ,page: true //开启分页
                    ,cols: [[ //表头
                        {field: 'id', title: '任务ID', width:150, sort: true, fixed: 'left'}
                        ,{field: 'processInstanceId', title: '实例ID', width:150}
                        ,{field: 'processDefinitionId', title: '流程ID', width:220}
                        ,{field: 'name', title: '任务名称', width:200}
                        ,{field: 'assignee', title: '任务执行人', width: 200}
                        ,{field: 'createTime', title: '任务创建时间', width: 180,templet:'#statusShow',templet : '<span>{{layui.util.toDateString(d.createTime,"yyyy-MM-dd HH:mm:ss")}}</span>'}
                        ,{field: '', title: '操作', width: 180 ,toolbar:'#barDemo'}
                    ]]
                });

            });
        };

        reload();
    </script>
</body>
</html>
