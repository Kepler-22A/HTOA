<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/14
  Time: 9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>周报详情</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
</head>
<body>
    <a class="layui-btn layui-btn-danger layui-btn-sm" href="javascript:history.back()">返回</a>
    <div style="width:600px;height: 750px;margin: 0 auto">
        <fieldset class="layui-elem-field layui-field-title">
            <legend>宏图软件工作周报</legend>
        </fieldset>

        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <span>部门：${depName}</span>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <span>姓名：${empName}</span>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <span>时间：${Workday}</span>

<%--        //本周情况--%>
        <div id="thisWeekDiv" style="height: 500px;border: solid 1px #666">
            <div id="thisWeekDivTitle" style="height:500px;width:30px;float: left;border-right: solid 1px #666">
                <h2>
                    本
                    周
                    总
                    结
                </h2>
            </div>
            <div id="thisWeekDivData" style="float: left;width: 567px">
                <div id="thisWeekContainer" style="width: 100%;height: 55%"><%--//本周情况***************************************--%>
                    <div id="thisWeekContainerTitle" style="float: left;width: 30px;height: 100%">
                        <h3>本周情况</h3>
                    </div>
                    <div id="thisWeekContainerData" style="float: left;border-left: solid 1px #666;width: 536px;height: 100%">
                        ${weekCur}
                    </div>
                    <p style="clear: both"></p>
                </div>
                <div id="studentQuestionContainer" style="width: 100%;height: 20%"><%--//问题学生*******************************--%>
                    <div id="studentQuestionContainerTitle" style="float: left;border-top:solid 1px #666;border-bottom:solid 1px #666;width: 30px;height: 98px">
                        <h3>问题学生</h3>
                    </div>
                    <div id="studentQuestionContainerData" style="float: left;border: solid 1px #666;border-right:none;width: 536px;height: 98px">
                        ${studentQuestion}
                    </div>
                    <p style="clear: both"></p>
                </div>
               <div id="IdeaContainer" style="width: 100%;height: 25%"><%-- //意见*********************************************--%>
                    <div id="IdeaContainerTitle" style="float: left;width: 30px;height: 100%">
                        <h3>意见</h3>
                    </div>
                    <div id="IdeaContainerData" style="float: left;border-left: solid 1px #666;width: 536px;height: 100%">
                        ${Idea}
                    </div>
                    <p style="clear: both"></p>
                </div>
            </div>
            <p style="clear: both"></p>
        </div>

<%--        //下周计划--%>
        <div id="nextWeekDiv" style="height: 150px;border: solid 1px #666">
            <div id="nextWeekDivTitle" style="height:150px;width:30px;float: left;border-right: solid 1px #666">
                <h2>
                    下
                    周
                    计
                    划
                </h2>
            </div>
            <div id="nextWeekDivData" style="float: left;width: 567px">
                <div id="nextWeekContainer"  style="width: 100%;height: 100%"><%--//下周情况**************************************--%>
                    <div id="nextWeekContainerTitle" style="float: left;border-right: solid 1px #666;width: 30px;height: 100%">
                        <h3>下周计划</h3>
                    </div>
                    <div id="nextWeekContainerData" style="float: left;width: 536px;height: 100%">
                        ${weekNext}
                    </div>
                    <p style="clear: both"></p>
                </div>
            </div>
            <p style="clear: both"></p>
        </div>
    </div>
</body>
</html>
