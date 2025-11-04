package com.placement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/placementdb";
    private static final String USER = "hari-nts0210";
    private static final String PASSWORD = "hari123";

    static {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("PostgreSQL Driver loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading PostgreSQL Driver!");
            e.printStackTrace();
        }
    }

    // ✅ Always return a *new* connection for each request
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("❌ Failed to establish database connection!");
            e.printStackTrace();
            return null;
        }
    }
}
