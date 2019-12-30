<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="java.util.Calendar" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/25
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的页面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;border: none">
    <legend><span id="myTitle">上班捞！不要眯梦捞！</span><a class="layui-btn layui-btn-xs" onclick="reloadPage()">刷新数据</a></legend>
</fieldset>

<div style="width:450px;height: 350px;padding: 5px;background-color: rgb(65,111,177)">
    <div style="width:100%;height: 100%;padding: 5px;background-color: rgb(255,224,85)">
        <div style="width:100%;height: 100%;padding: 5px;background-color: #e85cff">
            <div style="width:100%;height: 100%;padding: 5px;background-color: #65ff9f">
                <div style="width:100%;height: 100%;padding: 5px;background-color: #ffc6bd" id="container">
                    <br id="studentBR">
                    <a class="highClass" href="javascript:addTab('员工请假')" style="text-decoration: none">
                        <p id="empLeavaCrossP" style="color:#333;background-color: #ffe5d7;width: 90%;border-radius: 3px;padding-left: 3px">
                            员工请假待审批（<span id="empLeavaCrossNumber">0</span>）
                        </p>
                    </a>
                    <br class="highClass">
                    <a href="javascript:addTab('学生请假')" style="text-decoration: none">
                        <p id="stuLeavaCrossP" style="color:#333;background-color: #ffe5d7;width: 90%;border-radius: 3px;padding-left: 3px">
                            学生请假待审批（<span id="stuLeavaCrossNumber">0</span>）
                        </p>
                    </a>
                    <br>
                    <a class="highClass" href="javascript:" style="text-decoration: none">
                        <p id="dimissionCrossP" style="color:#333;background-color: #ffe5d7;width: 90%;border-radius: 3px;padding-left: 3px">
                            未打卡待审批（<span id="dimissionCrossNumber">0</span>）
                        </p>
                    </a>
                    <br class="highClass">
                    <a href="javascript:addTab('公告')" style="text-decoration: none" id="noticeA">
                        <p id="noticeWaitP" style="color:#333;background-color: #ffe5d7;width: 90%;border-radius: 3px;padding-left: 3px">
                            未读公告（<span id="noticeWaitNumber">0</span>）
                        </p>
                    </a>
                    <br>
                    <a href="javascript:addTab('邮件')" style="text-decoration: none">
                        <p id="emailWaitP" style="color:#333;background-color: #ffe5d7;width: 90%;border-radius: 3px;padding-left: 3px">
                            未读邮件（<span id="emailWaitNumber">0</span>）
                        </p>
                    </a>
                    <br>
                    <a href="javascript:addTab('我的周报')" style="text-decoration: none">
                        <p id="thisWooklyP" style="color:#333;background-color: #ffe5d7;width: 90%;border-radius: 3px;padding-left: 3px">
                            本周工作周报（<span id="thisWooklyNumber">未完成</span>）&nbsp;&nbsp;<span style="font-size: 12px;color: red">周日17:00前提交</span>
                        </p>
                    </a>
                    <br>
                    <a href="javascript:addTab('谈心记录')" style="text-decoration: none">
                        <p id="moothCharRecordP" style="color:#333;background-color: #ffe5d7;width: 90%;border-radius: 3px;padding-left: 3px">
                            月谈心记录（<span id="moothCharRecordNumber">0</span>）&nbsp;&nbsp;<span style="font-size: 12px;color: red">每月需完成5个</span>
                        </p>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>

    // 如果学生登录

    if (${studentId != null}){
        $("#container a").css("display","none");
        $("#container br").css("display","none");
        $("#studentBR").css("display","block");
        $("#noticeA").css("display","block");
        $("#myTitle").html("上课捞！不要迷梦捞！");
    }

    if (${postName == '讲师' || postName == '专职班主任' || postName == '教研副主任'}){
        $(".highClass").css("display","none");
    }

    function reloadPage() {
        if (${studentId != null}){
            //    获取未读公告数
            $.ajax({url:"${pageContext.request.contextPath}/message/selNoticeReceiverNumber",dataType:'json'
                ,success:function (data) {
                    $("#noticeWaitNumber").html(data.count);
                },error:function () {
                    layer.msg("获取未读公告数失败")
                }});
        }else {
            if (${postName != '讲师' || postName != '专职班主任' || postName != '教研副主任'}){
                <%--    获取员工请假待审核数--%>
                $.ajax({url:"${pageContext.request.contextPath}/leave/selMyTaskNumber",dataType:'json'
                    ,success:function (data) {
                        $("#empLeavaCrossNumber").html(data.count);
                    },error:function () {
                        layer.msg("获取员工请假待审核数失败")
                    }});
            }

            //    获取学生请假待审核数
            $.ajax({url:"${pageContext.request.contextPath}/leave/selMyTaskStudentNumber",dataType:'json'
                ,success:function (data) {
                    $("#stuLeavaCrossNumber").html(data.count);
                },error:function () {
                    layer.msg("获取学生请假待审核数失败")
                }});

            //    获取未读公告数
            $.ajax({url:"${pageContext.request.contextPath}/message/selNoticeReceiverNumber",dataType:'json'
                ,success:function (data) {
                    $("#noticeWaitNumber").html(data.count);
                },error:function () {
                    layer.msg("获取未读公告数失败")
                }});

            //    获取未读邮件数
            $.ajax({url:"${pageContext.request.contextPath}/message/selEmailIsReadNotNumber",dataType:'json'
                ,success:function (data) {
                    $("#emailWaitNumber").html(data.count);
                },error:function () {
                    layer.msg("获取未读邮件数数失败")
                }});

            //    获取提交的本周周报数
            $.ajax({url:"${pageContext.request.contextPath}/emp/selWeeklyNotPush",dataType:'json'
                ,success:function (data) {
                    if (data.count < 1){
                        $("#thisWooklyNumber").html("未完成");
                    }else {
                        $("#thisWooklyNumber").html("已完成");
                    }
                },error:function () {
                    layer.msg("获取未读邮件数数失败")
                }});

            //    获取提交的谈心记录数
            $.ajax({url:"${pageContext.request.contextPath}/emp/selChatRecord",dataType:'json'
                ,success:function (data) {
                    $("#moothCharRecordNumber").html(data.count);
                },error:function () {
                    layer.msg("获取谈心记录数失败")
                }});
        }

    }

    reloadPage();



    // 打开新tab的方法
        function addTab(type) {
            var url = "${pageContext.request.contextPath}/";
            var id = 190;
            var title = "";

            if (type == '员工请假'){
                url = "${pageContext.request.contextPath}/leave/toMyTaskPage";
                id = 190;
                title = "员工请假审批"
            }else if(type == '学生请假'){
                url = "${pageContext.request.contextPath}/leave/toMyTaskStudentPage";
                id = 191;
                title = "学生请假审批"
            }else if(type == '公告'){
                url = "${pageContext.request.contextPath}/message/notice";
                id = 192;
                title = "公告"
            }else if(type == '邮件'){
                url = "${pageContext.request.contextPath}/message/toEmailPage";
                id = 193;
                title = "我的邮件"
            }else if(type == '我的周报'){
                url = "${pageContext.request.contextPath}/emp/toMyWeeklyPage";
                id = 194;
                title = "我的周报"
            }else if(type == '谈心记录'){
                url = "${pageContext.request.contextPath}/emp/chatRecord";
                id = 195;
                title = "谈心记录"
            }

            parent.active.tabAdd(url,id,title);
            parent.active.tabChange(id);
        }
</script>
</html>
