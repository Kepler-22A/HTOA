package com.kepler.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kepler.service.EmpService;
import com.kepler.service.StudentService;
import com.kepler.vo.*;
import org.activiti.engine.*;
import org.apache.log4j.helpers.DateTimeDateFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        return "equipmentRepair";
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
    @RequestMapping(value = "/selEquipmentById")
    public void selEquipmentById(int id, HttpServletResponse response){
        response.setCharacterEncoding("utf-8");

        List list = sts.listEquipmentbyId((new EquipmentRepairVo()).getClass(),id);

        JSONObject jo = new JSONObject();

        for (Object o : list){
            jo.put("equipment",(EquipmentRepairVo)o);
        }


        System.out.println(jo);

        try {
            PrintWriter pw = response.getWriter();

            pw.print(jo.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //添加设备维修记录
    @RequestMapping("/addequipment")
    public String addequipment(EquipmentRepairVo equipmentRepairVo){
        equipmentRepairVo.setStartTime(new Date());
        sts.AddEuipment(equipmentRepairVo);
        return "redirect:/student/equipmentRepair";
    }
    //修改设备维修信息
    @RequestMapping("/updateEquiment/{equipmentId}")
    public String updateEquiment(EquipmentRepairVo equipmentRepairVo, @PathVariable(value = "equipmentId")int equipmentId){
//        System.out.println(hourid);
        equipmentRepairVo.setEquipmentId(equipmentId);
//        System.out.println(studentHuorVo);
        sts.updateEquiment(equipmentRepairVo);

        return "redirect:/student/equipmentRepair";
    }
    //完成设备维修
    @RequestMapping("/finishEquipment/{equipmentId}")
    public String FinishEquipment(@PathVariable(value = "equipmentId")int equipmentId){

        List list = sts.listEquipmentbyId(EquipmentRepairVo.class,equipmentId);
        EquipmentRepairVo equipmentRepairVo = new EquipmentRepairVo();
        for (Object o : list){
            equipmentRepairVo = (EquipmentRepairVo)o;
        }
        equipmentRepairVo.setEndTime(new Date());
        equipmentRepairVo.setStatus(1);  //0 未完成  1 完成
//        System.out.println(equipmentRepairVo);
        sts.updateEquiment(equipmentRepairVo);

        return "redirect:/student/equipmentRepair";
    }
    //刪除設備維修記錄
    @RequestMapping("/delEquipment")
    public String delEquipment(EquipmentRepairVo equipmentRepairVo){
        sts.delEquipment(equipmentRepairVo.getEquipmentId());
        return "equipmentRepair";
    }
//--------------------------------------------------------------------------------------------------------------

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
    //根据学生id修改出学生资料
    @RequestMapping(value = "/UpdateStudentID/{Studid}")
    public String UpdateStudentID(@PathVariable(value = "Studid")int Studid,StudentVo vo){
        sts.updateStudentData(vo);
        return "redirect:/student/studentdata";
    }
    //删除学生
    @RequestMapping(value = "/delstudent/{Studid}")
    @ResponseBody
    public void delstudent(@PathVariable(value = "Studid")int Studid,StudentVo vo){
        vo.setStudid(Studid);
        //再删除考试成绩和答辩成绩
       List list =  sts.selectStudent_score(Studid);
       Map map = (Map) list.get(0);
       Student_scoreVo ss = new Student_scoreVo();
       ss.setScoreId((Integer) map.get("scoreId"));
        sts.deleteStudent_score_id(ss);
        sts.deleStudentDatas(vo);
    }
    //林12-9写查询学生成绩页面
    @RequestMapping(value = "/studentScore")//跳转到学生成绩页面
    public String studentfengshu(){
        return "studentScore";
    }
    @RequestMapping(value = "/Ling_Score")
    public void Ling_Score(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter ptw = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        List list = sts.listStudentScore();
        jsonObject.put("code",0);
        jsonObject.put("count",list.size());
        jsonObject.put("msg","");
        jsonObject.put("data",list);
        ptw.print(jsonObject.toJSONString());
    }
    @RequestMapping(value = "/studentReplyScore")//跳转到答辩页面
    public String studentReplyScore(){
        return "studentReplyScore";
    }
    @RequestMapping(value = "/Ling_ReplyScore")
    public void Ling_ScoreReplyScore(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter ptw = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        List list = sts.listStudentReplyScore();
        jsonObject.put("code",0);
        jsonObject.put("count",list.size());
        jsonObject.put("msg","");
        jsonObject.put("data",list);
        ptw.print(jsonObject.toJSONString());
    }
    //跳转到班级管理页面
    @RequestMapping(value = "/studentClass")
    public String studentClass(){
        return "studentClass";
    }
    //查询班级信息
    @RequestMapping(value = "/selectstudentClass")
    public void selectstudentClass(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter ptw = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        List list = sts.selectStudentClass();
        jsonObject.put("code",0);
        jsonObject.put("count",list.size());
        jsonObject.put("msg","");
        jsonObject.put("data",list);
        ptw.print(jsonObject.toJSONString());
    }
    //添加班级信息
    //12-10
    //根据ajax查询老师的名字
    @RequestMapping(value = "/teacherNameAjax")
    public void teacherNameAjax(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt =  response.getWriter();
        JSONObject jsonObject = new JSONObject();
        //查询出老师的名字
        List<empVo> list = sts.listTeacherName();
        jsonObject.put("name",list);
        pwt.print(jsonObject.toJSONString());
        pwt.flush();
        pwt.close();
    }
    //根据ajax查询班主任的名字
    @RequestMapping(value = "/ClassTeacherNameAjax")
    public void ClassTeacherNameAjax(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt =  response.getWriter();
        JSONObject jsonObject = new JSONObject();
        //查询出老师的名字
        List<empVo> list = sts.listClassTeacherName();
        jsonObject.put("name1",list);
        pwt.print(jsonObject.toJSONString());
        pwt.flush();
        pwt.close();
    }
    //根据ajax查询班级类别的名字
    @RequestMapping(value = "/ClassTypeAjax")
    public void ClassTypeAjax(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt =  response.getWriter();
        JSONObject jsonObject = new JSONObject();
        //查询出老师的名字
        List<empVo> list = sts.listClassType();
        jsonObject.put("name2",list);
        pwt.print(jsonObject.toJSONString());
        pwt.flush();
        pwt.close();
    }
    //根据ajax查询系列
    @RequestMapping(value = "/selectdeptId")
    public void selectdeptId(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt =  response.getWriter();
        JSONObject jsonObject = new JSONObject();
        //查询出老师的名字
        List<empVo> list = sts.listDept();
        jsonObject.put("name3",list);
        pwt.print(jsonObject.toJSONString());
        pwt.flush();
        pwt.close();
    }
    //根据Ajax查询专业名称
    @RequestMapping(value = "/selectmajorId")
    public void selectmajorId(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt =  response.getWriter();
        JSONObject jsonObject = new JSONObject();
        //查询出老师的名字
        List<empVo> list = sts.listmajorId();
        jsonObject.put("name4",list);
        pwt.print(jsonObject.toJSONString());
        pwt.flush();
        pwt.close();
    }
    //班级管理信息提交
    @RequestMapping(value = "/studentClassAdd")
    public String studentClass(StudentClassVo vo){
        sts.studentClassAdd(vo);
        return "redirect:/student/studentClass";
    }
    //根据班级管理id查询出班级管理信息
    @RequestMapping(value = "/selectStudentClassID")
    public void selectStudentClassID(int id,HttpServletResponse response) throws IOException {
        List list = sts.seleceStudentClassID(id);
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        for (Object o : list){
            json.put("StudentVo",o);//返回的数据格式一定要和前端的格式一样
        }
        pwt.print(json.toJSONString());
    }
    //根据班级管理id修改出班级管理
    @RequestMapping(value = "/UpdateStudentClassID/{classid}")
    public String UpdateStudentClassID(@PathVariable(value = "classid")int classid,StudentClassVo vo){
        sts.updateStudentClassData(vo);
        return "redirect:/student/studentClass";
    }
    //删除学生
    @RequestMapping(value = "/delstudentClassID/{classid}")
    @ResponseBody
    public String delstudentClassID(@PathVariable(value = "classid")int Studid,StudentClassVo vo){
        vo.setClassid(Studid);
        sts.deleStudentClassTeacherDatas(vo);
        return "redirect:/student/studentClass";
    }
    //班级分配
    @RequestMapping(value = "/studentClassFenPei")
    public String studentClassFenPei(){
        return "studentClassFenPei";
    }
    //班级树行管理
    @RequestMapping(value = "/StudetnClasstree")
    public void StudetnClasstree(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        JSONArray objects = new JSONArray();//树
        //子节点
        Map map = new HashMap();
        map.put("id",0);
        map.put("text","班级列表");
        map.put("iconCls","icon-ok");

        //子节点下的节点
        List<Map> account = sts.listClasstree();
        JSONArray Aaa = new JSONArray();
        for(Map m : account){//增强for循环
            Map map1 = new HashMap();
            map1.put("id",m.get("classid"));
            map1.put("text",m.get("falled"));
            List<Map> account1 = sts.selectClass(String.valueOf(m.get("falled")));
            JSONArray Bbb = new JSONArray();
            for(Map mm : account1){
                Map map2 = new HashMap();
                map2.put("id",mm.get("classid"));
                map2.put("text",mm.get("className"));
                Bbb.add(map2);
            }
            map1.put("children",Bbb);
            Aaa.add(map1);
            map.put("children",Aaa);
        }
        objects.add(map);
        out.print(objects.toJSONString());
    }
    //点击班级分配信息查询出班级信息
    @RequestMapping(value = "/selectClassTree/{SJ}")
    public void selectClassTree(@PathVariable(value = "SJ") String ClassName,HttpServletResponse response) throws IOException {
        ClassName = new String(ClassName.getBytes("ISO-8859-1"),"UTF-8");
        List list = sts.Customss(ClassName);
        response.setCharacterEncoding("utf-8");
        PrintWriter ptw = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("count",list.size());
        jsonObject.put("msg","");
        jsonObject.put("data",list);
        ptw.print(jsonObject.toJSONString());
    }
    //查询出还没有分班的学生
    @RequestMapping(value = "/selectNOClassStudentData")
    public void selectNOClassStudentData(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter ptw = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        List list = sts.selectNOClassStudentData();
        jsonObject.put("code",0);
        jsonObject.put("count",list.size());
        jsonObject.put("msg","");
        jsonObject.put("data",list);
        ptw.print(jsonObject.toJSONString());
    }
    //跳转课程类别
    @RequestMapping(value = "/SelectclassType")
    public String SelectclassType(){
        return "SelectclassType";
    }
    //跳转课程管理
    @RequestMapping(value = "/course")
    public String course(){
        return "course";
    }
    //新增课程管理信息
    @RequestMapping(value = "/Addcourse")
    public String Addcourse(){
        return "redirect:/student/course";
    }
    //附表新增学生答辩成绩
    @RequestMapping(value = "/addStudentReplyScore")
    public String addStudentReplyScore(StudentReplyScoreVo vo){
        vo.setScore7(vo.getScore1()+vo.getScore2()+vo.getScore3()+vo.getScore4()+vo.getScore5()+vo.getScore6());
        sts.addStudentReplyScore(vo);
        return "redirect:/student/studentdata";
    }
    //根据学生id查询出学生答辩成绩
    @RequestMapping(value = "/StudentReplyScore/{id}")
    public void StudentReplyScore(@PathVariable(value = "id") int id,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter ptw = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        List list =  sts.selectStudentReplyScore(id);
        jsonObject.put("code",0);
        jsonObject.put("count",list.size());
        jsonObject.put("msg","");
        jsonObject.put("data",list);
        ptw.print(jsonObject.toJSONString());
    }
    //修改学生答辩成绩
    @RequestMapping(value = "/updateStudentReplyScore")
    public String updateStudentReplyScore(StudentReplyScoreVo vo){
        vo.setScore7(vo.getScore1()+vo.getScore2()+vo.getScore3()+vo.getScore4()+vo.getScore5()+vo.getScore6());
        sts.updateStudentReplyScore(vo);
        return "redirect:/student/studentdata";
    }
    //deleteStudentReplyScore
    //删除学生答辩成绩
    @RequestMapping(value = "/deleteStudentReplyScore/{jobId}")
    @ResponseBody
    public void delstudentClassID(@PathVariable(value = "jobId")int jobId,StudentReplyScoreVo vo){
        vo.setReplyId(jobId);
        sts.delectStudentReplyScore(vo);
    }
    //查询出课程名称
    @RequestMapping(value = "/courseIdAjax")
    public void courseIdAjax(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt =  response.getWriter();
        JSONObject jsonObject = new JSONObject();
        //查询出老师的名字
        List<empVo> list = sts.selectcourseId();
        jsonObject.put("course",list);
        pwt.print(jsonObject.toJSONString());
        pwt.flush();
        pwt.close();
    }
    //新增考试成绩
    @RequestMapping(value = "/addStudent_score")
    public String addStudent_score(String shijian2,String remarks, HttpSession session,Student_scoreVo vo){
        int  empid = (int) session.getAttribute("empId");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(shijian2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date = java.sql.Date.valueOf(shijian2);
        vo.setScoreTime(date);//转换时间
        vo.setEmpid(empid);
        vo.setRemark(remarks);
        sts.addStudent_score(vo);
        return "redirect:/student/studentdata";
    }
    //selectStudent_score
    //根据学生id查询出学生考试成绩
    @RequestMapping(value = "/selectStudent_score/{id}")
    public void selectStudent_score(@PathVariable(value = "id") int id,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter ptw = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        List list =  sts.selestStudent_score(id);
        jsonObject.put("code",0);
        jsonObject.put("count",list.size());
        jsonObject.put("msg","");
        jsonObject.put("data",list);
        ptw.print(jsonObject.toJSONString());
    }
    //修改学生考试成绩
    @RequestMapping(value = "/updateStudentReplyScoresss")
    public String updateStudentReplyScore(Student_scoreVo vo,String shijian2,String remarks,HttpSession session){
        int  empid = (int) session.getAttribute("empId");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(shijian2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date = java.sql.Date.valueOf(shijian2);
        vo.setScoreTime(date);//转换时间
        vo.setEmpid(empid);
        vo.setRemark(remarks);
        sts.updateStudent_score(vo);
        return "redirect:/student/studentdata";
    }
    //删除学生考试成绩
    @RequestMapping(value = "/deleteStudent_score/{jobId}")
    @ResponseBody
    public void deleteStudent_score(@PathVariable(value = "jobId")int jobId,Student_scoreVo vo){
        vo.setScoreId(jobId);
        sts.deleteStudent_score(vo);
    }
    //根据Ajax查询班级名称
    @RequestMapping(value = "/studentClassAjax")
    public void studentClassAjax(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt =  response.getWriter();
        JSONObject jsonObject = new JSONObject();

        List<StudentClassVo> list = sts.studentClassAjax();
        jsonObject.put("name4",list);
        pwt.print(jsonObject.toJSONString());
        pwt.flush();
        pwt.close();
    }
    //查询出课程类别信息
    @RequestMapping(value = "/selectClassType2")
    public void selectClassType2(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter ptw = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        List list =  sts.selectClassType2();
        jsonObject.put("code",0);
        jsonObject.put("count",list.size());
        jsonObject.put("msg","");
        jsonObject.put("data",list);
        ptw.print(jsonObject.toJSONString());
    }
    //新增课程类别信息
    @RequestMapping(value = "/AddClassType")
    public String AddClassType(CourseTypeVo vo){
        sts.AddClassType(vo);
        return "redirect:/student/SelectclassType";
    }
    //根据id查询出课程类别信息
    @RequestMapping(value = "/selectClassType5/{id}")
    public void selectClassType3(@PathVariable(value = "id") int id,HttpServletResponse response) throws IOException {
        List list = sts.selectClassType3(id);
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        for (Object o : list){
            json.put("StudentVo",o);//返回的数据格式一定要和前端的格式一样
        }
        pwt.print(json.toJSONString());
    }
    //根据id修改程类别信息
    @RequestMapping(value = "/updateClassType5/{classid}")
    public String updateClassType5(@PathVariable(value = "classid")int classid,CourseTypeVo vo){
        sts.updateClassType5(vo);
        return "redirect:/student/SelectclassType";
    }
    //根据id删除程类别信息
    @RequestMapping(value = "/deleteClassType5/{classid}")
    @ResponseBody
    public void deleteClassType5(@PathVariable(value = "classid")int classid,CourseTypeVo vo){
        vo.setCourseTypeID(classid);
        sts.deleteClassType4(vo);
    }
    //查询出课程管理信息
    @RequestMapping(value = "/selectCourseLing")
    public void selectCourseLing(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter ptw = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        List list =  sts.selectCoures();
        jsonObject.put("code",0);
        jsonObject.put("count",list.size());
        jsonObject.put("msg","");
        jsonObject.put("data",list);
        ptw.print(jsonObject.toJSONString());
    }
    //根据Ajax查询课程管理
    @RequestMapping(value = "/studentCourseAjax")
    public void studentClassTypeAjax(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt =  response.getWriter();
        JSONObject jsonObject = new JSONObject();
        List<CourseTypeVo> list = sts.ClassType();
        jsonObject.put("name4",list);
        pwt.print(jsonObject.toJSONString());
        pwt.flush();
        pwt.close();
    }
    //新增课程类别管理
    @RequestMapping(value = "/AddCourser")
    public String AddCourser(CourseVo vo){
        sts.AddCoures(vo);
        return "redirect:/student/course";
    }
    //根据id查询出课程管理信息
    @RequestMapping(value = "/selectCourer5/{id}")
    public void selectCourer5(@PathVariable(value = "id") int id,HttpServletResponse response) throws IOException {
        List list = sts.selectCourer5(id);
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        for (Object o : list){
            json.put("StudentVo",o);//返回的数据格式一定要和前端的格式一样
        }
        pwt.print(json.toJSONString());
    }
    //根据id修改程类别管理
    @RequestMapping(value = "/updateCourser/{classid}")
    public String updateCourser(@PathVariable(value = "classid")int classid,CourseVo vo){
        sts.updateCourseVo(vo);
        return "redirect:/student/course";
    }
    //根据id删除程类别信息
    @RequestMapping(value = "/deleteupdateCourser/{classid}")
    @ResponseBody
    public void deleteupdateCourser(@PathVariable(value = "classid")int classid,CourseVo vo){
        vo.setCourseID(classid);
        sts.deleteCourseVo(vo);
    }
    //跳转试讲培训
    @RequestMapping(value = "/Trial")
    public String Trial(){
        return "Trial";
    }
    //查询试讲培训
    @RequestMapping(value = "/selectTrial")
    public void selectTrial(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter ptw = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        List list =  sts.selectTrial();
        jsonObject.put("code",0);
        jsonObject.put("count",list.size());
        jsonObject.put("msg","");
        jsonObject.put("data",list);
        ptw.print(jsonObject.toJSONString());
    }
    //根据Ajax查询课程管理
    @RequestMapping(value = "/CouresAjax")
    public void CouresAjax(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt =  response.getWriter();
        JSONObject jsonObject = new JSONObject();
        List<CourseVo> list = sts.selectCouresName();
        jsonObject.put("name4",list);
        pwt.print(jsonObject.toJSONString());
        pwt.flush();
        pwt.close();
    }
    //新增试讲培训
    @RequestMapping(value = "/AddTrial")
    public String AddTrial(TrialVo vo,String shijian2){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(shijian2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date = java.sql.Date.valueOf(shijian2);
        vo.setDate(date);
        sts.addTrial(vo);
        return "redirect:/student/Trial";
    }
    //根据id查询出试讲培训
    @RequestMapping(value = "/selectAddTrialID/{id}")
    public void selectAddTrialID (@PathVariable(value = "id") int id,HttpServletResponse response) throws IOException {
        List list = sts.selectTrialID(id);
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        for (Object o : list){
            json.put("StudentVo",o);//返回的数据格式一定要和前端的格式一样
        }
        pwt.print(json.toJSONString());
    }
    //修改试讲培训
    @RequestMapping(value = "/updateTrialID")
    public String updateTrialID(TrialVo vo,String shijian2){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(shijian2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date = java.sql.Date.valueOf(shijian2);
        vo.setDate(date);
        sts.updateTrialID(vo);
        return "redirect:/student/Trial";
    }
    //根据id删除试讲培训
    @RequestMapping(value = "/deleteTrialID/{classid}")
    @ResponseBody
    public void deleteTrialID(@PathVariable(value = "classid")int classid,TrialVo vo){
        vo.setTrialID(classid);
        sts.delectTrialID(vo);
    }
}
