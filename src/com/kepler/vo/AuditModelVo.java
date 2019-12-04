package com.kepler.vo;

import javax.persistence.*;

@Table(name = "AuditModel")
@Entity
public class AuditModelVo { //考核指标表
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int auditModelID;
	private String auditName; //考核内容
	private int scores; //考核分数
	private int depID; //关联部门表
	private String remark; //说明

	public int getAuditModelID() {
		return auditModelID;
	}

	public void setAuditModelID(int auditModelID) {
		this.auditModelID = auditModelID;
	}

	public String getAuditName() {
		return auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public int getScores() {
		return scores;
	}

	public void setScores(int scores) {
		this.scores = scores;
	}

	public int getDepID() {
		return depID;
	}

	public void setDepID(int depID) {
		this.depID = depID;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "AuditModelVo{" +
				"auditModelID=" + auditModelID +
				", auditName='" + auditName + '\'' +
				", scores=" + scores +
				", depID=" + depID +
				", remark='" + remark + '\'' +
				'}';
	}
}
