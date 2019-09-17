-- auto-generated definition
create table MENU_GROUP
(
    ID          VARCHAR2(36) not null,
    GROUP_ID    VARCHAR2(36),
    GROUP_TYPE  VARCHAR2(20),
    MENU_ID     VARCHAR2(36),
    MENU_TYPE   VARCHAR2(255),
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

comment on table MENU_GROUP is '菜单和角色关联表'
/

create unique index MENU_GROUP_ID_UINDEX
    on MENU_GROUP (ID)
/

alter table MENU_GROUP
    add constraint MENU_GROUP_PK
        primary key (ID)
/

