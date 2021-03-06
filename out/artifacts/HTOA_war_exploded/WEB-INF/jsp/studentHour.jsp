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
    <title>宿舍管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/layui/lay/modules/layer.js" charset="utf-8"></script>
    <script>
    </script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>宿舍管理</legend>
</fieldset>
<button onclick="add()" type="button" class="layui-btn layui-btn-normal" style="margin-left:30px"><i class="layui-icon layui-icon-add-1"></i>添加</button>
<div class="layui-form">
    <table id="studentHourTable" class="layui-table" align="center">
    </table>
</div >
<div  id="hourstudent" class="layui-form" style="display: none">
    <table id="Huorstudent" class="layui-table" align="center">
    </table>
</div>
<script>
    layui.use('form', function(){
        var form = layui.form;
        form.render("select");
    });

    layui.use('table', function(){
        var table = layui.table;
        table.render({
            elem: '#studentHourTable'
            ,url:'/student/JSONStudentHourData'
            ,toolbar: '#toolbarDemo'
            ,height:400
            ,cols: [[
                {field:'hourid', width:80, title: '编号', sort: true}
                ,{field:'huoeIddsc', width:110, title: '排编序号', sort: true}
                ,{field:'huorName', width:110, title: '宿舍房号', sort: true}
                ,{field:'floorId', width:110, title: '宿舍楼栋'}
                ,{field:'numberBeds', width:110, title: '床位数', sort: true}
                ,{field:'addr', width:110, title: '宿舍地址'}
                ,{field:'', width:320, title: '操作', templet:"#dus"}
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
<script type="text/javascript">
    //添加
    function add() {
        $("#test").attr("action","${pageContext.request.contextPath}/student/addhour");
        document.getElementById("test").reset();
        $("#hourid").val(0);
        layer.open({
            type: 1,
            title:"新增",
            area:['400px','360px'],
            content: $("#test"),
            closeBtn :0, //隐藏弹出层的关闭按钮
            yes:function(index,layero){
            }
        });

        $.post("/student/selFloor",{},function (data) {
            var form = layui.form;
            var SelectHour_any = "";
            $.each(data,function (index,obj) {
                SelectHour_any += "<option value='" + obj.floorId + "'>" + obj.floorName + "</option>";
            });
            $("#SelectHour").html(SelectHour_any);
            form.render('select');
        },"json");

    }
    //修改
    function  update(hourid) {

        $.post("${pageContext.request.contextPath}/student/selStudnetAddhourById",{id : hourid},function (d) {
            $("#test").attr("action","${pageContext.request.contextPath}/student/updateHour/" + d.studentHour.hourid);
            $("#hourid").val(d.studentHour.hourid);
            $("#huoeIddsc").val(d.studentHour.huoeIddsc);
            $("#floorId").val(d.studentHour.floorId);
            $("#huorName").val(d.studentHour.huorName);
            $("#numberBeds").val(d.studentHour.numberBeds);
            $("#addr").val(d.studentHour.addr);
        },"json");
        layer.open({
            type: 1,
            title:"修改",
            area:['400px','360px'],
            content: $("#test"),
            closeBtn :0, //隐藏弹出层的关闭按钮
            yes:function(index,layero){
            }
        });
    }
    //删除
    function  delhour(hourid) {
        layer.confirm('是否要删除？', {
            icon:3,
            btn: ['确认','取消'] //按钮
        }, function(){
            $.post("${pageContext.request.contextPath}/student/delhour",{hourid:hourid},
                function (data) {
                    $(".layui-laypage-btn")[0].click();  //  局部刷新
                    //parent.location.reload();  //整体刷新
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






    };
</script>
<script type="text/html" id="dus">

    <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" onclick="delhour('{{ d.hourid }}')">
        <i class="layui-icon layui-icon-delete"></i> 删除
    </button>
    &nbsp;&nbsp;<button type="button" class="layui-btn layui-btn-sm layui-btn-normal" onclick="update('{{ d.hourid }}')">
        <i class="layui-icon layui-icon-edit"></i>编辑
    </button>
    &nbsp;&nbsp;<button type="button" class="layui-btn layui-btn-sm layui-btn-normal" onclick="selHuorStudent('{{d.huorName}}')">
        <i class="layui-icon layui-icon-search"></i>查看宿舍学员
    </button>
</script>
<%--弹出层--%>
<form  class="layui-form" id="test" style="display:none"  method="post"  action="${pageContext.request.contextPath}/student/addhour">
    <div class="layui-form-item">
        <div class="layui-input-inline">
            <input id="hourid" type="hidden" name="hourid"  required  lay-verify="required" autocomplete="off"  class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">排编序号</label>
        <div class="layui-input-inline">
            <input id="huoeIddsc" type="text" name="huoeIddsc" required  lay-verify="required" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item" style="margin-top: 20px;">
        <label class="layui-form-label" style="width: 80px;">宿舍楼栋</label>
        <div class="layui-input-inline">
            <select id="SelectHour" name="floorId" style="width: 150px">
            </select>
        </div>
    </div>
   <%-- <div class="layui-form-item">
        <label class="layui-form-label">宿舍楼栋</label>
        <div class="layui-input-block">
            <select  id="floorId" name="floorId" lay-filter="loudong">
                <option value=""></option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
            </select>
        </div>
    </div>--%>
   <%-- <div class="layui-form-item">
        <label class="layui-form-label">宿舍人数</label>
        <div class="layui-input-inline">
            <input id="count" type="text" name="count" required lay-verify="required"autocomplete="off" class="layui-input">
        </div>
    </div>--%>
    <div class="layui-form-item">
        <label class="layui-form-label">宿舍房号</label>
        <div class="layui-input-inline">
            <input  id="huorName" type="text" name="huorName" required lay-verify="required"autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">床位数</label>
        <div class="layui-input-inline">
            <input id="numberBeds" type="text" name="numberBeds" required lay-verify="required"autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">宿舍地址</label>
        <div class="layui-input-inline">
            <input id="addr" type="text" name="addr" required lay-verify="required"autocomplete="off" class="layui-input">
        </div>
    </div>
    <div align="center">
        <input type="submit" value="提交" style="height: 30px;width: 50px;">
        <input type="button" value="取消" style="height: 30px;width: 50px;" onclick="guanbi()">
    </div>
</form>

</body>
<script type="text/javascript">
    //关闭
    function guanbi() {
        // parent.location.reload();
        $("#addfloor").hide(); //jquery方式关闭
        layer.close(layer.index);
        // // 获得frame索引
        // var index = parent.layer.getFrameIndex(window.name);
        // //关闭当前frame
        // parent.layer.close(index);
        // window.parent.location.reload();
        // parent.location.reload();
    }
    //查询宿舍学员
    function selHuorStudent(huorName) {
        layer.open({
            title:'查看宿舍学员',
            type:1,
            content:$('#hourstudent'),
            area: ['600px', '450px'],
            resize:false,
        });
        layui.use('table',function () {
            var table =layui.table;
            table.render({
                elem: "#Huorstudent",
                height: 350,
                url: '${pageContext.request.contextPath}/system/selHuorStudent',
                where: {name:huorName},
                cols: [[
                    {field: 'huorName', width: 150, title: '宿舍房号'}
                    , {field: 'clazz', width: 150, title: '所在班级'}
                    , {field: 'stuname', width: 120, title: '学生名字'}
                    , {field: 'intrphone', width: 175, title: '电话'}
                ]]
                , page: true
            });

        })
    }
</script>
</html>
