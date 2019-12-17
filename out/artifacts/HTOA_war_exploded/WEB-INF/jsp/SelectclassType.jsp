<%--
  Created by IntelliJ IDEA.
  User: 28401
  Date: 2019/12/14
  Time: 8:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课程类别</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
</head>
<body>
<div class="layui-form">
    <div style="width: 1365px;margin: 0 auto">
        <%--    课程类别    --%>
        <table id="demo" lay-filter="test"></table>
            <%--    员工表表头工具    --%>
            <script type="text/html" id="toolbarDemo">
                <div class="layui-btn-container">
                    <button class="layui-btn layui-btn-sm" onclick="add()">添加员工</button>
                    <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reload()">刷新表格</button>
                </div>
            </script>
            <%--    每行员工信息的操作按钮    --%>
            <script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-xs" href="javascript:update('{{ d.empId }}')" lay-event="edit">编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" onclick="deleteEmp('{{ d.empId }}')">删除</a>
            </script>
    </div>
</div>
</body>
<script type="text/javascript">
    function reload(){
        layui.use('table', function(){
            var table = layui.table;

            //第一个实例
            table.render({
                elem: '#demo'
                ,height: 523
                ,url: '/student/SelectclassTypeData' //数据接口
                ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
                ,page: true //开启分页
                ,cols: [[ //表头
                    {field: 'empId', title: '员工编号', width:100, sort: true, fixed: 'left'}
                    ,{field: 'empName', title: '员工姓名', width:100}
                    ,{field: 'depName', title: '部门', width:80}
                    ,{field: '', title: '操作', width: 115 ,toolbar:'#barDemo'}
                ]]
            });

            //监听行单击事件
            table.on('row(test)', function(obj){
                console.log(obj.tr) //得到当前行元素对象
                console.log(obj.data) //得到当前行数据
                reloadSecondTable(obj.data.empId,obj.data.empName);
            });

        });
    };
    reload();
</script>
</html>
