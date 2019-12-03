package com.kepler.vo;

import javax.persistence.*;

@Table(name = "classTeacher")
@Entity
public class ClassTeacherVo {//班级老师表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classTeacherId;
    private int empId;//员工id
    private int classId;//班级id
    private int teacherType;
    private int courseId;
    private int status;

    public int getClassTeacherId() {
        return classTeacherId;
    }

    public void setClassTeacherId(int classTeacherId) {
        this.classTeacherId = classTeacherId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getTeacherType() {
        return teacherType;
    }

    public void setTeacherType(int teacherType) {
        this.teacherType = teacherType;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ClassTeacherVo{" +
                "classTeacherId=" + classTeacherId +
                ", empId=" + empId +
                ", classId=" + classId +
                ", teacherType=" + teacherType +
                ", courseId=" + courseId +
                ", status=" + status +
                '}';
    }
}
