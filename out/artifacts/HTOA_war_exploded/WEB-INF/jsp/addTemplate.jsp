<%--
  Created by IntelliJ IDEA.
  User: 半夏VEZ
  Date: 2019/12/11
  Time: 8:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>项目模板管理</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>
    <fieldset class="layui-elem-field layui-field-title">
        <legend>新增模板</legend>
    </fieldset>
    <form class="layui-form layui-form-pane" lay-filter="example" action="${pageContext.request.contextPath}/Controller/AddTemplate">
        <div class="layui-form-item">
            <label class="layui-form-label">模板名称</label>
            <div class="layui-input-inline">
                <input type="text" name="templateName" autocomplete="off" placeholder="请输入内容" class="layui-input">
            </div>
            <label class="layui-form-label">考核类型</label>
            <div class="layui-input-inline" style="height: 38px">
                <select name="templateType" lay-filter="type">
                    <option value=""></option>
                    <option value="月度考评">月度考评</option>
                    <option value="季度考评" >季度考评</option>
                    <option value="年中考评">年中考评</option>
                    <option value="年度考评">年度考评</option>
                </select>
            </div>
            <button type="button" class="layui-btn layui-btn-normal" id="LAY-component-form-setval">赋值</button>
            <button type="submit" class="layui-btn"  lay-filter="demo1">保存</button>
        </div>
        <div class="layui-form-item" >
            <label class="layui-form-label">考核部门</label>
            <div class="layui-input-inline" style="height: 38px">
                <select name="depId" lay-filter="type">
                    <option value=""></option>
                    <option value="2">教研部</option>
                    <option value="3" >学工部</option>
                    <option value="1004">行政部</option>
                    <option value="1006">后勤部</option>
                </select>
            </div>
            <label class="layui-form-label">总分：</label>
            <div class="layui-input-inline">
                <input type="text" name="total" autocomplete="off" readonly="readonly" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <input type="text" name="remark" autocomplete="off" placeholder="请输入" class="layui-input">
            </div>
        </div>
    </form>

    <fieldset class="layui-elem-field layui-field-title">
        <legend>设置项目</legend>
    </fieldset>
        <div >
            <div class="layui-tab">
<%--                标题！！--%>
                <ul class="layui-tab-title">
                    <li class="layui-this">考核项目</li>
                    <li class="layui-this">考核步骤</li>
                    <li class="layui-this">等级标准</li>
                    <li class="layui-this">评语</li>
                </ul>
