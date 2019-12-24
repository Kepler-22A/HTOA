<%--
  Created by IntelliJ IDEA.
  User: 28401
  Date: 2019/12/23
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>谈心记录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <meta charset="utf-8">
    <script src="${pageContext.request.contextPath}/layui/layui.all.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script type="text/javascript">
        //添加
        function add() {
            $("#secondTable1Form").attr("action","${pageContext.request.contextPath}/emp/AddCharRecord");
            layer.open({
                type: 1,
                title:"新增",
                area:['50%','50%'],
                content: $("#secondTable1Form"),
                closeBtn :0, //隐藏弹出层的关闭按钮
                yes:function(index,layero){
                }
            });
        }
        //修改
        function  update(hourid) {
            $.post("${pageContext.request.contextPath}/emp/selectCharRecordID/"+hourid,function (d) {
                $("#secondTable1Form").attr("action","${pageContext.request.contextPath}/emp/UpdateChatRecord");
                $("#ChatID").val(d.StudentVo.ChatID);
                $("#stuname").val(d.StudentVo.stuname);
                $("#addr").val(d.StudentVo.addr);
                document.getElementById("sayContent").innerHTML = d.StudentVo.sayContent;
                var laydate = layui.laydate;
                laydate.render({
                    elem: '#rutime'
                    ,value: layui.util.toDateString(d.StudentVo.chatDate,"yyyy-MM-dd HH:mm:ss")
                    ,isInitValue: true
                });
            },"json");
            layer.open({
                type: 1,
                title:"修改",
                area:['50%','50%'],
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
        function guanbi() {
            layer.closeAll();
            $("#stuname").val("")
            $("#addr").val("")
            $("#ChatID").val(0)
            $("#sayContent").val("")
        }
    </script>
</head>
<body>
    <table id="demo" lay-filter="test"></table>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" onclick="add()">添加记录</button>
            <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reload()">刷新表格</button>
        </div>
    </script>
    <%--    每行员工信息的操作按钮    --%>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" href="javascript:update('{{ d.ChatID }}')" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" onclick="deleteWorkExprience('{{ d.ChatID }}')">删除</a>
    </script>
    <form id="secondTable1Form" action="" method="post" style="display: none" class="layui-form">
        <input type="hidden" value="0" id="ChatID" name="ChatID">
        <div class="layui-form-item">
            <label class="layui-form-label">学生名称</label>
            <div class="layui-input-inline">
                <input id="stuname" type="text" name="name" lay-verify="ST1Remark" autocomplete="off" placeholder="请输入已知学生姓名" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">地址</label>
            <div class="layui-input-inline">
                <input id="addr" type="text" name="addr" lay-verify="ST1Remark" autocomplete="off" placeholder="请输入地址" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">时间</label>
            <div class="layui-input-block">
                <input type="entertime" name="shijian" required  lay-verify="required" placeholder="单击此处选择日期" id="rutime" autocomplete="off" class="layui-input"style="width:190px">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">谈心内容</label>
            <div class="layui-input-block">
                <textarea name="sayContent" id="sayContent" placeholder="请输入内容" class="layui-textarea" style="width:500px"></textarea>
            </div>
        </div>

        <div class="layui-form-item" style="margin: 0 auto">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button class="layui-btn" lay-filter="workExprienceForm">立即提交</button>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button type="button" class="layui-btn layui-btn-primary" onclick="guanbi()">返回</button>
        </div>
    </form>
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</body>
<script>
    reload();
    function reload(){
        layui.use('table', function(){
            var table = layui.table;

            //第一个实例
            table.render({
                elem: '#demo'
                ,height: 523
                ,url: '/emp/selectCharRecord' //数据接口
                ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
                ,page: true //开启分页
                ,cols: [[ //表头
                    {field: 'ChatID', title: '编号',sort: true, fixed: 'left',width:80}
                    ,{field: 'stuname', title: '学生名称',width: 100}
                    ,{field: 'empName', title: '员工名称',width: 100}
                    ,{field: 'addr', title: '地址',width: 100}
                    ,{field: 'sayContent', title: '谈心内容',}
                    ,{field: 'chatDate', title: '时间', width:180,templet : '<span>{{layui.util.toDateString(d.chatDate,"yyyy-MM-dd HH:mm:ss")}}</span>'}
                    ,{field: '', title: '操作',toolbar:'#barDemo',width:150}
                ]]
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
        });
    };

</script>
</html>
