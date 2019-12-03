package com.kepler.vo;

import javax.persistence.*;


@Table(name = "stuStatusSet")
@Entity
public class StuStatusSetVo {//学生状态表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusId; //学生状态id
    private String statusName;//状态名称：意向学生、预定报名学生、试学学生、在读学生、已毕业学生
    private String remark;//说明

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "StuStatusSetVo{" +
                "statusId=" + statusId +
                ", statusName='" + statusName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