<%--                //内容！！--%>
                <div class="layui-tab-content">
                    <div class="layui-tab-item ">
                        <button class="layui-btn" id="createNew">新增一条数据</button>
                        <button class="layui-btn" id="getAllData">保存数据</button>
                        <div id="testTable" lay-filter="testTable"></div>
                        <script type="text/html" id="table_tool">
                            <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
                        </script>
                    </div>
                    <div class="layui-tab-item ">
                        <button class="layui-btn" id="createNew2">新增一条数据</button>
                        <div id="testTable2" lay-filter="testTable"></div>
                        <script type="text/html" id="table_tool2">
                            <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
                        </script>
                    </div>
                    <div class="layui-tab-item ">
                        <button class="layui-btn" id="createNew3">新增一条数据</button>
                        <div id="testTable3" lay-filter="testTable"></div>
                        <script type="text/html" id="table_tool3">
                            <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
                        </script>
                    </div>
                    <div class="layui-tab-item ">
                        <button class="layui-btn" id="createNew4">新增一条数据</button>
                        <div id="testTable4" lay-filter="testTable"></div>
                        <script type="text/html" id="table_tool4">
                            <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
                        </script>
                    </div>
                </div>
            </div>
        </div>
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    //tab
    layui.use('element',function() {
        var $ = layui.jquery
            ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
        function FrameWH() {
            var h = $(window).height() -41- 10 - 60 -10-44 -10;
            $("iframe").css("height",h+"px");
        }
        //关闭tab页
        // $(window).resize(function () {
        //     FrameWH();
        // })
    });
    layui.use('form', function(){
        var form = layui.form;
        form.render();
    });
    //新增模板，项目
    layui.use(['jquery', 'laydate', 'util', 'layer', 'table', 'form'], function ($, laydate, util, layer, table, form) {
        var tableIns = table.render({
            elem: '#testTable',
            height: 400,
            data: [],
            page: {},
            cols: [[
                {field: 'name', edit: true, title: '项目名称'},
                {field: 'max', edit: true, title: '最高分'},
                {field: 'text', edit: true, title: '评分细则'},
                {field: 'remark', edit: true, title: '备注'},
                {toolbar: '#table_tool', title: '操作', fixed: 'right', align: 'center', width: 120}
            ]]
        });
        var tableIns2 = table.render({
            elem: '#testTable2',
            height: 400,
            data: [],
            page: {},
            cols: [[
                {field: 'checkStepName', edit: true, title: '名称'},
                {field: 'step', edit: true, title: '步骤'},
                {field: 'checkStepType', edit: true, title: '类型'},
                {field: 'weight', edit: true, title: '权重'},
                {field: 'checkTime', edit: true, title: '时间'},
                {toolbar: '#table_tool2', title: '操作', fixed: 'right', align: 'center', width: 120}
            ]]
        });
        var tableIns3 = table.render({
            elem: '#testTable3',
            height: 400,
            data: [],
            page: {},
            cols: [[
                {field: 'name', edit: true, title: '等级名称'},
                {field: 'min', edit: true, title: '最低分'},
                {field: 'max', edit: true, title: '最高分'},
                {field: 'order', edit: true, title: '顺序'},
                {toolbar: '#table_tool3', title: '操作', fixed: 'right', align: 'center', width: 120}
            ]]
        });
        var tableIns4 = table.render({
            elem: '#testTable4',
            height: 400,
            data: [],
            page: {},
            cols: [[
                {field: 'name', edit: true, title: '评语'},
                {toolbar: '#table_tool4', title: '操作', fixed: 'right', align: 'center', width: 120}
            ]]
        });

        // 工具监听
        table.on('tool(testTable)', function (data) {
            switch (data.event) {
                case 'del':
                    // data.del();
                    // layer.confirm('玩玩而已不用担心一会还会回来的。', function(index){
                    //     obj.del();
                    //     layer.close(index);
                    // });
                    // 只做测试数据的删除用，url的删除一般都是发交易然后重载表格就可以了
                    var tableObj = tableIns;
                    var config = tableObj.config;
                    var dataTemp = config.data;
                    var page = config.page;
                    // 得到tr的data-index
                    var trElem = data.tr.first();
                    var index = trElem.data('index');
                    // 计算出在data中的index
                    var dataIndex = index + page.limit * (page.curr - 1);
                    // 删除对应下标的数据
                    dataTemp.splice(dataIndex, 1);

                    // 新的页数
                    var pageNew = Math.ceil(dataTemp.length / page.limit);

                    // 重新接收reload返回的对象，这个很重要
                    tableIns = table.reload(config.id, $.extend(true, {
                        // 更新数据
                        data: dataTemp
                    }, config.page ? {
                        // 如果删除了数据之后页数变了，打开前一页
                        page: {
                            curr: page.curr > pageNew ? ((page.curr - 1) || 1) : page.curr
                        }
                    } : {}));
                    break;
                default:
                    break;
            }
        });
        table.on('tool(testTable)', function (data) {
            switch (data.event) {
                case 'del':
                    // data.del();
                    // layer.confirm('玩玩而已不用担心一会还会回来的。', function(index){
                    //     obj.del();
                    //     layer.close(index);
                    // });
                    // 只做测试数据的删除用，url的删除一般都是发交易然后重载表格就可以了
                    var tableObj = tableIns2;
                    var config = tableObj.config;
                    var dataTemp = config.data;
                    var page = config.page;
                    // 得到tr的data-index
                    var trElem = data.tr.first();
                    var index = trElem.data('index');
                    // 计算出在data中的index
                    var dataIndex = index + page.limit * (page.curr - 1);
                    // 删除对应下标的数据
                    dataTemp.splice(dataIndex, 1);

                    // 新的页数
                    var pageNew = Math.ceil(dataTemp.length / page.limit);

                    // 重新接收reload返回的对象，这个很重要
                    tableIns2 = table.reload(config.id, $.extend(true, {
                        // 更新数据
                        data: dataTemp
                    }, config.page ? {
                        // 如果删除了数据之后页数变了，打开前一页
                        page: {
                            curr: page.curr > pageNew ? ((page.curr - 1) || 1) : page.curr
                        }
                    } : {}));
                    break;
                default:
                    break;
            }
        });
        table.on('tool(testTable)', function (data) {
            switch (data.event) {
                case 'del':
                    // data.del();
                    // layer.confirm('玩玩而已不用担心一会还会回来的。', function(index){
                    //     obj.del();
                    //     layer.close(index);
                    // });
                    // 只做测试数据的删除用，url的删除一般都是发交易然后重载表格就可以了
                    var tableObj = tableIns3;
                    var config = tableObj.config;
                    var dataTemp = config.data;
                    var page = config.page;
                    // 得到tr的data-index
                    var trElem = data.tr.first();
                    var index = trElem.data('index');
                    // 计算出在data中的index
                    var dataIndex = index + page.limit * (page.curr - 1);
                    // 删除对应下标的数据
                    dataTemp.splice(dataIndex, 1);

                    // 新的页数
                    var pageNew = Math.ceil(dataTemp.length / page.limit);

                    // 重新接收reload返回的对象，这个很重要
                    tableIns3 = table.reload(config.id, $.extend(true, {
                        // 更新数据
                        data: dataTemp
                    }, config.page ? {
                        // 如果删除了数据之后页数变了，打开前一页
                        page: {
                            curr: page.curr > pageNew ? ((page.curr - 1) || 1) : page.curr
                        }
                    } : {}));
                    break;
                default:
                    break;
            }
        });
        table.on('tool(testTable)', function (data) {
            switch (data.event) {
                case 'del':
                    // data.del();
                    // layer.confirm('玩玩而已不用担心一会还会回来的。', function(index){
                    //     obj.del();
                    //     layer.close(index);
                    // });
                    // 只做测试数据的删除用，url的删除一般都是发交易然后重载表格就可以了
                    var tableObj = tableIns4;
                    var config = tableObj.config;
                    var dataTemp = config.data;
                    var page = config.page;
                    // 得到tr的data-index
                    var trElem = data.tr.first();
                    var index = trElem.data('index');
                    // 计算出在data中的index
                    var dataIndex = index + page.limit * (page.curr - 1);
                    // 删除对应下标的数据
                    dataTemp.splice(dataIndex, 1);

                    // 新的页数
                    var pageNew = Math.ceil(dataTemp.length / page.limit);

                    // 重新接收reload返回的对象，这个很重要
                    tableIns4 = table.reload(config.id, $.extend(true, {
                        // 更新数据
                        data: dataTemp
                    }, config.page ? {
                        // 如果删除了数据之后页数变了，打开前一页
                        page: {
                            curr: page.curr > pageNew ? ((page.curr - 1) || 1) : page.curr
                        }
                    } : {}));
                    break;
                default:
                    break;
            }
        });

        // 新增一条记录
        $('#createNew').click(function () {
            // debugger;
            var tableObj = tableIns;
            var config = tableObj.config;
            var dataTemp = config.data;
            dataTemp.push({});
            tableIns = table.reload(config.id, $.extend(true, {
                // 更新数据
                data: dataTemp,
            }, config.page ? {
                // 一般新增都是加到最后，所以始终打开最后一页
                page: {
                    curr: Math.ceil(dataTemp.length / config.page.limit)
                }
            } : {}));
        });
        $('#createNew2').click(function () {
            // debugger;
            var tableObj = tableIns2;
            var config = tableObj.config;
            var dataTemp = config.data;
            dataTemp.push({});
            tableIns2 = table.reload(config.id, $.extend(true, {
                // 更新数据
                data: dataTemp,
            }, config.page ? {
                // 一般新增都是加到最后，所以始终打开最后一页
                page: {
                    curr: Math.ceil(dataTemp.length / config.page.limit)
                }
            } : {}));
        });
        $('#createNew3').click(function () {
            // debugger;
            var tableObj = tableIns3;
            var config = tableObj.config;
            var dataTemp = config.data;
            dataTemp.push({});
            tableIns3 = table.reload(config.id, $.extend(true, {
                // 更新数据
                data: dataTemp,
            }, config.page ? {
                // 一般新增都是加到最后，所以始终打开最后一页
                page: {
                    curr: Math.ceil(dataTemp.length / config.page.limit)
                }
            } : {}));
        });
        $('#createNew4').click(function () {
            // debugger;
            var tableObj = tableIns4;
            var config = tableObj.config;
            var dataTemp = config.data;
            dataTemp.push({});
            tableIns4 = table.reload(config.id, $.extend(true, {
                // 更新数据
                data: dataTemp,
            }, config.page ? {
                // 一般新增都是加到最后，所以始终打开最后一页
                page: {
                    curr: Math.ceil(dataTemp.length / config.page.limit)
                }
            } : {}));
        });

        // 获得表格的所有数据
        $('#getAllData').click(function () {
            layer.alert(JSON.stringify(tableIns.config.data));
        });
        $('#getAllData2').click(function () {
            layer.alert(JSON.stringify(tableIns2.config.data));
        });
        $('#getAllData2').click(function () {
            layer.alert(JSON.stringify(tableIns3.config.data));
        });
        $('#getAllData2').click(function () {
            layer.alert(JSON.stringify(tableIns4.config.data));
        });
    });
    layui.use(['form', 'layedit', 'laydate'], function() {
        var form = layui.form
        //表单赋值
        layui.$('#LAY-component-form-setval').on('click', function(){
            form.val('example', {
                "templateName": "月度考评"
                ,"templateType": "月度考评"
                ,"depId": 2
                ,"total": 100
               ,"remark":"日常考评"
            });
        });
        // //表单取值
        // layui.$('#LAY-component-form-getval').on('click', function(){
        //     var data = form.val('example');
        //     alert(JSON.stringify(data));
        // });

        //监听提交
        form.on('submit(demo1)', function(data){
            layer.alert(JSON.stringify(data.field), {
                url:'/Controller/addTemplate',
                title: '最终的提交信息'
            })
            return false;
        });
    });
</script>
</body>
</html>
