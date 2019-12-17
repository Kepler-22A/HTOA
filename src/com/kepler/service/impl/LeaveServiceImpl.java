package com.kepler.service.impl;

import com.kepler.dao.BaseDao;
import com.kepler.service.ClassService;
import com.kepler.service.LeaveService;
import com.kepler.vo.HolidayVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2019/12/4.
 */
@Service
public class LeaveServiceImpl extends BaseDao implements LeaveService {
    @Override
    public void savaApply(HolidayVo holidayVo) {
        save(holidayVo);
    }

    @Override
    public List selEmpApplyList(int empId) {
        return sqlQuery("select *,(select empName e from empVo e where e.empId = h.empId) as empName from Holiday h where h.empId = " + empId);
    }
}
