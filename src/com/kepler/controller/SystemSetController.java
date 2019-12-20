package com.kepler.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kepler.dao.FileUpload;
import com.kepler.service.EmpService;
import com.kepler.service.SystemSetService;
import com.kepler.vo.*;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by ASUS on 2019/12/4.
 */
@Controller
@RequestMapping("system")
public class SystemSetController {
    @Resource
    private SystemSetService sys;
    @Resource
    private ProcessEngine processEngine;
    @Resource
    private TaskService taskService;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private HistoryService historyService;
    @Resource
    private RepositoryService repositoryService;

    //--------------------------------届别设置-------------------------------------------------------------------------------
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

    //--------------------------------班级类别-------------------------------------------------------------------------------
    @RequestMapping("/classtype")
    public String classType(){
        return "classType";
    }
    //  //查询班级类别数据
    @RequestMapping(value = "/classtypedata")
    public void classtypedata(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        List<ClassTypeVo> sum = sys.listClassTyopeData();
        json.put("code",0);
        json.put("count",sum.size());
        json.put("msg","");
        json.put("data",sum);
        pwt.print(json.toString());
    }
    //添加班级类型数据
    @RequestMapping(value = "/addClassType")
    public String addClassType(HttpServletRequest request, ClassTypeVo vo){
        sys.AddClassType(vo);
        return "redirect:/system/classtype";
    }
    //根据班级id查询出数据
    @RequestMapping(value = "/selectClassID")
    public void selectClassID(int id,HttpServletResponse response) throws IOException {
        List list = sys.selectClassById(id);
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        for (Object o : list){
            json.put("ClassTypeVo",o);//返回的数据格式一定要和前端的格式一样
        }
        pwt.print(json.toJSONString());
    }
    //修改班级数据
    @RequestMapping(value = "/UpdateClassID/{calssTypeId}")
    public String UpdateClassID(@PathVariable(value = "calssTypeId")int calssTypeId, ClassTypeVo vo){
        sys.updateClassData(vo);
        return "redirect:/system/classtype";
    }
    //删除班级数据
    @RequestMapping(value = "/delClassType")
    public String delClassType(ClassTypeVo vo){
        sys.deleClassDatas(vo.getCalssTypeId());
        return "redirect:/system/classtype";
    }
    //---------------------------------------------------------------------------------------------------

    @RequestMapping("/project")
    public String project(){
        return "systemProject";
    }
    //  //查询项目答辩数据
    @RequestMapping(value = "/projectdata")
    public void projectdata(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        List<ProjectVo> sum = sys.listProjectData();
        json.put("code",0);
        json.put("count",sum.size());
        json.put("msg","");
        json.put("data",sum);
        pwt.print(json.toString());
    }
    //添加项目答辩数据
    @RequestMapping(value = "/addProject")
    public String addProject(HttpServletRequest request, ProjectVo vo){
        sys.AddProject(vo);
        return "redirect:/system/project";
    }
    //根据项目答辩id查询出数据
    @RequestMapping(value = "/selectProjectID")
    public void selectProjectID(int id,HttpServletResponse response) throws IOException {
        List list = sys.selectProjectById(id);
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        for (Object o : list){
            json.put("ProjectVo",o);//返回的数据格式一定要和前端的格式一样
        }
//        System.out.println("json:"+json);
        pwt.print(json.toJSONString());
    }
    //修改项目答辩数据
    @RequestMapping(value = "/UpdateProjectID/{projectId}")
    public String UpdateProjectID(@PathVariable(value = "projectId")int projectId, ProjectVo vo){
        sys.updateProjectData(vo);
        System.out.println(vo);
        return "redirect:/system/project";
    }
    //删除项目答辩数据
    @RequestMapping(value = "/delProject")
    public String delProject(ProjectVo vo){
        sys.deleProjectDatas(vo.getProjectId());
        return "redirect:/system/project";
    }
    //----------------------------------------- 院系设置----------------------------------------------------
    @RequestMapping("/dept")
    public String dept(){
        return "systemDept";
    }
    //  //查询数据
    @RequestMapping(value = "/deptdata")
    public void deptdata(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        List<DeptVo> sum = sys.listDeptData();
        json.put("code",0);
        json.put("count",sum.size());
        json.put("msg","");
        json.put("data",sum);
        pwt.print(json.toString());
    }
    //添加数据
    @RequestMapping(value = "/addDept")
    public String addDept(HttpServletRequest request, DeptVo vo){
        sys.AddDept(vo);
        return "redirect:/system/dept";
    }
    //根据id查询出数据
    @RequestMapping(value = "/selectDeptID")
    public void selectDeptID(int id,HttpServletResponse response) throws IOException {
        List list = sys.selectDeptById(id);
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        for (Object o : list){
            json.put("DeptVo",o);//返回的数据格式一定要和前端的格式一样
        }
//        System.out.println("json:"+json);
        pwt.print(json.toJSONString());
    }
    //修改数据
    @RequestMapping(value = "/UpdateDeptID/{deptID}")
    public String UpdateDeptID(@PathVariable(value = "deptID")int deptID, DeptVo vo){
        sys.updateDeptData(vo);
        return "redirect:/system/dept";
    }
    //删除数据
    @RequestMapping(value = "/delDept")
    public String delDept(DeptVo vo) {
        sys.deleDeptDatas(vo.getDeptID());
        return "redirect:/system/dept";
    }

