package com.kepler.controller;

import com.alibaba.fastjson.JSONObject;
import com.kepler.service.EmpService;
import com.kepler.service.MessageService;
import com.kepler.vo.NoticeVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.soap.Text;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "/selectNoticeOK/{id}")
    public String selectNotice(@PathVariable(value = "id") int id,HttpServletRequest request){
        request.setAttribute("id",id);
        return "selectNotice";
    }
    //查询出公告信息
    @RequestMapping(value = "/selectcontent/{id}")
    public void selectContent(HttpServletResponse response,@PathVariable(value = "id") int id) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        List list = ms.selectContext(id);
        jsonObject.put("context",list);
        pwt.print(jsonObject.toJSONString());
        pwt.flush();
        pwt.close();
    }
    //删除公告信息
    @RequestMapping(value = "/delectNoticeOK/{id}")
    @ResponseBody
    public void delectNoticeOK(@PathVariable(value = "id")int id,NoticeVo vo){
        vo.setNoticeId(id);
        ms.delectContext(vo);
    }
    //修改公告
    @RequestMapping(value = "/toUpdateNoticeOK/{id}")
    public String toUpdateNoticeOK(HttpServletRequest request,@PathVariable(value = "id")int id) throws UnsupportedEncodingException {
        request.setAttribute("id",id);
        List title = ms.noticeTitle(id);
        Map map = (Map) title.get(0);
        request.setAttribute("title",map.get("title"));
        List list = ms.selectContext(id);
        Map context = (Map) list.get(0);
        request.setAttribute("context",context.get("content"));
        return "updateNotice";
    }
    //这是修改班级发送的
    @RequestMapping(value = "/updateNoticeOK/{title}/{noticeType}/{clazz}/{empid}/{id}")
    public void updateNoticeOK(HttpServletResponse response, HttpServletRequest request,@PathVariable(value = "title") String title,
                            @PathVariable(value = "noticeType") int noticeType, @PathVariable(value = "clazz") String clazz,
                            @PathVariable(value = "empid") int empid,
                               @PathVariable(value = "id") int id) throws IOException {
        title = new String(title.getBytes("ISO-8859-1"),"UTF-8");//强行转换格式器！！！！！！！！！！！！牛逼
        String context = request.getParameter("context");//文本框的数据
        NoticeVo vo = new NoticeVo();
        vo.setNoticeId(id);
        vo.setTitle(title);
        vo.setContent(context);
        vo.setNoticeType(noticeType);
        vo.setEmpid(empid);
        vo.setNoticeTime(new Date());
        vo.setClassIds(clazz);
        //查询出有多少个学生
        vo.setCcc((ms.selectStudentCount()));
        vo.setAaa(0);
        ms.updateNotice(vo);
        //响应编码集
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        pw.print("OK");
        pw.flush();
        pw.close();
    }
    //这是给学生和老师发送的
    @RequestMapping(value = "/updateNoticeOK2/{title}/{noticeType}/{empid}/{id}")
    public void updateNoticeOK2(HttpServletResponse response, HttpServletRequest request, @PathVariable(value = "title") String title,
                             @PathVariable(value = "noticeType") int noticeType,@PathVariable(value = "empid") int empid,
                                @PathVariable(value = "id") int id) throws IOException {
        title = new String(title.getBytes("ISO-8859-1"),"UTF-8");
        String context = request.getParameter("context");//文本框的数据
        NoticeVo vo = new NoticeVo();
        vo.setNoticeId(id);
        vo.setTitle(title);
        vo.setContent(context);
        vo.setNoticeType(noticeType);
        vo.setEmpid(empid);
        vo.setNoticeTime(new Date());
        //查询出有多少个学生
        vo.setCcc((ms.selectStudentCount()));
        vo.setAaa(0);
        ms.updateNotice(vo);

        //响应编码集
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        pw.print("OK");
        pw.flush();
        pw.close();
    }


    /*********************************邮件********************************************************************************/
    @RequestMapping(value = "/toEmailPage")
    public String toEmailPage(){
        return "email";
    }

    @RequestMapping(value = "/getEmailData")
    public void getEmailData(HttpServletResponse response,HttpSession session){
        response.setCharacterEncoding("utf-8");

        JSONObject jo = new JSONObject();

        List emailList = ms.selGetEmailList(Integer.parseInt(session.getAttribute("empId")+""));

        jo.put("code",0);
        jo.put("msg",0);
        jo.put("count",emailList.size());
        jo.put("data",emailList);

        try {
            PrintWriter pw = response.getWriter();

            pw.print(jo.toString());
        } catch (IOException e) {

        }
    }

    @RequestMapping(value = "/forEmailData")
    public void forEmailData(HttpServletResponse response,HttpSession session){
        response.setCharacterEncoding("utf-8");

        JSONObject jo = new JSONObject();

        List emailList = ms.selForEmailList(Integer.parseInt(session.getAttribute("empId")+""));

        jo.put("code",0);
        jo.put("msg",0);
        jo.put("count",emailList.size());
        jo.put("data",emailList);

        try {
            PrintWriter pw = response.getWriter();

            pw.print(jo.toString());
        } catch (IOException e) {

        }
    }

    @RequestMapping(value = "/getEmailDelete/{emailId}/{receId}")
    @ResponseBody
    public void getEmailDelete(@PathVariable(value = "emailId")int emailId,@PathVariable(value = "receId")int receId){
        ms.deleteGetEmail(emailId,receId);
    }

    @RequestMapping(value = "/forEmailDelete/{emailId}/{empId}")
    @ResponseBody
    public void forEmailDelete(@PathVariable(value = "emailId")int emailId,@PathVariable(value = "empId")int empId){
        ms.deleteForEmail(emailId,empId);
    }

    @RequestMapping(value = "/toAddEmailPage")
    public String toAddEmailPage(){
        return "addEmail";
    }

    @RequestMapping(value = "/addEmail/{empId}")
    public void addEmail(@PathVariable(value = "empId") int empId, String topic, String receId, String content){
        System.out.println(empId + "  " + topic + "  " + receId + "  " + content);
    }
}
