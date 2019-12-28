package com.kepler.vo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "checkStep")
public class checkStepVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int checkStepId ;
    private int templateId;//关联模板
    private String checkStepName ;//步骤名称
    private int step ;//步骤  1学生评，领导评
    private String checkStepType ;//步骤类型
    private float  weight ;//权重
    private String beginTime ; //开始时间

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public int getCheckStepId() {
        return checkStepId;
    }

    public void setCheckStepId(int checkStepId) {
        this.checkStepId = checkStepId;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
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


    @Override
    public String toString() {
        return "checkStepVo{" +
                "checkStepId=" + checkStepId +
                ", templateId=" + templateId +
                ", checkStepName='" + checkStepName + '\'' +
                ", step=" + step +
                ", checkStepType='" + checkStepType + '\'' +
                ", weight=" + weight +
                ", beginTime=" + beginTime +
                '}';
    }
}
