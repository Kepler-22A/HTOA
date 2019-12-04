package com.kepler.vo;

import javax.persistence.*;

@Table(name = "CourseType")
@Entity
public class CourseTypeVo { //课程类别表
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseTypeID;
	private String courseTypeName; //类别名称(公共课，专业课)
	private String remark; //说明

	public int getCourseTypeID() {
		return courseTypeID;
	}

	public void setCourseTypeID(int courseTypeID) {
		this.courseTypeID = courseTypeID;
	}

	public String getCourseTypeName() {
		return courseTypeName;
	}

	public void setCourseTypeName(String courseTypeName) {
		this.courseTypeName = courseTypeName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "CourseTypeVo{" +
				"courseTypeID=" + courseTypeID +
				", courseTypeName='" + courseTypeName + '\'' +
				", remark='" + remark + '\'' +
				'}';
	}
}
