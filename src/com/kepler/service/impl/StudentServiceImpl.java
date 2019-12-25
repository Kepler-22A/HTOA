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
        return sqlQuery("select studid,stuname,stuno,sex,cardid,phone,className,huor,stat,collar,grants,computer,parents,qkMoney\n" +
                "from Student s left join StudentClass c on s.clazz = c.classid");
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
        return sqlQuery("select scoreId,t.stuname,s.score,Rescore,courseName,courseTypeName,termName,scoreTime,o.empName,s.remark from Student_score s left join \n" +
                "Student t on s.stuid = t.Studid left join Course c on s.courseId =c.courseID left join CourseType u on c.courseTypeID=u.courseTypeID left join Term e on s.termid=e.termID left join empVo o on s.Empid=o.empId");
    }

    @Override
    public List<StudentReplyScoreVo> listStudentReplyScore() {
        return sqlQuery("\n" +
                "select replyId,score1,score2,score3,score4,score5,score6,score7,s.Remark,u.stuname,className,projectName from studentReplyScore s\n" +
                "left join Student u on s.StudentId=u.Studid left join StudentClass l on u.clazz =l.classid left join Project j on s.projectId = j.projectId");
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

    @Override
    public List selectStudent_score(int id) {
        return sqlQuery("select scoreId from Student_score where stuid =" + id);
    }

    @Override
    public void deleteStudent_score_id(Student_scoreVo vo) {
        delete(vo);
    }

    @Override
    public List<CourseTypeVo> selectClassType2() {
        return sqlQuery("select * from CourseType");
    }

    @Override
    public void AddClassType(CourseTypeVo vo) {
        save(vo);
    }

    @Override
    public List<CourseTypeVo> selectClassType3(int id) {
        return sqlQuery("select * from CourseType where courseTypeID ="+id);
    }

    @Override
    public void updateClassType5(CourseTypeVo vo) {
        update(vo);
    }

    @Override
    public void deleteClassType4(CourseTypeVo vo) {
        delete(vo);
    }

    @Override
    public List<CourseVo> selectCoures() {
        return sqlQuery("select c.courseID,c.courseName,c.isObligatory,c.remark,e.courseTypeName from Course c left join CourseType e on c.courseTypeID = e.courseTypeID");
    }

    @Override
    public List ClassType() {
        return sqlQuery("select courseTypeID,courseTypeName from CourseType");
    }

    @Override
    public void AddCoures(CourseVo vo) {
        save(vo);
    }

    @Override
    public List<CourseVo> selectCourer5(int id) {
        return sqlQuery("select * from Course where courseID ="+id);
    }

    @Override
    public void updateCourseVo(CourseVo vo) {
        update(vo);
    }

    @Override
    public void deleteCourseVo(CourseVo vo) {
        delete(vo);
    }

    @Override
    public List<TrialVo> selectTrial() {
        return sqlQuery("\n" +
                "select t.trialID,t.date,t.time,t.remark,e.empName,r.courseName,t.type from Trial t left join empVo e on t.empID =e.empId\n" +
                "left join Course r on t.courseID = r.courseID");
    }

    @Override
    public List<CourseVo> selectCouresName() {
        return sqlQuery("select courseID,courseName from Course");
    }

    @Override
    public void addTrial(TrialVo vo) {
        save(vo);
    }

    @Override
    public List selectTrialID(int id) {
        return sqlQuery("\tselect t.trialID,t.date,t.time,t.remark,e.empName,r.courseName,t.type from Trial t left join empVo e on t.empID =e.empId\n" +
                "  left join Course r on t.courseID = r.courseID where trialID ="+id);
    }

    @Override
    public void updateTrialID(TrialVo vo) {
        update(vo);
    }

    @Override
    public void delectTrialID(TrialVo vo) {
        delete(vo);
    }


}
