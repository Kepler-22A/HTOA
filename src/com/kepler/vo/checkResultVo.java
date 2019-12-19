package com.kepler.vo;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;

@Entity
@Table(name = "checkResult")
public class checkResultVo {//考评结果表！！
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int checkResultId ;
    private int templateId;//模板Id
    private int min ;//最低分
    private int max;//最高分
    private String remark ;//评语
    private String grade;//等级

    public int getCheckResultId() {
        return checkResultId;
    }

    public void setCheckResultId(int checkResultId) {
        this.checkResultId = checkResultId;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
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

    @Override
    public String toString() {
        return "checkResultVo{" +
                "checkResultId=" + checkResultId +
                ", templateId=" + templateId +
                ", min=" + min +
                ", max=" + max +
                ", remark='" + remark + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
