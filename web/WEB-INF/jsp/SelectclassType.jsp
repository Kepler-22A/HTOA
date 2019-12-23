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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <meta charset="utf-8">
    <script src="${pageContext.request.contextPath}/layui/layui.all.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script type="text/javascript">
        //添加
        function add() {
            $("#secondTable1Form").attr("action","${pageContext.request.contextPath}/student/AddClassType");
            layer.open({
                type: 1,
                title:"新增",
                area:['30%','30%'],
                content: $("#secondTable1Form"),
                closeBtn :0, //隐藏弹出层的关闭按钮
                yes:function(index,layero){
                }
            });
        }
        //修改
        function  update(hourid) {
            $.post("${pageContext.request.contextPath}/student/selectClassType5/"+hourid,function (d) {
                $("#secondTable1Form").attr("action","${pageContext.request.contextPath}/student/updateClassType5/" + d.StudentVo.courseTypeID);
                $("#courseTypeID").val(d.StudentVo.courseTypeID);
                $("#courseTypeName").val(d.StudentVo.courseTypeName);
                $("#remark").val(d.StudentVo.remark);
            },"json");
            layer.open({
                type: 1,
                title:"修改",
                area:['30%','30%'],
                content: $("#secondTable1Form"),
                closeBtn :0, //隐藏弹出层的关闭按钮
                yes:function(index,layero){
                }
            });
        }
        //删除答辩成绩
        function deleteWorkExprience(jobId) {
            layer.confirm('是否要删除？', {
                icon:3,
                btn: ['确认','取消'] //按钮
            }, function(){+
                $.post('<%=request.getContextPath()%>/student/deleteClassType5/' + jobId,{},
                    function (data) {
                        reload();
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
</head>
<body>
<div class="layui-form">
    <div style="width: 1365px;margin: 0 auto">
        <%--    课程类别    --%>
        <table id="demo" lay-filter="test"></table>
            <%--    员工表表头工具    --%>
            <script type="text/html" id="toolbarDemo">
                <div class="layui-btn-container">
                    <button class="layui-btn layui-btn-sm" onclick="add()">添加类别</button>
                    <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reload()">刷新表格</button>
                </div>
            </script>
            <%--    每行员工信息的操作按钮    --%>
            <script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-xs" href="javascript:update('{{ d.courseTypeID }}')" lay-event="edit">编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" onclick="deleteWorkExprience('{{ d.courseTypeID }}')">删除</a>
            </script>
    </div>
    <form id="secondTable1Form" action="" method="post" style="display: none" class="layui-form">
        <input type="hidden" value="0" id="courseTypeID" name="courseTypeID">
        <div class="layui-form-item">
            <label class="layui-form-label">类别名称</label>
            <div class="layui-input-inline">
                <input id="courseTypeName" type="text" name="courseTypeName" lay-verify="ST1Remark" autocomplete="off" placeholder="请输入类别名称" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">说明</label>
            <div class="layui-input-inline">
                <input id="remark" type="text" name="remark" lay-verify="ST1Remark" autocomplete="off" placeholder="请输入说明" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item" style="margin: 0 auto">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button class="layui-btn" lay-filter="workExprienceForm">立即提交</button>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button type="button" class="layui-btn layui-btn-primary" onclick="closeWorkExperience()">返回</button>
        </div>
    </form>
</div>
</body>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script type="text/javascript">
    function reload(){
        layui.use('table', function(){
            var table = layui.table;

            //第一个实例
            table.render({
                elem: '#demo'
                ,height: 523
                ,url: '/student/selectClassType2' //数据接口
                ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
                ,page: true //开启分页
                ,cols: [[ //表头
                    {field: 'courseTypeID', title: '编号',sort: true, fixed: 'left'}
                    ,{field: 'courseTypeName', title: '类别名称'}
                    ,{field: 'remark', title: '说明',}
                    ,{field: '', title: '操作',toolbar:'#barDemo'}
                ]]
            });


        });
    };
    reload();
</script>
</html>
