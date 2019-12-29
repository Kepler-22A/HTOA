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
        return executeIntSQL("select  count(*) from empVo where empName='"+name+"' and password='"+pwd+"' and status = 1");
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
        return sqlQuery("select e.empName ,d.depName,t.* from template t,empVo e,dep d where e.empId = t.empId and d.depId = t.depId");
    }

    @Override
    public List selectTable4() {
        return sqlQuery("select * from template where openCheck = 2");
    }

    @Override
    public List selectTable5() {
        return sqlQuery("select DISTINCT c.templateId,e.empName,t.templateName from template t LEFT JOIN checkPeople c on t.templateId  = c.templateId LEFT JOIN empVo e on e.empId = c.empId where c.leadState = '待考评'");
    }

    @Override
    public int addExmaine(AuditModelVo a) {
        save(a);
        return 1;
    }

    @Override
    public int addTemplate(TemplateVo t) {
        save(t);
        return 1;
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
        save(r);
        return 1;
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
    public List selectOverCheck() {
        return sqlQuery("select * from template  where openCheck = 3");
    }

    @Override
    public List selectMyCheck(int templateId, int empId) {
        return sqlQuery("select t.templateName,e.empName ,s.studentComment,s.leadComment,s.total from checkScoer s LEFT JOIN template t on s.templateId = t.templateId LEFT JOIN empVo e on s.empId = e.empId where s.templateId = "+templateId+" and s.empId = "+empId+"");
    }

    @Override //学生评价查询
    public List selectMyCheckProject(int templateId, int empId) {
        return sqlQuery("select DISTINCT t.templateId,t.projectName,e.empName,c.beginTime,s.checkScore from checkProject t left join studentCheckScore s on t.projectId = s.projectId LEFT JOIN empVo e on s.empId = e.empId LEFT JOIN checkStep c on t.templateId = c.templateId where t.templateId = "+templateId+"and e.empId ="+empId+"and c.step = 1" );
    }

    @Override
    public List selectStepType(int id) {
        return sqlQuery("select checkStepType from checkStep where templateId ="+id+"");
    }

    @Override
    public int deletePeople(int templateId) {
        return sqlUpdate("delete from checkPeople where templateId = "+templateId+"");
    }

    @Override
    public int selectScore(int empId) {
        return executeIntSQL("select sum(checkScore) from leadCheckScore where empId = "+empId+"");
    }

    @Override
    public int selectScore2(int empId) {
        return executeIntSQL("select sum(checkScore) from studentCheckScore where empId ="+empId+"");
    }

    @Override
    public float selectWeight(int templateId) {
        return executeFloatSQL("select weight from checkStep where checkStepType ='学生评' and templateId = 1");
    }

    @Override
    public float selectWeight2(int templateId) {
        return executeFloatSQL("select weight from checkStep where checkStepType ='领导评' and templateId = 1");
    }

    @Override
    public int addCheckScore(checkScoerVo scoreVo) {
        save(scoreVo);
        return 1;
    }

    @Override
    public String selectTeacher(int stuId) {
        return executeStringSQL("select empName from empVo where empId =(select c.empId from classTeacher c where c.classId = (select clazz from Student where Studid = "+stuId+"))");
    }

    @Override
    public String selectTeacher2(int stuId) {
        return executeStringSQL("select empName from empVo where empId =( select teacher from StudentClass where classid = (select clazz  from Student  where Studid = "+stuId+"))");
    }

    @Override
    public int selectTeacherId(int stuId) {
        return executeIntSQL("select c.empId from classTeacher c where  c.classId = (select clazz from Student where Studid = "+stuId+")");
    }

    @Override
    public int selectStuClassId(int stuId) {
        return executeIntSQL("select clazz from Student where Studid = "+stuId+"");
    }

    @Override
    public int selectTeacherID(String empName) {
        return executeIntSQL("select empId from empVo where  empName = '"+empName+"'");
    }

    @Override
    public int selectDepId() {
        return executeIntSQL("select depId from template where openCheck = 2");
    }

    @Override
    public int selectDepId2(String empName) {
        return executeIntSQL("select depId from empVo where  empName = '"+empName+"'");
    }

    @Override
    public int selectDepID(int templateId) {
        return executeIntSQL("select depId from template where templateId = "+templateId+"");
    }

    @Override
    public int addMark(studentCheckScoreVo scoreVo) {
        save(scoreVo);
        return 1;
    }

    @Override
    public int addLeadMark(leadCheckScoreVo leadCheckScoreVo) {
        save(leadCheckScoreVo);
        return 1;
    }

    @Override
    public List selectCheckPeople(int template) {
        return sqlQuery("select empId from empVo where depId = (select depId from template where templateId = "+template+")");
    }

    @Override
    public int addCheckPeople(checkPeopleVo peopleVo) {
        save(peopleVo);
        return 1;
    }

    @Override
    public int update(int templateId) {
        return sqlUpdate("update template set openCheck=2 where templateId="+templateId+" ");
    }

    @Override
    public int update2(int empId) {
        return sqlUpdate("update  checkPeople set leadState = '已考评' where empId = "+empId+"");
    }

    @Override
    public int updateClose(int template) {
        return sqlUpdate("update template set openCheck=3 where templateId="+template+"");
    }

    @Override
    public int selectLead(int empId) {
        return executeIntSQL("select empId from empVo where empName = ( select chairman from dep where depId = (select o.depId from empVo e left join dep o on e.depId = o.depId where e.empId = "+empId+" ))");
    }


    @Override
    public int OKAccount(String account, String pwd) {
        return executeIntSQL("select empId from empVo where empName = '"+account+"' and password = '"+pwd+"'");
    }
}
