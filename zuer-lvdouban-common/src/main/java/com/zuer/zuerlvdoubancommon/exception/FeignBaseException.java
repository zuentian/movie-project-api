package com.zuer.zuerlvdoubancommon.exception;

import lombok.Data;

@Data
public class FeignBaseException extends RuntimeException{
    private String message;
    private int status;
    private String exception;
    private String messageDetail;
    private String method;
    public FeignBaseException(String message, int status) {
        this.message=message;
        this.status = status;
    }
}
