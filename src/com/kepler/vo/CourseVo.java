package com.kepler.vo;

import javax.persistence.*;

@Table(name = "Course")
@Entity
public class CourseVo { //课程表
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseID;
	private String courseName; //课程名称
	private String isObligatory; //是否必修
	private int courseTypeID; //课程类别id外键
	private String remark; //说明

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getIsObligatory() {
		return isObligatory;
	}

	public void setIsObligatory(String isObligatory) {
		this.isObligatory = isObligatory;
	}

	public int getCourseTypeID() {
		return courseTypeID;
	}

	public void setCourseTypeID(int courseTypeID) {
		this.courseTypeID = courseTypeID;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "CourseVo{" +
				"courseID=" + courseID +
				", courseName='" + courseName + '\'' +
				", isObligatory='" + isObligatory + '\'' +
				", courseTypeID=" + courseTypeID +
				", remark='" + remark + '\'' +
				'}';
	}
}
