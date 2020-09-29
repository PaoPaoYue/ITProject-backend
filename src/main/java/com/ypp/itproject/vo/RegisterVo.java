package com.ypp.itproject.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegisterVo {

    private static final String USERNAME_PATTERN = "^\\w{3,20}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)\\w{8,20}$";

    @NotBlank @Pattern(regexp = USERNAME_PATTERN)
    private String username;
    @NotBlank @Email @Size(max = 255)
    private String email;
    @NotBlank @Pattern(regexp = PASSWORD_PATTERN)
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegisterVo{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
