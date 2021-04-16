package com.zuer.zuerlvdoubanservice.datasourceconfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
/*
springboot默认启动的时候会选择其中一个数据源，所以需要默认主数据源
因此加注解@Primary
 */
@Configuration
@MapperScan(basePackages = Zuer02DataSourceConfig.PACKAGE, sqlSessionFactoryRef = "zuer02SqlSessionFactory")
public class Zuer02DataSourceConfig {
    static final String PACKAGE = "com.zuer.zuerlvdoubanservice.service";
    static final String MAPPER_LOCATION = "classpath:mapper/zuer02/*.xml";
    @Value("${zuer02.springstudy.datasource.url}")
    private String url;

    @Value("${zuer02.springstudy.datasource.username}")
    private String user;

    @Value("${zuer02.springstudy.datasource.password}")
    private String password;

    @Value("${zuer02.springstudy.datasource.driver-class-name}")
    private String driverClass;

    @Bean(name = "zuer02DataSource")
    @Primary
    public DataSource zuer02DataSource() {
        System.out.println("加载zuer02数据库");
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "zuer02TransactionManager")
    @Primary
    public DataSourceTransactionManager zuer02TransactionManager() {
        return new DataSourceTransactionManager(zuer02DataSource());
    }
    @Bean(name = "zuer02SqlSessionFactory")
    @Primary
    public SqlSessionFactory zuer02SqlSessionFactory(@Qualifier("zuer02DataSource") DataSource zuer02DataSource)throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(zuer02DataSource);
        sessionFactory.setMapperLocations(
        new PathMatchingResourcePatternResolver().getResources(Zuer02DataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
