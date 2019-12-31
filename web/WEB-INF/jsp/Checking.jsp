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
            $("#secondTable1Form").attr("action","${pageContext.request.contextPath}/emp/addChecking");
            layer.open({
                type: 1,
                title:"未打卡说明",
                area:['35%','45%'],
                content: $("#secondTable1Form"),
                closeBtn :0, //隐藏弹出层的关闭按钮
                yes:function(index,layero){
                }
            });
        }
        function guanbi() {
            layer.closeAll();
            $("#CheckingID").val("")
            $("#causeReamk").val("")
        }
    </script>
</head>
<body>
    <table id="demo" lay-filter="test"></table>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" onclick="add()">添加未打卡说明</button>
            {{# if(${postName == '教研主任' || postName == '学工主任' || postName == '校长'}){ }}
                <a href="${pageContext.request.contextPath}/emp/manChecking"><button class="layui-btn layui-btn-normal layui-btn-sm">我的审批</button></a>
            {{# } }}
            <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reload()">刷新表格</button>
        </div>
    </script>
    <form id="secondTable1Form" action="" method="post" style="display: none" class="layui-form">
        <input type="hidden" value="0" id="CheckingID" name="CheckingID">
        <div class="layui-form-item">
            <label class="layui-form-label">时间</label>
            <div class="layui-input-block">
                <input type="entertime" name="shijian" required  lay-verify="required" placeholder="单击此处选择日期" id="rutime" autocomplete="off" class="layui-input"style="width:190px">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">时间点：</label>
            <div class="layui-input-block" style="width: 190px;">
                <select name="time" lay-verify="required" id="time">
                    <option value="8:00" selected="">8:00</option>
                    <option value="14:00" selected="">14:00</option>
                    <option value="17:00" selected="">17:00</option>
                    <option value="21:00" selected="">21:00</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">说明</label>
            <div class="layui-input-block">
                <textarea name="causeReamk" placeholder="请输入内容" class="layui-textarea" style="width: 300px"></textarea>
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
<script type="textml" id="roleTpl">
    {{# if (d.state=== 3) { }}
        还没有审核
    {{# } else if(d.state=== 1) { }}
        审核通过
    {{# } else { }}
        正在审核
    {{# } }}
</script>
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
                    {field: 'CheckingID', title: '编号',sort: true, fixed: 'left'}
                    ,{field: 'empName', title: '员工姓名'}
                    ,{field: 'NOdate', title: '未打卡时间'}
                    ,{field: 'causeReamk', title: '原因说明'}
                    ,{field: 'chairman', title: '审核人'}
                    ,{field: 'auditdate', title: '审核时间',templet : '<span>{{layui.util.toDateString(d.auditdate,"yyyy-MM-dd HH:mm:ss")}}</span>'}
                    ,{field: 'reamk', title: '审核说明'}
                    ,{field: 'state', title: '状态',templet: '#roleTpl'}
                ]]
            });


        });
        //Demo
        layui.use('laydate', function(){
            var laydate = layui.laydate;

            //执行一个laydate实例
            laydate.render({
                elem: '#rutime' //指定元素
                ,theme: '#393D89'
            });
        });
    };
    reload();
</script>
</html>
