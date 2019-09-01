-- auto-generated definition
create table MENU
(
  ID          VARCHAR2(36) not null,
  CODE        VARCHAR2(255) default NULL,
  TITLE       VARCHAR2(255) default NULL,
  PARENT_ID   VARCHAR2(36) ,
  HREF        VARCHAR2(255) default NULL,
  ICON        VARCHAR2(255) default NULL,
  TYPE        VARCHAR2(10)  default NULL,
  ORDER_NUM   NUMBER(11),
  DESCRIPTION VARCHAR2(255) default NULL,
  PATH        VARCHAR2(500) default NULL,
  ENABLED     VARCHAR2(1)   default NULL,
  CRT_TIME    TIMESTAMP(6)  default NULL,
  CRT_USER    VARCHAR2(255) default NULL,
  CRT_NAME    VARCHAR2(255) default NULL,
  CRT_HOST    VARCHAR2(255) default NULL,
  UPD_TIME    TIMESTAMP(6)  default NULL,
  UPD_USER    VARCHAR2(255) default NULL,
  UPD_NAME    VARCHAR2(255) default NULL,
  UPD_HOST    VARCHAR2(255) default NULL,
  ATTR1       VARCHAR2(255) default NULL,
  ATTR2       VARCHAR2(255) default NULL,
  ATTR3       VARCHAR2(255) default NULL,
  ATTR4       VARCHAR2(255) default NULL,
  ATTR5       VARCHAR2(255) default NULL,
  ATTR6       VARCHAR2(255) default NULL,
  ATTR7       VARCHAR2(255) default NULL,
  ATTR8       VARCHAR2(255) default NULL
)
/

comment on column MENU.CODE
is '路径编码'
/

comment on column MENU.TITLE
is '标题'
/

comment on column MENU.PARENT_ID
is '父级节点'
/

comment on column MENU.HREF
is '资源路径'
/

comment on column MENU.ICON
is '图标'
/

comment on column MENU.ORDER_NUM
is '排序'
/

comment on column MENU.DESCRIPTION
is '描述'
/

comment on column MENU.PATH
is '菜单上下级关系'
/

comment on column MENU.ENABLED
is '启用禁用'
/
INSERT INTO MENU (ID, CODE, TITLE, PARENT_ID, HREF, ICON, TYPE, ORDER_NUM, DESCRIPTION, PATH, ENABLED, CRT_TIME, CRT_USER, CRT_NAME, CRT_HOST, UPD_TIME, UPD_USER, UPD_NAME, UPD_HOST, ATTR1, ATTR2, ATTR3, ATTR4, ATTR5, ATTR6, ATTR7, ATTR8) VALUES ('00000999', 'userManager', '用户管理', '000000', '/admin/user', 'fa-user', 'menu', 0, null, '/adminSys/baseManager/userManager', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO MENU (ID, CODE, TITLE, PARENT_ID, HREF, ICON, TYPE, ORDER_NUM, DESCRIPTION, PATH, ENABLED, CRT_TIME, CRT_USER, CRT_NAME, CRT_HOST, UPD_TIME, UPD_USER, UPD_NAME, UPD_HOST, ATTR1, ATTR2, ATTR3, ATTR4, ATTR5, ATTR6, ATTR7, ATTR8) VALUES ('000000', 'baseManager', '基础配置管理', null, '/admin', 'setting', 'dirt', 0, null, '/adminSys/baseManager', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
