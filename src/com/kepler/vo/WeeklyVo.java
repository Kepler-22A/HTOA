package com.kepler.vo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="weekly" )
//周报管理表
public class WeeklyVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int worklogid;
    private  String empId;
    private Date Workday;//填写日期
    private String weekCur;//本周情况描述
    private String studentQuestion;//问题学生情况反馈
    private String Idea;//意见
    private String weekNext;//下周工作计划


    @Override
    public String toString() {
        return "WeeklyVo{" +
                "worklogid=" + worklogid +
                ", empId='" + empId + '\'' +
                ", Workday=" + Workday +
                ", weekCur='" + weekCur + '\'' +
                ", studentQuestion='" + studentQuestion + '\'' +
                ", Idea='" + Idea + '\'' +
                ", weekNext='" + weekNext + '\'' +
                '}';
    }

    public int getWorklogid() {
        return worklogid;
    }

    public void setWorklogid(int worklogid) {
        this.worklogid = worklogid;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public Date getWorkday() {
        return Workday;
    }

    public void setWorkday(Date workday) {
        Workday = workday;
    }

    public String getWeekCur() {
        return weekCur;
    }

    public void setWeekCur(String weekCur) {
        this.weekCur = weekCur;
    }

    public String getStudentQuestion() {
        return studentQuestion;
    }

    public void setStudentQuestion(String studentQuestion) {
        this.studentQuestion = studentQuestion;
    }

    public String getIdea() {
        return Idea;
    }

    public void setIdea(String idea) {
        Idea = idea;
    }

    public String getWeekNext() {
        return weekNext;
    }

    public void setWeekNext(String weekNext) {
        this.weekNext = weekNext;
    }
}
