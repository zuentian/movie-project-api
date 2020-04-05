create table CRAWLER_ACCOUNT
(
	ID VARCHAR2(36) not null
		primary key,
	WEB VARCHAR2(100),
	WEB_NAME VARCHAR2(100),
	ACCOUNT VARCHAR2(50),
	PASSWORD VARCHAR2(50),
	ALT_DATE DATE,
	CRT_DATE DATE,
	FLAG VARCHAR2(2)
)
/

comment on table CRAWLER_ACCOUNT is '爬虫账号'
/

comment on column CRAWLER_ACCOUNT.ACCOUNT is '账号'
/

comment on column CRAWLER_ACCOUNT.PASSWORD is '密码'
/

comment on column CRAWLER_ACCOUNT.FLAG is '0-未启用；1-已启用'
/

