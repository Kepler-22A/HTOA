package com.kepler.vo;

import javax.persistence.*;
import java.util.Date;

@Table(name = "AuditLog")
@Entity
public class AuditLogVo { //根据巡查结果，录入巡查数据
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int auditLogID;
	private int auditModelID; //外键，考核指标id
	private int empID; //员工id
	private int scores; //考核分数,根据考核指标规定的分数进行扣分负数或者加分为正数
	private Date auditDate; //考核时间
	private String remark; //说明
	private String auditPerson; //录入人员，从session取值
	private String image; //图片

	public int getAuditLogID() {
		return auditLogID;
	}

	public void setAuditLogID(int auditLogID) {
		this.auditLogID = auditLogID;
	}

	public int getAuditModelID() {
		return auditModelID;
	}

	public void setAuditModelID(int auditModelID) {
		this.auditModelID = auditModelID;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public int getScores() {
		return scores;
	}

	public void setScores(int scores) {
		this.scores = scores;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAuditPerson() {
		return auditPerson;
	}

	public void setAuditPerson(String auditPerson) {
		this.auditPerson = auditPerson;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "AuditLogVo{" +
				"auditLogID=" + auditLogID +
				", auditModelID=" + auditModelID +
				", empID=" + empID +
				", scores=" + scores +
				", auditDate=" + auditDate +
				", remark='" + remark + '\'' +
				", auditPerson='" + auditPerson + '\'' +
				", image='" + image + '\'' +
				'}';
	}
}
