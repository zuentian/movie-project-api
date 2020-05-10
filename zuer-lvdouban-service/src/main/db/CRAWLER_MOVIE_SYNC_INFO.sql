-- auto-generated definition
create table CRAWLER_MOVIE_SYNC_INFO
(
  ID                  VARCHAR2(36) not null,
  SYNC_FLAG          VARCHAR2(500),
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

comment on table CRAWLER_MOVIE_SYNC_INFO is '爬虫同步信息表'
/
