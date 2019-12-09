package com.kepler.service.impl;

import com.kepler.dao.BaseDao;
import com.kepler.service.TestService;
import com.kepler.vo.AuditModelVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2019/12/4.
 */
@Service
public class TestServiceImpl extends BaseDao implements TestService {
    @Override
    public int selectLogin(String name, String pwd) {
        return executeIntSQL("select  count(*) from empVo where empName='"+name+"' and password='"+pwd+"'");
    }

    @Override
    public List selectTable() {//查询考核数据！！
        return   sqlQuery("select auditModelID,auditName,scores,auditTypeName,remark from AuditType a ,AuditModel m where a.depID = m.depID");
    }

    @Override
    public List selectTable2() {
        return sqlQuery("select m.auditName,e.empName, a.* from AuditLog a,empVo e,AuditModel m where  a.empID = e.empID and a.auditModelID= m.auditModelID");
    }


    @Override
    public List selectTable3() {
        return sqlQuery("");
    }

    @Override
    public int addExmaine(AuditModelVo a) {
        return sqlUpdate("insert into AuditModel values('"+a.getAuditName()+"',"+a.getDepID()+",'"+a.getRemark()+"','"+a.getScores()+"')");
    }
}
