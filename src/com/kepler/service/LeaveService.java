package com.kepler.service;

import com.kepler.vo.HolidayStudent;
import com.kepler.vo.HolidayVo;
import com.kepler.vo.StudentVo;
import com.kepler.vo.empVo;

import java.util.List;

/**
 * Created by ASUS on 2019/12/4.
 */
public interface LeaveService {
    void savaApply(HolidayVo holidayVo);//保存员工请假单
    List selEmpApplyList(int empId);//查询用户自己(根据用户ID)的请假列表
    int selDepChairmanEmpId(int empId);//查询当前用户的部门主管的empId
    HolidayVo selHolidayById(int holidayId);//根据holidayId查询holidayVo
    String selEmpNameByEmpId(int empId);//根据empId取empName
    void updateHolidayVo(HolidayVo holidayVo);//修改holidayVo
    int selHTXZEmpId();//查询宏图校长的empId
    empVo selEmpByEmpId(int empId);//根据empId查找empVo

    List selStudentLeaveList(int studentId);//查询学生自己(根据学生ID)的请假列表
    String selStuNameByStuId(int studentId);//根据studentId取stuname
    void savaStuApply(HolidayStudent holidayVo);//保存员工请假单
    StudentVo selStuByStuId(int stuId);//根据stuId查找stuVo
    int selEmpIdByClazz(int clazz);//根据班级id拿班主任id
    HolidayStudent selHolidayStudentById(int holidayStudentId);//根据holidayId查询holidayVo
    void updateHolidayStudentVo(HolidayStudent holidayVo);//修改holidayStudentVo
}
