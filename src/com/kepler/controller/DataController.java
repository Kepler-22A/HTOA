package com.kepler.controller;

import com.kepler.service.DataService;
import com.kepler.service.EmpService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("data")
public class DataController {
    @Resource
    private DataService ds;
}
