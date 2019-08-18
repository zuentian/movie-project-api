package com.zuer.movieprojectuser.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {


    private String userId;
    private String userCode;
    private String password;
    private String userType;
    private String statue;
    private String userName;
    private String userNameBak;
    private String mobile;
    private String userPhotoUrl;
    private String sex;
    private String crtTime;
    private String altTime;

}
