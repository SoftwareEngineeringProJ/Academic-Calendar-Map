package com.academic.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 首页控制器
 */
@Controller
public class HomeController {

    // 进入主页
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getHomePage(){
        return "index";
    }



}
