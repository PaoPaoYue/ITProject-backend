package com.ypp.itproject.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class LoginVo {

    private static final String USERNAME_PATTERN = "^\\w{3,20}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)\\w{8,20}$";

    @NotBlank @Pattern(regexp = USERNAME_PATTERN)
    private String username;
    @NotBlank @Pattern(regexp = PASSWORD_PATTERN)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginVo{" +
                "username='" + username + '\'' +
                '}';
    }
}
