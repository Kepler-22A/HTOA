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
    <div style="width:600px;height: 1000px;margin: 0 auto;border: solid 1px #666">
        <fieldset class="layui-elem-field layui-field-title">
            <legend>宏图软件工作周报</legend>
        </fieldset>

        <span>部门：</span>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <span>姓名：</span>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <span>时间：</span>

        //本周情况
        <div id="thisWeekDiv" style="height: 500px">
            <div id="thisWeekDivTitle" style="float: left">
                <h3>本周总结</h3>
            </div>
            <div id="thisWeekDivData" style="float: left">
                <div id="thisWeekContainer">//本周情况***************************************
                    <div id="thisWeekContainerTitle" style="float: left">

                    </div>
                    <div id="thisWeekContainerData" style="float: left">

                    </div>
                    <p style="clear: both"></p>
                </div>
                <div id="studentQuestionContainer">//问题学生*******************************
                    <div id="studentQuestionContainerTitle" style="float: left">

                    </div>
                    <div id="studentQuestionContainerData" style="float: left">

                    </div>
                    <p style="clear: both"></p>
                </div>
                <div id="IdeaContainer">//意见*********************************************
                    <div id="IdeaContainerTitle" style="float: left">

                    </div>
                    <div id="IdeaContainerData" style="float: left">

                    </div>
                    <p style="clear: both"></p>
                </div>
            </div>
            <p style="clear: both"></p>
        </div>

        //下周计划
        <div id="nextWeekDiv" style="height: 400px">
            <div id="nextWeekDivTitle" style="float: left">
                <h3>下周计划</h3>
            </div>
            <div id="nextWeekDivData" style="float: left">
                <div id="nextWeekContainer">//下周情况**************************************
                    <div id="nextWeekContainerTitle" style="float: left">

                    </div>
                    <div id="nextWeekContainerData" style="float: left">

                    </div>
                    <p style="clear: both"></p>
                </div>
            </div>
            <p style="clear: both"></p>
        </div>
    </div>
</body>
</html>
