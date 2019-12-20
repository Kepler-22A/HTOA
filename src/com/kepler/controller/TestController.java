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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public String toBase(){
        return "Login";
    }

    /**
     * 登录！！
     *
     */
    @RequestMapping("/login")
    public String login(empVo empVo,HttpSession  session){
        int i = service.selectLogin(empVo.getEmpName(),empVo.getPassword());
        int  empId = service.selectInt(empVo.getEmpName()); //获的当前登陆的是谁
        if(i==1){
            session.setAttribute("empId",empId);//把当前员工信息存起来
            session.setAttribute("empName",empVo.getEmpName());
            //判断登陆的是憨憨老师还是帅气学生
            int OK =  service.OKAccount(empVo.getEmpName(),empVo.getPassword());
            if(OK>0){//有值就是老师
                session.setAttribute("array",2);//老师
            }else {
                session.setAttribute("array",3);//学生
            }
            return "main";
        }else {
            return "redirect:/Controller/Login";
        }
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
    public void table3(HttpServletResponse response) throws IOException {
        System.out.println("进入table1!!!");
        response.setCharacterEncoding("utf-8");
        List list = service.selectTable3();

        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",list.size());
        json.put("data",list);

        PrintWriter out = response.getWriter();
        out.print(json.toString());

        System.out.println(json.toJSONString());

    }
    @RequestMapping("selectMyCheck")
    public void selectMyCheck(int templateId,int empId){
        List list = service.selectMyCheckProject(templateId,empId);
        String beginTime = service.selectTime(templateId);
        int total = service.selectTotal(templateId);

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
    @RequestMapping("/checkTask")//任务
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

        int templateId = (int) session.getAttribute("templateId");
        System.out.println("templateId:"+templateId);

        if("project".equals(type)){         //新增项目！！
            checkProjectVo.setTemplateId(templateId);
            int i= service.addCheckProject(checkProjectVo);
            if(i>0){
                System.out.println("项目新增成功！");
            }
        }
        else if("setp".equals(type)){   //新增步骤！！
            String setpType = setpvo.getCheckStepType();
            System.out.printf(beginTimeEX + "  " + endTimeEX);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date d1 = null;
            Date d2 = null;

            try {
                d1 = sdf.parse(beginTimeEX);
                d2 = sdf.parse(endTimeEX);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            setpvo.setBeginTime(d1);
            setpvo.setEndTime(d2);

            if("自评".equals(setpType)){
                setpvo.setStep(1);
            }else {
                setpvo.setStep(2);
            }
            int i = service.addCheckSetp(setpvo);
            if (i>0) {
                System.out.println("项目步骤成功！");
            }
        }
        else if("result".equals(type)){      //新增考评标准！！
            setpvo.setTemplateId(templateId);
            int i= service.addCheckResult(resultVo);
            if(i>0){
                System.out.println("项目标准成功！");
            }
        }
    }
    //查看项目数据
    @RequestMapping("/templateTable/{table}/{templateTime}")
    public void templateTable(HttpServletResponse response,HttpSession session,@PathVariable(value = "table") String table,@PathVariable(value = "templateTime") long  time){
        response.setCharacterEncoding("utf-8");
        System.out.println("table:"+table);
        List list  = new ArrayList();
        //时间
        System.out.println(time);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        date.setTime(time);
        int  templateId = service.selectInt2(sdf.format(date));
        //int templateId2 = (int) session.getAttribute("templateId");
        System.out.println("templateId:"+templateId+":2:");

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

}
