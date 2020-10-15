package com.ypp.itproject.service;

import java.util.TreeMap;

public interface ICosService {
    TreeMap<String, Object> getImgSecret(int uid);
    TreeMap<String, Object> getFileSecret(int uid);
    String getImgPrefix();
    String getFilePrefix();
}
