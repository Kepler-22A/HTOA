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
}
