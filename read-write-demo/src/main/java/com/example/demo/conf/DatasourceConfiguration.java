package com.example.demo.conf;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.example.demo.util.DBContextHolder;
import com.example.demo.util.DBTypeEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@EnableTransactionManagement
@Configuration
public class DatasourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.slave1")
    public DataSource slave1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.slave2")
    public DataSource slave2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSource myRoutingDataSource(
            @Qualifier("masterDataSource") DataSource masterDatasource,
            @Qualifier("slave1DataSource") DataSource slave1Datasource,
            @Qualifier("slave2DataSource") DataSource slave2Datasource) {

        HashMap<Object, Object> map = new HashMap<>();
        map.put(DBTypeEnum.MASTER, masterDatasource);
        map.put(DBTypeEnum.SLAVE_1, slave1Datasource);
        map.put(DBTypeEnum.SLAVE_2, slave2Datasource);

        MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
        myRoutingDataSource.setDefaultTargetDataSource(masterDatasource);
        myRoutingDataSource.setTargetDataSources(map);

        return myRoutingDataSource;
    }

    @Primary
    @Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean(@Qualifier("myRoutingDataSource") DataSource datasource) {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(datasource);
        return bean;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(@Qualifier("myRoutingDataSource") DataSource datasource) {
        return new DataSourceTransactionManager(datasource);
    }

    private static class MyRoutingDataSource extends AbstractRoutingDataSource{

        @Override
        protected Object determineCurrentLookupKey() {
            System.out.println("==================>  " + DBContextHolder.get());
            return DBContextHolder.get();
        }
    }
}
