package com.kepler.service.impl;

import com.kepler.dao.BaseDao;
import com.kepler.service.DataService;
import com.kepler.vo.DataDocVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2019/12/4.
 */
@Service
public class DataServiceImpl extends BaseDao implements DataService {
    @Override
    public List selDocData() {
        return sqlQuery("select d.*,e.empName from dataDoc d left join empVo e on d.empid = e.empId");
    }

    @Override
    public void addFile(DataDocVo docVo) {
        save(docVo);
    }

    @Override
    public DataDocVo findById(int id) {
        return (DataDocVo)getObject(DataDocVo.class ,id);
    }

    @Override
    public DataDocVo findFile(Integer docId) {
        return (DataDocVo) getObject(DataDocVo.class,docId);
    }

    @Override
    public void deleteFile(DataDocVo dataDocVO) {
        delete(dataDocVO);
    }
}
