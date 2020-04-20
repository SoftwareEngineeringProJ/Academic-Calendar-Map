package com.academic.calendar.controller;


import com.academic.calendar.domain.Conference;
import com.academic.calendar.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 会议检索 添加 控制器
 */
@RequestMapping(path = "/conference")
@Controller
public class ConferenceController {

    @Autowired
    private ConferenceService conferenceService;

    // 获取查询会议界面
    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public String getSearchConferencePage(){
        return "conference";
    }

    // 获取添加会议
    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String getAddConferencePage() {
        return "add.html";
    }

    // 手动录入会议
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String doAddConference(Model model, Conference conference) {
        Map<String, Object> map = new HashMap<>();
        map = conferenceService.addConference(conference);
        if (map == null || map.isEmpty()) {
            model.addAttribute("msg", "录入会议成功");
            return "add";
        } else {
            model.addAttribute("msg", map.get("msg"));
            return "add";
        }
    }


}
