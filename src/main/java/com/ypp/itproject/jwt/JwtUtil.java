package com.ypp.itproject.jwt;

import com.ypp.itproject.jwt.core.JwtManager;
import com.ypp.itproject.jwt.core.WebMvcUtil;


public class JwtUtil {

    private static JwtManager manager;

    static void setManager(JwtManager jwtManager) {
        manager = jwtManager;
    }

    public static String issue(JwtSubject subject) {
        return manager.issue(subject);
    }

    public static JwtSubject extract() {
        return manager.extract(WebMvcUtil.getRequest());
    }


}
