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
    <meta charset="utf-8">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <script src="${pageContext.request.contextPath}/layui/lay/modules/layer.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery-1.7.2.min.js"></script><!---引用jquery库-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script><!---引用easyui库-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script><!---引用语言包-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css"></link><!---主题样式-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/easyui/themes/icon.css"></link><!---图标-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>部门管理</legend>
</fieldset>
<%--    <button onclick="add()" type="button" class="layui-btn layui-btn-normal" style="margin: 0px 20px">--%>
<%--        <i class="layui-icon layui-icon-add-1"></i> 添加--%>
<%--    </button>--%>

<%--<button type="button" class="layui-btn  layui-btn-normal" onclick="del('{{ d.depid }}')">--%>
<%--    <i class="layui-icon layui-icon-delete"></i> 删除--%>
<%--</button>--%>

<hr>



<div style="width: 14%;height: 300px;float: left;padding: 20px">
    <ul class="easyui-tree" id="aa" style="width: 230px">
    </ul>
</div>

<div style="width: 86%;float: left;">
    <table class="layui-hide" id="test"></table>

</div >
<%--<div id="container" style="padding: 0px 50px ;">--%>

<%--</div>--%>

<form  class="layui-form" id="addfloor" style="display: none" method="post" action="${pageContext.request.contextPath}/system/addDep">
    <div class="layui-form-item" style="margin-top: 20px;">
        <label class="layui-form-label" style="width: 100px;">部门名称:</label>
        <div class="layui-input-inline">
            <input id="depName" type="text" name="depName" required  lay-verify="required" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 20px;">
        <label class="layui-form-label" style="width: 100px;">部门负责人:</label>
        <div class="layui-input-inline">
            <input id="chairman" type="text" name="chairman" required  lay-verify="required" autocomplete="off" class="layui-input">
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

<p style="clear: both;"></p>
<%--    附表部分    --%>
<div >
    <div class="layui-tab">
        <%--                附表标题！！--%>
        <ul class="layui-tab-title ">
            <li class="layui-this">部门管理</li>
        </ul>
            <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" style="margin: 20px 100px;" onclick="add()">
                <i class="layui-icon layui-icon-add-1"></i> 添加
            </button>
            <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" onclick="del(SJ)">
                <i class="layui-icon layui-icon-delete"></i> 删除
            </button>
        <%-- 附表！！--%>
        <div class="layui-tab-content">
            <form  class="layui-form layui-show"  id="fubiao">
                <input type="hidden" name="depid" id="depid" value="{{d.depid}}">
                <div class="layui-form-item" style="margin-top: 20px;">
                    <label class="layui-form-label" style="width: 100px;">部门名称:</label>
                    <div class="layui-input-inline">
                        <input id="dName"  type="text" name="dName" required  lay-verify="required" autocomplete="off" class="layui-input">
                    </div>
                    <label class="layui-form-label" style="width: 100px;">上级部门名称:</label>
                    <div class="layui-input-inline">
                        <input   id="parebtId"type="text" name="dName" required  lay-verify="required" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item" style="margin-top: 20px;">
                    <label class="layui-form-label" style="width: 100px;">部门负责人:</label>
                    <div class="layui-input-inline">
                        <input id="fzr" type="text" name="chairman" required  lay-verify="required" autocomplete="off" class="layui-input">
                    </div>
                    <label class="layui-form-label" style="width: 100px;">部门类别：</label>
                    <div class="layui-input-inline">
                        <select id="depSelect" name="depID" style="width: 150px">
                        </select>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script>

    $("#aa").tree({
        url:'${pageContext.request.contextPath}/system/systemDeptree',//treesql
        onClick:function (node) {//当前点击的节点

            if(node.id!=-1){
                document.SJ = node.id;
                // alert(node.id+""+node.text);//得到当前节点
                $.post("${pageContext.request.contextPath}/system/selectDepTree/"+ document.SJ,function (d) {
                    $("#depid").val(document.SJ);

                    $("#dName").val(d.Dep.depName);
                    $("#fzr").val(d.Dep.chairman);
                },'json');
                // alert("dangfaxiam")
            }
        }
    });

        $.post("/system/selDep",{},function (data) {
            var form = layui.form;
            var depSelect_any = "";
            $.each(data,function (index,obj) {
                depSelect_any += "<option value='" + obj.depID + "'>" + obj.depName + "</option>";
            });
            $("#depSelect").html(depSelect_any);
            form.render('select');
        },"json");


    /*   function reloadList(){
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
    reloadList();*/

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
            $.post("${pageContext.request.contextPath}/system/delTree",{depid:depid},
                function (data) {
                    // $(".layui-laypage-btn")[0].click();
                    $(window.top.document).find("#aa").tree('reload');  //刷新树
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
</html>
