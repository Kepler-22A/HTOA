<%--
  Created by IntelliJ IDEA.
  User: 28401
  Date: 2019/12/5
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生资料</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <meta charset="utf-8">
    <script src="${pageContext.request.contextPath}/layui/layui.all.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script type="text/javascript">
        //添加
        function add() {
            $("#sss").attr("action","${pageContext.request.contextPath}/student/studentadd");
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
        //修改
        function  update(hourid) {
            $.post("${pageContext.request.contextPath}/student/selectStudentID",{id : hourid},function (d) {
                $("#sss").attr("action","${pageContext.request.contextPath}/student/UpdateStudentID/" + d.StudentVo.Studid);
                $("#Studid").val(d.StudentVo.Studid);
                $("#q").val(d.StudentVo.stuname);
                $("#w").val(d.StudentVo.password);
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
                area:['70%','70%'],
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
            layer.closeAll();
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
        <button class="layui-btn layui-btn-sm" onclick="add()">添加学生</button>
    </div>
</script>
<form  class="layui-form" id="sss" style="display:none" method="post"  action="${pageContext.request.contextPath}/student/studentadd">
    <input type="hidden" value="0" name="Studid" id="Studid"/>
    <input type="hidden" value="1" name="enrollno">
    <input type="hidden" value="0.0" name="qkMoney">
    <input type="hidden" value="0.0" name="score">
    <input type="hidden" value="1" name="studytype">
    <input type="hidden" value="1" name="vocationalflag">
    <input type="hidden" value="100" name="clazz"/>
    <input type="hidden" value="100" name="huor"/>
    <input type="hidden" value="1" name="stat"/>
    <input type="hidden" value="否" name="dibao"/>
    <input type="hidden" value="务农" name="sourceType"/>
    <input type="hidden" value="廖文汉" name="guarantee"/>
    <input type="hidden" value="0" name="soldier"/>
    <input type="hidden" value="100000" name="registration"/>
    <input type="hidden" value="1" name="tuixue"/>
    <input type="hidden" value="1" name="xiuxue"/>
    <input type="hidden" value="x100" name="stuno"/>
    <input type="hidden" value="是" name="grants"/>
    <input type="hidden" value="是" name="computer"/>
    <input type="hidden" value="是" name="collar"/>
    <input type="hidden" value="是" name="zhuxiao"/>
    <input type="button" value="赋值" onclick="fuzhi()">
    <div style="width: 56%;height: auto;margin-top: 1%">
        <div style="width:50%;height:100%;float: left">
            <div class="layui-form-item">
                <label class="layui-form-label">学生姓名</label>
                <div class="layui-input-block">
                    <input id="q" type="text" name="stuname" required  lay-verify="required" placeholder="请输入学生姓名" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-block">
                    <input id="w" type="text" name="password" required  lay-verify="required" placeholder="请设置登录密码" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <input type="radio" name="sex" value="男" title="男">
                    <input type="radio" name="sex" value="女" title="女" checked>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">年龄</label>
                <div class="layui-input-block">
                    <input id="e" type="text" name="age" required  lay-verify="required" placeholder="请输入年龄" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">出生年月</label>
                <div class="layui-input-block">
                    <input type="birthday" name="shijian" required  lay-verify="required" placeholder="单击此处选择日期" id="srtime" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">学生电话</label>
                <div class="layui-input-block">
                    <input id="r" type="text" name="phone" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">家庭地址</label>
                <div class="layui-input-block">
                    <input id="t" type="text" name="addr" required  lay-verify="required" placeholder="准确到乡镇即可" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">入学时间</label>
                <div class="layui-input-block">
                    <input type="entertime" name="shijian2" required  lay-verify="required" placeholder="单击此处选择日期" id="rutime" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">民族</label>
                <div class="layui-input-block">
                    <input id="y" type="text" name="nation" required  lay-verify="required" placeholder="民族" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">籍贯</label>
                <div class="layui-input-block">
                    <input id="u" type="text" name="natives" required  lay-verify="required" placeholder="籍贯" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">户口性质</label>
                <div class="layui-input-block">
                    <select name="residence" lay-verify="required">
                        <option value="">请选择</option>
                        <option value="1">农业</option>
                        <option value="2">非农</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">面试人建议</label>
                <div class="layui-input-block">
                    <textarea name="auditionoption" placeholder="请输入内容" class="layui-textarea"></textarea>
                </div>
            </div>
        </div>

        <div style="width: 50%;height: 100%;float: left">
            <div class="layui-form-item">
                <label class="layui-form-label">家长姓名</label>
                <div class="layui-input-block">
                    <input id="d" type="text" name="parents" required  lay-verify="required" placeholder="请输入家长姓名" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">家长电话</label>
                <div class="layui-input-block">
                    <input id="f" type="text" name="parentsphone" required  lay-verify="required" placeholder="请输入家长电话" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">老师电话</label>
                <div class="layui-input-block">
                    <input id="g" type="text" name="intrphone" required  lay-verify="required" placeholder="请输入老师电话" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">面试人</label>
                <div class="layui-input-block">
                    <input id="h" type="text" name="audition" required  lay-verify="required" placeholder="请输入面试人" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <input id="j" type="text" name="remark" required  lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">是否中专</label>
                <div class="layui-input-block">
                    <select name="isvocational" lay-verify="required">
                        <option value="">请选择</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">中专学校</label>
                <div class="layui-input-block">
                    <input id="l" type="text" name="vocationalsch" required  lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">毕业学校</label>
                <div class="layui-input-block">
                    <input id="x" type="text" name="middleschool" required  lay-verify="required" placeholder="请输入毕业学校" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">身份证号</label>
                <div class="layui-input-block">
                    <input id="o" type="text" name="cardid" required  lay-verify="required" placeholder="请输入11位完整的号码" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">就读专业</label>
                <div class="layui-input-block">
                    <input id="p" type="text" name="professional" required  lay-verify="required" placeholder="请输入就读专业" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">专业类别</label>
                <div class="layui-input-block">
                    <select name="prolevel" lay-verify="required">
                        <option value="">请选择</option>
                        <option value="1">中技</option>
                        <option value="2">高技</option>
                        <option value="3">3+2</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">介绍老师</label>
                <div class="layui-input-block">
                    <input id="v" type="text" name="introduretech" required  lay-verify="required" placeholder="老师" autocomplete="off" class="layui-input">
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
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test'
            ,url:'/student/data'
            ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            ,title: '用户数据表'
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'studid', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
                ,{field:'stuname', title:'学生姓名', width:120, edit: 'text'}
                ,{field:'stuno', title:'学号', width:80, edit: 'text', sort: true}
                ,{field:'sex', title:'性别', width:80, edit: 'text', sort: true}
                ,{field:'cardid', title:'身份证', width:100}
                ,{field:'phone', title:'学生电话'}
                ,{field:'clazz', title:'班级', width:100}//!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                ,{field:'huor', title:'宿舍房号', width:100,sort: true}///!!!!!!!!!!!!!!!!!!!!!!!!
                ,{field:'stat', title:'学生状态', width:100}
                ,{field:'collar', title:'是否领用电脑', width:100}
                ,{field:'grants', title:'享受助学金', width:100}
                ,{field:'computer', title:'是否送电脑', width:100}
                ,{field:'parents', title:'家长姓名', width:100}
                ,{field:'qkMoney', title:'欠款金额', width:100}
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
        //Demo
        layui.use('laydate', function(){
            var laydate = layui.laydate;

            //执行一个laydate实例
            laydate.render({
                elem: '#rutime' //指定元素
                ,theme: '#393D89'
            });
        });
        //Demo
        layui.use('laydate', function(){
            var laydate = layui.laydate;

            //执行一个laydate实例
            laydate.render({
                elem: '#srtime' //指定元素
                ,theme: '#393D49'
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
    function fuzhi() {
        document.getElementById("q").value="刘世阳";
        document.getElementById("w").value="123456";
        document.getElementById("e").value="18";
        document.getElementById("r").value="99999999";
        document.getElementById("t").value="江西赣州";
        document.getElementById("y").value="汉族";
        document.getElementById("u").value="江西";
        document.getElementById("o").value="36073215112312";
        document.getElementById("p").value="江西省赣州市";
        document.getElementById("d").value="茂茂";
        document.getElementById("f").value="123456789";
        document.getElementById("g").value="186461651156";
        document.getElementById("h").value="茂茂";
        document.getElementById("j").value="傻逼刘世阳";
        document.getElementById("l").value="赣州技术学院";
        document.getElementById("x").value="赣州技术学院";
        document.getElementById("v").value="廖文汉";
    }
    layui.use('form', function(){
        var form = layui.form;
        //监听提交
        form.on('submit(formDemo)', function(data){
            layer.msg("成功添加");
            return true;
        });
    });

</script>

</body>
</html>
