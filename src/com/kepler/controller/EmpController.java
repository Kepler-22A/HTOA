package com.kepler.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kepler.service.EmpService;
import com.kepler.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2019/12/4.
 */
@Controller
@RequestMapping("/emp")
public class EmpController {//员工的Controller
    @Resource
    private EmpService es;

    //阳2019-12-5 9:31开始编辑，用于跳转到员工页面
    @RequestMapping("/toEmpData")
    public String toEmpDataPage(){
        return "empdata";
    }

    //阳于2019-12-5 10:00开始编辑，用于empData页面获取员工信息返回给layUI的table接口使用
    @RequestMapping("/empDataList")
    public void empDataList(HttpServletResponse response){
        response.setCharacterEncoding("utf-8");

        List empList = es.sqlEmpDataList();

        JSONObject data = new JSONObject();
        data.put("code",0);
        data.put("msg","");
        data.put("count",empList.size());
        data.put("data",empList);

        System.out.println(data.toString());

        try {
            PrintWriter pw =  response.getWriter();

            pw.write(data.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //阳于12-5 11点10分开始编辑，用于empData页面改变员工状态
    @RequestMapping("/updateEmpStatus/{empId}")
    public String updateEmpStatus(@PathVariable (value = "empId")int empId){
        es.upDataEmpStatus(empId);

        return "redirect:/emp/toEmpData";
    }

    //阳于12-6 10:00开始编辑，用于删除员工
    @RequestMapping(value = "/delEmp/{empId}")
    public String delEmp(@PathVariable (value = "empId")int empId){

        System.out.println(empId);
        es.delEmp(empId);

        return "redirect:/emp/toEmpData";
    }

    //新增员工
    @RequestMapping(value = "/addEmp")
    public String addEmp(empVo emp){


        return "redirect:/emp/toEmpData";
    }

    //得到城市列表
    @RequestMapping(value = "/getNationList")
    public void getNationList(String type,String position,HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        List list = es.getNationList(type,position);
        JSONArray ja = new JSONArray();

        for (Object o : list){
            Map cityName = (HashMap)o;
            ja.add(cityName.get("cityname"));
        }

        try {
            PrintWriter pw = response.getWriter();

            pw.print(ja.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //得到岗位名
    @RequestMapping(value = "/getPostName")
    public void getPostName(HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        List list = es.getPostName();
        JSONArray ja = new JSONArray();

        for (Object o : list){
            Map postName = (HashMap)o;
            ja.add(postName.get("postName"));
        }

        try {
            PrintWriter pw = response.getWriter();

            pw.print(ja.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //新增员工
    @RequestMapping(value = "/empAdd")
    public String empAdd(empVo emp, String nation_1, String nation_2, String nation_3, String BirthdayEX,String hireDayEX){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        try {
            d1 = format.parse(BirthdayEX);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        d1 = java.sql.Date.valueOf(BirthdayEX);

        emp.setBirthday(d1);

        Date d2 = null;
        try {
            d2 = format.parse(hireDayEX);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        d2 = java.sql.Date.valueOf(hireDayEX);

        emp.setHireDay(d2);

        String nation = "";
        if (nation_1 != null && !"null".equals(nation_1)){
            nation += nation_1;
        }
        if (nation_2 != null && !"null".equals(nation_2)){
            nation += nation_2;
        }
        if (nation_3 != null && !"null".equals(nation_3)){
            nation += nation_3;
        }
        emp.setNation(nation);

        for(Object o : es.sqlPostAndDepId(emp.getPostName())){
            HashMap map = (HashMap)o;
            emp.setPostId(Integer.parseInt(map.get("postId")+""));
            emp.setDepId(Integer.parseInt(map.get("depId")+""));
        }

        System.out.println("即将新增" + emp);

        es.addEmp(emp);

        es.addCharEmp(emp.getPostId(),emp.getEmpId());

        return "redirect:/emp/toEmpData";
    }

    //查找员工根据员工Id
    @RequestMapping(value = "/selEmpById/{id}")
    public void selEmpById(@PathVariable(value = "id") int empId,HttpServletResponse response){
        response.setCharacterEncoding("utf-8");

        empVo emp = es.selEmpById(empId);

        JSONObject jo = new JSONObject();

        jo.put("emp",emp);

        try {
            PrintWriter pw = response.getWriter();

            pw.print(jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //修改员工信息
    @RequestMapping(value = "/empUpdate")
    public String empUpdate(empVo emp, String nation_1, String nation_2, String nation_3, String BirthdayEX,String hireDayEX){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        try {
            d1 = format.parse(BirthdayEX);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        d1 = java.sql.Date.valueOf(BirthdayEX);

        emp.setBirthday(d1);

        Date d2 = null;
        try {
            d2 = format.parse(hireDayEX);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        d2 = java.sql.Date.valueOf(hireDayEX);

        emp.setHireDay(d2);

        String nation = "";
        if (nation_1 != null && !"null".equals(nation_1)){
            nation += nation_1;
        }
        if (nation_2 != null && !"null".equals(nation_2)){
            nation += nation_2;
        }
        if (nation_3 != null && !"null".equals(nation_3)){
            nation += nation_3;
        }
        emp.setNation(nation);

        for(Object o : es.sqlPostAndDepId(emp.getPostName())){
            HashMap map = (HashMap)o;
            emp.setPostId(Integer.parseInt(map.get("postId")+""));
            emp.setDepId(Integer.parseInt(map.get("depId")+""));
        }

        System.out.println("即将修改" + emp);

        es.updataEmpVo(emp);//改变员工资料

        CharEmpVo charEmp = es.selCharEmpVoByEmpId(emp.getEmpId());//根据员工ID获取员工角色对应表的相对应对象

        charEmp.setCharacterId(es.selCharacterIdByPostId(emp.getPostId()));//根据已经改变的员工职务ID查询响应的角色ID，并设置进charEmp对象中

        System.out.println("即将修改" + charEmp);

        es.updateCharEmp(charEmp);//修改员工角色对应表

        return "redirect:/emp/toEmpData";
    }

    //员工工作经历查找
    @RequestMapping(value = "/empWorkExperience/{empId}")
    public void empWorkExperience(HttpServletResponse response,@PathVariable(value = "empId")int empId){
        response.setCharacterEncoding("utf-8");

        List list = es.empWorkExperience(empId);

        JSONObject jo = new JSONObject();

        jo.put("msg","");
        jo.put("code",0);
        jo.put("count",list.size());
        jo.put("data",list);

        try {
            PrintWriter pw = response.getWriter();

            pw.print(jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //员工教育经历查找
    @RequestMapping(value = "/empEducation/{empId}")
    public void empEducation(HttpServletResponse response,@PathVariable(value = "empId")int empId){
        response.setCharacterEncoding("utf-8");

        List list = es.empEducation(empId);

        JSONObject jo = new JSONObject();

        jo.put("msg","");
        jo.put("code",0);
        jo.put("count",list.size());
        jo.put("data",list);

        try {
            PrintWriter pw = response.getWriter();

            pw.print(jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //员工家庭联系人查找
    @RequestMapping(value = "/empFamily/{empId}")
    public void empFamily(HttpServletResponse response,@PathVariable(value = "empId")int empId){
        response.setCharacterEncoding("utf-8");

        List list = es.empFamily(empId);

        JSONObject jo = new JSONObject();

        jo.put("msg","");
        jo.put("code",0);
        jo.put("count",list.size());
        jo.put("data",list);

        try {
            PrintWriter pw = response.getWriter();

            pw.print(jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //员工考核信息查找
    @RequestMapping(value = "/empAudit/{empId}")
    public void empAudit(HttpServletResponse response,@PathVariable(value = "empId")int empId){
        response.setCharacterEncoding("utf-8");

        List list = es.empAudit(empId);

        JSONObject jo = new JSONObject();

        jo.put("msg","");
        jo.put("code",0);
        jo.put("count",list.size());
        jo.put("data",list);

        try {
            PrintWriter pw = response.getWriter();

            pw.print(jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //员工证件信息查找
    @RequestMapping(value = "/selEmpAnnex/{empId}")
    public void selEmpAnnex(HttpServletResponse response,@PathVariable(value = "empId")int empId){
        response.setCharacterEncoding("utf-8");

        List list = es.empAnnex(empId);

        JSONObject jo = new JSONObject();

        jo.put("msg","");
        jo.put("code",0);
        jo.put("count",list.size());
        jo.put("data",list);

        try {
            PrintWriter pw = response.getWriter();

            pw.print(jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //新增员工工作经历
    @RequestMapping(value = "/addEmpWorkExperience")
    public String addEmpWorkExperience(JobVo job,String startDateEX,String endDateEX){
        job.setStartDate(java.sql.Date.valueOf(startDateEX));
        job.setEndDate(java.sql.Date.valueOf(endDateEX));

        System.out.println("job:"+job);

        es.addworkExperience(job);

        return "redirect:/emp/toEmpData";
    }

    //删除员工工作经历
    @RequestMapping(value = "/deleteWorkExprience/{jobId}")
    @ResponseBody
    public void deleteWorkExprience(@PathVariable(value = "jobId")int jobId){
        es.deleteWorkExprience(jobId);
    }

    //修改员工工作经历
    @RequestMapping(value = "/updateWorkExperience")
    public String updateWorkExperience(JobVo job,String startDateEX,String endDateEX){
        job.setStartDate(java.sql.Date.valueOf(startDateEX));
        job.setEndDate(java.sql.Date.valueOf(endDateEX));

        System.out.println("job:"+job);

        es.updateWorkExperience(job);

        return "redirect:/emp/toEmpData";
    }

    //新增员工教育经历
    @RequestMapping(value = "/addEducation")
    public String addEducation(EducationVo education,String startDateEX,String endDateEX){
        education.setStartDate(java.sql.Date.valueOf(startDateEX));
        education.setEndDate(java.sql.Date.valueOf(endDateEX));

        System.out.println("education:"+education);
        es.addEducation(education);

        return "redirect:/emp/toEmpData";
    }

    //修改员工教育经历
    @RequestMapping(value = "/updateEducation")
    public String updateEducation(EducationVo education,String startDateEX,String endDateEX){
        education.setStartDate(java.sql.Date.valueOf(startDateEX));
        education.setEndDate(java.sql.Date.valueOf(endDateEX));

        System.out.println("education:"+education);

        es.updateEducation(education);

        return "redirect:/emp/toEmpData";
    }

    //删除员工教育经历
    @RequestMapping(value = "/deleteEducation/{collegeid}")
    @ResponseBody
    public void deleteEducation(@PathVariable(value = "collegeid")int collegeid){
        es.deleteEducation(collegeid);
    }

    //新增员工家庭联系信息
    @RequestMapping(value = "/addFamilyInfo")
    public String addFamilyInfo(FamilyInfoVo familyInfoVo){

        System.out.println("FamilyInfoVo familyInfoVo:"+familyInfoVo);
        es.addFamilyInfo(familyInfoVo);

        return "redirect:/emp/toEmpData";
    }

    //修改员工教育经历
    @RequestMapping(value = "/updateFamilyInfo")
    public String updateFamilyInfo(FamilyInfoVo familyInfoVo){

        System.out.println("FamilyInfoVo familyInfoVo:"+familyInfoVo);

        es.updateFamilyInfo(familyInfoVo);

        return "redirect:/emp/toEmpData";
    }

    //删除员工教育经历
    @RequestMapping(value = "/deleteFamilyInfo/{familyId}")
    @ResponseBody
    public void deleteFamilyInfo(@PathVariable(value = "familyId")int familyId){
        es.deleteFamilyInfo(familyId);
    }

    //跳转周报管理页
    @RequestMapping(value = "/toWeeklyCtrl")
    public String toWeeklyCtrl(){
        return "weekly";
    }

    //周报管理页的周报数据查询
    @RequestMapping(value = "/selWeeklyList")
    public void selWeekly(HttpServletResponse response){
        response.setCharacterEncoding("utf-8");

        List list = es.selWeekly();

        JSONObject jo = new JSONObject();

        jo.put("msg","");
        jo.put("code",0);
        jo.put("count",list.size());
        jo.put("data",list);

        try {
            PrintWriter pw = response.getWriter();

            pw.print(jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //周报详情页
    @RequestMapping(value = "/toWeeklyDetails/{worklogid}")
    public ModelAndView toWeeklyDetails(ModelAndView mv,@PathVariable(value = "worklogid") int worklogid, HttpServletRequest request){
        mv.setViewName("weekDetails");

        List list = es.selWeeklyAndDepName(worklogid);

        for (Object o : list){
            Map map = (HashMap)o;

            request.setAttribute("empId",map.get("empId"));
            request.setAttribute("weekCur",map.get("weekCur"));
            request.setAttribute("weekNext",map.get("weekNext"));
            request.setAttribute("Idea",map.get("Idea"));
            request.setAttribute("empName",map.get("empName"));
            request.setAttribute("worklogid",map.get("worklogid"));
            request.setAttribute("Workday",map.get("Workday"));
            request.setAttribute("depName",map.get("depName"));
            request.setAttribute("studentQuestion",map.get("studentQuestion"));
        }


        return mv;
    }

    /**
     * 去员工周报也
     * */
    @RequestMapping(value = "/toMyWeeklyPage")
    public String toMyWeeklyPage(){
        return "myWeekly";
    }

    //自己的周报详情页周报数据查询
    @RequestMapping(value = "/selWeeklyListByEmpId")
    public void selWeeklyListByEmpId(HttpServletResponse response, HttpSession session){
        response.setCharacterEncoding("utf-8");

        List list = es.selWeeklyByEmpId(Integer.parseInt(session.getAttribute("empId")+""));

        JSONObject jo = new JSONObject();

        jo.put("msg","");
        jo.put("code",0);
        jo.put("count",list.size());
        jo.put("data",list);

        try {
            PrintWriter pw = response.getWriter();

            pw.print(jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
