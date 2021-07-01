package com.example.geekbang.week5.jdbc.dao;

import com.example.geekbang.week5.jdbc.entity.User;
import com.example.geekbang.week5.jdbc.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fengdan
 * @date 2021年06月06日 23:02
 */
public class UserDao {
    public void addUser(User user) throws SQLException {
        //参数用?表示，相当于占位符;
        String sql = "INSERT INTO `demo_jdbc`.`t_jdbc_user` (`name`) VALUES (?);";
        //获取数据库连接
        Connection connection = DBUtil.getConnection();
        //预编译sql语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //先对应SQL语句，给SQL语句传递参数
        preparedStatement.setString(1, user.getName());
        preparedStatement.execute();
    }

    public void delUser(Integer id) throws SQLException {
        String sql = "delete from `demo_jdbc`.`t_jdbc_user` where id=?";
        //获取数据库连接
        Connection connection = DBUtil.getConnection();
        //预编译sql语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //先对应SQL语句，给SQL语句传递参数
        preparedStatement.setInt(1, id);
        //执行SQL语句,返回一个结果
        preparedStatement.execute();
    }

    public void updateUser(User user) throws SQLException {
        String sql = "update `demo_jdbc`.`t_jdbc_user` set name=? where id=?";
        //获取数据库连接
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setInt(2, user.getId());
        preparedStatement.execute();
    }

    public User getById(Integer id) throws SQLException {
        String sql = "select id,name from t_jdbc_user where id=?";
        User user = null;
        //获取数据库连接
        Connection connection = DBUtil.getConnection();
        //预编译sql语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //先对应SQL语句，给SQL语句传递参数
        preparedStatement.setInt(1, id);
        //执行SQL语句,返回一个结果
        ResultSet resultSet = preparedStatement.executeQuery();
        //遍历结果
        while (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
        }
        return user;
    }


    public List<User> queryList() throws SQLException {
        String sql = "select id,name from t_jdbc_user";
        List<User> users = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        User user = null;
        while (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            users.add(user);
        }
        return users;
    }
}
