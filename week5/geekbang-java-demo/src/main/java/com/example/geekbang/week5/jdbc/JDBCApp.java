package com.example.geekbang.week5.jdbc;

import com.example.geekbang.week5.jdbc.dao.UserDao;
import com.example.geekbang.week5.jdbc.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * 10.（必做）研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：
 * 1）使用 JDBC 原生接口，实现数据库的增删改查操作。
 *
 * @author fengdan
 */
public class JDBCApp {
    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDao();

        //addUser
        System.out.println("-----addUser-----");
        User addUser = new User(null, "孙悟空");
        userDao.addUser(addUser);


        //deleteUser
        System.out.println("-----deleteUser-----");
        userDao.delUser(1);


        //updateUser
        System.out.println("-----updateUser-----");
        User updateUser = new User(6, "齐天大圣");
        userDao.updateUser(updateUser);

        //list
        List<User> users = userDao.queryList();
        System.out.println("----queryList------");
        if (!users.isEmpty()) {
            users.forEach(user -> System.out.println("queryList:" + user.toString()));
        }

        //getById
        System.out.println("----getById------");
        User user = userDao.getById(6);
        System.out.println("getById:" + user.toString());
    }

}
