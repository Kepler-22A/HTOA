<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/17
  Time: 8:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工请假</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
</head>
<body>
<%--    员工请假表--%>
    <div id="empLeaveTable"></div>

    <%--    员工表表头工具    --%>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" onclick="add()">请假</button>
            <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reload()">刷新表格</button>
            {{# if(${postName == '教研主任' || postName == '学工主任' || postName == '校长'}){ }}
                <a class="layui-btn layui-btn-sm" href="${pageContext.request.contextPath }/leave/toMyTaskPage">我的任务</a>
            {{# } }}
        </div>
    </script>

    <%--    每行员工信息的操作按钮    --%>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" href="${pageContext.request.contextPath}/leave/lookComment/{{ d.holidayID }}" lay-event="edit">查看批注</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" href="${pageContext.request.contextPath }/leave/lookTaskImg/{{ d.holidayID }}/holidayID">办理进度</a>
    </script>

<%-- 根据状态ID显示状态--%>
    <script id="statusShow" type="text/html">
        {{# if(d.status == 1){ }}
            审批中
        {{# }else if(d.status == 2){ }}
            已完成
        {{# }else if(d.status == 3){ }}
            未批准
        {{# } }}
    </script>

    <script>
        function reload(){
            layui.use('table', function(){
                var table = layui.table;

                //第一个实例
                table.render({
                    elem: '#empLeaveTable'
                    ,height: 523
                    ,url: '/leave/selEmpApply' //数据接口
                    ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
                    ,page: true //开启分页
                    ,cols: [[ //表头
                        {field: 'holidayID', title: '编号', width:150, sort: true, fixed: 'left'}
                        ,{field: 'empName', title: '请假人', width:150}
                        ,{field: 'holidayDay', title: '请假时长', width:150,templet:'<span>{{getHolidayString(d.holidayDay)}}</span>'}
                        ,{field: 'startTime', title: '开始时间', width:200,templet : '<span>{{layui.util.toDateString(d.startTime,"yyyy-MM-dd HH:mm:ss")}}</span>'}
                        ,{field: 'endTime', title: '结束时间', width: 200,templet : '<span>{{layui.util.toDateString(d.endTime,"yyyy-MM-dd HH:mm:ss")}}</span>'}
                        ,{field: 'status', title: '状态', width: 150,templet:'#statusShow'}
                        ,{field: 'remark', title: '备注', width: 300}
                        ,{field: '', title: '操作', width: 180 ,toolbar:'#barDemo'}
                    ]]
                });

            });
        };

        reload();

        function getHolidayString(data) {

            return parseInt(data) + "天" + parseInt(((data)%1)*24) + "小时";
        }
    </script>

<%--           员工请假的form表单            --%>
    <form id="empApplyForm" action="${pageContext.request.contextPath}/leave/addEmpApply" method="post" style="display: none" class="layui-form">
        <div class="layui-form-item">
            <input type="hidden" name="empID" value="${empId}">
            <label class="layui-form-label">请假类型</label>
            <div class="layui-input-inline">
<%--                <input id="ST2CollegeName" type="text" name="collegeName" lay-verify="ST2CollegeName" autocomplete="off" placeholder="请输入学校名称" class="layui-input">--%>
                <select id="applyType" name="processType">
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">开始时间</label>
            <div class="layui-input-inline">
                <input id="startTimeEX" type="datetime-local" name="startTimeEX" lay-verify="startTime" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">结束时间</label>
            <div class="layui-input-inline">
                <input id="endTimeEX" type="datetime-local" name="endTimeEX" lay-verify="endTimeEX" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">请假时长</label>
            <div class="layui-input-inline" style="width: 90px">
                <input id="holidayEX1" type="number" name="holidayEX1" lay-verify="holidayEX1" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-input-inline" style="width: 16px">
                天
            </div>
            <div class="layui-input-inline">
                <select id="holidayEX2" name="holidayEX2">
                    <option value="0.041">
                        一小时
                    </option>
                    <option value="0.083">
                        两小时
                    </option>
                    <option value="0.125">
                        三小时
                    </option>
                    <option value="0.167">
                        四小时
                    </option>
                    <option value="0.208">
                        五小时
                    </option>
                    <option value="0.250">
                        六小时
                    </option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">说明</label>
            <div class="layui-input-block" style="width: 180px">
                <textarea id="remark" name="remark" lay-verify="remark" autocomplete="off" class="layui-input"></textarea>
            </div>
        </div>
        <div class="layui-form-item" style="margin: 0 auto">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button class="layui-btn" lay-submit lay-filter="applyForm">立即提交</button>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button type="button" class="layui-btn layui-btn-primary" onclick="closeForm()">返回</button>
        </div>

    </form>

    <script>
        //新增请假单*****************************************************************************************************
        function add(empName) {

            $.post("${pageContext.request.contextPath}/leave/selProcessType",{},function (data) {
                var processNode = "";
                $.each(data,function (index,o) {
                    if(o.name == '员工请假流程') {
                        processNode += "<option value='" + o.key + "'>" + o.name + "</option>";
                    }
                });
                $('#applyType').append(processNode);
                layui.form.render('select');
            },"json");

            layer.open({
                type: 1,
                title:"新增请假单",
                area:['40%','65%'],
                content: $("#empApplyForm"),
                closeBtn :0, //隐藏弹出层的关闭按钮
                yes:function(index,layero){
                }
            });
        }

        function closeForm(){
            layer.closeAll();
        }

        layui.use(['layer', 'jquery', 'form'], function() {
            var layer = layui.layer,
                $ = layui.jquery,
                form = layui.form;

            form.on('submit(applyForm)', function(){
                $.ajax({
                    url: "${pageContext.request.contextPath}/leave/addEmpApply"
                    ,type: "post"
                    ,data:$("#empApplyForm").serialize()
                    ,async:false
                    ,success:function () {
                        
                    }
                    ,error:function () {
                        
                    }
                });
                closeForm();
                layer.msg("添加成功");
                reload();

                return false;
            });
        });
    </script>
</body>
</html>
