package com.example.geekbang.week6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author fengdan
 * @date 2021年06月06日 22:45
 */
public class DBUtil {
    private static final String url = "jdbc:mysql://localhost:3306/test_db?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&rewriteBatchedStatements=true";
    private static final String username = "root";
    private static final String password = "123456";
    private static Connection CONNECTION = null;

    static {
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            //2.获得数据库的连接
            CONNECTION = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return CONNECTION;
    }


}
