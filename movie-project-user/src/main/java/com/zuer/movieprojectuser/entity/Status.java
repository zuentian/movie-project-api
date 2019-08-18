package com.zuer.movieprojectuser.entity;

import lombok.Getter;

@Getter
public enum Status {

    LOCKED("1", "账号被锁"),
    NORMAL("0", "账号正常");

    private String code;

    private String message;

    Status(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
