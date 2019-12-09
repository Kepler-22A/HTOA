package com.kepler.controller;

import com.alibaba.fastjson.JSONObject;
import com.kepler.service.TestService;
import com.kepler.service.impl.TestServiceImpl;
import com.kepler.vo.AuditModelVo;
import com.kepler.vo.AuditTypeVo;
import com.kepler.vo.empVo;
import org.activiti.engine.*;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
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
    public String login(empVo empVo){
        int i = service.selectLogin(empVo.getEmpName(),empVo.getPassword());
        System.out.println(i);

        if(i==1){
            System.out.print("登录成功！！");
            return "main";
        }
        System.out.print("登录失败！！！");
        return "Login";
    }
    @RequestMapping("/cheshi")
    public String cheshi(){
        return "cheshi";
    }
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
    //教师考评页面
    @RequestMapping("/teacherExamine")
    public String teacherExamiane(){
        return "teacherExamine";
    }

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
}
