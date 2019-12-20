<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/19
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的周报</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
</head>
<body>
    <table id="weeklyTable"></table>

    <%--每行数据的操作部分--%>
    <script id="weeklyRowsCZ" type="text/html">
        <a class="layui-btn layui-btn-xs" lay-event="edit" href="${pageContext.request.contextPath}/emp/toWeeklyDetails/{{ d.worklogid }}">详情</a>

        <a class="layui-btn layui-btn-xs" lay-event="edit" href="javascript:delWeekly('{{ d.worklogid }}')">删除</a>

        <a class="layui-btn layui-btn-xs" lay-event="edit" href="javascript:updateWeekly('{{ d.worklogid }}','{{ d.weekCur }}','{{ d.studentQuestion }}','{{ d.Idea }}','{{ d.weekNext }}')">编辑</a>
    </script>

    <%--表头工具栏--%>
    <script type="text/html" id="weeklyToolbar">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-xs" lay-event="edit" href="javascript:addWeekly()">添加周报</a>
            <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reload()">刷新表格</button>
        </div>
    </script>

    <form id="weeklyAddForm" method="post" action="${pageContext.request.contextPath}/emp/addWeekly" class="layui-form" style="display: none">
        <input id="empId" name="empId" type="hidden" value="0">
        <input id="weeklylogid" name="worklogid" type="hidden" value="0">
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 180px">本周情况描述：</label>
            <div class="layui-input-inline" style="width: 600px">
                <textarea id="weekCur" type="text" name="weekCur" lay-verify="weekCur" autocomplete="off" placeholder="" class="layui-input"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 180px">问题学生反馈：</label>
            <div class="layui-input-inline" style="width: 600px">
                <textarea id="studentQuestion" type="text" name="studentQuestion" lay-verify="studentQuestion" autocomplete="off" placeholder="" class="layui-input"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 180px">建议：</label>
            <div class="layui-input-inline" style="width: 600px">
                <textarea id="Idea" type="text" name="Idea" lay-verify="Idea" autocomplete="off" placeholder="" class="layui-input"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 180px">下周工作计划：</label>
            <div class="layui-input-inline" style="width: 600px">
                <textarea id="weekNext" type="text" name="weekNext" lay-verify="weekNext" autocomplete="off" placeholder="" class="layui-input"></textarea>
            </div>
        </div>

        <div class="layui-form-item" style="margin: 0 auto">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button class="layui-btn" lay-filter="weeklyForm">立即提交</button>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button type="button" class="layui-btn layui-btn-primary" onclick="closeWeeklyForm()">返回</button>
        </div>

    </form>

    <script>
        //表格数据编写
        function reload(){
            layui.use('table', function(){
                var table = layui.table;

                //表格数据接收
                table.render({
                    elem: '#weeklyTable'
                    ,height: 523
                    ,url: '/emp/selWeeklyListByEmpId' //数据接口
                    ,toolbar: '#weeklyToolbar' //开启头部工具栏，并为其绑定左侧模板
                    ,page: true //开启分页
                    ,cols: [[ //表头
                        {field: 'worklogid', title: '周报ID', width:85, sort: true, fixed: 'left'}
                        ,{field: 'empName', title: '员工姓名', width:85}
                        ,{field: 'Workday', title: '周报编写时间', width:160,templet : '<span>{{layui.util.toDateString(d.Workday,"yyyy-MM-dd HH:mm:ss")}}</span>'}
                        ,{field: 'weekCur', title: '本周汇报', width:240}
                        ,{field: 'studentQuestion', title: '问题学生反馈', width: 240}
                        ,{field: 'Idea', title: '意见', width: 240}
                        ,{field: 'weekNext', title: '下周计划', width:250}
                        ,{field: '', title: '操作', width: 180 ,toolbar:'#weeklyRowsCZ'}
                    ]]
                });

                // //监听行单击事件
                // table.on('row(test)', function(obj){
                //     console.log(obj.tr) //得到当前行元素对象
                //     console.log(obj.data) //得到当前行数据
                //     reloadSecondTable(obj.data.empId,obj.data.empName);
                // });

            });
        };

        reload();

        //新增周报*****************************************************************************************************
        function addWeekly() {
            $("#weeklyAddForm").attr("action","${pageContext.request.contextPath}/emp/addWeekly");
            $("#empId").val("0");
            layer.open({
                type: 1,
                title:"新增周报",
                area:['60%','65%'],
                content: $("#weeklyAddForm"),
                closeBtn :0, //隐藏弹出层的关闭按钮
                yes:function(index,layero){
                }
            });
        }

        function delWeekly(weeklylogid) {
            layer.confirm('是否要删除？', {
                icon:3,
                btn: ['确认','取消'] //按钮
            }, function(){
                $.post("${pageContext.request.contextPath}/emp/delWeekly/" + weeklylogid,{},function () {
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

        function updateWeekly(weeklylogid,weekCur,questionStudent,idea,nextWeek) {
            $("#weeklyAddForm").attr("action","${pageContext.request.contextPath}/emp/updataWeekly");
            $("#weeklylogid").val(weeklylogid);
            $("#weekCur").val(weekCur);
            $("#studentQuestion").val(questionStudent);
            $("#Idea").val(idea);
            $("#weekNext").val(nextWeek);
            layer.open({
                type: 1,
                title:"修改周报",
                area:['60%','65%'],
                content: $("#weeklyAddForm"),
                closeBtn :0, //隐藏弹出层的关闭按钮
                yes:function(index,layero){
                }
            });
        }

        function closeWeeklyForm() {
            layer.closeAll();
        }
    </script>
</body>
</html>
