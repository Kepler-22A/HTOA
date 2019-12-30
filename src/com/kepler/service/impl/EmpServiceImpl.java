package com.kepler.service.impl;

import com.kepler.dao.BaseDao;
import com.kepler.service.ClassService;
import com.kepler.service.EmpService;
import com.kepler.vo.*;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ASUS on 2019/12/4.
 */
@Service
public class EmpServiceImpl extends BaseDao implements EmpService {
    @Override
    public List sqlEmpDataList() {
        return sqlQuery("select\n" +
                "\t e.empId\n" +
                "\t ,e.empName\n" +
                "\t ,(select d.depName from dep d where e.depId = d.depid) as depName\n" +
                "\t ,e.postName\n" +
                "\t ,e.sex\n" +
                "\t ,e.Phone as phone\n" +
                "\t ,e.Address as address\n" +
                "\t ,e.status\n" +
                "\t ,e.password \n" +
                " from empVo e");
    }

    @Override
    public void upDataEmpStatus(int empId) {
        empVo emp = (empVo) getObject((new empVo()).getClass(),empId);
        if(emp.getStatus() == 0){
            emp.setStatus(1);
        }else {
            emp.setStatus(0);
        }

        update(emp);
    }

    @Override
    public void delEmp(int empId) {
        empVo emp = new empVo();
        emp.setEmpId(empId);
        delete(emp);

        CharEmpVo charEmp = selCharEmpVoByEmpId(empId);
        delete(charEmp);
    }

    @Override
    public void delCharEmp(int empId) {
    }

    @Override
    public List getPostName() {
        return sqlQuery("select postName from post");
    }

    @Override
    public List getNationList(String type, String position) {
        List list = new ArrayList();
        if ("省".equals(type)){
            list = sqlQuery("select cityname from city where citytype in ('省','直辖市')");
        }else if ("市".equals(type) && !"北京市".equals(position) && !"天津市".equals(position)){
            list = sqlQuery("select cityname from city where citytype in ('市') and py = '" + position + "'");
        }else if("县".equals(type) || "北京市".equals(position) || "天津市".equals(position)){
            list = sqlQuery("select cityname from city where citytype in ('区','县') and py = '" + position + "'");
        }
        return list;
    }

    @Override
    public void addEmp(empVo emp) {
        save(emp);
    }

    @Override
    public List sqlPostAndDepId(String postName) {
        return sqlQuery("select postId,depId from post where postName = '" + postName + "'");
    }

    @Override
    public empVo selEmpById(int empId) {
        return (empVo) getObject(empVo.class,empId);
    }

    @Override
    public void updataEmpVo(empVo emp) {
        update(emp);
    }

    @Override
    public int addCharEmp(int postId,int empId) {
        return sqlUpdate("insert into CharEmp values((select characterId from characters where postId = " + postId + ")," + empId + ")");
    }

    @Override
    public CharEmpVo selCharEmpVoByEmpId(int empId) {
        List list = sqlQuery("select * from CharEmp where empId = " + empId);

        CharEmpVo charEmp = new CharEmpVo();

        for (Object o : list){
            Map map = (HashMap)o;
            charEmp.setCharEmpId(Integer.parseInt(map.get("charEmpId")+""));
            charEmp.setCharacterId(Integer.parseInt(map.get("characterId")+""));
            charEmp.setEmpId(map.get("empId")+"");
        }

        return charEmp;
    }

    @Override
    public int selCharacterIdByPostId(int postId) {
        int characterId = 0;
        for (Object o : sqlQuery("select characterId from characters where postId = " + postId)){
            System.out.println(o+"");
            characterId = Integer.parseInt(((HashMap)o).get("characterId")+"");
        }

        return characterId;
    }

    @Override
    public void updateCharEmp(CharEmpVo charEmp) {
        update(charEmp);
    }

    @Override
    public List empWorkExperience(int empId) {
        return sqlQuery("select * from job where empId = " + empId);
    }

    @Override
    public List empEducation(int empId) {
        return sqlQuery("select * from education where empId = '" + empId + "'");
    }

    @Override
    public List empFamily(int empId) {
        return sqlQuery("select * from familyInfo where empId = '" + empId + "'");
    }

