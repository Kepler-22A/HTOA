package com.kepler.vo;

import javax.persistence.*;

@Table(name = "Dept")
@Entity
public class DeptVo { //系管理表
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deptID;
	private String deptName; //系名称
	private String remark; //说明

	public int getDeptID() {
		return deptID;
	}

	public void setDeptID(int deptID) {
		this.deptID = deptID;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "DeptVo{" +
				"deptID=" + deptID +
				", deptName='" + deptName + '\'' +
				", remark='" + remark + '\'' +
				'}';
	}
}




