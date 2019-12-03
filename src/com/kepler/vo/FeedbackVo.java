package com.kepler.vo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ASUS on 2019/12/3.
 */
/*
*    学生反馈问题表
* */
@Entity
@Table(name = "feedback")
public class FeedbackVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feedbackId;  //主键，标识列，自动生成
    private int feedBackType; //1：员工，2学生
    private int empId;
    private String empname;         //获取session中学生班级及姓名或员工姓名
    private Date feedbackTime;      //反馈时间，获取系统时间
    private String remark;             //建议
    private int status;             //状态：1 未处理 2 已处理
    private int depId;              //部门
    private String opinion;         //审批意见
    private int userId;             //审批人

    @Override
    public String toString() {
        return "FeedbackVo{" +
                "feedbackId=" + feedbackId +
                ", feedBackType=" + feedBackType +
                ", empId=" + empId +
                ", empname='" + empname + '\'' +
                ", feedbackTime=" + feedbackTime +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", depId=" + depId +
                ", opinion='" + opinion + '\'' +
                ", userId=" + userId +
                '}';
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getFeedBackType() {
        return feedBackType;
    }

    public void setFeedBackType(int feedBackType) {
        this.feedBackType = feedBackType;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public Date getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Date feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
