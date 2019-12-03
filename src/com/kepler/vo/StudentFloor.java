package com.kepler.vo;

import javax.persistence.*;

@Entity
@Table(name = "StudentFloor")//宿舍楼栋表
public class StudentFloor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int floorId;//楼栋主键id
    private String floorName;//楼栋名称

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    @Override
    public String toString() {
        return "StudentFloor{" +
                "floorId=" + floorId +
                ", floorName='" + floorName + '\'' +
                '}';
    }
}
