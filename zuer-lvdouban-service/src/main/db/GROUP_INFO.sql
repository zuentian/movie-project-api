
create table GROUP_INFO
(
    ID          VARCHAR2(36) not null,
    CODE        VARCHAR2(255),
    NAME        VARCHAR2(255),
    PARENT_ID   VARCHAR2(36),
    PATH        VARCHAR2(255),
    TYPE        VARCHAR2(255),
    GROUP_TYPE_ID  VARCHAR2(36),
    DESCRIPTION VARCHAR2(255),
    CRT_TIME    TIMESTAMP(6),
    CRT_USER    VARCHAR2(255),
    CRT_HOST    VARCHAR2(255),
    UPD_TIME    TIMESTAMP(6),
    UPD_USER    VARCHAR2(255),
    UPD_HOST    VARCHAR2(255),
    CRT_NAME    VARCHAR2(255),
    UPD_NAME    VARCHAR2(255),
    ATTR1         VARCHAR2(255),
    ATTR2         VARCHAR2(255),
    ATTR3         VARCHAR2(255),
    ATTR4         VARCHAR2(255)
)
/

comment on table GROUP_INFO is '角色表'
/

create unique index GROUP_INFO_ID_UINDEX
    on GROUP_INFO (ID)
/

alter table GROUP_INFO
    add constraint GROUP_INFO_PK
        primary key (ID)
/

