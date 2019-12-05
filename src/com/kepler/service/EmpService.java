package com.kepler.service;

import com.kepler.vo.empVo;

import java.util.List;

/**
 * Created by ASUS on 2019/12/4.
 */
public interface EmpService {
    List sqlEmpDataList();
    void upDataEmpStatus(empVo emp);
}
