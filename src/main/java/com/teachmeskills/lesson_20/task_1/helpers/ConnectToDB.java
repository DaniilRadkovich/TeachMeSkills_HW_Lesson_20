package com.teachmeskills.lesson_20.task_1.helpers;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectToDB {
    public Connection connectionToDB(){
        try {
            String url = "jdbc:mysql://localhost:3306/students_schema";
            String username = "root";
            String password = "daniilradkovich";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                return connection;
            }
        } catch (Exception e) {
            System.out.println("Connection failed...");

            System.out.println(e);
        }
        return null;
    }
}
