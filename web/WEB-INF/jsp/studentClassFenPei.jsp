<%--
  Created by IntelliJ IDEA.
  User: 28401
  Date: 2019/12/11
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>班级分配</title>
    <meta charset="utf-8">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery-1.7.2.min.js"></script><!---引用jquery库-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery-3.3.1.min.js"></script><!---引用jquery库-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script><!---引用easyui库-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script><!---引用语言包-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css"></link><!---主题样式-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/easyui/themes/icon.css"></link><!---图标-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>

<div style="width: 14%;height: 500px;float: left;">
    <ul class="easyui-tree" id="aa" style="width: 230px">
    </ul>
</div>
<div style="width: 86%;float: left;">
    <h1>班级学生列表</h1>

</div >
<div style="width: 86%;float: left;">
    <table class="layui-hide" id="test1"></table>

</div >
<div style="width: 86%;float: left;">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <div class="layui-inline"style="margin-left: 0px">
                <legend style="float: left;display: block;  padding: 9px 10px;    font-weight: 400;   line-height: 20px;  text-align: right">学生姓名</legend>
                <div class="layui-input-inline">
                    <input type="tel" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 0px">
                <legend style="float: left;display: block;  padding: 9px 10px;    font-weight: 400;   line-height: 20px;  text-align: right">手机号码</legend>
                <div class="layui-input-inline">
                    <input type="text" name="email" lay-verify="email" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 0px">
                <legend style="float: left;display: block;  padding: 9px 10px;    font-weight: 400;   line-height: 20px;  text-align: right">班级类型</legend>
                <div class="layui-input-inline">
                    <select name="classTeacher" lay-verify="required" id="classTeacher">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>
                <button type="button" class="layui-btn">搜索</button>
                <button type="button" class="layui-btn layui-btn-normal" onclick="fenban()">分班</button>
        </div>

    </form>
</div>
<div style="width: 86%;float: left;">
    <table class="layui-hide" id="test" lay-filter="test"></table>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
            <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
            <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
        </div>
    </script>
</div >
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</body>
<script>
    reload();
    layui.use('form', function(){
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function(data){
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });

    function reload() {
        layui.use('table', function(){
            var table = layui.table;

            table.render({
                elem: '#test'
                ,url:'${pageContext.request.contextPath}/student/selectNOClassStudentData'
                ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
                ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                    title: '提示'
                    ,layEvent: 'LAYTABLE_TIPS'
                    ,icon: 'layui-icon-tips'
                }]
                ,title: '用户数据表'
                ,cols: [[
                    {type: 'checkbox', fixed: 'left'}
                    ,{field:'enrollmentid', title: 'ID', }
                    ,{field:'studName', title: '学生姓名'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
                    ,{field:'card', title: '身份证'}
                    ,{field:'sex', title: '性别'}
                    ,{field:'tell', title: '电话号码'}
                    ,{field:'school', title: '学校'}
                    ,{field:'classes', title: '班级'}
                    ,{field:'empName', title: '招生老师'}
                    ,{field:'classTypeName', title: '班级类别'}
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
        });
        layui.use('table', function(){
            var table = layui.table;

            table.render({
                elem: '#test1'
                ,cellMinWidth: 100 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                ,cols: [[
                    {field:'Studid', title: 'ID', }
                    ,{field:'className', title: '班级昵称'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
                    ,{field:'stuname', title: '学生姓名'}
                    ,{field:'sex', title: '性别'}
                    ,{field:'phone', title: '电话号码'}
                ]]
                ,data: [{
                    "Studid": "10001"
                    ,"className": "开发一班"
                    ,"stuname": "IU"
                    ,"sex": "女"
                    ,"sex": "浙江杭州"
                    ,"phone": "1008611"

                }]
            });
        });
    }
    function fenban(){
        var table = layui.table;
        var ss = table.checkStatus("test");
        var checkDate = ss.data;
        var ID;
        for(var i=0;i<checkDate.length;i++){
            ID = checkDate[i].enrollmentid; //学生信息id
        }
        if(ss.isAll){
            var classid =  $("#aa").tree('getSelected').id; //班级信息
             addstudentfeipei(ID,classid);
       }else {
           alert("未选择学生");
       }
    }

    function addstudentfeipei(ID,classid) {
        layer.confirm('是否要为改学生分班？', {
            icon:3,
            btn: ['确认','取消'] //按钮
        }, function(){
            $.post('<%=request.getContextPath()%>/student/AddStudentClassFenPei/' + ID+'/'+classid,{},
                function (data) {
                    reload();
                });
            layer.msg('已分班', {
                icon: 1,
                time:2000
            });

        }, function(){
            layer.msg('已取消分班', {
                icon:0,
                time: 2000 //20s后自动关闭
            });
        });
    }

    $("#aa").tree({
        url:'${pageContext.request.contextPath}/student/StudetnClasstree',//treesql
        onClick:function (node) {//当前点击的节点
            if(node.id!=-1){
                // alert(node.id+""+node.text);//得到当前节点
                var SJ = node.id;
                sks("${pageContext.request.contextPath}/student/selectClassTree/"+SJ);//实现无缝天空一号观月计划无线对接数据 A
            }
        }
    });
    function sks(url){
        layui.use('table', function(){
            var table = layui.table;

            table.render({
                elem: '#test1'
                ,url:url
                ,cellMinWidth: 100 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                ,cols: [[
                    {field:'Studid', title: 'ID', }
                    ,{field:'className', title: '班级昵称'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
                    ,{field:'stuname', title: '学生姓名'}
                    ,{field:'sex', title: '性别'}
                    ,{field:'phone', title: '电话号码'}
                ]]
            });
        });
    };
    $(document).ready(function () {
            //查询出班级分类
            $.post("${pageContext.request.contextPath}/student/ClassTypeAjax",{},function (data) {
                var t = $("#classTeacher").html();
                $.each(data,function (index,obj) {
                    $.each(obj,function (i,o) {
                        t += "<option value='"+o.calssTypeId+"'>"+o.classTypeName+"</option>";
                    })
                });
                $("#classTeacher").html(t);
                layui.use(['form'], function(){
                    var form = layui.form;
                    form.render('select');
                });
            },'json');
    })
</script>
</html>
