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
    @Override
    public List listClasstree() {
        return sqlQuery("select * from StudentClass");
    }

    @Override
    public List selectClass(String id) {
        return sqlQuery("select classid,className from StudentClass where falled ="+id);
    }

    @Override
    public List Customss(String cuntom) {
        return sqlQuery("select Studid,className,stuname,sex,phone from Student s,StudentClass t where s.clazz = t.classno and className = '"+cuntom+"'");
    }

    @Override
    public List selectNOClassStudentData() {
        return sqlQuery("select distinct Studid,stuname,cardid,sex,phone,middleschool,clazz,introduretech,classTypeName from Student t left join StudentClass s on t.clazz = s.classid left join classType\n" +
                "e on s.classid = e.calssTypeId");
    }

    @Override
    public void addStudentReplyScore(StudentReplyScoreVo vo) {
        save(vo);
    }

    @Override
    public List selectStudentReplyScore(int id) {
        return sqlQuery("select replyId,stuname,className,projectName,score1,Score2,Score3,Score4,Score5,Score6,Score7,e.Remark,empName from StudentReplyScore e left join Student s on s.Studid = e.StudentId left join StudentClass l on s.clazz = l.classid \n" +
                "left join Project p on e.projectId = p.projectId left join empVo o on o.empId = e.empId where Studid = "+id);
    }

    @Override
    public void updateStudentReplyScore(StudentReplyScoreVo vo) {
        update(vo);
    }

    @Override
    public void delectStudentReplyScore(StudentReplyScoreVo vo) {
        delete(vo);
    }

    @Override
    public List selectcourseId() {
        return sqlQuery("select courseID,courseName from Course");
    }

    @Override
    public void addStudent_score(Student_scoreVo vo) {
        save(vo);
    }

    @Override
    public List selestStudent_score(int id) {
        return sqlQuery("select scoreId,stuname,e.score,Rescore,courseName,testType,termid,scoreTime,v.empName,v.empId from \n" +
                "Student_score e left join Student s on  e.stuid = s.Studid left join Course o on e.courseId = o.courseID left join empVo v on e.Empid = v.empid where Studid ="+id);
    }

    @Override
    public void updateStudent_score(Student_scoreVo vo) {
        update(vo);
    }

    @Override
    public void deleteStudent_score(Student_scoreVo vo) {
        delete(vo);
    }

    @Override
    public List studentClassAjax() {
        return sqlQuery("select classid,className from StudentClass");
    }
}
