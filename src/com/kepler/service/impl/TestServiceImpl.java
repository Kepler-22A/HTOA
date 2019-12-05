package com.kepler.service.impl;

import com.kepler.dao.BaseDao;
import com.kepler.service.TestService;
import org.springframework.stereotype.Service;

/**
 * Created by ASUS on 2019/12/4.
 */
@Service
public class TestServiceImpl extends BaseDao implements TestService {
    @Override
    public int selectLogin(String name, String pwd) {
        return executeIntSQL("select  count(*) from empVo where empName='"+name+"' and password='"+pwd+"'");
    }
}
