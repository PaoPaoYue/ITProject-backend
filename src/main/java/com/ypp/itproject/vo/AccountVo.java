package com.ypp.itproject.vo;

import com.ypp.itproject.entity.User;

import javax.validation.constraints.*;

public class AccountVo {
    private int uid;
    private String username;
    @NotBlank @Email @Size(max=255)
    private String email;
    @NotBlank @Size(max=50)
    private String displayName;
    @NotNull @Size(max=50)
    private String simpleDescription;
    @NotNull @Size(max=400)
    private String description;
    @NotNull @Size(max=2083)
    private String avatar;
    @NotBlank @Size(max=100)
    private String location;
    @NotNull @Size(max=40)
    private String phone;
    @NotNull @Size(max=2083)
    private String contactFacebook;
    @NotNull @Size(max=2083)
    private String contactLinkedin;
    @NotNull @Size(max=2083)
    private String contactGithub;

    public AccountVo() {}

    public AccountVo(User user){
        this.uid = user.getUid();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.displayName = user.getDisplayName();
        this.simpleDescription = user.getSimpleDescription();
        this.description = user.getDescription();
        this.avatar = user.getAvatar();
        this.location = user.getLocation();
        this.phone = user.getPhone();
        this.contactFacebook = user.getContactFacebook();
        this.contactLinkedin = user.getContactLinkedin();
        this.contactGithub = user.getContactGithub();
    }

    public User toUser() {
        User user = new User();
        user.setUid(this.uid);
        user.setUsername(this.username);
        user.setEmail(this.email);
        user.setDisplayName(this.displayName);
        user.setSimpleDescription(this.simpleDescription);
        user.setDescription(this.description);
        user.setAvatar(this.avatar);
        user.setLocation(this.location);
        user.setPhone(this.phone);
        user.setContactFacebook(this.contactFacebook);
        user.setContactLinkedin(this.contactLinkedin);
        user.setContactGithub(this.contactGithub);
        return user;
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

    public String getSimpleDescription() {
        return simpleDescription;
    }

    public void setSimpleDescription(String simpleDescription) {
        this.simpleDescription = simpleDescription;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContactFacebook() {
        return contactFacebook;
    }

    public void setContactFacebook(String contactFacebook) {
        this.contactFacebook = contactFacebook;
    }

    public String getContactLinkedin() {
        return contactLinkedin;
    }

    public void setContactLinkedin(String contactLinkedin) {
        this.contactLinkedin = contactLinkedin;
    }

    public String getContactGithub() {
        return contactGithub;
    }

    public void setContactGithub(String contactGithub) {
        this.contactGithub = contactGithub;
    }

    @Override
    public String toString() {
        return "AccountVo{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", displayName='" + displayName + '\'' +
                ", simpleDescription='" + simpleDescription + '\'' +
                ", description='" + description + '\'' +
                ", avatar='" + avatar + '\'' +
                ", location='" + location + '\'' +
                ", phone='" + phone + '\'' +
                ", contactFacebook='" + contactFacebook + '\'' +
                ", contactLinkedin='" + contactLinkedin + '\'' +
                ", contactGithub='" + contactGithub + '\'' +
                '}';
    }

}

