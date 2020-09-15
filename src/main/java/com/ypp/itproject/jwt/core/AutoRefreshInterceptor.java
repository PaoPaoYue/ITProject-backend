package com.ypp.itproject.jwt.core;

import com.auth0.jwt.JWT;
import com.ypp.itproject.jwt.JwtSubject;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class AutoRefreshInterceptor implements HandlerInterceptor {

    private final JwtManager manager;

    public AutoRefreshInterceptor(JwtManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = manager.getToken(request);
        if (token != null && new Date().getTime() > JWT.decode(token).getExpiresAt().getTime()) {
            manager.verify(token);
            JwtSubject subject = manager.extract(request);
            if (subject != null)
                manager.issue(subject, response);
        }
        return true;
    }
}
