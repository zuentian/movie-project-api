package com.zuer.movielvdoubancommon.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {

    public String id;
    public String username;
    public String password;
    public String name;
    private String description;
    private Date updTime;


}
