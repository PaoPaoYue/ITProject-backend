package com.ypp.itproject.vo.exception;

public class CheckLoginExceptionVo {

    private String msg;

    public CheckLoginExceptionVo(String msg) {
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
        return "CheckLoginExceptionVo{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
