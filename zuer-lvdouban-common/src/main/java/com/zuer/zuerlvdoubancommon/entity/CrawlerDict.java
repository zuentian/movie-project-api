package com.zuer.zuerlvdoubancommon.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name="CRAWLER_DICT")
public class CrawlerDict {

    @Id
    private String id;

    private String url;

    @Column(name = "URL_NAME")
    private String urlName;

    private String type;

    @Column(name = "typeName")
    private String typeName;

    @Column(name = "CRT_DATE")
    private Date crtDate;

    @Column(name = "ALT_DATE")
    private Date altDate;

}
