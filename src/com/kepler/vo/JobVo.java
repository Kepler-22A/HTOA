package com.kepler.vo;

import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "job")
public class JobVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobId;
    private String EmpId;
    private String companyName;//公司名称
    private String degree;//岗位
    private DateTime startDate;//入职时间
    private DateTime endDate;//离职时间
    private String reason;//离职原因
    private String Remark;//说明


    @Override
    public String toString() {
        return "JobVo{" +
                "jobId=" + jobId +
                ", EmpId='" + EmpId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", degree='" + degree + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", reason='" + reason + '\'' +
                ", Remark='" + Remark + '\'' +
                '}';
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getEmpId() {
        return EmpId;
    }

    public void setEmpId(String empId) {
        EmpId = empId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }


    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
