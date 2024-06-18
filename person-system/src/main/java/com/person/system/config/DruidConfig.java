package com.person.system.config;

import javax.sql.DataSource;

import com.person.system.config.properties.DruidProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * druid 数据源
 *
 * @author sk
 */
@Configuration
public class DruidConfig
{
    @Bean
    @ConfigurationProperties("spring.datasource.druid.person")
    public DataSource masterDataSource(DruidProperties druidProperties)
    {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }
}
