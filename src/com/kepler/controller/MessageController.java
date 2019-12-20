package com.kepler.controller;

import com.alibaba.fastjson.JSONObject;
import com.kepler.service.EmpService;
import com.kepler.service.MessageService;
import com.kepler.vo.NoticeVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.soap.Text;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
    //这是给班级发送的
    @RequestMapping(value = "/addNoticeOK/{title}/{noticeType}/{clazz}/{empid}")
    public void addNoticeOK(HttpServletResponse response, HttpServletRequest request,@PathVariable(value = "title") String title,
                            @PathVariable(value = "noticeType") int noticeType, @PathVariable(value = "clazz") String clazz,
                            @PathVariable(value = "empid") int empid) throws IOException {
        title = new String(title.getBytes("ISO-8859-1"),"UTF-8");//强行转换格式器！！！！！！！！！！！！牛逼
        String context = request.getParameter("context");//文本框的数据
        NoticeVo vo = new NoticeVo();
        vo.setTitle(title);
        vo.setContent(context);
        vo.setNoticeType(noticeType);
        vo.setEmpid(empid);
        vo.setNoticeTime(new Date());
        vo.setClassIds(clazz);
        //查询出有多少个学生
        System.out.println(ms.selectStudentCount());
        vo.setCcc((ms.selectStudentCount()));
        vo.setAaa(0);
        ms.addNotice(vo);
        //响应编码集
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        pw.print("OK");
        pw.flush();
        pw.close();
    }
    //这是给学生和老师发送的
    @RequestMapping(value = "/addNoticeOK2/{title}/{noticeType}/{empid}")
    public void addNoticeOK2(HttpServletResponse response, HttpServletRequest request, @PathVariable(value = "title") String title,
                             @PathVariable(value = "noticeType") int noticeType,@PathVariable(value = "empid") int empid) throws IOException {
        title = new String(title.getBytes("ISO-8859-1"),"UTF-8");
        String context = request.getParameter("context");//文本框的数据
        NoticeVo vo = new NoticeVo();
        vo.setTitle(title);
        vo.setContent(context);
        vo.setNoticeType(noticeType);
        vo.setEmpid(empid);
        vo.setNoticeTime(new Date());
        //查询出有多少个学生
        System.out.println(ms.selectStudentCount());
        vo.setCcc((ms.selectStudentCount()));
        vo.setAaa(0);
        ms.addNotice(vo);

        //响应编码集
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        pw.print("OK");
        pw.flush();
        pw.close();
    }
    //查看公告
    @RequestMapping(value = "/selectNotice")
    public String selectNotice(){
        return "selectNotice";
    }
}
