package com.kepler.controller;

import com.kepler.service.TestService;
import com.kepler.service.impl.TestServiceImpl;
import com.kepler.vo.empVo;
import org.activiti.engine.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/Controller")
public class TestController {
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
    public String toBase(empVo empVo){
        int i = service.selectLogin(empVo.getEmpName(),empVo.getPassword());
        System.out.println(i);

        if(i==1){
            System.out.print("登录成功！！");
            return "cheshi";
        }
        System.out.print("登录失败！！！");
        return "Login";
    }
}
