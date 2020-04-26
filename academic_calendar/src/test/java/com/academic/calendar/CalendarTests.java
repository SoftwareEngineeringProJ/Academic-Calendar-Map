package com.academic.calendar;

import com.academic.calendar.entity.Conference;
import com.academic.calendar.service.CalendarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = AcadCalMapApplication.class)
public class CalendarTests {
    @Autowired
    private CalendarService calendarService;

    @Test
    public void testCalendar() throws IOException {
        Conference c = new Conference();
        c.setId(121);
        c.setConference("HITWxH 2020:xxxxxxxxxxxxxsacxxxxxxxxxxxx");
        c.setStartTime("2020-5-29");
        c.setEndTime("2020-06-02");
        c.setLocation("suzhou,China");
        calendarService.grabEvents(c);
    }
}
