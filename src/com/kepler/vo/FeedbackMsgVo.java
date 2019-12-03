package com.kepler.vo;

import javax.persistence.*;
import java.util.Date;

@Table(name = "FeedbackMsg")
@Entity
public class FeedbackMsgVo {//返回问题反馈表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feedbackMsgId;
    private String feedbackMsgName;//
    private String userId;//发送人id
    private String userName;//发送人名称
    private int msgType;//1 问题反馈人  2 回复人
    private Date singDate;//
    private int feedbackId;//问题反馈表id

    public int getFeedbackMsgId() {
        return feedbackMsgId;
    }

    public void setFeedbackMsgId(int feedbackMsgId) {
        this.feedbackMsgId = feedbackMsgId;
    }

    public String getFeedbackMsgName() {
        return feedbackMsgName;
    }

    public void setFeedbackMsgName(String feedbackMsgName) {
        this.feedbackMsgName = feedbackMsgName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public Date getSingDate() {
        return singDate;
    }

    public void setSingDate(Date singDate) {
        this.singDate = singDate;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    @Override
    public String toString() {
        return "FeedbackMsgVo{" +
                "feedbackMsgId=" + feedbackMsgId +
                ", feedbackMsgName='" + feedbackMsgName + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", msgType=" + msgType +
                ", singDate=" + singDate +
                ", feedbackId=" + feedbackId +
                '}';
    }
}
