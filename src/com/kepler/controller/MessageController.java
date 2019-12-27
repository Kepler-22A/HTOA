package com.kepler.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kepler.service.EmpService;
import com.kepler.service.MessageService;
import com.kepler.vo.EmailVo;
import com.kepler.vo.NoticeReceiverVo;
import com.kepler.vo.NoticeVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import java.util.*;

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
    public void selectNotice(HttpServletResponse response,HttpSession session) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter ptw = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        int userType = 0;
        int receiver = 0;
        if (session.getAttribute("empId") != null){
            userType = 1;
            receiver = Integer.parseInt(session.getAttribute("empId")+"");
        }else{
            userType = 2;
            receiver = Integer.parseInt(session.getAttribute("studentId")+"");
        }
        List list = ms.selectNotice(userType,receiver);
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
        //查询出某个班有多少个学生
        List studentList = ms.selectStudentCountByClassId(Integer.parseInt(clazz));
        vo.setCcc(studentList.size());
        vo.setAaa(0);
        ms.addNotice(vo);

        NoticeReceiverVo noticeReceiver = new NoticeReceiverVo();
        noticeReceiver.setIsRead(2);
        noticeReceiver.setNoticeId(vo.getNoticeId());
        noticeReceiver.setUserType(2); //2为学生接收

        for (Object o : studentList){
            Map map = (HashMap)o;

            noticeReceiver.setReceiver(Integer.parseInt(map.get("Studid")+""));

            ms.addNoticeReceiver(noticeReceiver);
        }

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
        if (noticeType == 3){ // 添加对全体学生的公告
            vo.setNoticeType(noticeType);
            vo.setEmpid(empid);
            vo.setNoticeTime(new Date());
            //查询出有多少个学生
            List studentList = ms.selectStudentCount(); // 学生列表
            vo.setCcc(studentList.size());
            vo.setAaa(0);
            ms.addNotice(vo);

            NoticeReceiverVo noticeReceiver = new NoticeReceiverVo();
            noticeReceiver.setIsRead(2);
            noticeReceiver.setNoticeId(vo.getNoticeId());
            noticeReceiver.setUserType(2); //2为学生接收

            for (Object o : studentList){
                Map map = (HashMap)o;

                noticeReceiver.setReceiver(Integer.parseInt(map.get("Studid")+""));

                ms.addNoticeReceiver(noticeReceiver);
            }
        }else if (noticeType == 2){ // 全体员工的公告
            vo.setNoticeType(noticeType);
            vo.setEmpid(empid);
            vo.setNoticeTime(new Date());
            //查询出有多少个员工
            List empList = ms.selEmpCount(); //员工列表
            vo.setCcc(empList.size());
            vo.setAaa(0);
            ms.addNotice(vo);

            NoticeReceiverVo noticeReceiver = new NoticeReceiverVo();
            noticeReceiver.setIsRead(2);
            noticeReceiver.setNoticeId(vo.getNoticeId());
            noticeReceiver.setUserType(1); //1为员工接收

            for (Object o : empList){
                Map map = (HashMap)o;

                noticeReceiver.setReceiver(Integer.parseInt(map.get("empId")+""));

                ms.addNoticeReceiver(noticeReceiver);
            }
        }

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

        List studentList = ms.selectStudentCountByClassId(Integer.parseInt(clazz));

        vo.setCcc(studentList.size());
        vo.setAaa(0);
        ms.updateNotice(vo);

        ms.delNoticeReceiver(vo.getNoticeId());

        NoticeReceiverVo noticeReceiver = new NoticeReceiverVo();
        noticeReceiver.setIsRead(2);
        noticeReceiver.setNoticeId(vo.getNoticeId());
        noticeReceiver.setUserType(2); //2为学生接收

        for (Object o : studentList){
            Map map = (HashMap)o;

            noticeReceiver.setReceiver(Integer.parseInt(map.get("Studid")+""));

            ms.addNoticeReceiver(noticeReceiver);
        }

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

        if (noticeType == 3){ // 添加对全体学生的公告
            vo.setNoticeType(noticeType);
            vo.setEmpid(empid);
            vo.setNoticeTime(new Date());
            //查询出有多少个学生
            List studentList = ms.selectStudentCount(); // 学生列表
            vo.setCcc(studentList.size());
            vo.setAaa(0);
            ms.updateNotice(vo);

            ms.delNoticeReceiver(vo.getNoticeId());

            NoticeReceiverVo noticeReceiver = new NoticeReceiverVo();
            noticeReceiver.setIsRead(2);
            noticeReceiver.setNoticeId(vo.getNoticeId());
            noticeReceiver.setUserType(2); //2为学生接收

            for (Object o : studentList){
                Map map = (HashMap)o;

                noticeReceiver.setReceiver(Integer.parseInt(map.get("Studid")+""));

                ms.addNoticeReceiver(noticeReceiver);
            }
        }else if (noticeType == 2){ // 全体员工的公告
            vo.setNoticeType(noticeType);
            vo.setEmpid(empid);
            vo.setNoticeTime(new Date());
            //查询出有多少个员工
            List empList = ms.selEmpCount(); //员工列表
            vo.setCcc(empList.size());
            vo.setAaa(0);
            ms.updateNotice(vo);

            ms.delNoticeReceiver(vo.getNoticeId());

            NoticeReceiverVo noticeReceiver = new NoticeReceiverVo();
            noticeReceiver.setIsRead(2);
            noticeReceiver.setNoticeId(vo.getNoticeId());
            noticeReceiver.setUserType(1); //1为员工接收

            for (Object o : empList){
                Map map = (HashMap)o;

                noticeReceiver.setReceiver(Integer.parseInt(map.get("empId")+""));

                ms.addNoticeReceiver(noticeReceiver);
            }
        }
