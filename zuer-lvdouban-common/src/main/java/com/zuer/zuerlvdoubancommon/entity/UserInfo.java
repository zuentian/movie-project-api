package com.zuer.zuerlvdoubancommon.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String avatar;
    private String level;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date crtTime;
}
