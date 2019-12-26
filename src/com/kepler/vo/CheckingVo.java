package com.kepler.vo;

import javax.persistence.*;
import java.util.Date;

//考勤表
@Entity
@Table(name = "Checking")
public class CheckingVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CheckingID;//编号
    private int empID;//员工id
    private Date NOdate;//未打卡时间
    private String causeReamk;//原因说明
    private int superiorempID;//上级员工id
    private Date auditdate;//审核时间
    private String reamk;//说明
    private int state;//状态  1 通过  2 没有通过  3 还没审核  4 正在审核

    @Override
    public String toString() {
        return "Checking{" +
                "CheckingID=" + CheckingID +
                ", empID=" + empID +
                ", NOdate=" + NOdate +
                ", causeReamk='" + causeReamk + '\'' +
                ", superiorempID=" + superiorempID +
                ", auditdate=" + auditdate +
                ", reamk='" + reamk + '\'' +
                ", state=" + state +
                '}';
    }

    public int getCheckingID() {
        return CheckingID;
    }

    public void setCheckingID(int checkingID) {
        CheckingID = checkingID;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public Date getNOdate() {
        return NOdate;
    }

    public void setNOdate(Date NOdate) {
        this.NOdate = NOdate;
    }

    public String getCauseReamk() {
        return causeReamk;
    }

    public void setCauseReamk(String causeReamk) {
        this.causeReamk = causeReamk;
    }

    public int getSuperiorempID() {
        return superiorempID;
    }

    public void setSuperiorempID(int superiorempID) {
        this.superiorempID = superiorempID;
    }

    public Date getAuditdate() {
        return auditdate;
    }

    public void setAuditdate(Date auditdate) {
        this.auditdate = auditdate;
    }

    public String getReamk() {
        return reamk;
    }

    public void setReamk(String reamk) {
        this.reamk = reamk;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
