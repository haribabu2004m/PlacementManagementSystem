package com.placement.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static final String CONFIG_PATH = "/home/hari/IdeaProjects/StudentManagementSystem/config.properties";
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        try {
            // Load PostgreSQL Driver
            Class.forName("org.postgresql.Driver");
            System.out.println("✅ PostgreSQL Driver loaded successfully!");

            // Load configuration from properties file
            Properties props = new Properties();
            try (InputStream input = new FileInputStream(CONFIG_PATH)) {
                props.load(input);
                URL = props.getProperty("db.url");
                USER = props.getProperty("db.user");
                PASSWORD = props.getProperty("db.password");
                System.out.println("✅ Database configuration loaded successfully!");
            } catch (IOException e) {
                System.err.println("⚠️ Failed to load database configuration file!");
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            System.err.println("❌ Error loading PostgreSQL Driver!");
            e.printStackTrace();
        }
    }

    // ✅ Always return a new connection for each request
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("❌ Failed to establish database connection!");
            e.printStackTrace();
            return null;
        }
    }
}
