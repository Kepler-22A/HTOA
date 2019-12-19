package com.kepler.service.impl;

import com.kepler.dao.BaseDao;
import com.kepler.service.ClassService;
import com.kepler.service.MessageService;
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
}
