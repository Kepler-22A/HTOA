package com.kepler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ASUS on 2019/12/4.
 */
@Controller
@RequestMapping("student")
public class StundetContorller {

    @RequestMapping("/test2")
    public String test2(){
        return "studentHour";
    }
}
