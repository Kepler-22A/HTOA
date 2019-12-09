package com.kepler.controller;

import com.alibaba.fastjson.JSONObject;
import com.kepler.service.EmpService;
import com.kepler.vo.empVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
    @RequestMapping(value = "delEmp/{empId}")
    public String delEmp(@PathVariable (value = "empId")int empId){

        System.out.println(empId);
        es.delEmp(empId);

        return "redirect:/emp/toEmpData";
    }

    public String addEmp(empVo emp){


        return "redirect:/emp/toEmpData";
    }

}
