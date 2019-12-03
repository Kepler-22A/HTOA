package com.kepler.vo;

import javax.persistence.*;
import java.util.Date;

@Table(name = "Annex")
@Entity
public class AnnexVo {//附件表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int annexId;
    private String seName;//类型选择(1:员工,2:学生)
    private String seId;//关联员工或者学生ID
    private String annexName;//附件名称
    private String annexData;//上传附件
    private Date annexDate;//上传时间
    private String remark;//说明
    private String sessionName;//上传人

    public int getAnnexId() {
        return annexId;
    }

    public void setAnnexId(int annexId) {
        this.annexId = annexId;
    }

    public String getSeName() {
        return seName;
    }

    public void setSeName(String seName) {
        this.seName = seName;
    }

    public String getSeId() {
        return seId;
    }

    public void setSeId(String seId) {
        this.seId = seId;
    }

    public String getAnnexName() {
        return annexName;
    }

    public void setAnnexName(String annexName) {
        this.annexName = annexName;
    }

    public String getAnnexData() {
        return annexData;
    }

    public void setAnnexData(String annexData) {
        this.annexData = annexData;
    }

    public Date getAnnexDate() {
        return annexDate;
    }

    public void setAnnexDate(Date annexDate) {
        this.annexDate = annexDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    @Override
    public String toString() {
        return "AnnexVo{" +
                "annexId=" + annexId +
                ", seName='" + seName + '\'' +
                ", seId='" + seId + '\'' +
                ", annexName='" + annexName + '\'' +
                ", annexData='" + annexData + '\'' +
                ", annexDate=" + annexDate +
                ", remark='" + remark + '\'' +
                ", sessionName='" + sessionName + '\'' +
                '}';
    }
}
