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
    <title>员工资料</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script>
    </script>
</head>
<body>

<%--员工资料表部分--%>

<div class="layui-form">
    <div style="width: 1365px;margin: 0 auto">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;text-align: center">
            <legend>员工资料表</legend>
        </fieldset>
        <table id="demo" lay-filter="test"></table>
    </div>
    <script>
        layui.use('table', function(){
            var table = layui.table;

            //第一个实例
            table.render({
                elem: '#demo'
                ,height: 450
                ,url: '/emp/empDataList' //数据接口
                ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
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
            <a href="<%=request.getContextPath()%>/emp/updateEmpStatus/{{d.empId}}"  class="layui-table-link">
                禁用
            </a>
        {{# }else if(d.status == 0){ }}
            <a href="<%=request.getContextPath()%>/emp/updateEmpStatus/{{d.empId}}"  class="layui-table-link">
                启用
            </a>
        {{# } }}

    </script>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" href="<%=request.getContextPath()%>/emp/delEmp/{{ d.empId }}">删除</a>
    </script>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" >添加员工</button>
        </div>
    </script>

<%--员工添加&修改的表单--%>
</div>
<div style="width: 765px;margin: 0 auto">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>员工表单</legend>
    </fieldset>

    <form class="layui-form" action="" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">员工姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="empName" lay-verify="title" autocomplete="off" placeholder="员工姓名" class="layui-input">
            </div>

            <div class="layui-inline">
                <label class="layui-form-label">部门名称</label>
                <div class="layui-input-inline">
                    <select name="depId">
                        <option value="1">宏图</option>
                        <option value="2">教研部</option>
                        <option value="3">学工部</option>
                        <option value="1004">行政部</option>
                        <option value="1006">后勤部</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">职务名称</label>
            <div class="layui-input-inline">
                <input type="text" name="postName" lay-verify="title" autocomplete="off" placeholder="请输入职务名称" class="layui-input">
            </div>

            <label class="layui-form-label">家庭住址</label>
            <div class="layui-input-inline">
                <input type="text" name="Address" lay-verify="title" autocomplete="off" placeholder="请输入家庭住址" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-inline">
                <select name="sex">
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </div>

            <label class="layui-form-label">身份证号</label>
            <div class="layui-input-inline">
                <input type="text" name="Cardno" lay-verify="title" autocomplete="off" placeholder="请输入身份证号" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">出生日期</label>
            <div class="layui-input-inline">
                <input type="date" name="Birthday" lay-verify="title" autocomplete="off" placeholder="出生日期" class="layui-input">
            </div>

            <label class="layui-form-label">籍贯</label>
            <div class="layui-input-inline">


            </div>
        </div>
    </form>
</div>

</body>
</html>
