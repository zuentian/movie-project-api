package com.zuer.movieprojectuser.err;

import lombok.Data;

@Data
public class HippoServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    private Integer errorCode;

    private String errorMsg;


    public HippoServiceException(Integer errorCode,String errorMsg) {

        this.errorCode = errorCode;
        this.errorMsg =  errorMsg;
    }

}
