package com.jie.demo.demodbmove.conf;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.from")
    public DataSource fromDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.to")
    public DataSource toDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate fromJdbcTemplate(@Qualifier("fromDatasource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public JdbcTemplate toJdbcTemplate(@Qualifier("toDatasource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
