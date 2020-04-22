package com.academic.calendar.controller;

import com.academic.calendar.entity.User;
import com.academic.calendar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 登录、注册、忘记密码 所用控制器
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;


    // 进入注册页面
    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String getRegisterPage(){
        return "register";
    }

    //注册
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register(Model model, User user) {
        Map<String, Object> map;
        map = userService.register(user);
        if (map == null || map.isEmpty()) {
            model.addAttribute("msg", "注册成功");
            model.addAttribute("target", "/index");
            return "register-result";
        }
        else {
            model.addAttribute("usernameMsg", map.get("usernameMsg"));
            model.addAttribute("passwordMsg", map.get("passwordMsg"));
            model.addAttribute("emailMsg", map.get("emailMsg"));
            return "register";
        }
    }


    // 进入登录页面
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String getLoginPage(){
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(String username, String password, Model model, HttpSession session, HttpServletResponse response) {
        int expiredSeconds = 3600 * 24;
        Map<String, Object> map = userService.login(username, password, expiredSeconds);
        if (map.containsKey("ticket")) {
            Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
            cookie.setPath("");
            cookie.setMaxAge(expiredSeconds);
            response.addCookie(cookie);
            return "redirect:/index";
        }else{
            model.addAttribute("usernameMsg", map.get("usernameMsg"));
            model.addAttribute("passwordMsg", map.get("passwordMsg"));
            return "login";
        }
    }

    // 登出
    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(@CookieValue("ticket") String ticket){
        userService.logout(ticket);
        return "redirect:/login";
    }

    //忘记密码，邮箱找回
    @RequestMapping(path = "/forget", method = RequestMethod.GET)
    public String findBack() {
        return "findback-password";
    }

}
