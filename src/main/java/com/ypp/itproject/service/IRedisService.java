package com.ypp.itproject.service;

import java.util.List;

public interface IRedisService {
    void setInteger(String key, Integer value);
    Integer getInteger(String key);
    void deleteInteger(String key);

    <T> void enqueue(T object);
    <T> T dequeue();
    <T> List<T> getBatch(int limit);
    void clearQueue();
}
