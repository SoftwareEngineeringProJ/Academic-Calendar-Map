package com.academic.calendar.service;

import com.academic.calendar.dao.LoginTicketDao;
import com.academic.calendar.dao.UserDao;
import com.academic.calendar.domain.LoginTicket;
import com.academic.calendar.domain.User;
import com.academic.calendar.util.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户业务层
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private LoginTicketDao loginTicketDao;



    //注册业务
    public Map<String, Object> register(User user) {
        Map<String, Object> map = new HashMap<>();
        if (user == null) {
            throw new IllegalArgumentException("invalid parameters");
        }
        if (StringUtils.isBlank(user.getUsername()))
        {
            map.put("usernameMsg", "账号不能为空");
            return map;
        }
        if (StringUtils.isBlank(user.getPassword()))
        {
            map.put("passwordMsg", "密码不能为空");
            return map;
        }
        if (StringUtils.isBlank(user.getEmail()))
        {
            map.put("emailMsg", "邮箱不能为空");
            return map;
        }
        //判断账号和邮箱是否已存在
        User tmp = userDao.selectByName(user.getUsername());
        if (tmp != null) {
            map.put("usernameMsg", "该账号已存在");
            return map;
        }
        tmp = userDao.selectByEmail(user.getEmail());
        if (tmp != null) {
            map.put("emailMsg", "该邮箱已被注册");
            return map;
        }
        //无已存在 开始注册
        user.setSalt(CommonUtils.generateUUID().substring(0, 5));
        user.setPassword(CommonUtils.md5(user.getPassword() + user.getSalt()));
        userDao.insertUser(user);
        return map;
    }

    // 登录业务
    public Map<String, Object> login(String username, String password, int expiredSeconds){
        Map<String, Object> map = new HashMap<>();
        //1. 空值处理
        if (StringUtils.isBlank(username)){
            map.put("usernameMsg", "账号不能为空");
            return map;
        }
        if (StringUtils.isBlank(password)){
            map.put("passwordMsg", "密码不能为空");
            return map;
        }
        User user = userDao.selectByName(username);
        if (user == null){
            map.put("usernameMsg", "该账号不存在");
            return map;
        }
        // 验证密码
        password =CommonUtils.md5(password + user.getSalt());
        if (!user.getPassword().equals(password)){
            map.put("passwordMsg", "密码错误");
            return map;
        }
        //登录成功，录入凭证
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(user.getUserId());
        loginTicket.setTicket(CommonUtils.generateUUID());
        loginTicket.setStatus(1);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + expiredSeconds * 1000));
        loginTicketDao.insertLoginTicket(loginTicket);
        map.put("ticket", loginTicket.getTicket());
        return map;
    }


    //退出登录，status置0
    public void logout(String ticket){
        loginTicketDao.updateStatus(ticket, 0);
    }

    // 查询loginticket，简单来说就是作为业务层中转
    public LoginTicket findLoginTicket(String ticket) {
        return loginTicketDao.selectByTicket(ticket);
    }

    // 查询User，简单来说就是作为业务层中转
    public User findUserById(int id) {
        return userDao.selectById(id);
    }
}
