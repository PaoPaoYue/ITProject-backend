package com.ypp.itproject.vo.exception;

public class CheckLoginExceptionVo {

    private String message;

    public CheckLoginExceptionVo(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "CheckLoginExceptionVo{" +
                "msg='" + message + '\'' +
                '}';
    }
}
