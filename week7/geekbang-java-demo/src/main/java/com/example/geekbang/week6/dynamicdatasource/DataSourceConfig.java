package com.example.geekbang.week6.dynamicdatasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fengdan
 * @date 2021年06月20日 21:27
 */
@ComponentScan("com.example.geekbang.week6.dynamicdatasource")
@EnableAspectJAutoProxy
@Configuration
public class DataSourceConfig {

    @Bean(initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataSourceTest_db() throws SQLException {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setInitialSize(2);
        datasource.setMaxActive(10);
        datasource.setMaxWait(30000);
        datasource.setValidationQuery("SELECT 1");
        datasource.setTestOnBorrow(false);
        datasource.setTestOnReturn(false);
        datasource.setTestWhileIdle(true);
        datasource.setTimeBetweenEvictionRunsMillis(60000);
        datasource.setMinEvictableIdleTimeMillis(25200000);
        datasource.setRemoveAbandoned(true);
        datasource.setRemoveAbandonedTimeout(1800);
        datasource.setLogAbandoned(true);
        datasource.setFilters("mergeStat");
        datasource.setUrl("jdbc:mysql://localhost:3306/test_db?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
        datasource.setUsername("root");
        datasource.setPassword("123456");
        datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        datasource.setMaxActive(15);
        return datasource;

    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataSourceTest_db0() throws SQLException {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setInitialSize(2);
        datasource.setMaxActive(10);
        datasource.setMaxWait(30000);
        datasource.setValidationQuery("SELECT 1");
        datasource.setTestOnBorrow(false);
        datasource.setTestOnReturn(false);
        datasource.setTestWhileIdle(true);
        datasource.setTimeBetweenEvictionRunsMillis(60000);
        datasource.setMinEvictableIdleTimeMillis(25200000);
        datasource.setRemoveAbandoned(true);
        datasource.setRemoveAbandonedTimeout(1800);
        datasource.setLogAbandoned(true);
        datasource.setFilters("mergeStat");
        datasource.setUrl("jdbc:mysql://localhost:3306/test_db0?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
        datasource.setUsername("root");
        datasource.setPassword("123456");
        datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        datasource.setMaxActive(15);
        return datasource;
    }

    @Bean
    public DynamicDataSource dynamicDataSource() throws SQLException {
        DynamicDataSource data = new DynamicDataSource();
        DruidDataSource dataSourceTest_db = dataSourceTest_db();
        DruidDataSource dataSourceTest_db0 = dataSourceTest_db0();
        System.out.println("test_db:\t" + dataSourceTest_db.getUrl());
        System.out.println("test_db0:\t" + dataSourceTest_db0.getUrl());
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("test_db", dataSourceTest_db);
        map.put("test_db0", dataSourceTest_db0);
        data.setTargetDataSources(map);
        data.setDefaultTargetDataSource(dataSourceTest_db());
        return data;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws SQLException {
        JdbcTemplate tem = new JdbcTemplate();
        tem.setDataSource(dynamicDataSource());
        return tem;
    }

}
