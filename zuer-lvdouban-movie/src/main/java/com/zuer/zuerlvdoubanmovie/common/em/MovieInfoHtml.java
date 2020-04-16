package com.zuer.zuerlvdoubanmovie.common.em;

public enum  MovieInfoHtml {

    DIRECTOR("导演:"),
    AUTHOR("编剧::"),
    ACTOR("主演:"),
    TYPE("类型:"),
    AREA("制片国家/地区:"),
    LANGUAGE("语言:"),
    TIME("上映日期:"),
    LENGTH("片长:"),
    NAME_OTHER("又名:"),
    ADDRES("官方网站:"),
    IMDB("IMDb链接:");

    private String value;

    MovieInfoHtml(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
