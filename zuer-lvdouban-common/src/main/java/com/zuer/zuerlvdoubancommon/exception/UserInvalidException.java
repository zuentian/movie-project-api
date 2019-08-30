package com.zuer.zuerlvdoubancommon.exception;


import com.zuer.zuerlvdoubancommon.constants.CommonConstants;

public class UserInvalidException extends BaseException {
    public UserInvalidException(String message) {
        super(message, CommonConstants.EX_USER_PASS_INVALID_CODE);
    }
}
