package com.ypp.itproject.jwt.exception.handler;

import com.ypp.itproject.jwt.IJwtExceptionService;
import com.ypp.itproject.jwt.exception.CheckPermissionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CheckPermissionExceptionHandler {

    private IJwtExceptionService service;

    public CheckPermissionExceptionHandler(IJwtExceptionService service) {
        this.service = service;
    }

    @ExceptionHandler(CheckPermissionException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public Object handleRestException(CheckPermissionException e) {
        return service.getPermissionExceptionResponse();
    }
}