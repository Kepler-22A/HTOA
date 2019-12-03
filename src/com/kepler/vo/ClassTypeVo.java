package com.kepler.vo;

import javax.persistence.*;

@Table(name = "classType")
@Entity
public class ClassTypeVo {//班级类别表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int calssTypeId;//主键
    private String classTypeName;//1：高技，2：中技，3:3+2,4：成人教育

    public int getCalssTypeId() {
        return calssTypeId;
    }

    public void setCalssTypeId(int calssTypeId) {
        this.calssTypeId = calssTypeId;
    }

    public String getClassTypeName() {
        return classTypeName;
    }

    public void setClassTypeName(String classTypeName) {
        this.classTypeName = classTypeName;
    }

    @Override
    public String toString() {
        return "ClassTypeVo{" +
                "calssTypeId=" + calssTypeId +
                ", classTypeName='" + classTypeName + '\'' +
                '}';
    }
}
