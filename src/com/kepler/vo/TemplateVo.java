package com.kepler.vo;

import javax.persistence.*;

@Entity
@Table(name = "template")
public class TemplateVo {//考核模板！！
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int templateId;
    private int empId ;//关联员工表
    private int depId ;//被考核部门
    private String templateName ;
    private String templateType ;//考核类型 月底 ，年度
    private String templateTime ;//创建时间
    private String remark ;//备注

    @Override
    public String toString() {
        return "TemplateVo{" +
                "templateId=" + templateId +
                ", empId=" + empId +
                ", depId=" + depId +
                ", templateName='" + templateName + '\'' +
                ", templateType='" + templateType + '\'' +
                ", templateTime=" + templateTime +
                ", remark='" + remark + '\'' +
                '}';
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getTemplateTime() {
        return templateTime;
    }

    public void setTemplateTime(String templateTime) {
        this.templateTime = templateTime;
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

    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
