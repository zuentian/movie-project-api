-- auto-generated definition
create table GROUP_TYPE
(
    ID          VARCHAR2(36) not null,
    CODE        VARCHAR2(255),
    NAME        VARCHAR2(255),
    DESCRIPTION VARCHAR2(255),
    CRT_TIME    TIMESTAMP(6),
    CRT_USER    VARCHAR2(255),
    CRT_NAME    VARCHAR2(255),
    CRT_HOST    VARCHAR2(255),
    UPD_TIME    TIMESTAMP(6),
    UPD_USER    VARCHAR2(255),
    UPD_NAME    VARCHAR2(255),
    UPD_HOST    VARCHAR2(20),
    ATTR1       VARCHAR2(255),
    ATTR2       VARCHAR2(255),
    ATTR3       VARCHAR2(255),
    ATTR4       VARCHAR2(255)
)
/

create unique index GROUP_TYPE_ID_UINDEX
    on GROUP_TYPE (ID)
/

alter table GROUP_TYPE
    add constraint GROUP_TYPE_PK
        primary key (ID)
/

