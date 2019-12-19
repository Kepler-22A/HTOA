package com.kepler.service.impl;

import com.kepler.dao.BaseDao;
import com.kepler.service.ClassService;
import com.kepler.service.LeaveService;
import com.kepler.vo.HolidayVo;
import com.kepler.vo.empVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public int selDepChairmanEmpId(int empId) {
        List list = sqlQuery("select empId from empVo where empName = (select chairman from dep where depid = (select depId from post where postId = (select e.postId from empVo e where empId = " + empId + ")))");
        int assigneeEmpId = 0;
        for (Object o : list){
            Map map = (HashMap)o;
            assigneeEmpId = Integer.parseInt(map.get("empId")+"");
        }
        return assigneeEmpId;
    }

    @Override
    public HolidayVo selHolidayById(int holidayId) {
        return (HolidayVo) getObject(HolidayVo.class,holidayId);
    }

    @Override
    public String selEmpNameByEmpId(int empId) {
        List list = sqlQuery("select empName from empVo where empId = " + empId);
        String empName = "";
        for (Object o : list){
            Map map = (HashMap)o;
            empName = map.get("empName") + "";
        }
        return empName;
    }

    @Override
    public void updateHolidayVo(HolidayVo holidayVo) {
        update(holidayVo);
    }

    @Override
    public int selHTXZEmpId() {
        List list = sqlQuery("select empId from empVo where empName = (select chairman from dep where depId = (select depId from post where postName = '校长'))");
        int empId = 0;
        for (Object o : list){
            Map map = (HashMap)o;
            empId = Integer.parseInt(map.get("empId")+"");
        }

        return empId;
    }

    @Override
    public empVo selEmpByEmpId(int empId) {
        return (empVo)getObject(empVo.class,empId);
    }
}

