<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/12/11
  Time: 8:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>届别设置</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/layui/lay/modules/layer.js" charset="utf-8"></script>
    <script type="text/javascript">
        //添加
        function add() {
            layer.open({
                type: 1,
                title:"新增",
                area:['400px','200px'],
                content: $("#addfloor"),
                closeBtn :1, //显示弹出层的关闭按钮，0是不显示
                cancel: function(index, layero){
                    layer.close(index);
                    $("#addfloor").hide(); //jquery方式关闭
                    return false;
                }
            });

        }
        //修改
        function  update(fallid) {
            $.post("${pageContext.request.contextPath}/system/selectSystemID",{id : fallid},function (d) {
                $("#addfloor").attr("action","${pageContext.request.contextPath}/system/UpdateSystemID/" + d.StudentFallVo.fallid);
                $("#fallid").val(d.StudentFallVo.fallid);
                $("#level").val(d.StudentFallVo.level);
                $("#remark").val(d.StudentFallVo.remark);
            },"json");
            layer.open({
                type: 1,
                title:"修改",
                area:['400px','200px'],
                content: $("#addfloor"),
                closeBtn :1,
                cancel:function(index,layero){
                    layer.close(index);
                    $("#addfloor").hide(); //jquery方式关闭
                    return false;
                }
            });
        }


        //删除
        function  del(fallid) {
            layer.confirm('是否要删除？', {
                icon:3,
                btn: ['确认','取消'] //按钮
            }, function(){
                $.post("${pageContext.request.contextPath}/system/delSystem",{fallid:fallid},
                    function (data) {
                         parent.location.reload();
                    });
                layer.msg('已删除', {
                    icon: 1,
                    time:2000
                });


            }, function(){
                layer.msg('已取消', {
                    icon:0,
                    time: 2000 //20s后自动关闭
                    //btn: ['明白了', '知道了']
                });
            });
        }
    </script>
    <script>
    </script>
</head>
<body>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" onclick="add()">添加</button>
    </div>
</script>
<div class="layui-form">
    <table class="layui-table" align="center" id="Table" lay-filter="Table" ></table>
        </tbody>
        <script>
            layui.use('table', function(){
                var table = layui.table;
                table.render({
                    elem: '#Table'
                    ,url:'/system/data'
                    ,toolbar: '#toolbarDemo'
                    ,height:400
                    ,cols: [[
                        {field:'fallid', width:100, title: '编号', sort: true}
                        ,{field:'level', width:250, title: '届别名称', sort: true}
                        ,{field:'remark', width:250, title: '说明', sort: true}
                        ,{width:180, title: '操作', toolbar:'#dus' }
                    ]]
                    ,page: true
                });
                //头工具栏事件
                table.on('toolbar(Table)', function(obj){
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
            <button type="button"  class="layui-btn layui-btn-sm layui-btn-normal" onclick="update('{{ d.fallid }}')">
                <i class="layui-icon layui-icon-edit"></i>编辑
            </button>
            <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" onclick="del('{{ d.fallid }}')">
                <i class="layui-icon layui-icon-delete"></i> 删除
            </button>
        </script>

</div>
    <form  class="layui-form"id="addfloor"  style="display: none" method="post" action="${pageContext.request.contextPath}/system/addSystem">
        <div class="layui-form-item">
            <label class="layui-form-label">届别名称:</label>
            <div class="layui-input-inline">
                <input id="level" type="text" name="level" required  lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">说明:</label>
            <div class="layui-input-inline">
                <input id="remark" type="text" name="remark" required  lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div align="center">
            <input type="submit" value="提交" style="height: 30px;width: 50px;">
        </div>
    </form>
</body>
</html>
