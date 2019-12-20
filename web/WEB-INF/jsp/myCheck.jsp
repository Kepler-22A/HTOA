<%--
  Created by IntelliJ IDEA.
  User: 半夏VEZ
  Date: 2019/12/10
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的考评</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
    <fieldset class="layui-elem-field layui-field-title">
        <legend>选择查询的考评</legend>
    </fieldset>
    <form class="layui-form-item" method="post" id="myCheckForm">
        <label class="layui-form-label">考评模板号</label>
        <div class="layui-input-inline">
            <input type="text" name="templateId" autocomplete="off" placeholder="请输入查询的编号" class="layui-input">
        </div>

        <label class="layui-form-label">员工号</label>
        <div class="layui-input-inline">
            <input type="text" name="empId" autocomplete="off" placeholder="请输入查询的编号" class="layui-input">
        </div>
        <button class="layui-btn" lay-submit lay-filter="demoBtn">查询</button>
    </form>
    <table class="layui-hide" id="test" lay-filter="testTable"></table>
    <script>

        layui.use('form',function () {
            var form = layui.form;

            form.on('submit(demoBtn)',function (data) {
                alert("sfag");
                $.ajax({
                    url:'/Controller/selectMyCheck/from'
                    ,type:'POST'
                    ,data:$('#myCheckForm').serialize(),
                    success: function (result) {
                        layer.msg(result);
                        chaXun();
                    },
                    error : function() {
                        alert("异常！");
                    }
                });

                return false;
            })
        });


        function chaXun() {
            layer.msg("wgawerg");

            layui.use('table', function () {
                var table = layui.table;
                table.render({
                    elem: '#test'
                    , url: '/Controller/selectMyCheck/table'
                    , cols: [[
                        {field: 'checkScoerId', width: 100, title: '编号', sort: true}
                        , {field: 'projectName', width: 350, title: '考评内容'}
                        , {field: 'empName', width: 150, title: '考核人',}
                        , {
                            field: 'beginTime',
                            width: 200,
                            title: '考评时间',
                            templet: '<span>{{layui.util.toDateString(d.templateTime,"yyyy-MM-dd HH:mm:ss")}}</span>'
                        }
                        , {field: 'total', title: '总得分', minWidth: 100}
                        , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 150}
                    ]]
                    , page: true
                });
            });
        }
    </script>
</body>
</html>
