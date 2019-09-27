package com.zuer.zuerlvdoubancommon.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "MOVIE_INFO")
public class MovieInfo {

    private String id;

    @Column(name = "MOVIE_NAME")
    private String movieName;

    @Column(name = "MOVIE_NAME1")
    private String movieName1;

    @Column(name = "MOVIE_NAME2")
    private String movieName2;


    @Column(name = "MOVIE_TIME")
    private String movieTime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @Column(name = "MOVIE_SHOW_TIME")
    private Date movieShowTime;

    @Column(name = "WATCH_AFTER_NUMBER")
    private String WatchAfterNumber;

    @Column(name = "WATCH_AFTER_NUMBER")
    private String WatchBeforeNumber;

    private String score;


    @Column(name = "MOVIE_DESCRIPTION",columnDefinition = "CLOB")
    @Lob
    private String movieDescription;

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
