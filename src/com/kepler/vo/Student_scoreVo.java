package com.kepler.vo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "8")//学生成绩表
public class Student_scoreVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scoreId;//成绩主键id
    private int stuid;//学生表外键
    private int score;//学生成绩
    private int Rescore;//补考成绩
    private int courseId;//关联课程id
    private int testType;//1:笔试2:机试3:模拟面试
    private int termid;//关联学期id
    private Date scoreTime;//考试时间
    private int Empid;//录入人员,获取session值
    private String remark;//备注

    @Override
    public String toString() {
        return "Student_scoreVo{" +
                "scoreId=" + scoreId +
                ", stuid=" + stuid +
                ", score=" + score +
                ", Rescore=" + Rescore +
                ", courseId=" + courseId +
                ", testType=" + testType +
                ", termid=" + termid +
                ", scoreTime=" + scoreTime +
                ", Empid=" + Empid +
                ", remark='" + remark + '\'' +
                '}';
    }

    public int getScoreId() {
        return scoreId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    public int getStuid() {
        return stuid;
    }

    public void setStuid(int stuid) {
        this.stuid = stuid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRescore() {
        return Rescore;
    }

    public void setRescore(int rescore) {
        Rescore = rescore;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTestType() {
        return testType;
    }

    public void setTestType(int testType) {
        this.testType = testType;
    }

    public int getTermid() {
        return termid;
    }

    public void setTermid(int termid) {
        this.termid = termid;
    }

    public Date getScoreTime() {
        return scoreTime;
    }

    public void setScoreTime(Date scoreTime) {
        this.scoreTime = scoreTime;
    }

    public int getEmpid() {
        return Empid;
    }

    public void setEmpid(int empid) {
        Empid = empid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
