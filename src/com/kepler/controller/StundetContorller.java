package com.kepler.controller;

import com.kepler.service.EmpService;
import com.kepler.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by ASUS on 2019/12/4.
 */
@Controller
@RequestMapping("student")
public class StundetContorller {

    @Resource
    private StudentService sts;

    @RequestMapping("/test2")
    public String test2(){
        return "studentHour";
    }
}
