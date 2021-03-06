package com.kepler.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kepler.service.EmpService;
import com.kepler.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import java.util.*;

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

    //新增员工考核
    @RequestMapping(value = "/addAudit")
    public String addAudit(HttpSession session,AuditLogVo auditLogVo,int amID){
        auditLogVo.setAuditModelID(amID);
        System.out.println(auditLogVo);
        es.addAuditLog(auditLogVo);

        return "redirect:/emp/toEmpData";
    }

    //查出考核指标
    @RequestMapping(value = "/selAuditModel")
    @ResponseBody
    public void selAuditModel(HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        JSONArray ja = new JSONArray();


        List list = es.selAuditModel();

        for (Object o : list){
            JSONObject jo = new JSONObject();
            Map map = (HashMap)o;
            jo.put("auditModelID",map.get("auditModelID"));
            jo.put("auditName",map.get("auditName"));
            ja.add(jo);
        }

        System.out.println(ja);

        try {
            PrintWriter pw = response.getWriter();

            pw.print(ja.toString());
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

    /**
     * 新增周报
     * */
    @RequestMapping(value = "/addWeekly")
    public String addWeekly(HttpSession session,WeeklyVo weeklyVo){
        weeklyVo.setWorkday(new Date());
        weeklyVo.setEmpId(session.getAttribute("empId")+"");

        es.addWeekly(weeklyVo);

        return "myWeekly";
    }

    /**
     * 删除周报
     * */
    @RequestMapping(value = "/delWeekly/{worklogid}")
    public String delWeekly(@PathVariable(value = "worklogid") int worklogid){
        es.delWeekly(worklogid);

        return "myWeekly";
    }

    /**
     * 修改周报
     * */
    @RequestMapping(value = "/updataWeekly")
    public String updateWeekly(WeeklyVo weeklyVo){
        es.updateWeekly(weeklyVo);

        return "myWeekly";
    }
    /**
     * 谈心记录
     * */
    @RequestMapping(value = "/chatRecord")
    public String chatRecord(){
        return "chatRecord";
    }
    /**
     * 查询谈心记录
     * */
    @RequestMapping(value = "/selectCharRecord")
    public void selectCharRecord(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter ptw = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        List list = es.charRecord();
        jsonObject.put("code",0);
        jsonObject.put("count",list.size());
        jsonObject.put("msg","");
        jsonObject.put("data",list);
        ptw.print(jsonObject.toJSONString());
    }
    /**
     * 新增记录
     * */
    @RequestMapping(value = "/AddCharRecord")
    public String AddCharRecord(String shijian,HttpSession session,String name,ChatRecordVo vo){
        int empid = (int) session.getAttribute("empId");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(shijian);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date = java.sql.Date.valueOf(shijian);
        List list = es.selectStudentID(name);
        Map map = (Map) list.get(0);
        vo.setSayFace((Integer) map.get("Studid"));
        vo.setChatDate(date);
        vo.setTeacher(empid);
        es.AddcharRecord(vo);
        return "redirect:/emp/chatRecord";
    }
    /**
     * 根据id查询出谈心记录
     * */
    @RequestMapping(value = "/selectCharRecordID/{id}")
    public void selectCharRecordID(@PathVariable(value = "id") int id,HttpServletResponse response) throws IOException {
        List list = es.selectCharRecord(id);
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        for (Object o : list){
            json.put("StudentVo",o);//返回的数据格式一定要和前端的格式一样
        }
        pwt.print(json.toJSONString());
    }
    /**
     * 修改谈心记录
     * */
    @RequestMapping(value = "/UpdateChatRecord")
    public String UpdateStudentID(ChatRecordVo vo,HttpSession session,String name){
        int empid = (int) session.getAttribute("empId");
        List list = es.selectStudentID(name);
        Map map = (Map) list.get(0);
        vo.setSayFace((Integer) map.get("Studid"));
        vo.setTeacher(empid);
        es.updateCharRecord(vo);
        return "redirect:/emp/chatRecord";
    }
    /*
    * 删除谈心记录
    * */
    @RequestMapping(value = "/delectCharRecord/{id}")
    @ResponseBody
    public void delectCharRecord(@PathVariable(value = "id") int id, ChatRecordVo vo){
        vo.setChatID(id);
        es.delectCharRecord(vo);
    }

    /**
     * 去我的页面
     * ***/
    @RequestMapping(value = "/toMyPage")
    public String toMyPage(){
        return "myPage";
    }

    /*
     * 考勤管理
     * */
    @RequestMapping(value = "/Checking")
    public String Checking(){
        return "Checking";
    }
    /*
     * 查询考勤管理
     * */
    @RequestMapping(value = "/selectChecking")
    public void selectChecking(HttpServletResponse response,HttpSession session) throws IOException {
        int empid = (int) session.getAttribute("empId");
        response.setCharacterEncoding("utf-8");
        PrintWriter ptw = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        List list = es.selectChecking(empid);
        jsonObject.put("code",0);
        jsonObject.put("count",list.size());
        jsonObject.put("msg","");
        jsonObject.put("data",list);
        ptw.print(jsonObject.toJSONString());
    }

    @RequestMapping(value = "/selWeeklyNotPush")
    @ResponseBody
    public void selWeeklyNotPush(HttpServletResponse response,HttpSession session){ //查看当前周周报有没有提交
        int empId = Integer.parseInt(session.getAttribute("empId")+"");

        int count = es.selWeeklyNotPush(empId);

        JSONObject jo = new JSONObject();

        jo.put("count",count);

        try {
            PrintWriter pw = response.getWriter();

            pw.print(jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/selChatRecord")
    @ResponseBody
    public void selChatRecord(HttpServletResponse response,HttpSession session){ //查看当前月提交了多少份谈心记录
        response.setCharacterEncoding("utf-8");

        int count = es.selChatRecord(Integer.parseInt(session.getAttribute("empId")+""));

        JSONObject jo = new JSONObject();

        jo.put("count",count);

        try {
            PrintWriter pw = response.getWriter();

            pw.print(jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /*
     * 新增未打卡说明
     * */
    @RequestMapping(value = "/addChecking")
    public String addChecking(String shijian,CheckingVo vo,HttpSession session,String time){
        int empid = (int) session.getAttribute("empId");
        String date = shijian +" "+ time;
        vo.setNOdate(date);
        vo.setEmpID(empid);
        vo.setState(3);
        List id = es.selectshangjiID(empid);
        Map map = (Map) id.get(0);
        vo.setSuperiorempID((Integer) map.get("depid"));
        es.addChecking(vo);
        return "redirect:/emp/Checking";
    }
    /*
     * 跳转到我的审批
     * */
    @RequestMapping(value = "/manChecking")
    public String manChecking(){
        return "manChecking";
    }
    /*
     * 查询我的审批
     * */
    @RequestMapping(value = "/selectXiaJiChecking")
    public void selectXiaJiChecking(HttpServletResponse response,HttpSession session) throws IOException {
        int empid = (int) session.getAttribute("empId");
        response.setCharacterEncoding("utf-8");
        PrintWriter ptw = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        List list = es.selectXiaJiChecking(empid);
        Map map = new HashMap();
        for(int i=0;i<list.size();i++){
            map= (Map) list.get(i);
        }
        System.out.println("这是值"+(Integer)map.get("state"));
        if((Integer) map.get("state") == 3){   //还没有审核的表
            System.out.println("进来了还没有审核的表");
            jsonObject.put("code",0);
            jsonObject.put("count",list.size());
            jsonObject.put("msg","");
            jsonObject.put("data",list);
            ptw.print(jsonObject.toJSONString());
        }else if((Integer) map.get("state") == 1){//通过审批
            System.out.println("进来了审核的表");
            jsonObject.put("code",0);
            jsonObject.put("count",list.size());
            jsonObject.put("msg","");
            jsonObject.put("data",list);
            ptw.print(jsonObject.toJSONString());
        }

    }
    /*
     * 通过审批
     * */
    @RequestMapping(value = "/updatestate/{id}/{ok}")
    @ResponseBody
    public void updatestate(@PathVariable(value = "id") int id,@PathVariable(value = "ok") int ok,CheckingVo vo){
        List list = es.selectCheckings(id);
        Map map = new HashMap();
        for(int i=0;i<list.size();i++){
            map= (Map) list.get(i);
        }
        vo.setNOdate((String) map.get("NOdate"));
        vo.setCauseReamk((String) map.get("causeReamk"));
        vo.setEmpID((Integer) map.get("empID"));
        vo.setSuperiorempID((Integer) map.get("superiorempID"));
        vo.setCheckingID(id);
        vo.setAuditdate(new Date());
        vo.setState(1);
        if(ok == 1){
            vo.setReamk("属实");
        }else if(ok == 2){
            vo.setReamk("不属实");
        }
        es.updatestate(vo);
    }

    /**
     * 员工基本信息单
     * */
    @RequestMapping(value = "/toEmpBase")
    public String toEmpBase(HttpSession session, Model model){
        int empId = Integer.parseInt(session.getAttribute("empId")+"");

        List empList = es.selEmpBaseByEmpId(empId);
        List educationList = es.selEmpEducation(empId);
        List jobList = es.selEmpJobList(empId);
        List familyList = es.selEmpFamilyList(empId);

        model.addAttribute("empList",empList);
        model.addAttribute("educationList",educationList);
        model.addAttribute("jobList",jobList);
        model.addAttribute("familyList",familyList);

        return "empBase";
    }

    /**
     * 值班管理的转跳
     * **/
    @RequestMapping(value = "/toXexPage")
    public String toXexPage(){
        return "xex";
    }

    /**
     * 去修改密码页面
     * */
    @RequestMapping(value = "/toChangePwdPage")
    public String toChangePwdPage(){
        return "changePwd";
    }

    /**
    * 验证旧密码
    * */
    @RequestMapping(value = "/checkOldPwd")
    @ResponseBody
    public void checkOldPwd(HttpSession session,HttpServletResponse response,int userId,String password){
        String userType = "";
        if(session.getAttribute("empId") == null){
            userType = "student";
        }else {
            userType = "emp";
        }

        JSONObject jo = new JSONObject();

        jo.put("count",es.checkOldPassword(userId,password,userType));

        try {
            PrintWriter pw = response.getWriter();

            pw.print(jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改密码
     * */
    @RequestMapping(value = "/changePwd")
    @ResponseBody
    public void changePwd(HttpSession session,HttpServletResponse response,int userId,String password){
        String userType = "";
        if(session.getAttribute("empId") == null){
            userType = "student";
        }else {
            userType = "emp";
        }

        JSONObject jo = new JSONObject();

        jo.put("count",es.changePwd(userId,password,userType));

        try {
            PrintWriter pw = response.getWriter();

            pw.print(jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把密码初始化
     * */
    @RequestMapping(value = "/changePwdToIniti/{empId}")
    @ResponseBody
    public void changePwdToIniti(@PathVariable(value = "empId")int empId,HttpServletResponse response){
        es.changePwd(empId,"123456","emp");

        JSONObject jo = new JSONObject();

        jo.put("count",1);

        try {
            PrintWriter pw = response.getWriter();

            pw.print(jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
