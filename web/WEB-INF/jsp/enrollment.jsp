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
                area:['800px','600px'],
                content: $("#addfloor"),
                closeBtn :1, //显示弹出层的关闭按钮，0是不显示
                cancel: function(index, layero){
                    layer.close(index);
                    $("#addfloor").hide(); //jquery方式关闭
                    return false;
                }
            });

            $.post("/data/selMajor",{},function (data) {
                var form = layui.form;
                var deptSelect_any = "";
                $.each(data,function (index,obj) {
                    deptSelect_any += "<option value='" + obj.deptID + "'>" + obj.deptName + "</option>";
                });
                $("#deptSelect").html(deptSelect_any);
                form.render('select');
            },"json");

            $.post("/data/selClassType",{},function (data) {
                console.log(data);
                var form = layui.form;
                var ClassSelect_any = "";
                $.each(data,function (index,obj) {
                    ClassSelect_any += "<option value='" + obj.calssTypeId + "'>" + obj.classTypeName + "</option>";
                });
                $("#ClassSelect").html(ClassSelect_any);
                form.render('select');
            },"json");

            $.post("/data/selEmp",{},function (data) {
                var form = layui.form;
                var EmpSelect_any = "";
                $.each(data,function (index,obj) {
                    EmpSelect_any += "<option value='" + obj.empId + "'>" + obj.empName + "</option>";
                });
                $("#EmpSelect").html(EmpSelect_any);
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
        function  del(enrollmentid) {
            layer.confirm('是否要删除？', {
                icon:3,
                btn: ['确认','取消'] //按钮
            }, function(){
                $.post("${pageContext.request.contextPath}/data/delEnroll",{enrollmentid:enrollmentid},
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
    <script type="text/html" id="enrollMoneyTime">
        {{ dateFormat(d.enrollMoneyTime) }}
    </script>
    <script type="text/html" id="testTime">
        {{ dateFormat(d.testTime) }}
    </script>
    <script type="text/html" id="startTime">
        {{ dateFormat(d.startTime) }}
    </script>
    <script type="text/html" id="reviewerTime">
        {{ dateFormat(d.reviewerTime) }}
    </script>
    <script type="text/html"  id="xingbie">
        {{# if(d.sex == '1'){ }}
         男
        {{# }else if(d.sex == '2'){ }}
        女
        {{# }else if(d.sex == '男'){ }}
        男
        {{# }else if(d.sex == '女'){ }}
        女
        {{# } }}
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
                    {field:'enrollmentid', width:78, title: '编号', sort: true}
                    ,{field:'studName', width:150, title: '姓名'}
                    ,{field:'card', width:200, title: '身份证'}
                    ,{field:'sex',width:60,title: '性别',templet:'#xingbie'}
                    ,{field:'tell',width:130,title: '手机号'}
                    ,{field:'school',width:150,title: '学校'}
                    ,{field:'classes',width:120,title: '班级'}
                    ,{field:'score',width:80,title: '成绩'}
                    ,{field:'amount',width:120,title: '预定报名费'}
                    ,{field:'enrollMoneyTime',width:180,title: '发放时间',templet:'#enrollMoneyTime'}
                    ,{field:'testTime',width:180,title: '试学时间',templet:'#testTime'}
                    ,{field:'status',width:60,title: '状态'}
                    ,{field:'startTime',width:180,title: '录入时间',templet:'#startTime'}
                    ,{field:'empName',width:150,title: '招生老师'}
                    ,{field:'reviewer',width:150,title: '审核人'}
                    ,{field:'reviewerTime',width:180,title: '审核时间',templet:'#reviewerTime'}
                    ,{field:'classTypeName',width:150,title: '班级类别'}
                    ,{field:'deptName',width:200,title: '专业'}
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
        <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" onclick="del('{{ d.enrollmentid }}')">
            <i class="layui-icon layui-icon-delete"></i> 删除
        </button>

    </script>

</div>
<form  class="layui-form" id="addfloor" style="display: none" method="post" action="${pageContext.request.contextPath}/data/addenroll">
    <div class="layui-form-item" style="margin-top: 20px;">
        <label class="layui-form-label" style="width: 100px;">新生姓名:</label>
        <div class="layui-input-inline">
            <input id="studName" type="text" name="studName" required  lay-verify="required" autocomplete="off" class="layui-input">
        </div>
        <label class="layui-form-label" style="width: 100px;">身份证:</label>
        <div class="layui-input-inline">
            <input id="card" type="text" name="card" required  lay-verify="required" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 20px;">
        <label class="layui-form-label" style="width: 100px;">性别:</label>
        <div class="layui-input-inline">
            <select id="sex" name="sex" style="width: 150px">
                <option value="男">男</option>
                <option value="女">女</option>
            </select>
        </div>
        <label class="layui-form-label" style="width: 100px;">电话号码:</label>
        <div class="layui-input-inline">
            <input id="tell" type="text" name="tell" required  lay-verify="required" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 20px;">
        <label class="layui-form-label" style="width: 100px;">QQ账号:</label>
        <div class="layui-input-inline">
            <input id="qq" type="text" name="qq" required  lay-verify="required" autocomplete="off" class="layui-input">
        </div>
        <label class="layui-form-label" style="width: 100px;">就读学校:</label>
        <div class="layui-input-inline">
            <input id="school" type="text" name="school" required  lay-verify="required" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 20px;">
        <label class="layui-form-label" style="width: 100px;">所在班级:</label>
        <div class="layui-input-inline">
            <input id="classes" type="text" name="classes" required  lay-verify="required" autocomplete="off" class="layui-input">
        </div>
        <label class="layui-form-label" style="width: 100px;">录取成绩:</label>
        <div class="layui-input-inline">
            <input id="score" type="text" name="score" required  lay-verify="required" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 20px;">
        <label class="layui-form-label" style="width: 100px;">学生备注:</label>
        <div class="layui-input-inline">
            <input id="remark" type="text" name="remark" required  lay-verify="required" autocomplete="off" class="layui-input">
        </div>
        <label class="layui-form-label" style="width: 100px;">班级类别:</label>
        <div class="layui-input-inline">
            <select id="ClassSelect" name="studType" style="width: 150px">
            </select>
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 20px;">
        <label class="layui-form-label" style="width: 100px;">学生专业</label>
        <div class="layui-input-inline">
            <select id="deptSelect" name="majorId" style="width: 150px">
            </select>
        </div>
        <label class="layui-form-label" style="width: 100px;">招生老师:</label>
        <div class="layui-input-inline">
            <select id="EmpSelect" name="empid" style="width: 150px">
            </select>
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 20px;">
        <label class="layui-form-label" style="width: 100px;">是否送电脑</label>
        <div class="layui-input-inline">
            <select id="computerSelect" name="computer" style="width: 150px">
                <option value="是"> 是</option>
                <option value="否">否</option>
            </select>
        </div>
    </div>
    <div align="center">
        <input type="submit" value="提交" style="height: 30px;width: 50px;">
    </div>
</form>
</body>
</html>
