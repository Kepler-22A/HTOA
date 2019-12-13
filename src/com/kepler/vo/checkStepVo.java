package com.kepler.vo;



import org.h2.store.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "checkStep")
public class checkStepVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int checkStepId ;
    private String checkStepName ;//步骤名称
    private int step ;//步骤
    private String checkStepType ;//步骤类型
    private float  weight ;//权重
    private Date checkTime ;//考核时间


    @Override
    public String toString() {
        return "checkStepVo{" +
                "checkStepId=" + checkStepId +
                ", checkStepName='" + checkStepName + '\'' +
                ", step=" + step +
                ", checkStepType='" + checkStepType + '\'' +
                ", weight=" + weight +
                ", checkTime=" + checkTime +
                '}';
    }

    public int getCheckStepId() {
        return checkStepId;
    }

    public void setCheckStepId(int checkStepId) {
        this.checkStepId = checkStepId;
    }

    public String getCheckStepName() {
        return checkStepName;
    }

    public void setCheckStepName(String checkStepName) {
        this.checkStepName = checkStepName;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getCheckStepType() {
        return checkStepType;
    }

    public void setCheckStepType(String checkStepType) {
        this.checkStepType = checkStepType;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
}
