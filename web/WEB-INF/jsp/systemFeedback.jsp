<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/12/18
  Time: 8:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>问题反馈</title>
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
        }
        //删除
        function  del(feedbackId) {
            layer.confirm('是否要删除？', {
                icon:3,
                btn: ['确认','取消'] //按钮
            }, function(){
                $.post("${pageContext.request.contextPath}/system/delFeedback",{feedbackId:feedbackId},
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
    <script  type="text/html" id="feedbackTime">
        {{ dateFormat(d.feedbackTime) }}
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
                ,url:'/system/feedbackdata'
                ,toolbar: '#toolbarDemo'
                ,height:400
                ,cols: [[
                    {field:'feedbackId', width:100, title: '编号', sort: true}
                    ,{field:'feedBackType', width:150, title: '职务'}
                    ,{field:'empname', width:150, title: '姓名'}
                    ,{field:'depId', width:200, title: '反馈班级'}
                    ,{field:'feedbackTime', width:250, title: '反馈时间',templet:"#feedbackTime"}
                    ,{field:'remark', width:240, title: '建议'}
                    ,{field:'status', width:150, title: '是否处理'}
                    ,{fixed: 'right',width:150, title: '操作', toolbar:'#barDemo' }
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
    <script type="text/html" id="barDemo">
        <a  href="${pageContext.request.contextPath}/system/issueDetails?feedbackId={{ d.feedbackId }}" class="layui-btn layui-btn-xs" >详情</a>
<%--        <a class="layui-btn layui-btn-xs" onclick="update('{{ d.feedbackId }}')">修改</a>--%>
        <a class="layui-btn layui-btn-danger layui-btn-xs" onclick="del('{{ d.feedbackId}}')">删除</a>
    </script>

</div>
<form  class="layui-form" id="addfloor" style="display: none" method="post" action="${pageContext.request.contextPath}/system/addFeedback">
    <div class="layui-form-item" style="margin-top: 20px;">
        <label class="layui-form-label" style="width: 100px;">反馈问题:</label>
        <div class="layui-input-inline">
<%--            <input id="remark" type="textarea" name="remark" required  lay-verify="required" autocomplete="off" class="layui-input">--%>
            <textarea id="remark" name="remark" type="text/plain" style="width:99%;height:100px"></textarea>
        </div>
    </div>
    <div align="center">
        <input type="submit" value="提交" style="height: 30px;width: 50px;">
    </div>
</form>
</body>
</html>
