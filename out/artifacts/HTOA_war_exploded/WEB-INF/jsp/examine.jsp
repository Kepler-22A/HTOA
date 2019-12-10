<%--
  Created by IntelliJ IDEA.
  User: 半夏VEZ
  Date: 2019/12/5
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>考核指标页</title>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>
    <table class="layui-hide" id="test"></table>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
        <legend>新增指标</legend>
    </fieldset>
    <form class="layui-form layui-form-pane" action="${pageContext.request.contextPath}/Controller/addExamine">
        <div class="layui-form-item">
            <label class="layui-form-label">考核内容</label>
            <div class="layui-input-block">
                <input type="text" name="auditName" autocomplete="off" placeholder="请输入内容" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">考核分数</label>
            <div class="layui-input-inline">
                <input type="text" name="scores" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" >
            <label class="layui-form-label">考核部门</label>
            <div class="layui-input-inline" lay-filter="example">
                <select  name="depID" lay-filter="aihao">
                    <option value=""></option>
                    <option value="2">教研部</option>
                    <option value="3" >学工部</option>
                    <option value="1004">行政部</option>
                    <option value="1006">后勤部</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">说明</label>
            <div class="layui-input-block">
                <textarea name="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <button class="layui-btn" type="submit">提交</button>
            <button type="button" class="layui-btn layui-btn-normal" id="LAY-component-form-getval">取值</button>
        </div>
    </form>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test'
            ,url:'/Controller/table'
            ,cols: [[
                {field:'auditModelID', width:100, title: '编号'}
                ,{field:'auditName', width:350, title: '考核内容'}
                ,{field:'scores', width:100, title: '考核分数', sort: true}
                ,{field:'auditTypeName', width:150, title: '部门名称'}
                ,{field:'remark', title: '说明', minWidth: 100}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:200}
            ]]
            ,page: true
        });
        var table = layui.table;

        //监听工具条
        table.on('tool(barDemo)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                layer.msg('ID：'+ data.id + ' 的查看操作');
            } else if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del();
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                layer.alert('编辑行：<br>'+ JSON.stringify(data))
            }
        });
    });
</script>
</body>
</html>