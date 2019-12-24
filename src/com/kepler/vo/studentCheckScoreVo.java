

package com.kepler.vo;

import javax.persistence.*;

@Entity
@Table(name = "studentCheckScore")
public class studentCheckScoreVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentCheckScoreId;
    private int templateId; //模板Id
    private int empId;   //关联班主任
    private int classId;  //班级Id
    private int projectId; // 考评项目Id
    private int checkScore;  //得分

    @Override
    public String toString() {
        return "studentCheckScoreVo{" +
                "studentCheckScoreId=" + studentCheckScoreId +
                ", templateId=" + templateId +
                ", empId=" + empId +
                ", classId=" + classId +
                ", projectId=" + projectId +
                ", checkScore=" + checkScore +
                '}';
    }

    public int getStudentCheckScoreId() {
        return studentCheckScoreId;
    }

    public void setStudentCheckScoreId(int studentCheckScoreId) {
        this.studentCheckScoreId = studentCheckScoreId;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
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
