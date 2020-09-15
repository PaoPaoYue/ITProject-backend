package com.ypp.itproject.jwt.core;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ypp.itproject.jwt.JwtSubject;
import com.ypp.itproject.jwt.config.JwtProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@Component
public class JwtManager {

    private final JwtProperties prop;

    private final JwtCache cache;

    public JwtManager(JwtProperties prop) {
        this.prop = prop;
        cache = new JwtCache(prop.getCache());
    }

    public String issue(JwtSubject subject) {
        Date expiration = new Date(System.currentTimeMillis() + prop.getMaxAliveMinute() * 60 * 1000);
        Algorithm algorithm = Algorithm.HMAC256(prop.getSecret());
        String token = JWT.create()
                .withSubject(subject.getClass().getName())
                .withClaim(prop.getTokenDataName(), subject.getClaims())
                .withExpiresAt(expiration)
                .sign(algorithm);
        if (cache.isEnable())
            cache.put(token, subject, expiration);
        return token;
    }

    public String issue(JwtSubject subject, HttpServletResponse response) {
        String token = issue(subject);
        setToken(response, token);
        return token;
    }

    public JwtSubject extract(String token) {
        JwtSubject subject = null;
        if (token == null)
            return null;
        if (cache.isEnable())
            subject = extractFromCache(token);
        if (subject == null)
            subject = extractFromToken(token);
        return subject;
    }

    public JwtSubject extract(HttpServletRequest request) {
        return extract(getToken(request));
    }

    public boolean verify(String token) {
        if (cache.isEnable() && extractFromCache(token) != null) {
            if (new Date().getTime() <= cache.getExpiration(token).getTime() + prop.getRefreshWindow() * 60 *1000) {
                return true;
            } else {
                cache.remove(token);
                return false;
            }
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(prop.getSecret());
            JWTVerifier verifier = JWT.require(algorithm)
                    .acceptExpiresAt(this.prop.getMaxIdleMinute() * 60)
                    .build();
            verifier.verify(token);
            if (cache.isEnable())
                cache.put(token, extractFromToken(token), JWT.decode(token).getExpiresAt());
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public void setToken(HttpServletResponse response, String token) {
        response.setHeader("refresh-token", token);
    }

    public String getToken(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if (auth!=null && auth.startsWith("Bearer ")) {
            return auth.substring(7);
        }
        return null;
    }

    private JwtSubject extractFromToken(String token) {
        JwtSubject subject;
        Map<String, Object> data;
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            Map<String, Claim> claims = decodedJWT.getClaims();
            String subjectName = claims.get("sub").asString();
            data = claims.get(prop.getTokenDataName()).asMap();
            Class<?> clazz = Class.forName(subjectName);
            subject = (JwtSubject) clazz.newInstance();
        } catch (Exception e) {
            return null;
        }

        if (data != null)
            subject.setClaims(data);
        return subject;
    }

    private JwtSubject extractFromCache(String token) {
        return cache.get(token);
    }
}
