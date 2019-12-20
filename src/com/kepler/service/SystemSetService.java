package com.kepler.service;

import com.kepler.vo.*;

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

    //------------------------------------------项目答辩--------------------------------------------------------------

    //查询项目答辩数据
    public List<ProjectVo> listProjectData();
    //添加项目答辩数据
    public void AddProject(ProjectVo vo);
    //根据项目答辩id查询出数据
    public List<ProjectVo> selectProjectById(int id);
    //修改项目答辩数据
    public void updateProjectData(ProjectVo vo);
    //删除项目答辩数据
    public void deleProjectDatas(int id);

    //------------------------------------------院系设置--------------------------------------------------------------
    //查询院系数据
    public List<DeptVo> listDeptData();
    //添加院系数据
    public void AddDept(DeptVo vo);
    //根据院系id查询出数据
    public List<DeptVo> selectDeptById(int id);
    //修改院系数据
    public void updateDeptData(DeptVo vo);
    //删除院系数据
    public void deleDeptDatas(int id);

    //------------------------------------------专业设置--------------------------------------------------------------
    //查询专业数据
    public List<MajorVo> listMajorData();
    //添加专业数据
    public void AddMajor(MajorVo vo);
    //根据专业id查询出数据
    public List<MajorVo> selectMajorById(int id);
    //修改专业数据
    public void updateMajorData(MajorVo vo);
    //删除专业数据
    public void delMajorDatas(int id);
    //查院系
    public List selDept();

    //------------------------------------------报考学校--------------------------------------------------------------
   //查询报考学校数据
    public List<ApplicationSchoolVo> listSchoolData();
    //添加报考学校数据
    public void AddSchool(ApplicationSchoolVo vo);
    //根据报考学校id查询出数据
    public List<ApplicationSchoolVo> selectSchoolById(int id);
    //修改报考学校数据
    public void updateSchoolData(ApplicationSchoolVo vo);
    //删除报考学校数据
    public void deleSchoolDatas(int id);
    //------------------------------------------学期设置--------------------------------------------------------------
    //查询学期数据
    public List<TermVo> listTermData();
    //添加学期数据
    public void AddTerm(TermVo vo);
    //根据学期id查询出数据
    public List<TermVo> selectTermById(int id);
    //修改学期数据
    public void updateTermData(TermVo vo);
    //删除学期数据
    public void deleTermDatas(int id);
    //------------------------------------------部门管理--------------------------------------------------------------
    //查询数据
    public List<DepVo> listTreeData();
    //添加数据
    public void AddTree(DepVo vo);
    public List listDeptree();
    public List selectDep(String id);
    //根据点击班级分配然后查询数据
    public List Customss(String cuntom);
    //根据id查询出数据
    public List<TermVo> selectTreeById(int id);
    //修改数据
    public void updateTreeData(DepVo vo);
    //删除数据
    public void deleTreeDatas(int id);

    //-----------------附属：设备维修-----------------------
    //查班级
    public List selClass();

    //-----------------附属：问题反馈-----------------------
    //查询数据
    public List<FeedbackVo> listFeedData();
    //添加数据
    public void AddFeed(FeedbackVo vo);
    //根据id查询出数据
    public List<FeedbackVo> selectFeedById(int id);
    //修改数据
    public void updateFeedData(FeedbackVo vo);
    //删除数据
    public void deleFeedDatas(int id);

    //添加留言
    public void AddMessage(FeedbackMsgVo vo);
    //根据问题id查找对应的评论
    public List<FeedbackMsgVo> selMessageById(int feedbackId);
}
