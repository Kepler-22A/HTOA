package com.kepler.vo;

import javax.persistence.*;

@Table(name = "Major")
@Entity
public class MajorVo { //专业表
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int majorID;
	private String majorName; //专业名称
	private int deptID; //系id
	private String remark; //说明

	public int getMajorID() {
		return majorID;
	}

	public void setMajorID(int majorID) {
		this.majorID = majorID;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public int getDeptID() {
		return deptID;
	}

	public void setDeptID(int deptID) {
		this.deptID = deptID;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "MajorVo{" +
				"majorID=" + majorID +
				", majorName='" + majorName + '\'' +
				", deptID=" + deptID +
				", remark='" + remark + '\'' +
				'}';
	}
}
