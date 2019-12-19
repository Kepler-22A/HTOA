package com.kepler.service;

import com.kepler.vo.AuditModelVo;
import com.kepler.vo.TemplateVo;
import com.kepler.vo.checkProjectVo;
import com.kepler.vo.checkStepVo;
import com.kepler.vo.checkResultVo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface TestService {
    public int selectLogin(String name, String pwd);//登录
    public int selectInt(String name);
    public int selectInt2(String name);
    //    public int selectInt3(Date name);
    public List selectTable();//数据
    public List selectTable2();
    public List selectTable3();
    public int addExmaine(AuditModelVo auditModelVo);
    public int addTemplate(TemplateVo templateVo);
    public  int addCheckProject(checkProjectVo checkProjectVo);
    public int addCheckSetp(checkStepVo setpVo);
    public int addCheckResult(checkResultVo ResultVo);
    public List selectProject(int templateId);
    public List selectSetp(int templateId);
    public  List selectResult(int templateId);
    //判断登陆这个账号的人是憨憨老师还是帅气学生
    public int OKAccount(String account,String pwd);


}
