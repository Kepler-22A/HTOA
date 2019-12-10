
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/12/9
  Time: 8:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.all.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/layui/lay/modules/layer.js" charset="utf-8"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
</head>
<body>
<table class="layui-hide" id="test" lay-filter="test"></table>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" onclick="add()">添加维修记录</button>
    </div>
</script>
<form  class="layui-form" id="sss" style="display:none" method="post"  action="${pageContext.request.contextPath}/student/addequipment" >
    <div style="width: 56%;height: auto;margin-top: 1%">
        <div style="width:110%;height:100%;float: left">
            <div class="layui-form-item">
                <label class="layui-form-label">设备名称</label>
                <div class="layui-input-block">
                    <input id="equipmentType" type="text" name="equipmentType" required  lay-verify="required" placeholder="请输入设备名称" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-block">
                    <input id="Student" type="text" name="Student" required  lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
                </div>

            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">班级或部门</label>
                <div class="layui-input-block">
                    <input id="Classes" type="text" name="Classes" required  lay-verify="required" placeholder="请输入部门" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">类别</label>
                <div class="layui-input-block">
                    <input type="radio" name="userType" value="1" title="学生">
                    <input type="radio" name="userType" value="2" title="员工" >
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                    <button type="button" class="layui-btn layui-btn-primary" onclick="guanbi()">返回</button>
                </div>
            </div>
        </div>

    </div>
</form>

