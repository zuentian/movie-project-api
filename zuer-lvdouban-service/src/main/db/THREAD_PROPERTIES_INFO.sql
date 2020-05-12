-- auto-generated definition
create table THREAD_PROPERTIES_INFO
(
    TYPE           VARCHAR2(36) not null
        primary key,
    CORE_POOL_SIZE NUMBER(3),
    MAX_POOL_SIZE  NUMBER(3),
    QUEUE_CAPACITY NUMBER(3),
    CRT_TIME       TIMESTAMP(6)  default NULL,
    CRT_USER       VARCHAR2(255) default NULL,
    CRT_NAME       VARCHAR2(255) default NULL,
    CRT_HOST       VARCHAR2(255) default NULL,
    UPD_TIME       TIMESTAMP(6)  default NULL,
    UPD_USER       VARCHAR2(255) default NULL,
    UPD_NAME       VARCHAR2(255) default NULL,
    UPD_HOST       VARCHAR2(255) default NULL
)
/

comment on table THREAD_PROPERTIES_INFO is '线程大小设置表';
/

comment on column THREAD_PROPERTIES_INFO.TYPE is '线程种类';
/
comment on column THREAD_PROPERTIES_INFO.CORE_POOL_SIZE is '核心线程数';
/

comment on column THREAD_PROPERTIES_INFO.MAX_POOL_SIZE is '最大线程数';
/

comment on column THREAD_PROPERTIES_INFO.QUEUE_CAPACITY is '队列最大长度';
/

