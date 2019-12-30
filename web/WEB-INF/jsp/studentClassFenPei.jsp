<%--
  Created by IntelliJ IDEA.
  User: 28401
  Date: 2019/12/11
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>班级分配</title>
    <meta charset="utf-8">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery-1.7.2.min.js"></script><!---引用jquery库-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script><!---引用easyui库-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script><!---引用语言包-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css"></link><!---主题样式-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/easyui/themes/icon.css"></link><!---图标-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script language="JavaScript">

    </script>
</head>
<body>

<div style="width: 14%;height: 500px;float: left;">
    <ul class="easyui-tree" id="aa" style="width: 230px">
    </ul>
</div>
<div style="width: 86%;float: left;">
    <h1>班级学生列表</h1>

</div >
<div style="width: 86%;float: left;">
    <table class="layui-hide" id="test"></table>

</div >
<div style="width: 86%;float: left;">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <div class="layui-inline"style="margin-left: 0px">
                <legend style="float: left;display: block;  padding: 9px 10px;    font-weight: 400;   line-height: 20px;  text-align: right">学生姓名</legend>
                <div class="layui-input-inline">
                    <input type="tel" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 0px">
                <legend style="float: left;display: block;  padding: 9px 10px;    font-weight: 400;   line-height: 20px;  text-align: right">手机号码</legend>
                <div class="layui-input-inline">
                    <input type="text" name="email" lay-verify="email" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 0px">
                <legend style="float: left;display: block;  padding: 9px 10px;    font-weight: 400;   line-height: 20px;  text-align: right">班级类型</legend>
                <div class="layui-input-inline">
                    <select name="classTeacher" lay-verify="required" id="classTeacher">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>
            <l class="layui-icon"style="cursor:pointer;font-size: 20px;">&#xe615;</l>
        </div>

    </form>
</div>
<div style="width: 86%;float: left;">
    <table class="layui-hide" id="studentData" lay-filter="test"></table>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
            <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
            <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
        </div>
    </script>
</div >
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</body>
<script>
    // jQuery.noConflict();
    layui.use('form', function(){
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function(data){
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });
    $("#aa").tree({
        url:'${pageContext.request.contextPath}/student/StudetnClasstree',//treesql
        onClick:function (node) {//当前点击的节点
            if(node.id!=-1){
                // alert(node.id+""+node.text);//得到当前节点
                var SJ = node.id;
                sks("${pageContext.request.contextPath}/student/selectClassTree/"+SJ);//实现无缝天空一号观月计划无线对接数据 A
            }
        }
    });

    function sks(url){
        layui.use('table', function(){
            var table = layui.table;

            table.render({
                elem: '#test'
                ,url:url
                ,cellMinWidth: 100 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                ,cols: [[
                    {field:'Studid', title: 'ID', }
                    ,{field:'className', title: '班级昵称'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
                    ,{field:'stuname', title: '学生姓名'}
                    ,{field:'sex', title: '性别'}
                    ,{field:'phone', title: '电话号码'}
                ]]
            });
        });
    };

    layui.use('table', function(){
        var table = layui.table;
        table.render({
            elem: '#studentData'
            ,url:'${pageContext.request.contextPath}/student/selectNOClassStudentData'
            ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            ,cellMinWidth: 100 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'enrollmentid', title: 'ID', }
                ,{field:'studName', title: '学生姓名'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
                ,{field:'card', title: '身份证'}
                ,{field:'sex', title: '性别'}
                ,{field:'tell', title: '电话号码'}
                ,{field:'school', title: '学校'}
                ,{field:'classes', title: '班级'}
                ,{field:'empName', title: '招生老师'}
                ,{field:'classTypeName', title: '班级类别'}
            ]]
        });
    });

</script>
</html>
