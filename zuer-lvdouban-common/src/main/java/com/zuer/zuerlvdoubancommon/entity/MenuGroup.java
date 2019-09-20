package com.zuer.zuerlvdoubancommon.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Data
@Table(name = "MENU_GROUP")
public class MenuGroup {
    @Id
    private String id;

    @Column(name = "group_id")
    private String groupId;

    @Column(name = "menu_id")
    private String menuId;
    private String description;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Column(name = "crt_time")
    private Date crtTime;

    @Column(name = "crt_user")
    private String crtUser;

    @Column(name = "crt_name")
    private String crtName;

    @Column(name = "crt_host")
    private String crtHost;

    private String attr1;

    private String attr2;

    private String attr3;

    private String attr4;

    public MenuGroup() {
    }

}