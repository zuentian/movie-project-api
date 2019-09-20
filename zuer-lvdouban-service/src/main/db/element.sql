-- auto-generated definition
create table ELEMENT
(
    ID          VARCHAR2(36) not null
        primary key,
    CODE        VARCHAR2(255),
    NAME        VARCHAR2(255),
    MENU_ID     VARCHAR2(36),
    METHOD      VARCHAR2(255),
    DESCRIPTION VARCHAR2(255),
    CRT_TIME    TIMESTAMP(6) default NULL,
    CRT_USER    VARCHAR2(255),
    CRT_NAME    VARCHAR2(255),
    CRT_HOST    VARCHAR2(20),
    ATTR1       VARCHAR2(255),
    ATTR2       VARCHAR2(255),
    ATTR3       VARCHAR2(255),
    ATTR4       VARCHAR2(255)
)
/

