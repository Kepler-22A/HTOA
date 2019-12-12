package com.kepler.vo;

import javax.persistence.*;

@Entity
@Table(name = "checkScoer")
public class checkScoerVo {//考评各项成绩表！！
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int checkScoerId ;
    private int empId ;//
    private int myComment ;//自评分
    private int studentComment ; //学生评分
    private int leadComment ;//领导评分
    private int total ;//总得分

    @Override
    public String toString() {
        return "checkScoerVo{" +
                "checkScoerId=" + checkScoerId +
                ", empId=" + empId +
                ", myComment=" + myComment +
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

    public int getMyComment() {
        return myComment;
    }

    public void setMyComment(int myComment) {
        this.myComment = myComment;
    }

    public int getStudentComment() {
        return studentComment;
    }

    public void setStudentComment(int studentComment) {
        this.studentComment = studentComment;
    }

    public int getLeadComment() {
        return leadComment;
    }

    public void setLeadComment(int leadComment) {
        this.leadComment = leadComment;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
