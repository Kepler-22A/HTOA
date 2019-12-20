<%--
  Created by IntelliJ IDEA.
  User: 28401
  Date: 2019/12/18
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增公告</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <meta charset="utf-8">
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <script type="text/javascript">

    </script>
</head>
<body>
    <form class="layui-form">
        <div>
            <div class="layui-form-item" style="display: inline-block">
                <label class="layui-form-label">标题</label>
                <div class="layui-input-block" style="width: 200px">
                    <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input" id="title">
                </div>
            </div>
            <div class="layui-form-item" style="display: inline-block">
                <label class="layui-form-label">类别</label>
                <div class="layui-input-block"  style="width: 200px">
                    <select name="noticeType" lay-filter="noticeType" id="noticeType">
                        <option value="3" selected="">全体学生</option>
                        <option value="2">全体员工</option>
                        <option value="4">班级发送</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item" style="display: inline-block;" id="class">
                <label class="layui-form-label">班级</label>
                <div class="layui-input-block"  style="width: 200px">
                    <select name="clazz" lay-filter="aihao" id="clazz">
                        <option value="">请选择班级</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item" style="display: inline-block;margin-left: 20px">
                <i class="layui-icon" lay-submit style="font-size: 20px;" title="发布一个公告吧！">&#xe61f;</i>
                <a href="${pageContext.request.contextPath}/message/notice"><i class="layui-icon" style="font-size: 20px;margin-left: 15px;" title="返回">&#xe65c;</i></a>
            </div>
            <textarea id="demo" style="display: none;"></textarea>
        </div>

    </form>
</body>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    $("#class").hide()//隐藏
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;
        form.on('select',function (data) {
            var sss = $("#noticeType").val();
            if(sss == 4){
              $("#class").show()//显示
            }else {
                $("#class").hide()//隐藏
                $("#clazz").val('')//清空
            }
        })
        //富文本编辑器
        var layedit = layui.layedit;
        layedit.build('demo', {
            height: 600 //设置编辑器高度
        });
        var array = layedit.build('demo');

        //监听提交
        form.on('submit', function(data){
            var A = JSON.stringify(data.field);//选项框的数据
            var aa = layedit.getContent(array);//获取文本框的数据
            var DADA = document.getElementById("title")
            var title = DADA.value //标题
            var DADB = document.getElementById("noticeType")
            var noticeType = DADB.value //类别
            var DADC = document.getElementById("clazz")
            var clazz = DADC.value //班级
            var  bb = aa;
            // for (i = 0; i < bb.length; i++) {
            //     bb = bb.replace("/","&bbbsb;");
            // }
            var empid = <%=session.getAttribute("empId")%>
            var url = '';
            if(clazz != null && "" != clazz){
                alert("有班级")
                url = '${pageContext.request.contextPath}/message/addNoticeOK/'+title+'/'+noticeType+'/'+clazz+'/'+empid;
            }else {
                alert("没有班级")
                url = '${pageContext.request.contextPath}/message/addNoticeOK2/'+title+'/'+noticeType+'/'+empid;
            }
            $.ajax({
                type:'post',
                async:true,
                url:url,
                data:{
                    context:bb,
                },
                success:function (data) {
                    if(data!=null){
                        layer.msg('保存成功');
                        window.location.href="${pageContext.request.contextPath}/message/notice"
                    }else {
                        layer.msg('保存失败');
                    }
                }
            });
            return false;
        });
    });
    //异步查询出班级
    $(document).ready(function () {
        $.post("${pageContext.request.contextPath}/student/studentClassAjax",{},function (data) {
            var t = $("#clazz").html();
            $.each(data,function (index,obj) {
                $.each(obj,function (i,o) {
                    t += "<option value='"+o.classid+"'>"+o.className+"</option>";
                })
            });
            $("#clazz").html(t);
            layui.use(['form'], function(){
                var form = layui.form;
                form.render('select');
            });
        },'json');
    });

</script>
</html>
