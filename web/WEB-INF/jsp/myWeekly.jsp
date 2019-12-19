<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/19
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的周报</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
</head>
<body>
    <table id="weeklyTable"></table>

    <%--每行数据的操作部分--%>
    <script id="weeklyRowsCZ" type="text/html">
        <a class="layui-btn layui-btn-xs" lay-event="edit" href="${pageContext.request.contextPath}/emp/toWeeklyDetails/{{ d.worklogid }}">详情</a>
    </script>

    <%--表头工具栏--%>
    <script type="text/html" id="weeklyToolbar">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-xs" lay-event="edit" href="${pageContext.request.contextPath}/emp/toWeeklyDetails/{{ d.worklogid }}">添加周报</a>
            <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reload()">刷新表格</button>
        </div>
    </script>

    <script>
        //表格数据编写
        function reload(){
            layui.use('table', function(){
                var table = layui.table;

                //表格数据接收
                table.render({
                    elem: '#weeklyTable'
                    ,height: 523
                    ,url: '/emp/selWeeklyListByEmpId' //数据接口
                    ,toolbar: '#weeklyToolbar' //开启头部工具栏，并为其绑定左侧模板
                    ,page: true //开启分页
                    ,cols: [[ //表头
                        {field: 'worklogid', title: '周报ID', width:100, sort: true, fixed: 'left'}
                        ,{field: 'empName', title: '员工姓名', width:100}
                        ,{field: 'Workday', title: '周报编写时间', width:160,templet : '<span>{{layui.util.toDateString(d.Workday,"yyyy-MM-dd HH:mm:ss")}}</span>'}
                        ,{field: 'weekCur', title: '本周汇报', width:250}
                        ,{field: 'studentQuestion', title: '问题学生反馈', width: 250}
                        ,{field: 'Idea', title: '意见', width: 250}
                        ,{field: 'weekNext', title: '下周计划', width:250}
                        ,{field: '', title: '操作', width: 135 ,toolbar:'#weeklyRowsCZ'}
                    ]]
                });

                // //监听行单击事件
                // table.on('row(test)', function(obj){
                //     console.log(obj.tr) //得到当前行元素对象
                //     console.log(obj.data) //得到当前行数据
                //     reloadSecondTable(obj.data.empId,obj.data.empName);
                // });

            });
        };

        reload();
    </script>
</body>
</html>
