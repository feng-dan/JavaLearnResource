package com.example.geekbang.week6;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 2.（必做）按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率
 * 存储过程实现
 * DROP PROCEDURE IF EXISTS initData; -- 如果存在此存储过程则删掉
 * DELIMITER $
 * CREATE PROCEDURE initData()
 * BEGIN
 * DECLARE i INT DEFAULT 1;
 * WHILE i <= 1000000
 * DO
 * INSERT INTO test_db.t_user(user_name,phone,flag) VALUES (CONCAT('用户名', i), '18808521128', 0);
 * SET i = i + 1;
 * END WHILE;
 * END $
 * CALL initData();  用时 17 m 44 s 99 ms
 *
 * @author fengdan
 * @date 2021年06月20日 19:25
 */
public class InsertDBExample {
    public static void main(String[] args) throws SQLException {
//        insertCommit();
        insertBatch();
    }

    /**
     * 使用事务提交方式
     */
    private static void insertCommit() throws SQLException {
        Connection connection =
                DBUtil.getConnection();
        String sql = "INSERT INTO test_db.t_user(user_name,phone,flag) VALUES (CONCAT('用户名', ?), ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= 1000000; i++) {
            ps.setInt(1, i);
            ps.setInt(2, i);
            ps.setInt(3, 0);
            ps.executeUpdate();
        }
        connection.commit();
        long endTime = System.currentTimeMillis();
        System.out.println("OK,用时：" + (endTime - startTime));
        ps.close();
        connection.close();
    }

    /**
     * jdbc 实现 开启批处理 rewriteBatchedStatements=true
     * max_allowed_packet 参数需要调整
     */
    private static void insertBatch() throws SQLException {
        Connection connection =
                DBUtil.getConnection();
        String sql = "INSERT INTO test_db.t_user(user_name,phone,flag) VALUES (CONCAT('用户名', ?), ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= 1000000; i++) {
            ps.setInt(1, i);
            ps.setInt(2, i);
            ps.setInt(3, 0);
            ps.addBatch();
        }
        ps.executeBatch();
        long endTime = System.currentTimeMillis();
        System.out.println("OK,用时：" + (endTime - startTime));
        ps.close();
        connection.close();
    }

}
