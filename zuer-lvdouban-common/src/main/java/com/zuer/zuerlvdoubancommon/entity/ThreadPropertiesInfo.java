package com.zuer.zuerlvdoubancommon.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "THREAD_PROPERTIES_INFO")
public class ThreadPropertiesInfo {
    @Id
    private String type;

    @Column(name = "CORE_POOL_SIZE")
    private Integer corePoolSize;
    @Column(name = "MAX_POOL_SIZE")
    private Integer maxPoolSize;
    @Column(name = "QUEUE_CAPACITY")
    private Integer queueCapacity;

    @Column(name = "CRT_TIME")
    private Date crtTime;

    @Column(name = "UPD_TIME")
    private Date updTime;

    @Column(name = "UPD_USER")
    private String updUser;

    @Column(name = "CRT_USER")
    private String crtUser;

    @Column(name = "UPD_NAME")
    private String updName;

    @Column(name = "CRT_NAME")
    private String crtName;
}
