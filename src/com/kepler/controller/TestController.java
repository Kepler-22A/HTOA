package com.kepler.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kepler.service.TestService;
import com.kepler.vo.*;
import org.activiti.engine.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

import static oracle.net.aso.C00.*;

@Controller
@RequestMapping("/Controller")
public class TestController {//登录  考核管理！！
    @Resource
    private ProcessEngine processEngine;
    @Resource
    private TaskService taskService;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private HistoryService historyService;
    @Resource
    private RepositoryService repositoryService;

    @Resource
    private TestService service;

    @RequestMapping("/Test")
    public String toBase(HttpSession session){
        if (session.getAttribute("array") == null || Integer.parseInt(session.getAttribute("array")+"") != 0){
            session.setAttribute("array",99);
        }
        return "Login";
    }

    @RequestMapping("/changeArrayOnSession")
    @ResponseBody
    public void changeArrayOnSession(HttpSession session){
        session.setAttribute("array",99);
    }

    /**
     * 员工登录！！
     *
     */
    @RequestMapping("/login")
    public String login(empVo empVo,HttpSession  session){
        if (session.getAttribute("array") == null || Integer.parseInt(session.getAttribute("array")+"") == 0){
            return "redirect:/Controller/Test";
        }
        System.out.println("员工登录");
        if (empVo.getEmpName() == null || "".equals(empVo.getEmpName())){
            return "redirect:/Controller/Test";
        }
        int empId = service.selectInt(empVo.getEmpName()); //获的当前登陆的是谁
        session.setAttribute("empId",empId);//把当前员工信息存起来
        session.setAttribute("empName",empVo.getEmpName());
        session.setAttribute("postName",service.selEmpType(empId));//存储员工岗位

        session.removeAttribute("studentId");
        session.removeAttribute("stuName");

        return "main";
    }

    /**
     * 学生登录！！
     */
    @RequestMapping("/studentLogin")
    public String studentLogin(StudentVo studentVo,HttpSession  session){
        if (Integer.parseInt(session.getAttribute("array")+"") == 0){
            return "redirect:/Controller/Test";
        }
        System.out.println("学生登录");
        if (studentVo.getStuname() == null || "".equals(studentVo.getStuname())){
            return "redirect:/Controller/Test";
        }
        int  StdentId = service.selStudentId(studentVo.getStuname()); //获的当前登陆的是谁
        session.setAttribute("studentId",StdentId);//把当前学生信息存起来
        session.setAttribute("stuName",studentVo.getStuname());

        session.removeAttribute("empId");
        session.removeAttribute("empName");
        session.removeAttribute("postName");

        return "main";
    }

    @RequestMapping(value = "/loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("empId");
        session.removeAttribute("empName");
        session.removeAttribute("studentId");
        session.removeAttribute("stuName");
        session.removeAttribute("array");

        return "redirect:/";
    }

    @RequestMapping(value = "/checkUser/{userType}")
    @ResponseBody
    public String checkUser(@PathVariable(value = "userType")String userType,empVo empVo,StudentVo studentVo,HttpSession session){

        int i = 0;
        if ("emp".equals(userType)){ //如果是员工登录
            i = service.selectLogin(empVo.getEmpName(),empVo.getPassword());
            System.out.printf(i+"");
            if (i == 1){
                session.setAttribute("array",2);//老师
            }else {
                session.setAttribute("array",0);
            }
        }else if ("stu".equals(userType)){ //如果是学生登录
            i = service.selectStudentLogin(studentVo.getStuname(),studentVo.getPassword());
            if (i == 1){
                session.setAttribute("array",3);//学生
            }else {
                session.setAttribute("array",0);
            }
        }

        return i+"";
    }

    @RequestMapping("/cheshi")
    public String cheshi(){
        return "cheshi";
    }

    /**
     * 考核管理！！
     * @return
     */
    //考核指标页面
    @RequestMapping("/examine")
    public String Examiane(){
        return "examine";
    }
    //员工考核页面
    @RequestMapping("/empExamine")
    public String empExamiane(){
        return "empExamine";
    }


