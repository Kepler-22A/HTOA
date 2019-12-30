package com.kepler.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kepler.service.EmpService;
import com.kepler.service.LeaveService;
import com.kepler.vo.HolidayStudent;
import com.kepler.vo.HolidayVo;
import com.kepler.vo.JobVo;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by ASUS on 2019/12/4.
 */
@Controller
@RequestMapping("/leave")
public class LeaveController {
    @Resource
    private LeaveService ls;

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

    /**
     *
     * 跳转到流程查看页面
     * */
    @RequestMapping("/listProcessDefinition")
    public String listProcessDefinition(){
        return "listProcessDefinition";
    }

    /**
    *
    * 获取流程列表
    *
    * */
    @RequestMapping(value = "/progressDefine")
    public void progressDefine(HttpServletResponse response,Model model){
        response.setCharacterEncoding("utf-8");

        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        query.orderByProcessDefinitionVersion().desc();
        List<ProcessDefinition> list = query.list();

        JSONObject jo = new JSONObject();

        List la = new ArrayList();

        for (Object o : list){
            ProcessDefinition processDefinition = (ProcessDefinition)o;
            Map map = new HashMap();
            map.put("id",processDefinition.getId());
            map.put("name",processDefinition.getName());
            map.put("key",processDefinition.getKey());
            map.put("deploymentId",processDefinition.getDeploymentId());
            map.put("diagramResourceName",processDefinition.getDiagramResourceName());
            map.put("description",processDefinition.getDescription());
            map.put("tenantId",processDefinition.getTenantId());
            map.put("version",processDefinition.getVersion());
            map.put("category",processDefinition.getCategory());
            map.put("resourceName",processDefinition.getResourceName());

            la.add(map);
        }

        jo.put("msg","");
        jo.put("code",0);
        jo.put("count",list.size());
        jo.put("data",la);

        try {
            PrintWriter pw = response.getWriter();

            pw.print(jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传流程
     * */
    @RequestMapping(value = "/uploadProcess")
    public String uploadProcess(MultipartFile pdFile){

        System.out.println("uploadProcess方法");
        try {
            //创建临时file对象
            File file=File.createTempFile("tmp", null);
            //把MultipartFile对象转换成java.io.FileUI对象
            pdFile.transferTo(file);
            //部署Zip文件  将流程上传到Activiti框架
            Deployment deployment = repositoryService.createDeployment().addZipInputStream(new ZipInputStream(new FileInputStream(file))).deploy();
            System.out.println("ID "+deployment.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/leave/listProcessDefinition";
    }

    /**
     * 删除流程定义
     */
    @RequestMapping("/delProgressDefine/{deploymentId}")
    public String delProgressDefine(@PathVariable(value = "deploymentId") String deploymentId){
        repositoryService.deleteDeployment(deploymentId,true);
        return "redirect:/leave/listProcessDefinition";
    }


    /**
     *
     * 查看流程图
     *
     * */
    @RequestMapping("/viewProcessImage/{did}/{imageName}")
    public String viewProcessImage(@PathVariable(value = "did") String did, @PathVariable(value = "imageName") String imageName, HttpServletResponse resp){
        InputStream in = repositoryService.getResourceAsStream(did,imageName+".png");
        try {
            OutputStream out = resp.getOutputStream();
            // 把图片的输入流程写入resp的输出流中
            byte[] b = new byte[1024];
            for (int len = -1; (len= in.read(b))!=-1; ) {
                out.write(b, 0, len);
            }
            // 关闭流
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 下载流程图
     */
    @RequestMapping("/toExport/{id}")
    public String toExport(@PathVariable(value = "id") String id,HttpServletResponse resp){
        try {

            //设置response对象的头参数，attachment就是附件，filename=文件名称
            resp.setHeader("Content-disposition","attachment;filename=" +id+".zip" );
            //下载的文件类型是zip文件
            resp.setContentType("application/x-zip-compressed");

            //----------------------------------------------------------------------------

            //流程定义对象
            ProcessDefinition processDefinition = repositoryService
                    .createProcessDefinitionQuery()
                    .processDefinitionId(id).singleResult();
            //部署id
            String deploymentId = processDefinition.getDeploymentId();

            //bpmn资源文件名称
            String resourceName_bpmn = processDefinition.getResourceName();
            //bpmn资源文件输入流
            InputStream inputStream_bpmn = repositoryService.getResourceAsStream(deploymentId, resourceName_bpmn);
            //png文件名称
            String resourceName_png = processDefinition.getDiagramResourceName();
            //png资源文件输入流
            InputStream inputStream_png = repositoryService.getResourceAsStream(deploymentId, resourceName_png);

            //------创建输出流，绑定到response对象-------------------------------------------------------
            OutputStream out = resp.getOutputStream();
            //创建ZIP输出对象，绑定到输出流
            ZipOutputStream zipo = new ZipOutputStream(out);

            //流复制
            byte[] b = new byte[1024];
            int len = -1;

            //定义zip压缩包中的文件对象（zip实体）
            ZipEntry ze = new ZipEntry(resourceName_bpmn);
            //把创建的实体对象放到压缩包中
            zipo.putNextEntry(ze);
            //文件内容拷贝
            while((len = inputStream_bpmn.read(b,0,1024)) != -1){
                zipo.write(b,0,b.length);
            }
            zipo.closeEntry();
            //---------------
            ZipEntry ze1 = new ZipEntry(resourceName_png);
            zipo.putNextEntry(ze1);
            while((len = inputStream_png.read(b,0,1024)) != -1){
                zipo.write(b,0,b.length);
            }
            //关闭流
            inputStream_bpmn.close();
            inputStream_png.close();
            zipo.flush();
            zipo.close();
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /****************************************************************员工请假部分**********************************************************************/

    //跳转到员工请假页面
    @RequestMapping(value = "/toEmpApplyPage")
    public String toEmpApplyPage(){
        return "empApply";
    }

    //查员工请假单数据
    @RequestMapping(value = "/selEmpApply")
    public void selEmpApply(HttpSession session,HttpServletResponse response){
        response.setCharacterEncoding("utf-8");

        int empId = Integer.parseInt((session.getAttribute("empId")+""));

        List list = ls.selEmpApplyList(empId);

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

    //提交请假单
    @RequestMapping(value = "/addEmpApply")
    public String apply(HolidayVo holidayVo,String startTimeEX,String endTimeEX,String processType,String holidayEX1,String holidayEX2){
        System.out.println(processType);

        holidayVo.setStartTime(new Date());
        holidayVo.setStatus(1); //1为审核中

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(startTimeEX);
            d2 = format.parse(endTimeEX);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holidayVo.setStartTime(d1);

//        holidayVo.setEndTime(java.sql.Date.valueOf(endTimeEX));

        holidayVo.setEndTime(d2);

        float holidayDay = Float.parseFloat(holidayEX1) + Float.parseFloat(holidayEX2);

        holidayVo.setHolidayDay(holidayDay);

        System.out.println(holidayVo);

        ls.savaApply(holidayVo);

        Map<String,Object> variables = new HashMap<>();
        System.out.println(holidayVo);
        variables.put("empId",holidayVo.getEmpID());//获取用户名字
        variables.put("day",(int)holidayVo.getHolidayDay());//获取请假天数
        variables.put("holidayId",holidayVo.getHolidayID());//获取请假单据ID

        //去查询部门主任ID以便设置下一个办理人
        int assignee = ls.selDepChairmanEmpId(holidayVo.getEmpID());

        variables.put("assignee",assignee);//设置下一个办理人

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processType,variables);//根据流程定义的key启动一个流程实例
        System.out.println("流程实例"+processInstance.getId());

        //根据流程实例ID获取当前正在执行的任务
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByProcessInstanceId().desc().singleResult();
        System.out.println("任务ID"+task.getId());
        //完成任务（根据任务ID完成任务）
        taskService.complete(task.getId(),variables);

        if(holidayVo.getEmpID() == assignee){//如果申请的人为部门主任，查询宏图校长的empId授权办理
            assignee = ls.selHTXZEmpId();
            variables.put("type","zr");
            variables.put("assignee",assignee);//设置下一个办理人
            //根据流程实例ID获取当前正在执行的任务
            task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByProcessInstanceId().desc().singleResult();
            taskService.complete(task.getId(),variables);
        }

        return "redirect:/leave/toEmpApplyPage";
    }

    //获取所有请假类型
    @RequestMapping(value = "/selProcessType")
    public void selProcessType(HttpServletResponse response){
        response.setCharacterEncoding("utf-8");

        JSONArray ja = new JSONArray();

        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        query.orderByProcessDefinitionVersion().desc();
        List<ProcessDefinition> list = query.list();

        for (Object o : list){
            ProcessDefinition processDefinition = (ProcessDefinition)o;
            Map map = new HashMap();
            map.put("id",processDefinition.getId());
            map.put("name",processDefinition.getName());
            map.put("key",processDefinition.getKey());
            map.put("deploymentId",processDefinition.getDeploymentId());
            map.put("diagramResourceName",processDefinition.getDiagramResourceName());
            map.put("description",processDefinition.getDescription());
            map.put("tenantId",processDefinition.getTenantId());
            map.put("version",processDefinition.getVersion());
            map.put("category",processDefinition.getCategory());
            map.put("resourceName",processDefinition.getResourceName());

            ja.add(map);
        }

        try {
            PrintWriter pw = response.getWriter();

            pw.print(ja.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查看当前执行节点（红色框高亮显示）
     */
    @RequestMapping("/lookTaskImg/{holidayID}/{idType}")
    public String lookTaskImg(@PathVariable(value = "holidayID") String holidayId,@PathVariable(value = "idType") String idType,Model model){
        String processInstanceId = "";//流程实例ID
        if("holidayID".equals(idType)){//如果传过来的值为holidayID
            if(holidayId!=null&&!"".equals(holidayId)){
                //通过单据id查找实例对象

                System.out.println(holidayId);

                HistoricVariableInstance hvi = historyService.createHistoricVariableInstanceQuery().variableValueEquals("holidayId", Integer.parseInt(holidayId)).singleResult();
                //通过历史流程变量查询变量对象(获取流程实例ID)
                processInstanceId = hvi.getProcessInstanceId();
            }
        }else {//如果传过来的值为processInstanceId
            processInstanceId = holidayId;
        }
        //获取历史任务实例
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();

        if(historicProcessInstance!=null){
            //获取流程定义信息
            ProcessDefinition pd = repositoryService.getProcessDefinition(historicProcessInstance.getProcessDefinitionId());
            // 获取流程定义的实体（包含了流程中的任务节点信息，连线信息）
            ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity)pd;
            // 获取流程历史中已执行节点，并按照节点在流程中执行先后顺序排序
            List<HistoricActivityInstance> historicActivityInstanceList = historyService.createHistoricActivityInstanceQuery()
                    .processInstanceId(processInstanceId).orderByHistoricActivityInstanceId().asc().list();
            // 已经激活的节点ID集合
            //激活的节点（1.任务已经完成；2.任务已经开始，但还未结束）
            List mapList = new ArrayList();
            //获取已经激活的节点ID
            for (HistoricActivityInstance activityInstance : historicActivityInstanceList) {
                //getActivityId方法获取已经激活的节点id
                ActivityImpl activityImpl = processDefinition.findActivity(activityInstance.getActivityId());
                //获取当前节点在图片中的坐标位置，左上角坐标及长宽
                int x = activityImpl.getX();
                int y = activityImpl.getY();
                int height = activityImpl.getHeight();
                int width = activityImpl.getWidth();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("x", x);
                map.put("y", y);
                map.put("height", height);
                map.put("width", width);
                mapList.add(map);
            }
            model.addAttribute("pd",pd);
            model.addAttribute("mapList",mapList);
        }

        return "image";
    }

    /**
     * 去我的任务页面
     */
    @RequestMapping("/toMyTaskPage")
    public String toMyTaskPage(){
        return "myTask";
    }

    /**
     * select我的流程任务（emp）
     * */
    @RequestMapping(value = "/selMyTask")
    public void selMyTask(HttpSession session,HttpServletResponse response){
        response.setCharacterEncoding("utf-8");

        System.out.println(session.getAttribute("empId"));

        String actorId = session.getAttribute("empId").toString();
        //根据用户名称去查询任务列表(act_ru_task)
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(actorId).list();

        List la = new ArrayList();

        for (Object o : taskList){
            Task task = (Task)o;
            Map map = new HashMap();

            map.put("id",task.getId());
            map.put("processInstanceId",task.getProcessInstanceId());
            map.put("processDefinitionId",task.getProcessDefinitionId());
            map.put("name",task.getName());
            map.put("assignee",task.getAssignee());
            map.put("createTime",task.getCreateTime());

            if ("部门主管审批".equals(map.get("name")+"") || "校长审批".equals(map.get("name")+"")){
                la.add(map);
            }
        }

        JSONObject jo = new JSONObject();

        jo.put("msg","");
        jo.put("code",0);
        jo.put("count",taskList.size());
        jo.put("data",la);

        try {
            PrintWriter pw = response.getWriter();

            pw.print(jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查看共有多少个emp请假任务等你审
     * */
    @RequestMapping(value = "/selMyTaskNumber")
    @ResponseBody
    public void selMyTaskNumber(HttpSession session,HttpServletResponse response){
        response.setCharacterEncoding("utf-8");

        System.out.println(session.getAttribute("empId"));

        String actorId = session.getAttribute("empId").toString();
        //根据用户名称去查询任务列表(act_ru_task)
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(actorId).list();

        int count = 0;

        for (Object o : taskList){
            Task task = (Task)o;

            if ("部门主管审批".equals(task.getName()) || "校长审批".equals(task.getName())){
                count += 1;
            }
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

    /**
     * 查看emp请假详情
     * */
    @RequestMapping("/taskDetail/{taskId}/{instId}")
    public String taskDetail(@PathVariable(value = "taskId") String taskId,@PathVariable(value = "instId")String instId,Model model){
        //根据流程实例ID查询流程实例
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(instId).singleResult();
        //根据任务ID 查询任务实例
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //历史审批信息
        List<Comment> commentList = taskService.getProcessInstanceComments(instId);

        //获取流程定义id
        String processDefineId = task.getProcessDefinitionId();
        //查询流程定义实体对象
        ProcessDefinitionEntity pdentity = (ProcessDefinitionEntity)processEngine.getRepositoryService().getProcessDefinition(processDefineId);
        //获取当前活动id
        String activeId = pi.getActivityId();
        System.out.println("当前活动ID "+activeId);
        //获取当前活动(usertask2)
        ActivityImpl impl = pdentity.findActivity(activeId);
        //获取当前活动的连线(此List不能直接在页面上遍历)
        List<PvmTransition> pvmlist = impl.getOutgoingTransitions();
        List plist = new ArrayList();
        for(PvmTransition pvm:pvmlist){
            Map map = new HashMap();
            if(pvm.getProperty("name")==null){//如果没有设置连线名称，给一个默认的选项
                map.put("id",0);
                map.put("name","审批");
            }else{
                map.put("id",pvm.getId());
                map.put("name",pvm.getProperty("name"));
            }

            plist.add(map);
        }
        if (taskService.getVariable(taskId, "holidayId") == null || "".equals(taskService.getVariable(taskId, "holidayId").toString())){
            //获取holidayID
            int holidayId =  Integer.parseInt(taskService.getVariable(taskId, "holidayStudentID").toString());
            //根据学生单据ID查询学生对象
            HolidayStudent holidayStudent = ls.selHolidayStudentById(holidayId);
            String stuName = ls.selStuNameByStuId(holidayStudent.getStudentId());

            model.addAttribute("taskId",taskId);
            model.addAttribute("commentList",commentList);
            model.addAttribute("plist",plist);
            model.addAttribute("holidayVo",holidayStudent);
            model.addAttribute("stuName",stuName);
            return "adultStudent";
        }else {
            //获取holidayID
            int holidayId =  Integer.parseInt(taskService.getVariable(taskId, "holidayId").toString());
            //根据单据ID查询对象
            HolidayVo holidayVo = ls.selHolidayById(holidayId);
            String empName = ls.selEmpNameByEmpId(holidayVo.getEmpID());

            model.addAttribute("holidayVo",holidayVo);
            model.addAttribute("empName",empName);
            model.addAttribute("taskId",taskId);
            model.addAttribute("commentList",commentList);
            model.addAttribute("plist",plist);

            return "adult";
        }
    }

    /**
     * 审批
     */
    @RequestMapping("/complete")
    public String complete(int holidayId,String taskId,String flow,String comment,HttpSession session){
        //根据任务ID得到任务对象
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //通过任务对象获取流程实例ID
        String processInstId = task.getProcessInstanceId();
        //根据单据ID查询单据对象
        HolidayVo holidayVo = ls.selHolidayById(holidayId);

        //获取用户名称
        String userId = session.getAttribute("empId").toString();
        //设置当前任务办理人（主要是备注表）
        Authentication.setAuthenticatedUserId(userId);

        //设置备注信息(任务ID，实例ID，页面上的备注)
        taskService.addComment(taskId,processInstId,comment);

        //添加任务变量
        Map variable = new HashMap();
        variable.put("status",flow);

        int holidayDay = (int) holidayVo.getHolidayDay();
        if(holidayDay >= 2){//如果请假天数大于2，将查询宏图校长的empId授权办理
            //查询宏图校长的empId授权办理
            int assignee = ls.selHTXZEmpId();
            variable.put("assignee",assignee);//设置下一个办理人
        }

        //完成当前任务
        variable.put("empId",holidayVo.getEmpID());//获取用户名字
        variable.put("day",(int)holidayVo.getHolidayDay());//获取请假天数
        variable.put("holidayId",holidayVo.getHolidayID());//获取请假单据ID
        variable.put("type","");//设置一个type防止报错

        taskService.complete(taskId,variable);

        //根据流程实例获取实例对象(完成流程的实例依然会存放在数据库中 但是查询出来是null的)
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstId).singleResult();
        if(processInstance == null && flow.equals("agree")){
            //修改单据状态
            holidayVo.setStatus(2);//2、审批通过
            ls.updateHolidayVo(holidayVo);
        }else if(processInstance == null && flow.equals("disagree")){
            //修改单据状态
            holidayVo.setStatus(3);//2、审批不通过
            ls.updateHolidayVo(holidayVo);
        }

        return "redirect:/leave/toMyTaskPage";

    }

    /**
     * 我的单据- 查看备注
     */
    @RequestMapping("/lookComment/{holidayID}")
    public String lookComment(HttpSession session,Model model,@PathVariable(value = "holidayID")int holidayID){
        //通过jobID查询历史变量对象
        HistoricVariableInstance hvi = historyService.createHistoricVariableInstanceQuery().variableValueEquals("holidayId",holidayID).singleResult();
        //获取流程实例id （查询历史批注）
        List<Comment> commentList = taskService.getProcessInstanceComments(hvi.getProcessInstanceId());
        List empNameList = new ArrayList();

        for (Object o : commentList){
            Comment c = (Comment)o;

            Map map = new HashMap();
            map.put("empName",ls.selEmpNameByEmpId(Integer.parseInt(c.getUserId())));
            map.put("empId",Integer.parseInt(c.getUserId()));
            empNameList.add(map);
        }

        model.addAttribute("commentList",commentList);
        model.addAttribute("empNameList",empNameList);

        return "comment";
    }

    /*****学生请假************************************************************************************************************************************/

    //跳转到学生请假页面
    @RequestMapping(value = "/toStuApplyPage")
    public String toStuApplyPage(){
        return "studentApply";
    }

    //提交学生请假单
    @RequestMapping(value = "/addStuApply")
    public String addStuApply(HolidayStudent holidayVo, String startTimeEX, String endTimeEX, String processType, String holidayEX1, String holidayEX2){
        System.out.println(processType);

        holidayVo.setStartTime(new Date());
        holidayVo.setStatus(1); //1为审核中

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(startTimeEX);
            d2 = format.parse(endTimeEX);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holidayVo.setStartTime(d1);

//        holidayVo.setEndTime(java.sql.Date.valueOf(endTimeEX));

        holidayVo.setEndTime(d2);

        float holidayDay = Float.parseFloat(holidayEX1) + Float.parseFloat(holidayEX2);

        holidayVo.setHolidayDay(holidayDay);

        System.out.println(holidayVo);

        ls.savaStuApply(holidayVo);

        Map<String,Object> variables = new HashMap<>();
        System.out.println(holidayVo);
        variables.put("stuId",holidayVo.getStudentId());//获取用户名字
        variables.put("day",(int)holidayVo.getHolidayDay());//获取请假天数
        variables.put("holidayStudentID",holidayVo.getHolidayid());//获取请假单据ID

        //去查询班主任ID以便设置下一个办理人
        int assignee = ls.selEmpIdByClazz(ls.selStuByStuId(holidayVo.getStudentId()).getClazz());

        variables.put("assignee",assignee);//设置下一个办理人

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processType,variables);//根据流程定义的key启动一个流程实例
        System.out.println("流程实例"+processInstance.getId());

        //根据流程实例ID获取当前正在执行的任务
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByProcessInstanceId().desc().singleResult();
        System.out.println("任务ID"+task.getId());
        //完成任务（根据任务ID完成任务）
        taskService.complete(task.getId(),variables);

//        if(holidayVo.getEmpID() == assignee){//如果申请的人为部门主任，查询宏图校长的empId授权办理
//            assignee = ls.selHTXZEmpId();
//            variables.put("type","zr");
//            variables.put("assignee",assignee);//设置下一个办理人
//            //根据流程实例ID获取当前正在执行的任务
//            task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByProcessInstanceId().desc().singleResult();
//            taskService.complete(task.getId(),variables);
//        }

        return "redirect:/leave/toStuApplyPage";
    }

    //查学生请假单数据
    @RequestMapping(value = "/selStuApply")
    public void selStuApply(HttpSession session,HttpServletResponse response){
        response.setCharacterEncoding("utf-8");

        List list = new ArrayList();
        if (session.getAttribute("studentId") != null){
            int studentId = Integer.parseInt((session.getAttribute("studentId")+""));

            list = ls.selStudentLeaveList(studentId);
        }else {
            HashMap map = new HashMap();
            map.put("massage","当前员工登录");
            list.add(map);
        }


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
     * 我的单据- 查看备注 学生
     */
    @RequestMapping("/lookCommentStudent/{holidayStudentId}")
    public String lookCommentStudent(HttpSession session,Model model,@PathVariable(value = "holidayStudentId")int holidayStudentId){
        //通过jobID查询历史变量对象
        HistoricVariableInstance hvi = historyService.createHistoricVariableInstanceQuery().variableValueEquals("holidayStudentID",holidayStudentId).singleResult();
        //获取流程实例id （查询历史批注）
        List<Comment> commentList = taskService.getProcessInstanceComments(hvi.getProcessInstanceId());
        List empNameList = new ArrayList();

        for (Object o : commentList){
            Comment c = (Comment)o;

            Map map = new HashMap();
            map.put("empName",ls.selEmpNameByEmpId(Integer.parseInt(c.getUserId())));
            map.put("stuId",Integer.parseInt(c.getUserId()));
            empNameList.add(map);
        }

        model.addAttribute("commentList",commentList);
        model.addAttribute("empNameList",empNameList);

        return "commentStudent";
    }

    /**
     * 查看当前执行节点（红色框高亮显示）
     */
    @RequestMapping("/lookTaskImgStudent/{holidayID}/{idType}")
    public String lookTaskImgStudent(@PathVariable(value = "holidayID") String holidayId,@PathVariable(value = "idType") String idType,Model model){
        String processInstanceId = "";//流程实例ID
        if("holidayStudentID".equals(idType)){//如果传过来的值为holidayID
            if(holidayId!=null&&!"".equals(holidayId)){
                //通过单据id查找实例对象

                System.out.println(holidayId);

                HistoricVariableInstance hvi = historyService.createHistoricVariableInstanceQuery().variableValueEquals("holidayStudentID", Integer.parseInt(holidayId)).singleResult();
                //通过历史流程变量查询变量对象(获取流程实例ID)
                processInstanceId = hvi.getProcessInstanceId();
            }
        }else {//如果传过来的值为processInstanceId
            processInstanceId = holidayId;
        }
        //获取历史任务实例
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();

        if(historicProcessInstance!=null){
            //获取流程定义信息
            ProcessDefinition pd = repositoryService.getProcessDefinition(historicProcessInstance.getProcessDefinitionId());
            // 获取流程定义的实体（包含了流程中的任务节点信息，连线信息）
            ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity)pd;
            // 获取流程历史中已执行节点，并按照节点在流程中执行先后顺序排序
            List<HistoricActivityInstance> historicActivityInstanceList = historyService.createHistoricActivityInstanceQuery()
                    .processInstanceId(processInstanceId).orderByHistoricActivityInstanceId().asc().list();
            // 已经激活的节点ID集合
            //激活的节点（1.任务已经完成；2.任务已经开始，但还未结束）
            List mapList = new ArrayList();
            //获取已经激活的节点ID
            for (HistoricActivityInstance activityInstance : historicActivityInstanceList) {
                //getActivityId方法获取已经激活的节点id
                ActivityImpl activityImpl = processDefinition.findActivity(activityInstance.getActivityId());
                //获取当前节点在图片中的坐标位置，左上角坐标及长宽
                int x = activityImpl.getX();
                int y = activityImpl.getY();
                int height = activityImpl.getHeight();
                int width = activityImpl.getWidth();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("x", x);
                map.put("y", y);
                map.put("height", height);
                map.put("width", width);
                mapList.add(map);
            }
            model.addAttribute("pd",pd);
            model.addAttribute("mapList",mapList);
        }

        return "image";
    }

    /**
     * 去我的任务页面
     */
    @RequestMapping("/toMyTaskStudentPage")
    public String toMyTaskStudentPage(){
        return "myTaskStudent";
    }

    /**
     * select我的流程任务（emp）
     * */
    @RequestMapping(value = "/selMyTaskStudent")
    public void selMyTaskStudent(HttpSession session,HttpServletResponse response){
        response.setCharacterEncoding("utf-8");

        System.out.println(session.getAttribute("empId"));

        String actorId = session.getAttribute("empId").toString();
        //根据用户名称去查询任务列表(act_ru_task)
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(actorId).list();

        List la = new ArrayList();

        for (Object o : taskList){
            Task task = (Task)o;
            Map map = new HashMap();

            map.put("id",task.getId());
            map.put("processInstanceId",task.getProcessInstanceId());
            map.put("processDefinitionId",task.getProcessDefinitionId());
            map.put("name",task.getName());
            map.put("assignee",task.getAssignee());
            map.put("createTime",task.getCreateTime());

            if ("班主任审批".equals(map.get("name")+"") || "校长审批".equals(map.get("name")+"")){
                la.add(map);
            }
        }

        JSONObject jo = new JSONObject();

        jo.put("msg","");
        jo.put("code",0);
        jo.put("count",taskList.size());
        jo.put("data",la);

        try {
            PrintWriter pw = response.getWriter();

            pw.print(jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 查看共有多少个emp请假任务等你审
     * */
    @RequestMapping(value = "/selMyTaskStudentNumber")
    @ResponseBody
    public void selMyTaskStudentNumber(HttpSession session,HttpServletResponse response){
        response.setCharacterEncoding("utf-8");

        System.out.println(session.getAttribute("empId"));

        String actorId = session.getAttribute("empId").toString();
        //根据用户名称去查询任务列表(act_ru_task)
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(actorId).list();

        int count = 0;

        for (Object o : taskList){
            Task task = (Task)o;

            if ("班主任审批".equals(task.getName()) || "校长审批".equals(task.getName())){
                count += 1;
            }
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


    /**
     * 审批
     */
    @RequestMapping("/completeStudent")
    public String completeStudent(int holidayId,String taskId,String flow,String comment,HttpSession session){
        //根据任务ID得到任务对象
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //通过任务对象获取流程实例ID
        String processInstId = task.getProcessInstanceId();
        //根据单据ID查询单据对象
        HolidayStudent holidayStudent = ls.selHolidayStudentById(holidayId);

        //获取用户名称
        String userId = session.getAttribute("empId").toString();
        //设置当前任务办理人（主要是备注表）
        Authentication.setAuthenticatedUserId(userId);

        //设置备注信息(任务ID，实例ID，页面上的备注)
        taskService.addComment(taskId,processInstId,comment);

        //添加任务变量
        Map variable = new HashMap();
        variable.put("status",flow);

        int holidayDay = (int) holidayStudent.getHolidayDay();
        if(holidayDay >= 2){//如果请假天数大于2，将查询宏图校长的empId授权办理
            //查询宏图校长的empId授权办理
            int assignee = ls.selHTXZEmpId();
            variable.put("assignee",assignee);//设置下一个办理人
        }

        //完成当前任务
        variable.put("stuId",holidayStudent.getStudentId());//获取用户名字
        variable.put("day",(int)holidayStudent.getHolidayDay());//获取请假天数
        variable.put("holidayStudentID",holidayStudent.getHolidayid());//获取请假单据ID
        variable.put("type","");//设置一个type防止报错

        taskService.complete(taskId,variable);

        //根据流程实例获取实例对象(完成流程的实例依然会存放在数据库中 但是查询出来是null的)
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstId).singleResult();
        if(processInstance == null && flow.equals("agree")){
            //修改单据状态
            holidayStudent.setStatus(2);//2、审批通过
            ls.updateHolidayStudentVo(holidayStudent);
        }else if(processInstance == null && flow.equals("disagree")){
            //修改单据状态
            holidayStudent.setStatus(3);//2、审批不通过
            ls.updateHolidayStudentVo(holidayStudent);
        }

        return "redirect:/leave/toMyTaskStudentPage";

    }

    /*********************************************离职申请***************************************/

    @RequestMapping(value = "/toDimissionPage")
    public String toDimissionPage(){
        return "dimission";
    }

}
