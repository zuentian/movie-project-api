package com.zuer.movielvdoubancommon.exception;


import com.zuer.movielvdoubancommon.constatns.CommonConstants;

/**
 * Created by ace on 2017/9/8.
 */
public class UserInvalidException extends BaseException {
    public UserInvalidException(String message) {
        super(message, CommonConstants.EX_USER_PASS_INVALID_CODE);
    }
}
