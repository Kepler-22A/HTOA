<%--
  Created by IntelliJ IDEA.
  User: 28401
  Date: 2019/12/5
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生资料</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <meta charset="utf-8">
    <script src="${pageContext.request.contextPath}/layui/layui.all.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/echar/echarts.min.js" charset="utf-8" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/echar/jquery-3.3.1.min.js" charset="utf-8"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script type="text/javascript">
        //添加
        function add() {
            $("#sss").attr("action","${pageContext.request.contextPath}/student/studentadd");
            layer.open({
                type: 1,
                title:"新增",
                area:['70%','70%'],
                content: $("#sss"),
                closeBtn :0, //隐藏弹出层的关闭按钮
                yes:function(index,layero){
                }
            });
        }
        //修改
        function  update(hourid) {
            $.post("${pageContext.request.contextPath}/student/selectStudentID",{id : hourid},function (d) {
                $("#sss").attr("action","${pageContext.request.contextPath}/student/UpdateStudentID/" + d.StudentVo.Studid);
                $("#Studid").val(d.StudentVo.Studid);
                $("#q").val(d.StudentVo.stuname);
                $("#w").val(d.StudentVo.password);
                $("#e").val(d.StudentVo.age);
                $("#r").val(d.StudentVo.phone);
                $("#t").val(d.StudentVo.addr);
                $("#y").val(d.StudentVo.nation);
                $("#u").val(d.StudentVo.natives);
                $("#o").val(d.StudentVo.cardid);
                $("#p").val(d.StudentVo.professional);
                $("#d").val(d.StudentVo.parents);
                $("#f").val(d.StudentVo.parentsphone);
                $("#g").val(d.StudentVo.intrphone);
                $("#h").val(d.StudentVo.audition);
                $("#j").val(d.StudentVo.remark);
                $("#l").val(d.StudentVo.vocationalsch);
                $("#x").val(d.StudentVo.middleschool);
                $("#v").val(d.StudentVo.introduretech);

            },"json");
            layer.open({
                type: 1,
                title:"修改",
                area:['70%','70%'],
                content: $("#sss"),
                closeBtn :0, //隐藏弹出层的关闭按钮
                yes:function(index,layero){
                }
            });
        }
        //删除
        function  delhour(Studid) {
            console.log(Studid);
            layer.confirm('是否要删除？', {
                icon:3,
                btn: ['确认','取消'] //按钮
            }, function(){+
                $.post('${pageContext.request.contextPath}/student/delstudent/' + Studid,{},
                    function (data) {
                        reload();
                    });
                layer.msg('已删除', {
                    icon: 1,
                    time:2000,
                });

            }, function(){
                layer.msg('已取消', {
                    icon:0,
                    time: 2000 //20s后自动关闭
                });
            });

        }
        function guanbi() {
            layer.closeAll();
            $("#Studid").val(0);
            $("#q").val("");
            $("#w").val("");
            $("#e").val("");
            $("#r").val("");
            $("#t").val("");
            $("#y").val("");
            $("#u").val("");
            $("#o").val("");
            $("#p").val("");
            $("#d").val("");
            $("#f").val("");
            $("#g").val("");
            $("#h").val("");
            $("#j").val("");
            $("#l").val("");
            $("#x").val("");
            $("#v").val("");
        }
        function tubiao(a,b,c,d,e,f) {
            layer.open({
                type: 1,
                title:"答辩成绩图表",
                area:['60%','70%'],
                content: $("#main"),
                yes:function(index,layero){

                }
            });
            var myEcharts = echarts.init(document.getElementById("main"));
            var option = {
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data:['功能完善','技术难度','界面完美','回答方式','演示方式','语言表达']
                },
                series: [
                    {
                        name:'答辩成绩',
                        type:'pie',
                        radius: ['50%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            normal: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                show: true,
                                textStyle: {
                                    fontSize: '30',
                                    fontWeight: 'bold'
                                }
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data:[
                            {value:a, name:'功能完善'},
                            {value:b, name:'技术难度'},
                            {value:c, name:'界面完美'},
                            {value:e, name:'回答方式'},
                            {value:f, name:'演示方式'},
                            {value:g, name:'语言表达'}
                        ]
                    }
                ]
            };

            myEcharts.setOption(option);
        }
    </script>
