package com.fh.api.advice;

import com.fh.api.common.ResponseEnum;

public class GlobalException extends RuntimeException {

    private ResponseEnum responseEnum;

    public GlobalException(ResponseEnum responseEnum){
        this.responseEnum=responseEnum;
    }

    public ResponseEnum getResponseEnum() {
        return responseEnum;
    }
}
