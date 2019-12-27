<%--
  Created by IntelliJ IDEA.
  User: 28401
  Date: 2019/12/20
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>展示公告管理的页面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <meta charset="utf-8">
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
</head>
<body>
    <form class="layui-form">
        <div>
            <div class="layui-form-item" style="display: inline-block;margin-left: 20px">
                <a href="${pageContext.request.contextPath}/message/notice"><i class="layui-icon" style="font-size: 20px;margin-left: 15px;" title="返回">&#xe65c;</i></a>
            </div>
        </div>
        <div id="demo">

        </div>

    </form>
</body>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    $(document).ready(function () {
        $.post("${pageContext.request.contextPath}/message/selectcontent/${id}",{},function (data) {
            var t = $("#demo").html();
            $.each(data,function (index,obj) {
                $.each(obj,function (i,o) {
                    t += o.content;
                })
            });
            $("#demo").html(t);
            layui.use(['form'], function(){
                var form = layui.form;
                form.render('select');
            });
        },'json');

        $.ajax({url:'http://localhost:8888/message/changeNoticeReceiverIsRead/${id}',success:function (data) {
                console.log("已读完成");
            },error:function (data) {
                console.log("已读失败");
            }})
    });
</script>
</html>
