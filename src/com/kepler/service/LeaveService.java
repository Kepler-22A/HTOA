package com.kepler.service;

import com.kepler.vo.HolidayVo;

import java.util.List;

/**
 * Created by ASUS on 2019/12/4.
 */
public interface LeaveService {
    void savaApply(HolidayVo holidayVo);//保存员工请假单
    List selEmpApplyList(int empId);
}
