package com.zuer.zuerlvdoubanmovie.executor;

/**
 * @author Zuer
 * @version 1.0
 * @date 2021/8/27 10:56
 */
public interface AnalysisUserMovieTypeCount {
    /**
     * 增加用户看过的类型数量
     * @param userId
     * @param movieType
     */
    void addMovieTypeCount(String userId, String movieType);
}
