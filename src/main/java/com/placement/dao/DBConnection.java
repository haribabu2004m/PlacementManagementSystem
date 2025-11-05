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
            Class.forName("org.postgresql.Driver");
            System.out.println("✅ PostgreSQL Driver loaded successfully!");

            Properties props = new Properties();

            // Load config.properties from classpath (resources folder)
            try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("config.properties")) {
                if (input == null) {
                    System.err.println("⚠️ config.properties not found in resources folder!");
                } else {
                    props.load(input);
                    URL = props.getProperty("db.url");
                    USER = props.getProperty("db.user");
                    PASSWORD = props.getProperty("db.password");
                    System.out.println("✅ Database configuration loaded successfully!");
                }
            }

        } catch (ClassNotFoundException e) {
            System.err.println("❌ PostgreSQL Driver not found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("⚠️ Failed to load config.properties!");
            e.printStackTrace();
        }
    }


    // ✅ Always return a new connection for each request
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("❌ Failed to establish database connection!");
            System.err.println("URL: " + URL);
            System.err.println("USER: " + USER);
            System.err.println("PASSWORD: " + PASSWORD); // temporary — remove later!
            e.printStackTrace();
            return null;
        }

    }
}
