package com.kepler.vo;

import javax.persistence.*;

/**
 * Created by ASUS on 2019/12/3.
 */
/*
* 班主任和老师考评表
* */

@Entity
@Table(name = "headTeacher")
public class HeadTeacherVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int headTeacherid;  //主键，标识列，自动生成
    private int evaluationid;  //考评类别id关联考评类
    private String testScore;  //考评分
    private int totalId;       //关联班考评主表

    @Override
    public String toString() {
        return "HeadTeacherVo{" +
                "headTeacherid=" + headTeacherid +
                ", evaluationid=" + evaluationid +
                ", testScore='" + testScore + '\'' +
                ", totalId=" + totalId +
                '}';
    }

    public int getHeadTeacherid() {
        return headTeacherid;
    }

    public void setHeadTeacherid(int headTeacherid) {
        this.headTeacherid = headTeacherid;
    }

    public int getEvaluationid() {
        return evaluationid;
    }

    public void setEvaluationid(int evaluationid) {
        this.evaluationid = evaluationid;
    }

    public String getTestScore() {
        return testScore;
    }

    public void setTestScore(String testScore) {
        this.testScore = testScore;
    }

    public int getTotalId() {
        return totalId;
    }

    public void setTotalId(int totalId) {
        this.totalId = totalId;
    }
}