//        vo.setNoticeType(noticeType);
//        vo.setEmpid(empid);
//        vo.setNoticeTime(new Date());
//        //查询出有多少个学生
//        vo.setCcc((ms.selectStudentCount()).size());
//        vo.setAaa(0);
//        ms.updateNotice(vo);

        //响应编码集
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        pw.print("OK");
        pw.flush();
        pw.close();
    }

    @RequestMapping(value = "/changeNoticeReceiverIsRead/{noticeId}")
    @ResponseBody
    public void changeNoticeReceiverIsRead(@PathVariable(value = "noticeId")int noticeId,HttpSession session){
        NoticeReceiverVo noticeReceiverVo = null;
        if (session.getAttribute("empId") != null){ //员工
            noticeReceiverVo = ms.selNoticeReceiverByNoticeIdAndUserTypeAndReceiver(noticeId,1,Integer.parseInt(session.getAttribute("empId")+""));
        }else if (session.getAttribute("studentId") != null){
            noticeReceiverVo = ms.selNoticeReceiverByNoticeIdAndUserTypeAndReceiver(noticeId,2,Integer.parseInt(session.getAttribute("studentId")+""));
        }

        if (noticeReceiverVo.getIsRead() == 2){
            noticeReceiverVo.setIsRead(1);
            ms.updateNoticeReceiverVo(noticeReceiverVo);

            NoticeVo noticeVo = ms.selNoticeByNoticeId(noticeId);
            int ccc = noticeVo.getCcc();
            ccc = ccc - 1;
            noticeVo.setCcc(ccc);
            int aaa =noticeVo.getAaa();
            aaa = aaa + 1;
            noticeVo.setAaa(aaa);
            ms.updateNotice(noticeVo);
        }
    }

    @RequestMapping(value = "/selNoticeReceiverNumber")
    @ResponseBody
    public void selNoticeReceiverNumber(HttpServletResponse response,HttpSession session){
        int count = 0;
        if (session.getAttribute("studentId") != null){
            count = ms.selNoticeReceiverNumber(2,Integer.parseInt(session.getAttribute("studentId")+""));
        }else if (session.getAttribute("empId") != null){
            count = ms.selNoticeReceiverNumber(1,Integer.parseInt(session.getAttribute("empId")+""));
        }

        JSONObject jo = new JSONObject();
        jo.put("count",count);

        try {
            PrintWriter pw = response.getWriter();

            pw.print(jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    @ResponseBody
    public void addEmail(@PathVariable(value = "empId") int empId, String topic, String receId, String content){
        EmailVo emailVo = new EmailVo();
        emailVo.setEmpId(empId + "");
        emailVo.setContent(content);
        emailVo.setTopic(topic);
        emailVo.setIsRead(2);
        emailVo.setSendtime(new Date());

        List idList = new ArrayList();

        String receIdA = "";
        for(int i = 0;i < receId.length();i++){
            if (receId.charAt(i) == ','){
                idList.add(receIdA);
                receIdA = "";
            } else if (i == receId.length() - 1){
                char a = receId.charAt(i);
                receIdA = receIdA + a;
                idList.add(receIdA);
            }
            else {
                char a = receId.charAt(i);
                receIdA = receIdA + a;
            }
        }

        for (Object o : idList){
            String receIdData = o + "";
            emailVo.setReceId(receIdData);
            ms.addEmail(emailVo);
        }
    }

    @RequestMapping(value = "/getEmpJSON")
    @ResponseBody
    public JSONArray getEmpJSON(HttpServletResponse response,HttpSession session){
        response.setCharacterEncoding("utf-8");

        JSONArray ja = ms.getEmpJSON(Integer.parseInt(session.getAttribute("empId")+""));

//        System.out.println(ja.toString());

        return ja;
    }

    @RequestMapping(value = "/lookEmail/{emailId}")
    public String lookEmail(Model model,@PathVariable(value = "emailId")int emailId){
        model.addAttribute("emailVo",ms.selEmailVoByEmailId(emailId));
        return "lookEmail";
    }

    @RequestMapping(value = "/changeEmailStatus/{emailId}")
    @ResponseBody
    public void changeEmailStatus(@PathVariable(value = "emailId")int emailId){
        ms.changeEmailStatus(emailId);
    }

    @RequestMapping(value = "/selEmailIsReadNotNumber")
    @ResponseBody
    public void selEmailIsReadNotNumber(HttpSession session,HttpServletResponse response){
        int count = ms.selEmailIsReadNotNumber(Integer.parseInt(session.getAttribute("empId")+""));

        JSONObject jo = new JSONObject();

        jo.put("count",count);

        try {
            PrintWriter pw = response.getWriter();

            pw.print(jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
