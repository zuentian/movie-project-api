package com.zuer.zuerlvdoubancommon.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name="CRAWLER_URL_INFO")
public class CrawlerUrlInfo {

    @Id
    private String id;

    private String url;

    @Column(name = "URL_NAME")
    private String urlName;

    private String type;

    @Column(name = "TYPE_NAME")
    private String typeName;

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
