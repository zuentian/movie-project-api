package com.zuer.zuerlvdoubancommon.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class MovieUserCommand {


    private String name;
    private String userId;
    private String avatar;

    private String movieId;
    private Integer score;
    private String shortCommand;
    private String state;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date watchAfterTime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date watchBeforeTime;
}
