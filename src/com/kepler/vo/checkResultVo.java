package com.kepler.vo;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;

@Entity
@Table(name = "checkResult")
public class checkResultVo {//考评结果表！！
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int checkResultId ;
    private int checkScoerId;//成绩Id
    private String remark ;//评语
    private String grade;//等级

    @Override
    public String toString() {
        return "checkResultVo{" +
                "checkResultId=" + checkResultId +
                ", checkScoerId=" + checkScoerId +
                ", remark='" + remark + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }

    public int getCheckResultId() {
        return checkResultId;
    }

    public void setCheckResultId(int checkResultId) {
        this.checkResultId = checkResultId;
    }

    public int getCheckScoerId() {
        return checkScoerId;
    }

    public void setCheckScoerId(int checkScoerId) {
        this.checkScoerId = checkScoerId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
