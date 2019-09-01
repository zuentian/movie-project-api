package com.zuer.zuerlvdoubancommon.exception;


import com.zuer.zuerlvdoubancommon.constants.CommonConstants;


public class UserTokenException extends BaseException {
    public UserTokenException(String message) {
        super(message, CommonConstants.EX_USER_INVALID_CODE);
    }
}
