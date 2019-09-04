-- auto-generated definition
create table USER_INFO
(
    ID           VARCHAR2(36) not null,
    USERNAME     VARCHAR2(255) default NULL,
    PASSWORD     VARCHAR2(255) default NULL,
    NAME         VARCHAR2(255) default NULL,
    BIRTHDAY     VARCHAR2(255) default NULL,
    ADDRESS      VARCHAR2(255) default NULL,
    MOBILE_PHONE VARCHAR2(255) default NULL,
    TEL_PHONE    VARCHAR2(255) default NULL,
    EMAIL        VARCHAR2(255) default NULL,
    SEX          CHAR          default NULL,
    TYPE         CHAR          default NULL,
    STATUS       CHAR          default NULL,
    DESCRIPTION  VARCHAR2(255) default NULL,
    CRT_TIME     TIMESTAMP(6)  default NULL,
    CRT_USER     VARCHAR2(255) default NULL,
    CRT_NAME     VARCHAR2(255) default NULL,
    CRT_HOST     VARCHAR2(255) default NULL,
    UPD_TIME     TIMESTAMP(6)  default NULL,
    UPD_USER     VARCHAR2(255) default NULL,
    UPD_NAME     VARCHAR2(255) default NULL,
    UPD_HOST     VARCHAR2(255) default NULL,
    ATTR1        VARCHAR2(255) default NULL,
    ATTR2        VARCHAR2(255) default NULL,
    ATTR3        VARCHAR2(255) default NULL,
    ATTR4        VARCHAR2(255) default NULL,
    ATTR5        VARCHAR2(255) default NULL,
    ATTR6        VARCHAR2(255) default NULL,
    ATTR7        VARCHAR2(255) default NULL,
    ATTR8        VARCHAR2(255) default NULL
)
/

create unique index USER_INFO_ID_UINDEX
    on USER_INFO (ID)
/

alter table USER_INFO
    add constraint USER_INFO_PK
        primary key (ID)
/

alter table USER_INFO
	add name_bak varchar2(50) default null
/
