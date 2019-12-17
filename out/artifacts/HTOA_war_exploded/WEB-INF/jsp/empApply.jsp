<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/17
  Time: 8:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工请假</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
</head>
<body>
<%--    员工请假表--%>
    <div id="empLeaveTable"></div>

    <%--    员工表表头工具    --%>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" onclick="add()">请假</button>
            <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reload()">刷新表格</button>
        </div>
    </script>

    <%--    每行员工信息的操作按钮    --%>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" href="javascript:update('{{ d.empId }}')" lay-event="edit">查看批注</a>
    </script>

<%-- 根据状态ID显示状态--%>
    <script id="statusShow" type="text/html">
        {{# if(d.status == 1){ }}
            审批中
        {{# }else if(d.status == 2){ }}
            已完成
        {{# }else if(d.status == 3){ }}
            未批准
        {{# } }}
    </script>

    <script>
        function reload(){
            layui.use('table', function(){
                var table = layui.table;

                //第一个实例
                table.render({
                    elem: '#empLeaveTable'
                    ,height: 523
                    ,url: '/leave/selEmpApply' //数据接口
                    ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
                    ,page: true //开启分页
                    ,cols: [[ //表头
                        {field: 'holidayID', title: '编号', width:150, sort: true, fixed: 'left'}
                        ,{field: 'empName', title: '请假人', width:150}
                        ,{field: 'holidayDay', title: '请假时长', width:150,templet:'<span>{{getHolidayString(d.holidayDay)}}</span>'}
                        ,{field: 'startTime', title: '开始时间', width:200,templet : '<span>{{layui.util.toDateString(d.startTime,"yyyy-MM-dd HH:mm:ss")}}</span>'}
                        ,{field: 'endTime', title: '结束时间', width: 200,templet : '<span>{{layui.util.toDateString(d.endTime,"yyyy-MM-dd HH:mm:ss")}}</span>'}
                        ,{field: 'status', title: '状态', width: 150,templet:'#statusShow'}
                        ,{field: 'remark', title: '备注', width: 300}
                        ,{field: '', title: '操作', width: 100 ,toolbar:'#barDemo'}
                    ]]
                });

            });
        };

        reload();

        function getHolidayString(data) {

            return parseInt(data) + "天" + ((data)%1)*24 + "小时";
        }
    </script>
</body>
</html>
