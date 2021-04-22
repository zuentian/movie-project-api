# movie-project-api
完整的后端项目---电影管理平台对应的接口

开发过程：

springboot2.0

springcloud(Finchley.RELEASE)

整合mybatis,使用tk.myBatis

Eureka

Feign

shiro权限的初步整合

完成多数据源

开启mybatis二级缓存

auth增加热启动配置（电脑性能不好，不想每次修改类都要启动SpringBoot）

增添logback日志功能

Transactional事务

增添redis缓存（部分使用）

重新规划每个模块的pom.xml，可以正常打包，并且暂时将movie-project-eureka模块能够进行不同环境的打包

整合tx-lcn事务，处理分布式事务(待开发)