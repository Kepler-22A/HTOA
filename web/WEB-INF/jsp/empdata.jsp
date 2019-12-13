<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/12/4
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工资料</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <script>
    </script>
</head>
<body>

<%--员工资料表部分--%>

<div class="layui-form">
    <div style="width: 1365px;margin: 0 auto">

        <%--    员工表    --%>
        <table id="demo" lay-filter="test"></table>

        <%--员工添加&修改的表单--%>
        <form id="empDataForm" style="display: none" class="layui-form" action="" method="post">
            <div class="layui-form-item">
                <input id="empId" type="hidden" name="empId" value="0">
                <input id="remark" type="hidden" name="remark" value="xxx">
                <input id="Bank" type="hidden" name="Bank" value="中国工商">
                <input id="accountName" type="hidden" name="accountName" value="99999999999">
                <input id="bankNumber" type="hidden" name="bankNumber" value="999999999999">
                <input id="alipay" type="hidden" name="alipay" value="99999999">
                <input id="password" type="hidden" name="password" value="123456">
                <input id="postId" type="hidden" name="postId" value="3">
                <input id="status" type="hidden" name="status" value="1">
                <input id="nation" type="hidden" name="nation" value="xxx">
                <label class="layui-form-label">员工姓名</label>
                <div class="layui-input-inline">
                    <input id="empName" type="text" name="empName" lay-verify="empName" autocomplete="off" placeholder="请输入员工姓名" class="layui-input">
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">部门名称</label>
                    <div class="layui-input-inline">
                        <select id="depId" name="depId">
                            <option value="1">宏图</option>
                            <option value="2">教研部</option>
                            <option value="3">学工部</option>
                            <option value="1004">行政部</option>
                            <option value="1006">后勤部</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">职务名称</label>
                <div class="layui-input-inline">
                    <%--            <input id="postName" type="text" name="postName" lay-verify="postName" autocomplete="off" placeholder="请输入职务名称" class="layui-input">--%>
                    <select id="postName" name="postName">

                    </select>
                </div>

                <label class="layui-form-label">家庭住址</label>
                <div class="layui-input-inline">
                    <input id="Address" type="text" name="Address" lay-verify="Address" autocomplete="off" placeholder="请输入家庭住址" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-inline">
                    <select id="sex" name="sex">
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                </div>

                <label class="layui-form-label">身份证号</label>
                <div class="layui-input-inline">
                    <input id="Cardno" type="text" name="Cardno" lay-verify="Cardno" autocomplete="off" placeholder="请输入身份证号" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item layui-form">
                <label class="layui-form-label">出生日期</label>
                <div class="layui-input-inline">
                    <input id="BirthdayEX" type="date" name="BirthdayEX" lay-verify="title" autocomplete="off" placeholder="出生日期" class="layui-input">
                </div>

                <label class="layui-form-label">籍贯</label>
                <div class="layui-input-inline" style="width: 85px">
                    <select lay-filter="nation_1" id="nation_1" name="nation_1">

                    </select>
                </div>
                <div class="layui-input-inline" style="width: 85px">
                    <select lay-filter="nation_2" id="nation_2" name="nation_2">

                    </select>
                </div>
                <div class="layui-input-inline" style="width: 85px">
                    <select id="nation_3" name="nation_3">

                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">电话号码</label>
                <div class="layui-input-inline">
                    <input id="Phone" type="text" name="Phone" lay-verify="Phone" autocomplete="off" placeholder="请输入电话号码" class="layui-input">
                </div>

                <label class="layui-form-label">QQ号码</label>
                <div class="layui-input-inline">
                    <input id="QQcode" type="text" name="QQcode" lay-verify="QQcode" autocomplete="off" placeholder="请输入QQ号码" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">微信号码</label>
                <div class="layui-input-inline">
                    <input id="Weixin" type="text" name="Weixin" lay-verify="Weixin" autocomplete="off" placeholder="请输入微信号码" class="layui-input">
                </div>

                <label class="layui-form-label">邮箱地址</label>
                <div class="layui-input-inline">
                    <input id="Email" type="text" name="Email" lay-verify="Email" autocomplete="off" placeholder="请输入邮箱地址" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">婚姻状况</label>
                <div class="layui-input-inline">
                    <select id="married" name="married">
                        <option value="">----未选择----</option>
                        <option value="已婚">已婚</option>
                        <option value="未婚">未婚</option>
                        <option value="离异">离异</option>
                        <option value="丧偶">丧偶</option>
                    </select>
                </div>

                <label class="layui-form-label">毕业学校</label>
                <div class="layui-input-inline">
                    <input id="University" type="text" name="University" lay-verify="University" autocomplete="off" placeholder="请输入毕业学校" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">专业</label>
                <div class="layui-input-inline">
                    <input id="Major" type="text" name="Major" lay-verify="Major" autocomplete="off" placeholder="请输入您的专业" class="layui-input">
                </div>

                <label class="layui-form-label">学历</label>
                <div class="layui-input-inline">
                    <select id="Education" name="Education">
                        <option value="">----未选择----</option>
                        <option value="高中以下">高中以下</option>
                        <option value="专科">专科</option>
                        <option value="本科">本科</option>
                        <option value="本科以上">本科以上</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">入职日期</label>
                <div class="layui-input-inline">
                    <input id="hireDayEX" type="date" name="hireDayEX" lay-verify="title" autocomplete="off" placeholder="入职日期" class="layui-input">
                </div>

                <label class="layui-form-label">登录密码</label>
                <div class="layui-input-inline">
                    <input id="passwordEX" type="text" name="password" disabled="disabled" lay-verify="title" autocomplete="off" placeholder="登录密码默认123456" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item" style="margin: 0 auto">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <button type="button" class="layui-btn layui-btn-primary" onclick="closeForm()">返回</button>
            </div>
        </form>

        <%--    员工状态的禁用启用    --%>
        <script type="text/html" id="empStatusA">
            {{# if(d.status == 1) { }}
            <a href="<%=request.getContextPath()%>/emp/updateEmpStatus/{{d.empId}}"  class="layui-table-link">
                禁用
            </a>
            {{# }else if(d.status == 0){ }}
            <a href="<%=request.getContextPath()%>/emp/updateEmpStatus/{{d.empId}}"  class="layui-table-link">
                启用
            </a>
            {{# } }}

        </script>

        <%--    员工表表头工具    --%>
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm" onclick="add()">添加员工</button>
                <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reload()">刷新表格</button>
            </div>
        </script>

        <%--    每行员工信息的操作按钮    --%>
        <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-xs" href="javascript:update('{{ d.empId }}')" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" onclick="deleteEmp('{{ d.empId }}')">删除</a>
        </script>

        <%--    附表部分    --%>
        <div >
            <div class="layui-tab">
                <%--                附表标题！！--%>
                <ul class="layui-tab-title">
                    <li class="layui-this">工作经历</li>
                    <li>教育背景</li>
                    <li>家庭联系信息</li>
                    <li>员工考核</li>
                    <li>证件上传</li>
                </ul>
                <%-- 附表！！--%>
                <div class="layui-tab-content">

                    <%--     员工工作经历的附表    --%>
                    <div class="layui-tab-item layui-show">
                        <div id="secondTable1" lay-filter="testTable"></div>
                        <script id="secondTable1RowCZ" type="text/html">
                            <a class="layui-btn layui-btn-xs" href="javascript:updateWorkExperience('{{d.jobId}}','{{d.EmpId}}','{{d.Remark}}','{{d.companyName}}','{{d.degree}}','{{d.endDate}}','{{d.reason}}','{{d.startDate}}')" lay-event="edit">编辑</a>
                            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" onclick="deleteWorkExprience('{{ d.jobId }}')">删除</a>
                        </script>
                    </div>
                    <script type="text/html" id="secondTable1Tool">
                        <div class="layui-btn-container">
                            <button class="layui-btn layui-btn-sm" onclick="addWorkExperience(secondTableName,secondTableEmpId)">增加工作经历</button>
                            <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reloadSecondTable(secondTableEmpId)">刷新表格</button>
                        </div>
                    </script>

                    <%--员工工作经历的form表单--%>
                    <form id="secondTable1Form" action="" method="post" style="display: none" class="layui-form">
                        <input type="hidden" name="EmpId" id="workExperienceEmpId" value="0">
                        <input type="hidden" name="jobId" id="workExperienceJobId" value="0">
                        <div class="layui-form-item">
                            <label class="layui-form-label">公司名称</label>
                            <div class="layui-input-inline">
                                <input id="ST1CompanyName" type="text" name="companyName" lay-verify="ST1CompanyName" autocomplete="off" placeholder="请输入公司名称" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">岗位</label>
                            <div class="layui-input-inline">
                                <input id="ST1Degree" type="text" name="degree" lay-verify="ST1Degree" autocomplete="off" placeholder="请输入岗位" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">入职时间</label>
                            <div class="layui-input-inline">
                                <input id="ST1StartDate" type="date" name="startDateEX" lay-verify="ST1StartDate" autocomplete="off" placeholder="" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">离职时间</label>
                            <div class="layui-input-inline">
                                <input id="ST1EndDate" type="date" name="endDateEX" lay-verify="ST1EndDate" autocomplete="off" placeholder="" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">离职原因</label>
                            <div class="layui-input-inline">
                                <input id="ST1Reason" type="text" name="reason" lay-verify="ST1Reason" autocomplete="off" placeholder="请输入离职原因" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">说明</label>
                            <div class="layui-input-inline">
                                <input id="ST1Remark" type="text" name="remark" lay-verify="ST1Remark" autocomplete="off" placeholder="请输入说明" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item" style="margin: 0 auto">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <button class="layui-btn" lay-filter="workExprienceForm">立即提交</button>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <button type="button" class="layui-btn layui-btn-primary" onclick="closeWorkExperience()">返回</button>
                        </div>
                    </form>

                    <%--     员工教育背景的附表     --%>
                    <div class="layui-tab-item ">
                        <div id="secondTable2" lay-filter="testTable"></div>
                    </div>
                    <script type="text/html" id="secondTable2Tool">
                        <div class="layui-btn-container">
                            <button class="layui-btn layui-btn-sm" onclick="">增加</button>
                            <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reloadSecondTable(secondTableEmpId)">刷新表格</button>
                        </div>
                    </script>

                    <%--     员工家庭联系人的附表     --%>
                    <div class="layui-tab-item ">
                        <div id="secondTable3" lay-filter="testTable"></div>
                    </div>
                    <script type="text/html" id="secondTable3Tool">
                        <div class="layui-btn-container">
                            <button class="layui-btn layui-btn-sm" onclick="">增加</button>
                            <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reloadSecondTable(secondTableEmpId)">刷新表格</button>
                        </div>
                    </script>

                    <%--     员工考核信息的附表     --%>
                    <div class="layui-tab-item ">
                        <div id="secondTable4" lay-filter="testTable"></div>
                    </div>
                    <script type="text/html" id="secondTable4Tool">
                        <div class="layui-btn-container">
                            <button class="layui-btn layui-btn-sm" onclick="">增加</button>
                            <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reloadSecondTable(secondTableEmpId)">刷新表格</button>
                        </div>
                    </script>

                    <%--     员工证件的附表     --%>
                    <div class="layui-tab-item ">
                        <div id="secondTable5" lay-filter="testTable"></div>
                    </div>
                    <script type="text/html" id="secondTable5Tool">
                        <div class="layui-btn-container">
                            <button class="layui-btn layui-btn-sm" onclick="">增加</button>
                            <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reloadSecondTable(secondTableEmpId)">刷新表格</button>
                        </div>
                    </script>
                </div>
            </div>
        </div>
    </div>
    <script>
        function reload(){
            layui.use('table', function(){
                var table = layui.table;

                //第一个实例
                table.render({
                    elem: '#demo'
                    ,height: 523
                    ,url: '/emp/empDataList' //数据接口
                    ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
                    ,page: true //开启分页
                    ,cols: [[ //表头
                        {field: 'empId', title: '员工编号', width:100, sort: true, fixed: 'left'}
                        ,{field: 'empName', title: '员工姓名', width:100}
                        ,{field: 'depName', title: '部门', width:80}
                        ,{field: 'postName', title: '职务', width:200}
                        ,{field: 'sex', title: '性别', width: 60}
                        ,{field: 'phone', title: '手机号码', width: 200}
                        ,{field: 'address', title: '家庭住址', width: 200}
                        ,{field: 'status', title: '状态', width: 60}
                        ,{field: '', title: '设置状态', width: 100,templet:'#empStatusA'}
                        ,{field: 'password', title: '初始密码', width: 135}
                        ,{field: '', title: '操作', width: 115 ,toolbar:'#barDemo'}
                    ]]
                });

                //监听行单击事件
                table.on('row(test)', function(obj){
                    console.log(obj.tr) //得到当前行元素对象
                    console.log(obj.data) //得到当前行数据
                    reloadSecondTable(obj.data.empId,obj.data.empName);
                });

            });
        };

        reload();

        //打开工作经历新增表
        function addWorkExperience(empName,empId) {
            $("#secondTable1Form").attr("action","${pageContext.request.contextPath}/emp/addEmpWorkExperience");
            $("#workExprienceEmpId").attr("value",empId);
            layer.open({
                type: 1,
                title:"新增" + empName + "的工作经历",
                area:['25%','50%'],
                content: $("#secondTable1Form"),
                closeBtn :0, //隐藏弹出层的关闭按钮
                yes:function(index,layero){
                }
            });
        }

        //关闭工作经历新增表
        function closeWorkExperience() {
            layer.closeAll();
            $("#workExperienceEmpId").val(0);
            $("#workExperienceJobId").val(0);
            $("#ST1CompanyName").val("");
            $("#ST1Degree").val("");
            $("#ST1StartDate").val(ChangeDateFormat(""));
            $("#ST1EndDate").val(ChangeDateFormat(""));
            $("#ST1Reason").val("");
            $("#ST1Remark").val("");
        }

        //修改工作经历
        function updateWorkExperience(jobId,EmpId,Remark,companyName,degree,endDate,reason,startDate) {
            console.log(jobId+" "+EmpId+" "+Remark+" "+companyName+" "+degree+" "+endDate+" "+reason+" "+startDate);
            $("#secondTable1Form").attr("action","${pageContext.request.contextPath}/emp/updateWorkExperience");
            $("#workExperienceEmpId").val(EmpId);
            $("#workExperienceJobId").val(jobId);
            $("#ST1CompanyName").val(companyName);
            $("#ST1Degree").val(degree);
            $("#ST1StartDate").val(ChangeDateFormat(startDate));
            $("#ST1EndDate").val(ChangeDateFormat(endDate));
            $("#ST1Reason").val(reason);
            $("#ST1Remark").val(Remark);
            layer.open({
                type: 1,
                title:"修改" + secondTableName + "的工作经历",
                area:['25%','50%'],
                content: $("#secondTable1Form"),
                closeBtn :0, //隐藏弹出层的关闭按钮
                yes:function(index,layero){
                }
            });
        }

        function ChangeDateFormat(val) {
            if (val != null) {
                var date = new Date(parseInt(val.replace("/Date(", "").replace(")/", ""), 10));
                //月份为0-11，所以+1，月份小于10时补个0
                var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
                var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();

                var getHours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
                var getMinutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
                var getSeconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
                return date.getFullYear() + "-" + month + "-" + currentDate;
                // return date.getFullYear() + "-" + month + "-" + currentDate + " " + getHours + ":" + getMinutes + ":" + getSeconds;
            }
            return "";
        }

        function reloadSecondTable(empId,empName){

            secondTableEmpId = empId;
            secondTableName = empName;

            layui.use('table', function() {
                var table = layui.table;

                //第一个实例
                table.render({
                    elem: '#secondTable1'
                    ,height: 350
                    ,url: '/emp/empWorkExperience/' + empId//数据接口
                    ,toolbar: '#secondTable1Tool' //开启头部工具栏，并为其绑定左侧模板
                    ,page: true //开启分页
                    ,cols: [[ //表头
                        {field: 'companyName', title: '公司名称', width:180,fixed: 'left'}
                        ,{field: 'degree', title: '岗位', width:100}
                        ,{field: 'startDate', title: '入职时间', width:180,templet : '<span>{{layui.util.toDateString(d.startDate,"yyyy-MM-dd HH:mm:ss")}}</span>'}
                        ,{field: 'endDate', title: '离职时间', width:180,templet : '<span>{{layui.util.toDateString(d.endDate,"yyyy-MM-dd HH:mm:ss")}}</span>'}
                        ,{field: 'reason', title: '离职原因', width: 250}
                        ,{field: 'Remark', title: '说明', width: 250}
                        ,{field: '', title: '操作', width: 180,templet:'#secondTable1RowCZ'}
                    ]]
                });

                table.render({
                    elem: '#secondTable2'
                    ,height: 350
                    ,url: '/emp/empEducation/' + empId //数据接口
                    ,toolbar: '#secondTable2Tool' //开启头部工具栏，并为其绑定左侧模板
                    ,page: true //开启分页
                    ,cols: [[ //表头
                        {field: 'collegeName', title: '学校名称', width:200, fixed: 'left'}
                        ,{field: 'degree', title: '学历', width:120}
                        ,{field: 'startDate', title: '入校时间', width:180,templet : '<span>{{layui.util.toDateString(d.startDate,"yyyy-MM-dd HH:mm:ss")}}</span>'}
                        ,{field: 'endDate', title: '毕业时间', width:180,templet : '<span>{{layui.util.toDateString(d.endDate,"yyyy-MM-dd HH:mm:ss")}}</span>'}
                        ,{field: 'Remark', title: '说明', width: 200}
                    ]]
                });

                table.render({
                    elem: '#secondTable3'
                    ,height: 350
                    ,url: '/emp/empFamily/' + empId //数据接口
                    ,toolbar: '#secondTable3Tool' //开启头部工具栏，并为其绑定左侧模板
                    ,page: true //开启分页
                    ,cols: [[ //表头
                        {field: 'contactName', title: '联系人名称', width:150, fixed: 'left'}
                        ,{field: 'relationship', title: '与员工关系', width:180}
                        ,{field: 'Phone', title: '联系电话', width:150}
                        ,{field: 'Remark', title: '说明', width: 220}
                    ]]
                });

                table.render({
                    elem: '#secondTable4'
                    ,height: 350
                    ,url: '/emp/empAudit/' + empId //数据接口
                    ,toolbar: '#secondTable4Tool' //开启头部工具栏，并为其绑定左侧模板
                    ,page: true //开启分页
                    ,cols: [[ //表头
                        {field: 'auditName', title: '考核指标', width:468, fixed: 'left'}
                        ,{field: 'scores', title: '考核分数', width:120}
                        ,{field: 'auditDate', title: '考核时间', width:180,templet : '<span>{{layui.util.toDateString(d.auditDate,"yyyy-MM-dd HH:mm:ss")}}</span>'}
                        ,{field: 'image', title: '图片', width:250}
                        ,{field: 'remark', title: '说明', width: 200}
                        ,{field: 'auditPerson', title: '录入人员', width: 120}
                    ]]
                });

                table.render({
                    elem: '#secondTable5'
                    ,height: 350
                    ,url: '/emp/selEmpAnnex/' + empId //数据接口
                    ,toolbar: '#secondTable5Tool' //开启头部工具栏，并为其绑定左侧模板
                    ,page: true //开启分页
                    ,cols: [[ //表头
                        {field: '', title: '员工名称', width:100, fixed: 'left',templet: '<span>'+empName+'</span>'}
                        ,{field: 'annexName', title: '证件名称', width:180}
                        ,{field: 'annexData', title: '上传证件', width:200}
                        ,{field: 'annexDate', title: '上传时间', width:180,templet : '<span>{{layui.util.toDateString(d.annexDate,"yyyy-MM-dd HH:mm:ss")}}</span>'}
                        ,{field: 'sessionName', title: '上传人', width: 100}
                        ,{field: 'remark', title: '备注', width: 300}
                    ]]
                });

            });
        };



        layui.use('element', function(){
            var element = layui.element;

            //…
        });
    </script>
</div>

<%-- 全部都是emp表的增加修改操作 --%>
<script>

    function closeForm() {
        layer.closeAll();
        $("#empName").val("");
        $("#depId").val("1");
        $("#postName").val("教研主任");
        $("#Address").val("");
        $("#sex").val("男");
        $("#Cardno").val("");
        $("#BirthdayEX").val("");
        $("#Phone").val("");
        $("#QQcode").val("");
        $("#Weixin").val("");
        $("#Email").val("");
        $("#married").val("----未选择----");
        $("#University").val("");
        $("#Major").val("");
        $("#Education").val("----未选择----");

        $("#nation_1").val("北京市");
        $("#hireDayEX").val("");

        $.post("/emp/getNationList",{type:'市',position:'北京市'},function (data) {
            var form = layui.form;
            var nation_2_text = "";
            $.each(data,function (index,obj) {
                nation_2_text += "<option value='" + obj + "'>" + obj + "</option>";
            });
            $("#nation_2").html(nation_2_text);
            form.render('select');
        },"json");

        $.post("/emp/getNationList",{type:'县',position:'东城区'},function (data) {
            var form = layui.form;
            var nation_3_text = "";
            $.each(data,function (index,obj) {
                nation_3_text += "<option value='" + obj + "'>" + obj + "</option>";
            });
            $("#nation_3").html(nation_3_text);
            form.render('select');
        },"json");
    }

    layui.use(['layer', 'jquery', 'form'], function() {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form;

        form.on('submit(formDemo)',function () {
            form.verify({
                empName: function(value){
                    if(value.length < 1){
                        return '请输入姓名';
                    }
                }
                // ,pass: [
                //     /^[\S]{6,12}$/
                //     ,'密码必须6到12位，且不能出现空格'
                // ]
                // ,postName: function(value){
                //     if(value == "专职班主任" || value == "讲师" || value == "学工主任" || value == "教研副主任" || value == "教研主任"){
                //
                //     }else {
                //         return '请输入正确的职务名';
                //     }
                // }
                ,Address: function (value) {
                    if (value == '' || value == null){
                        return '请输入家庭住址';
                    }
                }
                ,Cardno: function (value) {
                    if (value == '' || value == null){
                        return '身份证号码为必填项！';
                    }
                }
                ,Phone: function (value) {
                    if (value == '' || value == null || value.length != 11){
                        return '手机号码不正确！';
                    }
                }
                ,QQcode: function (value) {
                    if (value == '' || value == null){
                        return 'QQ号码不能为空！';
                    }
                }
                ,Weixin: function (value) {
                    if (value == '' || value == null){
                        return '请留下您的微信号以便联系！';
                    }
                }
                ,Email:[
                    /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
                    ,'请输入正确的邮箱格式！'
                ]
                ,University: function (value) {
                    if (value == '' || value == null){
                        return "请输入您的毕业学校！";
                    }
                }
                ,Major: function (value) {
                    if (value == '' || value == null){
                        return "请输入您的专业！";
                    }
                }
            });
        });

    });

    layui.use(['layer', 'jquery', 'form'], function() {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form;

        //监听提交
        form.on('submit(workExprienceForm)', function(){
            <%--layui.use('jquery',function(){--%>
            <%--    var $=layui.$;--%>
            <%--    $.ajax({--%>
            <%--        type: 'post',--%>
            <%--        url: '${pageContext.request.contextPath}/emp/addEmpWorkExperience', // ajax请求路径--%>
            <%--        data: {--%>
            <%--        },--%>
            <%--        success: function(data){--%>
            <%--            if(data=='ok'){--%>
            <%--                layer.msg('添加成功');--%>
            <%--                reloadSecondTable(secondTableEmpId,secondTableName);--%>
            <%--            }else if(data=='error'){--%>
            <%--                layer.msg('添加失败');--%>
            <%--            }--%>
            <%--        }--%>
            <%--    });--%>
            <%--});--%>
            <%--return false;//禁止跳转，否则会提交两次，且页面会刷新--%>

            $.post("${pageContext.request.contextPath}/emp/addEmpWorkExperience",{},function (data) {
                layer.msg('添加成功');
                reloadSecondTable(secondTableEmpId,secondTableName);
            });

            return false;
        });

        return false;
    });


    function add() {
        $("#empDataForm").attr("action","${pageContext.request.contextPath}/emp/empAdd");
        layer.open({
            type: 1,
            title:"新增",
            area:['45%','75%'],
            content: $("#empDataForm"),
            closeBtn :0, //隐藏弹出层的关闭按钮
            yes:function(index,layero){
            }
        });

    }

    function  update(empId) {
        $.post("${pageContext.request.contextPath}/emp/selEmpById/"+empId,{},function (d) {
            $("#empDataForm").attr("action","${pageContext.request.contextPath}/emp/empUpdate/");
            $("#empId").val(d.emp.empId);
            $("#remark").val(d.emp.remark);
            $("#Bank").val(d.emp.bank);
            $("#accountName").val(d.emp.accountName);
            $("#bankNumber").val(d.emp.bankNumber);
            $("#alipay").val(d.emp.alipay);
            $("#password").val(d.emp.password);
            $("#postId").val(d.emp.postId);
            $("#status").val(d.emp.status);
            $("#nation").val(d.emp.nation);
            $("#empName").val(d.emp.empName);
            $("#depId").val(d.emp.depId);
            $("#postName").val(d.emp.postName);
            $("#Address").val(d.emp.address);
            $("#sex").val(d.emp.sex);
            $("#Cardno").val(d.emp.cardno);
            $("#BirthdayEX").val(getDateYYYYmmdd(d.emp.birthday));
            $("#Phone").val(d.emp.phone);
            $("#QQcode").val(d.emp.qQcode);
            $("#Weixin").val(d.emp.weixin);
            $("#Email").val(d.emp.email);
            $("#married").val(d.emp.married);
            $("#University").val(d.emp.university);
            $("#Major").val(d.emp.major);
            $("#Education").val(d.emp.education);
            var nation = d.emp.nation;
            if (nation.indexOf('省') == -1 || nation.indexOf('省') == null){
                $("#nation_1").val(nation.substr(0,nation.indexOf('市')+1));

                addShiOption(nation.substr(0,nation.indexOf('市')+1));
                $("#nation_2").val(nation.substr(nation.indexOf('市')+1,nation.length));
            }else {
                $("#nation_1").val(nation.substr(0,nation.indexOf('省')+1));

                addShiOption(nation.substr(0,nation.indexOf('省')+1));
                $("#nation_2").val(nation.substring(nation.indexOf('省')+1,nation.indexOf('市')+1));

                addXianOption(nation.substring(nation.indexOf('省')+1,nation.indexOf('市')+1));
                $("#nation_3").val(nation.substr(nation.indexOf('市')+1,nation.length+1));
            }
            $("#hireDayEX").val(getDateYYYYmmdd(d.emp.hireDay));
            $("#password").val(d.emp.password);

            layui.form.render('select');
        },"json");
        layer.open({
            type: 1,
            title:"修改",
            area:['45%','75%'],
            content: $("#empDataForm"),
            closeBtn :0, //隐藏弹出层的关闭按钮
            yes:function(index,layero){
            }
        });
    }

    function deleteEmp(empId) {

        layer.confirm('是否要删除？', {
            icon:3,
            btn: ['确认','取消'] //按钮
        }, function(){+
            $.post('<%=request.getContextPath()%>/emp/delEmp/' + empId,{},
                function (data) {
                    reload();
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
    }

    layui.use('form', function(){
        var form = layui.form;
        //监听提交
        form.on('submit(formDemo)', function(data){
            layer.msg("操作成功！");
            return true;
        });
    });

    function getDateYYYYmmdd(datelong){
        var ddd = new Date(datelong);
        var day =ddd.getDate();
        var month = ddd.getMonth()+1;

        if(ddd.getMonth()<10){
            var month = "0"+(ddd.getMonth()+1);
        }

        if(ddd.getDate()<10){
            day = "0"+ddd.getDate();
        }

        var datew = ddd.getFullYear()+"-"+month+"-"+day;
        var datew = datew.toString();

        return datew;
    }

    //监听省&直辖市的select是否改变
    layui.use(['layer', 'jquery', 'form'], function () {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form;

        form.on('select(nation_1)', function(data){
            addShiOption($("#nation_1").val());
        });
    });

    //监听市&区的select是否改变
    layui.use(['layer', 'jquery', 'form'], function () {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form;

        form.on('select(nation_2)', function(data){
            addXianOption($("#nation_2").val());
        });
    });

    //此方法根据省&直辖市select的变化改变所属市&区
    function addShiOption(position) {
        $.ajaxSettings.async = false;
        $.post("/emp/getNationList",{type:'市',position:position},function (data) {
            var form = layui.form;
            var nation_2_text = "";
            $.each(data,function (index,obj) {
                nation_2_text += "<option value='" + obj + "'>" + obj + "</option>";
            });
            $("#nation_2").html(nation_2_text);
            form.render('select');
            addXianOption($("#nation_2").val());
        },"json");
        $.ajaxSettings.async = true;
    }

    //此方法根据市select的变化改变所属县
    function addXianOption(position) {
        $.ajaxSettings.async = false;
        $.post("/emp/getNationList",{type:'县',position:position},function (data) {
            var form = layui.form;
            var nation_3_text = "";
            $.each(data,function (index,obj) {
                nation_3_text += "<option value='" + obj + "'>" + obj + "</option>";
            });
            $("#nation_3").html(nation_3_text);
            form.render('select');
        },"json");
        $.ajaxSettings.async = true;
    }

    //当页面加载完成时初步加载省select
    $(document).ready(function () {
        $.post("/emp/getPostName",{},function (data) {
            var form = layui.form;
            var postName_text = "";
            $.each(data,function (index,obj) {
                postName_text += "<option value='" + obj + "'>" + obj + "</option>";
            });
            $("#postName").html(postName_text);
            form.render('select');
        },'json')

        $.post("/emp/getNationList",{type:'省',position:''},function (data) {
            var form = layui.form;
            var nation_1_text = "";
            $.each(data,function (index,obj) {
                nation_1_text += "<option value='" + obj + "'>" + obj + "</option>";
            });
            $("#nation_1").html(nation_1_text);
            form.render('select');
            addShiOption($("#nation_1").val());
        },"json");
    });

    function deleteWorkExprience(jobId) {

        console.log(jobId);

        layer.confirm('是否要删除？', {
            icon:3,
            btn: ['确认','取消'] //按钮
        }, function(){+
            $.post('<%=request.getContextPath()%>/emp/deleteWorkExprience/' + jobId,{},
                function (data) {
                    reloadSecondTable(secondTableEmpId,secondTableName);
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
    }
</script>

</body>
</html>
