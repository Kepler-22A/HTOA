package com.kepler.service.impl;

import com.kepler.dao.BaseDao;
import com.kepler.service.ClassService;
import com.kepler.service.SystemSetService;
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
}
