package com.kepler.vo;

import javax.persistence.*;

@Table(name = "AuditType")
@Entity
public class AuditTypeVo { //考核指标类别
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int auditTypeID;
	private String auditTypeName; //1:教研部，2：学工科，3：教师考评，4：班主任考评，5：招生就业，6.后勤，7：成人教育
	private int depID; //关联部门表

	public int getAuditTypeID() {
		return auditTypeID;
	}

	public void setAuditTypeID(int auditTypeID) {
		this.auditTypeID = auditTypeID;
	}

	public String getAuditTypeName() {
		return auditTypeName;
	}

	public void setAuditTypeName(String auditTypeName) {
		this.auditTypeName = auditTypeName;
	}

	public int getDepID() {
		return depID;
	}

	public void setDepID(int depID) {
		this.depID = depID;
	}

	@Override
	public String toString() {
		return "AuditTypeVo{" +
				"auditTypeID=" + auditTypeID +
				", auditTypeName='" + auditTypeName + '\'' +
				", depID=" + depID +
				'}';
	}
}
