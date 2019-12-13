package com.kepler.vo;


import javax.persistence.*;

@Entity
@Table(name = "checkProject")
public class checkProjectVo {//考核项目表！！
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId ;
    private int templateId ;//关联templateVo表
    private String projectName ;//项目名称
    private int maxScoer ;//最高分
    private String judgment ;//评分细则
    private String remark ;//备注


    @Override
    public String toString() {
        return "checkProjectVo{" +
                "projectId=" + projectId +
                ", templateId=" + templateId +
                ", projectName='" + projectName + '\'' +
                ", maxScoer=" + maxScoer +
                ", judgment='" + judgment + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getMaxScoer() {
        return maxScoer;
    }

    public void setMaxScoer(int maxScoer) {
        this.maxScoer = maxScoer;
    }

    public String getJudgment() {
        return judgment;
    }

    public void setJudgment(String judgment) {
        this.judgment = judgment;
    }
}
