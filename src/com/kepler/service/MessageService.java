package com.kepler.service;

import com.alibaba.fastjson.JSONArray;
import com.kepler.vo.EmailVo;
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

    /**************邮件**************/
    List selGetEmailList(int receId);//查收到的邮件
    List selForEmailList(int empId);//查发送的邮件
    void deleteGetEmail(int emailId,int receId);//删除收到的邮件
    void deleteForEmail(int emailId,int empId);//删除发送的邮件
    void addEmail(EmailVo emailVo);//新增email
    JSONArray getEmpJSON(int empId);//获取emp的JSONArray
    EmailVo selEmailVoByEmailId(int emailId);//根据emailId查emailVo
    void changeEmailStatus(int emailId);//改变邮件的读取状态
}
