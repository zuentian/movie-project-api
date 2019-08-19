package com.zuer.movieprojectdict.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Dict {

    private String dictId;
    private String dictType;
    private String dictTypeContent;
    private String dictCode;
    private String dictValue;
    private String crtTime;
    private String altTime;
}
