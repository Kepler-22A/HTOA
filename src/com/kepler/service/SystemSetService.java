package com.kepler.service;

import com.kepler.vo.StudentFallVo;

import java.util.List;

/**
 * Created by ASUS on 2019/12/4.
 */
public interface SystemSetService {
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
}
