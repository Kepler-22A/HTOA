package com.kepler.controller;

import com.kepler.service.EmpService;
import com.kepler.service.StudentService;
import com.kepler.vo.StudentFloorVo;
import com.kepler.vo.StudentHuorVo;
import org.activiti.engine.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    //添加宿舍信息
    @RequestMapping("/addhour")
    @ResponseBody
    public String addhour(StudentHuorVo studentHuorVo){
        if (studentHuorVo.getHourid()!=0){
            sts.updatehour(studentHuorVo);
        }else {
            sts.addhour(studentHuorVo);
        }
        return "redirect:studenthuor";
    }

    //楼栋管理
    @RequestMapping("/studentFloor")
    public String studentFloor(Model model){
        List<StudentFloorVo>  list = sts.listfloor();
        System.out.println(list.size());
        model.addAttribute("floorlist",list);
        return "studentFloor";
    }


}
