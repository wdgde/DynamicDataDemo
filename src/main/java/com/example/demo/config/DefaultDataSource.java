package com.example.demo.config;

import com.example.demo.bean.Hikari;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: DynamicDataDemo
 * @description:
 * @author: wdgde
 * @create: 2020-06-12 15:36
 **/
@Configuration
@MapperScan(basePackages = "com.example.demo.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
@ConfigurationProperties(prefix = "spring.datasource.my")
public class DefaultDataSource {

    private List<Hikari> hikari = new ArrayList<>();

    public List<Hikari> getHikari() {
        return hikari;
    }

    public void setHikari(List<Hikari> hikari) {
        this.hikari = hikari;
    }

    @Bean("dynamicDataSource")
    public DynamicDataSource dynamicDataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        List<Hikari> hikari = getHikari();
        for (int i = 0; i < hikari.size(); i++) {
            HikariConfig config = new HikariConfig();
            config.setDriverClassName(hikari.get(i).getDriverClassName());
            config.setJdbcUrl(hikari.get(i).getJdbcUrl());
            config.setUsername(hikari.get(i).getUsername());
            config.setPassword(hikari.get(i).getPassword());
            HikariDataSource hikariDataSource = new HikariDataSource(config);
            targetDataSources.put(i + "", hikariDataSource);
        }
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSources);
        //默认源
        dynamicDataSource.setDefaultTargetDataSource(targetDataSources.get("0"));
        return dynamicDataSource;
    }


    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        return sessionFactory.getObject();
    }
}