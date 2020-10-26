package com.ypp.itproject.service.impl;

import com.ypp.itproject.service.IRedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements IRedisService {

    private final static long VALUE_EXPIRE = 10;
    private final static String LIST_NAME = "message::list";

    private final RedisTemplate redisTemplate;

    public RedisServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void setInteger(String key, Integer value) {
        redisTemplate.opsForValue().set(key, value, VALUE_EXPIRE, TimeUnit.MINUTES);
    }

    @Override
    public Integer getInteger(String key) {
        return (Integer) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void deleteInteger(String key) {
        redisTemplate.delete(key);
    }


    @Override
    public <T> void enqueue(T object) {
        redisTemplate.opsForList().rightPush(LIST_NAME, object);
    }

    @Override
    public <T> T dequeue() {
        return (T) redisTemplate.opsForList().leftPop(LIST_NAME);
    }

    @Override
    public <T> List<T> getBatch(int limit) {
        List<T> res = (java.util.List<T>) redisTemplate.opsForList().range(LIST_NAME, 0, limit-1);
        if(res.size() > 0) redisTemplate.opsForList().trim(LIST_NAME, res.size(), -1);
        return res;
    }

    @Override
    public void clearQueue() {
        redisTemplate.delete(LIST_NAME);
    }
}
