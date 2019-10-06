-- auto-generated definition
create table MOVIE_INFO
(
    ID                  VARCHAR2(36) not null,
    MOVIE_NAME          VARCHAR2(500),
    MOVIE_NAME1         VARCHAR2(500),
    MOVIE_NAME2         VARCHAR2(500),
    MOVIE_TIME          VARCHAR2(10) ,
    MOVIE_SHOW_TIME     TIMESTAMP(6),
    WATCH_AFTER_NUMBER  VARCHAR2(10),
    WATCH_BEFORE_NUMBER VARCHAR2(10),
    SCORE               VARCHAR2(10),
    MOVIE_DESCRIPTION   CLOB,
    CRT_TIME            TIMESTAMP(6)  default NULL,
    CRT_USER            VARCHAR2(255) default NULL,
    CRT_NAME            VARCHAR2(255) default NULL,
    CRT_HOST            VARCHAR2(255) default NULL,
    UPD_TIME            TIMESTAMP(6)  default NULL,
    UPD_USER            VARCHAR2(255) default NULL,
    UPD_NAME            VARCHAR2(255) default NULL,
    UPD_HOST            VARCHAR2(255) default NULL
)
/

comment on table MOVIE_INFO is '电影基本信息表'
/

create unique index MOVIE_INFO_ID_UINDEX
    on MOVIE_INFO (ID)
/

alter table MOVIE_INFO
    add constraint MOVIE_INFO_PK
        primary key (ID)
/
alter table MOVIE_INFO modify (WATCH_AFTER_NUMBER NUMBER(10));
alter table MOVIE_INFO modify (WATCH_BEFORE_NUMBER NUMBER(10));
alter table MOVIE_INFO modify WATCH_AFTER_NUMBER default 0;
alter table MOVIE_INFO modify WATCH_BEFORE_NUMBER default 0;
alter table MOVIE_INFO add( PERSON_SCORE_COUNT NUMBER(10) default 0) ;