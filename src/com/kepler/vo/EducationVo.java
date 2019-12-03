package com.kepler.vo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "education")
//员工的学习经历，实现新增，修改，删除，查询功能；education表
public class EducationVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int collegeid;//主键
    private  String empId;
    private String collegeName;//学校名称
    private String degree;//学校名称
    private Date startDate;//入校时间
    private Date endDate;//毕业时间
    private String Remark;//奖惩情况


    @Override
    public String toString() {
        return "EducationVo{" +
                "collegeid=" + collegeid +
                ", empId='" + empId + '\'' +
                ", collegeName='" + collegeName + '\'' +
                ", degree='" + degree + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", Remark='" + Remark + '\'' +
                '}';
    }

    public int getCollegeid() {
        return collegeid;
    }

    public void setCollegeid(int collegeid) {
        this.collegeid = collegeid;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
