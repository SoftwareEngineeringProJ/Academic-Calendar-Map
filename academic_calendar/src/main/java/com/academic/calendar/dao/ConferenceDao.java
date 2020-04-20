package com.academic.calendar.dao;

import com.academic.calendar.domain.Conference;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.Date;

/**
 * 涉及到动态sql，所以这里不采用注解方式了，采用xml更清晰
 */
@Mapper
public interface ConferenceDao {

    // 手动录入会议
    int insertConference(Conference conference);

    // 根据种类查询
    Conference selectConferenceByCate(String category);

    // 根据会议名查询
    Conference selectConferenceByName(String conference);

    // 查询所有
    Conference selectAllConference();

    // 根据时间查询
    Conference selectConferenceByTime(Date startTime, Date endTime, Date ddl);

}
