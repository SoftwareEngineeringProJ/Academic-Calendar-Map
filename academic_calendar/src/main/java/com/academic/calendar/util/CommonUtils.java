package com.academic.calendar.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 常用工具静态类
 */
public class CommonUtils {

    //生成随机字符串
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("_", "");
    }

    //md5：  采用linux用户登录模式
    //采用MD5对 {密码+salt} 进行散列
    public static String md5(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

    // 从cookie中获取值
    public static String getValueFromCookie(HttpServletRequest request, String name) {
        if (request == null || name == null){
            throw new IllegalArgumentException("invalid parameters");
        }
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
