package com.kepler.vo;

import javax.persistence.*;
import java.util.Date;

@Table(name = "Trial")
@Entity
public class TrialVo { //试讲与培训计划表
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int trialID;
	private Date date; //日期
	private String time; //时间(星期一---星期日)
	private int courseID; //关联课程/章节id
	private int type; //授课类型(试讲/培训)      试讲1 培训2
	private int empID; //员工id关联员工表(授课老师)
	private String remark; //备注(针对课程重要内容)

	public int getTrialID() {
		return trialID;
	}

	public void setTrialID(int trialID) {
		this.trialID = trialID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "TrialVo{" +
				"trialID=" + trialID +
				", date=" + date +
				", time='" + time + '\'' +
				", courseID=" + courseID +
				", type=" + type +
				", empID=" + empID +
				", remark='" + remark + '\'' +
				'}';
	}
}
