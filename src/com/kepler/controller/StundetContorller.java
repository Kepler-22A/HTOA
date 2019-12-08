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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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

    @RequestMapping(value = "/selStudnetAddhourById")
    public void selStudnetAddhourById(int id, HttpServletResponse response){
        response.setCharacterEncoding("utf-8");

        List list = sts.listbyId((new StudentHuorVo()).getClass(),id);

        JSONObject jo = new JSONObject();

        for (Object o : list){
            jo.put("studentHour",(StudentHuorVo)o);
        }


        System.out.println(jo);

        try {
            PrintWriter pw = response.getWriter();

            pw.print(jo.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //宿舍管理
    @RequestMapping("/studenthuor")
    public String studenthuor(){

        return "studentHour";
    }

    //表格数据
    @RequestMapping(value = "/JSONStudentHourData")
    public void JSONStudentHourData(HttpServletResponse response){
        response.setCharacterEncoding("utf-8");

        List<StudentHuorVo>  list = sts.listhour();

        JSONObject jo = new JSONObject();

        jo.put("msg","");
        jo.put("count",list.size());
        jo.put("code",0);
        jo.put("data",list);



        try {
            PrintWriter pw = response.getWriter();

            pw.print(jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //添加宿舍信息
    @RequestMapping("/addhour")
    public String addhour(StudentHuorVo studentHuorVo){
        System.out.println(studentHuorVo);

        if (studentHuorVo.getHourid()!=0){
            sts.updatehour(studentHuorVo);
        }else {
            sts.addhour(studentHuorVo);
        }
        return "redirect:/student/studenthuor";
    }


    @RequestMapping("/updateHour/{hourid}")
    public String updateHour(StudentHuorVo studentHuorVo, @PathVariable(value = "hourid")int hourid){
        System.out.println(hourid);
        studentHuorVo.setHourid(hourid);
        System.out.println(studentHuorVo);
        sts.updatehour(studentHuorVo);

        return "redirect:/student/studenthuor";
    }
    //删除宿舍资料
    @RequestMapping(value = "/delhour")
    public void delhour(StudentHuorVo studentHuorVo,HttpServletResponse response){
        System.out.println("idaaaaaaaaaa为："+studentHuorVo.getHourid());
        sts.deletehour(studentHuorVo.getHourid());

        try {
            PrintWriter pw = response.getWriter();

            pw.print("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //楼栋管理
    @RequestMapping("/studentFloor")
    public String studentFloor(Model model){
        return "studentFloor";
    }

    //添加楼栋
    @RequestMapping("/addfloor")
    public String addfloor(StudentFloorVo studentFloorVo){
        sts.addfloor(studentFloorVo);
       return "redirect:studentFloor";
    }
    //表格数据
    @RequestMapping(value = "/JSONStudentFloorData")
    public void JSONStudentFloorData(HttpServletResponse response){
        response.setCharacterEncoding("utf-8");

        List<StudentFloorVo>  list = sts.listfloor();

        JSONObject jo = new JSONObject();

        jo.put("msg","");
        jo.put("count",list.size());
        jo.put("code",0);
        jo.put("data",list);



        try {
            PrintWriter pw = response.getWriter();

            pw.print(jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //删除楼栋
    @RequestMapping("/delFloor")
    public void delFloor(StudentFloorVo studentFloorVo){
        sts.deletefloor(studentFloorVo.getFloorId());
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
