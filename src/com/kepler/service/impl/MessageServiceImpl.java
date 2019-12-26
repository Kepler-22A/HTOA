package com.kepler.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kepler.dao.BaseDao;
import com.kepler.service.ClassService;
import com.kepler.service.MessageService;
import com.kepler.vo.EmailVo;
import com.kepler.vo.NoticeReceiverVo;
import com.kepler.vo.NoticeVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2019/12/4.
 */
@Service
public class MessageServiceImpl extends BaseDao implements MessageService {
    @Override
    public List selectNotice(int userType,int receiver) {
        if (userType == 1){
            return sqlQuery("select noticeId,aaa,ccc,content,noticeTime,title,noticeType,empName from notice n left join empVo e on n.empid = e.empId where n.noticeId in (select noticeId from noticeReceiver where (userType = "+userType+" and receiver = "+receiver+") or n.empid = "+receiver+")");
        }else{
            return sqlQuery("select noticeId,aaa,ccc,content,noticeTime,title,noticeType,empName from notice n left join empVo e on n.empid = e.empId where n.noticeId in (select noticeId from noticeReceiver where userType = "+userType+" and receiver = "+receiver+")");
        }

    }

    @Override
    public List selectStudentCount() {
        return sqlQuery("select * from Student");
    }

    @Override
    public List selectStudentCountByClassId(int classId) {
        return sqlQuery("select * from Student where clazz = '" + classId + "'");
    }

    @Override
    public List selEmpCount() {
        return sqlQuery("select * from empVo");
    }

    @Override
    public void addNotice(NoticeVo vo) {
        save(vo);
    }

    @Override
    public void addNoticeReceiver(NoticeReceiverVo noticeReceiverVo) {
        save(noticeReceiverVo);
    }

    @Override
    public void delNoticeReceiver(int noticeId) {
        sqlUpdate("delete noticeReceiver where noticeId = " + noticeId);
    }

    @Override
    public NoticeReceiverVo selNoticeReceiverByNoticeIdAndUserTypeAndReceiver(int noticeId, int userType, int receiver) {
        List noticeReceiver = sqlQuery("select * from noticeReceiver where noticeId = "+noticeId+" and receiver = "+receiver+" and userType = " + userType);
        NoticeReceiverVo noticeReceiverVo = new NoticeReceiverVo();
        for (Object o : noticeReceiver){
            Map map = (HashMap) o;

            noticeReceiverVo.setReceiverId(Integer.parseInt(map.get("receiverId")+""));
            noticeReceiverVo.setUserType(Integer.parseInt(map.get("userType")+""));
            noticeReceiverVo.setReceiver(Integer.parseInt(map.get("receiver")+""));
            noticeReceiverVo.setNoticeId(Integer.parseInt(map.get("noticeId")+""));
            noticeReceiverVo.setIsRead(Integer.parseInt(map.get("isRead")+""));
        }

        System.out.println(noticeReceiverVo);

        return noticeReceiverVo;
    }

    @Override
    public void updateNoticeReceiverVo(NoticeReceiverVo noticeReceiverVo) {
        update(noticeReceiverVo);
    }

    @Override
    public List selectContext(int id) {
        return sqlQuery("select content from notice where noticeId ="+id);
    }

    @Override
    public void delectContext(NoticeVo vo) {
        delete(vo);
    }

    @Override
    public List noticeTitle(int id) {
        return sqlQuery("select  title from notice where noticeId ="+id);
    }

    @Override
    public void updateNotice(NoticeVo vo) {
        update(vo);
    }

    @Override
    public NoticeVo selNoticeByNoticeId(int noticeId) {
        return (NoticeVo)getObject(NoticeVo.class,noticeId);
    }

    @Override
    public int selNoticeReceiverNumber(int userType, int receiver) {
        int num = executeIntSQL("SELECT count(*) FROM noticeReceiver where isRead = 2 and receiver = "+receiver+" and userType = " + userType);
        return num;
    }

    @Override
    public List selGetEmailList(int receId) {
        return sqlQuery("select *,(select e.empName from empVo e where e.empId = em.empId) as empName from Email em where em.receId = '" + receId + "'");
    }

    @Override
    public List selForEmailList(int empId) {
        return sqlQuery("select *,(select e.empName from empVo e where e.empId = em.receId) as receName from Email em where em.empId = '" + empId + "'");
    }

    @Override
    public void deleteGetEmail(int emailId, int receId) {
        sqlUpdate("delete Email where emailId = " + emailId + " and receId = '" + receId + "'");
    }

    @Override
    public void deleteForEmail(int emailId, int empId) {
        sqlUpdate("delete Email where emailId = " + emailId + " and empId = '" + empId + "'");
    }

    @Override
    public void addEmail(EmailVo emailVo) {
        save(emailVo);
    }

    @Override
    public JSONArray getEmpJSON(int empId) {
        JSONArray ja = new JSONArray();

        List depList = sqlQuery("select depid,depName from dep");

//        System.out.println("depList :" + depList);

        for (Object o : depList){
            Map map = (HashMap)o;

            int depid = Integer.parseInt(map.get("depid")+"");

            List emplist = sqlQuery("select * from empVo where depId = " + Integer.parseInt(map.get("depid")+""));
//            System.out.println("empList :" + emplist);

            for (Object empMapO : emplist){
                Map empMap = (HashMap)empMapO;

                JSONObject jos = new JSONObject();

                jos.put("value",Integer.parseInt(empMap.get("empId")+""));
                jos.put("title",empMap.get("empName")+"");
                jos.put("disabled","");
                jos.put("checked","");

                ja.add(jos);

                if (Integer.parseInt(jos.get("value")+"") == empId){
                    ja.remove(jos);
                }
            }
        }

//        System.out.println("ja :" + ja);

        return ja;
    }

    @Override
    public EmailVo selEmailVoByEmailId(int emailId) {
        return (EmailVo) getObject(EmailVo.class,emailId);
    }

    @Override
    public void changeEmailStatus(int emailId) {
        EmailVo emailVo = (EmailVo) getObject(EmailVo.class,emailId);
        emailVo.setIsRead(1);
        update(emailVo);
    }

    @Override
    public int selEmailIsReadNotNumber(int receId) {
        return executeIntSQL("select count(*) from email where receId = "+receId+" and isRead = 2");
    }


}
