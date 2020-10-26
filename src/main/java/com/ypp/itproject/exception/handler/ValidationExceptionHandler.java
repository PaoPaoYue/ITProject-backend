package com.ypp.itproject.exception.handler;

import com.ypp.itproject.vo.exception.RestExceptionVo;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({MethodArgumentNotValidException.class})
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

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseBody
    public RestExceptionVo handleValidationExceptions(ConstraintViolationException ex) {
        RestExceptionVo vo = new RestExceptionVo();
        vo.setCode(0);
        String message = ex.getMessage();
        String fieldName = message.substring(message.indexOf('.')+1, message.indexOf(':'));
        String errorMessage = message.substring(message.indexOf(':')+2);
        vo.setMessage("invalid " + fieldName + ", " + errorMessage);
        return vo;
    }
}
