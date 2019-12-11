package com.kepler.controller;

import com.alibaba.fastjson.JSONObject;
import com.kepler.service.EmpService;
import com.kepler.service.SystemSetService;
import com.kepler.vo.StudentFallVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2019/12/4.
 */
@Controller
@RequestMapping("system")
public class SystemSetController {
    @Resource
    private SystemSetService sys;

        @RequestMapping("/test")
        public String test(){
            return "systemSet";
        }

    @RequestMapping(value = "/systemdata")
    public String systemdata(){
        return "systemStudentFall";
    }
    //  //查询届别数据
    @RequestMapping(value = "/data")
    public void Data(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        List<StudentFallVo> sum = sys.listSystemData();
        json.put("code",0);
        json.put("count",sum.size());
        json.put("msg","");
        json.put("data",sum);
        pwt.print(json.toString());
    }
    //添加届别数据
    @RequestMapping(value = "/addSystem")
    public String addSystem(HttpServletRequest request, StudentFallVo vo){
        sys.AddSystem(vo);
        return "redirect:/system/systemdata";
    }
    //根据届别id查询出数据
    @RequestMapping(value = "/selectSystemID")
    public void selectSystemID(int id,HttpServletResponse response) throws IOException {
        List list = sys.selectSystemById(id);
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        for (Object o : list){
            json.put("StudentFallVo",o);//返回的数据格式一定要和前端的格式一样
        }
        pwt.print(json.toJSONString());
    }
    //修改届别数据
    @RequestMapping(value = "/UpdateSystemID/{fallid}")
    public String UpdateStudentID(@PathVariable(value = "fallid")int fallid, StudentFallVo vo){
        sys.updateSystemData(vo);
        return "redirect:/system/systemdata";
    }
    //删除届别数据
    @RequestMapping(value = "/delSystem")
    public String delstudent(StudentFallVo vo){
        sys.deleSystemDatas(vo.getFallid());
        return "redirect:/system/systemdata";
    }
}
