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
    <title>layout 后台大布局 - Layui</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">

</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">学校管理后台</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">个人主页</a>
                    <dl class="layui-nav-child">
                        <dd><a data-url="/Controller/cheshi" data-id="11" data-title="选项1"  class="site-demo-active" data-type="tabAdd">测试</a></dd>
                        <dd><a data-url="/Controller/cheshi" data-id="22" data-title="选项2"  class="site-demo-active" data-type="tabAdd">测试2</a></dd>
                        <dd><a href="javascript:;">我的邮件</a></dd>
                        <dd><a href="javascript:;">个人请假</a></dd>
                        <dd><a href="javascript:;">我的周报</a></dd>
                        <dd><a href="javascript:;">考勤管理</a></dd>
                        <dd><a href="javascript:;">离职申请</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">通知公告</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">公告发布</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">员工管理</a>
                    <dl class="layui-nav-child">
                        <dd><a data-url="/emp/toEmpData" data-id="125" data-title="员工资料"  class="site-demo-active" style="cursor:pointer" data-type="tabAdd">员工资料</a></dd>
                        <dd><a href="javascript:;">周报管理</a></dd>
                        <dd><a href="javascript:;">谈心记录</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">教务管理</a>
                    <dl class="layui-nav-child">
                        <dd><a data-url="${pageContext.request.contextPath}/student/studentdata" data-id="21" data-title="学生资料"  class="site-demo-active" style="cursor:pointer" data-type="tabAdd">学生资料</a></dd>
                        <dd><a data-url="${pageContext.request.contextPath}/student/studentScore" data-id="22" data-title="学生成绩"  class="site-demo-active" style="cursor:pointer" data-type="tabAdd">学生成绩</a></dd>
                        <dd><a data-url="${pageContext.request.contextPath}/student/studentReplyScore" data-id="23" data-title="答辩成绩"  class="site-demo-active" style="cursor:pointer" data-type="tabAdd">答辩成绩</a></dd>
                        <dd><a data-url="${pageContext.request.contextPath}/student/studentClass" data-id="24" data-title="班级管理"  class="site-demo-active" style="cursor:pointer" data-type="tabAdd">班级管理</a></dd>
                        <dd><a data-url="${pageContext.request.contextPath}/student/studentClassFenPei" data-id="25" data-title="班级分配"  class="site-demo-active" style="cursor:pointer" data-type="tabAdd">班级分配</a></dd>
                        <dd><a href="javascript:;">课程类别</a></dd>
                        <dd><a href="javascript:;">课程管理</a></dd>
                        <dd><a href="javascript:;">试讲培训</a></dd>
                        <dd><a href="javascript:;">值班管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="#">后勤管理</a>
                    <dl class="layui-nav-child">
                        <dd><a data-url="${pageContext.request.contextPath}/student/studenthuor" data-id="31" data-title="宿舍管理"  class="site-demo-active" style="cursor:pointer" data-type="studenthuor">宿舍管理</a></dd>
                        <dd><a data-url="${pageContext.request.contextPath}/student/studentFloor" data-id="32" data-title="楼栋管理"  class="site-demo-active" style="cursor:pointer" data-type="studentFloor">楼栋管理</a></dd>
                        <dd><a data-url="${pageContext.request.contextPath}/student/equipmentRepair" data-id="33" data-title="维修管理"  class="site-demo-active" style="cursor:pointer" data-type="studentFloor">维修管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">考核管理</a>
                    <dl class="layui-nav-child">
                        <dd><a data-url="/Controller/examine" data-id="41" data-title="考核指标" class="site-demo-active" data-type="tabAdd" style="cursor:pointer">考核指标</a></dd>
                        <dd><a data-url="/Controller/empExamine" data-id="42" data-title="员工考核" class="site-demo-active" data-type="tabAdd" style="cursor:pointer">员工考核</a></dd>
                        <dd>
                            <li class="layui-nav-item">
                                <a href="javascript:;">教师考评</a>
                                <dl class="layui-nav-child">
                                    <dd><a data-url="/Controller/template" data-id="43" data-title="考评模板" class="site-demo-active" data-type="tabAdd" style="cursor:pointer">考评模板</a></dd>
                                    <dd><a data-url="/Controller/checkTask" data-id="44" data-title="考评任务" class="site-demo-active" data-type="tabAdd" style="cursor:pointer">考评任务</a></dd>
                                    <dd><a data-url="/Controller/myCheck" data-id="45" data-title="我的考评" class="site-demo-active" data-type="tabAdd" style="cursor:pointer">我的考评</a></dd>
                                </dl>
                            </li>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="#">财务管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">学费管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="#">问题反馈</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">问题反馈</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="#">招生就业</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">招生信息</a></dd>
                        <dd><a href="javascript:;">合作伙伴</a></dd>
                        <dd><a href="javascript:;">招生追踪</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="#">继续教育</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">继续教育</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="#">文件管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">资料文档</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="#">系统报表</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">系统报表</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="#">安全管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">权限管理</a></dd>
                        <dd><a href="javascript:;">系统日志</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
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
                <div class="layui-tab-item layui-show">欢迎进入宏图办公！！</div>
            </div>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
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
</script>
</body>
</html>