//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kepler.vo;

public class studentCheckScoreVo {
    private int studentCheckScoreId;
    private int templateId;
    private int empId;
    private int classId;
    private int projectId;
    private int checkScore;

    public studentCheckScoreVo() {
    }

    public String toString() {
        return "studentCheckScore{studentCheckScoreId=" + this.studentCheckScoreId + ", templateId=" + this.templateId + ", empId=" + this.empId + ", classId=" + this.classId + ", projectId=" + this.projectId + ", checkScore=" + this.checkScore + '}';
    }

    public int getStudentCheckScoreId() {
        return this.studentCheckScoreId;
    }

    public void setStudentCheckScoreId(int studentCheckScoreId) {
        this.studentCheckScoreId = studentCheckScoreId;
    }

    public int getTemplateId() {
        return this.templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public int getEmpId() {
        return this.empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getClassId() {
        return this.classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getProjectId() {
        return this.projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getCheckScore() {
        return this.checkScore;
    }

    public void setCheckScore(int checkScore) {
        this.checkScore = checkScore;
    }
}
