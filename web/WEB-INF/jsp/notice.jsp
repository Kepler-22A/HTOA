<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 28401
  Date: 2019/12/17
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>公告发布</title>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.all.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <script>
        //删除
        function deleteEmp(familyId) {

            console.log(familyId);

            layer.confirm('是否要删除？', {
                icon:3,
                btn: ['确认','取消'] //按钮
            }, function(){
                $.post('<%=request.getContextPath()%>/message/delectNoticeOK/' + familyId,{},
                    function (data) {
                        reload();
                    });
                layer.msg('已删除', {
                    icon: 1,
                    time:2000
                });

            }, function(){
                layer.msg('已取消', {
                    icon:0,
                    time: 2000 //20s后自动关闭
                });
            });
        }
    </script>
</head>
<body>
<table class="layui-hide" id="test"></table>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        {{# if(${postName == '教研主任' || postName == '学工主任' || postName == '校长'}){ }}
            <a href="${pageContext.request.contextPath}/message/addNotice"><button class="layui-btn layui-btn-sm">发布公告</button></a>
        {{# } }}
        <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reload()">刷新公告</button>
    </div>
</script>
<script type="text/html" id="barDemo">
    <a href="${pageContext.request.contextPath}/message/selectNoticeOK/{{ d.noticeId }}" class="layui-btn layui-btn-xs">查看</a>
    {{# if(d.empName == '${empName}'){ }}
        <a href="${pageContext.request.contextPath}/message/toUpdateNoticeOK/{{d.noticeId}}" class="layui-btn layui-btn-warm layui-btn-xs">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs"onclick="deleteEmp('{{ d.noticeId }}')">删除</a>
    {{# } }}
</script>
<script type="textml" id="roleTpl">
    {{# if (d.noticeType == 3) { }}
        全体学生
    {{# } else if(d.noticeType == 2) { }}
        全体员工
    {{# } else { }}
        班级发送
    {{# } }}
</script>
<script id="youIsReadSpan" type="text/html">
    {{# if (d.youIsRead == 1) { }}
        已读
    {{# } else if(d.noticeType == 2) { }}
        未读
    {{# } else { }}
        你是发送人
    {{# } }}
</script>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    reload()
    function reload(){
        layui.use('table', function(){
            var table = layui.table;

            table.render({
                elem: '#test'
                ,url:'${pageContext.request.contextPath}/message/selectNotice'
                ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
                ,cols: [[
                    {field:'noticeId', title: 'ID', sort: true}
                    ,{field:'title', title: '标题'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
                    ,{field:'noticeType', title: '类型',templet: '#roleTpl'}
                    ,{field:'content', title: '通知内容'}
                    ,{field:'empName', title: '发布人', align: 'center'} //单元格内容水平居中
                    // ,{field:'noticeTime', title: '发布时间',  align: 'center'} //单元格内容水平居中
                    ,{field: 'noticeTime', title: '发布时间', width:200,templet : '<span>{{layui.util.toDateString(d.noticeTime,"yyyy-MM-dd HH:mm:ss")}}</span>'}
                    ,{field:'aaa', title: '已读人数',sort: true}
                    ,{field:'ccc', title: '未读人数',sort: true}
                    ,{field:'youIsRead', title: '你已读',sort: true,templet:'#youIsReadSpan'}
                    ,{fixed: 'right', title:'操作',width: 200,toolbar: '#barDemo'}
                ]]
            });
        });
    }



    //href="${pageContext.request.contextPath}/message/delectNoticeOK/{{d.noticeId}}"
</script>
</body>
</html>
