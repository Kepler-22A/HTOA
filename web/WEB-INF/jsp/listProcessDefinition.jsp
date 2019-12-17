<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/16
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>权限设置</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
</head>
<body>
<table id="processTable"></table>

<%--每行数据的操作部分--%>
<script id="processRowsCZ" type="text/html">
    <a class="layui-btn layui-btn-xs" lay-event="edit" href="javascript:delProcess('{{ d.deploymentId }}')">删除流程图</a>
    <a class="layui-btn laui-btn-danger layui-btn-xs" lay-event="edit" href="${pageContext.request.contextPath}/leave/viewProcessImage/{{ d.deploymentId }}/{{ d.diagramResourceName }}">查看流程图</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit" href="${pageContext.request.contextPath}/leave/toExport/{{ d.id }}">下载流程图</a>
</script>

<%--表头工具栏--%>
<script type="text/html" id="processToolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" onclick="addProcess()">上传流程图</button>
        <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reload()">刷新表格</button>
    </div>
</script>

<form id="processUploadForm" style="display: none" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/leave/uploadProcess" class="layui-form">
    <div class="layui-form-item">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="file" name="pdFile" class="layui-input"><br>
    </div>
    <div class="layui-form-item">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button class="layui-btn" lay-submit lay-filter="formDemo">上传</button>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button type="button" class="layui-btn layui-btn-primary" onclick="close()">返回</button>
    </div>
</form>

<script>
    //表格数据编写
    function reload(){
        layui.use('table', function(){
            var table = layui.table;

            //表格数据接收
            table.render({
                elem: '#processTable'
                ,height: 523
                ,url: '${pageContext.request.contextPath}/leave/progressDefine' //数据接口
                ,toolbar: '#processToolbar' //开启头部工具栏，并为其绑定左侧模板
                ,page: true //开启分页
                ,cols: [[ //表头
                    {field: 'id', title: '流程ID', width:250, sort: true, fixed: 'left'}
                    ,{field: 'name', title: '流程名称', width:250}
                    ,{field: 'key', title: '流程Key', width:250}
                    ,{field: '', title: '操作', width: 280 ,toolbar:'#processRowsCZ'}
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

    function addProcess(){
        layer.open({
            type: 1,
            title:"新增流程",
            area:['20%','30%'],
            content: $("#processUploadForm"),
            closeBtn :0, //隐藏弹出层的关闭按钮
            yes:function(index,layero){
            }
        });
    };

    function delProcess(id){
        layer.confirm('是否要删除？', {
            icon:3,
            btn: ['确认','取消'] //按钮
        }, function(){+
            $.post('${pageContext.request.contextPath}/leave/delProgressDefine/' + id,
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

    function close() {
        layer.closeAll();
    }
</script>
</body>
</html>
