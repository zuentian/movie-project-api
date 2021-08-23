package com.zuer.zuerlvdoubancommon.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Zuer
 * @version 1.0
 * @date 2021/8/20 9:29
 * 用户和电影关联记录历史
 */
@Data
@Table(name="MOVIE_USER_REC_HIS")
public class MovieUserRecHis {

    @Column
    private String movieId;

    @Column
    private String userId;

    /**
     * 1-想看 2-看过
     */
    @Column
    private String state;

    /**
     * 评分
     */
    @Column
    private Double score;
    /**
     * 短评
     */
    @Column
    private String shortCommand;

    /**
     * 标记时间
     */
    @Column
    private Date markTime;


    @Column
    private Date crtTime;

}
