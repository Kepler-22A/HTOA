package com.kepler.controller;

import com.alibaba.fastjson.JSONObject;
import com.kepler.service.TestService;
import com.kepler.vo.*;
import org.activiti.engine.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @RequestMapping("/login")
    public String login(empVo empVo,HttpSession  session){
        int i = service.selectLogin(empVo.getEmpName(),empVo.getPassword());
        int  empId = service.selectInt(empVo.getEmpName());
        System.out.println("empId:"+empId);

        if(i==1){
            System.out.print("登录成功！！");
            session.setAttribute("empId",empId);//把当前员工信息存起来
            return "main";
        }
        System.out.print("登录失败！！！");
        return "Login";
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
    //新增指标
    @RequestMapping("/addExamine")
    public String addExamine(AuditModelVo auditModelVo,HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        int i = service.addExmaine(auditModelVo);
        if(i>0){
            System.out.println("新增指标成功！！");
        }
        System.out.println("新增指标失败！！");
        return "examine";
    }
    //查询数据
    @RequestMapping("/table")
    public void table(HttpServletResponse response) throws IOException {
        //System.out.println("进入table1!!!");
        response.setCharacterEncoding("utf-8");

        List list = service.selectTable();

        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",list.size());
        json.put("data",list);

        PrintWriter out = response.getWriter();
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
    @RequestMapping("/AddTemplate")//新增修改！！
    public String addTemplate(TemplateVo templateVo, checkProjectVo checkProjectVo, HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException {
        System.out.println("进入toAddTemplate");
        request.setCharacterEncoding("utf-8");
        //获取当前用户
         int str = (int) session.getAttribute("empId");
         templateVo.setEmpId(str);
         System.out.println("empId:"+str);
        //时间
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        templateVo.setTemplateTime(format.format(date));

        int i = service.addTemplate(templateVo);
        if(i>0){
            //查出模板Id
            int templateId = service.selectInt2(templateVo.getTemplateTime());
            session.setAttribute("templateId",templateId);
        }
        return "addTemplate";
    }
    @RequestMapping("/checkTask")//任务
    public String checkTask(){
        return "checkTask";
    }
    @RequestMapping("/myCheck")
    public String myCheck(){
        return "myCheck";
    }

}
