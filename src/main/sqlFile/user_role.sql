-- auto-generated definition
create table USER_ROLE
(
    ID       VARCHAR2(36) not null,
    USER_ID  VARCHAR2(36),
    ROLE_ID  VARCHAR2(36),
    CRT_TIME VARCHAR2(30),
    ALT_TIME VARCHAR2(30)
)
/

comment on table USER_ROLE is '用户和角色关联表'
/

comment on column USER_ROLE.ID is '主键ID'
/

comment on column USER_ROLE.USER_ID is '用户ID'
/

comment on column USER_ROLE.ROLE_ID is '角色ID'
/

create unique index USER_ROLE_ID_UINDEX
    on USER_ROLE (ID)
/

alter table USER_ROLE
    add constraint USER_ROLE_PK
        primary key (ID)
/

