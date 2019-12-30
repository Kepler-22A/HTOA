<%--
  Created by IntelliJ IDEA.
  User: 28401
  Date: 2019/12/11
  Time: 8:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生班级管理页面</title>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <script type="text/javascript">
        //添加
        function add() {
            $("#sss").attr("action","${pageContext.request.contextPath}/student/studentClassAdd");
            layer.open({
                type: 1,
                title:"新增",
                area:['70%','70%'],
                content: $("#sss"),
                closeBtn :0, //隐藏弹出层的关闭按钮
                yes:function(index,layero){
                }
            });
        }
        function guanbi() {
            layer.closeAll();
        }
        function  update(hourid) {
            $.post("${pageContext.request.contextPath}/student/selectStudentClassID",{id : hourid},function (d) {//去后台查询数据
                $("#sss").attr("action","${pageContext.request.contextPath}/student/UpdateStudentClassID/" + d.StudentVo.classid);
                $("#classid").val(d.StudentVo.classid);
                $("#a").val(d.StudentVo.classno);
                $("#b").val(d.StudentVo.className);
                $("#count").val(50);

            },"json");
            layer.open({
                type: 1,
                title:"修改",
                area:['70%','70%'],
                content: $("#sss"),
                closeBtn :0, //隐藏弹出层的关闭按钮
                yes:function(index,layero){
                }
            });
        }
        //删除
        function  delhourstudent(classid) {
            if(confirm("确认删除？")){
                $.post("${pageContext.request.contextPath}/student/delstudentClassID/"+classid,
                    function (data) {
                        parent.location.reload();
                    });
            }

        }
    </script>
</head>
<body>
<table class="layui-hide" id="test" lay-filter="test"></table>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
        <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
        <button class="layui-btn layui-btn-sm" onclick="add()">添加班级</button>
    </div>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" onclick="update('{{ d.classid }}')">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" onclick="delhourstudent('{{ d.classid }}')">删除</a>
</script>
<form  class="layui-form" id="sss" style="display:none" method="post"  action="${pageContext.request.contextPath}/student/studentClassAdd">
    <input type="hidden" name="count" value="50" id="count"/><!--班级人数-->
    <div style="width: 56%;height: auto;margin-top: 1%">
        <div style="width:50%;height:100%;float: left">
            <div class="layui-form-item">
                <label class="layui-form-label">班级编号</label>
                <div class="layui-input-block">
                    <input id="a" type="text" name="classno" required  lay-verify="required" placeholder="请取个班级班号" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">班级名称</label>
                <div class="layui-input-block">
                    <input id="b" type="text" name="className" required  lay-verify="required" placeholder="请输入班级昵称" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">授课老师</label>
                <div class="layui-input-block">
                    <select name="teacher" lay-verify="required" id="teacher">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">班主任</label>
                <div class="layui-input-block">
                    <select name="classTeacher" lay-verify="required" id="classTeacher">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea name="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
                </div>
            </div>
        </div>
    </div>
    <div style="width: 50%;height: 100%;float: left">
        <div class="layui-form-item">
            <label class="layui-form-label">班级分类</label>
            <div class="layui-input-block">
                <select name="classType" lay-verify="required" id="classType">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">系名称</label>
            <div class="layui-input-block">
                <select name="deptId" lay-verify="required" id="deptId" lay-filter="depId">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">专业名称</label>
            <div class="layui-input-block">
                <select name="majorId" lay-verify="required" id="majorId">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">届别</label>
            <div class="layui-input-block">
                <select name="falled" lay-verify="required" id="falled">
                    <option value="">请选择</option>
                    <option value="2017">2017</option>
                    <option value="2018">2018</option>
                    <option value="2019">2019</option>
                    <option value="2020">2020</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="button" class="layui-btn layui-btn-primary" onclick="guanbi()">返回</button>
            </div>
        </div>
    </div>
</form>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test'
            ,url:'${pageContext.request.contextPath}/student/selectstudentClass'
            ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            ,title: '用户数据表'
            ,cols: [[
                {type: 'checkbox', fixed: 'left', width:80}
                ,{field:'classid', title:'序号', width:80, fixed: 'left', unresize: true, sort: true}
                ,{field:'classno', title:'班级编号', width:120, edit: 'text'}
                ,{field:'className', title:'班级昵称', width:150, edit: 'text', sort: true}
                ,{field:'js', title:'授课老师', width:100,edit: 'text'}
                ,{field:'bzt', title:'班主任',width:100,edit: 'text'}
                ,{field:'classTypeName', title:'班级类别', width:80, sort: true,edit: 'text'}
                ,{field:'falled', title:'届别', width:120,edit: 'text'}
                ,{field:'deptName', title:'系名称', width:150, sort: true,edit: 'text'}
                ,{field:'MajorName', title:'专业名称', width:150,edit: 'text'}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
            ]]
            ,page: true
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
        layui.use('form', function(){
            var form = layui.form;
            //监听提交
            form.on('submit(formDemo)', function(data){
                layer.msg("成功添加");
                return true;
            });
        });
    });

    $(document).ready(function () {
        //查询出老师
        $.post("${pageContext.request.contextPath}/student/teacherNameAjax",{},function (data) {
            var t = $("#teacher").html();
            $.each(data,function (index,obj) {
                $.each(obj,function (i,o) {
                    t += "<option value='"+o.empId+"'>"+o.empName+"</option>";
                })
            });
            $("#teacher").html(t);
            layui.use(['form'], function(){
                var form = layui.form;
                form.render('select');
            });
        },'json');
        ///查询出班主任
        $.post("${pageContext.request.contextPath}/student/ClassTeacherNameAjax",{},function (data) {
            var t = $("#classTeacher").html();
            $.each(data,function (index,obj) {
                $.each(obj,function (i,o) {
                    t += "<option value='"+o.empId+"'>"+o.empName+"</option>";
                })
            });
            $("#classTeacher").html(t);
            layui.use(['form'], function(){
                var form = layui.form;
                form.render('select');
            });
        },'json');
        //查询出班级分类
        $.post("${pageContext.request.contextPath}/student/ClassTypeAjax",{},function (data) {
            var t = $("#classType").html();
            $.each(data,function (index,obj) {
                $.each(obj,function (i,o) {
                    t += "<option value='"+o.calssTypeId+"'>"+o.classTypeName+"</option>";
                })
            });
            $("#classType").html(t);
            layui.use(['form'], function(){
                var form = layui.form;
                form.render('select');
            });
        },'json');
        //查询出系名称
        $.post("${pageContext.request.contextPath}/student/selectdeptId",{},function (data) {
            var t = $("#deptId").html();
            $.each(data,function (index,obj) {
                $.each(obj,function (i,o) {
                    t += "<option value='"+o.deptID+"'>"+o.deptName+"</option>";
                })
            });
            $("#deptId").html(t);
            layui.use(['form'], function(){
                var form = layui.form;
                form.render('select');
            });
        },'json');
        //查询出专业名称
        $.post("${pageContext.request.contextPath}/student/selectmajorId",{},function (data) {
            var t = $("#majorId").html();
            $.each(data,function (index,obj) {
                $.each(obj,function (i,o) {
                    t += "<option value='"+o.majorID+"'>"+o.majorName+"</option>";
                })
            });
            $("#majorId").html(t);
            layui.use(['form'], function(){
                var form = layui.form;
                form.render('select');
            });
        },'json');
    });
</script>

</body>
</html>
