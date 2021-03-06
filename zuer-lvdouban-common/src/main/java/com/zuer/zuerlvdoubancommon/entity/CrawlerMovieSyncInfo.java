package com.zuer.zuerlvdoubancommon.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Data
@Table(name = "CRAWLER_MOVIE_SYNC_INFO")
public class CrawlerMovieSyncInfo {

    @Id
    private String id;

    @Column(name = "SYNC_FLAG")
    private String syncFlag;

    private String url;

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
