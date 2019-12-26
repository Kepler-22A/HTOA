package com.kepler.vo;

import javax.persistence.*;

@Entity
@Table(name = "checkScoer")
public class checkScoerVo {//考评各项成绩表！！
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int checkScoerId ;
    private int empId ;//
    private int templateId;//模板Id
    private float studentComment ; //学生评分
    private float leadComment ;//领导评分
    private float total ;//总得分

    @Override
    public String toString() {
        return "checkScoerVo{" +
                "checkScoerId=" + checkScoerId +
                ", empId=" + empId +
                ", templateId=" + templateId +
                ", studentComment=" + studentComment +
                ", leadComment=" + leadComment +
                ", total=" + total +
                '}';
    }

    public int getCheckScoerId() {
        return checkScoerId;
    }

    public void setCheckScoerId(int checkScoerId) {
        this.checkScoerId = checkScoerId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public float getStudentComment() {
        return studentComment;
    }

    public void setStudentComment(float studentComment) {
        this.studentComment = studentComment;
    }

    public float getLeadComment() {
        return leadComment;
    }

    public void setLeadComment(float leadComment) {
        this.leadComment = leadComment;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
