package com.ypp.itproject.jwt.core;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.ypp.itproject.jwt.JwtSubject;
import com.ypp.itproject.jwt.config.JwtCacheProperties;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JwtCache {

    public static class Record {
        JwtSubject subject;
        Date expiration;

        public Record(JwtSubject subject, Date expiration) {
            this.subject = subject;
            this.expiration = expiration;
        }

    }

    private final boolean enable;

    private Cache<String, Record> tokenCache = null;

    public JwtCache(JwtCacheProperties prop) {
        enable = prop.isEnable();
        if (enable)
            tokenCache = CacheBuilder.newBuilder().maximumSize(prop.getSize())
                    .expireAfterWrite(prop.getExpiration(), TimeUnit.MINUTES).build();
    }

    public boolean isEnable() {
        return enable;
    }

    public JwtSubject get(String token) {
        Record record = tokenCache.getIfPresent(token);
        return record == null ? null : record.subject;
    }

    public Date getExpiration(String token) {
        Record record = tokenCache.getIfPresent(token);
        return record == null ? null : record.expiration;
    }

    public void remove(String token) {
        tokenCache.invalidate(token);
    }

    public void put(String token, JwtSubject subject, Date expiration) {
        tokenCache.put(token, new Record(subject, expiration));
    }
}

