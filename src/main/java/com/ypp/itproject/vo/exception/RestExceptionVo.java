package com.ypp.itproject.vo.exception;

import com.ypp.itproject.exception.RestException;

public class RestExceptionVo {

    private int code;
    private String message;
    private Object data;

    public RestExceptionVo(RestException e) {
        this.code = e.getCode();
        this.message = e.getMessage();
        this.data = e.getData();
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RestExceptionVo{" +
                "code=" + code +
                ", msg='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
