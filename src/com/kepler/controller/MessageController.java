package com.kepler.controller;

import com.alibaba.fastjson.JSONObject;
import com.kepler.service.EmpService;
import com.kepler.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping(value = "/addNoticeOK/{title}/{noticeType}/{clazz}")
    public void addNoticeOK(HttpServletResponse response, HttpServletRequest request, @PathVariable(value = "title") String title,
     @PathVariable(value = "noticeType") String noticeType, @PathVariable(value = "clazz") String clazz) throws IOException {
        System.out.println("金色的JFK伺机待发");
        title = new String(title.getBytes("ISO-8859-1"),"UTF-8");//强行转换格式器！！！！！！！！！！！！牛逼
        System.out.println(title);
        System.out.println(noticeType);
        System.out.println(clazz);
        System.out.println(request.getParameter("context"));
        //响应编码集
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        pw.print("OK");
        pw.flush();
        pw.close();
    }
    @RequestMapping(value = "/addNoticeOK2/{title}/{noticeType}")
    public void addNoticeOK2(HttpServletResponse response, HttpServletRequest request, @PathVariable(value = "title") String title,
                             @PathVariable(value = "noticeType") String noticeType) throws IOException {
        System.out.println("时间和封建时代");
        title = new String(title.getBytes("ISO-8859-1"),"UTF-8");//强行转换格式器！！！！！！！！！！！！牛逼
        System.out.println(title);
        System.out.println(noticeType);
        System.out.println(request.getParameter("context"));
        //响应编码集
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        pw.print("OK");
        pw.flush();
        pw.close();
    }
}
