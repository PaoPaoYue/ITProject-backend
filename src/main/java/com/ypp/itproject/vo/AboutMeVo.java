package com.ypp.itproject.vo;

import com.ypp.itproject.entity.User;

import java.time.LocalDateTime;

public class AboutMeVo{
    private String email;
    private String description;
    private String location;
    private String phone;
    private String simpleDescription;
    private String education;
    private String avatar;
    private String displayName;
    private String contactFacebook;
    private String contactLinkedin;
    private String contactGithub;
    private String work;
    private String skillset;

    public AboutMeVo(User user){
        this.displayName = user.getDisplayName();
        this.phone = user.getPhone();
        this.avatar = user.getAvatar();
        this.location = user.getLocation();
        this.description = user.getDescription();
        this.simpleDescription = user.getSimpleDescription();
        this.email = user.getEmail();
        this.education = user.getEducation();
        this.contactFacebook= user.getContactFacebook();
        this.contactLinkedin= user.getContactLinkedin();
        this.contactGithub = user.getContactGithub();
        this.work = user.getWork();
        this.skillset = user.getSkillset();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSimpleDescription() {
        return simpleDescription;
    }

    public void setSimpleDescription(String simpleDescription) {
        this.simpleDescription = simpleDescription;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getSkillset() {
        return skillset;
    }

    public void setSkillset(String skillset) {
        this.skillset = skillset;
    }

    @Override
    public String toString() {
        return "User{" +
                "displayname=" + displayName +
                ", email=" + email +
                ", phone=" + phone +
                ", simple_description=" + simpleDescription +
                ", description=" + description +
                ", location=" + location +
                ", education=" + education +
                ", avatar=" + avatar +
                ", contact_facebook=" + contactFacebook +
                ", contact_linkedin=" + contactLinkedin +
                ", contact_github=" + contactGithub +
                ", work=" + work +
                ", skillset=" + skillset +
                "}";
    }

}
