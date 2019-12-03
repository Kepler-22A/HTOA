package com.kepler.vo;

import javax.persistence.*;

@Table(name = "semester")
@Entity
public class SemesterVo {//用于对各个学期的的设置
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int semeId;
    private String semesterName;//学期名称
    private String remark;//备注

    public int getSemeId() {
        return semeId;
    }

    public void setSemeId(int semeId) {
        this.semeId = semeId;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SemesterVo{" +
                "semeId=" + semeId +
                ", semesterName='" + semesterName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
