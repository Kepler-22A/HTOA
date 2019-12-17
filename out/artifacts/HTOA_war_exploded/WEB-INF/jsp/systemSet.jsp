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
        //新增tab页
        function add1() {
            parent.active.tabAdd("${pageContext.request.contextPath}/system/systemdata", 3001, "届别设置");
            parent.active.tabChange(3001)
        }
        //新增tab页
        function add2() {
            parent.active.tabAdd("${pageContext.request.contextPath}/system/classtype", 3002, "班级类别");
            parent.active.tabChange(3002)
        }
        function add3() {
            parent.active.tabAdd("${pageContext.request.contextPath}/system/project", 3003, "项目答辩");
            parent.active.tabChange(3003)
        }
        function add4() {
            parent.active.tabAdd("${pageContext.request.contextPath}/system/dept", 3004, "院系设置");
            parent.active.tabChange(3004)
        }

        function add5() {
            parent.active.tabAdd("${pageContext.request.contextPath}/system/major ", 3005, "专业设置");
            parent.active.tabChange(3005)
        }

        function add6() {
            parent.active.tabAdd("${pageContext.request.contextPath}/system/school", 3006, "报考学校");
            parent.active.tabChange(3006)
        }

        function add7() {
            parent.active.tabAdd("${pageContext.request.contextPath}/system/term", 3007, "学期设置");
            parent.active.tabChange(3007)
        }

        function add8() {
            parent.active.tabAdd("${pageContext.request.contextPath}/system/systemdata", 3008, "届别设置");
            parent.active.tabChange(3008)
        }

        function add9() {
            parent.active.tabAdd("${pageContext.request.contextPath}/leave/listProcessDefinition", 3009, "流程设置");
            parent.active.tabChange(3009)
        }









    </script>
</head>
<body>
<div class="layui-fluid" style="padding: 25px 30px 33px ;">
    <div class="layui-row">
        <div class="layui-col-sm3" align="center">
            <div class="grid-demo grid-demo-bg1">
                <a onclick="add6()" class="site-demo-active" style="cursor:pointer" data-type="school"  ><i class="layui-icon  layui-icon-set-sm" style="font-size: 100px;color:#2D93CA;"></i></a></div>
            <div style="font-size: 20px;">报考学校</div>
        </div>
        <div class="layui-col-sm3" align="center">
            <div class="grid-demo grid-demo-bg1">
                <a onclick="add2()" class="site-demo-active" style="cursor:pointer" data-type="classtype"  ><i class="layui-icon  layui-icon-set-sm" style="font-size: 100px;color:#2D93CA;"></i></a></div>
            <div style="font-size: 20px;">班级类别</div>
        </div>
        <div class="layui-col-sm3" align="center">
            <div class="grid-demo grid-demo-bg1">
                <a onclick="add3()" class="site-demo-active" style="cursor:pointer" data-type="classtype"  ><i class="layui-icon  layui-icon-set-sm" style="font-size: 100px;color:#2D93CA;"></i></a></div>
            <div style="font-size: 20px;">项目答辩</div>
        </div>
        <div class="layui-col-sm3" align="center">
            <div class="grid-demo">
                <a onclick="add8()" class="site-demo-active" style="cursor:pointer" data-type="term" ><i class="layui-icon  layui-icon-set-sm" style="font-size: 100px;color:#2D93CA;"></i></a></div>
            <div style="font-size: 20px;">部门管理</div>
        </div>
    </div>
</div>
<div class="layui-fluid" style="padding: 25px 30px 33px ;">
    <div class="layui-row">
        <div class="layui-col-sm3" align="center">
            <div class="grid-demo grid-demo-bg1">
                <a onclick="add1()" class="site-demo-active" style="cursor:pointer" data-type="systemdata"  ><i class="layui-icon  layui-icon-set-sm" style="font-size: 100px;color:#2D93CA;"></i></a></div>
            <div style="font-size: 20px;">届别设置</div>
        </div>
        <div class="layui-col-sm3" align="center">
            <div class="grid-demo grid-demo-bg1">
                <a onclick="add5()" class="site-demo-active" style="cursor:pointer" data-type="major"  ><i class="layui-icon  layui-icon-set-sm" style="font-size: 100px;color:#2D93CA;"></i></a></div>
            <div style="font-size: 20px;">专业设置</div>
        </div>

        <div class="layui-col-sm3" align="center">
            <div class="grid-demo grid-demo-bg1">
                <a onclick="add7()" class="site-demo-active" style="cursor:pointer" data-type="term"  ><i class="layui-icon  layui-icon-set-sm" style="font-size: 100px;color:#2D93CA;"></i></a></div>
            <div style="font-size: 20px;">学期设置</div>
        </div>
        <div class="layui-col-sm3" align="center">
            <div class="grid-demo grid-demo-bg1">
                <a onclick="add4()" class="site-demo-active" style="cursor:pointer" data-type="dept"  ><i class="layui-icon  layui-icon-set-sm" style="font-size: 100px;color:#2D93CA;"></i></a></div>
            <div style="font-size: 20px;">院系设置</div>
        </div>
        </div>
    </div>
<div class="layui-fluid" style="padding: 25px 30px 33px ;">
    <div class="layui-row">
        <div class="layui-col-sm3" align="center">
            <div class="grid-demo grid-demo-bg1">
                <a onclick="add9()" class="site-demo-active" style="cursor:pointer" data-type="systemdata"  ><i class="layui-icon  layui-icon-set-sm" style="font-size: 100px;color:#2D93CA;"></i></a></div>
            <div style="font-size: 20px;">流程设置</div>
        </div>
        <div class="layui-col-sm3" align="center">
            <div class="grid-demo grid-demo-bg1">
                <a onclick="add5()" class="site-demo-active" style="cursor:pointer" data-type="major"  ><i class="layui-icon  layui-icon-set-sm" style="font-size: 100px;color:#2D93CA;"></i></a></div>
            <div style="font-size: 20px;">专业设置</div>
        </div>
    </div>

</div>
</div>
</body>
</html>
