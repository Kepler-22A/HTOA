package com.kepler.vo;

import javax.persistence.*;

@Entity
@Table(name = "StudentFamily")//学生家庭情况表
public class StudentFamily {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentFamily;//家庭主键id
    private int familyid;//学生表外键
    private String stuid;//亲属姓名
    private String familyname;//与学生关系
    private String relation;//亲属电话

    @Override
    public String toString() {
        return "StudentFamily{" +
                "studentFamily=" + studentFamily +
                ", familyid=" + familyid +
                ", stuid='" + stuid + '\'' +
                ", familyname='" + familyname + '\'' +
                ", relation='" + relation + '\'' +
                '}';
    }

    public int getStudentFamily() {
        return studentFamily;
    }

    public void setStudentFamily(int studentFamily) {
        this.studentFamily = studentFamily;
    }

    public int getFamilyid() {
        return familyid;
    }

    public void setFamilyid(int familyid) {
        this.familyid = familyid;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
