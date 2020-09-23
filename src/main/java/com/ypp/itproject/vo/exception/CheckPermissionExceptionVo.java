package com.ypp.itproject.vo.exception;

public class CheckPermissionExceptionVo {

    private String message;

    public CheckPermissionExceptionVo(String message) {
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
        return "CheckPermissionExceptionVo{" +
                "msg='" + message + '\'' +
                '}';
    }
}
