package com.kepler.controller;

import com.alibaba.fastjson.JSONObject;
import com.kepler.service.EmpService;
import com.kepler.service.StudentService;
import com.kepler.vo.StudentFloorVo;
import com.kepler.vo.StudentHuorVo;
import com.kepler.vo.StudentVo;
import org.activiti.engine.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

    //宿舍管理
    @RequestMapping("/studenthuor")
    public String studenthuor(Model model){
        List<StudentHuorVo>  list = sts.listhour();
//        System.out.println(list.get(0).getHuorName());
        model.addAttribute("huorlist",list);
        return "studentHour";
    }

    //楼栋管理
    @RequestMapping("/studentFloor")
    public String studentFloor(Model model){
        List<StudentFloorVo>  list = sts.listfloor();
        System.out.println(list.size());
        model.addAttribute("floorlist",list);
        return "studentFloor";
    }
    //林5号10点 开始编辑，用于跳转学生资料展示页面
    @RequestMapping(value = "/studentdata")
    public String studentData(){


        return "studentData";
    }
    @RequestMapping(value = "/data")
    public void Data(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        List<StudentVo> sum = sts.liststudentdata();
        json.put("code",0);
        json.put("count",sum.size());
        json.put("msg","");
        json.put("data",sum);
        System.out.println(json);
        pwt.print(json.toString());
    }
    @RequestMapping(value = "/addstudent")//去添加学生的页面
    public String addstudent(){
        return "addstudent";
    }
}
