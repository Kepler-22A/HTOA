package com.kepler.vo;

import javax.persistence.*;

@Table(name = "CharEmp")
@Entity
public class CharEmpVo {//员工用户角色表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int charEmpId;
    private String empId;//员工id
    private int characterId;//角色id

    public int getCharEmpId() {
        return charEmpId;
    }

    public void setCharEmpId(int charEmpId) {
        this.charEmpId = charEmpId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    @Override
    public String toString() {
        return "CharEmpVo{" +
                "charEmpId=" + charEmpId +
                ", empId='" + empId + '\'' +
                ", characterId=" + characterId +
                '}';
    }
}
