-- auto-generated definition
create table ELEMENT_GROUP
(
    ID          VARCHAR2(36) not null,
    GROUP_ID    VARCHAR2(36),
    GROUP_TYPE  VARCHAR2(20),
    ELEMENT_ID  VARCHAR2(36),
    PARENT_ID   VARCHAR2(36),
    PATH        VARCHAR2(255),
    DESCRIPTION VARCHAR2(255),
    CRT_TIME    TIMESTAMP(6),
    CRT_HOST    VARCHAR2(20),
    CRT_NAME    VARCHAR2(255),
    CRT_USER    VARCHAR2(255),
    ATTR1       VARCHAR2(255),
    ATTR2       VARCHAR2(255),
    ATTR3       VARCHAR2(255),
    ATTR4       VARCHAR2(255)
)
/

comment on table ELEMENT_GROUP is '功能和角色关联表'
/

create unique index ELEMENT_GROUP_ID_UINDEX
    on ELEMENT_GROUP (ID)
/

alter table ELEMENT_GROUP
    add constraint ELEMENT_GROUP_PK
        primary key (ID)
/

