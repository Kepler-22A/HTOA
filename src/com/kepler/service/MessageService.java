package com.kepler.service;

import com.kepler.vo.NoticeVo;

import java.util.List;

/**
 * Created by ASUS on 2019/12/4.
 */
public interface MessageService {
    public List selectNotice();//查询出公告信息
    public int selectStudentCount();//查询出多少个学生
    public void addNotice(NoticeVo vo);//添加公告
    public List selectContext(int id);//查询出公告信息
    public void delectContext(NoticeVo vo);//删除公告信息
    public List noticeTitle(int id);//查询出标题
    public void updateNotice(NoticeVo vo);//修改公告信息
}
