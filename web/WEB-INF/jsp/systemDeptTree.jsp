<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/12/14
  Time: 9:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/layui/lay/modules/layer.js" charset="utf-8"></script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>宿舍管理</legend>
</fieldset>
    <button onclick="add()" type="button" class="layui-btn layui-btn-normal" style="margin: 0px 20px">
        <i class="layui-icon layui-icon-add-1"></i> 添加
    </button>

<%--<button type="button" class="layui-btn  layui-btn-normal" onclick="del('{{ d.depid }}')">--%>
<%--    <i class="layui-icon layui-icon-delete"></i> 删除--%>
<%--</button>--%>

<hr>

<div id="container" style="padding: 0px 50px ;">

</div>
<script>
    function reloadList(){
        $.post("${pageContext.request.contextPath}/system/treedata",{},function (data) {
            var listNode = "";
            $.each(data.list,function (index,obj) {
                console.log(obj);
                listNode += "<div><a href='#'>"+obj.depName+"</a></div>";
            });
            console.log(listNode);
            $("#container").html(listNode);
        },'json');
    }
    reloadList();

    //添加
    function add() {
        document.getElementById("addfloor").reset();  //清空表单
        layer.open({
            type: 1,
            title: "新增",
            area: ['400px', '300px'],
            content: $("#addfloor"),
            closeBtn: 1, //显示弹出层的关闭按钮，0是不显示
            cancel: function (index, layero) {
                layer.close(index);
                $("#addfloor").hide(); //jquery方式关闭
                return false;
            }
        });
    }

    //删除
    function  del(depid) {
        layer.confirm('是否要删除？', {
            icon:3,
            btn: ['确认','取消'] //按钮
        }, function(){
            $.post("${pageContext.request.contextPath}/system/delDept",{depid:depid},
                function (data) {
                    $(".layui-laypage-btn")[0].click();
                    // layer.closeAll();
                });
            layer.msg('已删除', {
                icon: 1,
                time:2000
            });

        }, function(){
            layer.msg('已取消', {
                icon:0,
                time: 2000//20s后自动关闭
                //btn: ['明白了', '知道了']
            });
        });
    }
</script>

<form  class="layui-form" id="addfloor" style="display: none" method="post" action="${pageContext.request.contextPath}/system/addDep">
    <div class="layui-form-item" style="margin-top: 20px;">
        <label class="layui-form-label" style="width: 100px;">部门名称:</label>
        <div class="layui-input-inline">
            <input id="depName" type="text" name="depName" required  lay-verify="required" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 20px;">
        <label class="layui-form-label" style="width: 100px;">说明:</label>
        <div class="layui-input-inline">
            <input id="remark" type="text" name="remark" required  lay-verify="required" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div align="center">
        <input type="submit" value="提交" style="height: 30px;width: 50px;">
    </div>
</form>

</body>
<script type="text/html" id="dus">


</script>
</html>