    @Override
    public List empAudit(int empId) {
        List list = new ArrayList();

        List alList = sqlQuery("select * from AuditLog where empID = " + empId);

        List amList = sqlQuery("select * from AuditModel");

        for (Object o : alList){
            Map map = (HashMap)o;

            for (Object os : amList){
                Map map1 = (HashMap)os;

                if(Integer.parseInt(map1.get("auditModelID")+"") == Integer.parseInt(map.get("auditModelID")+"")){
                    map.put("auditName",map1.get("auditName"));
                }
            }
            list.add(map);
        }

        return list;
    }

    @Override
    public List empAnnex(int empId) {
        return sqlQuery("select * from annex where seName = '1' and seId = '" + empId + "'");
    }

    @Override
    public void addworkExperience(JobVo job) {
        save(job);
    }

    @Override
    public void deleteWorkExprience(int jobId) {
        JobVo jobVo = new JobVo();
        jobVo.setJobId(jobId);
        delete(jobVo);
    }

    @Override
    public void updateWorkExperience(JobVo job) {
        update(job);
    }

    @Override
    public void addEducation(EducationVo education) {
        save(education);
    }

    @Override
    public void updateEducation(EducationVo education) {
        update(education);
    }

    @Override
    public void deleteEducation(int collegeid) {
        EducationVo educationVo = new EducationVo();
        educationVo.setCollegeid(collegeid);

        delete(educationVo);
    }

    @Override
    public void addFamilyInfo(FamilyInfoVo familyInfoVo) {
        save(familyInfoVo);
    }

    @Override
    public void updateFamilyInfo(FamilyInfoVo familyInfoVo) {
        update(familyInfoVo);
    }

    @Override
    public void deleteFamilyInfo(int familyId) {
        FamilyInfoVo familyInfoVo = new FamilyInfoVo();
        familyInfoVo.setFamilyId(familyId);

        delete(familyInfoVo);
    }

    @Override
    public List selWeekly() {
        return sqlQuery("select *,(select empName from empVo e where w.empId = e.empId) as empName from weekly w");
    }

    @Override
    public List selWeeklyAndDepName(int worklogid) {
        return sqlQuery("select *,(select empName from empVo e where w.empId = e.empId) as empName,(select (select depName from dep d where d.depid = e.depId) from empVo e where e.empId = w.EmpId) as depName from weekly w where worklogid = " + worklogid);
    }

    @Override
    public List selWeeklyByEmpId(int empId) {
        return sqlQuery("select *,(select empName from empVo e where w.empId = e.empId) as empName from weekly w where w.empId = '" + empId + "'");
    }

    @Override
    public void addWeekly(WeeklyVo weeklyVo) {
        save(weeklyVo);
    }

    @Override
    public void delWeekly(int weeklyLogId) {
        WeeklyVo weeklyVo = new WeeklyVo();
        weeklyVo.setWorklogid(weeklyLogId);

        delete(weeklyVo);
    }

    @Override
    public void updateWeekly(WeeklyVo weeklyVo) {
        System.out.println(weeklyVo);
        WeeklyVo hisWeeklyVo = selWeeklyVoByWeeklylogid(weeklyVo.getWorklogid());
        System.out.println(hisWeeklyVo);
        weeklyVo.setWorkday(hisWeeklyVo.getWorkday());
        weeklyVo.setEmpId(hisWeeklyVo.getEmpId());
        update(weeklyVo);
    }

    @Override
    public WeeklyVo selWeeklyVoByWeeklylogid(int weeklylogid) {
        return (WeeklyVo)getObject(WeeklyVo.class,weeklylogid);
    }

    @Override
    public List charRecord() {
        return sqlQuery("select ChatID,c.addr,c.chatDate,c.sayContent,e.empName,t.stuname from chatRecord c left join empVo e on c.teacher = e.EmpId left join \n" +
                "Student t on c.sayFace = t.Studid");
    }

    @Override
    public void AddcharRecord(ChatRecordVo vo) {
        save(vo);
    }

    @Override
    public List selectStudentID(String name) {
        return sqlQuery("select Studid from Student where stuname ="+"'"+name+"'");
    }

    @Override
    public List selectCharRecord(int id) {
        return sqlQuery("select ChatID,c.addr,c.chatDate,c.sayContent,e.empName,t.stuname from chatRecord c left join empVo e on c.teacher = e.EmpId left join Student t on c.sayFace = t.Studid where ChatID ="+id);
    }

    @Override
    public void updateCharRecord(ChatRecordVo vo) {
        update(vo);
    }

    @Override
    public void delectCharRecord(ChatRecordVo vo) {
        delete(vo);
    }

