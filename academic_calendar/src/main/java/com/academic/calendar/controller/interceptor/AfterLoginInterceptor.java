package com.academic.calendar.controller.interceptor;


import com.academic.calendar.entity.LoginTicket;
import com.academic.calendar.entity.User;
import com.academic.calendar.service.NoticeService;
import com.academic.calendar.service.UserService;
import com.academic.calendar.util.CommonUtils;
import com.academic.calendar.util.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 处理是否已登录 的拦截器
 */
@Component
public class AfterLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserHolder userHolder;
    @Autowired
    private UserService userService;
    @Autowired
    private NoticeService noticeService;

    //控制器执行前，获取user(方法是先从cookie中获取ticket，再根据ticket获取user)
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ticket = CommonUtils.getValueFromCookie(request, "ticket");
        if (ticket != null) {
            // 处理 ticket伪造，ticket不存在
            LoginTicket loginTicket = userService.findLoginTicket(ticket);
            // 若对应ticket已登录状态
            if (loginTicket != null && loginTicket.getStatus() == 1 && loginTicket.getExpired().after(new Date())) {
                User user = userService.findUserById(loginTicket.getUserId());
                userHolder.setUser(user);
            }
        }
        return true;
    }

    //该方法在执行器之后，模板之前运行，默认有modelandview对象，于是可以用来给模板装user
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user = userHolder.getUser();
        if (user != null && modelAndView != null) {
            modelAndView.addObject("alreadyLoginUser", user);
        }
        int unreadRows = 0;
        if (user != null && modelAndView != null) {
            unreadRows = noticeService.findUnreadRows(user.getUserId());
            modelAndView.addObject("unreadRows", unreadRows);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        userHolder.clear();
    }
}
