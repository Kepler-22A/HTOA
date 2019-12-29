<%--
  Created by IntelliJ IDEA.
  User: 28401
  Date: 2019/12/2
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HTOA系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/jquery-3.3.1.min.js" charset="utf-8"></script>

</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">学校管理后台</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item headName">
                <a href="javascript:">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    <span id="userNameSpan"></span>
                </a>
                <dl class="layui-nav-child">
                    <dd><a data-url="${pageContext.request.contextPath}/emp/toEmpBase" data-id="140" data-title="我的信息"  class="site-demo-active" style="cursor:pointer" data-type="tabAdd">我的信息</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item headName"><a href="${pageContext.request.contextPath}/Controller/loginOut">登出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed" id="leftLi_person">
                    <a class="" href="javascript:;">个人主页</a>
                    <dl class="layui-nav-child">
                        <dd><a data-url="/message/toEmailPage" data-id="131" data-title="我的邮件"  class="site-demo-active" style="cursor:pointer" data-type="tabAdd">我的邮件</a></dd>
                        <dd><a data-url="/leave/toEmpApplyPage" data-id="128" data-title="员工请假"  class="site-demo-active" style="cursor:pointer" data-type="tabAdd">员工请假</a></dd>
                        <dd><a data-url="/leave/toStuApplyPage" data-id="130" data-title="学生请假"  class="site-demo-active" style="cursor:pointer" data-type="tabAdd">学生请假</a></dd>
                        <dd><a data-url="/emp//toMyWeeklyPage" data-id="129" data-title="我的周报"  class="site-demo-active" style="cursor:pointer" data-type="tabAdd">我的周报</a></dd>
                        <dd><a data-url="/emp/Checking" data-id="132" data-title="考勤管理"  class="site-demo-active" style="cursor:pointer" data-type="tabAdd">考勤管理</a></dd>
                        <dd><a data-url="/leave/toDimissionPage" data-id="134" data-title="离职申请"  class="site-demo-active" style="cursor:pointer" data-type="tabAdd">离职申请</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"  id="leftLi_message">
                    <a href="javascript:;">通知公告</a>
                    <dl class="layui-nav-child">
                        <dd><a data-url="/message/notice" data-id="999" data-title="公告发布"  class="site-demo-active" style="cursor:pointer" data-type="tabAdd">公告发布</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"  id="leftLi_empCtrl">
                    <a href="javascript:;">员工管理</a>
                    <dl class="layui-nav-child">
                        <dd><a data-url="/emp/toEmpData" data-id="125" data-title="员工资料"  class="site-demo-active" style="cursor:pointer" data-type="tabAdd">员工资料</a></dd>
                        <dd><a data-url="/emp/toWeeklyCtrl" data-id="126" data-title="周报管理"  class="site-demo-active" style="cursor:pointer" data-type="tabAdd">周报管理</a></dd>
                        <dd><a data-url="/emp/chatRecord" data-id="127" data-title="谈心记录"  class="site-demo-active" style="cursor:pointer" data-type="tabAdd">谈心记录</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"  id="leftLi_classCtrl">
                    <a href="javascript:;">教务管理</a>
                    <dl class="layui-nav-child">
                        <dd><a data-url="${pageContext.request.contextPath}/student/studentdata" data-id="21" data-title="学生资料"  class="site-demo-active" style="cursor:pointer" data-type="tabAdd">学生资料</a></dd>
                        <dd><a data-url="${pageContext.request.contextPath}/student/studentScore" data-id="22" data-title="学生成绩"  class="site-demo-active" style="cursor:pointer" data-type="tabAdd">学生成绩</a></dd>
                        <dd><a data-url="${pageContext.request.contextPath}/student/studentReplyScore" data-id="23" data-title="答辩成绩"  class="site-demo-active" style="cursor:pointer" data-type="tabAdd">答辩成绩</a></dd>
                        <dd><a data-url="${pageContext.request.contextPath}/student/studentClass" data-id="24" data-title="班级管理"  class="site-demo-active" style="cursor:pointer" data-type="tabAdd">班级管理</a></dd>
                        <dd><a data-url="${pageContext.request.contextPath}/student/studentClassFenPei" data-id="25" data-title="班级分配"  class="site-demo-active" style="cursor:pointer" data-type="tabAdd">班级分配</a></dd>
                        <dd><a data-url="${pageContext.request.contextPath}/student/SelectclassType" data-id="26" data-title="课程类别"  class="site-demo-active" style="cursor:pointer" data-type="tabAdd">课程类别</a></dd>
                        <dd><a data-url="${pageContext.request.contextPath}/student/course" data-id="27" data-title="课程管理"  class="site-demo-active" style="cursor:pointer" data-type="tabAdd">课程管理</a></dd>
                        <dd><a data-url="${pageContext.request.contextPath}/student/Trial" data-id="28" data-title="试讲培训"  class="site-demo-active" style="cursor:pointer" data-type="tabAdd">试讲培训</a></dd>
                        <dd><a href="javascript:;">值班管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"  id="leftLi_logistics">
                    <a href="#">后勤管理</a>
                    <dl class="layui-nav-child">
                        <dd><a data-url="${pageContext.request.contextPath}/student/studenthuor" data-id="31" data-title="宿舍管理"  class="site-demo-active" style="cursor:pointer" data-type="studenthuor">宿舍管理</a></dd>
                        <dd><a data-url="${pageContext.request.contextPath}/student/studentFloor" data-id="32" data-title="楼栋管理"  class="site-demo-active" style="cursor:pointer" data-type="studentFloor">楼栋管理</a></dd>
                        <dd><a data-url="${pageContext.request.contextPath}/student/equipmentRepair" data-id="33" data-title="维修管理"  class="site-demo-active" style="cursor:pointer" data-type="studentFloor">维修管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"  id="leftLi_examine">
                    <a href="javascript:;">考核管理</a>
                    <dl class="layui-nav-child">
                        <dd><a data-url="/Controller/examine" data-id="41" data-title="考核指标" class="site-demo-active" data-type="tabAdd" style="cursor:pointer">考核指标</a></dd>
                        <dd id="leftLi_examine_empKaohe"><a data-url="/Controller/empExamine" data-id="42" data-title="员工考核" class="site-demo-active" data-type="tabAdd" style="cursor:pointer">员工考核</a></dd>
                        <dd>
                            <li id="leftLi_examine_kaoping" class="layui-nav-item">
                                <a href="javascript:;">教师考评</a>
                                <dl class="layui-nav-child">
                                    <dd><a data-url="/Controller/template" data-id="43" data-title="考评模板" class="site-demo-active" data-type="tabAdd" style="cursor:pointer">考评模板</a></dd>
                                    <dd id="leftLi_examine_kaoping_mission"><a data-url="/Controller/checkTask" data-id="44" data-title="考评任务" class="site-demo-active" data-type="tabAdd" style="cursor:pointer">考评任务</a></dd>
                                    <dd id="leftLi_examine_kaoping_myKaoping"><a data-url="/Controller/myCheck" data-id="45" data-title="我的考评" class="site-demo-active" data-type="tabAdd" style="cursor:pointer">我的考评</a></dd>
                                </dl>
                            </li>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item" id="leftLi_zhaosheng">
                    <a href="#">招生就业</a>
                    <dl class="layui-nav-child">
                        <dd><a data-url="/data/enrollment" data-id="309" data-title="招生信息" class="site-demo-active" data-type="tabAdd" style="cursor:pointer">招生信息</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item" id="leftLi_question">
                    <a href="#">问题反馈</a>
                    <dl class="layui-nav-child">
                        <dd><a data-url="/system/feedback" data-id="39" data-title="问题反馈" class="site-demo-active" data-type="tabAdd" style="cursor:pointer">问题反馈</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item" id="leftLi_data">
                    <a href="#">文件管理</a>
                    <dl class="layui-nav-child">
                        <dd><a data-url="/data/datadoc" data-id="3009" data-title="资料文档" class="site-demo-active" data-type="datadoc" style="cursor:pointer">资料文档</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item" id="leftLi_systemReport">
                    <a href="#">系统报表</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">系统报表</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item" id="leftLi_safe">
                    <a href="#">安全管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">权限管理</a></dd>
                        <dd><a href="javascript:;">系统日志</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item" id="leftLi_systemSet">
                    <a href="#">系统设置</a>
                    <dl class="layui-nav-child">
                        <dd><a data-url="/system/test" data-id="37" data-title="系统设置" class="site-demo-active" data-type="tabAdd" style="cursor:pointer">系统设置</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <div class="layui-body">
        <div class="layui-tab" lay-filter="demo" lay-allowclose="true">
            <ul class="layui-tab-title">
                <li class="layui-this">首页</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <div style="width: 100%;height: 100%">
                        <iframe style="width: 100%;height: 100%;border: none" src="${pageContext.request.contextPath}/emp/toMyPage"></iframe>
                    </div>
                </div>
            </div>
        </div>
        <div style="width: 18px;height: 18px;background-color: #fff;position: absolute;top: 20px;left: 56px;"></div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>

    if (${empId == null}){
        $("#userNameSpan").html('${stuName}');
    }else {
        $("#userNameSpan").html('${empName}');
    }

    layui.use('element',function() {
        var $ = layui.jquery;
        var element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块

        //触发事件
        window.active = {
            //在这里给active绑定几项事件，后面可通过active调用这些事件
            tabAdd: function(url,id,name) {
                //新增一个Tab项 传入三个参数，分别对应其标题，tab页面的地址，还有一个规定的id，是标签中data-id的属性值
                //关于tabAdd的方法所传入的参数可看layui的开发文档中基础方法部分
                element.tabAdd('demo', {
                    title: name,
                    content: '<iframe data-id="'+id+'" scrolling="auto" frameborder="0" src="'+url+'" style="width:100%;height:99%;"></iframe>',
                    id: id //规定好的id
                })

                FrameWH();  //计算ifram层的大小
            },
            tabChange: function(id) {
                //切换到指定Tab项
                element.tabChange('demo', id); //根据传入的id传入到指定的tab项
            },
            tabDelete: function (id) {
                element.tabDelete('demo', id);//删除
            }
            , tabDeleteAll: function (ids) {//删除所有
                $.each(ids, function (i,item) {
                    element.tabDelete('demo', item); //ids是一个数组，里面存放了多个id，调用tabDelete方法分别删除
                })
            }
        };

        //当点击有site-demo-active属性的标签时，即左侧菜单栏中内容 ，触发点击事件
        $('.site-demo-active').on('click', function() {
            var dataid = $(this);

            //这时会判断右侧.layui-tab-title属性下的有lay-id属性的li的数目，即已经打开的tab项数目
            if ($(".layui-tab-title li[lay-id]").length <= 0) {
                //如果比零小，则直接打开新的tab项
                active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"),dataid.attr("data-title"));
            } else {
                //否则判断该tab项是否以及存在

                var isData = false; //初始化一个标志，为false说明未打开该tab项 为true则说明已有
                $.each($(".layui-tab-title li[lay-id]"), function () {
                    //如果点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开
                    if ($(this).attr("lay-id") == dataid.attr("data-id")) {
                        isData = true;
                    }
                })
                if (isData == false) {
                    //标志为false 新增一个tab项
                    active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"),dataid.attr("data-title"));
                }
            }
            //最后不管是否新增tab，最后都转到要打开的选项页面上
            active.tabChange(dataid.attr("data-id"));
        });

        function FrameWH() {
            var h = $(window).height() -41- 10 - 60 -10-44 -10;
            $("iframe").css("height",h+"px");
        }

        $(window).resize(function () {
            FrameWH();
        })

    });

    var array = ${array};
    console.log("array:"+array);

    if (array == 3){//如果是学生登录
        $(".layui-nav-item").css("display","none");
        $("#leftLi_person").css("display","block");
        $("#leftLi_person dl dd:nth-child(1)").css("display","none");
        $("#leftLi_person dl dd:nth-child(2)").css("display","none");
        $("#leftLi_person dl dd:nth-child(4)").css("display","none");
        $("#leftLi_person dl dd:nth-child(5)").css("display","none");
        $("#leftLi_person dl dd:nth-child(6)").css("display","none");
        $("#leftLi_message").css("display","block");
        $("#leftLi_question").css("display","block");
        $("#leftLi_examine").css("display","block");


        $("#leftLi_examine dl dd:nth-child(1)").css("display","none");

        $("#leftLi_examine_kaoping").css("display","block");

        $("#leftLi_examine_kaoping_mission").css("display","block");

        $("#leftLi_examine_empKaohe").css("display","none");

        $("#leftLi_examine_kaoping_myKaoping").css("display","none");

        $(".headName").css("display","inline-block");
        $(".headName dl dd").css("display","none");

    }else if (array == 2){
    }
</script>
</body>
</html>
