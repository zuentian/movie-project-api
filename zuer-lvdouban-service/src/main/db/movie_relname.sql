-- auto-generated definition
create table MOVIE_RELNAME
(
    ID       VARCHAR2(36) not null,
    MOVIE_ID VARCHAR2(36),
    NAME     VARCHAR2(50),
    POSITION VARCHAR2(20),
    CRT_TIME TIMESTAMP(6)  default NULL,
    CRT_USER VARCHAR2(255) default NULL,
    CRT_NAME VARCHAR2(255) default NULL,
    CRT_HOST VARCHAR2(255) default NULL,
    UPD_TIME TIMESTAMP(6)  default NULL,
    UPD_USER VARCHAR2(255) default NULL,
    UPD_NAME VARCHAR2(255) default NULL,
    UPD_HOST VARCHAR2(255) default NULL
)
/

comment on table MOVIE_RELNAME is '电影相关人物表'
/

create unique index MOVIE_RELNAME_ID_UINDEX
    on MOVIE_RELNAME (ID)
/

alter table MOVIE_RELNAME
    add constraint MOVIE_RELNAME_PK
        primary key (ID)
/

