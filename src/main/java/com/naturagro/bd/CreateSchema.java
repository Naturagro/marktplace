package com.naturagro.bd;

import java.sql.*;

public class CreateSchema {

    String url = "jdbc:mysql://localhost:3306/";
    String user = "root";
    String password = "123456";
    Connection connection = null;
    Statement statement = null;

    public void createSchema() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            String sql = "CREATE DATABASE IF NOT EXISTS naturagro";
            statement.executeUpdate(sql);
            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}

