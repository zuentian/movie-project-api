package com.zuer.zuerlvdoubanmovie.common.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CrawlerDbRequestInfo implements Serializable {

    private String type;
    private String tag;
    private String page_limit;
    private String page_start;
    private String sort;
    private String watched;
}
