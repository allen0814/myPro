package com.lyl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@RequestMapping("/test")
@Controller
public class TestPage {

    @RequestMapping("/index")
    public String hellowWorld(){
        System.out.print("访问index后台。。。");
        return "index";
    }

}
