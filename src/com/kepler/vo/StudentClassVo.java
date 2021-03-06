package com.kepler.vo;

import javax.persistence.*;

@Entity
@Table(name = "StudentClass")//班级详情表
public class StudentClassVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classid;//主键  1
    private String classno;//班级编号 1
    private String className;//班级昵称1
    private int count;//班级人数1
    private int teacher;//授课老师员工表外键1
    private String classTeacher;//班主任员工表外键1
    private int classType;//班级类别,关联班级类别表1
    private String remark;//备注1
    private int falled;//关联班级类别1
    private int deptId;//关联系1
    private int majorId;//关联专业表1


    @Override
    public String toString() {
        return "StudentClassVo{" +
                "classid=" + classid +
                ", classno='" + classno + '\'' +
                ", className='" + className + '\'' +
                ", count=" + count +
                ", teacher='" + teacher + '\'' +
                ", classTeacher='" + classTeacher + '\'' +
                ", classType=" + classType +
                ", remark='" + remark + '\'' +
                ", falled=" + falled +
                ", deptId=" + deptId +
                ", majorId=" + majorId +
                '}';
    }

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public String getClassno() {
        return classno;
    }

    public void setClassno(String classno) {
        this.classno = classno;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTeacher() {
        return teacher;
    }

    public void setTeacher(int teacher) {
        this.teacher = teacher;
    }

    public String getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(String classTeacher) {
        this.classTeacher = classTeacher;
    }

    public int getClassType() {
        return classType;
    }

    public void setClassType(int classType) {
        this.classType = classType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getFalled() {
        return falled;
    }

    public void setFalled(int falled) {
        this.falled = falled;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }
}
