package com.ypp.itproject.vo;

import com.ypp.itproject.entity.Users;

public class AboutMe{
    private String displayName;
    private String email;
    private String description;
    private String location;
    private String education;
    private String profileImageLink;
    private String fb;
    private String linkedin;
    private String github;
    private String work;
    private String skillSet;

    public AboutMe(Users user){
        this.displayName = user.getDisplayname();
        this.profileImageLink = user.getProfileImageLink();
        this.location = user.getLocation();
        this.description = user.getDescription();
        this.email = user.getEmail();
        this.education = user.getEducation();
        this.github= user.getContactGithub();
        this.fb= user.getContactFacebook();
        this.linkedin= user.getContactLinkedin();
        this.github = user.getContactGithub();
        this.work = user.getWork();
        this.skillSet = user.getSkillset();
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
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
                "displayname=" + displayName +
                ", email=" + email +
                ", description=" + description +
                ", location=" + location +
                ", simpleDescription=" + description +
                ", education=" + education +
                ", profileImageLink=" + profileImageLink +
                ", contactFacebook=" + fb +
                ", contactLinkedin=" + linkedin +
                ", contactGithub=" + github +
                ", work=" + work +
                ", skillset=" + skillSet +
                "}";
    }

}
