package com.bndrs.voice.config;


import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;

/**
 * 数据源信息配置
 */
@Configuration("DataSourceConfiguration")
@MapperScan("com.bndrs.voice.dao")
@Order(1)
public class DataSourceConfiguration
{

    private static final Logger log = LoggerFactory.getLogger(DataSourceConfiguration.class);
    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name="dataSource",value = "dataSource",initMethod = "init",destroyMethod = "close")     //声明其为Bean实例
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource()
    {

        DataSource datasource = DataSourceBuilder.create().type(dataSourceType).build();
        return datasource;
    }

}