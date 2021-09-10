package com.zuer.zuerlvdoubancommon.constants;

import lombok.Getter;

/**
 * @author Zuer
 * @version 1.0
 * @date 2021/9/6 16:54
 */
@Getter
public enum RedisKeys {

    USER_WATCH_STATE("user_watch_state","用户标记电影状态"),
    USER_WATCH_COUNT_VO("user_watch_count_vo","电影数量统计");
    
    private String code;
    private String name;

    RedisKeys(String code,String name){
        this.code = code;
        this.name = name;
    }
}