    @Override
    public List selectChecking(int id) {
        return sqlQuery("select CheckingID,NOdate,auditdate,causeReamk,state,reamk,e.empName,p.chairman from Checking c left join empVo e on c.empID = e.empId left join dep p  on c.superiorempID = p.depid where c.empID ="+ id);
    }

    @Override
    public List selectshangjiID(int id) {
        return sqlQuery("select depid from dep where depId = (select depId from post where postId = (select postId from empVo where empId ="+id+"))");
    }

    @Override
    public void addChecking(CheckingVo vo) {
        save(vo);
    }

    @Override
    public List selectXiaJiChecking(int id) {
        return sqlQuery("select CheckingID,NOdate,auditdate,causeReamk,state,reamk,e.empName from Checking c left join empVo e on c.empID = e.empId left join dep p on c.superiorempID = p.depid where p.depid = (select depid from dep where depId = (select depId from post where postId = (select postId from empVo where empId = "+id+"))) and  not e.empId =" + id);
    }

    @Override
    public void updatestate(CheckingVo vo) {
        update(vo);
    }

    @Override
    public List selectCheckings(int id) {
        return sqlQuery("select * from Checking where CheckingID ="+id);
    }

    @Override
    public List selEmpBaseByEmpId(int empId) {
        return sqlQuery("select *,(select depName from dep d where e.depId = d.depid) as depName from empVo e where empId = " + empId);
    }

    @Override
    public List selEmpEducation(int empId) {
        return sqlQuery("select * from education where empId = " + empId);
    }

    @Override
    public List selEmpJobList(int empId) {
        return sqlQuery("select * from job where EmpId = " + empId);
    }

    @Override
    public List selEmpFamilyList(int empId) {
        return sqlQuery("select * from familyInfo where empId = " + empId);
    }

    @Override
    public int checkOldPassword(int userId, String password, String userType) {
        if ("emp".equals(userType)){

            return executeIntSQL("select count(*) from empVo where empId = " + userId + " and password = '" + password + "'");
        }else {

            return executeIntSQL("select count(*) from Student where Studid = " + userId + " and password = '" + password + "'");
        }
    }

    @Override
    public int changePwd(int userId, String password, String userType) {
        if ("emp".equals(userType)){
            empVo empVo = (com.kepler.vo.empVo)getObject(com.kepler.vo.empVo.class,userId);
            empVo.setPassword(password);
            update(empVo);
        }else {
            StudentVo studentVo = (StudentVo)getObject(StudentVo.class,userId);
            studentVo.setPassword(password);
            update(studentVo);
        }
        return 1;
    }

    @Override
    public int selWeeklyNotPush(int empId) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date dt = new Date();
        Calendar rightNow = Calendar.getInstance();
        Calendar leftNow = Calendar.getInstance();
        leftNow.setTime(dt);
        rightNow.setTime(dt);
        if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == 1){
            leftNow.add(Calendar.DAY_OF_WEEK,-7);
            rightNow.add(Calendar.DAY_OF_WEEK,7);
        }else {
            leftNow.add(Calendar.DAY_OF_WEEK,-(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-2));
            rightNow.add(Calendar.DAY_OF_WEEK,(8-Calendar.getInstance().get(Calendar.DAY_OF_WEEK)));
        }
        Date dr = rightNow.getTime();
        Date dl = leftNow.getTime();
        String dls = sdf.format(dl);
        String drs = sdf.format(dr);

        dls = dls.substring(0,dls.indexOf(' ')) + " 00:00";
        drs = drs.substring(0,drs.indexOf(' ')) + " 17:00";

        int count = executeIntSQL("select count(*) from weekly where Workday > '"+dls+"' and Workday < '"+drs+"' and empId = " + empId);

        return count;

    }

    @Override
    public int selChatRecord(int empId) {
        Calendar cale = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String firstday, lastday;
        // 获取前月的第一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        firstday = format.format(cale.getTime());
        // 获取下个月第一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        lastday = format.format(cale.getTime());
        firstday = firstday + " 00:00";
        lastday = lastday + " 00:00";
        System.out.println("本月第一天和下个月第一天分别是 ： " + firstday + " and " + lastday);

        int count = executeIntSQL("select count(*) from ChatRecord where chatDate > '"+firstday+"' and chatDate < '"+lastday+"' and teacher = "+empId);

        return count;
    }
}
