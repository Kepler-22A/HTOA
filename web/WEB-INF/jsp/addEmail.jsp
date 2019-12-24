<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/23
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发送邮件</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
</head>
<body>
<a id="backBtn" class="layui-btn layui-btn-xs" href="javascript:history.back()">返回</a>
<div style="border-bottom: 1px solid #ccc;margin: 10px 0"></div>
<form id="createEmailForm" lay-filter="createEmailForm" class="layui-form">
    <div class="layui-form-item">
        <label class="layui-form-label">标题</label>
        <div class="layui-input-inline" style="width: 200px">
            <input id="topic" class="layui-input" name="topic" type="text" lay-verify="topic" autocomplete="off" placeholder="请输入标题">
        </div>

        <label class="layui-form-label">接收人</label>
        <div class="layui-input-inline" style="width: 200px">
            <input id="receId" class="layui-input" name="receId" type="text" lay-verify="receId" autocomplete="off" readonly="readonly">
        </div>
        <div class="layui-input-inline">
            <a class="layui-btn layui-btn-danger" onclick="addReceName()">选择发送人</a>
        </div>
        <div class="layui-input-inline" style="width: 80px">
            <a class="layui-icon layui-btn" lay-submit lay-filter="emailForm">发送邮件</a>
        </div>
    </div>

<%--    <div class="layui-form-item">--%>
<%--        <label class="layui-form-label">附件</label>--%>
<%--        <div class="layui-input-inline" style="width: 80px">--%>
<%--            <input id="PDFile" type="file" name="PDFile">--%>
<%--        </div>--%>
<%--    </div>--%>


    <textarea id="demo" style="display: none;" lay-verify="context"></textarea>

</form>

<div id="receIdDiv" style="display: none">
    <div id="test1"></div>
    <p style="margin-bottom: 5px"></p>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a class="layui-btn layui-btn-xs" href="javascript:getReceId()">确定</a>
    <a class="layui-btn layui-btn-xs layui-btn-danger" href="javascript:closeReceIdDiv()">返回</a>
</div>

</body>
<script>
    function addReceName() {
        layer.open({
            type: 1,
            title:"选择接收人",
            area:['33%','63%'],
            content: $("#receIdDiv"),
            closeBtn :0, //隐藏弹出层的关闭按钮
            yes:function(index,layero){
            }
        });
    }

    function closeReceIdDiv() {
        layer.closeAll();
    }

    function getReceId(){
        var receNameStr = "";
        receIdStr = "";
        for (var i = 0;i < array.length;i++){
            if (i == array.length - 1){
                receIdStr += arrayId[i] + "";
                receNameStr += array[i] + "";
            } else {
                receIdStr += arrayId[i] + ",";
                receNameStr += array[i] + ",";
            }
        }

        $("#receId").val(receNameStr);

        closeReceIdDiv();
    }

    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;

        //富文本编辑器
        var layedit = layui.layedit;
        layedit.build('demo', {
            height: 900 //设置编辑器高度
        });
        var array = layedit.build('demo');

        //监听提交
        form.on('submit(emailForm)', function(data){
            if (layedit.getContent(array) == "" || layedit.getContent(array) == null){
                layer.msg("请输入内容");
            }else if($("#receId").val() == undefined ||$("#receId").val() == null || $("#receId").val() == ""){
                layer.msg("请输入接收人");
            }else if ($("#topic").val() == "" || $("#topic").val() == null){
                layer.msg("请输入标题");
            }else {
                var content = layedit.getContent(array);//获取文本框的数据
                var receId = arrayId;
                var topic = $("#topic").val();
                var empId = ${empId};
                var url = '${pageContext.request.contextPath}/message/addEmail/' + ${empId};

                $.ajax({
                    type:'post',
                    async:true,
                    url:url,
                    data:{
                        content:content,
                        receId:receIdStr,
                        topic:topic,
                        empId:empId
                    }
                    ,
                    success:function (data) {
                        if(data!=null){
                            layer.msg('发送成功！');
                            setTimeout(function () {
                                window.location.href="${pageContext.request.contextPath}/message/toEmailPage";
                            },2000);
                        }else {
                            layer.msg('发送失败！');
                            setTimeout(function () {
                                window.location.href="${pageContext.request.contextPath}/message/toEmailPage";
                            },2000);
                        }
                    }
                });
                return false;
            }
        });
    });

    var empData;

    $.ajax({
        url: '${pageContext.request.contextPath}/message/getEmpJSON'
        ,type: 'post'
        ,async: false
        ,dataType:'json'
        ,success:function (data) {
            empData = data;
        }
        ,error:function (data) {
        }
    });

    layui.use('transfer', function(){
        var transfer = layui.transfer;

        array = new Array();
        arrayId = new Array();

        //渲染
        transfer.render({
            elem: '#test1'  //绑定元素
            ,data: empData
            ,id: 'demo1' //定义索引
            ,showSearch: true
            ,onchange: function(data, index){
                // console.log(data); //得到当前被穿梭的数据
                // console.log(index); //如果数据来自左边，index 为 0，否则为 1

                if (index == 0 && index != null){
                    for (var i = 0;i < data.length;i++){
                        array.push(data[i].title);
                        arrayId.push(data[i].value);
                    }
                }else if (index == 1 && index != null){
                    for(var i = 0;i < array.length;i++){
                        var temp = array[i];
                        for (var j = 0;j < data.length;j++){
                            if (temp == data[j].title){
                                array.splice(i,1);
                                arrayId.splice(i,1);
                                i--;
                            }
                        }
                    }
                }
            }
        });

        $(".layui-transfer-data").css("height","269px");
    });
</script>
</html>
