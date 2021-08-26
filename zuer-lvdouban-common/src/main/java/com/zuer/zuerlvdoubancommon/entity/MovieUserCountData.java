package com.zuer.zuerlvdoubancommon.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Zuer
 * @version 1.0
 * @date 2021/8/26 10:06
 */
@Data
@Table(name="MOVIE_USER_COUNT_DATA")
public class MovieUserCountData {

    @Id
    @Column(name = "MOVIE_ID")
    private String movieId;
    /**
     * 计数的类型
     */
    @Id
    @Column
    private String type;
    /**
     * 计数的数量
     */
    @Column
    private Double count;
    /**
     * 计数的更新时间
     */
    @Column(name = "UPD_DATE")
    private Date updDate;
}
