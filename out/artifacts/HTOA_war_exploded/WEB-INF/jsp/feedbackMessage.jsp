<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/12/19
  Time: 8:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>留言板</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/layui/lay/modules/layer.js" charset="utf-8"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/admin.css"  media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/template.css"  media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layer.css"  media="all">

    <script type="text/javascript">

    </script>
</head>
<body layadmin-themealias="default">


<div class="layui-fluid layadmin-message-fluid">
    <div class="layui-row">
        <div class="layui-col-md12">
            <div class="layui-col-md12 layadmin-homepage-list-imgtxt message-content" style="font-size: 20px">
                反馈的问题：
                <c:forEach items="${FeedBack}" var="fed">
                   ${fed.remark}
                </c:forEach>
            </div>
            <form class="layui-form" method="post" action="${pageContext.request.contextPath}/system/addMessage">
                <input id="feedbackIdInput" type="hidden" name="feedbackId" value="">
                <div class="layui-form-item layui-form-text">
                    <div class="layui-input-block">
                        <textarea name="feedbackMsgName" placeholder="请输入对此问题的建以或看法" class="layui-textarea"></textarea>
                    </div>
                </div>

                <div class="layui-form-item" style="overflow: hidden;">
                    <div class="layui-input-block layui-input-right">
                        <button class="layui-btn" lay-submit="" lay-filter="formDemo">发表</button>
                    </div>
                    <div class="layadmin-messag-icon">
                        <a href="javascript:;"><i class="layui-icon layui-icon-face-smile-b"></i></a>
                        <a href="javascript:;"><i class="layui-icon layui-icon-picture"></i></a>
                        <a href="javascript:;"><i class="layui-icon layui-icon-link"></i></a>
                    </div>
                 </div>
            </form>
        </div>
        <div class="layui-col-md12 layadmin-homepage-list-imgtxt message-content">
            <c:forEach items="${Message}" var="meg">
                <div class="media-body">
                    <div class="pad-btm">
                        <p class="fontColor"><a href="javascript:;">${meg.userName}</a></p>
                        <p class="min-font">
                 <span class="layui-breadcrumb" lay-separator="-" style="visibility: visible;">
                    <a href="javascript:;" class="layui-icon layui-icon-cellphone"></a><span lay-separator="">-</span>
<%--                    <a href="javascript:;">从移动</a><span lay-separator="">-</span>--%>
                    <a href="javascript:;">${meg.singDate}</a>
                 </span>
                        </p>
                    </div>
                    <p class="message-text">${meg.feedbackMsgName}</p>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<script>
    <c:forEach items="${FeedBack}" var="fed">
    console.log(${fed.feedbackId});
    feedbackId = ${fed.feedbackId};
    $("#feedbackIdInput").val(feedbackId);
    </c:forEach>
</script>

<style id="LAY_layadmin_theme">
    .layui-side-menu,.layadmin-pagetabs .layui-tab-title li:after,.layadmin-pagetabs .layui-tab-title li.layui-this:after,.layui-layer-admin .layui-layer-title,.layadmin-side-shrink .layui-side-menu .layui-nav>.layui-nav-item>.layui-nav-child{background-color:#20222A !important;}.layui-nav-tree .layui-this,.layui-nav-tree .layui-this>a,.layui-nav-tree .layui-nav-child dd.layui-this,.layui-nav-tree .layui-nav-child dd.layui-this a{background-color:#009688 !important;}.layui-layout-admin .layui-logo{background-color:#20222A !important;}
</style>
</body>
</html>
