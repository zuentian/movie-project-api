package com.zuer.zuerlvdoubanmovie.common.entity;

import lombok.Data;

import java.util.List;

@Data
public class CrawlerDbResponseInfo {

    private String movieName;//电影名
    private String movieLocalName;//电影本土名
    private String year;//年份
    private List<String> length;//片长
    private List<String> areas;//制片国家/地区
    private List<String> types;//类型
    private List<String> language;//语言
    private List<String> time;//上映时间
    private List<String> nameOther;//其他名字

    //private List<String> directors;//导演
    //private List<String> actors; //演员
}
