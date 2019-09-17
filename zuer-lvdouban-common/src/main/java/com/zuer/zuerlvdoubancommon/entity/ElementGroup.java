package com.zuer.zuerlvdoubancommon.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "ELEMENT_GROUP")
public class ElementGroup {
    @Id
    private String id;

    @Column(name = "group_id")
    private String groupId;

    @Column(name = "group_type")
    private String groupType;

    @Column(name = "element_id")
    private String elementId;

    @Column(name = "parent_id")
    private String parentId;

    private String path;

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

    public ElementGroup(String groupType) {
        this.groupType = groupType;
    }

    public ElementGroup() {
    }

}