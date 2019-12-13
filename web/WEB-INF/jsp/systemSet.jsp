<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/12/10
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统设置</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/layui/lay/modules/layer.js" charset="utf-8"></script>
    <script>
    </script>
</head>
<body>
<div class="layui-fluid" style="padding: 25px 30px 75px ;">
    <div class="layui-row">
        <div class="layui-col-sm3" align="center">
            <div class="grid-demo grid-demo-bg1">
                <a href="${pageContext.request.contextPath}/system/systemdata" data-id="301" data-title="届别设置"  class="site-demo-active" style="cursor:pointer" data-type="systemdata"  ><i class="layui-icon  layui-icon-set-sm" style="font-size: 100px;color:#2D93CA;"></i></a></div>
            <div style="font-size: 20px;">届别设置</div>
        </div>
        <div class="layui-col-sm3" align="center">
            <div class="grid-demo grid-demo-bg1">
                <a href="${pageContext.request.contextPath}/system/classtype" data-id="302" data-title="班级类别"  class="site-demo-active" style="cursor:pointer" data-type="classtype"  ><i class="layui-icon  layui-icon-set-sm" style="font-size: 100px;color:#2D93CA;"></i></a></div>
            <div style="font-size: 20px;">班级类别</div>
        </div>
        <div class="layui-col-sm3" align="center">
            <div class="grid-demo grid-demo-bg1">
                <a href="${pageContext.request.contextPath}/system/project" data-id="303" data-title="项目答辩"  class="site-demo-active" style="cursor:pointer" data-type="classtype"  ><i class="layui-icon  layui-icon-set-sm" style="font-size: 100px;color:#2D93CA;"></i></a></div>
            <div style="font-size: 20px;">项目答辩</div>
        </div>
        <div class="layui-col-sm3" align="center">
            <div class="grid-demo grid-demo-bg1">
                <a href="${pageContext.request.contextPath}/system/dept" data-id="304" data-title="院系设置"  class="site-demo-active" style="cursor:pointer" data-type="dept"  ><i class="layui-icon  layui-icon-set-sm" style="font-size: 100px;color:#2D93CA;"></i></a></div>
            <div style="font-size: 20px;">院系设置</div>
        </div>
    </div>

</div>
<div class="layui-fluid" style="padding: 25px 30px 75px ;">
    <div class="layui-row">
        <div class="layui-col-sm3" align="center">
            <div class="grid-demo grid-demo-bg1">
                <a href="${pageContext.request.contextPath}/system/major" data-id="305" data-title="专业设置"  class="site-demo-active" style="cursor:pointer" data-type="major"  ><i class="layui-icon  layui-icon-set-sm" style="font-size: 100px;color:#2D93CA;"></i></a></div>
            <div style="font-size: 20px;">专业设置</div>
        </div>
        <div class="layui-col-sm3" align="center">
            <div class="grid-demo grid-demo-bg1">
                <a href="${pageContext.request.contextPath}/system/school" data-id="306" data-title="报考学校"  class="site-demo-active" style="cursor:pointer" data-type="school"  ><i class="layui-icon  layui-icon-set-sm" style="font-size: 100px;color:#2D93CA;"></i></a></div>
            <div style="font-size: 20px;">报考学校</div>
        </div>
        <div class="layui-col-sm3" align="center">
            <div class="grid-demo grid-demo-bg1"><i class="layui-icon  layui-icon-set-sm" style="font-size: 100px;"></i></div>
            <div>3333</div>
        </div>
        <div class="layui-col-sm3" align="center">
            <div class="grid-demo"><i class="layui-icon  layui-icon-set-sm" style="font-size: 100px;"></i></div>
            <div>5555</div>
        </div>
    </div>
</div></body>
</html>
