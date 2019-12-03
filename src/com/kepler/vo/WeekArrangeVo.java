package com.kepler.vo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ASUS on 2019/12/3.
 */
/*
* 值班管理表
* */
@Entity
@Table(name = "weekArrange")
public class WeekArrangeVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int weekArrangeid;  //主键，标识列，自动生成
    private String weekArrangeName;  //排班名称
    private String ranges;           //值班要求

    @Override
    public String toString() {
        return "WeekArrangeVo{" +
                "weekArrangeid=" + weekArrangeid +
                ", weekArrangeName='" + weekArrangeName + '\'' +
                ", ranges='" + ranges + '\'' +
                ", week='" + week + '\'' +
                ", empId=" + empId +
                ", orderId=" + orderId +
                ", duty=" + duty +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", remark='" + remark + '\'' +
                '}';
    }

    public int getWeekArrangeid() {
        return weekArrangeid;
    }

    public void setWeekArrangeid(int weekArrangeid) {
        this.weekArrangeid = weekArrangeid;
    }

    public String getWeekArrangeName() {
        return weekArrangeName;
    }

    public void setWeekArrangeName(String weekArrangeName) {
        this.weekArrangeName = weekArrangeName;
    }

    public String getRanges() {
        return ranges;
    }

    public void setRanges(String ranges) {
        this.ranges = ranges;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDuty() {
        return duty;
    }

    public void setDuty(int duty) {
        this.duty = duty;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private String week;             //一个星期中的那一天
    private int empId;               //关联Emp主键
    private int orderId;             //排序
    private int duty;                //总值班 1：是 2：否
    private Date startTime;           //开始时间
    private Date endTime;              //结束时间
    private String remark;              //说明


}
