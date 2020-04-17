package com.zuer.zuerlvdoubanmovie.common.em;

public enum  MovieInfoHtml {

    DIRECTOR("导演","导演:"),
    AUTHOR("编剧","编剧:"),
    ACTOR("主演","主演:"),
    TYPE("类型","类型:"),
    AREA("制片国家/地区","制片国家/地区:"),
    LANGUAGE("语言","语言:"),
    TIME("上映日期","上映日期:"),
    LENGTH("片长","片长:"),
    NAME_OTHER("又名","又名:"),
    ADDRESS("官方网站","官方网站:"),
    IMDB("IMDb链接","IMDb链接:");

    private String type;
    private String value;

    MovieInfoHtml(String type,String value){
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
