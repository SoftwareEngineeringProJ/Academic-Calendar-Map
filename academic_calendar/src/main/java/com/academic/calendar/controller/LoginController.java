package com.academic.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录所用控制器
 */
@Controller
public class LoginController {

    /**
     * 测试代码
     * @return
     */
    @RequestMapping("/test")
    @ResponseBody
    public String testSpringBoot(){
        return "springboot成功";
    }

}
