<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/12/19
  Time: 8:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>留言板</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/admin.css"  media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/template.css"  media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layer.css"  media="all">
</head>
<body layadmin-themealias="default">


<div class="layui-fluid layadmin-message-fluid">
    <div class="layui-row">
        <div class="layui-col-md12">
            <div class="layui-col-md12 layadmin-homepage-list-imgtxt message-content" style="font-size: 20px">
                反馈的问题：${FeedBack.REMARK}
            </div>
            <form class="layui-form">
                <div class="layui-form-item layui-form-text">
                    <div class="layui-input-block">
                        <textarea name="desc" placeholder="请输入对此问题的建以或看法" class="layui-textarea"></textarea>
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
            <div class="media-body">
                <a href="javascript:;" class="media-left" style="float: left;">
                    <img src="./portrait.png" height="46px" width="46px">
                </a>
                <div class="pad-btm">
                    <p class="fontColor"><a href="javascript:;">胡歌</a></p>
                    <p class="min-font">
              <span class="layui-breadcrumb" lay-separator="-" style="visibility: visible;">
                <a href="javascript:;" class="layui-icon layui-icon-cellphone"></a><span lay-separator="">-</span>
                <a href="javascript:;">从移动</a><span lay-separator="">-</span>
                <a href="javascript:;">11分钟前</a>
              </span>
                    </p>
                </div>
                <p class="message-text">历经打磨，@索尼中国 再献新作品—OLED电视A8F完美诞生。很开心一起参加了A8F的“首映礼”！[鼓掌]正如我们演员对舞台的热爱，索尼对科技与艺术的追求才创造出了让人惊喜的作品。作为A1兄弟款，A8F沿袭了黑科技“屏幕发声技术”和高清画质，色彩的出众表现和高端音质，让人在体验的时候如同身临其境。A8F，这次的“视帝”要颁发给你！  索尼官网预售： O网页链接 索尼旗舰店预售：</p>
            </div>
            <div class="media-body">
                <a href="javascript:;" class="media-left" style="float: left;">
                    <img src="./portrait.png" height="46px" width="46px">
                </a>
                <div class="pad-btm">
                    <p class="fontColor"><a href="javascript:;">胡歌</a></p>
                    <p class="min-font">
              <span class="layui-breadcrumb" lay-separator="-" style="visibility: visible;">
                <a href="javascript:;" class="layui-icon layui-icon-cellphone"></a><span lay-separator="">-</span>
                <a href="javascript:;">从移动</a><span lay-separator="">-</span>
                <a href="javascript:;">11分钟前</a>
              </span>
                    </p>
                </div>
                <p class="message-text">历经打磨，@索尼中国 再献新作品—OLED电视A8F完美诞生。很开心一起参加了A8F的“首映礼”！[鼓掌]正如我们演员对舞台的热爱，索尼对科技与艺术的追求才创造出了让人惊喜的作品。作为A1兄弟款，A8F沿袭了黑科技“屏幕发声技术”和高清画质，色彩的出众表现和高端音质，让人在体验的时候如同身临其境。A8F，这次的“视帝”要颁发给你！  索尼官网预售： O网页链接 索尼旗舰店预售：</p>
            </div>
            <div class="layui-row message-content-btn">
                <a href="javascript:;" class="layui-btn">更多</a>
            </div>
        </div>

    </div>
</div>


<style id="LAY_layadmin_theme">
    .layui-side-menu,.layadmin-pagetabs .layui-tab-title li:after,.layadmin-pagetabs .layui-tab-title li.layui-this:after,.layui-layer-admin .layui-layer-title,.layadmin-side-shrink .layui-side-menu .layui-nav>.layui-nav-item>.layui-nav-child{background-color:#20222A !important;}.layui-nav-tree .layui-this,.layui-nav-tree .layui-this>a,.layui-nav-tree .layui-nav-child dd.layui-this,.layui-nav-tree .layui-nav-child dd.layui-this a{background-color:#009688 !important;}.layui-layout-admin .layui-logo{background-color:#20222A !important;}
</style>
</body>
</html>
