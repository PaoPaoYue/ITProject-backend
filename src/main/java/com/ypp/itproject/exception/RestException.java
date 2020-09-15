package com.ypp.itproject.exception;

public class RestException extends Exception {

    private int code;
    private String msg;
    private Object data;

    public RestException(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public RestException(int code, String msg, Object data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return this.code + " : " + this.getMsg();
    }

}
