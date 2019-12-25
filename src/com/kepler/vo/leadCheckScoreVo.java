package com.kepler.vo;


import javax.persistence.*;

@Entity
@Table(name = "leadCheckScore")
public class leadCheckScoreVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leadCheckScoreId ;
    private int empId ; //关联考核人
    private int depId ;  //关联部门Id
    private int template ;  //模板
    private int projectId ; //项目Id
    private int checkScore;  //得分

    @Override
    public String toString() {
        return "leadCheckScoreVo{" +
                "leadCheckScoreId=" + leadCheckScoreId +
                ", empId=" + empId +
                ", depId=" + depId +
                ", template=" + template +
                ", projectId=" + projectId +
                ", checkScore=" + checkScore +
                '}';
    }

    public int getLeadCheckScoreId() {
        return leadCheckScoreId;
    }

    public void setLeadCheckScoreId(int leadCheckScoreId) {
        this.leadCheckScoreId = leadCheckScoreId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public int getTemplate() {
        return template;
    }

    public void setTemplate(int template) {
        this.template = template;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getCheckScore() {
        return checkScore;
    }

    public void setCheckScore(int checkScore) {
        this.checkScore = checkScore;
    }
}
