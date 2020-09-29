package com.ypp.itproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ypp
 * @since 2020-09-23
 */
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    private String password;

    private String username;

    private String email;

    private Boolean status;

    private String description;

    private String location;

    private LocalDateTime createTime;

    private String tag;

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

    private String interest;

    private String award;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    @Override
    protected Serializable pkVal() {
        return this.uid;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", createTime=" + createTime +
                ", tag='" + tag + '\'' +
                ", phone='" + phone + '\'' +
                ", simpleDescription='" + simpleDescription + '\'' +
                ", education='" + education + '\'' +
                ", avatar='" + avatar + '\'' +
                ", displayName='" + displayName + '\'' +
                ", contactFacebook='" + contactFacebook + '\'' +
                ", contactLinkedin='" + contactLinkedin + '\'' +
                ", contactGithub='" + contactGithub + '\'' +
                ", work='" + work + '\'' +
                ", skillset='" + skillset + '\'' +
                ", interest='" + interest + '\'' +
                ", award='" + award + '\'' +
                '}';
    }

    public boolean isEmpty() throws IllegalAccessException {
        Boolean flag=false;
        for(Field f : this.getClass().getDeclaredFields()){
            f.setAccessible(true);
            if(f.get(this) == null || f.get(this).equals("")){
                flag = true;
                return flag;
            }
        }
        return flag;
    }
}
