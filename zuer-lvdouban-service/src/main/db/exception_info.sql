-- auto-generated definition
create table EXCEPTION_INFO
(
  ID             VARCHAR2(36) not null
    primary key,
  ERR_MESSAGE    VARCHAR2(200),
  ERR_TYPE       VARCHAR2(255),
  STATUS         VARCHAR2(20),
  ERR_CLASS      VARCHAR2(500),
  ERR_FILENAME   VARCHAR2(500),
  ERR_METHOD     VARCHAR2(500),
  ERR_LINENUMBER NUMBER(8),
  ERR_DETAIL     CLOB,
  CRT_TIME       TIMESTAMP(6),
  CRT_USER       VARCHAR2(255),
  CRT_NAME       VARCHAR2(255),
  CRT_HOST       VARCHAR2(20)
)
/

comment on table EXCEPTION_INFO
is '错误信息表'
/

comment on column EXCEPTION_INFO.ERR_MESSAGE
is '错误信息'
/

comment on column EXCEPTION_INFO.ERR_TYPE
is '错误种类'
/

comment on column EXCEPTION_INFO.STATUS
is '错误状态'
/

comment on column EXCEPTION_INFO.ERR_CLASS
is '错误类路径'
/

comment on column EXCEPTION_INFO.ERR_FILENAME
is '错误类的文件名'
/

comment on column EXCEPTION_INFO.ERR_METHOD
is '错误方法、接口、请求'
/

comment on column EXCEPTION_INFO.ERR_LINENUMBER
is '错误行数'
/

comment on column EXCEPTION_INFO.ERR_DETAIL
is '错误具体信息'
/

