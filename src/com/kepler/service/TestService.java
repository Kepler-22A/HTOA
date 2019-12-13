package com.kepler.service;

import com.kepler.vo.AuditModelVo;
import com.kepler.vo.TemplateVo;

import java.util.List;

public interface TestService {
    public int selectLogin(String name,String pwd);
    public int selectInt(String name);
    public int selectInt2(String name);
    public List selectTable();
    public List selectTable2();
    public List selectTable3();
    public int addExmaine(AuditModelVo auditModelVo);
    public int addTemplate(TemplateVo templateVo);
}
