package com.kepler.service.impl;

import com.kepler.dao.BaseDao;
import com.kepler.service.StudentService;
import com.kepler.vo.EquipmentRepairVo;
import com.kepler.vo.StudentFloorVo;
import com.kepler.vo.StudentHuorVo;
import com.kepler.vo.StudentVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2019/12/4.
 */
/*
*
* */
@Service
public class StudentServiceImpl extends BaseDao implements StudentService {


    @Override
    public List<StudentHuorVo> listhour() {
        return hqlQuery("from StudentHuorVo");
    }

    @Override
    public void addhour(StudentHuorVo studentHuorVo) {
        save(studentHuorVo);
    }

    @Override
    public void updatehour(StudentHuorVo studentHuorVo) {
        update(studentHuorVo);
    }

    @Override
    public List listbyId(Class className,int id) {
        List list = new ArrayList();
        list.add(getObject(className,id));
        return list;
    }


    @Override
    public void deletehour(int id) {
        StudentHuorVo studentHuorVo = new StudentHuorVo();
        studentHuorVo.setHourid(id);

        delete(studentHuorVo);
    }

    @Override
    public void deletefloor(int id) {
        StudentFloorVo studentFloorVo = new StudentFloorVo();
        studentFloorVo.setFloorId(id);

        delete(studentFloorVo);
    }

    @Override
    public List<EquipmentRepairVo> listEquipmentData() {
        return hqlQuery("from EquipmentRepairVo");
    }


    @Override
    public List<StudentFloorVo> listfloor() {
        return hqlQuery("from StudentFloorVo");
    }

    @Override
    public void addfloor(StudentFloorVo studentFloorVo) {
        save(studentFloorVo);
    }

    @Override
    public List<StudentVo> liststudentdata() {
        return hqlQuery("from StudentVo");
    }

    @Override
    public void studentADD(StudentVo vo) {
        save(vo);
    }

    @Override
    public List<StudentVo> selectStudentIdData(int id) {
        return sqlQuery("select * from Student where Studid ="+id);
    }

    @Override
    public void updateStudentData(StudentVo vo) {
        update(vo);
    }

    @Override
    public void deleStudentDatas(StudentVo vo) {
        delete(vo);
    }
}
