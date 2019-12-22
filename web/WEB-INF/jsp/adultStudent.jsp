<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/18
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>任务详情</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
</head>
<body>
<form action="${pageContext.request.contextPath}/leave/completeStudent" theme="simple" method="post">
    <input type="hidden" name="holidayId" value="${holidayVo.holidayid}"/>
    <input type="hidden" name="taskId" value="${taskId}"/>
    <table width="90%" cellspacing="1" bgcolor="#6495ED" class="layui-table">
        <tr>
            <td align="right">请假人</td>
            <td>${stuName}</td>
        </tr>
        <tr bgcolor="white">
            <td align="right">请假天数：</td>
            <td id="qjTime"></td>
        </tr>
        <tr bgcolor="white">
            <td align="right">请假事由：</td>
            <td >${holidayVo.remark}</td>
        </tr>
        <tr bgcolor="white">
            <td align="right">是否批准：</td>
            <td >
                <select name="flow">
                    <option value="agree">同意</option>
                    <option value="disagree">拒绝</option>
                </select>
            </td>
        </tr>
        <tr bgcolor="white">
            <td align="right">备注：</td>
            <td ><textarea name="comment"></textarea></td>
        </tr>
        <tr bgcolor="white">
            <td colspan="2" align="center">
                <input class="layui-btn layui-btn-sm" type="submit" value="审批">
            </td>
        </tr>
    </table>
</form>

<hr/>
<table width="90%" cellspacing="1" bgcolor="#6495ED" lay-filter="parse-table-demo" class="layui-table">
    <tr bgcolor="#2F4F4F">
        <th colspan="2"><font color="white">历史审批信息</font></th>
    </tr>
    <tr bgcolor="#2F4F4F">
        <th><font color="white">ID</font></th>
        <th><font color="white">审批时间</font></th>
        <th><font color="white">审批人</font></th>
        <th><font color="white">批注内容</font></th>
    </tr>
    <c:forEach items="${commentList}" var="t">
        <tr bgcolor="white">
            <td>${t.id }</td>
            <td>${t.time} </td>
            <td>${t.userId }</td>
            <td>${t.fullMessage }</td>
        </tr>
    </c:forEach>
</table>
<script>
    function getHolidayString(data) {

        return parseInt(data) + "天" + parseInt(((data)%1)*24) + "小时";
    }

    $("#qjTime").html(getHolidayString(${holidayVo.holidayDay}));
</script>
</body>
</html>
