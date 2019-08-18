package com.zuer.movieprojectuser.entity;


import lombok.Getter;

@Getter
public enum UserType {

    MOBILE("0", "手机号");

    private String code;

    private String message;

    UserType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
