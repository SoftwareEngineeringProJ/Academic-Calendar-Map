package com.academic.calendar.controller;

import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.desktop.QuitEvent;
import java.util.LinkedList;
import java.util.Queue;

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

    // 进入关于我们，涉及锚点，需要重定向
    @RequestMapping(path = "/aboutUs", method = RequestMethod.GET)
    public String getAboutUsPage() {
        return "redirect:/index#aboutus";
    }


}
