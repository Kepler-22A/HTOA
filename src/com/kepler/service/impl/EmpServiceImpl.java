package com.kepler.service.impl;

import com.kepler.dao.BaseDao;
import com.kepler.service.ClassService;
import com.kepler.service.EmpService;
import com.kepler.vo.empVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2019/12/4.
 */
@Service
public class EmpServiceImpl extends BaseDao implements EmpService {
    @Override
    public List sqlEmpDataList() {
        return sqlQuery("select\n" +
                "\t e.empId\n" +
                "\t ,e.empName\n" +
                "\t ,(select d.depName from dep d where e.depId = d.depid) as depName\n" +
                "\t ,e.postName\n" +
                "\t ,e.sex\n" +
                "\t ,e.Phone as phone\n" +
                "\t ,e.Address as address\n" +
                "\t ,e.status\n" +
                "\t ,e.password \n" +
                " from empVo e");
    }

    @Override
    public void upDataEmpStatus(int empId) {
        empVo emp = (empVo) getObject((new empVo()).getClass(),empId);
        if(emp.getStatus() == 0){
            emp.setStatus(1);
        }else {
            emp.setStatus(0);
        }

        update(emp);
    }

    @Override
    public void delEmp(int empId) {
        empVo emp = new empVo();
        emp.setEmpId(empId);
        delete(emp);
    }
}
