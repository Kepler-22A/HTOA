//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kepler.vo;

public class studentCheckScoreVo {
    private int studentCheckScoreId;
    private int templateId; //模板Id
    private int empId;   //关联班主任
    private int classId;  //班级Id
    private int projectId; // 考评项目Id
    private int checkScore;  //得分

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
