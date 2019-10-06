package com.zuer.zuerlvdoubancommon.entity;

import lombok.Data;

import java.util.Date;
@Data
public class UserInfo {
    public String id;
    public String username;
    public String password;
    public String name;
    public String nameBak;
    private String description;
    private Date updTime;
    private String avatar;
}