<script type="text/html" id="barDemo">
    {{# if(d.status == 0){ }}
    <a id="ok" class="layui-btn layui-btn-xs" lay-event="ok" onclick="fulfill('{{ d.equipmentId }}','{{d.status}}')">完成</a>
    <a class="layui-btn layui-btn-xs" onclick="update('{{ d.equipmentId }}')">修改</a>
    {{# }else{ }}
    <a  class="layui-btn layui-btn-xs layui-btn-disabled" >完成</a>
    <a class="layui-btn layui-btn-xs layui-btn-disabled" >修改</a>
    {{# } }}

    <a class="layui-btn layui-btn-danger layui-btn-xs" onclick="delhour('{{ d.equipmentId}}')">删除</a>
</script>

</body>
<script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
    //添加
    function add() {
        $("#sss").attr("action","${pageContext.request.contextPath}/student/addequipment");
        layer.open({
            type: 1,
            title:"新增",
            area:['600px','600px'],
            content: $("#sss"),
            closeBtn :0, //隐藏弹出层的关闭按钮
            yes:function(index,layero){
            }
        });

    }
    //修改
    function  update(equipmentId) {
        $.post("${pageContext.request.contextPath}/student/selEquipmentById",{id : equipmentId},function (d) {
            $("#sss").attr("action","${pageContext.request.contextPath}/student/updateEquiment/" + d.equipment.equipmentId);
            $("#equipmentId").val(d.equipment.equipmentId);
            $("#equipmentType").val(d.equipment.equipmentType);
            $("#Student").val(d.equipment.student);
            $("#Classes").val(d.equipment.classes);
            $("#userType").val(d.equipment.userType);
        },"json");
        layer.open({
            type: 1,
            title:"修改",
            area:['600px','600px'],
            content: $("#sss"),
            closeBtn :0, //隐藏弹出层的关闭按钮
            yes:function(index,layero){
            }
        });
    }

    //完成

    function fulfill(equipmentId,status) {

        $.ajaxSettings.async = false;
        $.post("${pageContext.request.contextPath}/student/finishEquipment/"+equipmentId,{},function (d) {

        },'json');
        $.ajaxSettings.async = true;
        parent.location.reload();
    }
    //删除
    function  delhour(equipmentId) {
        if(confirm("确认删除？")){
            $.post("${pageContext.request.contextPath}/student/delEquipment",{equipmentId:equipmentId},
                function (data) {
                    parent.location.reload();
                });
        }

    }



    function guanbi() {
        url:'${pageContext.request.contextPath}/student/equipmentRepair'
        layer.close(layer.index);
    }
</script>
<script type="text/html"  id="leixing">
    {{# if(d.userType == 1){ }}
    学生
    {{# }else if(d.userType == 2){ }}
    员工
    {{# } }}
</script>

<script type="text/html"  id="zhuangtai">
    {{# if(d.status == 0){ }}
    未完成
    {{# }else if(d.status == 1){ }}
    已完成
    {{# } }}
</script>
<script type="text/javascript">
    /* 时间戳转化开始 */
    Date.prototype.format = function (fmt) { //author: meizz  
        var o = {
            "M+": this.getMonth() + 1, //月份  
            "d+": this.getDate(), //日  
            "h+": this.getHours(), //小时  
            "m+": this.getMinutes(), //分  
            "s+": this.getSeconds(), //秒   
            "q+": Math.floor((this.getMonth() + 3) / 3),    //q是季度
            "S": this.getMilliseconds() //毫秒  
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }

    String.prototype.format = function (args) {
        var result = this;
        if (arguments.length > 0) {
            if (arguments.length == 1 && typeof (args) == "loginTime") {
                for (var key in args) {
                    if (args[key] != undefined) {
                        var reg = new RegExp("({" + key + "})", "g");
                        result = result.replace(reg, args[key]);
                    }
                }
            }
            else {
                for (var i = 0; i < arguments.length; i++) {
                    if (arguments[i] != undefined) {
                        //var reg = new RegExp("({[" + i + "]})", "g");//这个在索引大于9时会有问题  
                        var reg = new RegExp("({)" + i + "(})", "g");
                        result = result.replace(reg, arguments[i]);
                    }
                }
            }
        }
        return result;
    }
    function dateFormat(value) {
        return value ? new Date(value).format("yyyy-MM-dd hh:mm:ss") : "";
    }
</script>
<script type="text/html" id="endTime">
    {{ dateFormat(d.endTime) }}
</script>

<script type="text/javascript">
    layui.use('table', function() {
        var table = layui.table;

        table.render({
            elem: '#test'
            , url: '/student/equipmentRepairdata'
            , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            , defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                , layEvent: 'LAYTABLE_TIPS'
                , icon: 'layui-icon-tips'
            }]
            , title: '设备维修数据表'
            , cols: [[
                {type: 'checkbox',fixed: 'left'}
                , {field: 'equipmentId', title: '编号', width: 80, fixed: 'left', sort: true}
                , {field: 'equipmentType', title: '维修设备名称', width: 150, edit: 'text'}
                , {field: 'student', title: '姓名', width: 80, edit: 'text', sort: true}
                , {field: 'classes', title: '部门或班级', width: 150, edit: 'text', sort: true}
                , {field: 'userType', title: '类别', width: 100,templet:'#leixing'}
                , {field: 'startTime', title: '申请时间',width:180,templet:'<div>{{layui.util.toDateString(d.startTime,"yyyy-MM-dd HH:mm:ss")}}</div>'}
                , {field: 'endTime', title: '完成时间', width: 180,templet:'#endTime'}
                , {field: 'status', title: '状态', width: 100, sort: true,templet:'#zhuangtai'}///!!!!!!!!!!!!!!!!!!!!!!!!
                , {fixed: 'right', title:'操作', toolbar: '#barDemo', width:200}
            ]]
            , page: true
        });
        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：'+ data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选': '未全选');
                    break;

                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
            };
        });
        //Demo
        layui.use('laydate', function(){
            var laydate = layui.laydate;

            //执行一个laydate实例
            laydate.render({
                elem: '#rutime' //指定元素
            });
        });

        layui.use('laydate', function(){
            var laydate = layui.laydate;

            //执行一个laydate实例
            laydate.render({
                elem: '#srtime' //指定元素
            });
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del();
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                layer.prompt({
                    formType: 2
                    ,value: data.email
                }, function(value, index){
                    obj.update({
                        email: value
                    });
                    layer.close(index);
                });
            }
        });
    });


</script>
</html>
