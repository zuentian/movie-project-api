package com.zuer.zuerlvdoubancommon.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "EXCEPTION_INFO")
public class ExceptionInfo {

    private String id;

    @Column(name="ERR_MESSAGE")
    private String errMessage;

    private String status;

    @Column(name="ERR_TYPE")
    private String errType;

    @Column(name="ERR_CLASS")
    private String errClass;

    @Column(name="ERR_FILENAME")
    private String errFileName;

    @Column(name="ERR_METHOD")
    private String errMethod;

    @Column(name="ERR_LINENUMBER")
    private int errLineNumber;

    @Column(name="ERR_DETAIL")
    private String errDetail;

    @Column(name = "crt_time")
    private Date crtTime;

    @Column(name = "crt_user")
    private String crtUser;

    @Column(name = "crt_name")
    private String crtName;

    @Column(name = "crt_host")
    private String crtHost;
}