    //新增指标页面
    @RequestMapping("/addExamine")
    public String addExamine(AuditModelVo auditModelVo,HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        System.out.println(auditModelVo.getAuditName());
        int i = service.addExmaine(auditModelVo);
        if(i>0){
            System.out.println("新增指标成功！！");
        }
        System.out.println("新增指标失败！！");
        return "examine";
    }
    @RequestMapping("/delete/{auditModelID}")
    public String delete(@PathVariable(value ="auditModelID" )int auditModelID ){
        System.out.println("auditModelID:"+auditModelID);
        int i = service.delete(auditModelID);
        return "examine";
    }
    @RequestMapping("/delete2/{auditLogID}")
    public String delete2(@PathVariable(value ="auditLogID" )int auditLogID ){
        int i = service.delete2(auditLogID);
        return "empExamine";
    }
    @RequestMapping("/delete3/{templateId}")
    public String delete3(@PathVariable(value ="templateId" )int templateId ){
        int i = service.delete3(templateId);
        return "empExamine";
    }


    //查询数据
    @RequestMapping("/table")
    public void table(HttpServletResponse response){
        //System.out.println("进入table1!!!");
        response.setCharacterEncoding("utf-8");

        List list = service.selectTable();

        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",list.size());
        json.put("data",list);

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.print(json.toString());

        //System.out.println(json.toJSONString());

    }
    @RequestMapping("/table2")
    public void table2(HttpServletResponse response) throws IOException {
        System.out.println("进入table1!!!");
        response.setCharacterEncoding("utf-8");

        List list = service.selectTable2();


        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",list.size());
        json.put("data",list);

        PrintWriter out = response.getWriter();
        out.print(json.toString());

        System.out.println(json.toJSONString());

    }
    @RequestMapping("/table3")
    public void table3(HttpServletResponse response,HttpSession session) throws IOException {
        response.setCharacterEncoding("utf-8");
        List list = service.selectTable3();

        JSONObject json = new JSONObject();
//        if(session.getAttribute("empId")!=null){
//            int empId= (int) session.getAttribute("empId");
//            int leadEmpId = service.selectLead(empId);
//            if(empId ==leadEmpId){
//                System.out.println("领导！");
//                json.put("code",1);
//            }else{
//                json.put("code",0);
//            }
//        }
        json.put("code",0);
        json.put("msg","");
        json.put("count",list.size());
        json.put("data",list);

        PrintWriter out = response.getWriter();
        out.print(json.toString());

        System.out.println(json.toJSONString());

    }
    @RequestMapping("/table5")
    public void table5(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        List list = service.selectTable5();

        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",list.size());
        json.put("data",list);

        PrintWriter out = response.getWriter();
        out.print(json.toString());

        System.out.println(json.toJSONString());

    }
    @RequestMapping("/myCheck")
    public  String myCheck(){
        return "myCheck";
    }


    /**
     *  我的考评获取数据
     */

    @RequestMapping("/selectMyCheck") //考评结束显示数据！！
    public void selectMyCheck(HttpServletResponse response,HttpSession session) throws IOException {
        response.setCharacterEncoding("utf-8");
        List list = new ArrayList();
        if(session.getAttribute("empId") != null){
            list = service.selectOverCheck();
        }
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",list.size());
        json.put("data",list);

        PrintWriter out = response.getWriter();
        out.print(json.toString());

        System.out.println(json.toJSONString());
    }

    @RequestMapping("toCheckScore/{templateId}") //去考评页
    public String toCheckScore(@PathVariable(value = "templateId")Integer templateId,HttpSession session){
        session.setAttribute("templateId",templateId);
        return "checkScore";
    }
    @RequestMapping("checkScore")  //我的考评！！
    public void checkScore(HttpServletResponse response,HttpSession session) throws IOException {
        response.setCharacterEncoding("utf-8");
        List list = new ArrayList();
        if(session.getAttribute("empId")!=null) {
            System.out.println("进入！！");
            int empId = (int) session.getAttribute("empId");
            int templateId = (int) session.getAttribute("templateId");
            list = service.selectMyCheck(templateId,empId); //查询我的考评数据
        }
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",list.size());
        json.put("data",list);

        PrintWriter out = response.getWriter();
        out.print(json.toString());

        System.out.println(json.toJSONString());
        //return "checkScore";
    }

