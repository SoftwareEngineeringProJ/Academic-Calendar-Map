package com.academic.calendar.util;

import org.springframework.stereotype.Component;

@Component
public class RedisKeyUtil {

    private static final String SPLIT = ":";
    private static final String PREFIX_CODE = "code";
    private static final String PREFIX_UV = "uv";
    private static final String PREFIX_DAU = "dau";

    // 找回密码 验证码  code:email  -->  value
    public static String getCodeKey(String email) {
        return PREFIX_CODE + SPLIT + email;
    }

    // 单日uv uv:date   --> hyperloglog
    public static String getUVKey(String date) {
        return PREFIX_UV + SPLIT + date;
    }

    // 多日uv  uv:startDate:endDate  --> hyperloglog
    public static String getUVKey(String startDate, String endDate) {
        return PREFIX_UV + SPLIT + startDate + SPLIT + endDate;
    }

    // 单日活跃用户 dau:date --> bitmap
    public static String getDAUKey(String date) {
        return PREFIX_DAU + SPLIT + date;
    }

    // 多日活跃用户  dau:startDate:endDate --> bitmap
    public static String getDAUKey(String startDate, String endDate) {
        return PREFIX_DAU + SPLIT + startDate + SPLIT + endDate;
    }

}
