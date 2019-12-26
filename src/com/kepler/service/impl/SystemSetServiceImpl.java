package com.kepler.service.impl;

import com.kepler.dao.BaseDao;
import com.kepler.service.ClassService;
import com.kepler.service.SystemSetService;
import com.kepler.vo.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2019/12/4.
 */
@Service
public class SystemSetServiceImpl extends BaseDao implements SystemSetService {
    @Override
    public List<StudentFallVo> listSystemData() {
        return hqlQuery("from StudentFallVo");
    }

    @Override
    public void AddSystem(StudentFallVo vo) {
        save(vo);
    }

    @Override
    public List<StudentFallVo> selectSystemById(int id) {
        return sqlQuery("select * from StudentFall where fallid ="+id);
    }

    @Override
    public void updateSystemData(StudentFallVo vo) {
        update(vo);
    }

    @Override
    public void deleSystemDatas(int id) {
        StudentFallVo studentFallVo = new StudentFallVo();
        studentFallVo.setFallid(id);

        delete(studentFallVo);
    }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public List<ClassTypeVo> listClassTyopeData() {
        return  hqlQuery("from ClassTypeVo");
    }

    @Override
    public void AddClassType(ClassTypeVo vo) {
        save(vo);
    }

    @Override
    public List<ClassTypeVo> selectClassById(int id) {
        return sqlQuery("select * from classType where calssTypeId ="+id);
    }

    @Override
    public void updateClassData(ClassTypeVo vo) {
        update(vo);
    }


    @Override
    public void deleClassDatas(int id) {
        ClassTypeVo classTypeVo = new ClassTypeVo();
        classTypeVo.setCalssTypeId(id);

        delete(classTypeVo);
    }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public List<ProjectVo> listProjectData() {
        return hqlQuery("from ProjectVo");
    }

    @Override
    public void AddProject(ProjectVo vo) {
        save(vo);
    }

    @Override
    public List<ProjectVo> selectProjectById(int id) {
        return sqlQuery("select * from Project where projectId ="+id);
    }

    @Override
    public void updateProjectData(ProjectVo vo) {
        update(vo);
    }

    @Override
    public void deleProjectDatas(int id) {
        ProjectVo projectVo = new ProjectVo();
        projectVo.setProjectId(id);

        delete(projectVo);
    }
//--------------------------------------------------------------------------------------------
    @Override
    public List<DeptVo> listDeptData() {
        return hqlQuery("from DeptVo");
    }

    @Override
    public void AddDept(DeptVo vo) {
        save(vo);
    }

    @Override
    public List<DeptVo> selectDeptById(int id) {
        return sqlQuery("select * from Dept where deptID ="+id);
    }

    @Override
    public void updateDeptData(DeptVo vo) {
        update(vo);
    }

    @Override
    public void deleDeptDatas(int id) {
        DeptVo deptVo = new DeptVo();
        deptVo.setDeptID(id);

        delete(deptVo);
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public List<MajorVo> listMajorData() {
        return sqlQuery("select *,(select deptName from dept d where d.deptId = m.deptId) as deptName from major m");
    }

    @Override
    public void AddMajor(MajorVo vo) {
        save(vo);
    }

    @Override
    public List<MajorVo> selectMajorById(int id) {
        return  sqlQuery("select * from Major where majorID ="+id);
    }

    @Override
    public void updateMajorData(MajorVo vo) {
            update(vo);
    }

    @Override
    public void delMajorDatas(int id) {
        MajorVo majorVo = new MajorVo();
        majorVo.setMajorID(id);
        delete(majorVo);
    }

    @Override
    public List selDept() {
        return sqlQuery("select * from dept");
    }
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public List<ApplicationSchoolVo> listSchoolData() {
        return hqlQuery("from ApplicationSchoolVo");
    }

    @Override
    public void AddSchool(ApplicationSchoolVo vo) {
            save(vo);
    }

    @Override
    public List<ApplicationSchoolVo> selectSchoolById(int id) {
        return sqlQuery("select * from ApplicationSchool where appId ="+id);
    }

    @Override
    public void updateSchoolData(ApplicationSchoolVo vo) {
            update(vo);
    }

    @Override
    public void deleSchoolDatas(int id) {
        ApplicationSchoolVo applicationSchoolVo = new ApplicationSchoolVo();
        applicationSchoolVo.setAppId(id);
        delete(applicationSchoolVo);
    }
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public List<TermVo> listTermData() {
        return hqlQuery("from TermVo");
    }

    @Override
    public void AddTerm(TermVo vo) {
        save(vo);
    }

    @Override
    public List<TermVo> selectTermById(int id) {
        return sqlQuery("select * from Term where termID ="+id);
    }

    @Override
    public void updateTermData(TermVo vo) {
        update(vo);
    }

    @Override
    public void deleTermDatas(int id) {
        TermVo termVo = new TermVo();
        termVo.setTermID(id);
        delete(termVo);
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public List<DepVo> listTreeData() {
        return sqlQuery("select depName from dep");
    }

    @Override
    public void AddTree(DepVo vo) {
        save(vo);
    }

    @Override
    public List listDeptree() {
        return sqlQuery("select * from dep");
    }

    @Override
    public List selectDep(String id) {
        return sqlQuery("select depid,depName from dep where depid ="+id);
    }

    @Override
    public List Customss(int id) {
        return sqlQuery("select * from dep where depid="+id);

    }


    @Override
    public List<TermVo> selectTreeById(int id) {
        return sqlQuery("select * from dep where depid ="+id);
    }

    @Override
    public void updateTreeData(DepVo vo) {
        update(vo);
    }

    @Override
    public void deleTreeDatas(int id) {
        DepVo depVo = new DepVo();
        depVo.setDepid(id);
        delete(depVo);
    }

    @Override
    public List<DepVo> selDep() {
        return sqlQuery("select * from dep");
    }
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public List selClass() {
        return sqlQuery("select * from StudentClass");
    }


    //------------------------------------------------------------------------------------------------------------------

    @Override
    public List<FeedbackVo> listFeedData() {
        return sqlQuery("select * from feedback");
    }

    @Override
    public void AddFeed(FeedbackVo vo) {
        save(vo);
    }

    @Override
    public List<FeedbackVo> selectFeedById(int id) {
        return sqlQuery("select * from feedback where feedbackId ="+id);
    }

    @Override
    public List<StudentVo> selectStudentById(int id) {
        return hqlQuery("from StudentVo where Studid="+id);
    }

    @Override
    public List<empVo> selectEmpById(int id) {
        return hqlQuery("from empVo where empId="+id);
    }
    @Override
    public void updateFeedData(FeedbackVo vo) {
            update(vo);
    }

    @Override
    public void deleFeedDatas(int id) {
        FeedbackVo feedbackVo = new FeedbackVo();
        feedbackVo.setFeedbackId(id);
        delete(feedbackVo);
    }

    @Override
    public void AddMessage(FeedbackMsgVo vo) {
        save(vo);
    }
    @Override
    public List<FeedbackMsgVo> selMessageById(int feedbackId){

        return sqlQuery("select * from FeedbackMsg where feedbackId="+feedbackId);
    }

    @Override
    public List selHourStudent(String huorName) {
        return sqlQuery("select s.*,h.huorName from Student s left join StudentHuor h  on s.huor = h."+"'"+huorName+"'");
    }

//    @Override
//    public List selHourStudent(String huorName) {
//        return sqlQuery("select s.*,h.huorName from Student s left join StudentHuor h  on s.huor = h."+"'"+huorName+"'");
//    }
    //------------------------------------------------------------------------------------------------------------------

}
