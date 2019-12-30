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
    public List selectTable5();
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

    /**
     * 我的考评
     *
     */
    public  List selectOverCheck();//查询已结束的考评
    public List selectMyCheck(int templateId,int empId);
    public List selectMyCheckProject(int template,int empId); //根据模板Id和empId 查询总得分
    public int selectLead(int empId);
    String selectLeadState(int empId); //查是否已考评！
    public int update(int templateId);
    int update2(int empId);
    int updateClose(int template);
    List selectStepType(int templateId );
    int deletePeople(int templateId);
    int selectScore(int empId);
    int selectScore2(int empId);
    float selectWeight(int templateId);
    float selectWeight2(int templateId);
    int addCheckScore(checkScoerVo scoreVo );
    int  selectProjectCount(int templateId);
    int selectEmpScoreCount(int empId);
    /**
     * 考评打分
     */
    String selectTeacher(int stuId);//查出班主任
    String selectTeacher2(int stuId); //查出任课老师
    int selectTeacherId(int stuId);//查出班主任Id
    int selectTeacherId2(int stuId);//查出任课老师Id
    int selectStuClassId(int stuId);//查出班级Id
    int  selectTeacherID(String empName);
    int selectDepId();
    int selectDepId2(String empName);
    int selectDepID(int template);
    int addMark(studentCheckScoreVo scoreVo);
    int addLeadMark(leadCheckScoreVo leadCheckScoreVo);

    List selectCheckPeople(int template);//查出考评人员
    int addCheckPeople(checkPeopleVo peopleVo);

    //判断登陆这个账号的人是憨憨老师还是帅气学生
    public int OKAccount(String account,String pwd);

    public String selEmpType(int empId);//查询员工类型


}
