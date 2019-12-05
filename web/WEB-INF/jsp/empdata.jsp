<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/12/4
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script>
    </script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>员工资料表</legend>
</fieldset>

<div class="layui-form">
    <table id="demo" lay-filter="test"></table>
    <script>
        layui.use('table', function(){
            var table = layui.table;

            //第一个实例
            table.render({
                elem: '#demo'
                ,height: 450
                ,url: '/emp/empDataList' //数据接口
                ,page: true //开启分页
                ,cols: [[ //表头
                    {field: 'empId', title: '员工编号', width:100, sort: true, fixed: 'left'}
                    ,{field: 'empName', title: '员工姓名', width:100}
                    ,{field: 'depName', title: '部门', width:80}
                    ,{field: 'postName', title: '职务', width:200}
                    ,{field: 'sex', title: '性别', width: 60}
                    ,{field: 'phone', title: '手机号码', width: 200}
                    ,{field: 'address', title: '家庭住址', width: 200}
                    ,{field: 'status', title: '状态', width: 60}
                    ,{field: '', title: '设置状态', width: 100,templet:'#empStatusA'}
                    ,{field: 'password', title: '初始密码', width: 135}
                    ,{field: '', title: '操作', width: 115 ,toolbar:'#barDemo'}
                ]]
            });

        });
    </script>
    <script type="text/html" id="empStatusA">
        {{# if(d.status == 1) { }}
            <a href="emp/updateEmpStatus/{{d.empId}}/{{d.status}}"  class="layui-table-link">
                禁用
            </a>
        {{# }else if(d.status == 0){ }}
            <a href="<%=request.getContextPath()%>/emp/updateEmpStatus/{{d.empId}}/{{d.status}}"  class="layui-table-link">
                启用
            </a>
        {{# } }}

    </script>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</div>

</body>
</html>
