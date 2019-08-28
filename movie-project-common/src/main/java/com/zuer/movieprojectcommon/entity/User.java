package com.zuer.movieprojectcommon.entity;


import lombok.Data;

import java.util.List;

@Data
public class User  {


    private String userId;
    private String userCode;
    private String password;
    private String userType;
    private String status;
    private String userName;
    private String userNameBak;
    private String mobile;
    private String userPhotoUrl;
    private String sex;
    private List<String> roleIds;
    private String crtTime;
    private String altTime;

}
