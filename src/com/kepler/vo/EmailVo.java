package com.kepler.vo;

import javax.persistence.*;
import java.util.Date;

@Table(name = "Email")
@Entity
public class EmailVo {//邮件表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int emailId;
    private String empId;//发布人
    private String receId;//接收人
    private String topic;//标题
    private String content;//发布内容
    private Date sendtime;//发布时间
    private int isRead;//是否已读,1:已读,2:未读
    private String image;//附件
    private String oldFileName;//旧文件名

    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getReceId() {
        return receId;
    }

    public void setReceId(String receId) {
        this.receId = receId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOldFileName() {
        return oldFileName;
    }

    public void setOldFileName(String oldFileName) {
        this.oldFileName = oldFileName;
    }

    @Override
    public String toString() {
        return "EmailVo{" +
                "emailId=" + emailId +
                ", empId='" + empId + '\'' +
                ", receId='" + receId + '\'' +
                ", topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                ", sendtime=" + sendtime +
                ", isRead=" + isRead +
                ", image='" + image + '\'' +
                ", oldFileName='" + oldFileName + '\'' +
                '}';
    }
}
