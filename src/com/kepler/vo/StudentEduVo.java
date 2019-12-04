package com.kepler.vo;

import javax.persistence.*;

@Entity
@Table(name = "StudentEdu")//学生教育经历表
public class StudentEduVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Eduid;//经历主键id
    private int stuId;//学生表外键
    private String school;//就读学校
    private String begindate;//开始时间
    private String enddate;//结束时间

    @Override
    public String toString() {
        return "StudentEduVo{" +
                "Eduid=" + Eduid +
                ", stuId=" + stuId +
                ", school='" + school + '\'' +
                ", begindate='" + begindate + '\'' +
                ", enddate='" + enddate + '\'' +
                '}';
    }

    public int getEduid() {
        return Eduid;
    }

    public void setEduid(int eduid) {
        Eduid = eduid;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getBegindate() {
        return begindate;
    }

    public void setBegindate(String begindate) {
        this.begindate = begindate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
}
