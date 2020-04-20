package com.academic.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户个人中心，整合GoogleMap和GoogleCalendar使用的控制器
 */
@Controller
@RequestMapping(path = "user")
public class UserController {

    @RequestMapping(path = "/setting", method = RequestMethod.GET)
    public String getSettingPage() {
        return "setting";
    }

    @RequestMapping(path = "/personal-center", method = RequestMethod.GET)
    public String getPersonalPage() {
        return "personal-center";
    }



}
