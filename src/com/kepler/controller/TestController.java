package com.kepler.controller;

import com.kepler.service.impl.TestServiceImpl;
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
    private TestServiceImpl service;

    @RequestMapping("/Test")
    public String toBase(){

        return "cheshi";
    }
}
