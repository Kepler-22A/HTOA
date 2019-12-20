package com.kepler.service;

import com.kepler.vo.AuditModelVo;
import com.kepler.vo.TemplateVo;
import com.kepler.vo.checkProjectVo;
import com.kepler.vo.checkStepVo;
import com.kepler.vo.checkResultVo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface TestService {
    public int selectLogin(String name, String pwd);//登录
    public  int selectStudentLogin(String name,String pwd);
    public int selectInt(String name);
    public int selectInt2(String name);
    int selStudentId(String stuname);
    //    public int selectInt3(Date name);
    public List selectTable();//数据
    public List selectTable2();
    public List selectTable3();
    public int addExmaine(AuditModelVo auditModelVo);//增删考核
    public int addTemplate(TemplateVo templateVo);
    public int delete(int id);
    public int delete2(int id);
    public int delete3(int id);
    public  int addCheckProject(checkProjectVo checkProjectVo);//设置项目
    public int addCheckSetp(checkStepVo setpVo);
    public int addCheckResult(checkResultVo ResultVo);
    public List selectProject(int templateId);//查看项目
    public List selectSetp(int templateId);
    public  List selectResult(int templateId);
    public List selectMyCheckProject(int templateId,int empId);//我的考评
    public String selectTime(int templateId);
    public int selectTotal(int templateId);

    //判断登陆这个账号的人是憨憨老师还是帅气学生
    public int OKAccount(String account,String pwd);


}
