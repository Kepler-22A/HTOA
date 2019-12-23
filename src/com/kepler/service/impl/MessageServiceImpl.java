package com.kepler.service.impl;

import com.kepler.dao.BaseDao;
import com.kepler.service.ClassService;
import com.kepler.service.MessageService;
import com.kepler.vo.NoticeVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2019/12/4.
 */
@Service
public class MessageServiceImpl extends BaseDao implements MessageService {
    @Override
    public List selectNotice() {
        return sqlQuery("select noticeId,aaa,ccc,content,noticeTime,title,noticeType,empName from notice n left join empVo e on n.empid = e.empId");
    }

    @Override
    public int selectStudentCount() {
        return executeIntSQL("select count(*) from Student");
    }

    @Override
    public void addNotice(NoticeVo vo) {
        save(vo);
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


}
