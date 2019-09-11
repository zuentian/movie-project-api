
create table "GROUP"
(
    ID          VARCHAR2(36) not null,
    CODE        VARCHAR2(255),
    NAME        VARCHAR2(255),
    PARENT_ID   VARCHAR2(36),
    PATH        VARCHAR2(255),
    TYPE        VARCHAR2(255),
    GROUP_TYPE  VARCHAR2(255),
    DESCRIPTION VARCHAR2(255),
    CRT_TIME    TIMESTAMP(6),
    CRT_USER    VARCHAR2(255),
    CRT_HOST    VARCHAR2(255),
    UPD_TIME    TIMESTAMP(6),
    UPD_USER    VARCHAR2(255),
    UPD_HOST    VARCHAR2(255),
    CRT_NAME    VARCHAR2(255),
    UPD_NAME    VARCHAR2(255)
)
/

comment on table "GROUP" is '角色表'
/

create unique index GROUP_ID_UINDEX
    on "GROUP" (ID)
/

alter table "GROUP"
    add constraint GROUP_PK
        primary key (ID)
/

