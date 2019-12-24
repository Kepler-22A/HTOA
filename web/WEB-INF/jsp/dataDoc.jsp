<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/19
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>资料文档</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/layui/lay/modules/layer.js" charset="utf-8"></script>
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
    <script  type="text/html" id="optime">
        {{ dateFormat(d.optime) }}
    </script>
    <style type="text/css">
        .layui-btn-llb{
            height: 22px;
            padding: 6px 5px;
            font-size: 12px;
        }
        .layui-btn-llbb{
            color: #fff;
            border-radius: 2px;
            cursor: pointer;
        }
    </style>
</head>

<body>

<div class="layui-form">
    <table class="layui-table" align="center" id="Table"  lay-filter="fTab"></table>
    </tbody>
</div>
<script type="text/html" id="toolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="addFile">添加文件</button>
    </div>
</script>
<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-xs" lay-event="getFile">下载文件</a>
</script>

<div id="addFile" style="display: none;padding: 20px 35px 20px 0px;">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">选择文件</label>
            <div class="layui-input-inline">
                <button type="button" class="layui-btn" id="upload">
                    <i class="layui-icon">&#xe67c;</i>上传文件
                </button>
            </div>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">说明</label>
        <div class="layui-input-block">
            <textarea id="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-input-block" style="margin-left: 208px;">
        <button type="submit" class="layui-btn" id="sub">确定添加</button>
    </div>
</div>
</body>
<script>
    layui.use(['table','upload'], function() {
        var table = layui.table;
        var upload = layui.upload;

        //执行实例
        var uploadInst = upload.render({
            elem: '#upload', //绑定元素
            //url: '/upload/', //上传接口
            size:10240,
            auto:false,
            accept: 'file',
            done: function(res){
                //上传完毕回调
            },
            error: function(){
                //请求异常回调
            }
        });

        $('#sub').click(function () {
            var file = $('.layui-upload-file')[0].files[0];
            var remark = $('#remark').val().trim();
            if (file == null) {
                layer.msg('请选择文件',{
                    icon:0
                });
                return;
            }
            if (remark == ""){
                layer.msg('请填写说明',{
                    icon:0
                });
                return;
            }
            var lod = layer.load();
            var formData = new FormData();
            formData.append('file',file);
            formData.append('remark',remark);
            $.ajax({
                type:'post',
                url:'${pageContext.request.contextPath}/data/addFile',
                processData: false,//jquery 是否对数据进行 预处理
                contentType: false,//不要自己修改请求内容类型
                async:true,
                dataType:'json',
                data:formData,
                success:function (data) {
                    layer.close(lod);
                    layer.msg('上传成功',{
                        icon:1,
                        time:2000
                    },function(){
                        location.reload();
                    });
                },
                error:function () {
                    layer.close(lod);
                    layer.msg('服务器错误，请稍后再试！',{
                        icon:2
                    });
                }
            });

        });

        table.render({
            elem: '#Table'
            , url: '/data/Alldata'
            , toolbar: '#toolbar'
            , height: 400
            , cols: [[
                {field: 'docId', width: 100, title: '编号', sort: true}
                , {field: 'dataName', width: 250, title: '资料名称'}
                , {field: 'optime', width: 250, title: '上传时间', templet: "#optime"}
                , {field: 'remark', width: 240, title: '备注'}
                , {field: 'empName', width: 150, title: '上传人'}
                , {fixed: 'right', width: 150, title: '操作', toolbar: '#bar'}
            ]]
            , page: true
        });
        //头工具栏事件
        table.on('toolbar(fTab)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            var length = checkStatus.data.length;
            switch(obj.event){
                case 'addFile':
                    layer.open({
                        title:'添加文件',
                        type:1,
                        content:$('#addFile'),
                        area: ['500px', '350px'],
                        resize:false,
                        // maxmin:true
                    });
                    break;

                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('点击数据即可修改');
                    break;
            };
        });
        //监听行工具事件
        table.on('tool(fTab)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('确认删除此文件吗', function(index){
                    var lod = layer.load();
                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        url: "${pageContext.request.contextPath}/data/delFile" ,
                        data: {docId:data.docId},
                        success: function (result) {
                            layer.close(lod);
                            layer.msg('删除成功',{
                                icon:1,
                                time:1000
                            },function () {
                                window.location.reload();
                            });
                        },
                        error : function() {
                            layer.close(lod);
                            layer.msg('服务器错误',{
                                icon:2
                            });
                        }
                    });
                });
            } else if(obj.event === 'getFile'){
                location.href='${pageContext.request.contextPath}/data/download.do?docId='+data.docId;
            }


        //
        var $ = layui.jquery
            , upload = layui.upload;

        //指定允许上传的文件类型
        upload.render({
            elem: '#test3'
            , url: '/upload/'
            , accept: 'file' //普通文件
            , done: function (res) {
                console.log(res)
            }
        });
     });
    })
</script>
</html>


