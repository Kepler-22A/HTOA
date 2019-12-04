package com.kepler.vo;

import javax.persistence.*;

@Table(name = "EmpAssessmentTotal")
@Entity
public class EmpAssessmentTotalVo { //员工考核总表
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empAssTotalID;
	private int empID; //关联员工表
	private int totalScores; //总得分
	private String remark; //备注

	public int getEmpAssTotalID() {
		return empAssTotalID;
	}

	public void setEmpAssTotalID(int empAssTotalID) {
		this.empAssTotalID = empAssTotalID;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public int getTotalScores() {
		return totalScores;
	}

	public void setTotalScores(int totalScores) {
		this.totalScores = totalScores;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "EmpAssessmentTotalVo{" +
				"empAssTotalID=" + empAssTotalID +
				", empID=" + empID +
				", totalScores=" + totalScores +
				", remark='" + remark + '\'' +
				'}';
	}
}
