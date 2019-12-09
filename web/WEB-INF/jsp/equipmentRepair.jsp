<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/12/9
  Time: 8:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/layui/lay/modules/layer.js" charset="utf-8"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
</head>
<script type="text/javascript">
    //添加
    function add() {
        $("#sss").attr("action","${pageContext.request.contextPath}/student/studentadd");
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
    function  update(hourid) {
        $.post("${pageContext.request.contextPath}/student/selectStudentID",{id : hourid},function (d) {
            $("#sss").attr("action","${pageContext.request.contextPath}/student/UpdateStudentID/" + d.StudentVo.Studid);
            $("#Studid").val(d.StudentVo.Studid);
            $("#q").val(d.StudentVo.stuname);
            $("#w").val(d.StudentVo.password);equipmentRepair
            $("#e").val(d.StudentVo.age);
            $("#r").val(d.StudentVo.phone);
            $("#t").val(d.StudentVo.addr);
            $("#y").val(d.StudentVo.nation);
            $("#u").val(d.StudentVo.natives);
            $("#o").val(d.StudentVo.cardid);
            $("#p").val(d.StudentVo.professional);
            $("#d").val(d.StudentVo.parents);
            $("#f").val(d.StudentVo.parentsphone);
            $("#g").val(d.StudentVo.intrphone);
            $("#h").val(d.StudentVo.audition);
            $("#j").val(d.StudentVo.remark);
            $("#l").val(d.StudentVo.vocationalsch);
            $("#x").val(d.StudentVo.middleschool);
            $("#v").val(d.StudentVo.introduretech);

        },"json");
        layer.open({
            type: 1,
            title:"修改",
            area:['1200px','700px'],
            content: $("#sss"),
            closeBtn :0, //隐藏弹出层的关闭按钮
            yes:function(index,layero){
            }
        });
    }
    //删除
    function  delhour(Studid) {
        if(confirm("确认删除？")){
            $.post("${pageContext.request.contextPath}/student/delstudent/"+Studid,
                function (data) {
                    parent.location.reload();
                });
        }

    }


    function guanbi() {
        url:'${pageContext.request.contextPath}/student/studentdata'
        parent.location.reload();
    }
</script>
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
                        <input id="Classes" type="text" name="Student" required  lay-verify="required" placeholder="请输入部门" autocomplete="off" class="layui-input">
                    </div>
            </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">类别</label>
                    <div class="layui-input-block">
                        <input type="radio" name="userType" value="学生" title="学生">
                        <input type="radio" name="userType" value="员工" title="员工" checked>
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
            <a class="layui-btn layui-btn-xs" onclick="update('{{ d.studid }}')">修改</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" onclick="delhour('{{ d.studid }}')">删除</a>
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
                    {type: 'checkbox'}
                    , {field: 'equipmentId', title: '编号', width: 80, fixed: 'left', unresize: true, sort: true}
                    , {field: 'equipmentType', title: '维修设备名称', width: 150, edit: 'text'}
                    , {field: 'Student', title: '姓名', width: 80, edit: 'text', sort: true}
                    , {field: 'Classes', title: '部门或班级', width: 150, edit: 'text', sort: true}
                    , {field: 'userType', title: '类别', width: 100}
                    , {field: 'startTime', title: '申请时间',width:150}
                    , {field: 'endTime', title: '完成时间', width: 150}//!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    , {field: 'status', title: '状态', width: 100, sort: true}///!!!!!!!!!!!!!!!!!!!!!!!!
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
</body>
</html>
