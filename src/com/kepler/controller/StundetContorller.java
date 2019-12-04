package com.kepler.controller;

import com.kepler.service.EmpService;
import com.kepler.service.StudentService;
import com.kepler.vo.StudentHuorVo;
import org.activiti.engine.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ASUS on 2019/12/4.
 */
@Controller
@RequestMapping("student")
public class StundetContorller {
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
    private StudentService sts;

    @RequestMapping("/studenthuor")
    public String studenthuor(Model model){
        List<StudentHuorVo>  list = sts.listhour();
        System.out.println(list.get(0).getHuorName());
        model.addAttribute("huorlist",list);
        return "studentHour";
    }
}
