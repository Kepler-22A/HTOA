package com.kepler.service;

import com.kepler.vo.DataDocVo;
import com.kepler.vo.EnrollmentVo;

import java.util.List;

/**
 * Created by ASUS on 2019/12/4.
 */
public interface DataService {
    //查询数据
    public List selDocData();
    //保存数据
    public void addFile(DataDocVo docVo);
    //
    public DataDocVo findById(int id);
    //
    public DataDocVo findFile(Integer docId);
    //
    public void deleteFile(DataDocVo dataDocVO);
    //查询数据
    public List selEnrollData();

    //添加
    public void addEnrllor(EnrollmentVo vo);
    //查询专业
    public List selMajor();
    //查询班级类别
    public List selClassType();
    //查询招生老师
    public List selEmp();
//
    public List<EnrollmentVo> selectEnrollById(int id);
    //根据id查找信息
    public List listEnrollbyId(Class className, int id);

    public void updateEnrollData(EnrollmentVo vo);
    //删除
    public void delEnrllor(int id );
}
