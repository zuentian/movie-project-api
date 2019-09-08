-- auto-generated definition
create table ELEMENT
(
  ID          VARCHAR2(36) not null
    primary key,
  CODE        VARCHAR2(255),
  TYPE        VARCHAR2(255),
  NAME        VARCHAR2(255),
  URI         VARCHAR2(255),
  MENU_ID     VARCHAR2(36),
  PARENT_ID   VARCHAR2(36),
  PATH        VARCHAR2(255),
  METHOD      VARCHAR2(255),
  DESCRIPTION VARCHAR2(255),
  CRT_TIME    TIMESTAMP(6) default NULL,
  CRT_USER    VARCHAR2(255),
  CRT_NAME    VARCHAR2(255),
  CRT_HOST    VARCHAR2(20),
  ATTR1       VARCHAR2(255),
  ATTR2       VARCHAR2(255),
  ATTR3       VARCHAR2(255),
  ATTR4       VARCHAR2(255)
)
/
INSERT INTO MENU (ID, CODE, TITLE, PARENT_ID, HREF, ICON, TYPE, ORDER_NUM, DESCRIPTION, PATH, ENABLED, CRT_TIME, CRT_USER, CRT_NAME, CRT_HOST, UPD_TIME, UPD_USER, UPD_NAME, UPD_HOST, ATTR1, ATTR2, ATTR3, ATTR4, ATTR5, ATTR6, ATTR7, ATTR8) VALUES ('000000', 'baseManager', '基础配置管理', '0', '/admin', 'setting', 'dirt', 0, null, '/adminSys/baseManager', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO MENU (ID, CODE, TITLE, PARENT_ID, HREF, ICON, TYPE, ORDER_NUM, DESCRIPTION, PATH, ENABLED, CRT_TIME, CRT_USER, CRT_NAME, CRT_HOST, UPD_TIME, UPD_USER, UPD_NAME, UPD_HOST, ATTR1, ATTR2, ATTR3, ATTR4, ATTR5, ATTR6, ATTR7, ATTR8) VALUES ('0920c6c5-ebda-4a52-aa71-eab65cfd1fc0', 'menuManager', '菜单管理', '000000', '/admin/menu', 'menu-setting', 'menu', 2, '菜单信息的增删改查', null, null, TO_TIMESTAMP('2019-09-07 16:33:23.247000', 'YYYY-MM-DD HH24:MI:SS.FF6'), 'zuentian', 'Faker', '127.0.0.1', TO_TIMESTAMP('2019-09-07 16:35:15.536000', 'YYYY-MM-DD HH24:MI:SS.FF6'), 'zuentian', 'Faker', '127.0.0.1', null, null, null, null, null, null, null, null);
INSERT INTO MENU (ID, CODE, TITLE, PARENT_ID, HREF, ICON, TYPE, ORDER_NUM, DESCRIPTION, PATH, ENABLED, CRT_TIME, CRT_USER, CRT_NAME, CRT_HOST, UPD_TIME, UPD_USER, UPD_NAME, UPD_HOST, ATTR1, ATTR2, ATTR3, ATTR4, ATTR5, ATTR6, ATTR7, ATTR8) VALUES ('6ba211c0-93d7-470a-9ba9-27d319065d60', 'dictManager', '数据字典', '000000', '/admin/dict', 'dict-setting', 'menu', 3, '数据字典，一些重要编码翻译', null, null, TO_TIMESTAMP('2019-09-07 16:31:48.702000', 'YYYY-MM-DD HH24:MI:SS.FF6'), 'zuentian', 'Faker', '127.0.0.1', TO_TIMESTAMP('2019-09-07 16:32:12.659000', 'YYYY-MM-DD HH24:MI:SS.FF6'), 'zuentian', 'Faker', '127.0.0.1', null, null, null, null, null, null, null, null);
INSERT INTO MENU (ID, CODE, TITLE, PARENT_ID, HREF, ICON, TYPE, ORDER_NUM, DESCRIPTION, PATH, ENABLED, CRT_TIME, CRT_USER, CRT_NAME, CRT_HOST, UPD_TIME, UPD_USER, UPD_NAME, UPD_HOST, ATTR1, ATTR2, ATTR3, ATTR4, ATTR5, ATTR6, ATTR7, ATTR8) VALUES ('cbe07cb4-02d2-4a2f-b744-03a202da9932', 'userManager', '用户管理', '000000', '/admin/user', 'fa-user', 'menu', 1, '用户信息页面', null, null, TO_TIMESTAMP('2019-09-07 14:47:11.371000', 'YYYY-MM-DD HH24:MI:SS.FF6'), 'zuentian', 'Faker', '127.0.0.1', TO_TIMESTAMP('2019-09-07 14:48:02.934000', 'YYYY-MM-DD HH24:MI:SS.FF6'), 'zuentian', 'Faker', '127.0.0.1', null, null, null, null, null, null, null, null);
