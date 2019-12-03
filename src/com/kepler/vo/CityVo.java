package com.kepler.vo;

import javax.persistence.*;

@Table(name = "city")
@Entity
public class CityVo {//户籍表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cityId;
    private String cityname;//地理名称
    private String citytype;//省,市,县
    private String py;
    private String wb;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getCitytype() {
        return citytype;
    }

    public void setCitytype(String citytype) {
        this.citytype = citytype;
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    public String getWb() {
        return wb;
    }

    public void setWb(String wb) {
        this.wb = wb;
    }

    @Override
    public String toString() {
        return "CityVo{" +
                "cityId=" + cityId +
                ", cityname='" + cityname + '\'' +
                ", citytype='" + citytype + '\'' +
                ", py='" + py + '\'' +
                ", wb='" + wb + '\'' +
                '}';
    }
}
