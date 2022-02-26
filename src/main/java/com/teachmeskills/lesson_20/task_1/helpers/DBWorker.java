package com.teachmeskills.lesson_20.task_1.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWorker {

    private final String URL = "jdbc:mysql://localhost:3306/students_schema";
    private final String USERNAME = "root";
    private final String PASSWORD = "daniilradkovich";


    private Connection connection;

    public DBWorker (){
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            System.out.println(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