    /**
     * 教师考评！！
     */
    @RequestMapping("/template")//模板！！
    public String template(){
        return "template";
    }
    @RequestMapping("/addTemplate")//新增页！！
    public String toAddTemplate (){
        return "addTemplate";
    }
    @RequestMapping("/checkTask")//任务页
    public String checkTask(){
        return "checkTask";
    }

    /**
     * 考评项目操作！！！
     */
    @RequestMapping("/AddTemplate")//新增修改！！
    public void addTemplate(TemplateVo templateVo, HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException{
        System.out.println("进入新增模板");
        request.setCharacterEncoding("utf-8");
        //获取当前用户
        int str = (int) session.getAttribute("empId");
        templateVo.setEmpId(str);
        System.out.println("empId:"+str);
        //时间
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        templateVo.setTemplateTime(format.format(date));

        templateVo.setOpenCheck(1);

        int i = service.addTemplate(templateVo);
        if(i>0){
            //查出模板Id
            System.out.println("新增成功！！");
            int templateId = service.selectInt2(templateVo.getTemplateTime());
            session.setAttribute("templateId",templateId);
        }
    }

    @RequestMapping("/addTemplate2/{type}")  //项目新增~~~~~
    public void addTemplate(checkProjectVo checkProjectVo,checkStepVo setpvo,checkResultVo resultVo ,HttpSession session,@PathVariable(value = "type")String type,String beginTimeEX,String endTimeEX){
        System.out.println("进入项目保存！！");
        System.out.println("type:"+type);
        //获取templateId
        int templateId = (int) session.getAttribute("templateId");
        session.setAttribute("templateId",templateId);
        System.out.println("templateId:"+templateId);

        if("project".equals(type)){         //新增项目！！
            checkProjectVo.setTemplateId(templateId);
            int i= service.addCheckProject(checkProjectVo);
            if(i>0){
                System.out.println("项目新增成功！");
            }
        }
        else if("setp".equals(type)){
            String setpType = setpvo.getCheckStepType();
            System.out.printf(beginTimeEX + "  " + endTimeEX);
//
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//            Date d1 = null;
//            Date d2 = null;
//            try {
//                d1 = sdf.parse(beginTimeEX);
//                d2 = sdf.parse(endTimeEX);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            setpvo.setBeginTime(d1);
//            setpvo.setEndTime(d2);

            //转换时间
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            setpvo.setBeginTime(format.format(date));
            //判断步骤类型
            if("学生评".equals(setpType)){
                setpvo.setStep(1);
            }else if("领导评".equals(setpType)) {
                setpvo.setStep(2);
            }
            //获取templateId
            int templateId2 = (int) session.getAttribute("templateId");
            session.setAttribute("templateId",templateId);

            setpvo.setTemplateId(templateId2);
            float width = setpvo.getWeight();
            String widthStr = new Formatter().format("%.2f", width).toString();
            System.out.printf(widthStr);
            setpvo.setWeight(Float.parseFloat(widthStr));
            int i = service.addCheckSetp(setpvo);
            if (i>0) {
                System.out.println("项目步骤成功！");
            }
        }
        else if("result".equals(type)){      //新增考评标准！！
            int templateId3 = (int) session.getAttribute("templateId");
            session.setAttribute("templateId",templateId);
            setpvo.setTemplateId(templateId3);

            int i= service.addCheckResult(resultVo);
            if(i>0){
                System.out.println("项目标准成功！");
            }
        }
    }
    //查看项目数据
    @RequestMapping("/templateTable/{table}/{templateId}")
    public void templateTable(HttpServletResponse response,@PathVariable(value = "table") String table,@PathVariable(value = "templateId") Integer  templateId){
        response.setCharacterEncoding("utf-8");
        System.out.println("table:"+table);
        List list  = new ArrayList();
        if("table1".equals(table)){
            list = service.selectProject(templateId);
        }
        else if("table2".equals(table)){
            list = service.selectSetp(templateId);
        }
        else if("table3".equals(table)){
            list = service.selectResult(templateId);
        }


        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",list.size());
        json.put("data",list);

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.print(json.toString());
    }


    /**
     * 开启考评！！！！
     */

    @RequestMapping("/openCheck/{templateId}/{depName}") //开启考评
    public void openCheck(HttpSession session,@PathVariable(value = "templateId")Integer templateId
            ,@PathVariable(value = "depName")String depName,checkPeopleVo peopleVo){
        System.out.println("进入开启考评！！"+templateId+",,"+depName);
        session.setAttribute("depName",depName);
        List checkPeople = service.selectCheckPeople(templateId);

        //int depId = service.selectDepID(templateId);
        int i = service.update(templateId);
        if(i>0){
            //增加考评人
            for(int t = 0,len =checkPeople.size();t<len ;t++){
                peopleVo.setTemplateId(templateId);
                String empId = (checkPeople.get(t) + "").substring(7,(checkPeople.get(t) + "").length() - 1);
                peopleVo.setEmpId(Integer.parseInt(empId));
                peopleVo.setLeadState("待考评");
                service.addCheckPeople(peopleVo);
                if (t == checkPeople.size()-1) {
                    System.out.println("开启考评成功！！");
                }
            }
        }
    }
    @RequestMapping("/closeCheck/{templateId}/{depName}") //关闭考评
    public void closeCheck(HttpSession session,@PathVariable(value = "templateId")Integer templateId
            ,@PathVariable(value = "depName")String depName,checkScoerVo scoerVo){
        System.out.println("进入关闭考评！！"+templateId+",,"+depName);
        session.setAttribute("depName",depName);

        //int depId = service.selectDepID(templateId);
        int i = service.updateClose(templateId);
        if(i>0 &&session.getAttribute("empId")!=null) {
            int empId = (int) session.getAttribute("empId");

            //根据权重算出总得分
            Float studentWeight = service.selectWeight(templateId);
            Float leadWeight = service.selectWeight2(templateId);
            //获取学生,领导评分
            float studentScore = service.selectScore(empId)*studentWeight;
            float leadScore = service.selectScore2(empId)*leadWeight;
            float total = studentScore + leadScore ;
            //存取总考评成绩
            scoerVo.setTemplateId(templateId);
            scoerVo.setEmpId(empId);
            scoerVo.setStudentComment(studentScore);
            scoerVo.setLeadComment(leadScore);
            scoerVo.setTotal(total);
            int d = service.addCheckScore(scoerVo);
            int t = service.deletePeople(templateId);
            if(t>0 && d>0){
                System.out.println("关闭考评成功！！");
            }
        }
    }


    @RequestMapping("/table4") //获取开启考评的模板数据！！
    public void table4(HttpServletResponse response ,HttpSession session) throws Exception {
        response.setCharacterEncoding("utf-8");
        List list = new ArrayList();
        //只有开启的教研部和学工部考评才会显示
        if(session.getAttribute("studentId") != null){
            System.out.println("学生！");
            int depId = service.selectDepId();
            session.setAttribute("teacherType",depId);
            int key = 0 ;
            if (session.getAttribute("key") != null){
                key = (int) session.getAttribute("key");
            }
            if((depId==2 ||depId == 3) &&key!=2){
                list = service.selectTable4();
            }
        }
        //只有开启有领导考评的指定的部门领导才会显示
        else if(session.getAttribute("empId") !=null){
            int empId= (int) session.getAttribute("empId");
            int leadEmpId = service.selectLead(empId);

            if(empId ==leadEmpId){
                System.out.println("领导！");
                list = service.selectTable4();
            }
        }
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",list.size());
        json.put("data",list);

        PrintWriter out = response.getWriter();
        out.print(json.toString());

        System.out.println(json.toJSONString());
    }

    @RequestMapping("/checkMark/{templateId}") //考评打分页
    public String checkMark(@PathVariable(value = "templateId")Integer templateId ,HttpSession session){  //考评打分
        List list = service.selectProject(templateId);
        //学生
        if(session.getAttribute("studentId") !=null) {
            //获取classId
            int stuId = (int) session.getAttribute("studentId");
            if(session.getAttribute("teacherType")!=null){
                int depId = (int) session.getAttribute("teacherType");
                if(depId==3){
                    String teacher = service.selectTeacher(stuId);//查出班主任
                    session.setAttribute("teacher",teacher);
                }else if(depId == 2){
                    String teacher = service.selectTeacher2(stuId);//查出任课老师
                    session.setAttribute("teacher",teacher);
                }
            }
        }
        //领导
        else if(session.getAttribute("empId") !=null){
            int empId = (int) session.getAttribute("empId");
            session.setAttribute("empId",empId);
            return "checkPeople";
        }
        System.out.println("考评数据:"+list);
        session.setAttribute("markTemplateId",templateId);
        session.setAttribute("project",list);
        return "checkMark";
    }

    @RequestMapping("leadCheckMark/{templateId}/{empName}")  //领导打分页
    public String  leadCheckMark(@PathVariable(value = "templateId")Integer templateId,@PathVariable(value = "empName")String empName  ,HttpSession session) throws UnsupportedEncodingException {
        empName = new String(empName.getBytes("ISO-8859-1"),"UTF-8");
        List list = service.selectProject(templateId);
        System.out.println(empName);
        session.setAttribute("teacher",empName);
        session.setAttribute("markTemplateId",templateId);
        session.setAttribute("project",list);
        return "checkMark";
    }
    @RequestMapping("addMark")  //打分！！！！！
    public String addMark(studentCheckScoreVo scoreVo,leadCheckScoreVo leadCheckScoreVo,HttpSession session,
                          @RequestParam(value = "projectId", required = false) int[] projectId,
                          @RequestParam(value = "checkScore", required = false) int[] checkScore){

        //学生打分
        if(session.getAttribute("studentId") !=null){
            int  stuId = (int) session.getAttribute("studentId");

            int teacherId = service.selectTeacherId(stuId);//查出班主任Id
            int stuclassId  = service.selectStuClassId(stuId);
            int markTemplateId = (int) session.getAttribute("markTemplateId");

            for(int i = 0,len = projectId.length ; i<len ; i++){
                System.out.println("进入第"+i+"次");
                scoreVo.setClassId(stuclassId);
                scoreVo.setEmpId(teacherId);
                scoreVo.setTemplateId(markTemplateId);
                scoreVo.setProjectId(projectId[i]);
                scoreVo.setCheckScore(checkScore[i]);
                int t= service.addMark(scoreVo);
                if(i==len-1){
                    int key = 2 ;
                    session.setAttribute("key",key);
                    System.out.println("成功打分！！");
                    return "checkTask";
                }
            }
        }
        //领导打分
        else if(session.getAttribute("empId")!=null){
            int empId = service.selectTeacherID((String) session.getAttribute("teacher"));
            int markTemplateId = (int) session.getAttribute("markTemplateId");
            int depId = service.selectDepId2((String) session.getAttribute("teacher"));
            for(int i = 0,len = projectId.length ; i<len ; i++){
                System.out.println("进入第"+i+"次");
                leadCheckScoreVo.setDepId(depId);
                leadCheckScoreVo.setEmpId(empId);
                leadCheckScoreVo.setTemplate(markTemplateId);
                leadCheckScoreVo.setProjectId(projectId[i]);
                leadCheckScoreVo.setCheckScore(checkScore[i]);
                int t= service.addLeadMark(leadCheckScoreVo);
                if(i==len-1){
                    int y = service.update2(empId);
                    if(y>0) {
                        System.out.println("成功打分！！");
                        return "checkPeople";
                    }
                }
            }
        }
        return "checkMark";
    }



}

