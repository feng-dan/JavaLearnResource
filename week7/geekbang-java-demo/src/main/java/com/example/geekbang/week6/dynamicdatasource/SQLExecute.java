package com.example.geekbang.week6.dynamicdatasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author fengdan
 * @date 2021年06月20日 21:42
 */
@Component
public class SQLExecute {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @ConnectionDB
    public void insertData() {
        String sql = "INSERT INTO t_user(user_name,phone,flag) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, new Object[]{"fengdan0", "18808521128", 0});

    }

    @ConnectionDB("test_db")
    public void insertData2() {
        String sql = "INSERT INTO t_user(user_name,phone,flag) VALUES ( ?, ?, ?)";
        jdbcTemplate.update(sql, new Object[]{"fengdan1", "18808521128", 0});

    }

    @ConnectionDB("test_db0")
    public void insertData3() {
        String sql = "INSERT INTO t_user(user_name,phone,flag) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, new Object[]{"fengdan2", "18808521128", 0});

    }
}
