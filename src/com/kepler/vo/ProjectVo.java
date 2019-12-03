package com.kepler.vo;

import javax.persistence.*;

@Table(name = "Project")
@Entity
public class ProjectVo {//项目答辩名称表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;
    private String projectName;//项目名称
    private String remark;//说明

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ProjectVo{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
