package com.kepler.service.impl;

import com.kepler.dao.BaseDao;
import com.kepler.service.StudentService;
import com.kepler.vo.StudentFloorVo;
import com.kepler.vo.StudentHuorVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2019/12/4.
 */
/*
*
* */
@Service
public class StudentServiceImpl extends BaseDao implements StudentService {


    @Override
    public List<StudentHuorVo> listhour() {
        return hqlQuery("from StudentHuorVo");
    }

    @Override
    public void addhour(StudentHuorVo studentHuorVo) {
        save(studentHuorVo);
    }

    @Override
    public void updatehour(StudentHuorVo studentHuorVo) {
        update(studentHuorVo);
    }

    @Override
    public List listbyId(int id) {
        return null;
    }

    @Override
    public List<StudentFloorVo> listfloor() {
        return hqlQuery("from StudentFloorVo");
    }
}
