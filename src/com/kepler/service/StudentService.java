package com.kepler.service;

import com.kepler.vo.StudentFloorVo;
import com.kepler.vo.StudentHuorVo;
import com.kepler.vo.StudentVo;

import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2019/12/4.
 */
/*
*
* */
public interface StudentService {
    //宿舍管理
    public List<StudentHuorVo>  listhour();
    //添加宿舍信息
    public void addhour(StudentHuorVo studentHuorVo);
    //修改宿舍信息
    public void updatehour(StudentHuorVo studentHuorVo);
    //根据id查找宿舍信息
    public List listbyId(Class className, int id);
    //删除宿舍资料
    public void deletehour(int id);
    //楼栋管理
    public  List<StudentFloorVo> listfloor();
   //添加楼栋
    public void addfloor(StudentFloorVo studentFloorVo);
    //删除楼栋
    public void deletefloor(int id);
    //查询学生资料
    public List<StudentVo> liststudentdata();
}
