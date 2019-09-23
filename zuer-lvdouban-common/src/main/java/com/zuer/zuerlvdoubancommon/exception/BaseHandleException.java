package com.zuer.zuerlvdoubancommon.exception;

import lombok.Data;

@Data
public class BaseHandleException extends RuntimeException{
    private int status = 200;

    public BaseHandleException() {
    }

    public BaseHandleException(String message, int status) {
        super(message);
        this.status = status;
    }

    public BaseHandleException(String message) {
        super(message);
    }

    public BaseHandleException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseHandleException(Throwable cause) {
        super(cause);
    }

    public BaseHandleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
