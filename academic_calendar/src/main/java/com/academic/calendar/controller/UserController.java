package com.academic.calendar.controller;

import com.academic.calendar.entity.Conference;
import com.academic.calendar.entity.Page;
import com.academic.calendar.entity.User;
import com.academic.calendar.service.SaveService;
import com.academic.calendar.service.UserService;
import com.academic.calendar.util.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户个人中心，整合GoogleMap和GoogleCalendar使用的控制器
 */
@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private SaveService saveService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserHolder userHolder;

    @RequestMapping(path = "/setting", method = RequestMethod.GET)
    public String getSettingPage(Model model) {
        User user = userHolder.getUser();
        model.addAttribute("userId", user.getUserId());
        return "setting";
    }

    @RequestMapping(path = "/setting", method = RequestMethod.POST)
    public String modifyPassword(Model model, String oldPwd, String newPwd) {
        User user = userHolder.getUser();
        Map<String, Object> map = userService.editPassword(user.getUserId(), oldPwd, newPwd);
        model.addAttribute("passwordMsg", map.get("passwordMsg"));
        return "setting";
    }

    // 进入主页，同时显示收藏的会议
    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public String getPersonalPage(Model model, Page page) {
        User user = userHolder.getUser();
        page.setRows(saveService.findSaveRows(user.getUserId()));
        page.setPath("/user/profile");
        List<Conference> conferenceByUser = saveService.findConferenceByUser(user.getUserId(), page.getOffset(), page.getLimit());
        model.addAttribute("conference", conferenceByUser);
        return "profile";
    }



}
