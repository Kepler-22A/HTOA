package com.kepler.vo;

import javax.persistence.*;

/**
 * Created by ASUS on 2019/12/3.
 */
/*
*通知公告接收人
* */
@Entity
@Table(name = "noticeReceiver")
public class NoticeReceiverVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int receiverId;  //主键，标识列，自动生成
    private int noticeId;      //关联管理通知公告
    private int receiver;    //接收人
    private int isRead;         //是否已读,1:已读,2:未读
    private int userType;  //用户类型，1为员工，2为学生

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "NoticeReceiverVo{" +
                "receiverId=" + receiverId +
                ", noticeId=" + noticeId +
                ", receiver=" + receiver +
                ", isRead=" + isRead +
                ", userType=" + userType +
                '}';
    }
}
