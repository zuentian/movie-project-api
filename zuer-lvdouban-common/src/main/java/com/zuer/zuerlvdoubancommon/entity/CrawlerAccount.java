package com.zuer.zuerlvdoubancommon.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "CRAWLER_ACCOUNT")
public class CrawlerAccount {

    @Id
    private String id;

    private String web;

    @Column(name = "WEB_NAME")
    private String webName;

    private String account;

    private String password;

    private String flag;

    @Column(name = "CRT_TIME")
    private Date crtTime;

    @Column(name = "UPD_TIME")
    private Date updTime;

    @Column(name = "UPD_USER")
    private String updUser;

    @Column(name = "CRT_USER")
    private String crtUser;

    @Column(name = "UPD_NAME")
    private String updName;

    @Column(name = "CRT_NAME")
    private String crtName;
}
