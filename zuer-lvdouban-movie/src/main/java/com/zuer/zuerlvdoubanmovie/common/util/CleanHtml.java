package com.zuer.zuerlvdoubanmovie.common.util;

import com.zuer.zuerlvdoubanmovie.common.em.MovieInfoHtml;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
public class CleanHtml {

    private static String [] codes ={
            MovieInfoHtml.TIME.getValue(),
            MovieInfoHtml.ACTOR.getValue(),
            MovieInfoHtml.AREA.getValue(),
            MovieInfoHtml.AUTHOR.getValue(),
            MovieInfoHtml.DIRECTOR.getValue(),
            MovieInfoHtml.IMDB.getValue(),
            MovieInfoHtml.LANGUAGE.getValue(),
            MovieInfoHtml.NAME_OTHER.getValue(),
            MovieInfoHtml.LENGTH.getValue(),
            MovieInfoHtml.TYPE.getValue(),
            MovieInfoHtml.ADDRES.getValue(),
    };

    public static Map<String, String> cleanHtmlMovieInfo(String info) {
        Map<String,String> resultInfo = new HashMap<String, String>();
        if(StringUtils.isNotBlank(info)){
            for (String code : codes) {
                info = info.replace(code,"&&"+code);
            }
            String[] infos = info.replace(" ","").split("&&");

            for (String str : infos) {
                if(StringUtils.isNotBlank(str)){
                    String[] strr = str.split(":");
                    if(strr!=null&&strr.length>1){
                        resultInfo.put(strr[0],strr[1]);
                    }
                }
            }
        }
        return resultInfo;
    }
}
