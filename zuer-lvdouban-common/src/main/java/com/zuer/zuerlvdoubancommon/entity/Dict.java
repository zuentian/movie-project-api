package com.zuer.zuerlvdoubancommon.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "DICT")
public class Dict {
    @Id
    @Column(name = "DICT_ID")
    private String dictId;

    @Column(name = "DICT_TYPE")
    private String dictType;

    @Column(name = "DICT_TYPE_NAME")
    private String dictTypeName;

    @Column(name = "DICT_CODE")
    private String dictCode;

    @Column(name = "DICT_VALUE")
    private String dictValue;

    @Column(name = "CRT_TIME")
    private String crtTime;

    @Column(name = "ALT_TIME")
    private String altTime;
}
