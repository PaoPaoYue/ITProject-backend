package com.ypp.itproject.vo;

import com.ypp.itproject.entity.User;

public class Account{
    private String username;
    private String email;
    private String location;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;
    private String password;

    public Account(User user){
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.location = user.getLocation();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username=" + username +
                ", email=" + email +
                ", location=" + location +
                ", status=" + location +
                "}";
    }

}

