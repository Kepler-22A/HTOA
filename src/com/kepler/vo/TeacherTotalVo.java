package com.kepler.vo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ASUS on 2019/12/3.
 */
/*
* 考评总表
* */
@Entity
@Table(name = "teacherTotal")
public class TeacherTotalVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teachertotalId;  //主键，标识列，自动生成
    private int classTeacher;    //班主任或老师员工表主键
    private int classid;           //关联班级表主键
    private int studentId;          //关联学生id
    private Date optime;            //考评时间
    private String sugges;      //问题与建议
    private int evaluationType;  //1:授课老师2:班主任

    @Override
    public String toString() {
        return "TeacherTotal{" +
                "teachertotalId=" + teachertotalId +
                ", classTeacher=" + classTeacher +
                ", classid=" + classid +
                ", studentId=" + studentId +
                ", optime=" + optime +
                ", sugges='" + sugges + '\'' +
                ", evaluationType=" + evaluationType +
                '}';
    }

    public int getTeachertotalId() {
        return teachertotalId;
    }

    public void setTeachertotalId(int teachertotalId) {
        this.teachertotalId = teachertotalId;
    }

    public int getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(int classTeacher) {
        this.classTeacher = classTeacher;
    }

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Date getOptime() {
        return optime;
    }

    public void setOptime(Date optime) {
        this.optime = optime;
    }

    public String getSugges() {
        return sugges;
    }

    public void setSugges(String sugges) {
        this.sugges = sugges;
    }

    public int getEvaluationType() {
        return evaluationType;
    }

    public void setEvaluationType(int evaluationType) {
        this.evaluationType = evaluationType;
    }
}
