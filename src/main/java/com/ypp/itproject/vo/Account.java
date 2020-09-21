package com.ypp.itproject.vo;

import com.ypp.itproject.entity.Users;

public class Account{
    private String name;
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

    public Account(Users user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.location = user.getLocation();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Users{" +
                "name=" + name +
                ", email=" + email +
                ", location=" + location +
                ", status=" + location +
                "}";
    }

}

