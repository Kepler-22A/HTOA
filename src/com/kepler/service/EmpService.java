package com.kepler.service;

import com.kepler.vo.CharEmpVo;
import com.kepler.vo.JobVo;
import com.kepler.vo.empVo;

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
}
