package com.zuer.zuerlvdoubancommon.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Zuer
 * @version 1.0
 * @date 2021/8/18 11:03
 * 用户和电影记录表
 */
@Data
@Table(name="MOVIE_USER_RECORD")
public class MovieUserRecord {
    @Id
    private int id;

    @Column
    private String movieId;

    @Column
    private String userId;

    @Column
    private String state;

    @Column
    private double score;
    /**
     * 短评
     */
    @Column
    private String shortCommand;

    @Column
    private Date crtTime;


}
