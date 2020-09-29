package com.ypp.itproject.vo;

import com.ypp.itproject.entity.User;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class AboutMeVo{
    @NotBlank
    private String education;
    @NotBlank
    private String work;
    @NotBlank
    private String skillset;
    @NotBlank
    private String interest;
    @NotBlank
    private String award;

    public AboutMeVo() {}

    public AboutMeVo(User user){
        this.education = user.getEducation();
        this.work = user.getWork();
        this.skillset = user.getSkillset();
        this.interest = user.getInterest();
        this.award = user.getAward();
    }

    public User toUser() {
        User user = new User();
        user.setEducation(this.education);
        user.setWork(this.work);
        user.setSkillset(this.skillset);
        user.setInterest(this.interest);
        user.setAward(this.award);
        return user;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
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
    public String toString() {
        return "AboutMeVo{" +
                "education='" + education + '\'' +
                ", work='" + work + '\'' +
                ", skillset='" + skillset + '\'' +
                ", interest='" + interest + '\'' +
                ", award='" + award + '\'' +
                '}';
    }

}
