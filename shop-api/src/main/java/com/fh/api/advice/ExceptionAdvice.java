package com.fh.api.advice;

import com.fh.api.common.ResponseEnum;
import com.fh.api.common.ServerResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(GlobalException.class)
    public ServerResponse globalExceptionHandler(GlobalException ex){
        ResponseEnum responseEnum = ex.getResponseEnum();
        return ServerResponse.error(responseEnum);
    }
}
