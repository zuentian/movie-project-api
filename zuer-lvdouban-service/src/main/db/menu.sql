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
INSERT INTO MENU (ID, CODE, TITLE, PARENT_ID, HREF, ICON, TYPE, ORDER_NUM, DESCRIPTION, PATH, ENABLED, CRT_TIME, CRT_USER, CRT_NAME, CRT_HOST, UPD_TIME, UPD_USER, UPD_NAME, UPD_HOST, ATTR1, ATTR2, ATTR3, ATTR4, ATTR5, ATTR6, ATTR7, ATTR8) VALUES ('000000', 'baseManager', '基础配置管理', '0', '/admin', 'setting', 'dirt', 0, null, '/adminSys/baseManager', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO MENU (ID, CODE, TITLE, PARENT_ID, HREF, ICON, TYPE, ORDER_NUM, DESCRIPTION, PATH, ENABLED, CRT_TIME, CRT_USER, CRT_NAME, CRT_HOST, UPD_TIME, UPD_USER, UPD_NAME, UPD_HOST, ATTR1, ATTR2, ATTR3, ATTR4, ATTR5, ATTR6, ATTR7, ATTR8) VALUES ('0920c6c5-ebda-4a52-aa71-eab65cfd1fc0', 'menuManager', '菜单管理', '000000', '/admin/menu', 'menu-setting', 'menu', 2, '菜单信息的增删改查', null, null, TO_TIMESTAMP('2019-09-07 16:33:23.247000', 'YYYY-MM-DD HH24:MI:SS.FF6'), 'zuentian', 'Faker', '127.0.0.1', TO_TIMESTAMP('2019-09-07 16:35:15.536000', 'YYYY-MM-DD HH24:MI:SS.FF6'), 'zuentian', 'Faker', '127.0.0.1', null, null, null, null, null, null, null, null);
INSERT INTO MENU (ID, CODE, TITLE, PARENT_ID, HREF, ICON, TYPE, ORDER_NUM, DESCRIPTION, PATH, ENABLED, CRT_TIME, CRT_USER, CRT_NAME, CRT_HOST, UPD_TIME, UPD_USER, UPD_NAME, UPD_HOST, ATTR1, ATTR2, ATTR3, ATTR4, ATTR5, ATTR6, ATTR7, ATTR8) VALUES ('6ba211c0-93d7-470a-9ba9-27d319065d60', 'dictManager', '数据字典', '000000', '/admin/dict', 'dict-setting', 'menu', 3, '数据字典，一些重要编码翻译', null, null, TO_TIMESTAMP('2019-09-07 16:31:48.702000', 'YYYY-MM-DD HH24:MI:SS.FF6'), 'zuentian', 'Faker', '127.0.0.1', TO_TIMESTAMP('2019-09-07 16:32:12.659000', 'YYYY-MM-DD HH24:MI:SS.FF6'), 'zuentian', 'Faker', '127.0.0.1', null, null, null, null, null, null, null, null);
INSERT INTO MENU (ID, CODE, TITLE, PARENT_ID, HREF, ICON, TYPE, ORDER_NUM, DESCRIPTION, PATH, ENABLED, CRT_TIME, CRT_USER, CRT_NAME, CRT_HOST, UPD_TIME, UPD_USER, UPD_NAME, UPD_HOST, ATTR1, ATTR2, ATTR3, ATTR4, ATTR5, ATTR6, ATTR7, ATTR8) VALUES ('cbe07cb4-02d2-4a2f-b744-03a202da9932', 'userManager', '用户管理', '000000', '/admin/user', 'fa-user', 'menu', 1, '用户信息页面', null, null, TO_TIMESTAMP('2019-09-07 14:47:11.371000', 'YYYY-MM-DD HH24:MI:SS.FF6'), 'zuentian', 'Faker', '127.0.0.1', TO_TIMESTAMP('2019-09-07 14:48:02.934000', 'YYYY-MM-DD HH24:MI:SS.FF6'), 'zuentian', 'Faker', '127.0.0.1', null, null, null, null, null, null, null, null);


commit;