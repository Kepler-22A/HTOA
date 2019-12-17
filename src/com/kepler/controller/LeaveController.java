package com.kepler.controller;

import com.alibaba.fastjson.JSONObject;
import com.kepler.service.EmpService;
import com.kepler.service.LeaveService;
import com.kepler.vo.HolidayVo;
import com.kepler.vo.JobVo;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
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
    @RequestMapping(value = "/addEmpApply/{processType}")
    public String apply(HolidayVo holidayVo,String startDate,String processType){
        holidayVo.setStartTime(new Date());
        holidayVo.setStatus(1); //1为审核中
        ls.savaApply(holidayVo);

        Map<String,Object> variables = new HashMap<>();
        System.out.println(holidayVo);
        variables.put("empId",holidayVo.getEmpID());//获取用户名字
        variables.put("days",holidayVo.getHolidayDay());//获取请假天数
        variables.put("holidayId",holidayVo.getHolidayID());//获取请假单据ID

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processType,variables);//根据流程定义的key启动一个流程实例
        System.out.println("流程实例"+processInstance.getId());

        //根据流程实例ID获取当前正在执行的任务
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByProcessInstanceId().desc().singleResult();
        System.out.println("任务ID"+task.getId());
        //完成任务（根据任务ID完成任务）
        taskService.complete(task.getId(),variables);

        return "redirect:jobList";
    }
}
