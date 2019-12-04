package com.kepler.vo;

import javax.persistence.*;
import java.util.Date;

@Table(name = "Holiday")
@Entity
public class HolidayVo { //请假申请表
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int holidayID;
	private int empID; //关联Emp主键(请假人)
	private float holidayDay; //假期天数
	private Date startTime; //开始时间
	private Date endTime; //结束时间
	private String title; //标题
	private int status; //状态 1:审批中 2：已完成 3：不批准
	private String remark; //内容

	public int getHolidayID() {
		return holidayID;
	}

	public void setHolidayID(int holidayID) {
		this.holidayID = holidayID;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
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
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "HolidayVo{" +
				"holidayID=" + holidayID +
				", empID=" + empID +
				", holidayDay=" + holidayDay +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", title='" + title + '\'' +
				", status=" + status +
				", remark='" + remark + '\'' +
				'}';
	}
}
