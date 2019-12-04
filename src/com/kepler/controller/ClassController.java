package com.kepler.controller;

import com.kepler.service.ClassService;
import com.kepler.service.EmpService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by ASUS on 2019/12/4.
 */
@Controller
@RequestMapping("class")
public class ClassController {
    @Resource
    private ClassService cs;
}
