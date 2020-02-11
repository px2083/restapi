package com.spring.boot.api.apiservice.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DatabaseConfig {

    @Autowired
    private DatabaseProperty dbProperty;

    public DataSource createDataSource(String url) {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClass(org.mariadb.jdbc.Driver.class);
        dataSource.setUsername(dbProperty.getUsername());
        dataSource.setPassword(dbProperty.getPassword());

        return dataSource;
    }
    
    @Bean
    public DataSource routingDataSource() {
        ReplicationRoutingDataSource replicationRoutingDataSource = new ReplicationRoutingDataSource();

            DataSource master = createDataSource(dbProperty.getUrl());

            Map<Object, Object> dataSourceMap = new LinkedHashMap<>();
            dataSourceMap.put("master", master);

            dbProperty.getSlaveList().forEach(slave -> {
                dataSourceMap.put(slave.getName(), createDataSource(slave.getUrl()));
            });

            replicationRoutingDataSource.setTargetDataSources(dataSourceMap);
            replicationRoutingDataSource.setDefaultTargetDataSource(master);
            return replicationRoutingDataSource;
        }

    @Bean
    public DataSource dataSource() {
        return new LazyConnectionDataSourceProxy(routingDataSource());
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("com.spring.boot.api.apiservice");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);

        return entityManagerFactoryBean;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory);
        return tm;
    }
}