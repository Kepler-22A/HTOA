package com.kepler.service;

import com.kepler.vo.empVo;

import java.util.List;

/**
 * Created by ASUS on 2019/12/4.
 */
public interface EmpService {
    List sqlEmpDataList();//查员工列表数据
    void upDataEmpStatus(int empId);//修改员工状态
    void delEmp(int empId);//删除员工
    List getNationList(String type,String position); //获取城市列表
    void addEmp(empVo emp); //添加员工
    List sqlPostAndDepId(String postName);
    int addCharEmp(int postId,int empId);
}
