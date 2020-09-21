package com.ypp.itproject.vo;

import com.ypp.itproject.entity.Users;

public class AboutMe{
    private String displayname;
    private String email;
    private String description;
    private String simpleDescription;
    private String location;
    private String education;
    private String profileImageLink;
    private String contactFacebook;
    private String contactLinkedin;
    private String contactGithub;
    private String work;
    private String skillSet;

    public AboutMe(Users user){
        this.displayname = user.getDisplayname();
        this.profileImageLink = user.getProfileImageLink();
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

    public String getProfileImageLink() {
        return profileImageLink;
    }

    public void setProfileImageLink(String profileImageLink) {
        this.profileImageLink = profileImageLink;
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
        return "Users{" +
                "displayname=" + displayname +
                ", email=" + email +
                ", description=" + description +
                ", location=" + location +
                ", simpleDescription=" + description +
                ", education=" + education +
                ", profileImageLink=" + profileImageLink +
                ", contactFacebook=" + contactFacebook +
                ", contactLinkedin=" + contactLinkedin +
                ", contactGithub=" + contactGithub +
                ", work=" + work +
                ", skillset=" + skillSet +
                "}";
    }

}
