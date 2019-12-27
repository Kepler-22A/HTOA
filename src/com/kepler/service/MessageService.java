package com.kepler.service;

import com.alibaba.fastjson.JSONArray;
import com.kepler.vo.EmailVo;
import com.kepler.vo.NoticeReceiverVo;
import com.kepler.vo.NoticeVo;

import java.util.List;

/**
 * Created by ASUS on 2019/12/4.
 */
public interface MessageService {
    public List selectNotice(int userType,int receiver);//查询出公告信息
    List selectStudentCount();//查询出学生列表
    List selectStudentCountByClassId(int classId);//查询出某个班学生列表
    List selEmpCount();//查询员工列表
    public void addNotice(NoticeVo vo);//添加公告
    void addNoticeReceiver(NoticeReceiverVo noticeReceiverVo);//添加已读详情表
    void delNoticeReceiver(int noticeId);//根据公告id删除已读详情
    NoticeReceiverVo selNoticeReceiverByNoticeIdAndUserTypeAndReceiver(int noticeId,int userType,int receiver);//根据公告id、用户类型、接收人id获取已读详情
    void updateNoticeReceiverVo(NoticeReceiverVo noticeReceiverVo);//修改NoticeReceiverVo的信息
    public List selectContext(int id);//查询出公告信息
    public void delectContext(NoticeVo vo);//删除公告信息
    public List noticeTitle(int id);//查询出标题
    public void updateNotice(NoticeVo vo);//修改公告信息
    NoticeVo selNoticeByNoticeId(int noticeId);//根据noticeId查noticeVo
    int selNoticeReceiverNumber(int userType,int receiver);//根据用户查询有多少未读的邮件

    /**************邮件**************/
    List selGetEmailList(int receId);//查收到的邮件
    List selForEmailList(int empId);//查发送的邮件
    void deleteGetEmail(int emailId,int receId);//删除收到的邮件
    void deleteForEmail(int emailId,int empId);//删除发送的邮件
    void addEmail(EmailVo emailVo);//新增email
    JSONArray getEmpJSON(int empId);//获取emp的JSONArray
    EmailVo selEmailVoByEmailId(int emailId);//根据emailId查emailVo
    void changeEmailStatus(int emailId);//改变邮件的读取状态
    int selEmailIsReadNotNumber(int receId);//查未读的邮件数
}
