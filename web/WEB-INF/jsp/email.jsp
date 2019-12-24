<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/22
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>邮件页面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
</head>
<body>

<div class="layui-tab">
    <%--                附表标题！！--%>
    <ul class="layui-tab-title">
        <li class="layui-this">收到的邮件</li>
        <li>发送的邮件</li>
    </ul>
    <%-- 附表！！--%>
    <div class="layui-tab-content">
<%--        /*************************************************************收到的邮件*************************************************************/--%>
        <div class="layui-tab-item layui-show">
            <div id="getEmail"></div>

            <%--    收到的邮件表表头工具    --%>
            <script type="text/html" id="getEmailToolbarDemo">
                <div class="layui-btn-container">
                    <a class="layui-btn layui-btn-sm" href="${pageContext.request.contextPath}/message/toAddEmailPage">写邮件</a>
                    <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reloadGetEmailTable()">刷新表格</button>
                </div>
            </script>

            <%--    每行收到的邮件表的操作按钮    --%>
            <script type="text/html" id="getEmailBarDemo">
                <a class="layui-btn layui-btn-xs" href="${pageContext.request.contextPath}/message/lookEmail/{{d.emailId}}" lay-event="edit">浏览</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" href="javascript:deleteGetEmail('{{d.emailId}}',${empId})">删除</a>
            </script>
        </div>

    <%--        /*************************************************************发送的邮件*************************************************************/--%>
        <div class="layui-tab-item">
            <div id="forEmail"></div>

            <%--    发送的邮件表表头工具    --%>
            <script type="text/html" id="forEmailToolbarDemo">
                <div class="layui-btn-container">
                    <a class="layui-btn layui-btn-sm" href="${pageContext.request.contextPath}/message/toAddEmailPage">写邮件</a>
                    <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reloadForEmailTable()">刷新表格</button>
                </div>
            </script>

            <%--    每行发送的邮件表的操作按钮    --%>
            <script type="text/html" id="forEmailBarDemo">
                <a class="layui-btn layui-btn-xs" href="${pageContext.request.contextPath}/message/lookEmail/{{d.emailId}}" lay-event="edit">浏览</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" href="javascript:deleteForEmail('{{d.emailId}}',${empId})">删除</a>
            </script>
        </div>
    </div>
</div>
</body>
<script>
    layui.use('element',function() {
        var $ = layui.jquery
            ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
        function FrameWH() {
            var h = $(window).height() -41- 10 - 60 -10-44 -10;
            $("iframe").css("height",h+"px");
        }
    });

    /****************************收到的邮件***************************/

    function reloadGetEmailTable(){
        layui.use('table', function(){
            var table = layui.table;

            //第一个实例
            table.render({
                elem: '#getEmail'
                ,height: 523
                ,url: '/message/getEmailData' //数据接口
                ,toolbar: '#getEmailToolbarDemo' //开启头部工具栏，并为其绑定左侧模板
                ,page: true //开启分页
                ,cols: [[ //表头
                    {field: 'emailId', title: '编号', width:80, sort: true, fixed: 'left'}
                    ,{field: 'empName', title: '发送人', width:80}
                    ,{field: 'topic', title: '标题', width:250}
                    ,{field: 'sendtime', title: '接收时间', width:180,templet : '<span>{{layui.util.toDateString(d.sendtime,"yyyy-MM-dd HH:mm:ss")}}</span>'}
                    ,{field: 'isRead', title: '是否已读', width: 180,templet:'<span>{{isReadToString(d.isRead)}}</span>'}
                    ,{field: '', title: '操作', width: 115 ,toolbar:'#getEmailBarDemo'}
                ]]
            });

            // //监听行单击事件
            // table.on('row(test)', function(obj){
            //     console.log(obj.tr) //得到当前行元素对象
            //     console.log(obj.data) //得到当前行数据
            //     reloadSecondTable(obj.data.empId,obj.data.empName);
            // });

        });
    };

    reloadGetEmailTable();

    function deleteGetEmail(emailId,receId) {
        $.ajax({
            url:"${pageContext.request.contextPath}/message/getEmailDelete/" + emailId + "/" + receId
            ,data:{}
            ,success:function () {
                layer.msg("删除成功！");
                reloadGetEmailTable();
            },
            error:function () {
                layer.msg("删除出错！");
            }
        });
    }

    function isReadToString(data){
        if(data == 1){
            return "已读"
        }else if (data == 2) {
            return "未读";
        }else {
            return "????";
        }
    }


    /****************************发送的邮件***************************/

    function reloadForEmailTable(){
        layui.use('table', function(){
            var table = layui.table;

            //第一个实例
            table.render({
                elem: '#forEmail'
                ,height: 523
                ,url: '/message/forEmailData' //数据接口
                ,toolbar: '#forEmailToolbarDemo' //开启头部工具栏，并为其绑定左侧模板
                ,page: true //开启分页
                ,cols: [[ //表头
                    {field: 'emailId', title: '编号', width:80, sort: true, fixed: 'left'}
                    ,{field: 'receName', title: '接收人', width:80}
                    ,{field: 'topic', title: '标题', width:250}
                    ,{field: 'sendtime', title: '发送时间', width:180,templet : '<span>{{layui.util.toDateString(d.sendtime,"yyyy-MM-dd HH:mm:ss")}}</span>'}
                    ,{field: 'isRead', title: '是否已读', width: 180,templet: '<span>{{isReadToString(d.isRead)}}</span>'}
                    ,{field: '', title: '操作', width: 115 ,toolbar:'#forEmailBarDemo'}
                ]]
            });

            // //监听行单击事件
            // table.on('row(test)', function(obj){
            //     console.log(obj.tr) //得到当前行元素对象
            //     console.log(obj.data) //得到当前行数据
            //     reloadSecondTable(obj.data.empId,obj.data.empName);
            // });

        });
    };

    reloadForEmailTable();

    function deleteForEmail(emailId,empId) {
        $.ajax({
            url:"${pageContext.request.contextPath}/message/forEmailDelete/" + emailId + "/" + empId
            ,data:{}
            ,success:function () {
                layer.msg("删除成功！");
                reloadForEmailTable();
            },
            error:function () {
                layer.msg("删除出错！");
            }
        });
    }
</script>
</html>
