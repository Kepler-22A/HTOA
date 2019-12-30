<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/30
  Time: 0:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工基本信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
</head>
<body>
    <table border="1px #000 solid" cellpadding="0" cellspacing="0" class="layui-table">
        <c:forEach items="${empList}" var="emp">
            <tr>
                <th colspan="4" style="text-align: center">员工信息</th>
            </tr>
            <tr>
                <td>员工姓名</td><td>${emp.empName}</td><td>部门名称</td><td>${emp.depName}</td>
            </tr>
            <tr>
                <td>出生日期</td><td>${emp.Birthday}</td><td>身份证号码</td><td>${emp.Cardno}</td>
            </tr>
            <tr>
                <td>籍贯</td><td>${emp.nation}</td><td>手机号</td><td>${emp.Phone}</td>
            </tr>
            <tr>
                <td>职务名称</td><td>${emp.postName}</td>
            </tr>
        </c:forEach>
        <tr>
            <th colspan="4" style="text-align: center">工作经历</th>
        </tr>
        <c:forEach items="${jobList}" var="job">
            <tr>
                <td>公司名称</td><td>${job.companyName}</td><td>岗位</td><td>${job.degree}</td>
            </tr>
            <tr>
                <td>入职时间</td><td>${job.startDate}</td><td>离职时间</td><td>${job.endDate}</td>
            </tr>
        </c:forEach>
        <tr>
            <th colspan="4" style="text-align: center">教育背景</th>
        </tr>
        <c:forEach items="${educationList}" var="education">
            <tr>
                <td>学校名称</td><td>${education.collegeName}</td><td>学历</td><td>${education.degree}</td>
            </tr>
            <tr>
                <td>入学时间</td><td>${education.startDate}</td><td>毕业时间</td><td>${education.endDate}</td>
            </tr>
        </c:forEach>
        <tr>
            <th colspan="4" style="text-align: center">家庭联系信息</th>
        </tr>
        <c:forEach items="${familyList}" var="family">
            <tr>
                <td>联系人名称</td><td>${family.contactName}</td><td>与员工关系</td><td>${family.relationship}</td>
            </tr>
            <tr>
                <td>联系电话</td><td colspan="3">${family.Phone}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
