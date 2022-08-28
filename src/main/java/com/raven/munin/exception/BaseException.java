package com.raven.munin.exception;

import lombok.Data;

@Data
public class BaseException extends RuntimeException{
    private String errorCode;

    private String errorMsg;

    public BaseException() {
    }

    public BaseException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BaseException(String errorMsg) {
        this.errorMsg=errorMsg;
    }
}
