package com.kepler.service;

import com.kepler.vo.ClassTypeVo;
import com.kepler.vo.StudentFallVo;

import java.util.List;

/**
 * Created by ASUS on 2019/12/4.
 */
public interface SystemSetService {
    //---------------------------------------届别设置--------------------------------------------------------
    //查询届别数据
    public List<StudentFallVo> listSystemData();
    //添加届别数据
    public void AddSystem(StudentFallVo vo);
    //根据届别id查询出数据
    public List<StudentFallVo> selectSystemById(int id);
    //修改届别数据
    public void updateSystemData(StudentFallVo vo);
    //删除届别数据
    public void deleSystemDatas(int id);

    //-----------------------------------------班级类别-------------------------------------------------------

    //查询班级类别数据
    public List<ClassTypeVo> listClassTyopeData();
    //添加班级类别数据
    public void AddClassType(ClassTypeVo vo);
    //根据班级类别id查询出数据
    public List<ClassTypeVo> selectClassById(int id);
    //修改班级类别数据
    public void updateClassData(ClassTypeVo vo);
    //删除班级类别数据
    public void deleClassDatas(int id);
}
