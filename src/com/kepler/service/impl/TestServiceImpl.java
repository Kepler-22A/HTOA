package com.kepler.service.impl;

import com.kepler.dao.BaseDao;
import com.kepler.service.TestService;
import com.kepler.vo.*;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public int selectStudentLogin(String name, String pwd) {
        return executeIntSQL("select  count(*) from Student where stuname='"+name+"' and password='"+pwd+"'");
    }

    @Override
    public int selectInt(String name) {
        return executeIntSQL("select  empId from empVo where empName='"+name+"'");
    }

    @Override
    public int selectInt2(String name) {
        return executeIntSQL("select  templateId from template where templateTime='"+name+"'");
    }

    @Override
    public int selStudentId(String stuname) {
        return executeIntSQL("select Studid from Student where stuname='"+stuname+"'");
    }

//    @Override
//    public int selectInt3(Date name) {
//        return executeIntSQL("select  templateId from template where templateTime='"+name+"'");
//    }

    @Override
    public List selectTable() {//查询考核数据！！
        return   sqlQuery("select auditModelID,auditName,scores,auditTypeName,remark from AuditType a ,AuditModel m where a.depID = m.depID");
    }

    @Override
    public List selectTable2() {
        return sqlQuery("select m.auditName,e.empName, a.* from AuditLog a,empVo e,AuditModel m where  a.empID = e.empID and a.auditModelID= m.auditModelID");
    }

    @Override
    public List selectTable3() {//查询考评模板
        return sqlQuery("select e.empName ,t.* from template t,empVo e,dep d where e.empId = t.empId and d.depId = t.depId");
    }

    @Override
    public int addExmaine(AuditModelVo a) {
        return sqlUpdate("insert into AuditModel values('"+a.getAuditName()+"',"+a.getDepID()+",'"+a.getRemark()+"','"+a.getScores()+"')");
    }

    @Override
    public int addTemplate(TemplateVo t) {
        return sqlUpdate("insert into template values("+t.getDepId()+","+t.getEmpId()+",'"+t.getRemark()+"','"+t.getTemplateName()+"','"+t.getTemplateTime()+"','"+t.getTemplateType()+"')");
    }

    @Override
    public int delete(int id) {
        return sqlUpdate("delete from AuditModel where auditModelID="+id+"");
    }

    @Override
    public int delete2(int id) {
        return sqlUpdate("delete from AuditLog where auditLogID='"+id+"'");
    }

    @Override
    public int delete3(int id) {
        return sqlUpdate("delete from template where templateId='"+id+"'");
    }


    @Override
    public int addCheckProject(checkProjectVo c) {
        return sqlUpdate("insert  into checkProject values('"+c.getJudgment()+"',"+c.getMaxScoer()+",'"+c.getProjectName()+"','"+c.getRemark()+"',"+c.getTemplateId()+")");
    }

    @Override
    public int addCheckSetp(checkStepVo s) {
        save(s);
        return 1;
        //return sqlUpdate("insert into checkStep values('"+s.getBeginTime()+"','"+s.getCheckStepName()+"','"+s.getCheckStepType()+"','"+s.getEndTime()+"',"+s.getStep()+","+s.getTemplateId()+","+s.getWeight()+")");
    }

    @Override
    public int addCheckResult(checkResultVo r) {
        return sqlUpdate("insert into checkResult values('"+r.getGrade()+"',"+r.getMax()+","+r.getMin()+",'"+r.getRemark()+"',"+r.getTemplateId()+")");
    }

    @Override
    public List selectProject(int templateId) {
        return sqlQuery("select * from checkProject  where templateId = "+templateId+"");
    }

    @Override
    public List selectSetp(int templateId) {
        return sqlQuery("select * from checkStep  where templateId = "+templateId+"");
    }

    @Override
    public List selectResult(int templateId) {
        return sqlQuery("select * from checkResult  where templateId = "+templateId+"");
    }

    @Override
    public List selectMyCheckProject(int templateId, int empId) {
        return sqlQuery("select p.projectName, e.empName from checkProject p,empVo e  where p.templateId = "+templateId+" and e.empId = "+empId+"");
    }

    @Override
    public String selectTime(int templateId) {
        return executeStringSQL("select beginTime from checkStep where templateId ="+templateId+"");
    }

    @Override
    public int selectTotal(int templateId) {
        return executeIntSQL("select total from checkScoer where templateId = "+templateId+"");
    }


    @Override
    public int OKAccount(String account, String pwd) {
        return executeIntSQL("select empId from empVo where empName = '"+account+"' and password = '"+pwd+"'");
    }
}
