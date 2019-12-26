<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/24
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看邮件</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
</head>
<body style="background-color: #fff5fe;">
    <a href="javascript:history.back()" class="layui-btn layui-btn-danger layui-btn-xs" style="margin: 10px 0 10px 20px">返回</a>
    <div>
        <div style="background-color: #88f7e0;width: 70%;margin: 0 auto;padding:0 4px;border-radius: 5px;">
            <div style="background-color: #c086ff;width: 100%;padding:0 4px;border-radius: 5px;">
                <div style="background-color: #947e46;width: 100%;padding:0 4px;border-radius: 5px;">
                    <div style="background-color: #ffc7b1;width: 100%;padding: 30px 5px 10px 5px;border-radius: 5px;">
                        <h1 style="text-align: center;margin-bottom: 20px">${emailVo.topic}</h1>
                        <p style="margin-bottom: 20px;width: 80%;text-align: right;margin-bottom: 40px">发件人：${emailVo.empId}</p>
                        <div style="background-color: #f7e6cd;">${emailVo.content}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    if(${emailVo.receId == empId && emailVo.isRead == 2}){
        $.ajax({
            url:"${pageContext.request.contextPath}/message/changeEmailStatus/" + ${emailVo.emailId}
            ,success:function () {
                console.log("成功")
            },error:function () {
                console.log("失败")
            }
        });
    }
</script>
</html>
