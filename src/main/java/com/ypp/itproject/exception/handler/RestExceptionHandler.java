package com.ypp.itproject.exception.handler;

import com.ypp.itproject.exception.RestException;
import com.ypp.itproject.vo.exception.RestExceptionVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(RestException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public Object handleRestException(RestException e) {
        return new RestExceptionVo(e);
    }
}