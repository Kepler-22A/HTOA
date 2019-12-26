<%--
  Created by IntelliJ IDEA.
  User: 28401
  Date: 2019/12/25
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>考勤管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <meta charset="utf-8">
    <script src="${pageContext.request.contextPath}/layui/layui.all.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script type="text/javascript">
        //添加
        function add() {
            $("#secondTable1Form").attr("action","${pageContext.request.contextPath}/student/AddCourser");
            layer.open({
                type: 1,
                title:"未打卡说明",
                area:['35%','40%'],
                content: $("#secondTable1Form"),
                closeBtn :0, //隐藏弹出层的关闭按钮
                yes:function(index,layero){
                }
            });
        }
    </script>
</head>
<body>
    <table id="demo" lay-filter="test"></table>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" onclick="add()">添加未打卡说明</button>
            <button class="layui-btn layui-btn-normal" onclick="add()">我的审批</button>
            <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reload()">刷新表格</button>
        </div>
    </script>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" href="javascript:update('{{ d.courseID }}')" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" onclick="deleteWorkExprience('{{ d.courseID }}')">删除</a>
    </script>
    <form id="secondTable1Form" action="" method="post" style="display: none" class="layui-form">
        <input type="hidden" value="0" id="courseID" name="courseID">
        <div class="layui-form-item">
            <label class="layui-form-label">时间</label>
            <div class="layui-input-block">
                <input type="entertime" name="shijian" required  lay-verify="required" placeholder="单击此处选择日期" id="rutime" autocomplete="off" class="layui-input"style="width:190px">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">时间点：</label>
            <div class="layui-input-block" style="width: 190px;">
                <select name="isObligatory" lay-verify="required" id="isObligatory">
                    <option value="8:00" selected="">8:00</option>
                    <option value="14:00" selected="">14:00</option>
                    <option value="17:00" selected="">17:00</option>
                    <option value="21:00" selected="">21:00</option>
                </select>
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
            <button type="button" class="layui-btn layui-btn-primary" onclick="guanbi()">返回</button>
        </div>
    </form>
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
                ,url: '/emp/selectChecking' //数据接口
                ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
                ,page: true //开启分页
                ,cols: [[ //表头
                    {field: 'courseID', title: '编号',sort: true, fixed: 'left'}
                    ,{field: 'courseName', title: '员工姓名'}
                    ,{field: 'isObligatory', title: '未打卡时间'}
                    ,{field: 'courseTypeName', title: '课程类别'}
                    ,{field: 'remark', title: '原因说明'}
                    ,{field: 'remark', title: '审核人'}
                    ,{field: 'remark', title: '审核时间'}
                    ,{field: 'remark', title: '审核说明'}
                    ,{field: 'remark', title: '状态'}
                    ,{field: '', title: '操作',toolbar:'#barDemo'}
                ]]
            });


        });
    };
    reload();
</script>
</html>
