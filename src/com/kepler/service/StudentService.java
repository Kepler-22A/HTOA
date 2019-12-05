package com.kepler.service;

import com.kepler.vo.StudentFloorVo;
import com.kepler.vo.StudentHuorVo;

import java.util.List;
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
    public List listbyId(int id);
    //楼栋管理
    public  List<StudentFloorVo> listfloor();
}
