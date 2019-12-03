package com.kepler.vo;

import javax.persistence.*;

@Entity
@Table(name = "HolidayStudent")//学生请假表
public class HolidayStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int holidayid;//主键，标识列，自动生成
    private int StudentId;//关联学生表主键(请假人)
    private int holidayDay;//假期天数
    private int startTime;//开始时间
    private int endTime;//结束时间
    private int Title;//标题
    private int Remark;//内容
    private int Status;//审批中，审批通过，审批未通过

    public int getHolidayid() {
        return holidayid;
    }

    public void setHolidayid(int holidayid) {
        this.holidayid = holidayid;
    }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }

    public int getHolidayDay() {
        return holidayDay;
    }

    public void setHolidayDay(int holidayDay) {
        this.holidayDay = holidayDay;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getTitle() {
        return Title;
    }

    public void setTitle(int title) {
        Title = title;
    }

    public int getRemark() {
        return Remark;
    }

    public void setRemark(int remark) {
        Remark = remark;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "HolidayStudent{" +
                "holidayid=" + holidayid +
                ", StudentId=" + StudentId +
                ", holidayDay=" + holidayDay +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", Title=" + Title +
                ", Remark=" + Remark +
                ", Status=" + Status +
                '}';
    }
}
