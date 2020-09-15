package com.ypp.itproject.vo;

import com.ypp.itproject.jwt.JwtSubject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentAuthVo implements JwtSubject {

    private int id;
    private String username;

    public StudentAuthVo() {
    }

    public StudentAuthVo(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "StudentAuthVo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public List<String> getPermissions() {
        return Arrays.asList("student");
    }

    @Override
    public void setClaims(Map<String, Object> claims) {
        id = (int) claims.get("id");
        username = (String) claims.get("username");
    }

    @Override
    public Map<String, Object> getClaims() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("username", username);
        return map;
    }
}
