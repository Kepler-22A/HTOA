package com.kepler.vo;

import javax.persistence.*;

@Table(name = "EmpAssessment")
@Entity
public class EmpAssessmentVo { //员工考核各项得分详情表
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empAssessID;
	private int auditModelID; //考核指标关联考核指标表
	private float scores; //得分
	private int empAssTotalID; //关联考核总表

	public int getEmpAssessID() {
		return empAssessID;
	}

	public void setEmpAssessID(int empAssessID) {
		this.empAssessID = empAssessID;
	}

	public int getAuditModelID() {
		return auditModelID;
	}

	public void setAuditModelID(int auditModelID) {
		this.auditModelID = auditModelID;
	}

	public float getScores() {
		return scores;
	}

	public void setScores(float scores) {
		this.scores = scores;
	}

	public int getEmpAssTotalID() {
		return empAssTotalID;
	}

	public void setEmpAssTotalID(int empAssTotalID) {
		this.empAssTotalID = empAssTotalID;
	}

	@Override
	public String toString() {
		return "EmpAssessmentVo{" +
				"empAssessID=" + empAssessID +
				", auditModelID=" + auditModelID +
				", scores=" + scores +
				", empAssTotalID=" + empAssTotalID +
				'}';
	}
}
