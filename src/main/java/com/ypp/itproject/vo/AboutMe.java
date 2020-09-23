package com.ypp.itproject.vo;

import com.ypp.itproject.entity.User;

public class AboutMe{
    private String displayname;
    private String email;
    private String description;
    private String simpleDescription;
    private String location;
    private String education;
    private String avatar;
    private String contactFacebook;
    private String contactLinkedin;
    private String contactGithub;
    private String work;
    private String skillSet;

    public AboutMe(User user){
        this.displayname = user.getDisplayName();
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
        this.skillSet = user.getSkillset();
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
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

    public String getSimpleDescription() {
        return simpleDescription;
    }

    public void setSimpleDescription(String simpleDescription) {
        this.simpleDescription = simpleDescription;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(String skillSet) {
        this.skillSet = skillSet;
    }

    @Override
    public String toString() {
        return "User{" +
                "display_name=" + displayname +
                ", email=" + email +
                ", description=" + description +
                ", location=" + location +
                ", simple_description=" + description +
                ", education=" + education +
                ", avatar=" + avatar +
                ", contact_facebook=" + contactFacebook +
                ", contact_linkedin=" + contactLinkedin +
                ", contact_github=" + contactGithub +
                ", work=" + work +
                ", skillset=" + skillSet +
                "}";
    }

}
