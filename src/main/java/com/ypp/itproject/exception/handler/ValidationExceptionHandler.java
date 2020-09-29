package com.ypp.itproject.exception.handler;

import com.ypp.itproject.vo.exception.RestExceptionVo;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public RestExceptionVo handleValidationExceptions(MethodArgumentNotValidException ex) {
        RestExceptionVo vo = new RestExceptionVo();
        vo.setCode(0);

        FieldError error = ex.getBindingResult().getFieldError();
        assert error != null;
        String fieldName = error.getField();
        String errorMessage = error.getDefaultMessage();
        vo.setMessage("invalid " + fieldName + ", " + errorMessage);

        return vo;
    }
}
