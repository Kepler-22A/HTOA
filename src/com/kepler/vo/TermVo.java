package com.kepler.vo;

import javax.persistence.*;

@Table(name = "Term")
@Entity
public class TermVo { //学期表
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int termID;
	private String termName; //学期名称
	private String remark; //说明

	public int getTermID() {
		return termID;
	}

	public void setTermID(int termID) {
		this.termID = termID;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "TermVo{" +
				"termID=" + termID +
				", termName='" + termName + '\'' +
				", remark='" + remark + '\'' +
				'}';
	}
}