</head>
<body>
    <table class="layui-hide" id="test" lay-filter="test"></table>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" onclick="add()">添加学生</button>
            <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reload()">刷新表格</button>

        </div>
    </script>
    <!--增加学生表-->
    <form  class="layui-form" id="sss" style="display:none" method="post"  action="${pageContext.request.contextPath}/student/studentadd">
        <input type="hidden" value="0" name="Studid" id="Studid"/>
        <input type="hidden" value="1" name="enrollno">
        <input type="hidden" value="0.0" name="qkMoney">
        <input type="hidden" value="0.0" name="score">
        <input type="hidden" value="1" name="studytype">
        <input type="hidden" value="1" name="vocationalflag">
        <input type="hidden" value="100" name="huor"/>
        <input type="hidden" value="1" name="stat"/>
        <input type="hidden" value="否" name="dibao"/>
        <input type="hidden" value="务农" name="sourceType"/>
        <input type="hidden" value="廖文汉" name="guarantee"/>
        <input type="hidden" value="0" name="soldier"/>
        <input type="hidden" value="100000" name="registration"/>
        <input type="hidden" value="1" name="tuixue"/>
        <input type="hidden" value="1" name="xiuxue"/>
        <input type="hidden" value="x100" name="stuno"/>
        <input type="hidden" value="是" name="grants"/>
        <input type="hidden" value="是" name="computer"/>
        <input type="hidden" value="是" name="collar"/>
        <input type="hidden" value="是" name="zhuxiao"/>
        <input type="hidden" value="0" name="prolevel">
        <input type="button" value="赋值" onclick="fuzhi()">
        <div style="width: 56%;height: auto;margin-top: 1%">
            <div style="width:50%;height:100%;float: left">
                <div class="layui-form-item">
                    <label class="layui-form-label">学生姓名</label>
                    <div class="layui-input-block">
                        <input id="q" type="text" name="stuname" required  lay-verify="required" placeholder="请输入学生姓名" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">密码</label>
                    <div class="layui-input-block">
                        <input id="w" type="text" name="password" required  lay-verify="required" placeholder="请设置登录密码" autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">性别</label>
                    <div class="layui-input-block">
                        <input type="radio" name="sex" value="男" title="男">
                        <input type="radio" name="sex" value="女" title="女" checked>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">年龄</label>
                    <div class="layui-input-block">
                        <input id="e" type="text" name="age" required  lay-verify="required" placeholder="请输入年龄" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">出生年月</label>
                    <div class="layui-input-block">
                        <input type="birthday" name="shijian" required  lay-verify="required" placeholder="单击此处选择日期" id="srtime" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">学生电话</label>
                    <div class="layui-input-block">
                        <input id="r" type="text" name="phone" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">家庭地址</label>
                    <div class="layui-input-block">
                        <input id="t" type="text" name="addr" required  lay-verify="required" placeholder="准确到乡镇即可" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">入学时间</label>
                    <div class="layui-input-block">
                        <input type="entertime" name="shijian2" required  lay-verify="required" placeholder="单击此处选择日期" id="rutime" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">民族</label>
                    <div class="layui-input-block">
                        <input id="y" type="text" name="nation" required  lay-verify="required" placeholder="民族" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">籍贯</label>
                    <div class="layui-input-block">
                        <input id="u" type="text" name="natives" required  lay-verify="required" placeholder="籍贯" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">户口性质</label>
                    <div class="layui-input-block">
                        <select name="residence" lay-verify="required">
                            <option value="">请选择</option>
                            <option value="1">农业</option>
                            <option value="2">非农</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">面试人建议</label>
                    <div class="layui-input-block">
                        <textarea name="auditionoption" placeholder="请输入内容" class="layui-textarea"></textarea>
                    </div>
                </div>
            </div>

            <div style="width: 50%;height: 100%;float: left">
                <div class="layui-form-item">
                    <label class="layui-form-label">家长姓名</label>
                    <div class="layui-input-block">
                        <input id="d" type="text" name="parents" required  lay-verify="required" placeholder="请输入家长姓名" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">家长电话</label>
                    <div class="layui-input-block">
                        <input id="f" type="text" name="parentsphone" required  lay-verify="required" placeholder="请输入家长电话" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">老师电话</label>
                    <div class="layui-input-block">
                        <input id="g" type="text" name="intrphone" required  lay-verify="required" placeholder="请输入老师电话" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">面试人</label>
                    <div class="layui-input-block">
                        <input id="h" type="text" name="audition" required  lay-verify="required" placeholder="请输入面试人" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-block">
                        <input id="j" type="text" name="remark" required  lay-verify="required" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">是否中专</label>
                    <div class="layui-input-block">
                        <select name="isvocational" lay-verify="required">
                            <option value="">请选择</option>
                            <option value="1">是</option>
                            <option value="0">否</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">中专学校</label>
                    <div class="layui-input-block">
                        <input id="l" type="text" name="vocationalsch" required  lay-verify="required" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">毕业学校</label>
                    <div class="layui-input-block">
                        <input id="x" type="text" name="middleschool" required  lay-verify="required" placeholder="请输入毕业学校" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">身份证号</label>
                    <div class="layui-input-block">
                        <input id="o" type="text" name="cardid" required  lay-verify="required" placeholder="请输入11位完整的号码" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">就读专业</label>
                    <div class="layui-input-block">
                        <input id="p" type="text" name="professional" required  lay-verify="required" placeholder="请输入就读专业" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">班级</label>
                    <div class="layui-input-block">
                        <select name="clazz" lay-verify="required" id="clazz">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">介绍老师</label>
                    <div class="layui-input-block">
                        <input id="v" type="text" name="introduretech" required  lay-verify="required" placeholder="老师" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                        <button type="button" class="layui-btn layui-btn-primary" onclick="guanbi()">返回</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" onclick="update('{{ d.studid }}')">修改</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" onclick="delhour('{{ d.studid }}')">删除</a>
    </script>
    <!---->
    <div >
        <div class="layui-tab">
            <%--                附表标题！！--%>
            <ul class="layui-tab-title">
                <li class="layui-this">答辩总成绩</li>
                <li>考试成绩</li>
            </ul>
            <%-- 附表！！--%>
            <div class="layui-tab-content">
                <%--    答辩成绩的附表  *****************************************************************************************  --%>
                <div class="layui-tab-item layui-show">
                    <div id="secondTable1" lay-filter="testTable"></div>
                    <script id="secondTable1RowCZ" type="text/html">
                        <a class="layui-btn layui-btn-xs" href="javascript:updateWorkExperience(secondTableEmpId,'{{d.replyId}}','{{d.StudentId}}','{{d.stuname}}','{{d.score1}}','{{d.Score2}}','{{d.Score3}}','{{d.Score4}}','{{d.Score5}}','{{d.Score6}}','{{d.Score7}}','{{d.Remark}}')" lay-event="edit">修改</a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" onclick="deleteWorkExprience('{{ d.replyId }}')">删除</a>
                        <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="del" onclick="tubiao('{{ d.score1 }}','{{d.Score2}}','{{d.Score3}}','{{d.Score4}}','{{d.Score5}}','{{d.Score6}}')">图表</a>
                    </script>
                </div>
                <script type="text/html" id="secondTable1Tool">
                    <div class="layui-btn-container">
                        <button class="layui-btn layui-btn-sm" onclick="addWorkExperience(secondTableName,secondTableEmpId)">增加答辩成绩</button>
                        <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reloadSecondTable(secondTableEmpId,secondTableName)">刷新表格</button>
                    </div>
                </script>
                <%--答辩成绩的form表单--%>
                <form id="secondTable1Form" action="" method="post" style="display: none" class="layui-form">
                    <input type="hidden" id="StudentId" name="StudentId" value="0">
                    <input type="hidden" id="replyId" name="replyId" value="0">
                    <div class="layui-form-item">
                        <label class="layui-form-label">项目名称</label>
                        <div class="layui-input-block" style="width: 190px;">
                            <select name="projectId" lay-verify="required" id="projectId">
                                <option value="">请选择</option>
                                <option value="1">第一学期</option>
                                <option value="2">第二学期</option>
                                <option value="3">第三学期</option>
                                <option value="4">第四学期</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">打分老师</label>
                        <div class="layui-input-block" style="width: 190px;">
                            <select name="empId" lay-verify="required" id="teacher">
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">功能完善50</label>
                        <div class="layui-input-inline">
                            <input id="score1" type="text" name="score1" lay-verify="ST1Degree" autocomplete="off" placeholder="请输入分数" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">技术难度10</label>
                        <div class="layui-input-inline">
                            <input id="score2" type="text" name="score2" lay-verify="ST1Degree" autocomplete="off" placeholder="请输入分数" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">界面完美10</label>
                        <div class="layui-input-inline">
                            <input id="score3" type="text" name="score3" lay-verify="ST1Degree" autocomplete="off" placeholder="请输入分数" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">回答方式10</label>
                        <div class="layui-input-inline">
                            <input id="score4" type="text" name="score4" lay-verify="ST1Degree" autocomplete="off" placeholder="请输入分数" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">演示方式10</label>
                        <div class="layui-input-inline">
                            <input id="score5" type="text" name="score5" lay-verify="ST1Degree" autocomplete="off" placeholder="请输入分数" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">语言表达10</label>
                        <div class="layui-input-inline">
                            <input id="score6" type="text" name="score6" lay-verify="ST1Degree" autocomplete="off" placeholder="请输入分数" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">备注</label>
                        <div class="layui-input-inline">
                            <input id="Remark" type="text" name="Remark" lay-verify="ST1Remark" autocomplete="off" placeholder="请输入说明" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item" style="margin: 0 auto">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <button class="layui-btn" lay-filter="workExprienceForm">立即提交</button>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <button type="button" class="layui-btn layui-btn-primary" onclick="closeWorkExperience()">返回</button>
                    </div>
                </form>


                <div class="layui-tab-item ">
                        <div id="secondTable2" lay-filter="testTable"></div>
                        <script id="secondTable2RowCZ" type="text/html">
                            <a class="layui-btn layui-btn-xs" href="javascript:updateEducation(secondTableEmpId,'{{d.scoreId}}','{{d.stuname}}','{{d.score}}','{{d.Rescore}}','{{d.empId}}')" lay-event="edit">编辑</a>
                            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" onclick="deleteEducation('{{ d.scoreId }}')">删除</a>
                        </script>
                    </div>
                <script type="text/html" id="secondTable2Tool">
                        <div class="layui-btn-container">
                            <button class="layui-btn layui-btn-sm" onclick="addEducation(secondTableName,secondTableEmpId)">增加考试成绩</button>
                            <button class="layui-btn layui-btn-danger layui-btn-sm" onclick="reloadSecondTable(secondTableEmpId,secondTableName)">刷新表格</button>
                        </div>
                </script>

                <%-- 考试成绩的form表单    --%>
                <form id="secondTable2Form" action="" method="post" style="display: none" class="layui-form">
                        <input type="hidden" name="stuid" id="bbb" value="0">
                        <input type="hidden" name="scoreId" id="scoreId" value="0">
                        <input type="hidden" name="Empid" id="empId" value="0">
                        <div class="layui-form-item">
                            <label class="layui-form-label">分数</label>
                            <div class="layui-input-inline">
                                <input id="score" type="text" name="score" lay-verify="ST2Degree" autocomplete="off" placeholder="请输入分数" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">补考分数</label>
                            <div class="layui-input-inline">
                                <input id="Rescore" type="text" name="Rescore" lay-verify="ST2Degree" autocomplete="off" placeholder="请输入补考分数" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">课程名称</label>
                            <div class="layui-input-block" style="width: 190px;">
                                <select name="courseId" lay-verify="required" id="courseId">
                                    <option value="">请选择</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">考试类别</label>
                            <div class="layui-input-block" style="width: 190px;">
                                <select name="testType" lay-verify="required" id="testType">
                                    <option value="">请选择</option>
                                    <option value="1">笔试</option>
                                    <option value="2">计试</option>
                                    <option value="3">模拟面试</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">在读学期</label>
                                <div class="layui-input-block" style="width: 190px;">
                                    <select name="termid" lay-verify="required" id="termid">
                                        <option value="">请选择</option>
                                        <option value="1">第一学期</option>
                                        <option value="2">第二学期</option>
                                        <option value="3">第三学期</option>
                                        <option value="4">第四学期</option>
                                    </select>
                                </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">考试时间</label>
                            <div class="layui-input-block" style="width: 190px;">
                                <input type="entertime" name="shijian2" required  lay-verify="required" placeholder="单击此处选择日期" id="scoreTime" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">说明</label>
                            <div class="layui-input-inline">
                                <input id="remarks" type="text" name="remarks" lay-verify="ST2Remark" autocomplete="off" placeholder="" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item" style="margin: 0 auto">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <button class="layui-btn" lay-filter="educationForm">立即提交</button>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <button type="button" class="layui-btn layui-btn-primary" onclick="closeEducation()">返回</button>
                        </div>
                    </form>
            </div>
        </div>
    </div>
    <!--图像表-->
    <div id="main" style="display: none;height:480px;width:950px"></div>
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
    <script type="textml" id="testType2">
    {{# if (d.stat=== 1) { }}
        笔试
    {{# } else if(d.stat=== 2) { }}
        机试
    {{# } else { }}
        模拟面试
    {{# } }}
</script>
    <script type="textml" id="roleTpl">
    {{# if (d.stat=== 1) { }}
        在校
    {{# } else if(d.stat=== 2) { }}
        不在校
    {{# } else { }}
        我也不晓得
    {{# } }}
</script>
    <script type="textml" id="testType3">
    {{# if (d.stat=== 1) { }}
        第一学期
    {{# } else if(d.stat=== 2) { }}
        第二学期
    {{# } else if(d.stat=== 3) { }}
          第三学期
    {{# } else { }}
        第四学期
    {{# } }}
</script>

</body>
<script>
    reload();
    function reload(){
        layui.use('table', function(){
            var table = layui.table;

            //第一个实例
            table.render({
                elem: '#test'
                ,url:'/student/data'
                ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
                ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                    title: '献出我们的心脏（♥）'
                    ,layEvent: 'LAYTABLE_TIPS'
                    ,icon: 'layui-icon-tips'
                }]
                ,title: '用户数据表'
                ,cols: [[
                    {type: 'checkbox', fixed: 'left'}
                    ,{field:'studid', title:'ID',  fixed: 'left', unresize: true, sort: true,width:50}
                    ,{field:'stuname', title:'学生姓名'}
                    ,{field:'stuno', title:'学号', sort: true}
                    ,{field:'sex', title:'性别' }
                    ,{field:'cardid', title:'身份证',edit: 'text'}
                    ,{field:'phone', title:'电话',edit: 'text'}
                    ,{field:'className', title:'班级',width:120}//!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    ,{field:'huor', title:'宿舍房号'}///!!!!!!!!!!!!!!!!!!!!!!!!
                    ,{field:'stat', title:'学生状态',templet: '#roleTpl'}
                    ,{field:'collar', title:'是否领用电脑' }
                    ,{field:'grants', title:'享受助学金' }
                    ,{field:'computer', title:'是否送电脑'}
                    ,{field:'parents', title:'家长姓名' }
                    ,{field:'qkMoney', title:'欠款金额' }
                    ,{fixed: 'right', title:'操作',width:150, toolbar: '#barDemo'}
                ]]
                ,page: true
            });
            //Demo
            layui.use('laydate', function(){
                var laydate = layui.laydate;

                //执行一个laydate实例
                laydate.render({
                    elem: '#rutime' //指定元素
                    ,theme: '#393D89'
                });
            });
            //Demo
            layui.use('laydate', function(){
                var laydate = layui.laydate;

                //执行一个laydate实例
                laydate.render({
                    elem: '#srtime' //指定元素
                    ,theme: '#393D49'
                });
            });
            //Demo
            layui.use('laydate', function(){
                var laydate = layui.laydate;

                //执行一个laydate实例
                laydate.render({
                    elem: '#scoreTime' //指定元素
                    ,theme: '#393D49'
                });
            });

            //监听行单击事件
            table.on('row(test)', function(obj){
                console.log(obj.tr) //得到当前行元素对象
                console.log(obj.data) //得到当前行数据
                reloadSecondTable(obj.data.studid,obj.data.stuname);
            });

        });
    }
    function reloadSecondTable(empId,empName){

        secondTableEmpId = empId;
        secondTableName = empName;

        layui.use('table', function() {
            var table = layui.table;

            table.render({
                elem: '#secondTable1'
                ,height: 350
                ,url: '/student/StudentReplyScore/' + secondTableEmpId//数据接口
                ,toolbar: '#secondTable1Tool' //开启头部工具栏，并为其绑定左侧模板
                ,page: true //开启分页
                ,cols: [[ //表头
                    {field:'replyId', title: 'ID', sort: true,width:60}
                    ,{field:'stuname', title: '学生姓名'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
                    ,{field:'className', title: '班级', width:120}
                    ,{field:'projectName', title: '项目名称',width:106}
                    ,{field:'score1', title: '功能完善50', align: 'center',width:106} //单元格内容水平居中
                    ,{field:'Score2', title: '技术难度10',  align: 'center',width:106} //单元格内容水平居中
                    ,{field:'Score3', title: '界面完美10', align: 'center',width:106}
                    ,{field:'Score4', title: '回答方式10', width:106,align: 'center'}
                    ,{field:'Score5', title: '演示方式10', width:106,align: 'center'}
                    ,{field:'Score6', title: '语言表达10',width:106,align: 'center'}
                    ,{field:'Score7', title: '总分100',sort: true, align: 'center',width:106}
                    ,{field:'empName', title: '评分老师',align: 'center',width:106}
                    ,{field:'Remark', title: '备注',width:150}
                    ,{field: '', title: '操作',templet:'#secondTable1RowCZ'}
                ]]
            });
            table.render({
                elem: '#secondTable2'
                ,height: 350
                ,url: '/student/selectStudent_score/' + secondTableEmpId//数据接口
                ,toolbar: '#secondTable2Tool' //开启头部工具栏，并为其绑定左侧模板
                ,page: true //开启分页
                ,cols: [[ //表头
                    {field:'scoreId', title: 'ID', sort: true}
                    ,{field:'stuname', title: '学生姓名'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
                    ,{field:'score', title: '分数', sort: true}
                    ,{field:'Rescore', title: '补考分数'}
                    ,{field:'courseName', title: '课程名称'}
                    ,{field:'testType', title: '考试类别', align: 'center',templet: '#testType2'} //单元格内容水平居中
                    ,{field:'termid', title: '在读学期',  align: 'right',templet: '#testType3'} //单元格内容水平居中
                    ,{field: 'scoreTime', title: '考试时间', width:200,templet : '<span>{{layui.util.toDateString(d.scoreTime,"yyyy-MM-dd HH:mm:ss")}}</span>'}
                    ,{field:'empName', title: '录入人员', sort: true, align: 'right'}
                    ,{field: '', title: '操作',templet:'#secondTable2RowCZ'}
                ]]
            });

        });
    };
    //新增答辩成绩*****************************************************************************************************
    function addWorkExperience(empName,empId) {
        $("#secondTable1Form").attr("action","${pageContext.request.contextPath}/student/addStudentReplyScore");
        $("#StudentId").val(empId);
        layer.open({
            type: 1,
            title:"新增" + empName + "的答辩成绩",
            area:['25%','50%'],
            content: $("#secondTable1Form"),
            closeBtn :0, //隐藏弹出层的关闭按钮
            yes:function(index,layero){
            }
        });
    }
    //修改答辩成绩
    function updateWorkExperience(empId,replyId,StudentId,stuname,score1,Score2,Score3,Score4,Score5,Score6,Score7,Remark) {
        $("#secondTable1Form").attr("action","${pageContext.request.contextPath}/student/updateStudentReplyScore");
        $("#replyId").val(replyId);
        $("#StudentId").val(empId);
        $("#score1").val(score1);
        $("#score2").val(Score2);
        $("#score3").val(Score3);
        $("#score4").val(Score4);
        $("#score5").val(Score5);
        $("#score6").val(Score6);
        $("#score7").val(Score7);
        $("#Remark").val(Remark);
        layer.open({
            type: 1,
            title:"修改"+stuname+ "的答辩成绩",
            area:['25%','50%'],
            content: $("#secondTable1Form"),
            closeBtn :0, //隐藏弹出层的关闭按钮
            yes:function(index,layero){
            }
        });
    }
    //删除答辩成绩
    function deleteWorkExprience(jobId) {

        console.log(jobId);

        layer.confirm('是否要删除？', {
            icon:3,
            btn: ['确认','取消'] //按钮
        }, function(){+
            $.post('<%=request.getContextPath()%>/student/deleteStudentReplyScore/' + jobId,{},
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

    //查询出老师
    $(document).ready(function () {

        $.post("${pageContext.request.contextPath}/student/teacherNameAjax",{},function (data) {
            var t = $("#teacher").html();
            $.each(data,function (index,obj) {
                $.each(obj,function (i,o) {
                    t += "<option value='"+o.empId+"'>"+o.empName+"</option>";
                })
            });
            $("#teacher").html(t);
            layui.use(['form'], function(){
                var form = layui.form;
                form.render('select');
            });
        },'json');
        //查询出课程 courseId
        $.post("${pageContext.request.contextPath}/student/courseIdAjax",{},function (data) {
            var t = $("#courseId").html();
            $.each(data,function (index,obj) {
                $.each(obj,function (i,o) {
                    t += "<option value='"+o.courseID+"'>"+o.courseName+"</option>";
                })
            });
            $("#courseId").html(t);
            layui.use(['form'], function(){
                var form = layui.form;
                form.render('select');
            });
        },'json');
        //查询出班级 clazz
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
    //关闭工作经历新增表
    function closeWorkExperience() {
        layer.closeAll();
        $("#projectId").val(0);
        $("#empId").val(0);
        $("#score1").val("");
        $("#score2").val("");
        $("#score3").val(ChangeDateFormat(""));
        $("#score4").val(ChangeDateFormat(""));
        $("#score5").val("");
        $("#score6").val("");
        $("#Remark").val("");
    }
    function closeEducation() {
        layer.closeAll();
        $("#stuid").val(0);
        $("#scoreId").val(0);
        $("#Empid").val(0);
        $("#remarks").val("");
    }

    //新增考试成绩***********************************************************************************************************
    function addEducation(empName,empId) {
        $("#secondTable2Form").attr("action","${pageContext.request.contextPath}/student/addStudent_score");
        $("#bbb").val(empId);
        layer.open({
            type: 1,
            title:"新增" + empName + "的考试成绩",
            area:['25%','50%'],
            content: $("#secondTable2Form"),
            closeBtn :0, //隐藏弹出层的关闭按钮
            yes:function(index,layero){
            }
        });
    }
    //修改考试成绩
    function updateEducation(empid,stuid,stuname,score,Rescore,empId) {
        $("#secondTable2Form").attr("action","${pageContext.request.contextPath}/student/updateStudentReplyScoresss");
        $("#bbb").val(empid);
        $("#scoreId").val(stuid);
        $("#score").val(score);
        $("#Rescore").val(Rescore);
        $("#empId").val(empId);
        layer.open({
            type: 1,
            title:"修改"+stuname+ "的考试成绩",
            area:['25%','50%'],
            content: $("#secondTable2Form"),
            closeBtn :0, //隐藏弹出层的关闭按钮
            yes:function(index,layero){
            }
        });
    }
    //删除考试成绩
    function deleteEducation(jobId) {
        console.log(jobId);

        layer.confirm('是否要删除？', {
            icon:3,
            btn: ['确认','取消'] //按钮
        }, function(){+
            $.post('<%=request.getContextPath()%>/student/deleteStudent_score/' + jobId,{},
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
    function fuzhi() {
        document.getElementById("q").value="刘世阳";
        document.getElementById("w").value="123456";
        document.getElementById("e").value="18";
        document.getElementById("r").value="99999999";
        document.getElementById("t").value="江西赣州";
        document.getElementById("y").value="汉族";
        document.getElementById("u").value="江西";
        document.getElementById("o").value="36073215112312";
        document.getElementById("p").value="江西省赣州市";
        document.getElementById("d").value="茂茂";
        document.getElementById("f").value="123456789";
        document.getElementById("g").value="186461651156";
        document.getElementById("h").value="茂茂";
        document.getElementById("j").value="傻逼刘世阳";
        document.getElementById("l").value="赣州技术学院";
        document.getElementById("x").value="赣州技术学院";
        document.getElementById("v").value="廖文汉";
    }
</script>
</html>
