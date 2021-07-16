#mysql_windows安装

**************安装**************

1、官网地址：https://dev.mysql.com/downloads/mysql/

2、选择Windows (x86, 64-bit), ZIP Archive，下载

3、解压文件目录，我的是：E:\mysql-8.0.25-winx64

4、修改环境变量，在path增加：E:\mysql-8.0.25-winx64\bin;

5、在E:\mysql-8.0.25-winx64目录下增加配置文件mysql.ini,内容如下，里面有端口号、默认存储引擎、设置安装目录和数据存放目录：
    
    [mysql]
    
    # 设置mysql客户端默认字符集
    default-character-set=utf8 
    
    [mysqld]
    
    #设置3306端口
    port = 3306 
    
    # 设置mysql的安装目录
    basedir=E:\mysql-8.0.25-winx64
    
    # 设置mysql数据库的数据的存放目录
    datadir=E:\mysql-8.0.25-winx64\data
    
    # 允许最大连接数
    max_connections=200
    
    # 服务端使用的字符集默认为8比特编码的latin1字符集
    character-set-server=utf8
    
    # 创建新表时将使用的默认存储引擎
    default-storage-engine=INNODB
  
6、新增E:\mysql-8.0.25-winx64\data文件夹

7、打开cmd，需要用管理员的身份运行，输入：mysqld --initialize-insecure --user=mysql，会执行一段时间

8、执行mysqld install，提示：Service successfully installed.

9、执行net start mysql，提示：MySQL 服务正在启动 .... MySQL 服务已经启动成功。

10、执行mysql -u root -p，第一次没有密码，直接回车跳过

11、退出之后执行mysqladmin -u root -p password修改密码，我自己设置的密码是password


********创建数据库********

1、创建新的数据库：create database zuersql DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

2、验证：show databases;


********新增用户********

1、打开cmd，执行mysql -u root -p，输入密码password

2、执行：create user "zuer04"@"localhost" identified by "password";

3、验证： select host,user,authentication_string from mysql.user;

4、给予查询权限：grant select on test.* to 'zuer04'@'localhost';
   添加插入权限：rant insert on test.* to 'user1'@'localhost';
   添加删除权限：grant delete on test.* to 'user1'@'localhost';
   添加更新权限：grant update on test.* to 'user1'@'localhost'; 
   授予用户在本地服务器对该数据库的全部权限：grant all privileges on test.* to 'zuer04'@'localhost';
   刷新权限：flush privileges;
   
5、查看权限：show grants for 'zuer04'@'localhost';


********赋权限********
1、mysql8.0版本的sql:
###### grant all privileges on `zuersql`.* to 'zuer04'@'%' identified by 'editest123456' with grant option;
我目前用的mysql版本是8.0.25，需要执行：grant all privileges on zuersql.* to 'zuer04'@'localhost' with grant option;


********idea登录mysql********

url8.0需要加后缀（由于数据库和系统时区差异所造成的）：
jdbc:mysql://localhost:3306/zuersql?serverTimezone=UTC


