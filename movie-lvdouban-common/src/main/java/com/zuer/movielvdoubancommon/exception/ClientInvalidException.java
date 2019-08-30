package com.zuer.movielvdoubancommon.exception;


import com.zuer.movielvdoubancommon.constatns.CommonConstants;

/**
 * Created by ace on 2017/9/10.
 */
public class ClientInvalidException extends BaseException {
    public ClientInvalidException(String message) {
        super(message, CommonConstants.EX_CLIENT_INVALID_CODE);
    }
}
