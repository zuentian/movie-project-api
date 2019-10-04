-- auto-generated definition
create table MOVIE_PICTURE_INFO
(
    ID        VARCHAR2(36) not null,
    MOVIE_ID  VARCHAR2(36),
    FILE_NAME VARCHAR2(100),
    FILE_URL  VARCHAR2(100),
    FILE_URI  VARCHAR2(100),
    TYPE      VARCHAR2(10),
    CRT_TIME TIMESTAMP(6)  default NULL,
    CRT_USER VARCHAR2(255) default NULL,
    CRT_NAME VARCHAR2(255) default NULL,
    CRT_HOST VARCHAR2(255) default NULL
)
/

comment on column MOVIE_PICTURE_INFO.TYPE is 'S:电影剧照 B:电影海报'
comment on column MOVIE_PICTURE_INFO.FILE_URL is '图片具体存放路径'
comment on column MOVIE_PICTURE_INFO.FILE_URI is '图片展示路径'
/

create unique index MOVIE_PICTURE_INFO_ID_UINDEX
    on MOVIE_PICTURE_INFO (ID)
/

alter table MOVIE_PICTURE_INFO
    add constraint MOVIE_PICTURE_INFO_PK
        primary key (ID)
/

