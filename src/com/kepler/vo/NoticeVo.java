package com.kepler.vo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ASUS on 2019/12/3.
 */
/*
* 通知公告明细表
* */
@Entity
@Table(name = "notice")
public class NoticeVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noticeId;  //主键，标识列，自动生成
    private String title;  //标题
    private String content;//通知内容
    private int noticeType;//类型;1:学生，2员工
    private int empid;     //发布人
    private Date noticeTime;//发布时间
    private String classIds;//班级id列表，逗号分隔
    private int aaa ;//已读人数
    private int ccc;//未读人数

    @Override
    public String toString() {
        return "NoticeVo{" +
                "noticeId=" + noticeId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", noticeType=" + noticeType +
                ", empid=" + empid +
                ", noticeTime=" + noticeTime +
                ", classIds='" + classIds + '\'' +
                ", aaa=" + aaa +
                ", ccc=" + ccc +
                '}';
    }

    public int getAaa() {
        return aaa;
    }

    public void setAaa(int aaa) {
        this.aaa = aaa;
    }

    public int getCcc() {
        return ccc;
    }

    public void setCcc(int ccc) {
        this.ccc = ccc;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(int noticeType) {
        this.noticeType = noticeType;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public Date getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

    public String getClassIds() {
        return classIds;
    }

    public void setClassIds(String classIds) {
        this.classIds = classIds;
    }

}
