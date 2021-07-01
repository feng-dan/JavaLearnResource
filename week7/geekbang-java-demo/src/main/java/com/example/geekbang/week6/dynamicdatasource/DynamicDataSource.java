package com.example.geekbang.week6.dynamicdatasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 9.（必做）读写分离 - 动态切换数据源版本 1.0
 *
 * @author fengdan
 * @date 2021年06月20日 21:15
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return CustomerContextHolder.getCustomerType();
    }
}
