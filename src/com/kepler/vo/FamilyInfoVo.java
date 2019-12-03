package com.kepler.vo;

import javax.persistence.*;

@Entity
@Table(name = "familyInfo")
//员工的家庭情况，包括父母信息，配偶信息，联系方式及家庭地址等情况
public class FamilyInfoVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int familyId;
    private String empId;//
    private String contactName;//联系人名称
    private String relationship;//与员工关系
    private String Phone;//	联系电话
    private String Remark;//说明


    @Override
    public String toString() {
        return "FamilyInfoVo{" +
                "familyId=" + familyId +
                ", empId='" + empId + '\'' +
                ", contactName='" + contactName + '\'' +
                ", relationship='" + relationship + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Remark='" + Remark + '\'' +
                '}';
    }

    public int getFamilyId() {
        return familyId;
    }

    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