    //----------------------------------------专业-----------------------------------------------------
    @RequestMapping("/major")
    public String major(){
        return "systemMajor";
    }
    //  //查询专业数据
    @RequestMapping(value = "/majordata")
    public void majordata(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        List<MajorVo> sum = sys.listMajorData();
        json.put("code",0);
        json.put("count",sum.size());
        json.put("msg","");
        json.put("data",sum);
        pwt.print(json.toString());
    }
    //添加专业数据
    @RequestMapping(value = "/addMajor")
    public String addMajor(HttpServletRequest request, MajorVo vo,int deptId){
        sys.AddMajor(vo);
        return "redirect:/system/major";
    }
    //根据专业id查询出数据
    @RequestMapping(value = "/selectMajorID")
    public void selectMajorID(int id,HttpServletResponse response) throws IOException {
        List list = sys.selectMajorById(id);
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        for (Object o : list){
            json.put("MajorVo",o);//返回的数据格式一定要和前端的格式一样
        }
//        System.out.println("json:"+json);
        pwt.print(json.toJSONString());
    }
    //修改专业数据
    @RequestMapping(value = "/UpdateMajorID/{majorID}")
    public String UpdateMajorID(@PathVariable(value = "majorID")int majorID, MajorVo vo){
        sys.updateMajorData(vo);
        return "redirect:/system/major";
    }
    //删除专业数据
    @RequestMapping(value = "/delMajor")
    public String delMajor(MajorVo vo) {
//        System.out.println("id为："+vo.getMajorID());
       sys.delMajorDatas(vo.getMajorID());
        return "redirect:/system/major";
    }

