package com.zuer.zuerlvdoubancommon.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "MOVIE_USER")
public class MovieUser {

    /*@Id
    private String id;*/

    @Id
    @Column(name = "MOVIE_ID")
    private String movieId;
    
    @Id
    @Column(name = "USER_ID")
    private String userId;

    private String score;

    @Column(name = "SHORT_COMMAND")
    private String shortCommand;

    private String state;

    @Column(name = "WATCH_AFTER_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date watchAfterTime;

    @Column(name = "WATCH_BEFORE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date watchBeforeTime;

    @Column(name = "crt_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date crtTime;

    @Column(name = "crt_user")
    private String crtUser;

    @Column(name = "crt_name")
    private String crtName;

    @Column(name = "crt_host")
    private String crtHost;


    @Column(name = "upd_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updTime;

    @Column(name = "upd_user")
    private String updUser;

    @Column(name = "upd_name")
    private String updName;

    @Column(name = "upd_host")
    private String updHost;

}
