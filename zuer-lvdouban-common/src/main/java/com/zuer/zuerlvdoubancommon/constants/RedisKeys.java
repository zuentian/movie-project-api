package com.zuer.zuerlvdoubancommon.constants;

import lombok.Getter;

/**
 * @author Zuer
 * @version 1.0
 * @date 2021/9/6 16:54
 */
@Getter
public enum RedisKeys {

    MOVIE_USER_WATCH_COUNT("user_watch_count_vo","电影观影用户统计")
    ;
    
    private String code;
    private String name;

    RedisKeys(String code,String name){
        this.code = code;
        this.name = name;
    }
}
