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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <meta charset="utf-8">
    <script src="${pageContext.request.contextPath}/layui/layui.all.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%--    //弹出层表单~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~--%>
    <script type="text/javascript">
        function add() {
            layer.open({
                type: 1,
                title:"新增考评项目",
                area:['70%','70%'],
                content: $("#projectFrom"),
                closeBtn :0, //隐藏弹出层的关闭按钮
                yes:function(index,layero){
                }
            });
        }
        function add2() {
            layer.open({
                type: 1,
                title:"新增考评步骤",
                area:['70%','70%'],
                content: $("#projectFrom2"),
                closeBtn :0, //隐藏弹出层的关闭按钮
                yes:function(index,layero){
                }
            });
        }
        function add3() {
            layer.open({
                type: 1,
                title:"新增考评标准",
                area:['70%','70%'],
                content: $("#projectFrom3"),
                closeBtn :0, //隐藏弹出层的关闭按钮
                yes:function(index,layero){
                }
            });
        }
        // 项目提交~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        function fromTable12() {
            $.ajax({
                url:'/Controller/AddTemplate'
                ,dataType:'json'
                ,type:'POST'
                ,data:$('#template').serialize(),
                success: function (result) {
                    console.log(result);//打印服务端返回的数据(调试用)
                    if (result.resultCode = 200) {
                        layer.msg("成功添加");
                    };
                },
                error : function() {
                    alert("异常！");
                }
            });
        }//模板
        function fromTable() {
            $.ajax({
                url:'/Controller/addTemplate2/project'
                ,dataType:'json'
                ,type:'POST'
                ,data:$('#projectFrom').serialize(),
                success: function (result) {
                    console.log(result);//打印服务端返回的数据(调试用)
                    if (result.resultCode == 200) {
                        layer.msg("成功添加");
                    };
                },
                error : function() {
                    alert("异常！");
                }
            });
        } //项目
        function fromTable2() {
            $.ajax({
                url:'/Controller/addTemplate2/setp'
                ,dataType:'json'
                ,type:'POST'
                ,data:$('#projectFrom2').serialize(),
                success: function (result) {
                    console.log(result);//打印服务端返回的数据(调试用)
                    if (result.resultCode == 200) {
                        layer.msg("成功添加");
                    };
                },
                error : function() {
                    alert("请先添加考评模板在新增项目！");
                }
            });
        } //步骤
        function fromTable3() {
            $.ajax({
                url:'/Controller/addTemplate2/result'
                ,dataType:'json'
                ,type:'POST'
                ,data:$('#projectFrom3').serialize(),
                success: function (result) {
                    console.log(result);//打印服务端返回的数据(调试用)
                    if (result.resultCode == 200) {
                        layer.msg("成功添加");
                    };
                },
                error : function() {
                    alert("异常！");
                }
            });
        } //考评标准

        function guanbi() {
            layer.closeAll();
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title">
    <legend>新增模板</legend>
</fieldset>
<form class="layui-form layui-form-pane" id="template">
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
        <button type="button" class="layui-btn layui-btn-normal" id="LAY-component-form-getval">取值</button>
        <button class="layui-btn" onclick="fromTable12()">立即提交</button>
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
            <select name="total" lay-filter="type">
                <option value=""></option>
                <option value="10">10</option>
                <option value="30" >30</option>
                <option value="50">50</option>
                <option value="100">100</option>
            </select>
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
            <li class="layui-this">考评标准</li>
        </ul>
        <%--                //内容！！--%>
        <div class="layui-tab-content">
            <div class="layui-tab-item ">
                <button class="layui-btn layui-btn-sm"  onclick="add()">新增数据</button>
                <div id="testTable" lay-filter="testTable"></div>
                <script type="text/html" id="table_tool">
                    <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
                </script>
            </div>
            <div class="layui-tab-item ">
                <button class="layui-btn layui-btn-sm"  onclick="add2()">新增数据</button>
                <div id="testTable2" lay-filter="testTable"></div>
                <script type="text/html" id="table_tool2">
                    <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
                </script>
            </div>
            <div class="layui-tab-item ">
                <button class="layui-btn layui-btn-sm"  onclick="add3()">新增数据</button>
                <div id="testTable3" lay-filter="testTable"></div>
                <script type="text/html" id="table_tool3">
                    <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
                </script>
            </div>
        </div>
    </div>
</div>
<%--   项目表单--%>
<form  class="layui-form" id="projectFrom" style="display:none" method="post" >
    <div class="layui-form-item">
        <label class="layui-form-label">项目名称</label>
        <div class="layui-input-inline">
            <input type="text" name="projectName" autocomplete="off" placeholder="请输入内容" class="layui-input">
        </div>
        <label class="layui-form-label">最高分</label>
        <div class="layui-input-inline" style="height: 38px">
            <select name="maxScoer" lay-filter="type">
                <option value=""></option>
                <option value="10">10</option>
                <option value="30" >30</option>
                <option value="50">50</option>
                <option value="100">100</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">评分细则</label>
        <div class="layui-input-block">
            <input type="text" name="judgment" autocomplete="off" placeholder="请输入" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">说明</label>
        <div class="layui-input-block">
            <textarea name="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <button class="layui-btn layui-btn-normal" onclick="fromTable()" >保存</button>
        <button type="button" class="layui-btn layui-btn-normal" onclick="guanbi()">返回</button>
    </div>
</form>
<form class="layui-form" id="projectFrom2" style="display:none" method="post" >
    <hr>
    <div class="layui-form-item">
        <label class="layui-form-label">步骤名称</label>
        <div class="layui-input-inline">
            <input type="text" name="checkStepName" autocomplete="off" placeholder="请输入内容" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">步骤类型</label>
        <div class="layui-input-inline" style="height: 38px">
            <select name="checkStepType" lay-filter="type">
                <option value=""></option>
                <option value="自评">自评</option>
                <option value="学生评" >学生评</option>
                <option value="领导评">领导评</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">权重</label>
        <div class="layui-input-inline" style="height: 38px">
            <select name="weight" lay-filter="type">
                <option value=""></option>
                <option value="0.100">20%</option>
                <option value="0.200">30%</option>
                <option value="0.300" >50%</option>
                <option value="0.500">70%</option>
                <option value="0.500">80%</option>
                <option value="0.500">100%</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <button class="layui-btn layui-btn-normal" onclick="fromTable2()" >保存</button>
        <button type="button" class="layui-btn layui-btn-normal" onclick="guanbi()">返回</button>
    </div>
</form>
<form class="layui-form" id="projectFrom3" style="display:none" method="post">
    <hr>
    <div class="layui-form-item">
        <label class="layui-form-label">评级名称</label>
        <div class="layui-input-inline">
            <input type="text" name="grade" autocomplete="off" placeholder="请输入内容" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">最低分</label>
        <div class="layui-input-inline">
            <input type="text" name="min" autocomplete="off" placeholder="请输入内容" class="layui-input">
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">最高分</label>
            <div class="layui-input-inline">
                <input type="text" name="max" autocomplete="off" placeholder="请输入内容" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">评语</label>
        <div class="layui-input-block">
            <input type="text" name="remark" autocomplete="off" placeholder="请输入" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <button class="layui-btn layui-btn-normal" onclick="fromTable3()" >保存</button>
        <button type="button" class="layui-btn layui-btn-normal" onclick="guanbi()">返回</button>
    </div>
</form>
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
    });
    //项目部分！！
    // layui.use(['jquery', 'util', 'layer', 'table'], function () {
    //     var table  = layui.table;
    //     table.render(
    //         {   elem: '#testTable',
    //             height: 400,
    //             url: '/Controller/templateTable/table1',
    //             page: {},
    //             cols: [[
    //                 {field: 'projectName', edit: true, title: '项目名称'},
    //                 {field: 'maxScoer', edit: true, title: '最高分'},
    //                 {field: 'judgment', edit: true, title: '评分细则'},
    //                 {field: 'remark', edit: true, title: '备注'},
    //                 {toolbar: '#table_tool', title: '操作', fixed: 'right', align: 'center', width: 120}
    //             ]]
    //         },
    //     table.render( {
    //             elem: '#testTable2',
    //             height: 400,
    //            url:'/Controller/templateTable/table2',
    //             cols: [[
    //                 {field: 'checkStepName', edit: true, title: '名称'},
    //                 {field: 'step', edit: true, title: '步骤'},
    //                 {field: 'checkStepType', edit: true, title: '类型'},
    //                 {field: 'weight', edit: true, title: '权重'},
    //                 {field: 'checkTime', edit: true, title: '时间'},
    //                 {toolbar: '#table_tool2', title: '操作', fixed: 'right', align: 'center', width: 120}
    //             ]]
    //         }),
    //     table.render({
    //             elem: '#testTable3',
    //             height: 400,
    //             url:'/Controller/templateTable/table3',
    //             cols: [[
    //                 {field: 'name', edit: true, title: '等级名称'},
    //                 {field: 'min', edit: true, title: '最低分'},
    //                 {field: 'max', edit: true, title: '最高分'},
    //                 {field: 'name', edit: true, title: '评语'},
    //                 {toolbar: '#table_tool3', title: '操作', fixed: 'right', align: 'center', width: 120}
    //             ]]
    //         })
    //     );
    //     });
    // 工具监
    // switch (data.event) {
    //             case 'del':
    //                 var tableObj = tableIns;
    //                 var config = tableObj.config;
    //                 var dataTemp = config.data;
    //                 var page = config.page;
    //                 // 得到tr的data-index
    //                 var trElem = data.tr.first();
    //                 var index = trElem.data('index');
    //                 // 计算出在data中的index
    //                 var dataIndex = index + page.limit * (page.curr - 1);
    //                 // 删除对应下标的数据
    //                 dataTemp.splice(dataIndex, 1);
    //
    //                 // 新的页数
    //                 var pageNew = Math.ceil(dataTemp.length / page.limit);
    //
    //                 // 重新接收reload返回的对象，这个很重要
    //                 tableIns = table.reload(config.id, $.extend(true, {
    //                     // 更新数据
    //                     data: dataTemp
    //                 }, config.page ? {
    //                     // 如果删除了数据之后页数变了，打开前一页
    //                     page: {
    //                         curr: page.curr > pageNew ? ((page.curr - 1) || 1) : page.curr
    //                     }
    //                 } : {}));
    //                 break;
    //             default:
    //                 break;
    //         }
    //模板部分！！
    layui.use('form', function() {
        var form = layui.form;
        form.render();
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
    });
</script>
</body>
</html>
