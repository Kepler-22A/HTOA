package com.kepler.vo;

import javax.persistence.*;

/**
 * Created by ASUS on 2019/12/3.
 */
/*
* 员工考评标准表
* */
@Entity
@Table(name = "evaluation")
public class EvaluationVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int evaluationid;  //主键，标识列，自动生成
    private String evaluationame; //考评名称
    private int score;             //考核分值
    private int evaluationType;    //1:授课老师2:班主任
    private String remark;             //说明

    @Override
    public String toString() {
        return "EvaluationVo{" +
                "evaluationid=" + evaluationid +
                ", evaluationame='" + evaluationame + '\'' +
                ", score=" + score +
                ", evaluationType=" + evaluationType +
                ", remark='" + remark + '\'' +
                '}';
    }

    public int getEvaluationid() {
        return evaluationid;
    }

    public void setEvaluationid(int evaluationid) {
        this.evaluationid = evaluationid;
    }

    public String getEvaluationame() {
        return evaluationame;
    }

    public void setEvaluationame(String evaluationame) {
        this.evaluationame = evaluationame;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getEvaluationType() {
        return evaluationType;
    }

    public void setEvaluationType(int evaluationType) {
        this.evaluationType = evaluationType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
