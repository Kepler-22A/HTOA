<%--
  Created by IntelliJ IDEA.
  User: 28401
  Date: 2019/12/26
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的审批</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <meta charset="utf-8">
    <script src="${pageContext.request.contextPath}/layui/layui.all.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script type="text/javascript">
        function guanbi() {
            layer.closeAll();
            $("#CheckingID").val("")
            $("#causeReamk").val("")
        }
        //审批
        function add() {
            $("#secondTable1Form").attr("action","${pageContext.request.contextPath}/emp/addChecking");
            layer.open({
                type: 1,
                title:"审批",
                area:['35%','40%'],
                content: $("#secondTable1Form"),
                closeBtn :0, //隐藏弹出层的关闭按钮
                yes:function(index,layero){
                }
            });
        }
        //删除记录
        function deleteWorkExprience(jobId) {
            layer.confirm('是否要删除？', {
                icon:3,
                btn: ['确认','取消'] //按钮
            }, function(){+
                $.post('<%=request.getContextPath()%>/emp/delectCharRecord/' + jobId,{},
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

        function OK(jobId,OK) {
            layer.confirm('是否要通过？', {
                icon:3,
                btn: ['确认','取消'] //按钮
            }, function(){+
                $.post('<%=request.getContextPath()%>/emp/updatestate/' + jobId+'/'+OK,{},
                    function (data) {
                        reload();
                    });
                layer.msg('已通过审批', {
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
    <table id="demo" lay-filter="test"></table>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a href="${pageContext.request.contextPath}/emp/Checking"><button class="layui-btn layui-btn-sm" onclick="add()">返回</button></a>
            <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reload()">刷新表格</button>
        </div>
    </script>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" href="javascript:OK('{{ d.CheckingID }}','{{1}}')" lay-event="edit">属实</a>
        <a class="layui-btn layui-btn-warm layui-btn-xs" href="javascript:OK('{{ d.CheckingID }}','{{2}}')" lay-event="edit">不属实</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" onclick="deleteWorkExprience('{{ d.CheckingID }}')">删除</a>
    </script>
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
                ,url: '/emp/selectXiaJiChecking' //数据接口
                ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
                ,page: true //开启分页
                ,cols: [[ //表头
                    {field: 'CheckingID', title: '编号',sort: true, fixed: 'left'}
                    ,{field: 'empName', title: '员工姓名'}
                    ,{field: 'NOdate', title: '未打卡时间'}
                    ,{field: 'causeReamk', title: '原因说明'}
                    ,{field: 'auditdate', title: '审核时间',templet : '<span>{{layui.util.toDateString(d.auditdate,"yyyy-MM-dd HH:mm")}}</span>'}
                    ,{field: 'reamk', title: '审核说明'}
                    ,{field: 'state', title: '状态',templet: '#roleTpl'}
                    ,{field: '', title: '操作',toolbar:'#barDemo'}
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
