package com.zuer.zuerlvdoubanmovie.common.entity;

import lombok.Data;

@Data
public class CrawlerDbMovieSimpleInfo {
    private String cover;
    private String id;
    private String rate;
    private String playable;
    private String title;
    private String url;
    private String is_new;
    private String cover_y;
    private String cover_x;
    private String base64Photo;
    private String syncFlag;
}
