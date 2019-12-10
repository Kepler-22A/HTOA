<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/12/4
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工资料</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <script>
    </script>
</head>
<body>

<%--员工资料表部分--%>

<div class="layui-form">
    <div style="width: 1365px;margin: 0 auto">
        <table id="demo" lay-filter="test"></table>
    </div>
    <script>
        layui.use('table', function(){
            var table = layui.table;

            //第一个实例
            table.render({
                elem: '#demo'
                ,height: 523
                ,url: '/emp/empDataList' //数据接口
                ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
                ,page: true //开启分页
                ,cols: [[ //表头
                    {field: 'empId', title: '员工编号', width:100, sort: true, fixed: 'left'}
                    ,{field: 'empName', title: '员工姓名', width:100}
                    ,{field: 'depName', title: '部门', width:80}
                    ,{field: 'postName', title: '职务', width:200}
                    ,{field: 'sex', title: '性别', width: 60}
                    ,{field: 'phone', title: '手机号码', width: 200}
                    ,{field: 'address', title: '家庭住址', width: 200}
                    ,{field: 'status', title: '状态', width: 60}
                    ,{field: '', title: '设置状态', width: 100,templet:'#empStatusA'}
                    ,{field: 'password', title: '初始密码', width: 135}
                    ,{field: '', title: '操作', width: 115 ,toolbar:'#barDemo'}
                ]]
            });

        });
    </script>
    <script type="text/html" id="empStatusA">
        {{# if(d.status == 1) { }}
            <a href="<%=request.getContextPath()%>/emp/updateEmpStatus/{{d.empId}}"  class="layui-table-link">
                禁用
            </a>
        {{# }else if(d.status == 0){ }}
            <a href="<%=request.getContextPath()%>/emp/updateEmpStatus/{{d.empId}}"  class="layui-table-link">
                启用
            </a>
        {{# } }}

    </script>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" href="<%=request.getContextPath()%>/emp/delEmp/{{ d.empId }}">删除</a>
    </script>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" onclick="add()">添加员工</button>
        </div>
    </script>

<%--员工添加&修改的表单--%>
</div>

<form id="empDataForm" style="display: none" class="layui-form" action="" method="post">
    <div class="layui-form-item">
        <input type="hidden" name="empId" value="0">
        <input type="hidden" name="depId" value="3">
        <input type="hidden" name="remark" value="xxx">
        <input type="hidden" name="Bank" value="中国工商">
        <input type="hidden" name="accountName" value="99999999999">
        <input type="hidden" name="bankNumber" value="999999999999">
        <input type="hidden" name="alipay" value="99999999">
        <input type="hidden" name="password" value="123456">
        <input type="hidden" name="postId" value="3">
        <input type="hidden" name="status" value="1">
        <input type="hidden" name="nation" value="xxx">
        <label class="layui-form-label">员工姓名</label>
        <div class="layui-input-inline">
            <input type="text" name="empName" lay-verify="empName" autocomplete="off" placeholder="请输入员工姓名" class="layui-input">
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">部门名称</label>
            <div class="layui-input-inline">
                <select name="depId">
                    <option value="1">宏图</option>
                    <option value="2">教研部</option>
                    <option value="3">学工部</option>
                    <option value="1004">行政部</option>
                    <option value="1006">后勤部</option>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">职务名称</label>
        <div class="layui-input-inline">
            <input type="text" name="postName" lay-verify="postName" autocomplete="off" placeholder="请输入职务名称" class="layui-input">
        </div>

        <label class="layui-form-label">家庭住址</label>
        <div class="layui-input-inline">
            <input type="text" name="Address" lay-verify="Address" autocomplete="off" placeholder="请输入家庭住址" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-inline">
            <select name="sex">
                <option value="男">男</option>
                <option value="女">女</option>
            </select>
        </div>

        <label class="layui-form-label">身份证号</label>
        <div class="layui-input-inline">
            <input type="text" name="Cardno" lay-verify="Cardno" autocomplete="off" placeholder="请输入身份证号" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item layui-form">
        <label class="layui-form-label">出生日期</label>
        <div class="layui-input-inline">
            <input type="date" name="BirthdayEX" lay-verify="title" autocomplete="off" placeholder="出生日期" class="layui-input">
        </div>

        <label class="layui-form-label">籍贯</label>
        <div class="layui-input-inline" style="width: 85px">
            <select lay-filter="nation_1" id="nation_1" name="nation_1">

            </select>
        </div>
        <div class="layui-input-inline" style="width: 85px">
            <select lay-filter="nation_2" id="nation_2" name="nation_2">

            </select>
        </div>
        <div class="layui-input-inline" style="width: 85px">
            <select id="nation_3" name="nation_3">

            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">电话号码</label>
        <div class="layui-input-inline">
            <input type="text" name="Phone" lay-verify="Phone" autocomplete="off" placeholder="请输入电话号码" class="layui-input">
        </div>

        <label class="layui-form-label">QQ号码</label>
        <div class="layui-input-inline">
            <input type="text" name="QQcode" lay-verify="QQcode" autocomplete="off" placeholder="请输入QQ号码" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">微信号码</label>
        <div class="layui-input-inline">
            <input type="text" name="Weixin" lay-verify="Weixin" autocomplete="off" placeholder="请输入微信号码" class="layui-input">
        </div>

        <label class="layui-form-label">邮箱地址</label>
        <div class="layui-input-inline">
            <input type="text" name="Email" lay-verify="Email" autocomplete="off" placeholder="请输入邮箱地址" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">婚姻状况</label>
        <div class="layui-input-inline">
            <select name="married">
                <option value="">----未选择----</option>
                <option value="已婚">已婚</option>
                <option value="未婚">未婚</option>
                <option value="离异">离异</option>
                <option value="丧偶">丧偶</option>
            </select>
        </div>

        <label class="layui-form-label">毕业学校</label>
        <div class="layui-input-inline">
            <input type="text" name="University" lay-verify="University" autocomplete="off" placeholder="请输入毕业学校" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">专业</label>
        <div class="layui-input-inline">
            <input type="text" name="Major" lay-verify="Major" autocomplete="off" placeholder="请输入您的专业" class="layui-input">
        </div>

        <label class="layui-form-label">学历</label>
        <div class="layui-input-inline">
            <select name="Education">
                <option value="">----未选择----</option>
                <option value="高中以下">高中以下</option>
                <option value="专科">专科</option>
                <option value="本科">本科</option>
                <option value="本科以上">本科以上</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">入职日期</label>
        <div class="layui-input-inline">
            <input type="date" name="hireDayEX" lay-verify="title" autocomplete="off" placeholder="入职日期" class="layui-input">
        </div>

        <label class="layui-form-label">登录密码</label>
        <div class="layui-input-inline">
            <input type="text" name="password" disabled="disabled" lay-verify="title" autocomplete="off" placeholder="登录密码默认123456" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item" style="margin: 0 auto">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button type="button" class="layui-btn layui-btn-primary" onclick="closeForm()">返回</button>
    </div>
</form>

<script>
    // $('#nation_1').change(function () {
    //     console.log("aaa");
    //     addShiOption($("#nation_1").val());
    // });

    function closeForm() {
        layer.closeAll();
    }

    layui.use(['layer', 'jquery', 'form'], function() {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form;

        form.verify({
            empName: function(value){
                if(value.length < 1){
                    return '请输入姓名';
                }
            }
            // ,pass: [
            //     /^[\S]{6,12}$/
            //     ,'密码必须6到12位，且不能出现空格'
            // ]
            ,postName: function(value){
                if(value == "专职班主任" || value == "讲师" || value == "学工主任" || value == "教研副主任" || value == "教研主任"){

                }else {
                    return '请输入正确的职务名';
                }
            }
            ,Address: function (value) {
                if (value == '' || value == null){
                    return '请输入家庭住址';
                }
            }
            ,Cardno: function (value) {
                if (value == '' || value == null){
                    return '身份证号码为必填项！';
                }
            }
            ,Phone: function (value) {
                if (value == '' || value == null || value.length != 11){
                    return '手机号码不正确！';
                }
            }
            ,QQcode: function (value) {
                if (value == '' || value == null){
                    return 'QQ号码不能为空！';
                }
            }
            ,Weixin: function (value) {
                if (value == '' || value == null){
                    return '请留下您的微信号以便联系！';
                }
            }
            ,Email:[
                /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
                ,'请输入正确的邮箱格式！'
            ]
            ,University: function (value) {
                if (value == '' || value == null){
                    return "请输入您的毕业学校！";
                }
            }
            ,Major: function (value) {
                if (value == '' || value == null){
                    return "请输入您的专业！";
                }
            }
        });
    });


    function add() {
        $("#empDataForm").attr("action","${pageContext.request.contextPath}/emp/empAdd");
        layer.open({
            type: 1,
            title:"新增",
            area:['45%','75%'],
            content: $("#empDataForm"),
            closeBtn :0, //隐藏弹出层的关闭按钮
            yes:function(index,layero){
            }
        });

    }

    layui.use('form', function(){
        var form = layui.form;
        //监听提交
        form.on('submit(formDemo)', function(data){
            layer.msg("成功添加");
            return true;
        });
    });


    //监听省&直辖市的select是否改变
    layui.use(['layer', 'jquery', 'form'], function () {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form;

        form.on('select(nation_1)', function(data){
            addShiOption($("#nation_1").val());
        });
    });

    //监听市&区的select是否改变
    layui.use(['layer', 'jquery', 'form'], function () {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form;

        form.on('select(nation_2)', function(data){
            addXianOption($("#nation_2").val());
        });
    });

    //此方法根据省&直辖市select的变化改变所属市&区
    function addShiOption(position) {
        $.post("/emp/getNationList",{type:'市',position:position},function (data) {
            var form = layui.form;
            var nation_2_text = "";
            $.each(data,function (index,obj) {
                nation_2_text += "<option value='" + obj + "'>" + obj + "</option>";
            });
            $("#nation_2").html(nation_2_text);
            form.render('select');
            addXianOption($("#nation_2").val());
        },"json");
    }

    //此方法根据市select的变化改变所属县
    function addXianOption(position) {
        $.post("/emp/getNationList",{type:'县',position:position},function (data) {
            var form = layui.form;
            var nation_3_text = "";
            $.each(data,function (index,obj) {
                nation_3_text += "<option value='" + obj + "'>" + obj + "</option>";
            });
            $("#nation_3").html(nation_3_text);
            form.render('select');
        },"json");
    }

    //当页面加载完成时初步加载省select
    $(document).ready(function () {
        $.post("/emp/getNationList",{type:'省',position:''},function (data) {
            var form = layui.form;
            var nation_1_text = "";
            $.each(data,function (index,obj) {
                nation_1_text += "<option value='" + obj + "'>" + obj + "</option>";
            });
            $("#nation_1").html(nation_1_text);
            form.render('select');
            addShiOption($("#nation_1").val());
        },"json");
    });
</script>

</body>
</html>
