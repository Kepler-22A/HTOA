<%--
  Created by IntelliJ IDEA.
  User: 半夏VEZ
  Date: 2019/12/10
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>考评模板</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
</head>
<body>
<div class="demoTable">
<%--    考评名称：--%>
<%--    <div class="layui-inline">--%>
<%--        <input class="layui-input" name="id" id="demoReload" autocomplete="off">--%>
<%--    </div>--%>
    <button class="layui-btn" onclick="rederr()">刷新</button>
    <button class="layui-btn"  onclick="addTab()" style="cursor:pointer">添加</button>
</div>
<table class="layui-hide" id="test" lay-filter="testTable"></table>
<fieldset class="layui-elem-field layui-field-title">
    <legend>查看项目</legend>
    <div >
        <div class="layui-tab">
            <%--                标题！！--%>
            <ul class="layui-tab-title">
                <li class="layui-this">考核项目</li>
                <li class="layui-this">考核步骤</li>
                <li class="layui-this">考评标准</li>
            </ul>
            <%--                //内容！！--%>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <div id="testTable" lay-filter="testTable"></div>
                </div>
                <div class="layui-tab-item ">
                    <div id="testTable2" lay-filter="testTable"></div>
                </div>
                <div class="layui-tab-item ">
                    <div id="testTable3" lay-filter="testTable"></div>
                </div>
            </div>
        </div>
    </div>
</fieldset>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" onclick="del('{{d.templateId}}')">删除</a>
</script>

<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    //tab
    rederr();
    layui.use('element',function() {
        var $ = layui.jquery
            ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
        function FrameWH() {
            var h = $(window).height() -41- 10 - 60 -10-44 -10;
            $("iframe").css("height",h+"px");
        }
    });
    function del(templateId) {
        console.log(templateId);
        layer.confirm('是否要删除？', {
            icon:3,
            btn: ['确认','取消'] //按钮
        }, function(){
            $.post('<%=request.getContextPath()%>/Controller/delete3/'+templateId,{},
                function (data) {
                    rederr();
                });
            layer.msg('已删除', {
                icon: 1,
                time:2000
            });
        }, function(){
            layer.msg('已取消', {
                icon:0,
                time: 2000 //20s后自动关闭
            });
        });
    };
    //加载表格数据
    function rederr() {
        layui.use('table', function () {
            var table = layui.table;
            table.render(
                {
                    elem: '#test'
                    , url: '/Controller/table3'
                    , cols: [[
                        {field: 'templateId', width: 100, title: '模板Id'}
                        , {field: 'templateName', width: 150, title: '考核名称'}
                        , {field: 'templateType', width: 150, title: '考核类型'}
                        , {field: 'empName', width: 150, title: '创建人'}
                        , {field: 'templateTime', width: 200, title: '创建日期', templet: '<span>{{layui.util.toDateString(d.templateTime,"yyyy-MM-dd HH:mm:ss")}}</span>'}
                        , {field: 'remark', title: '备注', Width: 350}
                        , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 200}
                    ]]
                    , page: true
                }
            );
            //监听行单击事件
            table.on('row(testTable)', function (obj) {
                console.log(obj.tr) //得到当前行元素对象
                console.log(obj.data) //得到当前行数据
                reloadTable(obj.data.templateTime);
            });
        });
    }
    function reloadTable(templateTime) {
        layui.use('table', function() {
            var table = layui.table;
            table.render(
                {   elem: '#testTable',
                    height: 400,
                    url: '/Controller/templateTable/table1/'+templateTime,
                    cols: [[
                        {field: 'projectId', edit: true, title: '项目编号'},
                        {field: 'projectName', edit: true, title: '项目名称'},
                        {field: 'maxScoer', edit: true, title: '最高分'},
                        {field: 'judgment', edit: true, title: '评分细则'},
                        {field: 'remark', edit: true, title: '备注'},
                        {toolbar: '#table_tool', title: '操作', fixed: 'right', align: 'center', width: 120}
                    ]]
                }
            );
            table.render(
                {
                    elem: '#testTable2',
                    height: 400,
                    url:'/Controller/templateTable/table2/'+templateTime,
                    cols: [[
                        {field: 'checkStepName', edit: true, title: '名称'},
                        {field: 'step', edit: true, title: '步骤'},
                        {field: 'checkStepType', edit: true, title: '类型'},
                        {field: 'weight', edit: true, title: '权重'},
                        {field: 'beginTime', edit: true, title: '开始时间',templet : '<span>{{layui.util.toDateString(d.beginTime,"yyyy-MM-dd HH:mm:ss")}}</span>'},
                        {field: 'endTime', edit: true, title: '结束时间',templet : '<span>{{layui.util.toDateString(d.endTime,"yyyy-MM-dd HH:mm:ss")}}</span>'},
                        {toolbar: '#table_tool2', title: '操作', fixed: 'right', align: 'center', width: 120}
                    ]]
                }
            );
            table.render(
                {
                    elem: '#testTable3',
                    height: 400,
                    url:'/Controller/templateTable/table3/'+templateTime,
                    cols: [[
                        {field: 'grade', edit: true, title: '等级名称'},
                        {field: 'min', edit: true, title: '最低分'},
                        {field: 'max', edit: true, title: '最高分'},
                        {field: 'remark', edit: true, title: '评语'},
                        {toolbar: '#table_tool3', title: '操作', fixed: 'right', align: 'center', width: 120}
                    ]]
                }
            );
        });
    }
    //新增tab页
    function addTab() {
        parent.active.tabAdd("/Controller/addTemplate", 46, "新增考评");
        parent.active.tabChange(46)
    }

</script>
</body>
</html>
