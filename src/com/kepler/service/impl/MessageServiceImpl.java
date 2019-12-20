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


}
