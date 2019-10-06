package com.zuer.zuerlvdoubancommon.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zuer.zuerlvdoubancommon.entity.MovieCountry;
import com.zuer.zuerlvdoubancommon.entity.MovieRelName;
import com.zuer.zuerlvdoubancommon.entity.MovieType;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MovieInfoExp {


    private String id;
    private String movieName;
    private String movieName1;
    private String movieName2;
    private String movieTime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date movieShowTime;
    private Integer watchAfterNumber;
    private Integer watchBeforeNumber;
    private Integer personScoreCount;
    private String score;
    private String movieDescription;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date crtTime;
    private String crtUser;
    private String crtName;
    private String crtHost;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updTime;
    private String updUser;
    private String updName;
    private String updHost;

    private List<MovieCountry> movieCountryList;
    private List<MovieType> movieTypeList;
    private List<MovieRelName> movieRelNameList;


}
