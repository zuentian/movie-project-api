package com.zuer.zuerlvdoubanmovie.common.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CrawlerDbRequestInfo implements Serializable {

    private String type;
    private String tag;
    private Integer page_limit;
    private Integer page_start;
    private String sort;
}
