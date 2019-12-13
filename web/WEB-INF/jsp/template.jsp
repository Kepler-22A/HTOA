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
</head>
<body>
    <div class="demoTable">
        考评名称：
    <div class="layui-inline">
        <input class="layui-input" name="id" id="demoReload" autocomplete="off">
    </div>
    <button class="layui-btn" data-type="reload">搜索</button>
        <button class="layui-btn"  onclick="add()" style="cursor:pointer">添加</button>
</div>
    <table class="layui-hide" id="test" lay-filter="testTable"></table>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="update">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>

    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
    <script>
        //加载表格数据
        layui.use('table', function(){
            var table = layui.table;
            table.render({
                elem: '#test'
                ,url:'/Controller/table3'
                ,cols: [[
                    {field:'templateName', width:200, title: '考核名称'}
                    ,{field:'templateType', width:200, title: '考核类型'}
                    ,{field:'empName', width:150, title: '创建人'}
                    ,{field:'templateTime', width:250, title: '创建日期'}
                    ,{field:'remark', title: '备注', Width: 350}
                    ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:200}
                ]]
                ,id: 'testReload'
                ,page: true
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
            // 搜索
            var $ = layui.$, active = {
                reload: function(){
                    var demoReload = $('#demoReload');

                    //执行重载
                    table.reload('testReload', {
                        page: {
                            curr: 1 //重新从第 1 页开始
                        }
                        ,where: {
                            key: {
                                id: demoReload.val()
                            }
                        }
                    }, 'data');
                }
            };
            $('.demoTable .layui-btn').on('click', function(){
                var type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
            });
        });
        //新增tab页
        function add() {
                parent.active.tabAdd("/Controller/addTemplate", 46, "新增考评");
                parent.active.tabChange(46)
        }
    </script>
</body>
</html>
