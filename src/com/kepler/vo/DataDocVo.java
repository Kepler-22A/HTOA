package com.kepler.vo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ASUS on 2019/12/3.
 */

/*
* 资料文档表
* */
@Entity
@Table(name = "dataDoc")
public class DataDocVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int docId;      //主键，标识列，自动生成
    private String dataName;    //资料名称
    private String url;     //资料地址
    private Date optime;       //上传时间
    private String remark;  //备注
    private int empid;      //上传人员

    @Override
    public String toString() {
        return "DataDocVo{" +
                "docId=" + docId +
                ", dataName='" + dataName + '\'' +
                ", url='" + url + '\'' +
                ", optime=" + optime +
                ", remark='" + remark + '\'' +
                ", empid=" + empid +
                '}';
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getOptime() {
        return optime;
    }

    public void setOptime(Date optime) {
        this.optime = optime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }
}
