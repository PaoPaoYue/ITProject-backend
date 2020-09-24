package com.ypp.itproject.vo;

import com.ypp.itproject.entity.User;

public class AccountVo {
    private String displayName;
    private String email;
    private String location;
    private String description;

    private String status;
    private String password;

    public AccountVo(User user){
        this.displayName = user.getDisplayName();
        this.email = user.getEmail();
        this.location = user.getLocation();
        this.description = user.getDescription();
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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


    public String getStatus() {
        return status;
    }


    @Override
    public String toString() {
        return "User{" +
                "displayname=" + displayName +
                ", email=" + email +
                ", location=" + location +
                ", description=" + description +
                ", status=" + status +
                "}";
    }

}

