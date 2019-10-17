
-- auto-generated definition
create table DICT
(
    DICT_ID        VARCHAR2(36) not null
        primary key,
    DICT_TYPE      VARCHAR2(50),
    DICT_TYPE_NAME VARCHAR2(50),
    DICT_CODE      VARCHAR2(50),
    DICT_VALUE     VARCHAR2(50),
    CRT_TIME       VARCHAR2(30),
    ALT_TIME       VARCHAR2(30)
)
/

comment on table DICT is '数据字典表'
/

comment on column DICT.DICT_ID is '数据字典ID'
/

comment on column DICT.DICT_TYPE is '数据字典类型'
/

comment on column DICT.DICT_TYPE_NAME is '数据字典类型名称'
/

comment on column DICT.DICT_CODE is '数据字典编码'
/

comment on column DICT.DICT_VALUE is '数据字典数值'
/



INSERT INTO  DICT (DICT_ID, DICT_TYPE, DICT_TYPE_NAME, DICT_CODE, DICT_VALUE, CRT_TIME, ALT_TIME) VALUES ('b1c23b40-b82d-4335-9c22-b41868b2aef1', 'MOVIECOUNTRY', '电影出品方国家地区', 'KOR', '韩国', '2019-09-25 15:29:59', '2019-09-25 15:29:59');
INSERT INTO  DICT (DICT_ID, DICT_TYPE, DICT_TYPE_NAME, DICT_CODE, DICT_VALUE, CRT_TIME, ALT_TIME) VALUES ('4361ec7a-41f4-4ca7-9e5f-d56a5310866c', 'MOVIECOUNTRY', '电影出品方国家地区', 'TWN', '中国台湾', '2019-09-25 15:29:30', '2019-09-25 15:29:30');
INSERT INTO  DICT (DICT_ID, DICT_TYPE, DICT_TYPE_NAME, DICT_CODE, DICT_VALUE, CRT_TIME, ALT_TIME) VALUES ('181b75b2-5e43-49e4-9521-3f58b867d840', 'MOVIECOUNTRY', '电影出品方国家地区', 'USA', '美国', '2019-09-25 15:30:23', '2019-09-25 15:30:23');
INSERT INTO  DICT (DICT_ID, DICT_TYPE, DICT_TYPE_NAME, DICT_CODE, DICT_VALUE, CRT_TIME, ALT_TIME) VALUES ('43119b2a-4ce5-4453-b70f-9ae71b91a9dc', 'MOVIECOUNTRY', '电影出品方国家地区', 'GBR', '英国', '2019-09-25 15:30:52', '2019-09-25 15:30:52');
INSERT INTO  DICT (DICT_ID, DICT_TYPE, DICT_TYPE_NAME, DICT_CODE, DICT_VALUE, CRT_TIME, ALT_TIME) VALUES ('8aa36c02-aa00-4f8e-88c8-acf9ac2a81dd', 'MOVIECOUNTRY', '电影出品方国家地区', 'IND', '印度', '2019-09-25 15:31:20', '2019-09-25 15:31:20');
INSERT INTO  DICT (DICT_ID, DICT_TYPE, DICT_TYPE_NAME, DICT_CODE, DICT_VALUE, CRT_TIME, ALT_TIME) VALUES ('567cb493-2fb5-4a0a-8e4c-d3755fed5981', 'MOVIETYPE', '电影类型', 'feature', '剧情', '2019-09-25 15:38:51', '2019-09-25 15:38:51');
INSERT INTO  DICT (DICT_ID, DICT_TYPE, DICT_TYPE_NAME, DICT_CODE, DICT_VALUE, CRT_TIME, ALT_TIME) VALUES ('dbf5effe-3198-42e1-ac62-912331724f51', 'MOVIETYPE', '电影类型', 'Cartoon', '动画', '2019-09-25 15:39:42', '2019-09-25 15:44:45');
INSERT INTO  DICT (DICT_ID, DICT_TYPE, DICT_TYPE_NAME, DICT_CODE, DICT_VALUE, CRT_TIME, ALT_TIME) VALUES ('61462981-9c3e-4719-b339-b06881a8a470', 'MOVIETYPE', '电影类型', 'Action', '动作', '2019-09-25 15:43:51', '2019-09-25 15:43:51');
INSERT INTO  DICT (DICT_ID, DICT_TYPE, DICT_TYPE_NAME, DICT_CODE, DICT_VALUE, CRT_TIME, ALT_TIME) VALUES ('f761eec3-b6b6-4c65-8b79-ef5c6c09ed15', 'MOVIETYPE', '电影类型', 'Comedy', '喜剧', '2019-09-25 15:44:14', '2019-09-25 15:44:14');
INSERT INTO  DICT (DICT_ID, DICT_TYPE, DICT_TYPE_NAME, DICT_CODE, DICT_VALUE, CRT_TIME, ALT_TIME) VALUES ('e2de7116-d71b-4a6b-8a7e-385e8e72875e', 'MOVIETYPE', '电影类型', 'Affectional', '爱情', '2019-09-25 15:45:14', '2019-09-25 15:45:14');
INSERT INTO  DICT (DICT_ID, DICT_TYPE, DICT_TYPE_NAME, DICT_CODE, DICT_VALUE, CRT_TIME, ALT_TIME) VALUES ('af06e1f3-c669-45fc-9315-a23166fd607a', 'MOVIETYPE', '电影类型', 'Fantasy', '奇幻', '2019-09-25 15:45:35', '2019-09-25 15:45:35');
INSERT INTO  DICT (DICT_ID, DICT_TYPE, DICT_TYPE_NAME, DICT_CODE, DICT_VALUE, CRT_TIME, ALT_TIME) VALUES ('900fa6d5-745c-480b-be58-da6f5511ada2', 'MOVIETYPE', '电影类型', 'Science fiction', '科幻', '2019-09-25 15:46:09', '2019-09-25 15:46:09');
INSERT INTO  DICT (DICT_ID, DICT_TYPE, DICT_TYPE_NAME, DICT_CODE, DICT_VALUE, CRT_TIME, ALT_TIME) VALUES ('26dd9691-3ad6-46b0-a7c4-46de96f58525', 'MOVIECOUNTRY', '电影出品方国家地区', 'CHN', '中国大陆', '2019-09-25 15:10:54', '2019-09-25 15:10:54');
INSERT INTO  DICT (DICT_ID, DICT_TYPE, DICT_TYPE_NAME, DICT_CODE, DICT_VALUE, CRT_TIME, ALT_TIME) VALUES ('2adda1dc-eec8-477f-8a58-c3f511d335de', 'MOVIECOUNTRY', '电影出品方国家地区', 'JPN', '日本', '2019-09-25 15:11:41', '2019-09-25 15:11:41');
INSERT INTO  DICT (DICT_ID, DICT_TYPE, DICT_TYPE_NAME, DICT_CODE, DICT_VALUE, CRT_TIME, ALT_TIME) VALUES ('6789d413-17d2-4217-ac8a-8ab8df35b729', 'MOVIECOUNTRY', '电影出品方国家地区', 'HKG', '中国香港', '2019-09-25 15:12:15', '2019-09-25 15:12:15');









