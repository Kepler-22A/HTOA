package com.kepler.service;

import com.kepler.vo.*;

import java.util.List;

/**
 * Created by ASUS on 2019/12/4.
 */
public interface EmpService {
    List sqlEmpDataList();//查员工列表数据
    void upDataEmpStatus(int empId);//修改员工状态
    void delEmp(int empId);//删除员工
    void delCharEmp(int empId);//查找员工角色对应表
    List getPostName();//查找岗位表
    List getNationList(String type,String position); //获取城市列表
    void addEmp(empVo emp); //添加员工
    List sqlPostAndDepId(String postName);//获取岗位和部门表
    empVo selEmpById(int empId);//根据获取员工Id获取员工信息
    void updataEmpVo(empVo emp);//修改员工信息
    int addCharEmp(int postId,int empId);//增加员工级联增加员工角色对应表信息
    CharEmpVo selCharEmpVoByEmpId(int empId);//查找员工角色对应表信息根据员工的Id
    int selCharacterIdByPostId(int postId);//查找角色表根据岗位Id
    void updateCharEmp(CharEmpVo charEmp);//修改员工时级联修改员工角色对应表内容
    List empWorkExperience(int empId);//查找员工工作经历
    List empEducation(int empId);//查找员工教育经历
    List empFamily(int empId);//查找员工家庭联系人
    List empAudit(int empId);//查找员工考核信息
    List empAnnex(int empId);//查找员工证件信息
    void addworkExperience(JobVo job);//新增员工的工作经历
    void deleteWorkExprience(int jobId);//删除员工工作经历
    void updateWorkExperience(JobVo job);//修改员工工作经历
    void addEducation(EducationVo education);//新增员工教育经历
    void updateEducation(EducationVo education);//修改员工教育经历
    void deleteEducation(int collegeid);//删除员工教育经历
    void addFamilyInfo(FamilyInfoVo familyInfoVo);//新增员工家庭联系信息
    void updateFamilyInfo(FamilyInfoVo familyInfoVo);//修改员工家庭联系信息
    void deleteFamilyInfo(int familyId);//删除员工家庭联系信息
    List selWeekly();//查找员工周报表信息
    List selWeeklyAndDepName(int worklogid);//查找员工周报表信息和部门名称
    List selWeeklyByEmpId(int empId);//查找员工周报表信息根据员工的ID
    void addWeekly(WeeklyVo weeklyVo);//新增周报
    void delWeekly(int weeklyLogId);//删除周报
    void updateWeekly(WeeklyVo weeklyVo);//修改周报
    WeeklyVo selWeeklyVoByWeeklylogid(int weeklylogid);//根据周报id查周报Vo
    List charRecord();//查询谈心记录
    void AddcharRecord(ChatRecordVo vo);//新增谈心记录
    List selectStudentID(String name);//查询学生id
    List selectCharRecord(int id);//根据id查询出谈心记录
    void updateCharRecord(ChatRecordVo vo);//修改谈心记录
    void delectCharRecord(ChatRecordVo vo);//删除谈心记录
    List selectChecking(int id);//查询出考勤管理

    int selWeeklyNotPush(int empId);//检测当前员工有没有提交本周周报
    int selChatRecord(int empId);//根据empId查看当前月提交了多少份谈心记录

    List selectshangjiID(int id);//查询上级id
    void addChecking(CheckingVo vo);//新增考勤管理
    List selectXiaJiChecking(int id);//查询我的下级审批
    void updatestate(CheckingVo vo);//审批通过或者不通过
    List selectCheckings(int id);//根据id查询出所有值
}
