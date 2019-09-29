-- auto-generated definition
create table MOVIE_COUNTRY
(
    ID           VARCHAR2(36) not null
        primary key,
    COUNTRY_CODE VARCHAR2(10),
    MOVIE_ID     VARCHAR2(36),
    CRT_TIME     TIMESTAMP(6)  default NULL,
    CRT_USER     VARCHAR2(255) default NULL,
    CRT_NAME     VARCHAR2(255) default NULL,
    CRT_HOST     VARCHAR2(255) default NULL
)
/

comment on table MOVIE_COUNTRY is '电影国家信息表'
/

