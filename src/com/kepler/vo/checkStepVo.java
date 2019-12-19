package com.kepler.vo;



import org.h2.store.Data;
import org.joda.time.DateTime;

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
    private int step ;//步骤  1自评 ,2 学生评，领导评
    private String checkStepType ;//步骤类型
    private float  weight ;//权重
    private Date beginTime ;//开始时间
    private Date endTime ;//结束时间


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

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
                ", endTime=" + endTime +
                '}';
    }
}
