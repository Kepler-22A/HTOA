package com.kepler.vo;

import javax.persistence.*;

@Entity
@Table(name = "checkPeople")
public class checkPeopleVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  checkPeopleId ;
    private int templateId ; //关联模板
    private int empId ;//考评人
    private String leadState ; //状态  1、待考评  2.已考评

    @Override
    public String toString() {
        return "checkPeopleVo{" +
                "checkPeopleId=" + checkPeopleId +
                ", templateId=" + templateId +
                ", empId=" + empId +
                ", leadState='" + leadState + '\'' +
                '}';
    }

    public int getCheckPeopleId() {
        return checkPeopleId;
    }

    public void setCheckPeopleId(int checkPeopleId) {
        this.checkPeopleId = checkPeopleId;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getLeadState() {
        return leadState;
    }

    public void setLeadState(String leadState) {
        this.leadState = leadState;
    }
}
