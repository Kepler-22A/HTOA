<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/12/4
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>楼栋管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <script type="text/javascript">
        //添加
        function add() {
            layer.open({
                type: 1,
                title:"新增",
                area:['400px','160px'],
                content: $("#addfloor"),
                closeBtn :0, //隐藏弹出层的关闭按钮
                yes:function(index,layero){
                }
            });

        }


        //删除
        function  delfloor(floorId) {
            if(confirm("确认删除？")){
                $.post("${pageContext.request.contextPath}/student/delFloor",{floorId:floorId},

                    function (data) {

                    });
                $(".layui-laypage-btn")[0].click();
                // parent.location.reload();
            }

        }
        //关闭
        function guanbi() {
            url:'${pageContext.request.contextPath}/student/studentFloor'
            layer.close(layer.index);
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>楼栋管理</legend>
</fieldset>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="add()" type="button" class="layui-btn layui-btn-normal"><i class="layui-icon layui-icon-add-1"></i> 添加</button>

<div class="layui-form">
    <table class="layui-table" align="center" id="studentFloorTable">
        </tbody>
        <script>
            layui.use('table', function(){
                var table = layui.table;
                table.render({
                    elem: '#studentFloorTable'
                    ,url:'/student/JSONStudentFloorData'
                    ,toolbar: '#toolbarDemo'
                    ,height:400
                    ,cols: [[
                        {field:'floorId', width:100, title: '编号', sort: true}
                        ,{field:'floorName', width:110, title: '楼栋名称', sort: true}
                        ,{field:'', width:150, title: '操作', templet:"#dus"}
                    ]]
                    ,page: true
                });
                //头工具栏事件
                table.on('toolbar(test)', function(obj){
                    var checkStatus = table.checkStatus(obj.config.id); //获取选中行状态
                    switch(obj.event){
                        case 'getCheckData':
                            var data = checkStatus.data;  //获取选中行数据
                            layer.alert(JSON.stringify(data));
                            break;
                    };
                });
            });
        </script>
        <script type="text/html" id="dus">
            <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" onclick="delfloor('{{ d.floorId }}')">
                <i class="layui-icon layui-icon-delete"></i> 删除
            </button>
        </script>
    </table>
</div>
<form  class="layui-form" id="addfloor" style="display:none" method="post"  action="${pageContext.request.contextPath}/student/addfloor">
    <div class="layui-form-item">
        <label class="layui-form-label">楼栋名称:</label>
        <div class="layui-input-inline">
            <input id="floorName" type="text" name="floorName" required  lay-verify="required" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div align="center">
        <input type="submit" value="提交" style="height: 30px;width: 50px;">
        <input type="button" value="取消" style="height: 30px;width: 50px;" onclick="guanbi()">
    </div>
</form>
</body>
</html>
