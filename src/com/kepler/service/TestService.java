package com.kepler.service;

import com.kepler.vo.AuditModelVo;

import java.util.List;

public interface TestService {
    public int selectLogin(String name,String pwd);
    public List selectTable();
    public List selectTable2();
    public List selectTable3();
    public int addExmaine(AuditModelVo auditModelVo);
}
