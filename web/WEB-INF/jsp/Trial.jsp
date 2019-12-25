<%--
  Created by IntelliJ IDEA.
  User: 28401
  Date: 2019/12/24
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>试讲培训</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <meta charset="utf-8">
    <script src="${pageContext.request.contextPath}/layui/layui.all.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script type="text/javascript">
        //添加
        function add() {
            $("#secondTable1Form").attr("action","${pageContext.request.contextPath}/student/AddTrial");
            layer.open({
                type: 1,
                title:"新增",
                area:['50%','50%'],
                content: $("#secondTable1Form"),
                closeBtn :0, //隐藏弹出层的关闭按钮
                yes:function(index,layero){
                }
            });
        }
        //修改
        function  update(hourid) {
            $.post("${pageContext.request.contextPath}/student/selectAddTrialID/"+hourid,function (d) {
                $("#secondTable1Form").attr("action","${pageContext.request.contextPath}/student/updateTrialID");
                $("#trialID").val(d.StudentVo.trialID);
                $("#time").val(d.StudentVo.time);
                $("#classTeacher").val(d.StudentVo.courseName);
                $("#teacher").val(d.StudentVo.empName);
                document.getElementById("remark").innerHTML = d.StudentVo.remark;
                var laydate = layui.laydate;
                laydate.render({
                    elem: '#rutime'
                    ,value: layui.util.toDateString(d.StudentVo.date,"yyyy-MM-dd HH:mm:ss")
                    ,isInitValue: true
                });

            },"json");
            layer.open({
                type: 1,
                title:"修改",
                area:['50%','50%'],
                content: $("#secondTable1Form"),
                closeBtn :0, //隐藏弹出层的关闭按钮
                yes:function(index,layero){
                }
            });
        }
        //删除记录
        function deleteWorkExprience(jobId) {
            layer.confirm('是否要删除？', {
                icon:3,
                btn: ['确认','取消'] //按钮
            }, function(){+
                $.post('<%=request.getContextPath()%>/student/deleteTrialID/' + jobId,{},
                    function (data) {
                        reload();
                    });
                layer.msg('已删除', {
                    icon: 1,
                    time:2000
                });

            }, function(){
                layer.msg('已取消', {
                    icon:0,
                    time: 2000 //20s后自动关闭
                });
            });
        }
        function guanbi() {
            layer.closeAll();
            $("#trialID").val(0)
            $("#time").val("")
            $("#remark").val("")
        }
    </script>
</head>
<body>
<div style="width: 1365px;margin: 0 auto">
    <table id="demo" lay-filter="test" class="layui-hide"></table>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" onclick="add()">添加试讲</button>
            <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reload()">刷新表格</button>
        </div>
    </script>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" href="javascript:update('{{ d.trialID }}')" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" onclick="deleteWorkExprience('{{ d.trialID }}')">删除</a>
    </script>
    <form id="secondTable1Form" action="" method="post" style="display: none" class="layui-form">
        <input type="hidden" value="0" id="trialID" name="trialID">
        <div class="layui-form-item">
            <label class="layui-form-label">日期</label>
            <div class="layui-input-block">
                <input type="entertime" name="shijian2" required  lay-verify="required" placeholder="单击此处选择日期" id="rutime" autocomplete="off" class="layui-input"style="width:190px">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">时间</label>
            <div class="layui-input-inline">
                <input id="time" type="text" name="time" lay-verify="ST1Remark" autocomplete="off" placeholder="请输入时间" class="layui-input" onclick="QQT(this)">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">课程/章节</label>
            <div class="layui-input-inline">
                <select name="courseID" lay-verify="required" id="classTeacher">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">授课类型</label>
            <div class="layui-input-inline">
                <select name="type" lay-verify="required" id="type">
                    <option value="1">试讲</option>
                    <option value="2">培训</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">授课老师</label>
            <div class="layui-input-inline">
                <select name="empID" lay-verify="required" id="teacher">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">说明</label>
            <div class="layui-input-block">
                <textarea name="remark" id="remark" placeholder="请输入内容" class="layui-textarea" style="width:500px"></textarea>
            </div>
        </div>

        <div class="layui-form-item" style="margin: 0 auto">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button class="layui-btn" lay-filter="workExprienceForm">立即提交</button>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button type="button" class="layui-btn layui-btn-primary" onclick="guanbi()">返回</button>
        </div>
    </form>
    <script type="textml" id="roleTpl">
    {{# if (d.type=== 1) { }}
        试讲
    {{# } else if(d.type=== 2) { }}
        培训
    {{# } else { }}
        无数据
    {{# } }}
</script>
</div>
</body>
<script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
<script>
    reload();
    function reload(){
        layui.use('table', function(){
            var table = layui.table;

            //第一个实例
            table.render({
                elem: '#demo'
                ,height: 523
                ,url: '/student/selectTrial' //数据接口
                ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
                ,page: true //开启分页
                ,cols: [[ //表头
                    {field: 'trialID', title: '序号',sort: true, fixed: 'left',width:80}
                    ,{field: 'date', title: '日期', width:180,templet : '<span>{{layui.util.toDateString(d.chatDate,"yyyy-MM-dd")}}</span>'}
                    ,{field: 'time', title: '时间',width: 180}
                    ,{field: 'courseName', title: '课程/章节'}
                    ,{field: 'type', title: '授课类型',templet: '#roleTpl'}
                    ,{field: 'empName', title: '授课老师'}
                    ,{field: 'remark', title: '备注'}
                    ,{field: '', title: '操作',toolbar:'#barDemo',width:150}
                ]]
            });

            //Demo
            layui.use('laydate', function(){
                var laydate = layui.laydate;

                //执行一个laydate实例
                laydate.render({
                    elem: '#rutime' //指定元素
                    ,theme: '#777D777'
                });
            });
        });
    };
    $(document).ready(function () {
        $.post("${pageContext.request.contextPath}/student/CouresAjax",{},function (data) {
            var t = $("#classTeacher").html();
            $.each(data,function (index,obj) {
                $.each(obj,function (i,o) {
                    t += "<option value='"+o.courseID+"'>"+o.courseName+"</option>";
                })
            });
            $("#classTeacher").html(t);
            layui.use(['form'], function(){
                var form = layui.form;
                form.render('select');
            });
        },'json');
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

    })

    function QQT() {

        var times = "";
        // var week = new Date().getDay();
        var ti = $("#rutime").val();
        var week = new Date(ti).getDay();
        if (week == 0) {
            str = "星期日";
        } else if (week == 1) {
            str = "星期一";
        } else if (week == 2) {
            str = "星期二";
        } else if (week == 3) {
            str = "星期三";
        } else if (week == 4) {
            str = "星期四";
        } else if (week == 5) {
            str = "星期五";
        } else if (week == 6) {
            str = "星期六";
        }
        $("#time").attr("value",str);
    }

</script>
</html>
