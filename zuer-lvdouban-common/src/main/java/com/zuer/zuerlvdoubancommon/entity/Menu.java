package com.zuer.zuerlvdoubancommon.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "MENU")
public class Menu {
    @Id
    private String id;

    private String code;

    private String title;

    @Column(name = "parent_id")
    private String parentId = "";

    private String href;

    private String icon;

    private String type;

    @Column(name = "ORDER_NUM")
    private int orderNum;

    private String description;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Column(name = "crt_time")
    private Date crtTime;

    @Column(name = "crt_user")
    private String crtUser;

    @Column(name = "crt_name")
    private String crtName;

    @Column(name = "crt_host")
    private String crtHost;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Column(name = "upd_time")
    private Date updTime;

    @Column(name = "upd_user")
    private String updUser;

    @Column(name = "upd_name")
    private String updName;

    @Column(name = "upd_host")
    private String updHost;

    private String attr1;

    private String attr2;

    private String attr3;

    private String attr4;

    private String attr5;

    private String attr6;

    private String attr7;

    private String attr8;

}