    @RequestMapping(value = "/selDept")
    public void selDept(HttpServletResponse response){
            response.setCharacterEncoding("utf-8");

            List list = sys.selDept();

            JSONArray ja = new JSONArray();

            for (Object o : list){
                Map map = (HashMap)o;

                ja.add(map);
            }

        try {
            PrintWriter pw = response.getWriter();

            pw.println(ja.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //---------------------------------------------------------------------------------------------

    @RequestMapping("/school")
    public String school(){
        return "systemApplicationSchool";
    }
    //  //查询数据
    @RequestMapping(value = "/schooldata")
    public void schooldata(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        List<ApplicationSchoolVo> sum = sys.listSchoolData();
        json.put("code",0);
        json.put("count",sum.size());
        json.put("msg","");
        json.put("data",sum);
        pwt.print(json.toString());
    }
    //添加数据
    @RequestMapping(value = "/addSchool")
    public String addSchool(HttpServletRequest request, ApplicationSchoolVo vo){
        sys.AddSchool(vo);
        return "redirect:/system/school";
    }
    //根据id查询出数据
    @RequestMapping(value = "/selectSchoolID")
    public void selectSchoolID(int id,HttpServletResponse response) throws IOException {
        List list = sys.selectSchoolById(id);
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        for (Object o : list){
            json.put("School",o);//返回的数据格式一定要和前端的格式一样
        }
//        System.out.println("json:"+json);
        pwt.print(json.toJSONString());
    }
    //修改数据
    @RequestMapping(value = "/UpdateSchoolID/{appId}")
    public String UpdateSchoolID(@PathVariable(value = "appId")int appId, ApplicationSchoolVo vo){
        sys.updateSchoolData(vo);
        return "redirect:/system/school";
    }
    //删除数据
    @RequestMapping(value = "/delSchool")
    public String delSchool(ApplicationSchoolVo vo) {
        sys.deleSchoolDatas(vo.getAppId());
        return "redirect:/system/school";
    }
    //---------------------------------------------------------------------------------------------

    @RequestMapping("/term")
    public String term(){
        return "systemTerm";
    }
    //  //查询数据
    @RequestMapping(value = "/termdata")
    public void termdata(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        List<TermVo> sum = sys.listTermData();
        json.put("code",0);
        json.put("count",sum.size());
        json.put("msg","");
        json.put("data",sum);
        pwt.print(json.toString());
    }
    //添加数据
    @RequestMapping(value = "/addTerm")
    public String addTerm(HttpServletRequest request, TermVo vo){
        sys.AddTerm(vo);
        return "redirect:/system/term";
    }
    //根据id查询出数据
    @RequestMapping(value = "/selectTermID")
    public void selectTermID(int id,HttpServletResponse response) throws IOException {
        List list = sys.selectTermById(id);
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        for (Object o : list){
            json.put("Term",o);//返回的数据格式一定要和前端的格式一样
        }
//        System.out.println("json:"+json);
        pwt.print(json.toJSONString());
    }
    //修改数据
    @RequestMapping(value = "/UpdateTermID/{termID}")
    public String UpdateTermID(@PathVariable(value = "termID")int termID, TermVo vo){
        sys.updateTermData(vo);
        return "redirect:/system/term";
    }
    //删除数据
    @RequestMapping(value = "/delTerm")
    public String delTerm(TermVo vo) {
        sys.deleTermDatas(vo.getTermID());
        return "redirect:/system/term";
    }

    //-----------------------------------------部门管理----------------------------------------------------
    @RequestMapping("/deptTree")
    public String deptTree(){
            return "systemDeptTree";
    }
    //  //查询数据
    @RequestMapping(value = "/treedata")
    public void treedata(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        List list = sys.listTreeData();
        json.put("list",list);
//        System.out.println(json.toString());
        pwt.print(json.toString());
    }
    //部门树行管理
    @RequestMapping(value = "/systemDeptree")
    public void systemDeptree(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        JSONArray objects = new JSONArray();//树
        //子节点
        Map map = new HashMap();
        map.put("id",0);
        map.put("text","宏图");
        map.put("iconCls","icon-ok");

        //子节点下的节点
        List<Map> account = sys.listDeptree();
        JSONArray Aaa = new JSONArray();
        for(Map m : account){//增强for循环
            Map map1 = new HashMap();
            map1.put("id",m.get("depid"));
            map1.put("text",m.get("depName"));
            List<Map> account1 = sys.selectDep(String.valueOf(m.get("parentId")));
            JSONArray Bbb = new JSONArray();
            for(Map mm : account1){
                Map map2 = new HashMap();
                map2.put("id",mm.get("depid"));
                map2.put("text",mm.get("depName"));
                Bbb.add(map2);
            }
            map1.put("children",Bbb);
            Aaa.add(map1);
            map.put("children",Aaa);
        }
        objects.add(map);
        System.out.println(objects.toJSONString());
        out.print(objects.toJSONString());
    }

    //点击信息查询出班级信息
    @RequestMapping(value = "/selectDepTree/{SJ}")
    public void selectDepTree(@PathVariable(value = "SJ") String depName,HttpServletResponse response) throws IOException {
        System.out.println("fgdcfgdfgdf");
        depName = new String(depName.getBytes("ISO-8859-1"),"UTF-8");
        List list = sys.Customss(depName);
        response.setCharacterEncoding("utf-8");
        PrintWriter ptw = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("count",list.size());
        jsonObject.put("msg","");
        jsonObject.put("data",list);
        ptw.print(jsonObject.toJSONString());
    }

    //添加部门
    @RequestMapping("/addDep")
    public String addDep(HttpServletRequest request, DepVo vo ){
            sys.AddTree(vo);
        return "redirect:/system/deptTree";
        }

    //删除部门
    @RequestMapping("/delTree")
    public String delTree(DepVo vo){
        sys.deleTreeDatas(vo.getDepid());
        return "redirect:/system/deptTree";
    }

//-------------------------附属：设备维修获取班级或部门---------------------
@RequestMapping(value = "/selClass")
public void selClass(HttpServletResponse response){
    response.setCharacterEncoding("utf-8");
    List list = sys.selClass();
    JSONArray ja = new JSONArray();
    for (Object o : list){
        Map map = (HashMap)o;
        ja.add(map);
    }
    try {
        PrintWriter pw = response.getWriter();
        pw.println(ja.toString());
    } catch (IOException e) {
        e.printStackTrace();
    }
}

//-------------------------附属：问题反馈---------------------
@RequestMapping("/feedback")
public String feedback(){
            return "systemFeedback";
}
    //  //查询数据
    @RequestMapping(value = "/feedbackdata")
    public void feedbackdata(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        List<FeedbackVo> sum = sys.listFeedData();
        json.put("code",0);
        json.put("count",sum.size());
        json.put("msg","");
        json.put("data",sum);
        pwt.print(json.toString());
    }
    //添加数据
    @RequestMapping(value = "/addFeedback")
    public String addFeedback(HttpSession session,HttpServletRequest request ,FeedbackVo vo){
            vo.setEmpname("张三");
            vo.setDepId(1);
            vo.setFeedbackTime(new Date());
            vo.setEmpId(1);
          sys.AddFeed(vo);
        return "redirect:/system/feedback";
    }
    //根据id查询出数据
    @RequestMapping(value = "/selectFeedbackID")
    public void selectFeedbackID(int id,HttpServletResponse response) throws IOException {
        List list = sys.selectFeedById(id);
        response.setCharacterEncoding("utf-8");
        PrintWriter pwt = response.getWriter();
        JSONObject json = new JSONObject();
        for (Object o : list){
            json.put("Feed",o);//返回的数据格式一定要和前端的格式一样
        }
//        System.out.println("json:"+json);
        pwt.print(json.toJSONString());
    }
    //修改数据
    @RequestMapping(value = "/UpdateFeedbackID/{feedbackId}")
    public String UpdateFeedbackID(@PathVariable(value = "feedbackId")int feedbackId, FeedbackVo vo){
        sys.updateFeedData(vo);
        return "redirect:/system/feedback";
    }
    //删除数据
    @RequestMapping(value = "/delFeedback")
    public String delFeedback(FeedbackVo vo) {
//        System.out.println("id为："+vo.getFeedbackId());
        sys.deleFeedDatas(vo.getFeedbackId());
        return "redirect:/system/feedback";
    }
    //详情
    @RequestMapping("/issueDetails")
    public String issueDetails( int feedbackId,HttpServletResponse response,Model model)throws IOException {
//        System.out.println("id为:"+feedbackId);
        List list = sys.selectFeedById(feedbackId);
        //根据问题id查找对应的评论
        List MessageList =   sys.selMessageById(feedbackId);
        System.out.println(MessageList.size());
        model.addAttribute("FeedBack",list);
        model.addAttribute("Message",MessageList);
//        System.out.println(model);

            return "feedbackMessage";
    }

    //--------------------------------------附属：问题反馈留言板---------------------------------------------------------
    @RequestMapping("/message")
    public String message(){
            return "feedbackMessage";
    }

    //添加
    @RequestMapping("/addMessage")
    public String addMessage(HttpSession session,FeedbackMsgVo vo,Model model,int feedbackId){
            vo.setFeedbackId(feedbackId);
            vo.setMsgType(1);
            vo.setSingDate(new Date());
            vo.setUserId("1");
            vo.setUserName("张三");
            sys.AddMessage(vo);
//        System.out.println("id为:"+feedbackId);
        List list = sys.selectFeedById(feedbackId);
        model.addAttribute("FeedBack",list);
        List MessageList =   sys.selMessageById(feedbackId);
        model.addAttribute("Message",MessageList);
        return "feedbackMessage";
    }

}
