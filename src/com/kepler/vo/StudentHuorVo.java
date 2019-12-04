package com.kepler.vo;

import javax.persistence.*;

@Entity
@Table(name = "StudentHuor")//宿舍表
public class StudentHuorVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Hourid;//宿舍主键id
    private String huorName;//宿舍房号(508)
    private String addr;//宿舍地址
    private int count;//宿舍人数
    private int floorId;//楼栋id外键
    private int numberBeds;//床位数
    private int huoeIddsc;//序号

    @Override
    public String toString() {
        return "StudentHuorVo{" +
                "Hourid=" + Hourid +
                ", huorName='" + huorName + '\'' +
                ", addr='" + addr + '\'' +
                ", count=" + count +
                ", floorId=" + floorId +
                ", numberBeds=" + numberBeds +
                ", huoeIddsc=" + huoeIddsc +
                '}';
    }

    public int getHourid() {
        return Hourid;
    }

    public void setHourid(int hourid) {
        Hourid = hourid;
    }

    public String getHuorName() {
        return huorName;
    }

    public void setHuorName(String huorName) {
        this.huorName = huorName;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public int getNumberBeds() {
        return numberBeds;
    }

    public void setNumberBeds(int numberBeds) {
        this.numberBeds = numberBeds;
    }

    public int getHuoeIddsc() {
        return huoeIddsc;
    }

    public void setHuoeIddsc(int huoeIddsc) {
        this.huoeIddsc = huoeIddsc;
    }
}
