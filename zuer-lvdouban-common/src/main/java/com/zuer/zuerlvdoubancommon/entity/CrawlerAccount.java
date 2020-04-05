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

    @Column(name = "CRT_DATE")
    private Date crtDate;

    @Column(name = "ALT_DATE")
    private Date altDate;
}
