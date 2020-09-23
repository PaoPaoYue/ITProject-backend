package com.ypp.itproject.vo;

import com.ypp.itproject.jwt.JwtSubject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersAuthVo implements JwtSubject {
    private int uid;
    private String username;

    public UsersAuthVo() {

    }
    public UsersAuthVo(int uid, String username){
        this.uid=uid;
        this.username=username;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UsersAuthVo{" +
                "uid=" + uid +
                ", username='" + username + "\'" +
                "}";
    }

    @Override
    public List<String> getPermissions() {
        return Arrays.asList("user");
    }

    @Override
    public void setClaims(Map<String, Object> claims) {
        uid = (int) claims.get("uid");
        username = (String) claims.get("username");
    }

    @Override
    public Map<String, Object> getClaims() {
        Map<String, Object> map = new HashMap<>();
        map.put("uid",uid);
        map.put("username", username);
        return map;
    }
}


