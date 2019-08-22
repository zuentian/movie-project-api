package com.zuer.movieprojectcommon.entity;


import lombok.Getter;

@Getter
public enum UserType {

    MOBILE("0", "手机号"),
    EMAIL("1","邮箱"),
    OTHEREAMIL("2","其他邮箱"),
    OTHER("3","其他");

    private String code;

    private String message;

    UserType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
