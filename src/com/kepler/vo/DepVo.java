package com.kepler.vo;

import javax.persistence.*;

@Table(name = "dep")
@Entity
public class DepVo {//部门设置
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int depid;//
    private String depName;//部门名称
    private int parentId;//父部门名称
    private String chairman;//部门负责人
    private String remark;//备注
    private int personnel;//是否为人事部

    public int getDepid() {
        return depid;
    }

    public void setDepid(int depid) {
        this.depid = depid;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getChairman() {
        return chairman;
    }

    public void setChairman(String chairman) {
        this.chairman = chairman;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getPersonnel() {
        return personnel;
    }

    public void setPersonnel(int personnel) {
        this.personnel = personnel;
    }

    @Override
    public String toString() {
        return "DepVo{" +
                "depid=" + depid +
                ", depName='" + depName + '\'' +
                ", parentId=" + parentId +
                ", chairman='" + chairman + '\'' +
                ", remark='" + remark + '\'' +
                ", personnel=" + personnel +
                '}';
    }
}
