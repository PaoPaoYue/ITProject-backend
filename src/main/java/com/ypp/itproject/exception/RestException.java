package com.ypp.itproject.exception;

public class RestException extends RuntimeException {

    private int code;
    private String message;
    private Object data;

    public RestException(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public RestException(int code, String message, Object data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
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
        return this.code + " : " + this.getMessage();
    }

}
