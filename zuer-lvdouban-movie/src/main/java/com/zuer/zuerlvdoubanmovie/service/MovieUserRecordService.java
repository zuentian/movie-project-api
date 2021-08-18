package com.zuer.zuerlvdoubanmovie.service;

import com.zuer.zuerlvdoubancommon.entity.MovieUserRecord;

/**
 * @author Zuer
 * @version 1.0
 * @date 2021/8/18 11:23
 */
public interface MovieUserRecordService {
    /**
     * 用户和电影关联记录新增
     * @param movieUserRecord
     */
    void insertMovieUserRecord(MovieUserRecord movieUserRecord);
}
