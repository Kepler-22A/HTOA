package com.kepler.controller;

import com.alibaba.fastjson.JSONObject;
import com.kepler.service.EmpService;
import com.kepler.service.StudentService;
import com.kepler.vo.EquipmentRepairVo;
import com.kepler.vo.StudentFloorVo;
import com.kepler.vo.StudentHuorVo;
import com.kepler.vo.StudentVo;
import org.activiti.engine.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    //设备维修
    @RequestMapping("/equipmentRepair")
    public String equipmentRepair(){
        return "text";
    }
    //查设备维修数据
    @RequestMapping(value = "/equipmentRepairdata")
    public void equipmentRepairdata(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        List<EquipmentRepairVo> list = sts.listEquipmentData();
        json.put("code",0);
        json.put("count",list.size());
        json.put("msg","");
        json.put("data",list);
        pwt.print(json.toString());
    }

    //添加设备维修记录
    @RequestMapping("/addequipment")
    public String addequipment(){

        return "redirect:/student/equipmentRepair";
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
        pwt.print(json.toString());
    }
    @RequestMapping(value = "/studentadd")//学生添加操作
    public String studentadd(HttpServletRequest request,StudentVo vo,String shijian,String shijian2){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(shijian);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date = java.sql.Date.valueOf(shijian);

        vo.setBirthday(date);
        Date date1 = null;
        try {
            date1 = format.parse(shijian2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date1 = java.sql.Date.valueOf(shijian2);

        vo.setBirthday(date);
        vo.setEntertime(date1);

        sts.studentADD(vo);
        return "redirect:/student/studentdata";
    }
    //根据学生id查询出学生资料
    @RequestMapping(value = "/selectStudentID")
    public void selectStudentID(int id,HttpServletResponse response) throws IOException {
        List list = sts.selectStudentIdData(id);
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        for (Object o : list){
            json.put("StudentVo",o);//返回的数据格式一定要和前端的格式一样
        }
        pwt.print(json.toJSONString());
    }
    //根据学生id查询出学生资料
    @RequestMapping(value = "/UpdateStudentID/{Studid}")
    public String UpdateStudentID(@PathVariable(value = "Studid")int Studid,StudentVo vo){
        sts.updateStudentData(vo);
        return "redirect:/student/studentdata";
    }
    //删除学生
    @RequestMapping(value = "/delstudent/{Studid}")
    public String delstudent(@PathVariable(value = "Studid")int Studid,StudentVo vo){
        vo.setStudid(Studid);
        sts.deleStudentDatas(vo);
        return "redirect:/student/studentdata";
    }
}
