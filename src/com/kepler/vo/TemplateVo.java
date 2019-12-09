package com.kepler.vo;

import javax.persistence.*;

@Entity
@Table(name = "template")
public class TemplateVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int templateId;
    private String templateName ;
    private String templateType ;
    private String templateJob;

    @Override
    public String toString() {
        return "TemplateVo{" +
                "templateId=" + templateId +
                ", templateName='" + templateName + '\'' +
                ", templateType='" + templateType + '\'' +
                ", templateJob='" + templateJob + '\'' +
                '}';
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getTemplateJob() {
        return templateJob;
    }

    public void setTemplateJob(String templateJob) {
        this.templateJob = templateJob;
    }
}
