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
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery-1.7.2.min.js"></script><!---引用jquery库-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script><!---引用easyui库-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script><!---引用语言包-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css"></link><!---主题样式-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/easyui/themes/icon.css"></link><!---图标-->
    <!--tip的样式-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/easyui/demo/demo.css"></link>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script language="JavaScript">

    </script>
</head>
<body>

<div style="width: 14%;height: 500px;float: left;">
    <ul class="easyui-tree" id="aa" style="width: 230px">
    </ul>
</div>
<div style="width: 86%;height: 250px;float: left;">
    <table class="layui-hide" id="test"></table>

</div>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</body>
<script>
    $("#aa").tree({
        url:'${pageContext.request.contextPath}/student/StudetnClasstree',//treesql
        onClick:function (node) {//当前点击的节点
            if(node.id!=-1){
                // alert(node.id+""+node.text);//得到当前节点
                var SJ = node.text;
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
                    {field:'id', title: 'ID', sort: true}
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
            elem: '#test'
            ,url:'/demo/table/user/'
            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {field:'id', title: 'ID', sort: true}
                ,{field:'username', title: '用户名'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
                ,{field:'sex', title: '性别', sort: true}
                ,{field:'city', title: '城市'}
                ,{field:'sign', title: '签名'}
                ,{field:'classify', title: '职业', align: 'center'} //单元格内容水平居中
            ]]
        });
    });

</script>
</html>
