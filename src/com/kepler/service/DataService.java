package com.kepler.service;

import com.kepler.vo.DataDocVo;

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
}
