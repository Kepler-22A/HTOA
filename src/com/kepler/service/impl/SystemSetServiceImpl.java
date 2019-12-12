package com.kepler.service.impl;

import com.kepler.dao.BaseDao;
import com.kepler.service.ClassService;
import com.kepler.service.SystemSetService;
import com.kepler.vo.ClassTypeVo;
import com.kepler.vo.DeptVo;
import com.kepler.vo.ProjectVo;
import com.kepler.vo.StudentFallVo;
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
}
