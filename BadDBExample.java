package com.example.badcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BadDBExample {

    public static Connection getConnection() throws SQLException {
        // ❌ Hardcoded credentials
        String url = "jdbc:mysql://localhost:3306/mydb";
        String user = "admin";
        String password = "password123";
        return DriverManager.getConnection(url, user, password);
    }

    public void runQuery() {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();

            // ❌ Vulnerable SQL (SQL injection)
            String username = "admin' OR '1'='1";
            String query = "SELECT * FROM users WHERE username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(query);

            // ❌ Not closing resources
        } catch (SQLException e) {
            // ❌ Empty catch block
        }
    }
}
