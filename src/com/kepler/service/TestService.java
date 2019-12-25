package com.kepler.service;

import com.kepler.vo.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface TestService {
    public int selectLogin(String name, String pwd);//登录
    public  int selectStudentLogin(String name,String pwd);
    public int selectInt(String name);
    public int selectInt2(String name);
    int selStudentId(String stuname);
    public List selectTable();//数据
    public List selectTable2();
    public List selectTable3();
    public List selectTable4();
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
    public int selectLead(int empId);
    public int update(int templateId);
    List selectStepType(int templateId );
    /**
     * 考评打分
     */
    String selectTeacher(int stuId);//查出班主任
    int selectTeacherId(int stuId);//查出班主任Id
    int selectStuClassId(int stuId);//查出班级Id
    int selectDepId();
    int addMark(studentCheckScoreVo scoreVo);

    //判断登陆这个账号的人是憨憨老师还是帅气学生
    public int OKAccount(String account,String pwd);


}
