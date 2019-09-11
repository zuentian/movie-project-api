
create table ZU_GROUP
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

comment on table ZU_GROUP is '角色表'
/

create unique index ZU_GROUP_ID_UINDEX
    on ZU_GROUP (ID)
/

alter table ZU_GROUP
    add constraint ZU_GROUP_PK
        primary key (ID)
/

