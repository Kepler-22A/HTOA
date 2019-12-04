package com.kepler.vo;

import javax.persistence.*;

@Entity
@Table(name = "StudentFall")//届表
public class StudentFallVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fallid;//届表id
    private String level;//届别名称
    private String remark;//说明

    @Override
    public String toString() {
        return "StudentFallVo{" +
                "fallid=" + fallid +
                ", level='" + level + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public int getFallid() {
        return fallid;
    }

    public void setFallid(int fallid) {
        this.fallid = fallid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
