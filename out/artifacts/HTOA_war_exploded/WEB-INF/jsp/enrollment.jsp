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
    <title>招生信息</title>
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
            document.getElementById("addfloor").reset();  //清空表单
            layer.open({
                type: 1,
                title:"新增",
                area:['400px','300px'],
                content: $("#addfloor"),
                closeBtn :1, //显示弹出层的关闭按钮，0是不显示
                cancel: function(index, layero){
                    layer.close(index);
                    $("#addfloor").hide(); //jquery方式关闭
                    return false;
                }
            });

            $.post("/system/selDept",{},function (data) {
                var form = layui.form;
                var deptSelect_any = "";
                $.each(data,function (index,obj) {
                    deptSelect_any += "<option value='" + obj.deptID + "'>" + obj.deptName + "</option>";
                });
                $("#deptSelect").html(deptSelect_any);
                form.render('select');
            },"json");
        }
        //修改
        function  update(majorID) {

            $.post("${pageContext.request.contextPath}/system/selectMajorID",{id : majorID},function (d) {
                $("#addfloor").attr("action","${pageContext.request.contextPath}/system/UpdateMajorID/" + d.MajorVo.majorID);
                $("#majorID").val(d.MajorVo.majorID);
                $("#majorName").val(d.MajorVo.majorName);
                $("#remark").val(d.MajorVo.remark);
            },"json");
            layer.open({
                type: 1,
                title:"修改",
                area:['400px','300px'],
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
        function  del(majorID) {
            layer.confirm('是否要删除？', {
                icon:3,
                btn: ['确认','取消'] //按钮
            }, function(){
                $.post("${pageContext.request.contextPath}/system/delMajor",{majorID:majorID},
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
                ,url:'/data/enrollmentdata'
                ,toolbar: '#toolbarDemo'
                ,height:400
                ,cols: [[
                    {field:'enrollmentid', width:100, title: '编号', sort: true}
                    ,{field:'studName', width:150, title: '姓名'}
                    ,{field:'card', width:200, title: '身份证'}
                    ,{field:'sex',width:60,title: '性别'}
                    ,{field:'tell',width:100,title: '手机号'}
                    ,{field:'school',width:150,title: '学校'}
                    ,{field:'classes',width:100,title: '班级'}
                    ,{field:'score',width:80,title: '成绩'}
                    ,{field:'amount',width:120,title: '预定报名费'}
                    ,{field:'enrollMoneyTime',width:200,title: '发放时间'}
                    ,{field:'testTime',width:200,title: '试学时间'}
                    ,{field:'status',width:60,title: '状态'}
                    ,{field:'startTime',width:200,title: '录入时间'}
                    ,{field:'empid',width:150,title: '招生老师'}
                    ,{field:'reviewer',width:150,title: '审核人'}
                    ,{field:'reviewerTime',width:200,title: '审核时间'}
                    ,{field:'studType',width:150,title: '班级类别'}
                    ,{field:'majorId',width:200,title: '专业'}
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
<%--        <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" onclick="update('{{d.majorID}}')">--%>
<%--            <i class="layui-icon layui-icon-edit"></i>编辑--%>
<%--        </button>--%>
        <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" onclick="del('{{ d.majorID }}')">
            <i class="layui-icon layui-icon-delete"></i> 删除
        </button>

    </script>

</div>
<form  class="layui-form" id="addfloor" style="display: none" method="post" action="${pageContext.request.contextPath}/data/addenroll">
    <div class="layui-form-item" style="margin-top: 20px;">
        <label class="layui-form-label" style="width: 100px;">系名称</label>
        <div class="layui-input-inline">
            <select id="deptSelect" name="deptID" style="width: 150px">
            </select>
        </div>
    </div>


    <div class="layui-form-item" style="margin-top: 20px;">
        <label class="layui-form-label" style="width: 100px;">专业名称:</label>
        <div class="layui-input-inline">
            <input id="majorName" type="text" name="majorName" required  lay-verify="required" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 20px;">
        <label class="layui-form-label" style="width: 100px;">说明:</label>
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
