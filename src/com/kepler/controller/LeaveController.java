package com.kepler.controller;

import com.kepler.service.EmpService;
import com.kepler.service.LeaveService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by ASUS on 2019/12/4.
 */
@Controller
@RequestMapping("leave")
public class LeaveController {
    @Resource
    private LeaveService ls;
}
