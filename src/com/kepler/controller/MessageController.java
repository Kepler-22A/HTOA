package com.kepler.controller;

import com.alibaba.fastjson.JSONObject;
import com.kepler.service.EmpService;
import com.kepler.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.Text;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by ASUS on 2019/12/4.
 */
@Controller
@RequestMapping("message")
public class MessageController {
    @Resource
    private MessageService ms;
    //跳转到公告页面
    @RequestMapping(value = "/notice")
    public String notice(){
        return "notice";
    }
    //查询出公告信息
    @RequestMapping(value = "/selectNotice")
    public void selectNotice(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter ptw = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        List list = ms.selectNotice();
        jsonObject.put("code",0);
        jsonObject.put("count",list.size());
        jsonObject.put("msg","");
        jsonObject.put("data",list);
        ptw.print(jsonObject.toJSONString());
    }
    //新增公告信息
    @RequestMapping(value = "/addNotice")
    public String addNotice(){
        return "addNotice";
    }
    @RequestMapping(value = "/addNoticeOK")
    public void addNoticeOK(HttpServletResponse response, HttpServletRequest request, String title, String notType, String clazz,String content) throws IOException {
        System.out.println("金色的JFK伺机待发");

        System.out.println(request.getParameter("title"));
        System.out.println(notType);
        System.out.println(clazz);
        System.out.println(content);
        //响应编码集
        response.setContentType("text/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        pw.print("OK");
        pw.flush();
        pw.close();
    }
}
