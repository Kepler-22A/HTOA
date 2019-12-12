package com.kepler.service.impl;

import com.kepler.dao.BaseDao;
import com.kepler.service.StudentService;
import com.kepler.vo.*;
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
    public void AddEuipment(EquipmentRepairVo equipmentRepairVo) {
        save(equipmentRepairVo);
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
    public List listEquipmentbyId(Class className, int id) {
        List list = new ArrayList();
        list.add(getObject(className,id));
        return list;
    }

    @Override
    public void updateEquiment(EquipmentRepairVo equipmentRepairVo) {
        update(equipmentRepairVo);
    }

    @Override
    public void delEquipment(int id) {
        EquipmentRepairVo equipmentRepairVo = new EquipmentRepairVo();
        equipmentRepairVo.setEquipmentId(id);

        delete(equipmentRepairVo);
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

    @Override
    public List<Student_scoreVo> listStudentScore() {
        return sqlQuery("select scoreId,t.stuname,s.score,Rescore,courseName,courseTypeName,termName,scoreTime,o.empName,s.remark from Student_score s,Student t,Course c,CourseType u,Term e,empVo o where\n" +
                " s.stuid = t.Studid and s.courseId =c.courseID and c.courseTypeID=u.courseTypeID and s.termid=e.termID and s.Empid=o.empId");
    }

    @Override
    public List<StudentReplyScoreVo> listStudentReplyScore() {
        return sqlQuery("select replyId,u.stuname,className,projectId,score1,score2,score3,score4,score5,score6,score7,s.Remark from studentReplyScore s,Student u,StudentClass l where s.empId=u.Studid and u.clazz =l.classid");
    }

    @Override
    public List<empVo> listTeacherName() {
        return sqlQuery("select empId,empName from empVo where postId = 3");//讲师
    }

    @Override
    public List<empVo> listClassTeacherName() {
        return sqlQuery("select empId,empName from empVo where postId = 5"); //班主任
    }

    @Override
    public List listClassType() {
        return sqlQuery("select calssTypeId,classTypeName from classType");//班级类别
    }

    @Override
    public List listDept() {
        return sqlQuery("select deptID,deptName from Dept");//系列
    }

    @Override
    public List listmajorId() {
        return sqlQuery("select majorID,majorName from Major");//专业
    }

    @Override
    public void studentClassAdd(StudentClassVo vo) {
        save(vo);
    }

    @Override
    public List<StudentClassVo> selectStudentClass() {
        return sqlQuery("select  classid,classno,className,e. empName as bzt,ee.empName as js,classTypeName,falled,deptName,MajorName from StudentClass s  left join  empVo e on  s.classTeacher = e.empid left join empVo ee on s.teacher = ee.empId \n" +
                "left join classType c on s.classType = c.calssTypeId left join Dept d on s.deptId =d.deptId left join Major m on s.majorId = m.majorID");
    }

    @Override
    public List<StudentClassVo> seleceStudentClassID(int id) {
        return sqlQuery("select  classid,classno,className,e. empName as bzt,ee.empName as js,classTypeName,falled,deptName,MajorName from StudentClass s  left join  empVo e on  s.classTeacher = e.empid left join empVo ee on s.teacher = ee.empId \n" +
                "left join classType c on s.classType = c.calssTypeId left join Dept d on s.deptId =d.deptId left join Major m on s.majorId = m.majorID where  classid ="+id);
    }

    @Override
    public void updateStudentClassData(StudentClassVo vo) {
         update(vo);
    }

    @Override
    public void deleStudentClassTeacherDatas(StudentClassVo vo) {
        delete(vo);
    }
}
