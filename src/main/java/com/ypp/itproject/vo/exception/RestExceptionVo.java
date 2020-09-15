package com.ypp.itproject.vo.exception;

import com.ypp.itproject.exception.RestException;

public class RestExceptionVo {

    private int code;
    private String msg;
    private Object data;

    public RestExceptionVo(RestException e) {
        this.code = e.getCode();
        this.msg = e.getMsg();
        this.data = e.getData();
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
        return "RestExceptionVo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
