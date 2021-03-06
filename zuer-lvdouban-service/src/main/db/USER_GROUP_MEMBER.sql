-- auto-generated definition
create table USER_GROUP_MEMBER
(
  ID          VARCHAR2(36) not null
    primary key,
  GROUP_ID    VARCHAR2(36),
  USER_ID     VARCHAR2(36),
  DESCRIPTION VARCHAR2(255),
  CRT_TIME    TIMESTAMP(6),
  CRT_USER    VARCHAR2(255),
  CRT_NAME    VARCHAR2(255),
  CRT_HOST    VARCHAR2(20),
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

comment on table USER_GROUP_MEMBER
is '角色和用户下属层关联表'
/

