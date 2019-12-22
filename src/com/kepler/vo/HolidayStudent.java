package com.kepler.vo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "HolidayStudent")//学生请假表
public class HolidayStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int holidayid;//主键，标识列，自动生成
    private int StudentId;//关联学生表主键(请假人)
    private float holidayDay;//假期天数
    private Date startTime;//开始时间
    private Date endTime;//结束时间
    private String Title;//标题
    private String Remark;//内容
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

    public float getHolidayDay() {
        return holidayDay;
    }

    public void setHolidayDay(float holidayDay) {
        this.holidayDay = holidayDay;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
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
                ", Title='" + Title + '\'' +
                ", Remark='" + Remark + '\'' +
                ", Status=" + Status +
                '}';
    }
}
