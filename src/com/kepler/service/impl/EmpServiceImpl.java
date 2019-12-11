package com.kepler.service.impl;

import com.kepler.dao.BaseDao;
import com.kepler.service.ClassService;
import com.kepler.service.EmpService;
import com.kepler.vo.CharEmpVo;
import com.kepler.vo.empVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2019/12/4.
 */
@Service
public class EmpServiceImpl extends BaseDao implements EmpService {
    @Override
    public List sqlEmpDataList() {
        return sqlQuery("select\n" +
                "\t e.empId\n" +
                "\t ,e.empName\n" +
                "\t ,(select d.depName from dep d where e.depId = d.depid) as depName\n" +
                "\t ,e.postName\n" +
                "\t ,e.sex\n" +
                "\t ,e.Phone as phone\n" +
                "\t ,e.Address as address\n" +
                "\t ,e.status\n" +
                "\t ,e.password \n" +
                " from empVo e");
    }

    @Override
    public void upDataEmpStatus(int empId) {
        empVo emp = (empVo) getObject((new empVo()).getClass(),empId);
        if(emp.getStatus() == 0){
            emp.setStatus(1);
        }else {
            emp.setStatus(0);
        }

        update(emp);
    }

    @Override
    public void delEmp(int empId) {
        empVo emp = new empVo();
        emp.setEmpId(empId);
        delete(emp);

        CharEmpVo charEmp = selCharEmpVoByEmpId(empId);
        delete(charEmp);
    }

    @Override
    public void delCharEmp(int empId) {
    }

    @Override
    public List getPostName() {
        return sqlQuery("select postName from post");
    }

    @Override
    public List getNationList(String type, String position) {
        List list = new ArrayList();
        if ("省".equals(type)){
            list = sqlQuery("select cityname from city where citytype in ('省','直辖市')");
        }else if ("市".equals(type) && !"北京市".equals(position) && !"天津市".equals(position)){
            list = sqlQuery("select cityname from city where citytype in ('市') and py = '" + position + "'");
        }else if("县".equals(type) || "北京市".equals(position) || "天津市".equals(position)){
            list = sqlQuery("select cityname from city where citytype in ('区','县') and py = '" + position + "'");
        }
        return list;
    }

    @Override
    public void addEmp(empVo emp) {
        save(emp);
    }

    @Override
    public List sqlPostAndDepId(String postName) {
        return sqlQuery("select postId,depId from post where postName = '" + postName + "'");
    }

    @Override
    public empVo selEmpById(int empId) {
        return (empVo) getObject(empVo.class,empId);
    }

    @Override
    public void updataEmpVo(empVo emp) {
        update(emp);
    }

    @Override
    public int addCharEmp(int postId,int empId) {
        return sqlUpdate("insert into CharEmp values((select characterId from characters where postId = " + postId + ")," + empId + ")");
    }

    @Override
    public CharEmpVo selCharEmpVoByEmpId(int empId) {
        List list = sqlQuery("select * from CharEmp where empId = " + empId);

        CharEmpVo charEmp = new CharEmpVo();

        for (Object o : list){
            Map map = (HashMap)o;
            charEmp.setCharEmpId(Integer.parseInt(map.get("charEmpId")+""));
            charEmp.setCharacterId(Integer.parseInt(map.get("characterId")+""));
            charEmp.setEmpId(map.get("empId")+"");
        }

        return charEmp;
    }

    @Override
    public int selCharacterIdByPostId(int postId) {
        int characterId = 0;
        for (Object o : sqlQuery("select characterId from characters where postId = " + postId)){
            System.out.println(o+"");
            characterId = Integer.parseInt(((HashMap)o).get("characterId")+"");
        }

        return characterId;
    }

    @Override
    public void updateCharEmp(CharEmpVo charEmp) {
        update(charEmp);
    }
}
