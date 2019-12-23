<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/23
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发送邮件</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
</head>
<body>
<a class="layui-btn layui-btn-xs" href="${pageContext.request.contextPath}/message/toEmailPage">返回</a>
<div style="border-bottom: 1px solid #ccc;margin: 10px 0"></div>
<form id="createEmailForm" lay-filter="createEmailForm" class="layui-form">
    <div class="layui-form-item">
        <label class="layui-form-label">标题</label>
        <div class="layui-input-inline" style="width: 200px">
            <input id="topic" class="layui-input" name="topic" type="text" lay-verify="topic" autocomplete="off" placeholder="请输入标题">
        </div>

        <label class="layui-form-label">接收人</label>
        <div class="layui-input-inline" style="width: 200px">
            <input id="receId" class="layui-input" name="receId" type="text" lay-verify="receId" autocomplete="off" readonly="readonly">
        </div>
        <div class="layui-input-inline">
            <button class="layui-btn layui-btn-danger" onclick="addReceName()">选择发送人</button>
        </div>
        <div class="layui-input-inline" style="width: 80px">
            <button class="layui-icon layui-btn" lay-submit lay-filter="emailForm">发送邮件</button>
        </div>
    </div>

<%--    <div class="layui-form-item">--%>
<%--        <label class="layui-form-label">附件</label>--%>
<%--        <div class="layui-input-inline" style="width: 80px">--%>
<%--            <input id="PDFile" type="file" name="PDFile">--%>
<%--        </div>--%>
<%--    </div>--%>


    <textarea id="demo" style="display: none;"></textarea>

</form>
</body>
<script>
    function addReceName() {

    }
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;

        //富文本编辑器
        var layedit = layui.layedit;
        layedit.build('demo', {
            height: 900 //设置编辑器高度
        });
        var array = layedit.build('demo');

        //监听提交
        form.on('submit(emailForm)', function(data){
            var A = JSON.stringify(data.field);//选项框的数据
            var content = layedit.getContent(array);//获取文本框的数据
            var receId = $("#receId").val();
            var topic = $("#topic").val();
            var url = '${pageContext.request.contextPath}/message/addEmail/' + ${empId};

            // var oFiles = document.getElementById("PDFile").files[0];

            // var params = new FormData();

            <%--params.append("pdFile",oFiles);--%>
            <%--params.append("empId",${empId});--%>
            <%--params.append("topic",$("#topic").val());--%>
            <%--params.append("receId",$("#receId").val());--%>
            <%--params.append("content",aa);--%>

            $.ajax({
                type:'post',
                async:true,
                url:url,
                data:$("#createEmailForm").serialize()
                ,
                success:function (data) {
                    if(data!=null){
                        layer.msg('保存成功');
                    }else {
                        layer.msg('保存失败');
                    }
                }
            });
            return false;
        });
    });
</script>
</html>
