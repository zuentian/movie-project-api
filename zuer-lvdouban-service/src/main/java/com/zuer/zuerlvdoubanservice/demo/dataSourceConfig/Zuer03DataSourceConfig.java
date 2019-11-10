package com.zuer.zuerlvdoubanservice.demo.dataSourceConfig;

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

@Configuration
@MapperScan(basePackages = Zuer03DataSourceConfig.PACKAGE, sqlSessionFactoryRef = "zuer03SqlSessionFactory")
public class Zuer03DataSourceConfig {
    static final String PACKAGE = "com.zuer.zuerlvdoubanservice.demo";
    static final String MAPPER_LOCATION = "classpath:mapper/zuer03/*.xml";
    @Value("${zuer03.spring.datasource.url}")
    private String url;

    @Value("${zuer03.spring.datasource.username}")
    private String user;

    @Value("${zuer03.spring.datasource.password}")
    private String password;

    @Value("${zuer03.spring.datasource.driver-class-name}")
    private String driverClass;

    @Bean(name = "zuer03DataSource")
    public DataSource zuer03DataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "zuer03TransactionManager")
    public DataSourceTransactionManager zuer03TransactionManager() {
        return new DataSourceTransactionManager(zuer03DataSource());
    }
    @Bean(name = "zuer03SqlSessionFactory")
    public SqlSessionFactory zuer03SqlSessionFactory(@Qualifier("zuer03DataSource") DataSource zuer03DataSource)throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(zuer03DataSource);
        sessionFactory.setMapperLocations(
        new PathMatchingResourcePatternResolver().getResources(Zuer03DataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
