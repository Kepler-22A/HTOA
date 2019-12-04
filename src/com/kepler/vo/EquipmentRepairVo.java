package com.kepler.vo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ASUS on 2019/12/3.
 */
/*
* 设备维修表
* */
@Entity
@Table(name = "equipmentRepair")
public class EquipmentRepairVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int equipmentId;        //主键，标识列，自动生成
    private String equipmentType;   //维修设备名称
    private int status;             //维修状态0未完成,1已完成
    private int Classes;            //关联班级表主键
    private String Student;         //关联学生表申请人
    private String remark;          //备注
    private int depId;
    private int  userType;          //1学生 2员工
    private Date startTime;         //开始时间
    private Date endTime;           //结束时间

    @Override
    public String toString() {
        return "EquipmentRepair{" +
                "equipmentId=" + equipmentId +
                ", equipmentType='" + equipmentType + '\'' +
                ", status=" + status +
                ", Classes=" + Classes +
                ", StudentVo='" + Student + '\'' +
                ", remark='" + remark + '\'' +
                ", depId=" + depId +
                ", userType=" + userType +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public int getEquipmentId() {

        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getClasses() {
        return Classes;
    }

    public void setClasses(int classes) {
        Classes = classes;
    }

    public String getStudent() {
        return Student;
    }

    public void setStudent(String student) {
        Student = student;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
