package com.kepler.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kepler.service.EmpService;
import com.kepler.vo.CharEmpVo;
import com.kepler.vo.empVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(value = "/addEmp")
    public String addEmp(empVo emp){


        return "redirect:/emp/toEmpData";
    }

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
}
