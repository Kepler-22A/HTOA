package com.kepler.service;

import com.kepler.vo.*;

import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2019/12/4.
 */
/*
*
* */
public interface StudentService {
    //宿舍管理
    public List<StudentHuorVo>  listhour();
    //添加宿舍信息
    public void addhour(StudentHuorVo studentHuorVo);
    //修改宿舍信息
    public void updatehour(StudentHuorVo studentHuorVo);
    //根据id查找宿舍信息
    public List listbyId(Class className, int id);
    //删除宿舍资料
    public void deletehour(int id);
    //楼栋管理
    public  List<StudentFloorVo> listfloor();
   //添加楼栋
    public void addfloor(StudentFloorVo studentFloorVo);
    //删除楼栋
    public void deletefloor(int id);
    //查设备维修数据
    public List<EquipmentRepairVo> listEquipmentData();
    //添加设备维修记录
    public void AddEuipment(EquipmentRepairVo equipmentRepairVo);
    //根据id查找设备维修信息
    public List listEquipmentbyId(Class className, int id);
    //修改设备信息
    public void updateEquiment(EquipmentRepairVo equipmentRepairVo);
    //删除设备维修记录
    public void delEquipment(int id);
    //查询学生资料
    public List<StudentVo> liststudentdata();
    //添加学生
    public void studentADD(StudentVo vo);
    //根据学生id查询出数据
    public List<StudentVo> selectStudentIdData(int id);
    //修改学生
    public void updateStudentData(StudentVo vo);
    //删除学生
    public void deleStudentDatas(StudentVo vo);
    //查询学生成绩
    public List<Student_scoreVo> listStudentScore();
    //查询出学生答辩成绩
    public List<StudentReplyScoreVo>listStudentReplyScore();
    //查询出老师的名字
    public List listTeacherName();
    //查询出班主任的名字
    public List listClassTeacherName();
    //查询出班级类别
    public List listClassType();
    //查询出系列
    public List listDept();
    //查询专业名称
    public List listmajorId();
    //保存班级管理的数据
    public void studentClassAdd(StudentClassVo vo);
    //查询出班级信息
    public List<StudentClassVo> selectStudentClass();
    //根据id查询出班级管理信息
    public List<StudentClassVo> seleceStudentClassID(int id);
    //根据班级管理id修改出班级管理
    public void updateStudentClassData(StudentClassVo vo);
    //删除学生
    public void deleStudentClassTeacherDatas(StudentClassVo vo);
    //查询出分配班级的信息
    public List listClasstree();
    //根据班级分配时间来查询这一年有的班级
    public List selectClass(String id);
    //根据点击班级分配然后查询数据
    public List Customss(String cuntom);
    //查询出没有分班的学生
   public List selectNOClassStudentData();
   //增加学生答辩成绩
    public void addStudentReplyScore(StudentReplyScoreVo vo);
    //根据学生id查询出答辩成绩
    public List selectStudentReplyScore(int id);
    //根据学生id修改答辩成绩
    public void updateStudentReplyScore(StudentReplyScoreVo vo);
    //根据学生删除答辩成绩
    public void delectStudentReplyScore(StudentReplyScoreVo vo);
    //查询出课程名称
    public List selectcourseId();
    //增加学生考试成绩
   public void addStudent_score(Student_scoreVo vo);
   //查询出学生考试成绩
   public List selestStudent_score(int id);
   //修改学生考试成绩
    public void updateStudent_score(Student_scoreVo vo);
    //删除学生考试成绩
    public void deleteStudent_score(Student_scoreVo vo);
    //查询出班级
    public List studentClassAjax();
}
