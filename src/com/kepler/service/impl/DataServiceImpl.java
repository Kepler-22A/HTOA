package com.kepler.service.impl;

import com.kepler.dao.BaseDao;
import com.kepler.service.DataService;
import com.kepler.vo.DataDocVo;
import com.kepler.vo.EnrollmentVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2019/12/4.
 */
@Service
public class DataServiceImpl extends BaseDao implements DataService {
    @Override
    public List selDocData() {
        return sqlQuery("select d.*,e.empName from dataDoc d left join empVo e on d.empid = e.empId");
    }

    @Override
    public void addFile(DataDocVo docVo) {
        save(docVo);
    }

    @Override
    public DataDocVo findById(int id) {
        return (DataDocVo)getObject(DataDocVo.class ,id);
    }

    @Override
    public DataDocVo findFile(Integer docId) {
        return (DataDocVo) getObject(DataDocVo.class,docId);
    }

    @Override
    public void deleteFile(DataDocVo dataDocVO) {
        delete(dataDocVO);
    }

    @Override
    public List selEnrollData() {
        return sqlQuery("select e.*,em.empName,ct.classTypeName,d.deptName from enrollment e " +
                "left join empVo em on e.empid=em.empId left join classType ct on e.studType=ct.calssTypeId " +
                "left join Dept d on e.majorId=d.deptID");
    }

    @Override
    public void addEnrllor(EnrollmentVo vo) {
        save(vo);
    }

    @Override
    public List selMajor(){
        return sqlQuery("select * from Dept");

    }
    @Override
    public List selClassType(){
        return sqlQuery("select * from classType");

    }
    @Override
    public List selEmp(){
        return sqlQuery("select * from empVo");
    }

    @Override
    public void delEnrllor(int id){
        EnrollmentVo vo = new EnrollmentVo();
        vo.setEnrollmentid(id);
        delete(vo);
    }
}
