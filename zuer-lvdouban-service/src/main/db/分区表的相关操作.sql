--按月创建分区表
create table test_part
(
    ID NUMBER(20) not null,
    REMARK VARCHAR2(1000),
    create_time DATE
)
PARTITION BY RANGE (CREATE_TIME) INTERVAL (numtoyminterval(1, 'month'))
(partition part_t01 values less than(to_date('2020-11-01', 'yyyy-mm-dd')));

--查看分区表的类型,RANGE：范围分区
select * from USER_PART_TABLES  where table_name='TEST_PART';

--查询当前表有多少分区
select table_name,partition_name from user_tab_partitions where table_name='TEST_PART';

--测试插入一条当月的数据和上个月的数据
insert into test_part values (111,'测试',TO_DATE('2020-09-06','YYYY-MM-DD'));
insert into test_part values (1,'测试',TO_DATE('2020-10-06','YYYY-MM-DD'));
insert into test_part values (0,'测试',TO_DATE('2020-11-06','YYYY-MM-DD'));
commit ;

--查询这个表的某个里的数据
select * from TEST_PART;
select * from TEST_PART partition(PART_T01);
select * from TEST_PART partition(SYS_P21);

--此时，如果更新分区键的时候，又跨了分区，会报错：ORA-14402: 更新分区关键字列将导致分区的更改
update test_part set create_time = TO_DATE('2020-09-12','YYYY-MM-DD') WHERE ID = 111;

-- 7. 作用是：允许分区表的分区键是可更新。
-- 当某一行更新时，如果更新的是分区列，并且更新后的列植不属于原来的这个分区，
-- 如果开启了这个选项，就会把这行从这个分区中 delete 掉，并加到更新后所属的分区，此时就会发生 rowid 的改变。
-- 相当于一个隐式的 delete + insert ，但是不会触发 insert/delete 触发器。
alter table test_part enable row movement;

--这个时候就可以分区键更新
update test_part set create_time = TO_DATE('2020-12-12','YYYY-MM-DD') WHERE ID = 111;
