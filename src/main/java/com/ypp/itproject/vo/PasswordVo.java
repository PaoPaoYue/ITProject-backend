package com.ypp.itproject.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class PasswordVo {

    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)\\w{8,20}$";

    @NotBlank
    @Pattern(regexp = PASSWORD_PATTERN)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PasswordVo{}";
    }

}
