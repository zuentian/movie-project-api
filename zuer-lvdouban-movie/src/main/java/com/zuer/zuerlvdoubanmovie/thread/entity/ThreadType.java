package com.zuer.zuerlvdoubanmovie.thread.entity;

public enum ThreadType {

    ANALY_DB_MOVIE_INFO("解析豆瓣电影信息","ADMI");

    private String type;
    private String value;

    ThreadType(String type,String value){
        this.type = type;
        this.value = value;
    }
    public String getValue(){
        return value;
    }
    public String getType(){
        return type;
    }
}
