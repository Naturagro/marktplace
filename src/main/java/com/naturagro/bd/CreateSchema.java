package com.naturagro.bd;

import java.sql.*;

public class CreateSchema {

    // URL de conexão JDBC para o MySQL
    String url = "jdbc:mysql://localhost:3306/";
    // Nome de usuário do MySQL
    String user = "root";
    // Senha do MySQL
    String password = "123456";

    Connection connection = null;
    Statement statement = null;

    public void createSchema() {

        try {
            // Estabelecer a conexão
            connection = DriverManager.getConnection(url, user, password);
            // Criar um Statement
            statement = connection.createStatement();


            // Executar uma query SQL (por exemplo, selecionar todos os funcionários)
            String sql = "CREATE DATABASE IF NOT EXISTS naturagro";

            statement.executeUpdate(sql);

            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}

