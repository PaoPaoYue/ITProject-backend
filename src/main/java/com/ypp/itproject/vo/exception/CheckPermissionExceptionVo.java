package com.ypp.itproject.vo.exception;

public class CheckPermissionExceptionVo {

    private String msg;

    public CheckPermissionExceptionVo(String msg) {
        this.msg = msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "CheckPermissionExceptionVo{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
