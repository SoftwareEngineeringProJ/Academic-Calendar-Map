package com.academic.calendar.service;

import com.academic.calendar.dao.ConferenceDao;
import com.academic.calendar.domain.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 会议相关业务层
 */
@Service
public class ConferenceService {

    @Autowired
    private ConferenceDao conferenceDao;

    // 添加会议
    public Map<String, Object> addConference(Conference conference) {
        Map<String, Object> map = new HashMap<>();
        Conference conf = conferenceDao.selectConferenceByName(conference.getConference());
        if (conf != null) {
            map.put("msg", "该会议已存在，请直接检索~");
            return map;
        }
        int ans = conferenceDao.insertConference(conference);
        if (ans != 1) {
            map.put("msg", "录入会议失败，请检查连接~");
            return map;
        }
        return map;
    }

    // 检索会议


}
