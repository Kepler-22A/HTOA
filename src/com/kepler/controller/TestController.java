package com.kepler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Controller")
public class TestController {
    @RequestMapping("/Test")
    public String toBase(){
        return "cheshi";
    }
}
